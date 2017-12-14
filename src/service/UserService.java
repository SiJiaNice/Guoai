package service;

import pojo.User;

import java.util.List;
import java.util.Map;

/**
* Service Interface User
*
* 2014-12-06
*/
public interface UserService {
	
	//根据id查询
    public User getUserById(Integer id)throws Exception;
    
    //用户登录
    public User login(String userCode, String userPassword);

    //查询所有
    public List<User> getUserListByMap(Map<String,Object> param)throws Exception;
    
	public Integer getUserRecCountByMap(Map<String,Object> param)throws Exception;

    //添加
    public Integer insertUser(User user)throws Exception;
    
}