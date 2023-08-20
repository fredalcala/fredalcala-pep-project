package Service;

import Model.Message;
import DAO.MessageDAO;
import DAO.AccountDAO;
import Model.Account;

import java.util.*;

public class MessageService {
    MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public Message addMessage(Message message){
        if(message.getMessage_text().length() >= 255 || message.getMessage_text().length() == 0){
            return null;
        } else {
            return messageDAO.newMessage(message);
        }
    }

    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }

    public Message getMessageById(int id){
        return messageDAO.getMessageById(id);
    }

    
}
