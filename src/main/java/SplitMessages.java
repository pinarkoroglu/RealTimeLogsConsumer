

public class SplitMessages {

    public static String logMessagesSplit(String messages){

        String[] logs= messages.split(" ");
        String timeStamp=logs[0] +" "+logs[1];
        String logLevel=logs[2];
        String logServer=logs[3];
        String logDetail=logs[4];
     //   return ("logs[0] + logs[1]"+"logs[2]"+"logs[3]"+"logs[4]");
        return timeStamp+logLevel+logServer+logDetail;

    }
}
