package Service;

import Model.Message;
import DAO.MessageDAO;
import DAO.AccountDAO;
import Model.Account;

public class MessageService {
    MessageDAO messageDAO;
    AccountDAO accountDAO;

    public MessageService(){
        accountDAO = new AccountDAO();
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO, AccountDAO accountDAO){
        this.messageDAO = messageDAO;
        this.accountDAO = accountDAO;
    }

    public Message addMessage(Message message){
        Account account = accountDAO.getAccount_id(message.getPosted_by());
        if(message.getMessage_text().length() >= 255 || message.getMessage_text().length() == 0
        || account == null){
            return null;
        } else {
            return messageDAO.newMessage(message);
        }
    }

    
}
