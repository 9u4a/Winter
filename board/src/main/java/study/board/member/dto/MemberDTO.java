package study.board.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.board.member.domain.Member;


@Getter
@NoArgsConstructor
public class MemberDTO {
    private String member_id;
    private String member_name;
    private String member_email;

    @Builder
    public MemberDTO(String member_id, String member_name, String member_email) {
        this.member_id = member_id;
        this.member_name = member_name;
        this.member_email = member_email;
    }

    public Member toEntity() {
        return Member.builder()
                .member_id(member_id)
                .member_name(member_name)
                .member_email(member_email)
                .build();
    }

}
