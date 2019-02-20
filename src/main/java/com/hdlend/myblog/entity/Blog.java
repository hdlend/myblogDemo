package com.hdlend.myblog.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="blog")
public class Blog {
	
	@Id
    @GeneratedValue()
	private Integer bId;
	
	private String bTitle;
	
	private String bType;
	
	private String bAbstract;
	
	private String bContent;
	
	
	@Column(updatable=false)
	private Date bAddDate;
	
	private Date bUpdateDate;
	

	
	public Blog() {};

	public Integer getbId() {
		return bId;
	}

	public void setbId(Integer bId) {
		this.bId = bId;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbType() {
		return bType;
	}

	public void setbType(String bType) {
		this.bType = bType;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public Date getbAddDate() {
		return bAddDate;
	}

	public void setbAddDate(Date bAddDate) {
		this.bAddDate = bAddDate;
	}

	public Date getbUpdateDate() {
		return bUpdateDate;
	}

	public void setbUpdateDate(Date bUpdateDate) {
		this.bUpdateDate = bUpdateDate;
	}

	public String getbAbstract() {
		return bAbstract;
	}

	public void setbAbstract(String bAbstract) {
		this.bAbstract = bAbstract;
	}

	@Override
	public String toString() {
		return "Blog [bId=" + bId + ", bTitle=" + bTitle + ", bType=" + bType + ", bAbstract=" + bAbstract
				+ ", bAddDate=" + bAddDate + ", bUpdateDate=" + bUpdateDate + ", bContent=" + bContent + "]";
	}
	
	

}
