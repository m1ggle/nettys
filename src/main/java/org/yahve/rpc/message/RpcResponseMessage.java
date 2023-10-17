package org.yahve.rpc.message;

import lombok.Data;
import lombok.ToString;
import org.yahve.chat.message.Message;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/17
 */
@Data
@ToString(callSuper = true)
public class RpcResponseMessage extends Message {

    private Object returnValue;
    private Exception exceptionValue;

    @Override
    public int getMessageType() {
        return RPC_MESSAGE_TYPE_RESPONSE;
    }
}
