package com.example.xfash;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {

    @Test
    public void generateEncodedPassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // 常用密码加密示例
        String[] passwords = {"123456", "admin123", "password", "888888"};

        System.out.println("=== BCrypt 加密密码生成器 ===\n");

        for (String password : passwords) {
            String encoded = encoder.encode(password);
            System.out.println("原始密码：" + password);
            System.out.println("加密后：" + encoded);
            System.out.println("---");
        }

        // 生成单个密码（可修改这里）
        String rawPassword = "123456"; // 改成你需要的密码
        String encoded = encoder.encode(rawPassword);

        System.out.println("\n=== 单个密码加密 ===");
        System.out.println("原始密码：" + rawPassword);
        System.out.println("加密后：" + encoded);
        System.out.println("\n复制上面的加密值去更新数据库：");
        System.out.println("UPDATE emp SET password = '" + encoded + "' WHERE username = '你的用户名';");
    }
}
