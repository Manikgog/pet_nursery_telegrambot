package pet.nursery.telegrambot.service.manager;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactsManager {
    public void answerMessage(Update update, TelegramBot bot){
        StringBuilder text = new StringBuilder();
        List<String> listOfContacts = List.of("Иван Михайлович Петров - 8-922-922-92-92",
                "Ирина Романовна Хомякова - 8-923-923-93-93",
                "Мария Дмитриевна Терезова - 8-924-924-94-94");
        for(String item : listOfContacts){
            text.append(item).append('\n');
        }
        String botAnswer = text.toString();
        SendMessage sendMessage = new SendMessage(update.message().chat().id(), botAnswer);
        bot.execute(sendMessage);
    }
}
