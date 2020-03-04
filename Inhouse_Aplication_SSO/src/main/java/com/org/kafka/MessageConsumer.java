package com.org.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.annotation.PartitionOffset;
//import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import com.org.dto.EmployeeDto;
import com.org.exeception.RegistrationException;
import com.org.reflection.AbstractReflection;
import com.org.serializable.GenericDeserializer;
import com.org.util.ApplicationContextProvider;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MessageConsumer {

	@Value(value = "${kafka.topicName}")
	private String topicName;

	@Autowired
	private GenericDeserializer<?> genericDeserializer;

	@Autowired
	private ApplicationContextProvider applicationContextProvider;

	@Autowired
	private AbstractReflection abstractReflection;

	/*
	 * @KafkaListener(topicPartitions = @TopicPartition(topic = "Apps",
	 * partitionOffsets = {
	 * 
	 * @PartitionOffset(partition = "0", initialOffset = "0"),
	 * 
	 * @PartitionOffset(partition = "3", initialOffset = "0") }))
	 */

	/*
	 * @KafkaListener(topicPartitions = @TopicPartition(topic = "Apps", partition =
	 * {"0", "1"}))
	 */

	@SuppressWarnings({ "unchecked" })

	@KafkaListener(topics = "${kafka.topicName}", groupId = "${kafka.groupId}", containerFactory = "concurrentKafkaListenerContainerFactory")
	public void consumeMessage(@Payload byte[] message, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
			@Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) throws RegistrationException {

		EmployeeDto genericObjectDto = (EmployeeDto) genericDeserializer.deserialize(this.topicName, message,
				EmployeeDto.class);

		System.out.println("Received Message: " + genericObjectDto + "from partition: " + partition + " key: " + key);
		abstractReflection.callingMethod(genericObjectDto, key, applicationContextProvider);

		if (genericObjectDto == null) {
			log.warn("Unable to Received Message");
		}
	}
}
