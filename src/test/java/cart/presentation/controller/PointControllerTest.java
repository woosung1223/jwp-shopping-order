package cart.presentation.controller;

import cart.config.WebMvcConfig;
import cart.application.service.MemberService;
import cart.presentation.dto.response.PointResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = PointController.class, excludeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE, classes = {
        HandlerMethodArgumentResolver.class,
        HandlerInterceptor.class,
        WebMvcConfig.class
}))
class PointControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MemberService memberService;

    @Test
    @DisplayName("멤버 포인트를 조회할 수 있다")
    void getPoint() throws Exception {
        // given
        when(memberService.getPoint(any())).thenReturn(new PointResponse(1000L));
        // when
        mockMvc.perform(get("/point"))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.point").exists());
    }
}
