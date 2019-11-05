package com.fiap.microservices.netflix.servicedesk.events;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class ProducerKafka {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static String TOPIC;

    private final Properties props;
    
    public ProducerKafka() {
    	String username = "ka7vhg10";
    	String password = "JYdxEeOiknXaQKAcoExjpSh9yDn9HNl_";
    	String brokers = "velomobile-01.srvs.cloudkafka.com:9094,velomobile-02.srvs.cloudkafka.com:9094\r\n" + 
    			",velomobile-03.srvs.cloudkafka.com:9094";
        ProducerKafka.TOPIC = username + "-default";

        String jaasTemplate = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";";
        String jaasCfg = String.format(jaasTemplate, username, password);

        String serializer = StringSerializer.class.getName();
        String deserializer = StringDeserializer.class.getName();
        props = new Properties();
        props.put("bootstrap.servers", brokers);
        props.put("group.id", username + "-consumer");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "earliest");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", deserializer);
        props.put("value.deserializer", deserializer);
        props.put("key.serializer", serializer);
        props.put("value.serializer", serializer);
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.mechanism", "SCRAM-SHA-256");
        props.put("sasl.jaas.config", jaasCfg);
    }
    
    public void produce(String key, String value) {
    	Producer<String, String> producer = new KafkaProducer<>(props);
    	producer.send(new ProducerRecord<>(TOPIC, key, value));
    	producer.close();
//        Thread one = new Thread() {
//            public void run() {
//                try {
//                    Producer<String, String> producer = new KafkaProducer<>(props);
//                    int i = 0;
//                    while(true) {
//                        Date d = new Date();
//                        producer.send(new ProducerRecord<>(TOPIC, Integer.toString(i), d.toString()));
//                        Thread.sleep(1000);
//                        i++;
//                    }
//                } catch (InterruptedException v) {
//                    System.out.println(v);
//                }
//            }
//        };
//        one.start();
    }
}

