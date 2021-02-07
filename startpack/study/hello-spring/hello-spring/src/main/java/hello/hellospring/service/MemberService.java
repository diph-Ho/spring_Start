package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service //이걸 선언하기 이전에 MemberService는 스프링 컨테이너에 등록되지 않으므로 선언해야 컨트롤러에서 객체를 선언할때 인식이 가능하다.
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired // 스프링 컨테이너에 있는 MemberRepositroy와 MemberService를 연결시켜준다
    //Dependency inejection(의존성 주입)
    //이때 Repository 어노테이션은 MemoryMemberRepository에 있으므로 해당하는 것을 연결시켜준다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원 X
        /*Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 이름 입니다");
        });*/

        validateDuplicateMember(member);//중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 이름 입니다");
            });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
