package study.board.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.board.member.domain.Member;


@Getter
@Setter
@NoArgsConstructor
public class UpdateMemberDTO {

    private Long id;
    private String member_name;
    private String member_email;

    @Builder
    public UpdateMemberDTO(Long id, String member_name, String member_email) {
        this.id = id;
        this.member_name = member_name;
        this.member_email = member_email;
    }

    public Member UpdateDTOtoEntity() {
        return Member.builder()
                .member_name(member_name)
                .member_email(member_email)
                .build();
    }

}