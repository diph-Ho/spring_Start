package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository //Member service에서 @service 어노테이션을 사용하면 Repository를 못찾는다. 이때 스프링 컨테이너에서 repository를 사용하기 위해 @Repository 어노테이션 선언
public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();


}
