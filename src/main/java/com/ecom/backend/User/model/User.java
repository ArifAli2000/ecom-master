package com.ecom.backend.User.model;


import com.ecom.backend.Role.model.Role;
import com.ecom.backend.cart.model.Cart;
import com.ecom.backend.common.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_info")
public class User extends AbstractEntity implements UserDetails {

    @Column(nullable = false)
    private  String name;
    @Column(unique = true,nullable = false)
    private String email;
    private String password;
    private String address;
    private String about;
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false,length = 10)
    private String phone;
    @Column(name = "CreateAt")
    private Date date;
    private boolean active;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    Set<Role> role =new HashSet<>();

    @OneToOne(mappedBy = "user")
    private Cart cart;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> collect = this.role.stream().map((r) ->new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
        return collect;
    }

    @Override
    public String getUsername() {
        return null;
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
