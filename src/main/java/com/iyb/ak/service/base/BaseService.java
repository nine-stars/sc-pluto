package com.iyb.ak.service.base;

import com.iyb.ak.entity.base.BaseEncryptEntity;
import com.iyb.ak.entity.base.BaseEntity;
import com.iyb.ak.exception.BadRequestException;
import org.apache.commons.collections.CollectionUtils;
import tk.mybatis.mapper.common.Mapper;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T extends BaseEntity, ID extends Serializable> {

	Mapper<T> getBaseMapper();

	default T insert(T record) {
//		record.buildForInsert();
		this.encrypt(record);
		int re = this.getBaseMapper().insert(record);
		this.decrypt(record);
		if (re == 0) {
			throw new BadRequestException("error.0003", this);
		}
		return record;
	}

	default T selectByPrimaryKey(ID id) {
		if (id == null) {
			return null;
		}
		T record = this.getBaseMapper().selectByPrimaryKey(id);
		this.decrypt(record);
		return record;
	}

	default List<T> select(T record) {
		this.encrypt(record);
		List<T> records = this.getBaseMapper().select(record);
		if (CollectionUtils.isNotEmpty(records)) {
			for (T item : records) {
				this.decrypt(item);
			}
		}
		return records;
	}

	default void encrypt(T record) {
		if (record == null) {
			return;
		}
		if (record instanceof BaseEncryptEntity) {
			((BaseEncryptEntity) record).encrypt();
		}
	}

	default void decrypt(T record) {
		if (record == null) {
			return;
		}
		if (record instanceof BaseEncryptEntity) {
			((BaseEncryptEntity) record).decrypt();
		}
	}

	default T updateByPrimaryKey(T record) {
//		record.buildForUpdate();
		this.encrypt(record);
		int re = this.getBaseMapper().updateByPrimaryKey(record);
		this.decrypt(record);
		if (re == 0) {
			throw new BadRequestException("error.0000", this);
		}
		return record;
	}

	default void deleteByPrimaryKey(ID id) {
		this.getBaseMapper().deleteByPrimaryKey(id);
	}

	default T insertSelective(T record) {
//		record.buildForInsert();
		this.encrypt(record);
		int re = this.getBaseMapper().insertSelective(record);
		this.decrypt(record);
		if (re == 0) {
			throw new BadRequestException("error.0003", this);
		}
		return record;
	}

	default T updateByPrimaryKeySelective(T record) {
//		record.buildForUpdate();
		this.encrypt(record);
		int re = this.getBaseMapper().updateByPrimaryKeySelective(record);
		this.decrypt(record);
		if (re == 0) {
			throw new BadRequestException("error.0000", this);
		}
		return record;
	}
}