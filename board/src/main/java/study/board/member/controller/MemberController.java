package study.board.member.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.board.member.dto.MemberDTO;
import study.board.member.service.MemberService;

@RequestMapping("/member")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping()
    public ResponseEntity<MemberDTO> createMember(@RequestBody MemberDTO createMemberDTO) {
        MemberDTO memberDTO = memberService.createMember(createMemberDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(memberDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> getMember(@PathVariable Long id) {
        MemberDTO memberDTO = memberService.getMember(id);
        return ResponseEntity.status(HttpStatus.OK).body(memberDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDTO> updateMember(@PathVariable Long id, @RequestBody MemberDTO updatememberDTO) {
        MemberDTO memberDTO = memberService.updateMember(id, updatememberDTO);
        return ResponseEntity.status(HttpStatus.OK).body(memberDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}
