package com.example.demo.security;

import com.example.demo.dto.resp.LoginUser;
import com.example.demo.entity.SysLoginEntity;
import com.example.demo.service.SysLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class MyUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    SysLoginService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysLoginEntity sysLoginEntity = service.getByUsername(username);
        if (Objects.isNull(sysLoginEntity)){
            throw new UsernameNotFoundException("用户名或密码错误");
        }else if ("1".equals(sysLoginEntity.getStatus())){
            throw new UsernameNotFoundException("账号已被封禁，具体联系管理员!");
        }
//        //根据用户查询权限信息 添加到loginUser中
//        // 使用 BCryptPasswordEncoder 对存储的密码进行编码
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode(sysLoginEntity.getPassword());
//        sysLoginEntity.setPassword(encodedPassword);
        List<String> list = service.getUserPermissions(username);
        return new LoginUser(sysLoginEntity,list);
    }

//    private List<GrantedAuthority> getUserAuthority(String role) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        if ("admin".equals(role)) {
//            // 如果是admin，赋予所有接口的访问权限
//            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        } else if ("user".equals(role)) {
//            // 如果是user，赋予部分接口的访问权限
//            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//        }
//        return authorities;
//    }
}
