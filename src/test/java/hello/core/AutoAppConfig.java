package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
/*컴포넌트 스캔 기본 대상
* @Component : 컴포넌트 스캔에서 사용
* @Controller : 스프링 MVC 컨트롤러에서 사용
* @Service : 스프링 비즈니스 로직에서 사용
* @Repository : 스프링 데이터 접근 계층에서 사용
* @Configuration : 스프링 설정 정보에서 사용
**/
@ComponentScan(
        /*컴포넌트 스캔을 사용하면, @Configuration 애노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록한다.
        따라서 설정 정보도 자동으로 등록되기 때문에, AppConfig, TestConfig 등 앞서 만든 설정 정보도 함께 등록되고,
        실행되는 문제가 발생한다.
        excludeFilters를 이용해서 설정 정보는 컴포넌트 스캔 대상에서 제외해야 함.*/
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class),

        /*탐색할 패키지의 시작 위치를 지정한다. 이 패키지를 포함해서 하위 패키지를 모두 탐색*/
        basePackages = "hello.core.member"
)
public class AutoAppConfig {
/*기존 AppConfig와 달리 @Bean으로 등록된 클래스가 없다.*/
}