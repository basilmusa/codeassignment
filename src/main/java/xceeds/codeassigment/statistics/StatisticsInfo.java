package xceeds.codeassigment.statistics;

import com.google.common.collect.Multiset;

/**
 * This class is returned by the {@link StatisticExtracter#calculate(java.util.List)} method.
 * 
 * It simply contains two multisets, one tracking the number of times a word occurred in several tweets,
 * and another multiset containing the number of time a two letter word occurred, a three letter word, a four letter word,
 * etc.
 *
 * This class is immutable and thread safe.
 * 
 */
public class StatisticsInfo 
{	
	public StatisticsInfo(Multiset<String> wordCount,
			Multiset<Integer> wordLengthCount) {
		super();
		this.wordCount = wordCount;
		this.wordLengthCount = wordLengthCount;
	}

	private final Multiset<String> wordCount;
	private final Multiset<Integer> wordLengthCount;

	public Multiset<String> getWordCount() {
		return wordCount;
	}

	public Multiset<Integer> getWordLengthCount() {
		return wordLengthCount;
	}
}
