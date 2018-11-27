package web.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author NIce
 * @date 2018-04-26 12:39
 */
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    final static Logger logger = LoggerFactory.getLogger(MyHandlerExceptionResolver.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.error(ex.getMessage(), ex);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ex", ex);
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
