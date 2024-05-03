package pet.nursery.telegrambot.service.manager;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

@Service
public class StartManager {
    public void answerMessage(Update update, TelegramBot bot){
        String text = """
                        Здравствуйте! %s %s
                        Телеграм бот для приюта домашних животных.
                        /nursery - для получения информации о приюте.
                        /info - для получения информации о том как взять животное из приюта.
                        /report - для получения информации о том как послать отчет о питомце.
                        /contacts - для получения контактов для связи с волонтерами.
                        /admin - получение доступа к административным командам.
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
