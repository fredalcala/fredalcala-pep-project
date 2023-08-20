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

    public Message editMessage(int message_id, Message message){
        if (messageDAO.getMessageById(message_id) == null || message.getMessage_text().length() >= 255 || message.getMessage_text().length() == 0){
            return null;
        } else {
            messageDAO.editMessage(message_id, message);
            return messageDAO.getMessageById(message_id);
        }
    }

    
}
