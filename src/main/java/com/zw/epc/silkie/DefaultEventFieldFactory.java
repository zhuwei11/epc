/**
 * 
 */
package com.zw.epc.silkie;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zw.epc.silkie.field.DataField;

/**
 * 协议解码编码、事件创建工厂
 * @author defier
 * 2011-4-15 下午02:27:58
 * @since 1.0
 */
public class DefaultEventFieldFactory implements EventFieldFactory {
	
	private static final Log LOG = LogFactory.getLog(DefaultEventFieldFactory.class);
	
	private String fieldPackageBase = "com.pw186.silkie.jms.demo.fields";
	
	private String eventPackageBase = "com.pw186.silkie.jms.demo.event";
	
	public final static String FIELD_HEAD = "P";
	
	public final static String EVENT_HEAD = "T";
	

	public DefaultEventFieldFactory() {
		
	}
	
	/**
	 * 有的进行加速
	 */
	private final Map<String, Class<?>> clazz = new ConcurrentHashMap<String, Class<?>>();
	/**
	 * 没有的进行加速
	 */
	private final Map<String, Boolean> clazz_null = new ConcurrentHashMap<String, Boolean>();

	/**
	 * 获取package
	 * @param head
	 * @param commandCode
	 * @return
	 */
	private String getBasePackage(String head, int commandCode) {
		StringBuffer sb = new StringBuffer();
		if(head.equalsIgnoreCase(FIELD_HEAD)) {
			sb.append(getFieldPackageBase());
		} else {
			sb.append(getEventPackageBase());
		}
		sb.append(".");
		sb.append(head.toLowerCase() + (commandCode / 1000)); // 说明
		sb.append(".");
		return sb.toString();
	}

	/**
	 * 按规则 组装全局 类名
	 * @param head
	 * @param commandCode
	 * @return
	 */
	private String generateSubPackage(String head, int commandCode) {
		return head + commandCode;
	}

	@Override
	public DataField createField(int fid) throws Exception {
		Class<?> c = getClazz(FIELD_HEAD, fid);
		if (c == null)
			return null;
		else
			return (DataField) c.newInstance();
	}

	@Override
	public EpcEvent createEvent(int tid) throws Exception {
		Class<?> c = getClazz(EVENT_HEAD, tid);
		if (c == null)
			return null;
		else
			return (EpcEvent) c.newInstance();
	}
	
	protected Class<?> getClazz(String head, int id) {
		String key = head+id;
		Class<?> c = clazz.get(key);
		if (c == null) {
			if (clazz_null.get(key) != null)
				return null;
			try {
				c = Class.forName(getBasePackage(head, id) + generateSubPackage(head, id));
			} catch (ClassNotFoundException e) {
				clazz_null.put(key, true);
				LOG.info("class is null");
				LOG.info(getBasePackage(head, id) + generateSubPackage(head, id));
				return null;
			}
			clazz.put(key, c);
			LOG.info("class is ok");
		}
		return c;
	}

	public void setFieldPackageBase(String fieldPackageBase) {
		this.fieldPackageBase = fieldPackageBase;
	}

	public String getFieldPackageBase() {
		return fieldPackageBase;
	}

	public void setEventPackageBase(String eventPackageBase) {
		this.eventPackageBase = eventPackageBase;
	}

	public String getEventPackageBase() {
		return eventPackageBase;
	}
	
}
