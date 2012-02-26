package com.ruanjf.springMVC.web.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruanjf.springMVC.commons.Constants;
import com.ruanjf.springMVC.commons.Utils;
import com.ruanjf.springMVC.persistent.Company;
import com.ruanjf.springMVC.persistent.User;
import com.ruanjf.springMVC.services.CompanyService;
import com.ruanjf.springMVC.services.UserService;

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
	
	@RequestMapping(value="{companyId}", produces="application/json")
	@ResponseBody
	public Company info(@PathVariable String companyId){
		Company company = companyService.getCompany(companyId);
		if(company!=null){
			String[] area = company.getSearchAddress().split(",");
			if(area!=null && area.length>=3){
				Map<String, String> areaData = Constants.getInstance().getAreaDataMap();
				String province = areaData.get(area[0]); // 省
				String city = areaData.get(area[1]); // 市
				if("县".equals(city) || "市辖区".equals(city))
					city="";
				String county = areaData.get(area[2]); // 县（县级市）
				company.setAddress(province+city+county+company.getAddress());
			}
			
			User u = userService.getUserById(company.getPrincipal().toString());
			if(u!=null){
				company.setPrincipalName(u.getUsername());
			}
			return company;
		}
		return new Company();
	}
	
	@Resource
	private CompanyService companyService;
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	@Resource
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}
