package com.ruanjf.springMVC.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruanjf.springMVC.commons.Utils;
import com.ruanjf.springMVC.persistent.User;

@Controller
@RequestMapping("/c")
public class CompanyController extends BaseController {
	
	@RequestMapping("list")
	public String list(HttpServletRequest request){
		User user = Utils.getLoginUser(request);
		if(user!=null){
			if("admin".equals(user.getRoles()))
				return "companyList";
			else
				return "redirect:/u/"+user.getUserId();
		}
		return "login";
	}
}
