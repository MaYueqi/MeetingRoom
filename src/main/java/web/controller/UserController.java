package web.controller;

import bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.SiteService;
import service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author NIce
 * @date 2018-04-24 09:39
 */
@Controller
@RequestMapping("user")
public class UserController {
    private UserService userService;

    private SiteService siteService;

    @RequestMapping("login")
    public String login(String username, String password, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, @RequestParam(defaultValue = "false") Boolean autoLogin, HttpServletResponse response) {
        User user = userService.selectByUsername(username);
        if (user == null) {
            redirectAttributes.addFlashAttribute("msg", "Username don't exist.");
            return "redirect:/toLogin.do";
        }
        if (!user.getPassword().equals(password)) {
            redirectAttributes.addFlashAttribute("msg", "Password error.");
            return "redirect:/toLogin.do";
        }
        Cookie cookie = new Cookie("autoLogin", user.getUsername());
        cookie.setPath(request.getContextPath());
        if (autoLogin) {
            cookie.setMaxAge(Integer.MAX_VALUE);
        }else{
            cookie.setMaxAge(0);
        }
        session.setAttribute("user", user);
        response.addCookie(cookie);
        return "redirect:/index.do";
    }

    @RequestMapping("register")
    public String register(User user, RedirectAttributes redirectAttributes, HttpSession session) {
        user.setExpired(User.USER_STATUS_NORMAL);
        if (userService.selectByUsername(user.getUsername()) == null) {
            userService.insert(user);
        } else {
            redirectAttributes.addFlashAttribute("msg", "The username already exists.");
            return "redirect:/toRegister.do";
        }
        redirectAttributes.addFlashAttribute("msg", "Successful registration, Please login!");
        return "redirect:/toLogin.do";
    }

    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/toLogin.do";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setSiteService(SiteService siteService) {
        this.siteService = siteService;
    }
}
