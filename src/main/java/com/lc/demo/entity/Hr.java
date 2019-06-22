package com.lc.demo.entity;

import lombok.Data;

/**
 * 
 * @author liangc
 *
 */
@Data
public class Hr {
	
	private int id;
	private String name;
	private String phone;
	private String telephone;
	private String address;
	private boolean enabled;
	private String username;
	private String password;
	private String userface;
	private String remark;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserface() {
		return userface;
	}
	public void setUserface(String userface) {
		this.userface = userface;
	}
	@Override
	public String toString() {
		return "Hr [id=" + id + ", name=" + name + ", phone=" + phone + ", telephone=" + telephone + ", address="
				+ address + ", enabled=" + enabled + ", username=" + username + ", password=" + password + ", userface="
				+ userface + ", remark=" + remark + "]";
	}

}
