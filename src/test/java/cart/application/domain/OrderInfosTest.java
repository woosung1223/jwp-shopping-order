package cart.application.domain;

import cart.entity.OrderInfo;
import cart.entity.OrderInfos;
import cart.entity.PointPolicy;
import cart.entity.Product;
import cart.entity.ProductImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OrderInfosTest {

    private ProductImage productImage;

    @BeforeEach
    void setup() {
        this.productImage = new ProductImage("", 0, "");
    }

    @Test
    @DisplayName("모든 사용가능한 포인트의 합을 계산할 수 있다")
    void calculateAvailablePoint() {
        // given
        Product pointAvailable = new Product(0L, "", 1000, "", 10.0, PointPolicy.of(true));
        Product pointUnavailable = new Product(0L, "", 500, "", 5.0, PointPolicy.of(false));
        OrderInfos orderInfos = new OrderInfos(List.of(
                new OrderInfo(0L, pointAvailable, productImage, 4),
                new OrderInfo(0L, pointUnavailable, productImage, 4),
                new OrderInfo(0L, pointAvailable, productImage, 4)
        ));
        // when
        long point = orderInfos.calculateAvailablePoint();
        // then
        assertThat(point).isEqualTo(8000L);
    }

    @Test
    @DisplayName("적립될 포인트의 합을 계산할 수 있다")
    void calculateEarnedPoint() {
        // given
        Product pointAvailable = new Product(0L, "", 1000, "", 10.0, PointPolicy.of(true));
        Product pointUnavailable = new Product(0L, "", 500, "", 5.0, PointPolicy.of(false));
        OrderInfos orderInfos = new OrderInfos(List.of(
                new OrderInfo(0L, pointAvailable, productImage, 4),
                new OrderInfo(0L, pointUnavailable, productImage, 4),
                new OrderInfo(0L, pointAvailable, productImage, 4)
        ));
        // when
        long point = orderInfos.calculateEarnedPoint();
        // then
        assertThat(point).isEqualTo(900L);
    }
}
