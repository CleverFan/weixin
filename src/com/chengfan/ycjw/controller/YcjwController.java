package com.chengfan.ycjw.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.chengfan.ycjw.po.Student;
import com.chengfan.ycjw.po.StudentTable;
import com.chengfan.ycjw.service.StudentService;
import com.chengfan.ycjw.util.YcjwUtil;

/**
 * @author ChengFan
 *
 */
@Controller
public class YcjwController {
	@Autowired
	private StudentService service;
	
	//默认查询课表学期
	private final String defaultXueqiForClassTable = "2016/2017(1)";
	//默认查询成绩学期
	private final String defaultXueqiForScore = "2015/2016(2)";

	/**
	 * 进行原创绑定
	 * @param request
	 * @return
	 */
	@RequestMapping("/bangding")
	public ModelAndView BangDing(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		// 从数据库中查找这个人
		String FromUserName = request.getParameter("FromUserName");// 请求的链接必须加上FromUserName参数
		Student s = null;
		if(FromUserName != null){
			s = service.findStudentByWeixinname(FromUserName);
		}else{
			modelAndView.addObject("feifa", "请通过微信进行绑定，别瞎搞");
			modelAndView.setViewName("bangding");
			return modelAndView;
		}
		if (FromUserName == null || s == null) {// 如果没有 返回 绑定界面
			System.out.println("没这个人");
			modelAndView.addObject("FromUserName", FromUserName);
			modelAndView.setViewName("bangding");
			return modelAndView;
		} else {
			modelAndView.addObject("FromUserName", FromUserName);
			modelAndView.setViewName("allready");
			return modelAndView;
		}
	}

	/**
	 * 原创绑定界面提交
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public ModelAndView submit(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String FromUserName = request.getParameter("FromUserName");
		String studentID = request.getParameter("studentID");
		String ycPassword = request.getParameter("ycPassword");
		// 先进行模拟登陆 如果成功 保存数据到数据库 失败则提示
		if (YcjwUtil.isRightInfo(studentID, ycPassword)) {
			// 保存到数据库
			Student student = new Student();
			student.setStudentid(studentID);
			if(FromUserName == null){
				student.setUserweinxinname(" ");
			}else{
				student.setUserweinxinname(FromUserName);
			}
			student.setYcpassword(ycPassword);
			service.insertStudent(student);
			modelAndView.addObject("BangdingOK", "绑定成功");
			modelAndView.addObject("FromUserName", FromUserName);
			modelAndView.setViewName("bangding");
			return modelAndView;
		} else {
			modelAndView.addObject("infoError", "输入的学号或密码错误");
			modelAndView.addObject("FromUserName", FromUserName);
			modelAndView.setViewName("bangding");
			return modelAndView;
		}
	}

	/**
	 * 已经进行绑定的用户查询课表   
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryTable", method = RequestMethod.GET)
	public ModelAndView queryTableByUsername(HttpServletRequest request) {
		// 测试forward后request是否可以共享 FromUserName为url参数
		ModelAndView modelAndView = new ModelAndView();

		String FromUserName = request.getParameter("FromUserName");// 请求的链接必须加上FromUserName参数
		String Cbo_Xueqi = request.getParameter("Cbo_Xueqi");

		// 调用service根据FromUserName在数据库中查找学生id和密码
		Student s = null;
		if(FromUserName != null){
			s = service.findStudentByWeixinname(FromUserName);
		}
		if(FromUserName == null || s == null){
			//该用户没有绑定   返回输入学号密码界面
			System.out.println("no bangding");
			modelAndView.addObject("FromUserName", FromUserName);
			modelAndView.setViewName("queryTableIndex");
			return modelAndView;
		}
		String Txt_UserName = s.getStudentid();
		String Txt_Password = s.getYcpassword();
		//默认按课表查询
		if(Cbo_Xueqi == null){
			Cbo_Xueqi=defaultXueqiForClassTable;//设置默认查询学期
		}
		List<String> tableList = YcjwUtil.queryTable(Txt_UserName, Txt_Password,"按课表查询",Cbo_Xueqi);
		String html = (String) tableList.get(0);
		// 返回ModelAndView
		modelAndView.addObject("classTable", html);
		modelAndView.addObject("FromUserName", FromUserName);

		// 指定视图缀
		modelAndView.setViewName("classTable");

		return modelAndView;
	}
	
	/**
	 * 未绑定的用户通过输入id和密码查询课表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryTable", method = RequestMethod.POST)
	public ModelAndView queryTable(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String FromUserName = request.getParameter("FromUserName");// 请求的链接必须加上FromUserName参数

		String Txt_UserName = request.getParameter("studentID");
		String Txt_Password = request.getParameter("ycPassword");
		String Cbo_Xueqi = request.getParameter("Cbo_Xueqi");
		String queryMethod = request.getParameter("queryMethod");
		// 先进行模拟登陆 如果成功 保存数据到数据库 失败则提示
		if (YcjwUtil.isRightInfo(Txt_UserName, Txt_Password)) {
			// 学号密码正确
			if(queryMethod.equals("按课程查询")){
				List<StudentTable> tableList = YcjwUtil.queryTableList(Txt_UserName, Txt_Password,queryMethod,Cbo_Xueqi);
				// 返回ModelAndView
				modelAndView.addObject("tableList", tableList);
				modelAndView.addObject("FromUserName", FromUserName);
				// 指定视图
				modelAndView.setViewName("classTableList");			
				return modelAndView;
			}else{
				List<String> tableList = YcjwUtil.queryTable(Txt_UserName, Txt_Password,queryMethod,Cbo_Xueqi);
				String html = (String) tableList.get(0);
				// 返回ModelAndView
				modelAndView.addObject("classTable", html);
				modelAndView.addObject("FromUserName", FromUserName);
				// 指定视图
				modelAndView.setViewName("classTable");
				return modelAndView;
			}
						
		} else {
			modelAndView.setViewName("queryTableIndex");
			modelAndView.addObject("infoError", "输入的学号或密码错误");
			modelAndView.addObject("FromUserName", FromUserName);
			return modelAndView;
		}
	}
	
	/**
	 * 已经绑定的用户查询成绩
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryScore", method = RequestMethod.GET)
	public ModelAndView queryScoreIndex(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		
		String FromUserName = request.getParameter("FromUserName");
		String Cbo_Xueqi = request.getParameter("Cbo_Xueqi");
		System.out.println(FromUserName);

		// 调用service根据FromUserName在数据库中查找学生id和密码
		Student s = null;
		if(FromUserName != null){
			s = service.findStudentByWeixinname(FromUserName);
		}
		if(FromUserName == null || s == null){
			//该用户没有绑定   返回输入学号密码界面
			System.out.println("no bangding");
			modelAndView.addObject("FromUserName", FromUserName);
			modelAndView.setViewName("score/queryScoreIndex");
			return modelAndView;
		}
		String Txt_UserName = s.getStudentid();
		String Txt_Password = s.getYcpassword();
		//默认按课表查询
		if(Cbo_Xueqi == null){
			Cbo_Xueqi = defaultXueqiForScore;//设置默认查询学期
		}
		//YcjwUtil.queryScoreUtil(Txt_UserName, Txt_Password,"＝所有学期＝");
		String scoreHtml = YcjwUtil.queryScoreUtil(Txt_UserName, Txt_Password,Cbo_Xueqi);
		// 返回ModelAndView
		modelAndView.addObject("queryScore", scoreHtml);
		modelAndView.addObject("FromUserName", FromUserName);

		// 指定视图缀
		modelAndView.setViewName("score/queryScore");
		return modelAndView;
	}
	
	/**
	 * 未绑定的用户通过输入id和密码查询成绩
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryScore", method = RequestMethod.POST)
	public ModelAndView queryScore(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String FromUserName = request.getParameter("FromUserName");
		String Txt_UserName = request.getParameter("studentID");
		String Txt_Password = request.getParameter("ycPassword");
		String Cbo_Xueqi = request.getParameter("Cbo_Xueqi");
		// 先进行模拟登陆 如果成功 保存数据到数据库 失败则提示
		if (YcjwUtil.isRightInfo(Txt_UserName, Txt_Password)) {
			// 学号密码正确
			String scoreHtml = YcjwUtil.queryScoreUtil(Txt_UserName, Txt_Password, Cbo_Xueqi);
			// 返回ModelAndView
			modelAndView.addObject("queryScore", scoreHtml);
			modelAndView.addObject("FromUserName", FromUserName);
			// 指定视图
			modelAndView.setViewName("score/queryScore");
			return modelAndView;
			
						
		} else {
			modelAndView.setViewName("score/queryScoreIndex");
			modelAndView.addObject("FromUserName", FromUserName);
			modelAndView.addObject("infoError", "输入的学号或密码错误");
			return modelAndView;
		}
	}

	@RequestMapping(value = "/checkError", method = RequestMethod.GET)
	public ModelAndView checkError(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String FromUserName = request.getParameter("FromUserName");
		modelAndView.addObject("FromUserName", FromUserName);
		System.out.println(FromUserName);
		modelAndView.setViewName("checkError/checkError");
		return modelAndView;
	}
	
	@RequestMapping(value = "/checkError", method = RequestMethod.POST)
	public ModelAndView checkErrorInfo(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String FromUserName = request.getParameter("FromUserName");
		
		String studentName = request.getParameter("studentName");
		String studentid = request.getParameter("studentid");
		String studentPhone = request.getParameter("studentPhone");
		String content = request.getParameter("content");
		
		if(studentName.equals("") || studentid.equals("") || studentPhone.equals("") || content.equals("")){
			modelAndView.addObject("jieshouFalse", "请输入完整的信息");
			modelAndView.addObject("FromUserName", FromUserName);
			modelAndView.setViewName("checkError/checkError");
			return modelAndView;
		}
		modelAndView.addObject("jieshouOK", "我们已经收到你的信息，感谢你的反馈，我们会做得更好~");
		modelAndView.addObject("FromUserName", FromUserName);
		modelAndView.setViewName("checkError/checkError");
		return modelAndView;
	}
	@RequestMapping(value = "/jiebang", method = RequestMethod.GET)
	public ModelAndView jiebang(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String FromUserName = request.getParameter("FromUserName");
		service.deleteStudentById(FromUserName);
		modelAndView.addObject("okInfo", "成功解绑");
		modelAndView.addObject("FromUserName", FromUserName);
		modelAndView.setViewName("allready");
		return modelAndView;
	}
	//queryZhf
	@RequestMapping(value = "/queryZhf", method = RequestMethod.GET)
	public ModelAndView queryZhf(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String FromUserName = request.getParameter("FromUserName");
		//service.deleteStudentById(FromUserName);
		modelAndView.addObject("info", "不好意思，暂时还没有你的综合分信息");
		modelAndView.addObject("FromUserName", FromUserName);
		modelAndView.setViewName("queryZhf/queryZhf");
		return modelAndView;
	}

}
