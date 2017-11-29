package com.iyb.ak.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iyb.ak.utils.UuidUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@SuppressWarnings("all")
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 858294042419172707L;

	/**
	 * 记录uuid
	 *//*
	@Id
	@Column(name = "UUID")
	private String uuid;
	*//**
	 * 创建者
	 *//*
	@JsonIgnore
	@Column(name = "INSERT_USER")
	private String insertUser;
	*//**
	 * 创建时间
	 *//*
	@JsonIgnore
	@Column(name = "INSERT_TIME")
	private Date insertTime;
	*//**
	 * 修改者
	 *//*
	@JsonIgnore
	@Column(name = "UPDATE_USER")
	private String updateUser;
	*//**
	 * 修改时间
	 *//*
	@JsonIgnore
	@Column(name = "UPDATE_TIME")
	private Date updateTime;
	*//**
	 * 删除状态（0-未删除，1-已删除）
	 *//*
	@JsonIgnore
	@Column(name = "DELETE_FLAG")
	private Boolean deleteFlag;

	public BaseEntity buildForInsert() {
		if (StringUtils.isBlank(this.uuid)) {
			this.setUuid(UuidUtils.base58Uuid());
		}

		if (Objects.isNull(this.insertUser)) {
//			this.setInsertUser(this.currentUserId());
		}

		if (Objects.isNull(this.insertTime)) {
			this.setInsertTime(new Date());
		}

		if (Objects.isNull(this.updateTime)) {
			this.setUpdateTime(new Date());
		}

		if (this.deleteFlag == null) {
			this.setDeleteFlag(false);
		}

		return this;
	}*/

/*	public BaseEntity buildForUpdate() {
		if(StringUtils.isNotEmpty(this.currentUserId()))
			this.setUpdateUser(this.currentUserId());
		this.setUpdateTime(new Date());
		return this;
	}

	public BaseEntity buildForDel() {
		if(StringUtils.isNotEmpty(this.currentUserId()))
			this.setUpdateUser(this.currentUserId());
		this.setUpdateTime(new Date());
		this.setDeleteFlag(true);
		return this;
	}*/

//	public void build(Object vo) {
//		BeanUtils.copyProperties(vo, this);
//	}

}