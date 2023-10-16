package org.yahve.chat.message;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/16
 */
public class PingMessage extends Message {

    @Override
    public int getMessageType() {
        return PingMessage;
    }
}
