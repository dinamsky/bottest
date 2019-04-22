package bot;

import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.objects.Message;

import java.net.URLEncoder;

/**
 * Voice Bot
 * - any simple text message to voice message
 */
public class VKBot {

    public static void main(String[] args) {

        Group group = new Group(181300898, "0b7243c9e6824021764f87ca57e1d01efed6466ba4f4f4bb6acdea8163daf4b6a52445cdaddce9c174f02");

        // Yandex SpeechKit API key
        // http://developer.tech.yandex.ru/keys/
        String yandexKey = "3947e1d7-2fda-40c2-b235-94f169657ecd";

        String url = "https://tts.voicetech.yandex.net/generate?format=mp3&lang=ru&speaker=zahar&key=" + yandexKey + "&text=";

        // Voice all text messages
        group.onSimpleTextMessage(message -> {
            new Message()
                    .from(group)
                    .text("Эй чуваак , пришли мне текст я его озыучу.")
                    .send();
        });

        // Send error for other messages
        group.onMessage(message -> {
            new Message()
                    .from(group)
                    .to(message.authorId())
                    .text("Эй чуваак , пришли мне текст я его озыучу.")
                    .send();
        });
    }
}