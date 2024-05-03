package pet.nursery.telegrambot.service.adminmanager;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class AdminActions {
    public void answerMessage(Update update, TelegramBot bot){
        String text = """
                        Список команд для администрирования бота:
                        /add_animal - добавление животного в список приюта
                        /mark - отметка животного, как отданного на руки
                        /add_volunteer - добавление волонтера в таблицу
                        """;
        SendMessage sendMessage = new SendMessage(update.message().chat().id(), text);
        bot.execute(sendMessage);
    }
}
