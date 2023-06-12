package cart.persistence.repository;

import cart.entity.Member;
import cart.entity.Order;
import cart.entity.OrderInfo;
import cart.entity.OrderInfos;
import cart.entity.PointPolicy;
import cart.entity.Product;
import cart.entity.ProductImage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Sql({"classpath:truncate.sql", "classpath:data.sql"})
class OrderPersistenceAdapterTest {

    private OrderPersistenceAdapter orderPersistenceAdapter;

    private Product product;
    private Member member;
    private OrderInfos orderInfos;

    @Autowired
    public OrderPersistenceAdapterTest(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.orderPersistenceAdapter = new OrderPersistenceAdapter(namedParameterJdbcTemplate);
        this.member = new Member(1L, "a@a.com", "1234", 0L);
        this.product = new Product(1L, "고기", 10000, "https://", 10.0, PointPolicy.of(true));
        this.orderInfos = new OrderInfos(List.of(
                new OrderInfo(1L, product, new ProductImage("고기", 5000, "https://"), 1)
        ));
    }

    @Test
    @DisplayName("주문을 삽입할 수 있다")
    void insert() {
        // given, when
        Order inserted = orderPersistenceAdapter.insert(new Order(null, member, orderInfos, 0, 0, 0));
        // then
        assertThat(inserted.getId()).isNotNull();

    }

    @Test
    @DisplayName("멤버 ID를 통해 주문을 찾을 수 있다")
    void findByMemberId() {
        // given
        Order inserted = orderPersistenceAdapter.insert(new Order(null, member, orderInfos, 0, 0, 0));
        // when
        List<Order> found = orderPersistenceAdapter.findByMemberId(member.getId());
        // then
        assertThat(found).isNotEmpty();
    }

    @Test
    @DisplayName("ID를 통해 주문을 찾을 수 있다")
    void findById() {
        // given
        Order inserted = orderPersistenceAdapter.insert(new Order(null, member, orderInfos, 0, 0, 0));
        // when
        Optional<Order> found = orderPersistenceAdapter.findById(inserted.getId());
        // then
        assertThat(found).isPresent();
    }
}
