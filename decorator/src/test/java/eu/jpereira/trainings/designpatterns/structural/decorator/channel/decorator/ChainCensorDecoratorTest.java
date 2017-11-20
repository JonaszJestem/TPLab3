package eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator;

import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannel;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelBuilder;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelProperties;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelPropertyKey;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.spy.TestSpySocialChannel;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ChainCensorDecoratorTest extends AbstractSocialChanneldDecoratorTest {
    @Test
    public void ShouldReturnProperMessageWithThreeDecorators() {
        SocialChannelBuilder builder = createTestSpySocialChannelBuilder();

        SocialChannelProperties props = new SocialChannelProperties().putProperty(SocialChannelPropertyKey.NAME, TestSpySocialChannel.NAME);
        SocialChannel channel = builder.
                with(new MessageTruncator(20)).
                with(new URLAppender("http://ja234.com")).
                with(new WordCensor()).
                getDecoratedChannel(props);

        // Now call channel.deliverMessage
        channel.deliverMessage("o 1231231223, gjeiuagoiae345fuca, apwledijgkvcaaaa !!!,fg.aege/gae123 AXEP");

        TestSpySocialChannel spyChannel = (TestSpySocialChannel) builder.buildChannel(props);
        assertEquals("o ######1223, gje... http://ja###.com", spyChannel.lastMessagePublished());
    }
}
