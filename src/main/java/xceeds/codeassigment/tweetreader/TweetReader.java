package xceeds.codeassigment.tweetreader;

import java.util.List;

/**
 * This interface represents a service for reading public tweets from the web.
 *
 */
public interface TweetReader {
	
	/**
	 * 
	 * @param screenName ScreenName of the twitter feed to read from
	 * @param maxTweetsToRead Maximum number of tweets to read
	 * @return Returns an array containing the tweets read or an empty array. This method never returns null.
	 */
	List<String> readTweets(String screenName, int maxTweetsToRead);
}
