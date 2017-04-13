/**
 * pw186.com Inc. Copyright (c) 2015 All Rights Reserved.
 */
package com.zw.epc.trader.biz;

import com.zw.epc.silkie.field.BaseDataField;
import com.zw.epc.silkie.impl.BaseEpcEvent;
import com.zw.epc.silkie.impl.EpcEventParam;
import com.zw.epc.trader.protocol.p110.P110990;

/**
 * 采集控制器JMS事件基类
 * 
 * @author defier.lai 2012-7-13 下午06:11:59
 * @version 1.0
 */
public abstract class BaseTraderEvent extends BaseEpcEvent {

  protected EpcEventParam eventParam;
  public int memberId;

  
  @Override
  protected int checkParam(EpcEventParam param) throws Exception {
	 this.eventParam = param;
	 return checkBizParam(eventParam);
  }
  
  protected abstract int checkBizParam(EpcEventParam param) throws Exception;
  
  
  /**
   * 参数错误的处理方式
   */
  @Override
  public void doParamError(int errCode) throws Exception {

  }
  
  protected void responseCommonMessage(int tid, int errCode, String errMsg) throws Exception {
	  P110990 p110990 = new P110990();
	  p110990.setRtnCode((short)errCode);
	  p110990.setRtnMsg(errMsg);
	  responseMessage(tid, p110990);
  }
  
  /**
   * 响应消息
   * @param tid
   * @param fields
   * @throws Exception
   */
  protected void responseMessage(int tid, BaseDataField... fields) throws Exception {
	 EpcEventParam param = new EpcEventParam();
	 param.setFields(fields);
	 param.setTid(tid);
	 
	 setResponseParam(param); // 设置响应结果
	 
	 eventParam.getModeLock().singal(); // 释放锁
	 
  }
  
  public void setMemberId(int memberId) {
	this.memberId = memberId;  
  }
  
  public int getMemberId() {
	  return memberId;
  }
  
}
