package study.board.member.service;

import study.board.member.dto.MemberDTO;


public interface MemberService {

    MemberDTO createMember(MemberDTO memberDTO);
    MemberDTO getMember(Long id);
    MemberDTO updateMember(Long id, MemberDTO memberDTO);
    void deleteMember(Long id);
}
