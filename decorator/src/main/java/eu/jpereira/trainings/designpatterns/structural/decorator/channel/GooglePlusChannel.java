package eu.jpereira.trainings.designpatterns.structural.decorator.channel;

public class GooglePlusChannel implements SocialChannel {

    public static final String NAME = "Google+";
    @Override
    public void deliverMessage(String message) {
        System.out.println("Google+: " + message);
    }
}
