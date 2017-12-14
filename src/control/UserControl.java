package control;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import pojo.User;
import service.UserService;

import com.github.pagehelper.Page;

@Controller
@RequestMapping("/user") 
public class UserControl {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/dologin.html")
	public ModelAndView userLogin(@Valid User loginuser, BindingResult result){
		
		ModelAndView mv = new ModelAndView();
		
		if(result.hasErrors() == true){  
			mv.setViewName("login");  
			return mv;
		}
		
		User user = userService.login(loginuser.getUserName(), 
									  loginuser.getPassword());
		
		if(user != null){
			mv.setViewName("frame");
		}else{
			mv.addObject("error", "用户名或密码错误 ");
			mv.setViewName("login");
		}
		return mv;
	}

	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView userRegist(@Valid User reguser,  BindingResult result,
			MultipartFile idPicPath,
			HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView();
			String path = request.getServletContext().getRealPath("/upload");
		
			String fileName = idPicPath.getOriginalFilename(); // ԭʼ�ļ���
			String ext = FilenameUtils.getExtension(fileName);
			fileName = UUID.randomUUID().toString() + "." + ext;
			
			File file = 
					new File(path + "/" + fileName);
			idPicPath.transferTo(file); 
			
			
			int count = userService.insertUser(reguser);
			if(count > 0) {
				mv.setViewName("redirect:/user");
			}else{
				mv.setViewName("useradd");
			}
		//}
		return mv;
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView userList(Integer pageIndex,String queryname,
			Integer queryUserRole) throws Exception{
		Map<String,Object> param = new HashMap<String,Object>();
		int pageno = 1;
		if(pageIndex != null){	// 第一次加载Control时，pageIndex=null
			pageno = pageIndex.intValue();
		}
		//分页参数
		param.put("start", pageno);	 
		param.put("size",5);
		//查询条件
		param.put("userName", queryname); // 查询条件key，和Mapper中查询条件对应
		if(queryUserRole != null && queryUserRole != 0){
			param.put("userRole", queryUserRole);
		}
		
		// 查询分页结果Page对象
		Page<User> userList = (Page<User>) userService.getUserListByMap(param);
		// 查询结果打包，装入Map集合(Model)
		HashMap<String, Object> model =new HashMap<String, Object>();
		
		// 总记录数
		model.put("totalCount",userList.getTotal());
		// 总页数
		model.put("totalPageCount", userList.getPages());
		// 当前页码
		model.put("currentPageNo", pageno);
		// 查询结果
		model.put("userList", userList);
		// 查询条件
		model.put("queryUserName", queryname);
		model.put("queryUserRole", queryUserRole);

		
		// 通过ModelAndView实现跳转及数据传递
		return new ModelAndView("userlist", model);
	}
	
}
