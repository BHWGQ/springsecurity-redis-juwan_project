package com.example.demo.dto.resp;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.demo.entity.SysLoginEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class LoginUser implements UserDetails , Serializable {

//    private String  username;

    private SysLoginEntity sysLoginEntity;

    //存储权限信息
    private List<String> permissions;

//    private String userPasswordStorgae;
//    private String encodedPassword;

    public LoginUser(SysLoginEntity sysLoginEntity, List<String> permissions) {
        this.sysLoginEntity = sysLoginEntity;
        this.permissions = permissions;
//        this.userPasswordStorgae = userPasswordStorage;
    }

    public LoginUser() {
    }

    //存储SpringSecurity所需要的权限信息的集合
    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities!=null){
            return authorities;
        }
        //把permissions中字符串类型的权限信息转换成GrantedAuthority对象存入authorities中
        authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return sysLoginEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return sysLoginEntity.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
