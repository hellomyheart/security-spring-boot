package cn.hellomyheart.security.spring.boot.model;

import lombok.Data;

/**
 * @description
 * @className: PermissionDto
 * @package: cn.hellomyheart.security.spring.boot.model
 * @author: Stephen Shen
 * @date: 2020/10/9 下午1:38
 */
@Data
public class PermissionDto {
    private String id;
    private String code;
    private String description;
    private String url;
}
