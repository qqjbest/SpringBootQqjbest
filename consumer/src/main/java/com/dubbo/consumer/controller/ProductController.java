package com.dubbo.consumer.controller;

import com.dubbo.consumer.service.ProductService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private static Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping("/add")
    public String getCost(int a){
        return "该产品共消费："+productService.getCost(a);
    }

    @RequestMapping("/testSendMsg")
    @ResponseBody
    public String testSendMsg(){
        int num = 100;
        for (int i = 1; i< num; i++){
            kafkaTemplate.send("hellow","key"+100,""+i);
            if(i == 50){



            }
//            kafkaTemplate.send("hellow","key", null);
        }
        return "success";
    }

    @KafkaListener(topics = "hellow")
    public void receive(ConsumerRecord<?,?> consumer){
        logger.info("{} - {}:{}", consumer.topic(), consumer.key(), consumer.value());
    }
}
