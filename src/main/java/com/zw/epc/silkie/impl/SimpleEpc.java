/**
 * Copyright (c) 2014 by pw186.com.
 * All right reserved.
 */
package com.zw.epc.silkie.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zw.epc.silkie.EpcEvent;

/**
 * 非常简单的事件处理中心
 * 按时间顺序排队，挨个执行
 *
 */
public class SimpleEpc extends BaseEpc {
	private static final Log log = LogFactory.getLog(SimpleEpc.class);
	private ExecutorService es;
	private boolean isShutdown;
	SimpleEpc() {
		es = Executors.newFixedThreadPool(1);
		isShutdown = false;
	}
	
	@Override
	public void pushEvent(EpcEvent event, Collision collis) {
		if (isShutdown) return;
		try {
			es.submit(new Task(event, collis));
		} catch (Exception ex) {
			log.error("execute event error:", ex);
		}
	}

	@Override
	public void shutdown() {
		isShutdown = true;
		es.shutdown();
	}

	@Override
	public void shutdownNow() {
		isShutdown = true;
		es.shutdownNow();
	}

}
