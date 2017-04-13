package com.zw.epc.silkie.field;




public interface DataField extends Cloneable{

	/**报头长度 */
	public static final short FIELD_HEADER_LEN =  6;

	/**
	 * 唯一标识id
	 * 数据域标识
	 */
	public int id();
	
	
	
}
