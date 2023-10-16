package org.yahve.chat.server.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/13
 */
public class UserServiceMemoryImpl implements UserService {

    private Map<String, String> allUserMap = new ConcurrentHashMap<>();
    {
        allUserMap.put("zhangsan","123");
        allUserMap.put("lisi","123");
        allUserMap.put("wangwu","123");
    }
    @Override
    public boolean login(String username, String password) {
        String passwd = allUserMap.get(username);
        if (passwd == null){
            return false;
        }
        return passwd.equals(password);

    }
}
