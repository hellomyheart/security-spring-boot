package cn.hellomyheart.security.spring.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @description security配置类
 * @className: WebSecurityConfig
 * @package: cn.hellomyheart.security.spring.mvc.config
 * @author: Stephen Shen
 * @date: 2020/10/8 下午1:41
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //定义用户信息服务（查询用户信息）
//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager memoryUserDetailsManager = new InMemoryUserDetailsManager();
//        memoryUserDetailsManager.createUser(User.withUsername("zhangsan").password("123").authorities("p1").build());
//        memoryUserDetailsManager.createUser(User.withUsername("lisi").password("456").authorities("p2").build());
//        return memoryUserDetailsManager;
//    }


    //密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //安全拦截机制

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) //创建session
                .and()
//                .csrf().disable() //屏蔽csrf的方式一
                .authorizeRequests()
//                .antMatchers("/r/r1").hasAuthority("p1") //权限设置，基于权限授权
//                .antMatchers("/r/r2").hasAuthority("p2")
                .antMatchers("/r/**").authenticated() //所有/r/**,必须认证
                .anyRequest().permitAll()  //其他请求可以访问
                .and()
                .formLogin() //允许表单登录
                .loginPage("/login-view")//登录页面
                .loginProcessingUrl("/login")//指定登录处理的url
                .successForwardUrl("/login-success") //自定义登录成功的页面地址
                .and()
                .logout()//自定义退出url
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login-view?logout")
                //运行get方式退出
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"));

    }

}
