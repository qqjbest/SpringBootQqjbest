package com.dubbo.consumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestKafkaService {

    @Resource
    private KafkaService kafkaService;

    @Test
    public void testPrintOneTopicConfigs() throws ExecutionException, InterruptedException {
        kafkaService.printTopicConfigs("hellowWorld_new_method");
    }

    @Test
    public void testListAllTopic(){
        kafkaService.listAllTopic();
    }

    @Test
    public void testDelTopic(){
        kafkaService.delTopic("hellowWorld_new_method");
    }

    @Test
    public void testCreateTopics(){
        try {
            kafkaService.createTopic("hellowWorld_new_method");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
