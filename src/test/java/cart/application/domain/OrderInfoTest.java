package cart.application.domain;

import cart.entity.OrderInfo;
import cart.entity.PointPolicy;
import cart.entity.Product;
import cart.entity.ProductImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderInfoTest {

    private ProductImage productImage;

    @BeforeEach
    void setup() {
        this.productImage = new ProductImage("", 1000, "");
    }

    @Test
    @DisplayName("상품 개수에 따라 사용가능한 포인트를 계산할 수 있다")
    void calculateAvailablePoint() {
        // given
        Product product = new Product(0L, "", 1000, "", 10.0, PointPolicy.of(true));
        OrderInfo orderInfo = new OrderInfo(0L, product, productImage, 4);
        // when
        long point = orderInfo.calculateAvailablePoint();
        // then
        assertThat(point).isEqualTo(4000L);
    }

    @Test
    @DisplayName("만약 포인트 사용 불가능 상품이라면, 0원을 반환한다")
    void calculateAvailablePoint_unavailable() {
        // given
        Product product = new Product(0L, "", 1000, "", 10.0, PointPolicy.of(false));
        OrderInfo orderInfo = new OrderInfo(0L, product, productImage, 4);
        // when
        long point = orderInfo.calculateAvailablePoint();
        // then
        assertThat(point).isEqualTo(0L);
    }

    @Test
    @DisplayName("상품 개수에 따라 적립될 포인트를 계산할 수 있다")
    void calculateEarnedPoint() {
        // given
        Product product = new Product(0L, "", 1000, "", 10.0, PointPolicy.of(false));
        OrderInfo orderInfo = new OrderInfo(0L, product, productImage, 4);
        // when
        long point = orderInfo.calculateEarnedPoint();
        // then
        assertThat(point).isEqualTo(400L);
    }
}
