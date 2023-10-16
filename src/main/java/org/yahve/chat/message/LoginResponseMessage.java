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
public class LoginResponseMessage extends AbstractResponseMessage {

    public LoginResponseMessage(boolean success, String reason) {
        super(success, reason);
    }

    @Override
    public int getMessageType() {
        return LoginResponseMessage;
    }
}
