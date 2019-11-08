package com.gaogao.cucaishop.adm.security;

import com.gaogao.cucaishop.home.models.Users;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class JwtUser implements UserDetails {

    private String userId;

    private String username;

    private String password;

    private String email;

    private String phone;

    private Collection<? extends GrantedAuthority> authorities;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
