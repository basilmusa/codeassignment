package xceeds.codeassigment.tweetreader;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import xceeds.codeassigment.config.Configuration;

/**
 * This is an implementation of TweetReader that takes its initialization
 * parameters from a configuration properties file using the following format:
 * 
 * An example of the properties file for configuration:
 * 
 * http.proxy.host=iproxy.isu.net.sa	
 * http.proxy.port=8080
 * oauth.consumerkey=
 * oauth.consumersecret=
 * oauth.accesstoken=
 * oauth.accesstokensecret=
 * debug=[true,false]
 * 
 */
public class TweetReaderUsingPropertiesFile implements TweetReader 
{
	private static final int MAX_TWEET_READS_PERMITTED = 10000;
	private Twitter twitter;
	
	public TweetReaderUsingPropertiesFile() 
	{
		Configuration propConfig = Configuration.getInstance();
		
        ConfigurationBuilder cb = new ConfigurationBuilder()
	    	.setDebugEnabled(propConfig.isDebug())
	    	.setOAuthConsumerKey(propConfig.getOauthConsumerKey())
	    	.setOAuthConsumerSecret(propConfig.getOauthConsumerSecret())
	    	.setOAuthAccessToken(propConfig.getOauthAccessToken())
	    	.setOAuthAccessTokenSecret(propConfig.getOauthAccessTokenSecret());

        if (!propConfig.getHttpProxyHost().isEmpty()) {
        	cb.setHttpProxyHost(propConfig.getHttpProxyHost());
        }
        if (propConfig.getHttpProxyPort() != null) {
        	cb.setHttpProxyPort(propConfig.getHttpProxyPort());
        }

	    twitter = new TwitterFactory(cb.build()).getInstance();
	}
	

	/**
	 * Screen name example is "Khabar_KSA"
	 * 
	 */
	public List<String> readTweets(String screenName, int tweetsToRead) 
	{
		Preconditions.checkNotNull(screenName);
		Preconditions.checkArgument(tweetsToRead >= 1, "Minimum acceptable value is 1 for tweetsToRead");
		Preconditions.checkArgument(tweetsToRead <= MAX_TWEET_READS_PERMITTED, "You are not allowed to read more than [" + MAX_TWEET_READS_PERMITTED + "] in a single call.");
		
		List<String> tweets = new ArrayList<String>();
		
	    try 
	    {
	    	Paging paging = new Paging(1, tweetsToRead);
			List<Status> status = twitter.getUserTimeline(screenName, paging);
			for (Status theStatus : status) 
			{
				tweets.add(theStatus.getText());
			}
		} 
	    catch (TwitterException e) 
		{
	    	Throwables.propagate(e);
		}
	    
	    return tweets;
	}
}
