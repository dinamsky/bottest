package bot;


import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import parser.Feed;
import parser.FeedMessage;
import parser.RSSFeedParser;
import parser.WeatherParser;

import java.io.IOException;

public class Example extends TelegramLongPollingBot {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botapi = new TelegramBotsApi();
        try {
            botapi.registerBot(new Example());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public String getBotUsername() {
        return "fake_newsbot";
        //возвращаем юзера
    }


    public void onUpdateReceived(Update e) {
        Message msg = e.getMessage();
        String txt = msg.getText();
        if (txt.equals("/start")) {
            sendMsg(msg, "Hello, world! This is Fake News bot!");
        }
        if (txt.equals("/info")) {
            this.sendMsg(msg, "Soon here'll be some interesting service! for more info you can go direct on http://fakenewsbot.online");
        }
        if (txt.equals("/weather")) {

            RSSFeedParser parser = new RSSFeedParser(
                    "https://habr.com/ru/rss/");
            Feed feed = parser.readFeed();
            System.out.println(feed);
            for (FeedMessage message : feed.getMessages()) {
                this.sendMsg(msg, message.toString());


            }
            if (txt.equals("/help")) {
                sendMsg(msg, "Hello, world! This is Fake News bot!");
            }
        }}

    @Override
    public String getBotToken() {
        return "719168959:AAGn2WNu8ndkK70F3rG_aOcOe_0-DN2pkaM";

    }
    @SuppressWarnings("deprecation")
    private void sendMsg(Message msg, String text) {
        SendMessage s = new SendMessage();
        s.setChatId(msg.getChatId());
        s.setText(text);
        try {
            execute(s);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

}