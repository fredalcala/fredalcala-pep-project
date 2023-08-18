package Service;

import Model.Account;
import DAO.AccountDAO;
import java.util.*;

public class AccountService {
    AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    public Account addAccount(Account account){
        if (account.getUsername().length() == 0 || account.getPassword().length() < 4){
            return null;
        } else {
            return accountDAO.newAccount(account);
        }
    }

    public Account getAccount(Account account){
        if (accountDAO.accountLogin(account) == null){
            return null;
        } else {
            return accountDAO.accountLogin(account);
        }
    }


    
}
