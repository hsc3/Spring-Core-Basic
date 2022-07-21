package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.order.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        OrderService orderService = ac.getBean(OrderService.class);
        assertThat(orderService).isInstanceOf(OrderService.class);
    }
}
