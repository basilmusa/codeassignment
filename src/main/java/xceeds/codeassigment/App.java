package xceeds.codeassigment;

import java.util.List;

import xceeds.codeassigment.tweetreader.TweetReader;
import xceeds.codeassigment.tweetreader.TweetReaderUsingPropertiesFile;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args )
    {
    	TweetReader tweetReader = new TweetReaderUsingPropertiesFile();
    	
    	List<String> last100Tweets = tweetReader.readTweets("Khabar_KSA", 100);
    	
    	for (String tweet : last100Tweets) {
    		System.out.println(tweet);
    	}
    }
}
