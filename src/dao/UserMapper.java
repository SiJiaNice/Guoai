package dao;

import pojo.User;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface UserMapper {

	//����id��ѯ
    public User getUserById(@Param(value = "id") Integer id)throws Exception;

    //��ѯ����
    public List<User> getUserListByMap(Map<String,Object> param)throws Exception;

    //ģ����ѯ
    public Integer getUserRecordCount(Map<String,Object> param)throws Exception;
    
    //�����Ʒ
    public Integer insertUser(User user)throws Exception;
    
}