package org.yahve.chat.message;

import lombok.Data;
import lombok.ToString;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/13
 */
@Data
@ToString(callSuper = true)
public class LoginRequestMessage extends Message {
    private String userName;
    private String password;

    public LoginRequestMessage() {
    }

    public LoginRequestMessage(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }


    @Override
    public int getMessageType() {
        return LoginRequestMessage;
    }
}
