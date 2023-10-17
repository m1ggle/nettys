package org.yahve.rpc.service.impl;

import org.yahve.rpc.service.RpcHelloService;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/17
 */
public class RpcHelloServiceImpl implements RpcHelloService {

    @Override
    public String rpcSendMsg(String msg) {
        return "hello " + msg;
    }
}
