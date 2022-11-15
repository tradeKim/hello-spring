package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import hello.hellospring.repsitory.MemberRepository;
import hello.hellospring.repsitory.MemoryMemberRepository;
import org.apache.catalina.mbeans.MemoryUserDatabaseMBean;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    //Test를 할때 repository 객체의 데이터가 계속 저장 하여 테스트 오류가 발생하는 현상을 방지하기 위하여
    //테스트 함수가 실행이 완료 되면 객체의 데이터를 삭제 하기 위한 처리
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();  //get으로 바로 조회 하는건 좋은 방법은 아님

//        Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result);  //import org.assertj.core.api.Assertions;
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
