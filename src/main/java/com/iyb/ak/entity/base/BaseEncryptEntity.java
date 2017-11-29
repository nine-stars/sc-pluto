package com.iyb.ak.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 提供加密功能的实体类
 * @author yc096
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class BaseEncryptEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5341273669027825823L;

	/*@JsonIgnore
	@Autowired
	@Transient
	private PasswordManager passwordManager;*/
	
	/**
	 * 加密用密码版本，0为不加密
	 */
	@JsonIgnore
	protected Integer cryptVer = null; 
	
	public void encrypt() {
		/*List<Field> fields = getEncryptFields();
		if (fields != null) {
			if (passwordManager == null)
				passwordManager = ApplicationContextUtils.getBean(PasswordManager.class);
			cryptVer = passwordManager.getLastestPasswordVersion();
			String p1 = passwordManager.getPassword(1);
			String ps = passwordManager.getPassword(cryptVer);
			for (Field field : fields) {
				field.setAccessible(true);
				try {
					String value = (String) field.get(this);
					if (value != null) {
						EncryptField anno = field.getAnnotation(EncryptField.class);
						if (anno.fixed()) {
							value = EncryptUtil.encrypt(value, p1);
						} else {
							value = EncryptUtil.encrypt(value, ps);
						}
						field.set(this, value);
					}
				} catch (IllegalAccessException ex) {
					throw new RuntimeException(ex);
				}
			}
		}*/
	}
	
	public void decrypt()  {
		/*if (cryptVer == null || cryptVer <= 0)
			return;
		List<Field> fields = getEncryptFields();
		if (fields != null) {
			if (passwordManager == null)
				passwordManager = ApplicationContextUtils.getBean(PasswordManager.class);
			String p1 = passwordManager.getPassword(1);
			String ps = passwordManager.getPassword(cryptVer);
			for (Field field : fields) {
				field.setAccessible(true);
				try {
					String value = (String) field.get(this);
					if (value != null) {
						EncryptField anno = field.getAnnotation(EncryptField.class);
						if (anno.fixed()) {
							value = EncryptUtil.decrypt(value, p1);
						} else {
							value = EncryptUtil.decrypt(value, ps);
						}
						field.set(this, value);
					}
				}catch (IllegalAccessException ex){
					throw new RuntimeException(ex);
				}
			}
		}*/
	}
	
	protected List<Field> getEncryptFields() {
		/*Field[] fields = this.getClass().getDeclaredFields();
		Stream<Field> fieldStream = Stream.of(fields);
		return fieldStream.filter(f -> f.isAnnotationPresent(EncryptField.class) &&
				(f.getType() ==ring.class))
			.collect(Collectors.toList());*/
		return null;
	}
}
