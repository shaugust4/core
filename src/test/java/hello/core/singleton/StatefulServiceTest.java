package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : A 사용자가 10,000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // ThreadB : B 사용자가 20,000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        // ThreadA : 사용자 A의 주문 금액 조회
        System.out.println("userAPrice = " + userAPrice); //10000
        System.out.println("userBPrice = " + userBPrice); //20000

        Assertions.assertThat(userAPrice).isEqualTo(10000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}