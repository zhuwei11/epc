package com.zw.epc.trader.protocol.p120;  

import com.zw.epc.silkie.field.BaseDataField;
import com.zw.epc.trader.EpcPidConstant;

/**
 * 在线账户提现
 *
 * 2016年5月25日下午2:52:49
 *
 * @author zw
 */
public class P120020 extends BaseDataField {

	/**
	 * 租户ID
	 */
	private int tenantId;
	/**
	 * 用户ID
	 * 冲突体  同一用户不能并发操作
	 */
	private int memberId;
	
	
	@Override
	public int id() {
		return EpcPidConstant.P120020;
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

	

}
