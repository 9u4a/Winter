package study.board.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class UpdateMemberDTO {

    private String member_name;
    private String member_email;

    @Builder
    public UpdateMemberDTO(String member_name, String member_email) {
        this.member_name = member_name;
        this.member_email = member_email;
    }

}