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
    public MemberDTO createMember(MemberDTO memberDTO) {
        Member member =memberDTO.toEntity();
        Member savedMember = memberRepository.save(member);
        return new MemberDTO(savedMember);
    }

    @Override
    @Transactional
    public MemberDTO getMember(Long id) {
        return new MemberDTO(memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원")));
    }

    @Override
    @Transactional
    public MemberDTO updateMember(Long id, MemberDTO memberDTO) {
        Member member = memberDTO.toEntity();
        member.setId(id);
        Member savedMember = memberRepository.save(member);
        return new MemberDTO(savedMember);
    }

    @Override
    @Transactional
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
