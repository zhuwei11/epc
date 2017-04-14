/**
 * Copyright (c) Since 2014, Power by Pw186.com
 */
package com.zw.epc.trader.biz.t320;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.zw.epc.module.AllAccounts;
import com.zw.epc.module.MemberAccount;
import com.zw.epc.silkie.EpcErrCode;
import com.zw.epc.silkie.impl.Collision;
import com.zw.epc.silkie.impl.EpcEventParam;
import com.zw.epc.trader.EpcPidConstant;
import com.zw.epc.trader.EpcTidConstant;
import com.zw.epc.trader.biz.BaseTraderEvent;
import com.zw.epc.trader.protocol.p120.P120020;

/**
 * 帐户提现
 *
 * 2016年5月25日下午4:55:46
 *
 * @author zw
 */
public class T320021 extends BaseTraderEvent {
	
	private static final Log LOG = LogFactory.getLog(T320021.class);
	
	private P120020 p120020;
	
//	@Resource
//	private AllAccounts allAccounts;
	
	@Override
	protected int checkBizParam(EpcEventParam param) throws Exception {
		if(param == null) {
			if(LOG.isWarnEnabled()) LOG.warn("未收到事件参数");
			return EpcErrCode.INVALID_PARAM;
		}
		p120020 = (P120020)param.queryFirst(EpcPidConstant.P120020);
		if (p120020 == null) {
			return EpcErrCode.INVALID_PARAM;
		}
		if(p120020.getTenantId() <= 0 || p120020.getMemberId() <= 0) {
			return EpcErrCode.INVALID_PARAM;
		}
		setMemberId(p120020.getMemberId());
		return EpcErrCode.SUCCESS;
	}

	@Override
	protected void doBiz() throws Exception {
		
		try {
			@SuppressWarnings("resource")
			ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:spring-context.xml");
			MemberAccount account = new MemberAccount();
			AllAccounts allAccounts = (AllAccounts) ac.getBean("allAccounts");
			List<MemberAccount> list = allAccounts.getAll();
			for(MemberAccount memberAccount : list) {
				if(memberAccount.getId() == p120020.getMemberId()) {
					account = memberAccount;
				}
			}
			if(account.getBalance() > 0) {
				account.setBalance(account.getBalance() - 1);
			}else {
				responseCommonMessage(EpcTidConstant.T320022, EpcErrCode.UNKNOWN_ERR, "提现失败，余额不足");
				return;
			}
			responseCommonMessage(EpcTidConstant.T320022, EpcErrCode.SUCCESS, "提现成功");
		
		} catch (Exception e) {
			responseCommonMessage(EpcTidConstant.T320022, EpcErrCode.UNKNOWN_ERR, "在线充值失败，error:"+e.getMessage());
			LOG.error("在线充值异常", e);
		} finally {
		}
		
	}

	@Override
	public Collision getCollision() {
		p120020 = (P120020)param.queryFirst(EpcPidConstant.P120020);
		/**
		 * 提现事件的冲突体为会员id
		 */
		return Collision.generateCollision("memberId_"+p120020.getMemberId());
	}

}
