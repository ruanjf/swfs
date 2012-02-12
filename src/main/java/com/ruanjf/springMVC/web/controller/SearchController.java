package com.ruanjf.springMVC.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruanjf.springMVC.commons.Constants;
import com.ruanjf.springMVC.commons.Return;
import com.ruanjf.springMVC.commons.Utils;
import com.ruanjf.springMVC.persistent.Company;
import com.ruanjf.springMVC.persistent.User;
import com.ruanjf.springMVC.services.CompanyService;
import com.ruanjf.springMVC.services.UserService;

@Controller // 用于标注控制层组件（如struts中的action）
@RequestMapping("/s")
public class SearchController extends BaseController {
	
	private CompanyService companyService;
	@Resource
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}
	private UserService userService;
	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	@RequestMapping(produces="application/json")
	@ResponseBody
	public Object verifyExist(@RequestParam String key, HttpServletRequest request){
		User user = Utils.getLoginUser(request);
		if(user==null){
			return "redirect:/";
		}else{
			log.info("用户"+user.getUsername()+"("+user.getUserId()+")搜索关键字："+key);
			List<Company> clist = companyService.getSearchList(user.getUserId(), key);
			if(Utils.notEmpty(clist)){
				StringBuffer ret = new StringBuffer();
				for (Company company : clist) {
					User cuser = userService.getUserById(String.valueOf(company.getPrincipal()));
					if(cuser!=null)
						ret.append(","+cuser.getUsername());
				}
				if(ret.length()>1)
					return new Return(1, Constants.getInstance().getAppInfo().getFailure().replace("{user}", ret.substring(1)));
				else
					return new Return(0, Constants.getInstance().getAppInfo().getSuccess());
				
			}else{
				return new Return(0, Constants.getInstance().getAppInfo().getSuccess());
			}
		}
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
	
}
