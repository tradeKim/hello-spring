package hello.hellospring.repsitory;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    //테스트
    //Optional은 return값이 null일 경우 기본값으로 반환하기 위함?
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
