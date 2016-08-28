package cn.zjc.entity;

import java.util.Date;

/**
 * @author zjc
 * @version 2016/8/28 23:15
 * @description
 */
public class User {

	private Integer id;
	private String name;
	private String email;
	private Date birth;

	public User() {
	}

	public User(Integer id, String name, String email, Date birth) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.birth = birth;
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
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", birth=" + birth +
				'}';
	}
}
