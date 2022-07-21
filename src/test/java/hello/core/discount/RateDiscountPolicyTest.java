package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

// 정롤 할인 test
class RateDiscountPolicyTest {

    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP고객은 10% 할인이 적용되어야 한다") // test 이름을 지정
    void vip_o() {

        // 1. 고객 생성
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        // 2. 할인 금액을 반환
        int discount = rateDiscountPolicy.discount(member, 10000);
        
        // 3. 검증 (할인 금액이 1000원 인지)
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP고객이 아니면 할인이 적용되지 않아야 한다")
    void vip_x() {

        Member member = new Member(2L, "memberBASIC", Grade.BASIC);
        int discount = rateDiscountPolicy.discount(member, 10000);
        assertThat(discount).isEqualTo(0);
    }
}