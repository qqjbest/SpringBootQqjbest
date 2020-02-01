package com.dubbo.kafka.service.impl;

import com.dubbo.kafka.service.KafkaService;
import com.google.common.collect.ImmutableList;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.config.ConfigResource;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class KafkaServiceImpl implements KafkaService {

    @Override
    public void createTopic(String topicName) throws ExecutionException, InterruptedException {
        Properties pros = new Properties();
        pros.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        AdminClient adminClient = AdminClient.create(pros);
        CreateTopicsOptions options = new CreateTopicsOptions();
        Integer numPartitions = 1;
        Short replicationFactor = 1;
        NewTopic newTopic = new NewTopic(topicName, numPartitions, replicationFactor);
        CreateTopicsResult result = adminClient.createTopics(ImmutableList.of(newTopic), options);
        for(Map.Entry<String,KafkaFuture<Void>> e : result.values().entrySet()){
            KafkaFuture<Void> future= e.getValue();
            future.get();
            boolean success=!future.isCompletedExceptionally();
            if(success){
                System.out.println("已成功创建Kafka topic "+topicName+" ,分区 "+numPartitions+" ,副本 "+replicationFactor);
            }
        }
        adminClient.close();

/*
        下面这种已废弃
        ZkUtils zkUtils = ZkUtils.apply("127.0.0.1:2181", 30000, 30000, JaasUtils.isZkSecurityEnabled());
        // 创建一个单分区单副本名为topic-20的topic
        AdminUtils.createTopic(zkUtils, topicName, 1,1, new Properties(), AdminUtils.createTopic$default$6());
        zkUtils.close();
*/
    }

    @Override
    public void delTopic(String topic) {
        Properties pros = new Properties();
        pros.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        AdminClient adminClient = AdminClient.create(pros);
        DeleteTopicsOptions options=new DeleteTopicsOptions();
//        options.timeoutMs(6000);
        DeleteTopicsResult result = adminClient.deleteTopics(Arrays.asList(new String[]{topic}), options);
        Boolean delFlag = result.all().isDone();
        System.out.println(delFlag);
        adminClient.close();
        /*
        以下方法过时
        ZkUtils zkUtils = ZkUtils.apply("127.0.0.1:2181", 30000, 30000, JaasUtils.isZkSecurityEnabled());
        AdminUtils.deleteTopic(zkUtils, "topic-20");
        zkUtils.close();
        */
    }

    @Override
    public List<String> listAllTopic() {
        Properties pros = new Properties();
        pros.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        AdminClient adminClient = AdminClient.create(pros);
        ListTopicsResult result = adminClient.listTopics();
        List<String> strs = new ArrayList<>();
        try {
            Set<String> topics= result.names().get();
            Iterator<String> it = topics.iterator();
            while (it.hasNext()){
                String name = it.next();
                System.out.println(name);
                strs.add(name);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        adminClient.close();
        //下面这种方法过时
/*        ZkUtils zkUtils = ZkUtils.apply("127.0.0.1:2181", 30000, 30000, JaasUtils.isZkSecurityEnabled());
        List<String> topics = JavaConversions.seqAsJavaList(zkUtils.getAllTopics());
        topics.forEach(System.out::println);*/
        return strs;
    }

    @Override
    public void printTopicConfigs(String topicName) throws ExecutionException, InterruptedException {
        Properties pros = new Properties();
        pros.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        AdminClient adminClient = AdminClient.create(pros);
        DescribeTopicsResult ret = adminClient.describeTopics(Arrays.asList(topicName,"__consumer_offsets"));
        Map<String, TopicDescription> topics = ret.all().get();
        for(Map.Entry<String, TopicDescription> entry : topics.entrySet()) {
            System.out.println(entry.getKey() +" ===> "+ entry.getValue());
        }
        adminClient.close();
        /*
            以下方法已经过时
        ZkUtils zkUtils = null;
        try{
            zkUtils = ZkUtils.apply("127.0.0.1:2181",30000,30000, JaasUtils.isZkSecurityEnabled());
            Map<String, Properties> configs = JavaConversions.mapAsJavaMap(AdminUtils.fetchAllTopicConfigs(zkUtils));
            // 获取特定topic的元数据
//            MetadataResponse.TopicMetadata topicMetadata = AdminUtils.fetchTopicMetadataFromZk("topictopic-20", zkUtils);
            MetadataResponse.TopicMetadata topicMetadata = AdminUtils.fetchTopicMetadataFromZk("hellow", zkUtils);
            // 获取特定topic的配置信息
//            Properties properties = AdminUtils.fetchEntityConfig(zkUtils, "topics", "topic-20");
            Properties properties = AdminUtils.fetchEntityConfig(zkUtils, "topics", "hellow");
            for (Map.Entry<String, Properties> entry: configs.entrySet()){
                System.out.println("key=" + entry.getKey() + ";value= " + entry.getValue());
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            if(zkUtils != null){
                zkUtils.close();
            }
        }*/
    }

    @Override
    public void updateTopic() throws ExecutionException, InterruptedException {
        String topicName = "topicName";
        Properties properties = new Properties();
        properties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        AdminClient adminClient = AdminClient.create(properties);
        List<ConfigEntry> configEntrys = new ArrayList<>();
        configEntrys.add(new ConfigEntry("cleanup.policy","compact"));
        Config topicConfig =new Config(configEntrys);

        Map<ConfigResource, Config> configs = new HashMap<>();
        configs.put(new ConfigResource(ConfigResource.Type.TOPIC, topicName), topicConfig);
        AlterConfigsResult configsResult = adminClient.alterConfigs(configs);
        KafkaFuture<Void> future= configsResult.all();
        future.get();
        boolean success=!future.isCompletedExceptionally();
        if(success){
            //成功修改配置
        }
        adminClient.close();
        /*
        下面方法过时
        ZkUtils zkUtils = ZkUtils.apply("127.0.0.1:2181", 30000, 30000, JaasUtils.isZkSecurityEnabled());
        Properties props = AdminUtils.fetchEntityConfig(zkUtils, ConfigType.Topic(), "topic-20");
        //增加topic级别属性
        props.put("min.cleanable.dirty.ratio", "0.3");
        props.remove("max.message.bytes");
        AdminUtils.changeTopicConfig(zkUtils, "topic-20", props);
        zkUtils.close();*/
    }
}
