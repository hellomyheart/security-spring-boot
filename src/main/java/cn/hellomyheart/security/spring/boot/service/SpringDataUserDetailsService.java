package cn.hellomyheart.security.spring.boot.service;

import cn.hellomyheart.security.spring.boot.dao.UserDao;
import cn.hellomyheart.security.spring.boot.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description
 * @className: SpringDataUserDetailsService
 * @package: cn.hellomyheart.security.spring.boot.service
 * @author: Stephen Shen
 * @date: 2020/10/9 上午6:20
 */
@Service
public class SpringDataUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //连接数据库，根据账号查询用户信息
        //登录账号
        UserDto userDto = userDao.getUserByUsername(s);

        if (userDto == null) {
            //如果用户查询不到，返回null，由provider来抛异常。
            return null;
        }
        //根据用户id查询用户权限
        List<String> permissions = userDao.findPermissionsByUserId(userDto.getId());
        //将permissions转成数组
        String[] permissionsArray = new String[permissions.size()];
        permissions.toArray(permissionsArray);
        UserDetails userDetails = User.withUsername(userDto.getUsername()).password(userDto.getPassword()).authorities(permissionsArray).build();
        return userDetails;
    }
}
