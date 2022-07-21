package hello.core.findbean;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

// 스프링 빈 조회 test
class ApplicationContextBasicFindTest {

    // 스프링 컨테이너 호출
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    // 1. 빈 이름으로 조회
    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // 2. 빈 타입으로 조회
    @Test
    @DisplayName("빈 타입으로 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // 3. 존재하지 않는 빈 조회
    @Test
    @DisplayName("존재하지 않는 빈 조회")
    void findBeanByNameX() {

        // 오른쪽의 람다식을 실행했을 경우, 왼쪽의 오류가 발생해야 테스트를 통과한다.
        Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("notExist", MemberService.class));
    }
}
