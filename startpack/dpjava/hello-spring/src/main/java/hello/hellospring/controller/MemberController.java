package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    //private final MemberService memberService = new MemberService(); -> 이렇게 생성해서 쓸수도 있지만
    //위처럼 사용하지 않고 스프링 컨테이너로 부터 해당 객체를 받아오게 생성해야 한다.

    private final MemberService memberService;

    @Autowired //이 어노테이션이 생성자에 선언 돼 있으면 스프링컨테이너의 memberservice와 연결 해 준다.
    //@Autowired는 스프링 컨테이너가 인식하고있는 memberController와 memberService를 연결시켜준다.
    //이런거를 dependency injection(의존성 주입)이라고 한다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
