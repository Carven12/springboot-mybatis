package com.lc.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Hr implements UserDetails {

	private static final long serialVersionUID = -313993863526226983L;

	private Integer id;

    private String name;

    private String phone;

    private String telephone;

    private String address;

    private Boolean enabled = true;

    private String username;

    private String password;

    private String userface;

    private String remark;
    
    private Role role;

    public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Hr(String username,
			String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUserface() {
        return userface;
    }

    public void setUserface(String userface) {
        this.userface = userface == null ? null : userface.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("admin"));
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }
 
    @Override
    public String getUsername() {
        return username;
    }
 
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}