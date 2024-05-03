package pet.nursery.telegrambot.service.handler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Service;
import pet.nursery.telegrambot.service.action.Action;
import pet.nursery.telegrambot.service.action.AdminAction;
import pet.nursery.telegrambot.service.manager.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MessageHandler {
    private final StartManager startManager;
    private final InfoManager infoManager;
    private final ContactsManager contactsManager;
    private final NurseryManager nurseryManager;
    private final ReportManager reportManager;
    private final Map<String, Action> actions = Map.of("/admin", new AdminAction());
    // карта пар идентификатор чата -> команда, которую отправил пользователь в прошлом запросе
    private final Map<String, String> bindingBy = new ConcurrentHashMap<>();
    public MessageHandler(StartManager startManager,
                          InfoManager infoManager,
                          ContactsManager contactsManager,
                          NurseryManager nurseryManager,
                          ReportManager reportManager){
        this.startManager = startManager;
        this.infoManager = infoManager;
        this.contactsManager = contactsManager;
        this.nurseryManager = nurseryManager;
        this.reportManager = reportManager;
    }
    public void answer(Update update, TelegramBot bot) {
            switch (update.message().text()){
                case "/start" -> startManager.answerMessage(update, bot);
                case "/info" -> infoManager.answerMessage(update, bot);
                case "/nursery" -> nurseryManager.answerMessage(update, bot);
                case "/report" -> reportManager.answerMessage(update, bot);
                case "/contacts" -> contactsManager.answerMessage(update, bot);
            }
            String key = update.message().text();
            String chatId = update.message().chat().id().toString();
            if (actions.containsKey(key)) {
                actions.get(key).handle(update, bot);   // выполняется ответ пользователю в котором указывается, что надо ввести
                bindingBy.put(chatId, key);             // запись пары chatId - команда (/start, /new, /info ...)
            } else if (bindingBy.containsKey(chatId)) {
                actions.get(bindingBy.get(chatId)).callback(update, bot);   // проверяется ответ пользователя
                bindingBy.remove(chatId);                                   // удаляется идентификатор т.к. команда обработана
            }
    }
}