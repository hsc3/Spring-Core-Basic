package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
// @RequiredArgsConstructor // final 필드를 모아서 생성자를 자동으로 생성
public class OrderServiceImpl implements OrderService {

    // 주문 서비스가 협력하게 되는 저장소 및 할인 정책을 생성
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("rateDiscountPolicy") DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
//
//    @Autowired // 1. 생성자 메서드를 통한 빈 의존관계 주입 ( 필수, 불변 )
//    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
//        System.out.println("1. memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }
//
//    @Autowired // 2. 수정자 메서드를 통한 빈 의존관계 주입 ( 선택, 변경 )
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//    @Autowired // 2. 수정자 메서드를 통한 빈 의존관계 주입 ( 선택, 변경 )
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }
//
//    @Autowired // 3. 일반 메서드를 통한 빈 의존관계 주입 ( 여러 의존관계 주입 O )
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        // 저장소와 할인정책에 접근하여 멤버를 조회하고 할인 정책이 적용된 금액을 생성
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 주문 정보 객체를 생성해서 반환
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // test용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
