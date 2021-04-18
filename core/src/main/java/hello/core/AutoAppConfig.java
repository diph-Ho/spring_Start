package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.*;

/*
AppConfig에 @Configuration Annotation이 붙어있기 때문에 모든 빈 정보를 자동으로 가져오게된다
이러한 경우 AppConfig의 모든 @Bean 이 자동으로 등록되기 때문에 제외 시켜줘야한다.
제외 시켜주기 위해서
@ComponentScan(excludeFilters = @ComponentScan.@Filter(type = FilterType.ACCOTATION, classes = Configuration.class))
를 사용하여 제외 시켜준다.
 */
@Configuration
@ComponentScan(
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
