package com.hdlend.myblog.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="user")
public class User {
	
	@Id
	@GenericGenerator(name="idGenerator", strategy="uuid") //这个是hibernate的注解/生成32位UUID
    @GeneratedValue(generator="idGenerator")
	@Column(length=32)
	private String uId;
	
	private String uName;
	
	private String uPass;
	
	private String uNickName;
	
	private String uHeadImage;
	
	private String uQq;
	
	private String uWechat;
	
	private String uPhone;
	
	private String uEmil;
	
	private Integer uState;
	
	@Column(updatable=false)
	private Date uAddDate;
	
	private Date uUpdateDate;
	
	public User() {};

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuNickName() {
		return uNickName;
	}

	public void setuNickName(String uNickName) {
		this.uNickName = uNickName;
	}

	public String getuHeadImage() {
		return uHeadImage;
	}

	public void setuHeadImage(String uHeadImage) {
		this.uHeadImage = uHeadImage;
	}

	public String getuQq() {
		return uQq;
	}

	public void setuQq(String uQq) {
		this.uQq = uQq;
	}

	public String getuWechat() {
		return uWechat;
	}

	public void setuWechat(String uWechat) {
		this.uWechat = uWechat;
	}

	public String getuPhone() {
		return uPhone;
	}

	public void setuPhone(String uPhone) {
		this.uPhone = uPhone;
	}

	public String getuEmil() {
		return uEmil;
	}

	public void setuEmil(String uEmil) {
		this.uEmil = uEmil;
	}

	public Integer getuState() {
		return uState;
	}

	public void setuState(Integer uState) {
		this.uState = uState;
	}

	public Date getuAddDate() {
		return uAddDate;
	}

	public void setuAddDate(Date uAddDate) {
		this.uAddDate = uAddDate;
	}

	public Date getuUpdateDate() {
		return uUpdateDate;
	}

	public void setuUpdateDate(Date uUpdateDate) {
		this.uUpdateDate = uUpdateDate;
	}

	
	public String getuPass() {
		return uPass;
	}

	public void setuPass(String uPass) {
		this.uPass = uPass;
	}

	@Override
	public String toString() {
		return "User [uId=" + uId + ", uName=" + uName + ", uPass=" + uPass + ", uNickName=" + uNickName
				+ ", uHeadImage=" + uHeadImage + ", uQq=" + uQq + ", uWechat=" + uWechat + ", uPhone=" + uPhone
				+ ", uEmil=" + uEmil + ", uState=" + uState + ", uAddDate=" + uAddDate + ", uUpdateDate=" + uUpdateDate
				+ "]";
	}

	

	
	
}
