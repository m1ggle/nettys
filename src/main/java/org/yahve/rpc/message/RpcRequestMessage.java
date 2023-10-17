package org.yahve.rpc.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.yahve.chat.message.Message;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/17
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class RpcRequestMessage extends Message {

    private int sequenceId;
    // 方法的全限定名称
    private String interfaceName;
    // 调用接口的方法名
    private String methodName;
    // 方法的返回值
    private Class<?> returnType;
    // 方法的参数类型数组
    private Class[] parameterTypes;
    // 方法参数值数组
    private Object[] parameterValue;

    public RpcRequestMessage(int sequenceId, String interfaceName, String methodName, Class<?> returnType, Class[] parameterTypes, Object[] parameterValue) {
        this.sequenceId = sequenceId;
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.returnType = returnType;
        this.parameterTypes = parameterTypes;
        this.parameterValue = parameterValue;
    }

    @Override
    public int getMessageType() {
        return RPC_MESSAGE_TYPE_REQUEST;
    }
}
