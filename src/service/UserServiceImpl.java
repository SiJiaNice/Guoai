package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.UserMapper;
import pojo.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper usermapper;
	
	
	@Transactional(readOnly = true)
	public User getUserById(Integer id) throws Exception {
		return usermapper.getUserById(id);
    }
    
    //登录
	@Override
	public User login(String userName, String password) {
		try {
 			// 从DB中查询指定name用户记录是否存在
 			HashMap<String, Object> param = new HashMap<String, Object>();
 			param.put("userName", userName);
 			List<User> users = usermapper.getUserListByMap(param);
 			if(users.size() > 0){
 				User user = users.get(0);
 				// 如果用户存在，以为符合name条件记录是存在，进一步验证密码
 				if(user != null && user.getPassword().equals(password)){
 					return user;
 				}
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 			throw new RuntimeException("用户登录数据查询发生了问题！" + e.getMessage());
 		}	
 		return null;
	}

	// 用户列表
	@Transactional(readOnly = true)
	public List<User> getUserListByMap(Map<String, Object> param) throws Exception {
		return usermapper.getUserListByMap(param);
	}

	@Transactional(readOnly = true)
	public Integer getUserRecCountByMap(Map<String, Object> param) throws Exception {
		return usermapper.getUserRecordCount(param);
	}

	//增加
	@Transactional(readOnly = true)
	public Integer insertUser(User user) throws Exception {
		return usermapper.insertUser(user);
	}

}
