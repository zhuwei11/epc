package com.zw.epc.module;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class AllAccounts {
	
	private static List<MemberAccount> all;
	
	static {
		all = new ArrayList<>(); 
		all.add(new MemberAccount(1, 5));
		all.add(new MemberAccount(2, 100));
	}

	public List<MemberAccount> getAll() {
		return all;
	}
}
