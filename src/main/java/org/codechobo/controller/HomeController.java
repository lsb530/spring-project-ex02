package org.codechobo.controller;

import org.codechobo.domain.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
@RequestMapping("/javachobo")
public class HomeController {

    String first_path="/codechobo/";

    @RequestMapping(value = "/")
    public String home(Locale locale, Model model) {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        model.addAttribute("serverTime", formattedDate);
        return first_path+ "index";
    }

    @RequestMapping(value = "/hello") // 404 hello.jsp를 못찾겟다
    public void main() {
        System.out.println("Hello"); // 콘솔에 출력
    }
    // public void service(HttpServletRequest req, HttpservletResponse res)
    // 브라우저에 응답하려면 HttpServletResponse가 필요
    // 매개변수로 필요한 것들을 선언하면 DispatcherServlet이 제공함
    @RequestMapping("/hello2")
    public void main2(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Hello</h1>");
        out.println("</body>");
        out.println("</html>");
    }

    @RequestMapping("/hello3")
    public void main3(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");
        PrintWriter out = res.getWriter(); // IOException 발생
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>id="+id+"</h1>");
        out.println("<h1>pwd="+pwd+"</h1>");
        out.println("</body>");
        out.println("</html>");
    }

    @RequestMapping("/hello4")
    public String main4(String id, String pwd, Model model) {
//        @RequestParam("id") String id; << 이게 메소드 인자로 들어온다
        model.addAttribute("id", id);
        model.addAttribute("pwd", pwd);
//        return "/javachobo/hello";
        return first_path+"/hello";
    }

    @RequestMapping("/hello5")
    public String main5(String name, int age, Model model) {
//        @RequestParam("id") String id; << 이게 메소드 인자로 들어온다
        model.addAttribute("name", name);
        model.addAttribute("age", age);
//        return "/javachobo/hello2";
        return first_path+"/hello2";
    }

    @RequestMapping("/hello6")
    public String main6(String name, int age, @DateTimeFormat(pattern = "yyyy-MM-dd") Date birth, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        model.addAttribute("birth", birth);
        return first_path+"hello2";
    }
    
    @RequestMapping("hello7")
    public String main7( //@Modelattribute 변수를 곧장 model에 저장한다.
        @ModelAttribute("name") String name, @ModelAttribute("age") int age, @ModelAttribute("birth")@DateTimeFormat(pattern = "yyyy-MM-dd") Date birth
     ) {
//        model.addAttribute("name", name);
//        model.addAttribute("age", age);
//        model.addAttribute("birth", birth);
        return first_path+"hello2";
    }

    @RequestMapping("hello8")
    public String main8(
            @ModelAttribute User user) {
//        return "/javachobo/hello3";
        return first_path+"hello3";
    }

    @RequestMapping("setCookie")
    public void main9(HttpServletResponse res) {
        Cookie cookie = new Cookie("cookie", "yammy");
        cookie.setMaxAge(60*60*24); // 1일동안
        res.addCookie(cookie);
        System.out.println("cookie is produced");
    }

    @RequestMapping("destroyCookie")
    public void main10(@CookieValue(value="cookie", required = false) Cookie cookie, HttpServletResponse res) {
//        res.setContentType("application/json charset=UTF-8");
//        req.setCharacterEncoding("UTF-8");
        if(cookie != null) {
            cookie.setMaxAge(0);
            res.addCookie(cookie);
        }
        System.out.println("cookie is destroyed");
    }

    @RequestMapping("setSession")
    public void main11(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute("session", "boki");
        System.out.println("session is produced");
    }

    @RequestMapping("destroySession")
    public void main12(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String SES = (String)session.getAttribute("session"); // 세션에 저장된 id의 값을 읽어옴
        System.out.println("session" + SES);
        session.invalidate();
        System.out.println("session is destroyed");
    }
}
