package com.example.penzi_project20;

class Post_Incoming_Message_Response {

    private String delivery_time;
    private Integer id;
    private String message;
    private String sender_number;
    private Integer shortcode;
    private String status;


    public String getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(String delivery_time) {
        this.delivery_time = delivery_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender_number() {
        return sender_number;
    }

    public void setSender_number(String sender_number) {
        this.sender_number = sender_number;
    }

    public Integer getShortcode() {
        return shortcode;
    }

    public void setShortcode(Integer shortcode) {
        this.shortcode = shortcode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Post_Incoming_Message_Response{" +
                "delivery_time='" + delivery_time + '\'' +
                ", id=" + id +
                ", message='" + message + '\'' +
                ", sender_number='" + sender_number + '\'' +
                ", shortcode=" + shortcode +
                ", status='" + status + '\'' +
                '}';
    }
}
