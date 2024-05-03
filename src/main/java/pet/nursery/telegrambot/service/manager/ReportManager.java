package pet.nursery.telegrambot.service.manager;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

@Service
public class ReportManager {
    public void answerMessage(Update update, TelegramBot bot){
        String botAnswer = """
                В ежедневный отчет входит следующая информация:
                - Фото животного.
                - Рацион животного.
                - Общее самочувствие и привыкание к новому месту.
                - Изменения в поведении: отказ от старых привычек, приобретение новых.
                Отчет нужно присылать каждый день, ограничений в сутках по времени сдачи отчета нет.
                """;
        SendMessage sendMessage = new SendMessage(update.message().chat().id(), botAnswer);
        bot.execute(sendMessage);
    }
}
