package cn.hellomyheart.security.spring.boot.dao;

import cn.hellomyheart.security.spring.boot.model.PermissionDto;
import cn.hellomyheart.security.spring.boot.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

    //根据用户id查询用户权限
    public List<String> findPermissionsByUserId(String userId) {
        String sql = "SELECT * FROM t_permission WHERE id IN(\n" +
                "SELECT permission_id FROM t_role_permission WHERE role_id IN(\n" +
                "\tSELECT role_id FROM t_user_role WHERE user_id = ? \n" +
                ")\n" +
                ")";
        List<PermissionDto> list = jdbcTemplate.query(sql, new Object[]{userId}, new
                BeanPropertyRowMapper<>(PermissionDto.class));
        List<String> permissions = new ArrayList<>();
        list.iterator().forEachRemaining(c -> permissions.add(c.getCode()));
        return permissions;
    }
}
