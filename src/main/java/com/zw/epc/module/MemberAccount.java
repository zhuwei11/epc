package com.zw.epc.module;

import java.util.List;

/**
 * 模拟会员账户
 * @author zw
 * 2017年4月14日 上午9:29:20
 * @version 1.0	
 */
public class MemberAccount {
	
	private int id;
	
	private double balance;
	
	private static List<MemberAccount> all;
	
	
	public MemberAccount() {
		super();
	}
	
	public MemberAccount(int id,double balance) {
		super();
		this.id = id;
		this.balance = balance;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<MemberAccount> getAll() {
		return all;
	}

}
