package com.example.penzi_project20;

class Post_Incoming_Message_Request {

    private String sender_number;
    private String message;
    private Integer shortcode;

    public String getSender_number() {
        return sender_number;
    }

    public void setSender_number(String sender_number) {
        this.sender_number = sender_number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getShortcode() {
        return shortcode;
    }

    public void setShortcode(Integer shortcode) {
        this.shortcode = shortcode;
    }




}
