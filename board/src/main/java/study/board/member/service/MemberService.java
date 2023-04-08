package study.board.member.service;

import study.board.member.domain.Member;
import study.board.member.dto.MemberDTO;


public interface MemberService {

    Member createMember(MemberDTO memberDTO);
}
