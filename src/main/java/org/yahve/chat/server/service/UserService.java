package org.yahve.chat.server.service;

/**
 * @author m1ggle
 * @project nettys
 * @describe 用户登录接口
 * @date 2023/10/13
 */
public interface UserService {
    /**
     * 用户登录接口
     * @param username
     * @param password
     * @return
     */
    boolean login(String username, String password);
}
