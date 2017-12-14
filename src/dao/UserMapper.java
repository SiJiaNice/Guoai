package dao;

import pojo.User;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface UserMapper {

	//根据id查询
    public User getUserById(@Param(value = "id") Integer id)throws Exception;

    //查询所有
    public List<User> getUserListByMap(Map<String,Object> param)throws Exception;

    //模糊查询
    public Integer getUserRecordCount(Map<String,Object> param)throws Exception;
    
    //添加商品
    public Integer insertUser(User user)throws Exception;
    
}