package com.zw.epc.service.base;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 默认result实现
 * 
 * @author defier.lai 2011-9-7 下午12:10:17
 * @version 1.0
 */
public class DefaultResult implements Result {

	private boolean success = true;
	/** 处理返回的代码及消息 **/
	private ResultCode resultCode;
	/** 处理返回的数据模型 **/
	private Map<String, Object> models = new HashMap<String, Object>();

	/**
	 * 创建一个result。
	 */
	public DefaultResult() {
	}

	/**
	 * 创建一个result。
	 * 
	 * @param success
	 *            是否成功
	 */
	public DefaultResult(boolean success) {
		this.success = success;
		if (success) {
			this.resultCode = new ResultCode(ResultCode.SUCCESS);
		} else {
			this.resultCode = new ResultCode(ResultCode.FAILURE);
		}
	}

	/**
	 * 创建一个result。
	 * 
	 * @param success
	 *            是否成功
	 */
	public DefaultResult(ResultCode resultCode) {
		if (resultCode.getCode() == ResultCode.SUCCESS) {
			this.success = true;
		} else {
			this.success = false;
		}
		this.resultCode = resultCode;
	}

	/**
	 * 请求是否成功。
	 * 
	 * @return 如果成功，则返回<code>true</code>
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * 取得结果代码。
	 * 
	 * @return 结果代码
	 */
	public ResultCode getResultCode() {
		return resultCode;
	}

	/**
	 * 设置结果代码。
	 * 
	 * @param resultCode
	 *            结果代码
	 */
	public void setResultCode(ResultCode resultCode) {
		this.resultCode = resultCode;
		if (resultCode.getCode() == ResultCode.SUCCESS) {
			success = true;
		} else {
			success = false;
		}
	}

	/**
	 * 取得默认model对象的key。
	 * 
	 * @return 默认model对象的key
	 */
	public String getDefaultModelKey() {
		return DEFAULT_MODEL_KEY;
	}

	/**
	 * 取得model对象。
	 * 
	 * <p>
	 * 此调用相当于<code>getModels().get(DEFAULT_MODEL_KEY)</code>。
	 * </p>
	 * 
	 * @return model对象
	 */
	public Object getDefaultModel() {
		return models.get(getDefaultModelKey());
	}

	/**
	 * 设置model对象。
	 * 
	 * <p>
	 * 此调用相当于<code>getModels().put(DEFAULT_MODEL_KEY, model)</code>。
	 * </p>
	 * 
	 * @param model
	 *            model对象
	 */
	public void setDefaultModel(Object model) {
		setModel(DEFAULT_MODEL_KEY, model);
	}

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
	public void setModel(String key, Object model) {
		if (StringUtils.isEmpty(key)) {
			throw new IllegalArgumentException("model key can not empty!");
		}
		models.put(key, model);
	}

	/**
	 * 取得所有model对象。
	 * 
	 * @return model对象表
	 */
	public Map<String, Object> getModels() {
		return models;
	}

}
