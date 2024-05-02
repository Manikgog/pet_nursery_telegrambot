package pet.nursery.telegrambot.service.handler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageHandler {
    @Autowired
    TelegramBot bot;
    public void answer(Update update) {
        switch (update.message().text()){
            case "/start" -> {
                String text = """
                        Телеграм бот для приюта домашних животных.
                        """;
                String botAnswer = String.format(text);
                SendMessage sendMessage = new SendMessage(update.message().chat().id(), botAnswer);
                bot.execute(sendMessage);
            }
        }
    }
}