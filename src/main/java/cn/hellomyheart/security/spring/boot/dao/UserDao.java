package cn.hellomyheart.security.spring.boot.dao;

import cn.hellomyheart.security.spring.boot.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description
 * @className: UserDao
 * @package: cn.hellomyheart.security.spring.boot.dao
 * @author: Stephen Shen
 * @date: 2020/10/9 上午9:44
 */
@Repository
public class UserDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 根据账号查询用户信息
     */
    public UserDto getUserByUsername(String username) {
        String sql = "select id,username,password,fullname,mobile from t_user where username = ?";
        List<UserDto> list = jdbcTemplate.query(sql, new Object[]{username}, new
                BeanPropertyRowMapper<>(UserDto.class));
        if (list == null && list.size() <= 0) {
            return null;
        }
        return list.get(0);
    }
}