package org.yahve.rpc.service;

import java.util.HashMap;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/17
 */
public class RpcServiceFactory {
    static HashMap<Class<?>, Object> map = new HashMap<>(16);

    public static Object getInstance(Class<?> interfaceClass) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        // 根据Class创建实例
        try {
            Class<?> clazz = Class.forName("org.yahve.rpc.service.RpcHelloService");
            Object instance = Class.forName("org.yahve.rpc.service.impl.RpcHelloServiceImpl").newInstance();

            // 放入 InterfaceClass -> InstanceObject 的映射
            map.put(clazz, instance);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return map.get(interfaceClass);
    }
}
