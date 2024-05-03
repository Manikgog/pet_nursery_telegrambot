package pet.nursery.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.nursery.telegrambot.service.handler.MessageHandler;

@Service
public class UpdateDispatcher {
    private final MessageHandler messageHandler;

    @Autowired
    TelegramBot bot;

    @Autowired
    public UpdateDispatcher(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    public void distribute(Update update) {
        if(update.message() != null && update.message().text() != null) {
            messageHandler.answer(update, bot);
        }
    }
}
