package web.interceptor;

import bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author NIce
 * @date 2018-04-24 21:38
 */
public class LoginInterceptor implements HandlerInterceptor {
    private UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("user") != null) {
            return true;
        }
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if("autoLogin".equals(cookie.getName())){
                    User user = userService.selectByUsername(cookie.getValue().trim());
                    request.getSession().setAttribute("user", user);
                    return true;
                }
            }
        }
        String uri = request.getRequestURI();
        if(uri.endsWith("ogin.do")||uri.endsWith("egister.do")||uri.endsWith("site/findAll.do")){
            return true;
        }
        response.sendRedirect(request.getContextPath() + "/toLogin.do");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
