package com.gaogao.cucaishop.adm.security;

import com.gaogao.cucaishop.home.models.Users;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class JwtUser implements OAuth2User,UserDetails{

    private String userId;

    private String username;

    private String password;

    private String email;

    private String fullname;

    private String phone;

    private Collection<? extends GrantedAuthority> authorities;

    private Map<String, Object> attributes;

    public JwtUser(String userId, String username, String password, String email, String phone, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.authorities = authorities;
    }

    public static JwtUser create(Users users){
        List<GrantedAuthority> authorities = users.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getRoleName().name())).collect(Collectors.toList());
        return new JwtUser(
                users.getUserId(),
                users.getUserName(),
                users.getPassword(),
                users.getEmail(),
                users.getPhone(),
                authorities
        );
    }

    public static JwtUser create(Users users, Map<String, Object> attributes){
        JwtUser jwtUser = JwtUser.create(users);
        jwtUser.setAttributes(attributes);
        return jwtUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    @Override
    public String getName() {
        return fullname;
    }
}
