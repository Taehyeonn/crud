package hello.crud.domain.user;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String userId;
    private String userPw;
    private String email;

    public User(Long num) {
    }

    public User(String userId, String userPw, String email) {
        this.userId = userId;
        this.userPw = userPw;
        this.email = email;
    }
}
