import java.util.*;


import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class KafkaConsumerCreator implements Runnable {
        DatabaseActions databaseActions;
        /*kafka consumer creator */
        public static Consumer<String, String> createConsumer() {
            Properties properties = new Properties();
            properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, IKafkaConstants.KAFKA_BROKERS);
            properties.put(ConsumerConfig.GROUP_ID_CONFIG, IKafkaConstants.GROUP_ID_CONFIG);
            properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
            properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, IKafkaConstants.MAX_POLL_RECORDS);
            properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
            properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, IKafkaConstants.OFFSET_RESET_EARLIER);
            Consumer<String, String> consumer = new KafkaConsumer<>(properties);
            consumer.subscribe(Collections.singletonList(IKafkaConstants.TOPIC_NAME));
            return consumer;
        }

        @Override
        public void run() {
            Consumer<String, String> consumer = createConsumer();
            int noMessageFound = 0;
            try{
                while (true) {
                    ConsumerRecords<String, String> consumerRecords = consumer.poll(1000);
                    for (ConsumerRecord<String, String> record : consumerRecords){
                        String[] logs= record.value().split(" ");
                        DatabaseActions.actionsOfDatabase(logs[0]+" "+logs[1],logs[2],logs[3],logs[4]);
                    }

                }

            }catch(WakeupException ex){
                System.out.println("Exception caught " + ex.getMessage());
            }finally{
                consumer.close();
                System.out.println("Close KafkaConsumer");
            }

        }

        public Consumer<String,String> getKafkaConsumer(){
            return createConsumer();
        }

        public KafkaConsumerCreator(){

        }
    }


