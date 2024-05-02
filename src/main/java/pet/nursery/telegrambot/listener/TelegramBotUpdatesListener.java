package pet.nursery.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.nursery.telegrambot.service.UpdateDispatcher;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {
    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private TelegramBot telegramBot;
    private UpdateDispatcher updateDispatcher;
    public TelegramBotUpdatesListener(TelegramBot telegramBot,
                                      UpdateDispatcher updateDispatcher){
        this.telegramBot = telegramBot;
        this.updateDispatcher = updateDispatcher;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            updateDispatcher.distribute(update);
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
