package com.zw.epc.service;

import com.zw.epc.service.base.Result;

/**
 * 帐户资金变更交易接口
 *
 * 2016年5月28日上午10:54:21
 *
 * @author zw
 */
public interface AccountFund {
	

	
	public Result recharge(int tenantId, int memberId, int rechargeId);
	
	public Result withdraw(int tenantId, int memberId);


}
