package com.zw.epc.trader.protocol.p120;  

import com.zw.epc.silkie.field.BaseDataField;
import com.zw.epc.trader.EpcPidConstant;

/**
 * 在线账户充值
 *
 * 2016年5月25日下午2:52:49
 *
 * @author zw
 */
public class P120010 extends BaseDataField {

	/**
	 * 租户ID
	 */
	private int tenantId;
	/**
	 * 用户ID
	 * 冲突体  同一用户不能并发操作
	 */
	private int memberId;
	/**
	 * 充值ID
	 */
	private int rechargeId;
	
	@Override
	public int id() {
		return EpcPidConstant.P120010;
	}

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getRechargeId() {
		return rechargeId;
	}

	public void setRechargeId(int rechargeId) {
		this.rechargeId = rechargeId;
	}

}
