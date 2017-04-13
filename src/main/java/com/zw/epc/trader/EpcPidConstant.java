package com.zw.epc.trader;

import com.zw.epc.silkie.PidConstant;

/**
 * Pid 常量表
 *
 * 2016年5月25日下午2:49:45
 *
 * @author zjg
 */
public class EpcPidConstant extends PidConstant {
	
	public static final byte VERSION = 0x10;
	/** 通用应答*/
	public static final int P110990 = 110990;
	/** 帐户充值 */
	public static final int P120010= 120010;
	/** 帐户提现 */
	public static final int P120020 = 120020;
	/** 帐户后台手工充值**/
	public static final int P120030 = 120030;
	/** 帐户提现确认 **/
	public static final int P120040 = 120040;
	/** 提现响应 **/
	public static final int P120050 = 120050;
	/** 用户发红包 **/
	public static final int P130010 = 130010;
	/** 用户抢红包 **/
	public static final int P130020 = 130020;
	/** 发红包响应 **/
	public static final int P130030 = 130030;
	/** 抢红包响应 **/
	public static final int P130040 = 130040;
	/** 红包过期通知 **/
	public static final int P130050 = 130050;
	/** 购买道具 **/
	public static final int P140010 = 140010;
	/** 用户抽奖 **/
	public static final int P140020 = 140020;
	/** 用户抽奖响应 **/
	public static final int P140030 = 140030;
}
