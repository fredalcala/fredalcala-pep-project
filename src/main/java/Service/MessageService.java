package Service;

import Model.Message;
import DAO.MessageDAO;
import DAO.AccountDAO;
import Model.Account;

public class MessageService {
    MessageDAO messageDAO;
   // AccountDAO accountDAO;

    public MessageService(){
        //accountDAO = new AccountDAO();
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
       // this.accountDAO = accountDAO;
    }

    public Message addMessage(Message message){
        if(message.getMessage_text().length() >= 255 || message.getMessage_text().length() == 0){
            return null;
        } else {
            return messageDAO.newMessage(message);
        }
    }

    
}
