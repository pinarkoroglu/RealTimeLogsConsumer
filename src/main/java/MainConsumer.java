public class MainConsumer {


    public static void main(String[] args){

        KafkaConsumerCreator kafkaConsumerCreator = new KafkaConsumerCreator();
        kafkaConsumerCreator.run();

        kafkaConsumerCreator.getKafkaConsumer().wakeup();
        System.out.println("Stopping consumer .....");




}

}