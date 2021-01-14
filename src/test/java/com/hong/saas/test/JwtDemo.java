package com.hong.saas.test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @Description: JWT 测试demo
 * @Author wanghong
 * @Date 2021/1/14 9:28
 * @Version V1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtDemo {

    /**
     * 使用 jjwt 创建 token
     */
    @Test
    public void createJwtToken() {
        JwtBuilder jwtBuilder = Jwts.builder().setId("123").setSubject("张三")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "signMacro123")
                .claim("tenantID", "csci123")
                .claim("tenantName", "csci");
        String token = jwtBuilder.compact();
        System.out.println(token);
    }

    /**
     * 解析 Jwt
     */
    @Test
    public void parseJwtToken() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjMiLCJzdWIiOiLlvKDkuIkiLCJpYXQiOjE2MTA1ODkxMTYsInRlbmFudElEIjoiY3NjaTEyMyIsInRlbmFudE5hbWUiOiJjc2NpIn0.GMmI7Jijs2kl6FWNXS--Ne-QMOQx7WnUc9RbHbBuYNI";
        Claims claims = Jwts.parser().setSigningKey("signMacro123").parseClaimsJws(token).getBody();

        //私有数据存放在claims对象中
        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
        System.out.println(claims.getIssuedAt());

        //解析自定义claim的内容
        String tenantID = (String) claims.get("tenantID");
        String companyName = (String) claims.get("tenantName");

        System.out.println(tenantID + "    -----   " + companyName);
    }


}
