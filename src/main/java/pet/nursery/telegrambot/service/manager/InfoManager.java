package pet.nursery.telegrambot.service.manager;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

@Service
public class InfoManager {
    public void answerMessage(Update update, TelegramBot bot){
        String text = """
                        Здравствуйте! %s %s
                        Для получения животного из приюта необходимо написать заявку. 
                        В заявке указать свой телефон, адрес и животное, которое Вы 
                        выбрали.
                        """;
        String firstName = update.message().chat().firstName();
        String lastName = update.message().chat().lastName();
        String botAnswer;
        if(lastName == null){
            botAnswer = String.format(text, firstName, "");
        }else {
            botAnswer = String.format(text, firstName, lastName);
        }
        SendMessage sendMessage = new SendMessage(update.message().chat().id(), botAnswer);
        bot.execute(sendMessage);
    }
}
