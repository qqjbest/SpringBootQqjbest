package com.dubbo.kafka.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface KafkaService {

    /**
     * 创建话题
     * @param topicName
     */
    public void createTopic(String topicName) throws ExecutionException, InterruptedException;

    /**
     * 删除话题
     * @param topic
     */
    public void delTopic(String topic);

    /**
     * 查看所有主题
     * @return
     */
    public List<String> listAllTopic();

    /**
     * 打印所有话题的配置
     */
    public void printTopicConfigs(String topicName) throws ExecutionException, InterruptedException;

    /**
     * 修改话题
     */
    public void updateTopic() throws ExecutionException, InterruptedException;
}
