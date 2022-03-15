package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long join(Member member){
        // 같은 이름이 있는 중복 회원 불가
//        Optional<Member> result = memberRepository.findByName(member.getName());
//
//        result.ifPresent(m->{ // 값이 존재하면
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        validateDuplicateMember(member);
        //2번째 방법 (ctrl + t를 눌러서 함수로 만들 수 있음)

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) { //중복회원 검증
        memberRepository.findByName(member.getName()) //Optional 멤버
                .ifPresent(m-> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //전체 회원 조회
    //비즈니스에 의존적으로 설계함
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
