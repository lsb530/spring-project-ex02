package org.codechobo.domain;

import lombok.*;
import lombok.experimental.Accessors;
import org.codechobo.encrypt.SHA256Util;

//@Data
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
public class LoginUser {
    private Long uno;
    private String id;
    private String pw;
    private String salt;

    public LoginUser() {
        this.salt = SHA256Util.generateSalt();
    }

    public LoginUser(Long uno, String id, String pw) {
        this.uno = uno;
        this.id = id;
        this.pw = pw;
        this.salt = SHA256Util.generateSalt();
    }

    public LoginUser(String pw) {
        this.pw = pw;
        this.salt = SHA256Util.generateSalt();
    }
}
