package xceeds.codeassigment;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Multiset;

import xceeds.codeassigment.statistics.StatisticExtracter;
import xceeds.codeassigment.statistics.StatisticsInfo;
import xceeds.codeassigment.tweetreader.TweetReader;
import xceeds.codeassigment.tweetreader.TweetReaderUsingPropertiesFile;


/**
 * Hello world!
 *
 */
public class App 
{
	/**
	 * Main method, application starts here.
	 * 
	 */
    public static void main(String[] args )
    {
    	
    	// Query the tweets
    	TweetReader tweetReader = new TweetReaderUsingPropertiesFile();
    	
    	List<String> last100Tweets = tweetReader.readTweets("Khabar_KSA", 100);
    	
    	for (String tweet : last100Tweets) {
    		System.out.println(tweet);
    	}
    	
    	// Perform statistical analysis of the sample data queried
    	StatisticsInfo statisticsInfo = StatisticExtracter.analyze(last100Tweets);

    	// Store the results in the MongoDB
    	
    	
    	// Format the results showing useful info
    	printResults(statisticsInfo);
    }

    /**
     * Method to format some useful statistical results
     * 
     * @param statisticsInfo
     */
	private static void printResults(StatisticsInfo statisticsInfo) {
		Set<String> elementSet = statisticsInfo.getWordCount().elementSet();

    	// Print on screen words occurring more than 3 times in the last 100 tweets
    	final int WORD_OCCURRENCE_TO_DISPLAY_THRESHOLD = 10;
    	System.out.println("Words occurring more than " + WORD_OCCURRENCE_TO_DISPLAY_THRESHOLD + " times in the last 100 tweets:");
    	System.out.println("---------------------------------------------------------");
    	
    	for (String element : elementSet) {
    		int count = statisticsInfo.getWordCount().count(element);

    		if (count > WORD_OCCURRENCE_TO_DISPLAY_THRESHOLD) {
    			System.out.printf("The word [%s] occurred [%d] times.\n", element, statisticsInfo.getWordCount().count(element));
    		}
    	}
    	    
    	// Print the count of words with more than 7 characters
    	Multiset<Integer> wordLengthCount = statisticsInfo.getWordLengthCount();
    	Set<Integer> elementSet2 = wordLengthCount.elementSet();
    	
    	final int WORD_LENGTH_COUNT_DISPLAY_THRESHOLD = 8;
    	System.out.println("Showing word count for words with length of " + WORD_LENGTH_COUNT_DISPLAY_THRESHOLD + " or more characters in last 100 tweets");
    	System.out.println("-----------------------------------------------------------------------------------------------------------");
    	for (Integer element : elementSet2) {
    		if (element >= WORD_LENGTH_COUNT_DISPLAY_THRESHOLD) {
    			System.out.printf(
    				"Words with length of [%d] characters occurred [%d] times\n", 
    					element, 
    					wordLengthCount.count(element));
    		}
    	}
	}
    
}
