package cn.internship.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.internship.service.AdminService;
import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;




import cn.internship.entity.Student;
import cn.internship.entity.Teacher;
import cn.internship.service.StudentService;
import cn.internship.service.TeacherService;
import cn.internship.utils.UserInfo;

/**
 * 登陆页面的相关请求与相应
 * @author dreamlate
 */
public class LoginAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	private HttpServletRequest request;
	private HttpServletResponse response;

	private StudentService studentService;
	private TeacherService teacherService;
	private AdminService adminService;
	
    // 用户类型
    private int userType;
	// 用户名
	private String username;
	// 密码
	private String password;
	// 验证码
	private String verifyCode;
	

	@Override
	public String execute() throws Exception {
		HttpSession session = request.getSession();
//		// 判断session中是否已经存在用户
//		if (session.getAttribute("currentUser") != null) {
//			return SUCCESS;
//		}
		
		// 清除登陆验证时提示的错误信息
		this.clearErrorsAndMessages();
		// 验证码错误
		String verifyCode2 = (String) session.getAttribute("verifyCode");
		if (verifyCode == null || "".equals(verifyCode) || !verifyCode.equalsIgnoreCase(verifyCode2)) {
			this.addActionError("输入的验证码不正确，请重新输入！");
			return INPUT;
		}

		// 用户名或者密码为空
		if (username == null || "".equals(username) || password == null || "".equals(password)) {
			this.addActionError("用户名或密码为空！");
			return INPUT;
		}

		// 判断登陆类型(0：管理员；1：老师；2：企业；3：学生)
		if (userType == 2) {
			//用户类型为老师，验证用户是否存在
			Teacher teacher = teacherService.login(username, password);
			if(teacher == null){
				this.addActionError("用户名或密码不正确！");
				return INPUT;
			}
			//将登陆用户存到session中
			session.setAttribute("currentUser", teacher);
			session.setAttribute("userType", userType);
//            session.setAttribute("currentType", teacher.getUserType());
			return "success";
		}
		else if (userType == 3) {
			//用户类型为学生，验证用户是否存在
			Student student = studentService.login(username, password);
			if(student == null){
				this.addActionError("用户名或密码不正确！");
				return INPUT;
			}
			//将登陆用户存到session中
			session.setAttribute("currentUser", student);
			session.setAttribute("userType", userType);
//            session.setAttribute("currentType", student.getUserType());
			return "success";
		}
		else {
			this.addActionError("登录类型不对");
            return INPUT;
		}
	}

	
	//注销
	public String logout(){
		request.getSession().removeAttribute("currentUser");
//        request.getSession().removeAttribute("currentType");
		int uType = (int) request.getSession().getAttribute("userType");
		if(uType==1){
			adminService.logout();
		}
		if(uType==2){
			teacherService.logout();
		} else if(uType == 3){
			studentService.logout();
		}
		return "logout";
	}
	
	// ----------------------------get与set方法--------------------------------------

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}


	public TeacherService getTeacherService() {
		return teacherService;
	}


	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
}