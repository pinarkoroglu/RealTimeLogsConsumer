package com.pinar.consumer.Database;

import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public  class DatabaseActions {

    public static DBCollection databaseConfig() {
        MongoClient mongoClntObj = new MongoClient("localhost", 27017);
        DB dbObj = mongoClntObj.getDB("mongodbdemo");
        DBCollection collectionObj = dbObj.getCollection("jcg");
        return collectionObj;
    }

    public static DBCollection collection = databaseConfig();

    public static void actionsOfDatabase(String timeStamp, String logLevel, String logServer, String logDetail) {

        /**** INSERT OPERATION ****/
        // Creating The MongoDb Documents To Store Key-Value Pair

        try {
            BasicDBObject documentObj;

            documentObj = new BasicDBObject();
            documentObj.append("timestamp", timeStamp);
            documentObj.append("loglevel", logLevel);
            documentObj.append("logserver", logServer);
            documentObj.append("logdetail", logDetail);
            collection.insert(documentObj);
        } catch (Exception e) {
            System.out.println(e);
        }





    }
}