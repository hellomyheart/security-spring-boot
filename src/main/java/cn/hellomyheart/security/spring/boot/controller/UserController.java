package cn.hellomyheart.security.spring.boot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 登录Controller
 * @className: UserController
 * @package: cn.hellomyheart.security.spring.mvc.controller
 * @author: Stephen Shen
 * @date: 2020/10/8 下午2:06
 */
@RestController
public class UserController {
    @RequestMapping(value = "/login-success", produces = {"text/plain;charset=utf-8"})
    public String loginSuccess() {
        //提示具体的用户名称
        return getUsername() + "登录成功";
    }

    @GetMapping(value = "/r/r1", produces = {"text/plain;charset=utf-8"})
    @PreAuthorize("hasAnyAuthority('p1','p3')") //拥有p1或者p3都可以访问
    public String r1() {
        return getUsername() + "访问资源r1";
    }

    @PreAuthorize("hasAuthority('p2')") //拥有p2可以访问
    @GetMapping(value = "/r/r2", produces = {"text/plain;charset=utf-8"})
    public String r2() {
        return getUsername() + "访问资源r2";
    }

    //获取当前用户的信息
    private String getUsername() {
        String username = null;
        //当前认证通过的用户身份
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal == null) {
            username = "匿名";
        }
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            username = userDetails.getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}
