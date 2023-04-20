package study.security.security.common.auth.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String image;

    private String social;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String email, String name, String password, String image,String social, Role role){
        this.name = name;
        this.email = email;
        this.social = social;
        this.password = password;
        this.image = image;
        this.role = role;
    }

    public User update(String name, String image){
        this.name = name;
        this.image = image;
        return this;
    }
}
