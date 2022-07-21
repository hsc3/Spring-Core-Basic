package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// Test
public class MemberServiceTest {

    MemberService memberService;

    // test가 동작하기 이전에 실행되는 코드 (test의 초기설정)
    @BeforeEach
    public void beforeEach() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        memberService = ac.getBean("memberService", MemberServiceImpl.class);
    }

    // 회원 가입 기능 test
    @Test
    void join() {

        // 1) 회원 가입
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        
        // 2) 회원 조회
        Member findMember = memberService.findMember(1L);

        // 3) 일치 여부 확인
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
