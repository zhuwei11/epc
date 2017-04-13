/**
 * 
 */
package com.zw.epc.silkie.field;


/**
 * @author defier
 * 2011-4-14 下午05:39:43
 * @since 1.0
 */
public abstract class BaseDataField implements DataField {
	
	private String orderCode;

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	
}
