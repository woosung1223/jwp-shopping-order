package cart.application;

import cart.application.repository.ProductRepository;
import cart.domain.Product;
import cart.dto.ProductRequest;
import cart.dto.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;

    private Product pizza;
    private Product chicken;

    @BeforeEach
    void setup() {
        pizza = new Product(1L, "피자", 20000, "https://a.com", 10.0, true);
        chicken = new Product(2L, "치킨", 30000, "https://b.com", 0.0, false);
    }

    @Test
    void 모든_상품을_찾을_수_있다() {
        // given
        when(productRepository.findAll()).thenReturn(List.of(pizza, chicken));

        // when
        List<ProductResponse> allProductResponses = productService.getAllProducts();

        // then
        assertThat(allProductResponses.get(0).getId()).isEqualTo(1L);
        assertThat(allProductResponses.get(1).getId()).isEqualTo(2L);
    }

    @Test
    void 특정_상품을_찾을_수_있다() {
        // given
        when(productRepository.findById(any())).thenReturn(pizza);

        // when
        ProductResponse productResponse = productService.getProductById(1L);

        // then
        assertThat(productResponse.getId()).isEqualTo(1L);
    }

    @Test
    void 상품을_생성할_수_있다() {
        // given
        when(productRepository.insert(any())).thenReturn(3L);
        ProductRequest request = new ProductRequest("오이", 2000, "https://oi.com", 0.0, false);

        // when
        Long productId = productService.createProduct(request);

        // then
        assertThat(productId).isEqualTo(3L);
    }
}
