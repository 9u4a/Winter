package study.board.member.service;

import study.board.member.dto.MemberDTO;
import study.board.member.dto.UpdateMemberDTO;


public interface MemberService {

    MemberDTO createMember(MemberDTO memberDTO);
    MemberDTO getMember(Long id);
    MemberDTO updateMember(Long id, MemberDTO memberDTO);
    void deleteMember(Long id);
}
