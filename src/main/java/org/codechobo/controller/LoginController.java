package org.codechobo.controller;

import lombok.Setter;
import org.codechobo.domain.LoginUser;
import org.codechobo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
public class LoginController {

    @Setter(onMethod_ = @Autowired)
    private LoginService service;

    @RequestMapping(value = "/")
    public String index(Locale locale, Model model) {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        model.addAttribute("serverTime", formattedDate);
        return "codechobo/index";
    }
    @GetMapping(value = "/login")
    public String loginPageMove() {
        return "codechobo/login";
    }

    @PostMapping(value = "/login")
    public String loginResultPage(
            LoginUser user, @RequestParam(value = "ck_cookie", required = false, defaultValue = "false") Boolean check,
            @CookieValue(value="cook", required = false) Cookie cookie,
            HttpServletRequest req, HttpServletResponse res) {
        System.out.println(user);
        System.out.println("check = " + check);
//        LoginService service = new LoginService();
        Boolean result_login = service.Login(user.getId(), user.getPw());
        if (!result_login) { // 로그인 실패
            return "codechobo/login";
        }
        // 다 성공처리
        if (check) { // 체크 표시
//                Cookie newcookie = new Cookie("cookie", Integer.toString(user.hashCode()));
            Cookie newcookie = new Cookie("cook", user.getId());
            newcookie.setMaxAge(60*60*24); // 1일동안
            res.addCookie(newcookie);
        } else {
            if(cookie != null) {
                cookie.setMaxAge(0);
                res.addCookie(cookie);
            }
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        System.out.println("session is produced");
        return "redirect:/";
    }
    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest req) {
        HttpSession session = req.getSession();
        LoginUser SESSION = (LoginUser)session.getAttribute("user"); // 세션에 저장된 id의 값을 읽어옴
        System.out.println("session" + SESSION);
        session.invalidate();
        System.out.println("session is destroyed");
        return "redirect:/";
    }
}