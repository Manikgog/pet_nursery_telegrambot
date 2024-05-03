package pet.nursery.telegrambot.service.manager;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
@Service
public class NurseryManager {
    public void answerMessage(Update update, TelegramBot bot){
        String botAnswer = "Адрес приюта: г. Екатеринбург ул. Калинина д.24\nТелефон: 8-3434-456-789\n";
        SendMessage sendMessage = new SendMessage(update.message().chat().id(), botAnswer);
        bot.execute(sendMessage);
    }
}
