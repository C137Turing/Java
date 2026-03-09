package com.bite.spingbookdemo.interceptor;
import com.bite.spingbookdemo.constant.Constants;
import com.bite.spingbookdemo.model.Result;
import com.bite.spingbookdemo.model.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       log.info("preHandle目标方法执行前");
       HttpSession session = request.getSession(false);
        if(session==null||session.getAttribute(Constants.SEESION_USER_KEY)==null){
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(401);
            String msg="用户未登录";
            response.getOutputStream().write(msg.getBytes("utf-8"));
            return false;
        }
        UserInfo userInfo = (UserInfo) session.getAttribute(Constants.SEESION_USER_KEY);
        if(userInfo == null||userInfo.getId()<=0) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(401);
            String msg="用户未登录";
            return false;
        }
        log.info("用户已登录");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    log.info("postHandle");
    HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    log.info("afterCompletion");
    HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
