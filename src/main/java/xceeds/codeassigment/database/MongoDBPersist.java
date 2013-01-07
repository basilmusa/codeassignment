package xceeds.codeassigment.database;

import java.net.UnknownHostException;
import java.util.Set;

import xceeds.codeassigment.statistics.StatisticsInfo;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.collect.Multiset;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class MongoDBPersist 
{
	private static MongoDBPersist INSTANCE = null;
	
	public static MongoDBPersist getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MongoDBPersist();
		}
		return INSTANCE;
	}
	
	private Mongo mongo;
	private DB db;
	
	private MongoDBPersist() 
	{
		try 
		{
			mongo = new Mongo("localhost", 27017);

			db = mongo.getDB("xceeds");

		} catch (UnknownHostException e) {
			Throwables.propagate(e);
		}		
	}
	
	/**
	 * This method stores the statistics info collected to the MongoDB database
	 * 
	 * @param statisticsInfo
	 */
	public void storeStatistics(StatisticsInfo statisticsInfo) {
		Preconditions.checkNotNull(statisticsInfo);

		DBCollection collection = db.getCollection("statistics");

		
		// Map the word count data to mongo db structures
		Multiset<String> wordCount = statisticsInfo.getWordCount();
		BasicDBObject mdbWordCount = new BasicDBObject();
		
		Set<String> elementSet = wordCount.elementSet();
		
		int wcIndex = 0;
		for (String element : elementSet) 
		{
			wcIndex++;
			mdbWordCount.append("wordCountEntry_" + wcIndex, 
				new BasicDBObject("word", element).append("count", wordCount.count(element)));
		}
		
		// Now map the 'word length' count data to mongo db structures
		Multiset<Integer> wordLengthCount = statisticsInfo.getWordLengthCount();
		BasicDBObject mdbWordLengthCount = new BasicDBObject();
		Set<Integer> wlcElementSet = wordLengthCount.elementSet();
		
		int index = 0;
		for (Integer element : wlcElementSet) {
			index++;
			mdbWordLengthCount.append("wordLengthEntry_" + index, 
					new BasicDBObject("wordLength", element).append("count", wordLengthCount.count(element)));
		}
		
		BasicDBObject object = 
				new BasicDBObject("wordCountStats", mdbWordCount)
						.append("wordLengthCountStats", mdbWordLengthCount);

		// Now insert it into the database
		collection.insert(object);
	}
	
	public static void main(String[] args) 
	{
		Mongo mongo;
		
		try 
		{
			mongo = new Mongo("localhost", 27017);

			DB db = mongo.getDB("xceeds");

			DBCollection collection = db.getCollection("statistics");

			BasicDBObject object = new BasicDBObject("wordCount", 5).append(
					"wordLengthCount", 10);

			collection.insert(object);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
