//package com.cognizant.moviebookingapp.kafka;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class KafkaProducer {
//
//    @Value("movieapp")
//    private String topicName;
//
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    public void sendMessage(String message){
//        System.out.println(String.format("kafka Message sent from movie: %s", message));
//        kafkaTemplate.send(topicName, message);
//    }
//}