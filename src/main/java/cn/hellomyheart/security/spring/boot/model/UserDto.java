package cn.hellomyheart.security.spring.boot.model;

import lombok.Data;

/**
 * @description
 * @className: UserDto
 * @package: cn.hellomyheart.security.spring.boot.model
 * @author: Stephen Shen
 * @date: 2020/10/9 上午9:42
 */
@Data
public class UserDto {
    private String id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;
}
