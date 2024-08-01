package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


class MemberRepositoryTest {

    //여기서 pure 한 자바로 싱글톤 구현한 것.
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 저장() {
        //given
        Member memberA = new Member("mino", 20);
        //when
        Member saveMember = memberRepository.save(memberA);
        Long id = memberA.getId();
        //then
        Member findMember = memberRepository.findById(id);
        assertThat(findMember).isSameAs(saveMember);
    }

    @Test
    void 모든_것_조회() {
        //given
        Member member1 = new Member("mino", 20);
        Member member2 = new Member("sh", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> result = memberRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);
    }
}