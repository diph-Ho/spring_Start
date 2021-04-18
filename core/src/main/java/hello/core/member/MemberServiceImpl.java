package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
    public class MemberServiceImpl implements MemberService{

        private final MemberRepository memberRepository;

        @Autowired
        public MemberServiceImpl(MemberRepository memberRepository) {
            this.memberRepository = memberRepository;
        }

        @Override
        public void join(Member member) {
            memberRepository.save(member);
        }

        @Override
        public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //AppConfig에서 MemoryMemberRepository를 두번 생성하게 되는데 이때 싱글톤이 깨지는 것인가를 확인하기위한 테스트 코드
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
