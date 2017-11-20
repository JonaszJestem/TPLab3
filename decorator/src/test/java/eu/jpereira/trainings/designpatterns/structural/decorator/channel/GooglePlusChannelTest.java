package eu.jpereira.trainings.designpatterns.structural.decorator.channel;

import eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator.AbstractSocialChanneldDecoratorTest;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GooglePlusChannelTest {

    @Test
    public void shouldSendMessagesToGooglePlus() {
        DefaultSocialChannelBuilder builder = new DefaultSocialChannelBuilder();

        SocialChannelProperties properties = new SocialChannelProperties();
        SocialChannel googleChannel = builder.buildChannel(properties.putProperty(SocialChannelPropertyKey.NAME, GooglePlusChannel.NAME));
        assertNotNull(googleChannel);
        assertTrue(googleChannel instanceof GooglePlusChannel);
        googleChannel.deliverMessage("Message to google+");
    }
}
