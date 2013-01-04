package xceeds.codeassigment.statistics;

import java.util.List;

import com.google.common.base.Splitter;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * Stateless class to receive a list of tweets and extract some useful information
 *
 */
public class StatisticExtracter 
{
	private static Splitter splitter = Splitter.on(' ').trimResults().omitEmptyStrings();

	public static StatisticsInfo analyze(List<String> tweets) 
	{
		Multiset<String> wordCount = HashMultiset.create();
		Multiset<Integer> wordLengthCount = HashMultiset.create();
		
		for (String tweet : tweets) 
		{
			Iterable<String> words = splitter.split(tweet);
			
			for (String word : words) 
			{
				wordCount.add(word.toLowerCase());
				wordLengthCount.add(word.length());
			}
		}
		
		return new StatisticsInfo(wordCount, wordLengthCount);
	}
}
