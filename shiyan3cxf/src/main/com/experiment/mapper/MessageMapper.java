package com.experiment.mapper;

import com.experiment.pojo.message;

import java.util.List;

public interface MessageMapper {

    public void sendMessage(message message);

    public void deleteMessage(Integer id);

    public List<message> getallMessage();

    public List<message> getsendermessagebyid(Integer id);

    public List<message> getrecievermessagebyid(Integer id);

}
