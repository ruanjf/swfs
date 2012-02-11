package com.ruanjf.springMVC.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruanjf.springMVC.commons.Utils;
import com.ruanjf.springMVC.persistent.User;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController {
	
	@RequestMapping
	public String index(HttpServletRequest request){
		User user = Utils.getLoginUser(request);
		if(user!=null)
			return "redirect:/u/"+user.getUserId();
		return "login";
	}
}
