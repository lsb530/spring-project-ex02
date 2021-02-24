package org.codechobo.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.codechobo.domain.LoginUser;
import org.codechobo.encrypt.SHA256Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
@Log4j
public class LoginUserMapperTests {

    @Setter(onMethod_ = @Autowired)
    private LoginUserMapper mapper;

    @Test
    public void testGetList() {
        mapper.getList().forEach(user -> log.info(user));
    }

    @Test
    public void testInput() {
        LoginUser loginUser = new LoginUser();
        String before_pw = "12345";
        String encrypted_password = SHA256Util.getEncrypt(before_pw,loginUser.getSalt());
        loginUser
                .setId("테스트4")
                .setPw(encrypted_password)
                .setSalt(loginUser.getSalt());
//                .setSalt("ALKFMASLM1383jXAM!");
//        System.out.println(loginUser);
        mapper.insert(loginUser);
        log.info(loginUser);
    }

    @Test
    public void testRead() {
        // 존재하는 유저 ID로 테스트
        LoginUser loginUser = mapper.read("test");
        log.info(loginUser);
    }

    @Test
    public void testDelete() {
        log.info("DELETE Count: " + mapper.delete("테스트"));
    }

    @Test
    public void testUpdate() {
        // 실행전 존재하는 번호인지 확인할 것
        LoginUser loginUser = new LoginUser();
        String before_pw = "123456";
        String encrypted_password = SHA256Util.getEncrypt(before_pw,loginUser.getSalt());
        loginUser.setId("테스트2").setPw(encrypted_password).setSalt(loginUser.getSalt());
        int count = mapper.update(loginUser);
        log.info("UPDATE COUNT: " + count);
    }

    @Test
    public void loginTest() {
        LoginUser loginUser = mapper.read("테스트2");
        String pw="123456";
        String compare_pw = SHA256Util.getEncrypt(pw, loginUser.getSalt());
        log.info("Check value: " + compare_pw.equals(loginUser.getPw()));
        if (compare_pw.equals(loginUser.getPw())) {
            System.out.println("login success");
        } else {
            System.out.println("login failed");
        }
    }
}