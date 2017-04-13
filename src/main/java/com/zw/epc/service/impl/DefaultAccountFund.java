/**
 * Copyright (c) Since 2014, Power by Pw186.com
 */
package com.zw.epc.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.zw.epc.service.AccountFund;
import com.zw.epc.service.base.DefaultResult;
import com.zw.epc.service.base.Result;
import com.zw.epc.service.base.ResultCode;
import com.zw.epc.service.base.TraderEventProducer;
import com.zw.epc.silkie.impl.EpcEventParam;
import com.zw.epc.trader.EpcPidConstant;
import com.zw.epc.trader.EpcTidConstant;
import com.zw.epc.trader.protocol.p110.P110990;
import com.zw.epc.trader.protocol.p120.P120010;


/**
 * 帐户资金变更交易接口
 *
 * 2016年5月28日上午10:54:52
 *
 * @author zw
 */
@Service
public class DefaultAccountFund implements AccountFund {

	private static final Logger LOG = Logger.getLogger(DefaultAccountFund.class);
	@Resource
	private TraderEventProducer traderEventProducer;
	
	@Override
	public Result recharge(int tenantId, int memberId, int rechargeId) {
		DefaultResult result = new DefaultResult();
		ResultCode resultCode = new ResultCode(ResultCode.SUCCESS, "帐户充值成功");
		try {
			P120010 p120010 = new P120010();
			p120010.setTenantId(tenantId);
			p120010.setMemberId(memberId);
			p120010.setRechargeId(rechargeId);
			EpcEventParam param = traderEventProducer.sendRequestMessage(EpcTidConstant.T320011, p120010);
			if(param == null) {
				resultCode = new ResultCode(ResultCode.SUCCESS, "帐户充值已提交,正在处理中");
			} else {
				P110990 p110990 = (P110990) param.queryFirst(EpcPidConstant.P110990);
				resultCode = new ResultCode(p110990.getRtnCode(), p110990.getRtnMsg());
			}
			
		} catch (Exception e) {
			resultCode = new ResultCode(ResultCode.FAILURE, "帐户充值失败");
			LOG.error("帐户充值异常", e);
		}
		result.setResultCode(resultCode);
		return result;
	}




}
