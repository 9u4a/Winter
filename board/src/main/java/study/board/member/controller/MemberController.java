package study.board.member.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.board.member.domain.Member;
import study.board.member.dto.MemberDTO;
import study.board.member.service.MemberService;

@RequestMapping("/member")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping()
    public long create(@RequestBody MemberDTO memberDTO) {
        Member member = memberService.createMember(memberDTO);
        return member.getId();
    }
}
