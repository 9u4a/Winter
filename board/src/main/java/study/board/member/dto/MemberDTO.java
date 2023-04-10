package study.board.member.dto;

import lombok.*;
import study.board.member.domain.Member;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {


    private String member_id;
    private String member_name;
    private String member_email;



    public MemberDTO(Member member) {
        this.member_id = member.getMember_id();
        this.member_name = member.getMember_name();
        this.member_email = member.getMember_email();
    }
    public Member toEntity() {
        return Member.builder()
                .member_id(member_id)
                .member_name(member_name)
                .member_email(member_email)
                .build();
    }

}
