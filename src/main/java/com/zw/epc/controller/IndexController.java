/**
 * @author zw
 * 2017年4月13日 上午9:46:51
 * @version 1.0	
 */
package com.zw.epc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zw.epc.service.AccountFund;
import com.zw.epc.service.base.ResultCode;


@Controller
public class IndexController {
	@Autowired
	private AccountFund accountFund;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	/**
	 * 模拟充值
	 * @param model
	 * @return
	 */
	@RequestMapping("/rechage")
	public String rechage(Model model) {
		ResultCode code = accountFund.recharge(1, 1, 1).getResultCode();
		model.addAttribute("result", code.getMessage());
		return "rechageResult";
	}
	
}