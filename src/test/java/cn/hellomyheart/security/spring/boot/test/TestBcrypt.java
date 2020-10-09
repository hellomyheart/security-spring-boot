package cn.hellomyheart.security.spring.boot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description
 * @className: TestBcrypt
 * @package: cn.hellomyheart.security.spring.boot.test
 * @author: Stephen Shen
 * @date: 2020/10/9 上午6:42
 */
@RunWith(SpringRunner.class)
public class TestBcrypt {
    @Test
    public void testBcrypt() {
        //对密码进行加密
        String hashpw = BCrypt.hashpw("456", BCrypt.gensalt());
        //校验密码
        boolean checkpw = BCrypt.checkpw("123", "$2a$10$1dDXn890NovD3sTMhnke1uLZZgWIzcd9FaNRb9liQRoEa6MU281bq");
        boolean checkpw2 = BCrypt.checkpw("123", "$2a$10$FE4FJyBfVaOjgS34uVYaUe1kEirFDHuQKVqbBYMCeSSeW2fBGOAAG");
        System.out.println(hashpw);
        System.out.println(""+checkpw+checkpw2);
    }
}
