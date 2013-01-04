package xceeds.codeassigment.database;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class MongoDBPersist {
	public static void main(String[] args) {
		Mongo mongo;
		try {
			mongo = new Mongo("localhost", 27017);

			DB db = mongo.getDB("xceeds");

			DBCollection collection = db.getCollection("statistics");

			BasicDBObject object = new BasicDBObject("wordCount", 5).append(
					"wordLengthCount", 10);

			collection.insert(object);
			
			collection.save
			db.cleanCursors(true);
			

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
