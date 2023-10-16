//package com.moviebooking.auth.kafka;
//
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class KafkaConsumer {
//   
//    @KafkaListener(topics = "movieapp", groupId = "movieappgroup")
//    public void consume(String message){
//        System.out.println(String.format("Message received from movie-> %s", message));
//    }
//}