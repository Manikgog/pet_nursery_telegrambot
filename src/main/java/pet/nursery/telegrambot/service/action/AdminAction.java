package pet.nursery.telegrambot.service.action;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import pet.nursery.telegrambot.service.adminmanager.AdminActions;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminAction implements Action {
    private final List<Long> adminsIds = new ArrayList<>();
    private final String password = "prijut";
    private final AdminActions adminActions = new AdminActions();

    @Override
    public void handle(Update update, TelegramBot bot) {
        Long chatId = update.message().chat().id();
        // проверить, является ли человек администратором бота по таблице администраторов
        // и если не является, то запросить пароль
        if(adminsIds.contains(chatId)){
            adminActions.answerMessage(update, bot);
            return;
        }
        StringBuilder out = new StringBuilder();
        out.append("Введите пароль");
        SendMessage sendMessage = new SendMessage(chatId, out.toString());
        bot.execute(sendMessage);
    }

    @Override
    public void callback(Update update, TelegramBot bot) {
        if (adminsIds.contains(update.message().chat().id())){
            return;
        }
        if(password != null && password.equals(update.message().text())){
            // если пароль правильный, то необходимо добавить данные человека в таблицу администраторов бота
            adminsIds.add(update.message().chat().id());
            SendMessage sendMessage = new SendMessage(update.message().chat().id(), "Вы добавлены в базу данных как администратор");
            bot.execute(sendMessage);
        }
        else{
            SendMessage sendMessage = new SendMessage(update.message().chat().id(), "Пароль неверный");
            bot.execute(sendMessage);
        }
    }
}
