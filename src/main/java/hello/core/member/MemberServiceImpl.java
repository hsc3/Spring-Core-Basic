package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // 컴포넌트 대상으로 설정
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Autowired // 의존관계 자동 주입
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

    // test용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
