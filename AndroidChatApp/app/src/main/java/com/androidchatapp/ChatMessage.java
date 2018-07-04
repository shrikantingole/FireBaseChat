package com.androidchatapp;

import java.util.Date;

public class ChatMessage {

    private String mgsText;
    private String msgUserName;
    private long msgTime;
    private int msgUserType;
    private String msgId;

    public String getMgsText() {
        return mgsText;
    }

    public String getMsgUserName() {
        return msgUserName;
    }

    public long getMsgTime() {
        return msgTime;
    }

    public int getMsgUserType() {
        return msgUserType;
    }

    public String getMsgId() {
        return msgId;
    }

    public ChatMessage(String mgsText, String msgUserName, int msgUserType) {
        this.mgsText = mgsText;
        this.msgUserName = msgUserName;
        msgTime = new Date().getTime();
        this.msgUserType=msgUserType;
    }

}