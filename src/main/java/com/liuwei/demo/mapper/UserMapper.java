package com.liuwei.demo.mapper;

import com.liuwei.demo.bean.User;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    /**
     * 返回一般数据类型
     * @param userId
     * @return
     */
    String getUserName(Long userId);

    /**
     * 返回 JavaBean 类型
     * @param userId
     * @return
     */
    User getUserByUserId(Long userId);

    /**
     * 返回List类型
     * @return
     */
    List<User> getUserList();

    /**
     * 返回Map类型, 但结果只有一个
     * @return
     */
    @MapKey("userId")
    Map<Long, User> getUserAsMap(Long userId);

    /**
     * 返回Map类型, 但结果有多个
     * @return
     */
    @MapKey("userId")
    Map<Long, User> getUserListAsMap();

    /**
     *
     * @param user
     */
    void addUser(User user);

}
