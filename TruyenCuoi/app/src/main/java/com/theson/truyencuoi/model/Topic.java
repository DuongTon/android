package com.theson.truyencuoi.model;

public class Topic {

    private int imageId;
    private String topic;

    public Topic(int imageId, String topic) {
        this.imageId = imageId;
        this.topic = topic;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
