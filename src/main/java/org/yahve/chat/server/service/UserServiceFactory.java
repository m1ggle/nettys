package org.yahve.chat.server.service;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/13
 */
public class UserServiceFactory {
    private static UserService userService = new UserServiceMemoryImpl();

    public static UserService getUserService(){
        return userService;
    }
}
