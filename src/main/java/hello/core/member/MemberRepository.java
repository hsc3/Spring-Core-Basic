package hello.core.member;

public interface MemberRepository {

    // 회원 객체 저장
    void save(Member member);

    // 회원 Id 검색
    Member findById(Long memberId);
}
