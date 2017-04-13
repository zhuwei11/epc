package com.zw.epc.service.base;

import java.util.Map;

/**
 * 业务执行结果接口
 * 
 * @author defier.lai 2011-9-7 下午12:02:43
 * @version 1.0
 */
public interface Result {

	/** 在models表中代表默认的model的key。 */
	String DEFAULT_MODEL_KEY = "_defaultModel";

	/**
	 * 是否成功。
	 *
	 * @return 如果成功，则返回<code>true</code>
	 */
	boolean isSuccess();

	/**
	 * 取得结果代码。
	 *
	 * @return 结果代码
	 */
	ResultCode getResultCode();

	/**
	 * 设置结果代码。
	 *
	 * @param resultCode
	 *            结果代码
	 */
	void setResultCode(ResultCode resultCode);

	/**
	 * 取得默认model对象的key。
	 *
	 * @return 默认model对象的key
	 */
	String getDefaultModelKey();

	/**
	 * 取得model对象。
	 * 
	 * <p>
	 * 此调用相当于<code>getModels().get(getDefaultModelKey())</code>。
	 * </p>
	 *
	 * @return model对象
	 */
	Object getDefaultModel();

	/**
	 * 设置model对象。
	 * 
	 * <p>
	 * 此调用相当于<code>getModels().put(DEFAULT_MODEL_KEY, model)</code>。
	 * </p>
	 *
	 * @param model对象
	 */
	void setDefaultModel(Object model);

	/**
	 * 设置model对象。
	 * 
	 * <p>
	 * 此调用相当于<code>getModels().put(key, model)</code>。
	 * </p>
	 *
	 * @param key
	 *            字符串key
	 * @param model
	 *            model对象
	 */
	void setModel(String key, Object model);

	/**
	 * 取得所有model对象。
	 *
	 * @return model对象表
	 */
	Map<String, Object> getModels();
}
