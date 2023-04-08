package study.board.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.member.domain.Member;
import study.board.member.dto.MemberDTO;
import study.board.member.repository.MemberRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;


    @Override
    @Transactional
    public Member createMember(MemberDTO memberDTO) {
        Member member =memberDTO.toEntity();
        return memberRepository.save(member);
    }
}
