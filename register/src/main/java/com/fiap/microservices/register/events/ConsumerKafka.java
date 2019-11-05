package com.fiap.microservices.register.events;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fiap.microservices.register.enums.Status;
import com.fiap.microservices.register.model.OrderChangeDTO;

@Service
public class ConsumerKafka {

	private final Logger logger = LoggerFactory.getLogger(Producer.class);
	private static String TOPIC;

	private final Properties props;

	//    @KafkaListener(topics = "testes", groupId = "from-beginnig")
	//    public void consume(OrderChangeDTO ordUpdated) throws IOException {
	//        logger.info(String.format("#### -> Consumed message -> %s", ordUpdated));
	//    }
	public ConsumerKafka() {
		String username = "ka7vhg10";
		String password = "JYdxEeOiknXaQKAcoExjpSh9yDn9HNl_";
		String brokers = "velomobile-01.srvs.cloudkafka.com:9094,velomobile-02.srvs.cloudkafka.com:9094\r\n" + 
				",velomobile-03.srvs.cloudkafka.com:9094";
		ConsumerKafka.TOPIC = username + "-default";

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

	public void consume() {
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Arrays.asList(TOPIC));
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(1000);
			for (ConsumerRecord<String, String> record : records) {
				System.out.printf("%s [%d] offset=%d, key=%s, value=\"%s\"\n",
						record.topic(), record.partition(),
						record.offset(), record.key(), record.value());
				String[] id = record.value().split(",");
				OrderChangeDTO ordUpdated = new OrderChangeDTO(Long.valueOf(id[0]), Long.valueOf(id[1]), Status.valueOf(id[2]));
				logger.debug("Recebida atualização de Status do chamado de id: " + ordUpdated);
				switch (ordUpdated.getStatus()) {
				case ABERTO:
					logger.debug("Atualizado status para ABERTO do usuário de id {}",
							ordUpdated.getUserId());
					break;
				case EM_ANALISE:
					logger.debug("Atualizado status para EM ANÁLISE do usuário de id {}",
							ordUpdated.getUserId());
					break;
				case CONCLUIDO:
					logger.debug("Atualizado status para CONCLUÍDO do usuário de id {}",
							ordUpdated.getUserId());
					break;
				case CANCELADO:
					logger.debug("Atualizado status para CANCELADO do usuário de id {}",
							ordUpdated.getUserId());
					break;
				default:
					logger.error("Recebido uma mensagem inesperada do chamado de id ", ordUpdated.getId());
					break;
				}
			}
		}
	}
}
