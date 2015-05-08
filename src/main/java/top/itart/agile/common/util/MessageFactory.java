package top.itart.agile.common.util;

import top.itart.agile.common.bean.Message;
import top.itart.agile.common.bean.MessageImp;

public class MessageFactory {
    
    public static Message getMessage() {
        return new MessageImp();
    }

}
