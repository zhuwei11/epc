/**
 * Copyright (c) Since 2014, Power by Pw186.com
 */
package com.zw.epc.service.base;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.zw.epc.silkie.Epc;
import com.zw.epc.silkie.EpcEvent;
import com.zw.epc.silkie.EventFieldFactory;
import com.zw.epc.silkie.ModeLock;
import com.zw.epc.silkie.field.DataField;
import com.zw.epc.silkie.impl.EpcEventParam;


/**
 * 消息生产者
 * @author defier
 * 2014年12月27日 下午5:21:14
 * @version 1.0
 */
@Component
public class TraderEventProducer {
	
	private final static Logger LOG = Logger.getLogger(TraderEventProducer.class);
	
	@Resource
	private ModeLock modeLock;
	@Resource
	private Epc epc;
	@Resource
	private EventFieldFactory eventFieldFactory;
	
	public EpcEventParam sendRequestMessage(int tid, DataField... fields) throws Exception {
		EpcEvent event = null;
		try {
			modeLock.lock();
			
			EpcEventParam param = new EpcEventParam();
			param.setFields(fields);
			param.setTid(tid);
			param.setModeLock(modeLock);
			
			event = eventFieldFactory.createEvent(tid);
			if(event == null) {
				LOG.warn("Unknow Event, Tid:" + tid);
			} else {
				event.setEventParam(param);
				epc.pushEvent(event, event.getCollision());
			}
			modeLock.await();

		} catch (Exception e) {
			LOG.error("发送请求异常！！！", e);
		} finally {
			modeLock.unlock();
		}
		return event.getResponseParam();
	}

	public void sendNoticeMessage(int tid, DataField... fields) throws Exception {
		try {
			EpcEventParam param = new EpcEventParam();
			param.setFields(fields);
			param.setTid(tid);
			param.setModeLock(modeLock);
			
			EpcEvent event = eventFieldFactory.createEvent(tid);
			if(event == null) {
				LOG.warn("Unknow Event, Tid:" + tid);
			} else {
				event.setEventParam(param);
				epc.pushEvent(event, event.getCollision());
			}

		} catch (Exception e) {
			LOG.error("发送通知异常！！！", e);
		} finally {
			
		}
	}
	
}
