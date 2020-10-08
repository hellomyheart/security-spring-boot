package cn.hellomyheart.security.spring.boot.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @description
 * @className: SpringDataUserDetailsService
 * @package: cn.hellomyheart.security.spring.boot.service
 * @author: Stephen Shen
 * @date: 2020/10/9 上午6:20
 */
@Service
public class SpringDataUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //连接数据库，根据账号查询用户信息
        //暂时采用模拟的方式
        //登录账号
        System.out.println("username="+s);

        UserDetails userDetails = User.withUsername("zhangsan").password("123").authorities("p1").build();
        return userDetails;
    }
}
