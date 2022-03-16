package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    // 멤버 컨트롤러가 아닌 컨트롤러에서도 memberSerivce를 사용할 수 있다는 단점이 존재
//    private final MemberService memberService = new MemberService();
    private final MemberService memberService;

    @Autowired
    //의존관계 주입
    //memberService 스프링 컨테이너와 연결해줌
    //스프링에서  memberService를 관리할 수 없어 MemberSerivce에 @Servic 붙여줘야 함
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }
}
