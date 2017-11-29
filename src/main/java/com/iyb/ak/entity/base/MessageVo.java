package com.iyb.ak.entity.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：
 * 备注：
 */
public class MessageVo {

    private List<MessageObj> reasons;

    public MessageVo addMessageObj(String message_id, String message, String message_obj){
        MessageObj messageObj = new MessageObj(message_id,message,message_obj);
        getReasons().add(messageObj);
        return this;
    }

    public List<MessageObj> getReasons() {
        if(null == reasons){
            reasons = new ArrayList<MessageObj>();
        }
        return reasons;
    }

    public void setReasons(List<MessageObj> reasons) {
        this.reasons = reasons;
    }
}
