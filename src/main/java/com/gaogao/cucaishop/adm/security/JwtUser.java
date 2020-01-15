package com.gaogao.cucaishop.adm.security;

import com.gaogao.cucaishop.home.models.Users;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class JwtUser implements OAuth2User, UserDetails{

    private String id;

    private String password;

    private String email;

    private String name;

    private String phone;

    private Collection<? extends GrantedAuthority> authorities;

    private Map<String, Object> attributes;


    public JwtUser(String id,String email,String name, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.authorities = authorities;
    }

    public static JwtUser create(Users users){
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(users.getRoles().getRoleName().name()));
        return new JwtUser(
                users.getProviderId(),
                users.getEmail(),
                users.getFullName(),
                users.getPassword(),
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
        return email;
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


    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }


    @Override
    public String getName() {
        return email;
    }
}
