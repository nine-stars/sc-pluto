package com.iyb.ak.utils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
* @ClassName: ApplicationContextUtils
* @Description: applicationUtils
* @author zhangsk
* @date 2016年5月4日 下午3:02:36
*
*/ 
@Component(value="applicationUtils")
@Lazy(false)
public final class ApplicationContextUtils implements ApplicationContextAware,DisposableBean{

	private static ApplicationContext applicationContext;
	
	/**
	 * 不可实例化
	 */
	private ApplicationContextUtils(){
		
	}

	public static String getProperty(String propertyName, String defaultValue) {
		String value = applicationContext.getEnvironment().getProperty(propertyName);
		return value == null ? defaultValue : value;
	}

	public static String getProperty(String key) {
		return applicationContext.getEnvironment().getProperty(key);
	}

	/** 
	* @Title: setApplicationContext 
	* @Description: setApplicationContext
	* @return 
	* @throws 
	*/
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

		ApplicationContextUtils.applicationContext = applicationContext;
	}
	

	/** 
	* @Title: getApplicationContext 
	* @Description: getApplicationContext
	* @return 
	* @throws 
	*/
	public static ApplicationContext getApplicationContext() {

		return applicationContext;
	}

	
	/** 
	* @Title: destroy 
	* @Description: bean销毁时执行
	* @return 
    * @throws 
	*/
	public void destroy() throws Exception {
		applicationContext = null;
	}
	
	
	/**
	 * 获取实例
	 * 
	 * @param name
	 *            Bean名称
	 * @return 实例
	 */
	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}
	
	/**
	 * 获取实例
	 * 
	 * @param type
	 *            Bean类型
	 * @return 实例
	 */
	public static <T> T getBean(Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}

	
	/**
	 * 获取实例
	 * 
	 * @param name
	 *            Bean名称
	 * @param type
	 *            Bean类型
	 * @return 实例
	 */
	public static <T> T getBean(String name, Class<T> type) {
		return applicationContext.getBean(name, type);
	}
	

}