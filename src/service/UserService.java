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
	
	//����id��ѯ
    public User getUserById(Integer id)throws Exception;
    
    //�û���¼
    public User login(String userCode, String userPassword);

    //��ѯ����
    public List<User> getUserListByMap(Map<String,Object> param)throws Exception;
    
	public Integer getUserRecCountByMap(Map<String,Object> param)throws Exception;

    //���
    public Integer insertUser(User user)throws Exception;
    
}