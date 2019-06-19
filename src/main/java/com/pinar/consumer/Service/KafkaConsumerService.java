package com.pinar.consumer.Service;

import com.pinar.consumer.Interfaces.IKafkaConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import com.pinar.consumer.Database.DatabaseActions;

@Service
public class KafkaConsumerService {
    @Autowired
     SimpMessagingTemplate template;
    @KafkaListener(topics= IKafkaConstants.TOPIC_NAME,groupId = IKafkaConstants.GROUP_ID_CONFIG)
    public  void consume(@Payload String message) {
        String[] logs= message.split(" ");
        DatabaseActions.actionsOfDatabase(logs[0]+" "+logs[1],logs[2],logs[3],logs[4]);
        template.convertAndSend("/topic/logs", message);

        }

}
