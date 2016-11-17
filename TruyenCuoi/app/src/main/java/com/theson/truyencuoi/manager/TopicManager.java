package com.theson.truyencuoi.manager;

import com.theson.truyencuoi.model.Topic;
import com.theson.truyencuoi.model.Variables;

import java.util.ArrayList;

public class TopicManager {

    private static TopicManager instance;

    public static TopicManager getInstance(){
        if(instance == null){
            instance = new TopicManager();
        }
        return  instance;
    }

    private ArrayList<Topic> arrTopic;

    private TopicManager(){
        arrTopic = new ArrayList<>();
       for (int i = 0; i< Variables.TOPICS.length; i++){
           arrTopic.add(new Topic(Variables.ICONS_TOPIC[i], Variables.TOPICS[i]));
       }
    }

    public int getCount(){
        return arrTopic.size();
    }

    public Topic getTopic(int position){
        return arrTopic.get(position);
    }


}
