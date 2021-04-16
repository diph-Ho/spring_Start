package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링이 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        //1. 조회 : 호출할 때 마다 객체를 생성하는지
        MemberService memberService1 = appConfig.memberService();

        //2. 조회 : 호출할 때 마다 객체를 생성하는지
        MemberService memberService2 = appConfig.memberService();

        /*
        * 위와 같이 생성하면 총 4개의 객체가 생성된다
        * appconfig 파일에서 보면
        *     @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }
    * 이 부분에서 MemberServiceImpl에 대한 객체가 두개
    *     @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
    * 이 부분에서 MemoryMemberRepository에 대한 객체가 두개가 생성된다.
    * 굉장히 비효율적이라는 것을 알 수 있다.
*/
        //3. 참조값이 같은지 다른지 확인 memberService1 != memberService2
//        System.out.println("memberService1 = " + memberService1);
//        System.out.println("memberService2 = " + memberService2);
//        assertThat(memberService1).isEqualTo(memberService2);
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    //SingltonService에서 private 생성자로 막아두었기 때문에 new로 무분별한 객체 생성을 막을 수 있다.
/*
    public static void main(String[] args) {
        SingletonService singletonService = new SingletonService();
    }
*/
    @Test
    @DisplayName("싱글톤을 이용한 객체 생성 비교")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        assertThat(singletonService1).isSameAs(singletonService2);

        //isSameAs는 == 비교와 같다
        //isEqualTo는 java의 equals의 메서드를 오버라이드 해오는것이다.
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //실재로 MemberService 코드안에는 싱글톤을 위한 코드가 전혀 없다.
        //하지만 아래의 경우 싱글톤으로 가능한 이유는 스프링컨테이너가 싱글톤을 가능하게 해주기 때문이다.
        MemberService memberService1 = applicationContext.getBean("memberService", MemberService.class);
        MemberService memberService2 = applicationContext.getBean("memberService", MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isSameAs(memberService2);
    }
}
