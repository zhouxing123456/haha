package com.study.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.dao.IUserDao;
import com.study.pojo.User;



@Service("userService")
public class UserServiceImpl implements IUserService {
	
	/*log4j的配置*/
    public static Logger logger = Logger.getLogger(UserServiceImpl.class);
    
    @Autowired
    private IUserDao userMpper;//usermapper接口
    
    
    @Transactional/*添加*/
    public User addUser(User user) {
        userMpper.addUser(user);
        logger.info("向数据库添加用户");
        return user;
    }
    /*
     * 	private String id;
		private String name;
     * 
     * */
    /*1.查询数据库
     * 2.数据的拼装
     * */
    
    @Transactional/*删除*/
    @CacheEvict(cacheNames="default", key="#userId" ,allEntries=false)
    
    public void deleteUser(String userId) {
        userMpper.deleteUser(userId);
    }
    @Transactional/*更新*/
    @CachePut(cacheNames="default", key="#user.id")
    public User updateUser(User user) {
        userMpper.updateUser(user);
        return user;
    }
    @Transactional(readOnly=true)/*查询*/
    @Cacheable(cacheNames="default", key="#userId+':info'" ,unless="#result==null")
    public User queryUser(int userId) {
        User user = userMpper.queryUser(String.valueOf(userId));
        return user;
    }
   
}
