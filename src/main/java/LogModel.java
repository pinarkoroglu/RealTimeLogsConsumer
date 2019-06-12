public class LogModel {
    private static String timeStamp;
    private static String logLevel;
    private static String logServer;
    private static String logDetail;

    public LogModel(String timeStamp,String logLevel,String logServer,String logDetail){
        this.timeStamp=timeStamp;
        this.logLevel=logLevel;
        this.logServer=logServer;
        this.logDetail=logDetail;
    }

    public static String getTimeStamp() {
        return timeStamp;
    }

    public static void setTimeStamp(String timeStamp) {
        LogModel.timeStamp = timeStamp;
    }

    public static String getLogLevel() {
        return logLevel;
    }

    public static void setLogLevel(String logLevel) {
        LogModel.logLevel = logLevel;
    }

    public static String getLogServer() {
        return logServer;
    }

    public static void setLogServer(String logServer) {
        LogModel.logServer = logServer;
    }

    public static String getLogDetail() {
        return logDetail;
    }

    public static void setLogDetail(String logDetail) {
        LogModel.logDetail = logDetail;
    }
}
