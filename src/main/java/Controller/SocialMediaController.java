package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import Model.Account;
//import DAO.AccountDAO;
import Service.AccountService;
import Model.Message;
//import DAO.MessageDAO;
import Service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService = new AccountService();
    MessageService messageService = new MessageService();

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postAccountHandler);
        app.post("/login", this::accountLoginHandler);
        app.post("/messages", this::postMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMsgByIdHandler);
        app.patch("/messages/{message_id}", this::editMessageHandler);
        app.delete("/messages/{message_id}", this::deleteMessageHandler);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */

    private void postAccountHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account addedAccount = accountService.addAccount(account);
        if(addedAccount == null){
            ctx.status(400);
        } else {
            ctx.json(addedAccount);
            ctx.status(200);
        }
    }

    private void accountLoginHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account foundAccount = accountService.getAccount(account);
        if (foundAccount == null){
            ctx.status(401);
        } else {
            ctx.json(foundAccount);
            ctx.status(200);
        }
    }

    private void postMessageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message newMessage = messageService.addMessage(message);
        if (newMessage == null){
            ctx.status(400);
        } else {
            ctx.json(newMessage);
            ctx.status(200);
        }
    }

    private void getAllMessagesHandler(Context ctx) throws JsonProcessingException{
        ctx.json(messageService.getAllMessages());
    }

    private void getMsgByIdHandler(Context ctx) throws JsonProcessingException{
        int msgId = Integer.parseInt(ctx.pathParam("message_id"));
        Message message = messageService.getMessageById(msgId);
        if (message == null){
            ctx.json("");
            ctx.status(200);
        } else {
        ctx.json(message);
        ctx.status(200);
        }
    }

    private void editMessageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message editedMessage = messageService.editMessage(message_id, message);
        System.out.println(editedMessage);
        if(editedMessage == null){
            ctx.status(400);
        } else {
            ctx.json(mapper.writeValueAsString(editedMessage));
        }
    }

    private void deleteMessageHandler(Context ctx) throws JsonProcessingException {
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message messageToDelete = messageService.deleteMessage(message_id);
        System.out.println(messageToDelete);
        if(messageToDelete == null){
            ctx.json("");
            ctx.status(200);
        } else {
            ctx.json(messageToDelete);
            ctx.status(200);
        }
    }

}