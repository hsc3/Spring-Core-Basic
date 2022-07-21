package hello.core.findbean;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

// 같은 타입의 빈이 2개 이상 존재할 경우 test
public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(DuplicateBeanConfig.class);

    @Test
    @DisplayName("같은 타입의 빈이 2개이상 존재")
    void findBeanByTypeDuplicate() {
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class));
    } // -> 타입뿐만 아니라, 빈의 이름도 지정한다.

    @Test
    @DisplayName("특정 타입의 빈 모두 조회")
    void findAllBeanByType() {

        // MemberRepository 타입의 모든 빈을 조회하고 출력합니다.
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value : " + beansOfType.get(key));
        }
        // 조회한 빈의 개수가 2개인지를 확인합니다.
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    // 내부 클래스 (해당 클래스 내에서만 사용하는 클래스를 생성)
    //  를 활용해서 스프링 컨테이너에 같은 타입의 객체를 2개 저장합니다.
    @Configuration
    static class DuplicateBeanConfig {

        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
