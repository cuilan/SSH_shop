package cn.cuilan.shop.adminuser.entity;

import java.io.Serializable;

/**
 * 后台用户实体对象
 * 
 * @author 翠兰123
 * @date 2015年3月26日
 * @SQL CREATE TABLE 'adminuser' ( 'uid' int(11) NOT NULL AUTO_INCREMENT,
 *      'username' varchar(255) DEFAULT NULL, 'password' varchar(255) DEFAULT
 *      NULL, PRIMARY KEY ('uid') ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT
 *      CHARSET=utf8;
 */
public class AdminUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer uid;

	private String username;

	private String password;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
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

}
