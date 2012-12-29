package xceeds.codeassigment;

import java.util.List;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ConfigurationBuilder cb = new ConfigurationBuilder()
        	.setDebugEnabled(true)
        	.setHttpProxyHost("iproxy.isu.net.sa")
        	.setHttpProxyPort(8080)
        	.setOAuthConsumerKey("DmrUqDEgZCrylTYjDK9w")
        	.setOAuthConsumerSecret("3O9lrkt6iyjpudGTTud7B5QfHuHoYMsBJgo7oladU")
        	.setOAuthAccessToken("989118554-i2LMnTQrmc50572teBPU1yBJnvMGIOFctF8t6RKD")
        	.setOAuthAccessTokenSecret("8VDYi9ZXdXAJX73vmreVb2hseuwuSuLvHh3l8Nygo");
        
        
        Twitter twitter = new TwitterFactory(cb.build()).getInstance();

        try 
        {
        	Paging paging = new Paging(1, 10);
			List<Status> status = twitter.getUserTimeline("Khabar_KSA", paging);
			for (Status theStatus : status) 
			{
				System.out.println(theStatus.getText());
			}
		} 
        catch (TwitterException e) 
		{
			e.printStackTrace();
		}
    }
}
