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
    
    //��¼
	@Override
	public User login(String userName, String password) {
		try {
 			// ��DB�в�ѯָ��name�û���¼�Ƿ����
 			HashMap<String, Object> param = new HashMap<String, Object>();
 			param.put("userName", userName);
 			List<User> users = usermapper.getUserListByMap(param);
 			if(users.size() > 0){
 				User user = users.get(0);
 				// ����û����ڣ���Ϊ����name������¼�Ǵ��ڣ���һ����֤����
 				if(user != null && user.getPassword().equals(password)){
 					return user;
 				}
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 			throw new RuntimeException("�û���¼���ݲ�ѯ���������⣡" + e.getMessage());
 		}	
 		return null;
	}

	// �û��б�
	@Transactional(readOnly = true)
	public List<User> getUserListByMap(Map<String, Object> param) throws Exception {
		return usermapper.getUserListByMap(param);
	}

	@Transactional(readOnly = true)
	public Integer getUserRecCountByMap(Map<String, Object> param) throws Exception {
		return usermapper.getUserRecordCount(param);
	}

	//����
	@Transactional(readOnly = true)
	public Integer insertUser(User user) throws Exception {
		return usermapper.insertUser(user);
	}

}
