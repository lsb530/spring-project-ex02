package org.codechobo.service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.codechobo.domain.LoginUser;
import org.codechobo.encrypt.SHA256Util;
import org.codechobo.mapper.LoginUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {

    @Setter(onMethod_ = @Autowired)
    private LoginUserMapper mapper;

    public boolean Login(String id, String pw) {
        LoginUser loginUser = mapper.read(id);
        String compare_pw = SHA256Util.getEncrypt(pw, loginUser.getSalt());
        return compare_pw.equals(loginUser.getPw());
    }
}
