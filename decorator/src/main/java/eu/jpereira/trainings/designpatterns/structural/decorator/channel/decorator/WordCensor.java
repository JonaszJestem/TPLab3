package eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator;

import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCensor extends SocialChannelDecorator {
    String[] badWords = {"123", "234", "345"};

    @Override
    public void deliverMessage(String message) {

        String regex = String.join("|", badWords);
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        Matcher m = p.matcher(message);
        message = m.replaceAll("###");

        delegate.deliverMessage(message);
    }
}
