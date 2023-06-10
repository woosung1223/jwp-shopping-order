package cart.config;

import cart.presentation.MemberArgumentResolver;
import cart.presentation.MemberAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final MemberArgumentResolver memberArgumentResolver;
    private final MemberAuthInterceptor memberAuthInterceptor;

    public WebMvcConfig(MemberArgumentResolver memberArgumentResolver,
                        MemberAuthInterceptor memberAuthInterceptor) {
        this.memberArgumentResolver = memberArgumentResolver;
        this.memberAuthInterceptor = memberAuthInterceptor;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .exposedHeaders("*")
                .allowedOrigins(
                        "http://woowacourse-teo.store",
                        "https://woowacourse-teo.store",
                        "https://kyw0716.github.io",
                        "https://gyeongza.github.io"
                ).allowCredentials(true);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(memberArgumentResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(memberAuthInterceptor)
                .addPathPatterns("/cart-items/{*path}")
                .addPathPatterns("/orders/{*path}")
                .addPathPatterns("/point/{*path}");
    }
}
