/**
 * @author zw
 * 2017年4月13日 上午9:46:51
 * @version 1.0	
 */
package com.zw.epc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zw.epc.module.AllAccounts;
import com.zw.epc.module.MemberAccount;
import com.zw.epc.service.AccountFund;
import com.zw.epc.service.base.ResultCode;


@Controller
public class IndexController {
	@Autowired
	private AccountFund accountFund;
	
	@Autowired
	private AllAccounts allAccounts;
	
	@RequestMapping("/{memberId}")
	public String index(Model model,@PathVariable Integer memberId) {
		MemberAccount account = new MemberAccount();
		for(MemberAccount aa : allAccounts.getAll()) {
			if(aa.getId() == memberId) {
				account = aa;
			}
		}
		model.addAttribute("account", account);
		return "index";
	}
	
	/**
	 * 模拟充值
	 * @param model
	 * @return
	 * @throws InterruptedException 
	 */
	@RequestMapping("/rechage/{memberId}")
	public String rechage(Model model,
			@PathVariable Integer memberId) throws InterruptedException {
		new Thread(new rechage(memberId)).start();
		new Thread(new rechage(memberId)).start();
		new Thread(new rechage(memberId)).start();
		new Thread(new rechage(memberId)).start();
		new Thread(new rechage(memberId)).start();
		
		Thread.sleep(2000);
		MemberAccount account = new MemberAccount();
		for(MemberAccount aa : allAccounts.getAll()) {
			if(aa.getId() == memberId) {
				account = aa;
			}
		}
		model.addAttribute("balance", account.getBalance());
		return "rechageResult";
	}
	
	@RequestMapping("/withdraw/{memberId}")
	public String withdraw(Model model,
			@PathVariable Integer memberId) throws InterruptedException {
//		ResultCode code = accountFund.withdraw(1, 1).getResultCode();//用户1余额5元
//		ResultCode code = accountFund.withdraw(1, 2).getResultCode();//用户2余额100元
		new Thread(new withdraw(memberId)).start();
		new Thread(new withdraw(memberId)).start();
		new Thread(new withdraw(memberId)).start();
		new Thread(new withdraw(memberId)).start();
		new Thread(new withdraw(memberId)).start();
		new Thread(new withdraw(memberId)).start();
		new Thread(new withdraw(memberId)).start();
		new Thread(new withdraw(memberId)).start();
		
		/**
		 * 模拟并发提现 操作完成后 如果余额不为负数 则说明有效
		 */
		Thread.sleep(2000);
		MemberAccount account = new MemberAccount();
		for(MemberAccount aa : allAccounts.getAll()) {
			if(aa.getId() == memberId) {
				account = aa;
			}
		}
		model.addAttribute("balance", account.getBalance());
		return "withdrawResult";
	}
	
	class rechage implements Runnable{
		private int id;
		public rechage(int id) {
			this.id = id;
		}
		@Override
		public void run() {
			ResultCode code = accountFund.recharge(1,id,1).getResultCode();
			System.out.println("提现结果:"+code.getMessage());
			
		}
		
	}
	
	class withdraw implements Runnable{
		private int id;
		public withdraw(int id) {
			this.id = id;
		}
		@Override
		public void run() {
//			ResultCode code = accountFund.withdraw(1, 1).getResultCode();
			ResultCode code = accountFund.withdraw(1, id).getResultCode();
			System.out.println("提现结果:"+code.getMessage());
			
		}
		
	}
	
}