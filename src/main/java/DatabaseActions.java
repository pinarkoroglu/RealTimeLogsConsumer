import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public  class DatabaseActions {

    public static void actionsOfDatabase(String timeStamp,String logLevel,String logServer,String logDetail) {

        MongoClient mongoClntObj = new MongoClient("localhost", 27017);
        DB dbObj = mongoClntObj.getDB("mongodbdemo");
        DBCollection collectionObj = dbObj.getCollection("jcg");

        /**** INSERT OPERATION ****/
        // Creating The MongoDb Documents To Store Key-Value Pair
        BasicDBObject documentObj;

            documentObj = new BasicDBObject();
            documentObj.append("timestamp", timeStamp);
            documentObj.append("loglevel", logLevel);
            documentObj.append("logserver", logServer);
            documentObj.append("logdetail", logDetail);
            collectionObj.insert(documentObj);


        /**** READ OPERATION ****/
        DBCursor cursorObj = collectionObj.find();
        try {
            while (cursorObj.hasNext()) {
                System.out.println(cursorObj.next());
            }
        } finally {
            cursorObj.close();
        }

    }



}
