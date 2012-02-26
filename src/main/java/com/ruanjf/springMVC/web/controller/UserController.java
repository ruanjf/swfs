package com.ruanjf.springMVC.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruanjf.springMVC.commons.Constants;
import com.ruanjf.springMVC.commons.Return;
import com.ruanjf.springMVC.commons.Utils;
import com.ruanjf.springMVC.dao.support.Page;
import com.ruanjf.springMVC.persistent.Company;
import com.ruanjf.springMVC.persistent.User;
import com.ruanjf.springMVC.persistent.User.Status;
import com.ruanjf.springMVC.services.CompanyService;
import com.ruanjf.springMVC.services.UserService;

@Controller // 用于标注控制层组件（如struts中的action）
@RequestMapping("/u")
public class UserController extends BaseController {
	
	@RequestMapping
	public String home(HttpServletRequest request){
		User user = Utils.getLoginUser(request);
		if(user!=null){
			if("admin".equals(user.getRoles()))
				return "forward:/u/c";
			else
				return "forward:/u/"+user.getUserId();
				
		}
		return "login";
	}
	
	@RequestMapping("{userId}")
	public String user(@PathVariable String userId, Model model, HttpServletRequest request){
		User user = Utils.getLoginUser(request);
		if(user!=null){
			if("admin".equals(user.getRoles())){
				model.addAttribute("type", "user");
				User editUser = userService.getUserById(userId);
				model.addAttribute("msg", "用户");
				
				if(editUser!=null && !editUser.getUserId().equals(user.getUserId())){
					model.addAttribute("user", editUser);
					model.addAttribute("edit", true);
				}else{
					model.addAttribute("edit", false);
				}
				return "admin";
				
			}else if(user.getUserId().compareTo(Long.valueOf(userId))==0){
				model.addAttribute("user", user);
				return "home";
				
			}else{
				return "redirect:/u/"+userId;
			}
		}else{
			return "redirect:/";
		}
	}
	
	/**
	 * 跳转作用
	 * @return
	 */
	@RequestMapping("c")
	public String company(){
		return "forward:/u/c/0";
	}
	
	@RequestMapping(value="c/{companyId}")
	public String company(@PathVariable String companyId, Model model, HttpServletRequest request){
		
		User user = Utils.getLoginUser(request);
		if(user!=null){
			if("admin".equals(user.getRoles())){
				
				model.addAttribute("type", "company");
				model.addAttribute("users",userService.getList(0, 0, null).getResult());
				model.addAttribute("msg", "客户");
				
				Company company = companyService.getCompany(companyId);
				if(company!=null){
					model.addAttribute("company", company);
					model.addAttribute("edit", true);
				}else{
					model.addAttribute("edit", false);
				}
				return "admin";
				
			}else{
				return "redirect:errors/404";
			}
		}else{
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="udel/{userId}")
	@ResponseBody
	public Return delUser(@PathVariable String userId, Model model, HttpServletRequest request){
		userService.delUserById(userId);
		return new Return(0, "删除成功！");
	}
	
	@RequestMapping(value="cdel/{companyId}")
	@ResponseBody
	public Return delCompany(@PathVariable String companyId, Model model, HttpServletRequest request){
		companyService.delCompanyById(companyId);
		return new Return(0, "删除成功！");
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String login(@RequestParam String username, @RequestParam String password, Model model, HttpServletRequest request){
		
		if(Utils.notEmpty(username) && Utils.notEmpty(password)){
			User user = Utils.getLoginUser(request);
			if(user!=null) // 用户已登录
				if("admin".equals(user.getRoles()))
					return "redirect:/u/c";
				else
					return "redirect:/u/"+username;
			
			user = userService.login(username, password);
			if(user!=null){
				if(user.getStatus()!=null && Status.USE.compareTo(user.getStatus())==0){
					Utils.setLoginUser(request, user);
					if("admin".equals(user.getRoles()))
						return "redirect:/u/c";
					else
						return "redirect:/u/"+username;
				}else{
					model.addAttribute("msg", "当前账号不可用！");
				}
			
			}else{
				model.addAttribute("msg", "密码错误！");
			}
		}
		return "login";
	}
	
	@RequestMapping(value="ulist", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public Page<User> userList(HttpServletRequest request){
		User user = Utils.getLoginUser(request);
		if(user!=null){
			if("admin".equals(user.getRoles())){
				return userService.getList(Utils.getInt(request, "currPage"), Utils.getInt(request, "pageSize"), null);
			}else{
				return userService.getList(Utils.getInt(request, "currPage"), Utils.getInt(request, "pageSize"), user.getUserId());
			}
		}
		return new Page<User>();
	}
	
	@RequestMapping(value="clist", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public Page<CompanyForm> companyList(HttpServletRequest request){
		User user = Utils.getLoginUser(request);
		if(user!=null){
			Long userId = user.getUserId(); // 查询某个用户
			if("admin".equals(user.getRoles())){
				userId = Utils.getLong(request, "userId"); // 管理员可以看到所以客户
			}
			Page<Company> page = companyService.getList(Utils.getInt(request, "currPage"), Utils.getInt(request, "pageSize"), userId);
			
			if(page!=null && page.getTotalCount()>0){
				List<CompanyForm> cflist = new ArrayList<CompanyForm>();
				for (Company c : page.getResult()) {
					CompanyForm cf = new CompanyForm();
					BeanUtils.copyProperties(c, cf);
					cflist.add(cf);
					
					User u = userService.getUserById(String.valueOf(c.getPrincipal()));
					if(u!=null){
						cf.setPrincipalName(u.getUsername());
					}
				}
				return new Page<CompanyForm>(page.getStart(), page.getTotalCount(), page.getPageSize(), cflist);
			}
			return new Page<CompanyForm>();
		}
		return new Page<CompanyForm>();
	}
	
//	@RequestMapping(value="clist/{page:\\d*}", method=RequestMethod.GET, produces="application/json")
//	@ResponseBody
//	public String companyList(@PathVariable int page, HttpServletRequest request){
//		return "{\"clist\":0}";
//	}
	
	@RequestMapping(value="uedit", produces="application/json")
	@ResponseBody
	public Return userEdit(HttpServletRequest request){
		
		String username = Utils.getParameter(request, "username");
		String password = Utils.getParameter(request, "password");
		String password1 = Utils.getParameter(request, "password1");
		String desc = Utils.getParameter(request, "desc");
		String status = Utils.getParameter(request, "status");
		
		if(Utils.isEmpty(username)){
			return new Return(1, "用户名不能为空！");
		}
		if(Utils.isEmpty(password)){
			return new Return(1, "用户名密码为空！");
		}
		if(!password.equals(password1)){
			return new Return(1, "两次的密码输入不相同！");
		}
		if(Utils.isEmpty(status)){
			return new Return(1, "用户状态不能为空！");
		}
		
		String userId = request.getParameter("userId");
		User user = null;
		if(Utils.notEmpty(userId)){
			user = userService.getUserById(userId);
			user.setModifyTime(new Date());
			user.setModifyUser(Utils.getLoginUser(request).getUserId());
		}else{
			user = new User();
			user.setUserId(userService.getNextId());
			user.setCreateTime(new Date());
			user.setCreateUser(Utils.getLoginUser(request).getUserId());
		}
		user.setUsername(username);
		user.setPassword(password);
		user.setStatus(Status.valueOf(status));
		if(Utils.notEmpty(desc))
			user.setDesc(desc);
		
		userService.saveUser(user);
		return new Return(0, String.valueOf(user.getUserId()));
	}
	
	@RequestMapping(value="cedit", produces="application/json")
	@ResponseBody
	public Return companyEdit(HttpServletRequest request){
		
		// 公司名称
		String name = Utils.getParameter(request, "name");
		// 搜索关键字
		String searchAddress = Utils.getParameter(request, "searchAddress");
		// 公司详细的地址
		String address = Utils.getParameter(request, "address");
		// 公司联系人
		String contactPerson = Utils.getParameter(request, "contactPerson");
		// 联系人手机
		String contactPhone = Utils.getParameter(request, "contactPhone");
		// 联系人座机
		String contactTelephone = Utils.getParameter(request, "contactTelephone");
		// 己方负责人
		Long principal = Utils.getLong(request, "principal");
		// 备注
		String desc = Utils.getParameter(request, "desc");
		// 地区id
		String area = Utils.getParameter(request, "area");
		
		if(Utils.isEmpty(name)){
			return new Return(1, "公司名称不能为空！");
		}
		
		if(Utils.isEmpty(searchAddress)){
			return new Return(1, "搜索关键字不能为空！");
		}
		
		if(Utils.isEmpty(area)){
			return new Return(1, "公司所在省没有选择！");
		}else if(area.endsWith("000")){
			return new Return(1, "公司所在市没有选择！");
		}else if(area.endsWith("00")){
			return new Return(1, "公司所在县市区没有选择！");
		}
				
		if(Utils.isEmpty(address)){
			return new Return(1, "公司详细地址不能为空！");
		}
		if(Utils.isEmpty(contactPerson)){
			return new Return(1, "公司联系人不能为空！");
		}
		
		if(Utils.isEmpty(contactPhone) && Utils.isEmpty(contactTelephone)){
			return new Return(1, "联系人手机和联系人座机必须填一个！");
		}
		contactPhone = contactPhone==null?"":contactPhone;
		if(!contactPhone.matches("[\\d|\\s|\\-]+")){
			return new Return(1, "联系人手机包含非法字符！");
		}
		if(contactPhone.length()!=11){
			return new Return(1, "联系人手机位数不正确！");
		}
		contactTelephone = contactTelephone==null?"":contactTelephone;
		if(!contactTelephone.matches("[\\d|\\s|\\-]+")){
			return new Return(1, "联系人座机包含非法字符！");
		}
		if(contactTelephone.length()<7){
			return new Return(1, "联系人座机位数不正确！");
		}
		
		if(principal==null || principal<=0){
			return new Return(1, "己方负责人不能为空！");
		}
		
		String companyId = request.getParameter("companyId");
		Company company = null;
		if(Utils.notEmpty(companyId)){
			company = companyService.getCompany(companyId);
			company.setModifyTime(new Date());
			company.setModifyUser(Utils.getLoginUser(request).getUserId());
		}else{
			company = new Company();
			company.setCompanyId(companyService.getNextId());
			company.setCreateTime(new Date());
			company.setCreateUser(Utils.getLoginUser(request).getUserId());
		}
		
		company.setName(name);
		company.setContactPerson(contactPerson);
		company.setContactPhone(contactPhone);
		company.setContactTelephone(contactTelephone);
		company.setPrincipal(principal);
		company.setArea(area);
		// 公司地址
		company.setAddress(address);
		
//		Map<String, String> areaData = Constants.getInstance().getAreaDataMap();
//		String province = areaData.get(area.substring(0, 3)+"000"); // 省
//		String city = areaData.get(area.substring(0, 4)+"00"); // 市
//		if("县".equals(city) || "市辖区".equals(city))
//			city="";
//		String county = areaData.get(area); // 县（县级市）
		// 公司搜索地址
//		String searchAddress = province+city+county+address;
		
		searchAddress = searchAddress.replaceAll("[\\s+，+]", ",");
		if(!searchAddress.startsWith(","))
			searchAddress = ","+searchAddress;
		if(!searchAddress.endsWith(","))
			searchAddress = searchAddress+",";
		company.setSearchAddress(searchAddress);
		
		if(Utils.notEmpty(desc))
			company.setDesc(desc);
		
		companyService.saveCompany(company);
		return new Return(0, String.valueOf(company.getCompanyId()));
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request){
		Utils.removeLoginUser(request);
		return "redirect:/";
	}

	@Resource
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Resource
	private CompanyService companyService;
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	
//	private TestService testService;
//	@Resource // JSR-250标准注解，推荐使用它来代替Spring专有的@Autowired注解
//	public void setTestService(TestService testService) {
//		this.testService = testService;
//	}
//	
//	private TestaDao testaDao;
//	@Resource
//	public void setTestaDao(TestaDao testaDao) {
//		this.testaDao = testaDao;
//	}
//	private TestDao testDao;
//	@Resource
//	public void setTestDao(TestDao testDao) {
//		this.testDao = testDao;
//	}
//
//	@RequestMapping("add")
//	@ResponseBody
//	public String addTest(){
//		List<Testa> lista = testaDao.find("from Testa");
//		for (Testa testa : lista) {
//			System.out.println("testa :"+testa.getContent());
//		}
//		
//		List<Test> list = testDao.find("from Test");
//		for (Test test : list) {
//			System.out.println("test :"+test.getContent());
//		}
//		
//		Test test = new Test();
//		test.setContent("测试保存");
//		testService.addTest(test);
////		return "<a href='http://localhost:8080/springWebDemo/test/"+test.getId()+".do' target=_blank>查看</a>";
//		return fillHtml("<a href='http://localhost:8080/springWebDemo/test/"+test.getId()+".do' target=_blank>查看</a>");
//	}
//
//	@RequestMapping("list")
//	public String getList(Model model){
//		List<Test> list = testService.getTests();
//		if(list != null){
//			for (Test test : list) {
//				System.out.println("id "+test.getId()+"'s content: "+test.getContent());
//			}
//		}
//		return "index";
//	}
//	
//	@RequestMapping(value="{id}", method=RequestMethod.GET, produces="application/json")
//	@ResponseBody
//	public Test getTest(@PathVariable String id){
//		String str = "id "+id+"'s content: "+testService.getTest(id).getContent();
//		System.out.println(str);
//		return testService.getTest(String.valueOf(id));
//	}
//	
//    public String main(Model model) {
//        model.addAttribute( "date" ,new Date());
//        System.out.println("xx "+new Date());
//        return "index";
//    }
//    
//    public static String fillHtml(String content){
//    	StringBuilder sb = new StringBuilder("<!DOCTYPE html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head><body>");
//    	sb.append(content).append("</body></html>");
//    	return sb.toString();
//    }
	
	public static void main(String[] args) {
		System.out.println(new Date(1326532719000L));
		System.out.println("123123-1 a3114".matches("[\\d|\\s|\\-]+"));
		System.out.println("wefw fwef，,范围发改委".replaceAll("[\\s+，+]", ","));
	}
	
}
