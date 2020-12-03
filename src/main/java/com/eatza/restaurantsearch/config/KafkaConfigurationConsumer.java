package com.eatza.restaurantsearch.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.eatza.restaurantsearch.dto.ReviewResponseDto;

@EnableKafka
@Configuration
public class KafkaConfigurationConsumer {
	@ConditionalOnMissingBean(ConsumerFactory.class)
	public ConsumerFactory<String, ReviewResponseDto> userConsumerFactory() {
//		Map<String, Object> config = new HashMap<>();
//
//		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
//		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
//		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
//				new JsonDeserializer<>(CustomerDto.class));

		JsonDeserializer<ReviewResponseDto> deserializer = new JsonDeserializer<>(ReviewResponseDto.class);
		deserializer.setRemoveTypeHeaders(false);
		deserializer.addTrustedPackages("*");
		deserializer.setUseTypeMapperForKey(true);
		Map<String, Object> config = new HashMap<>();

		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
		config.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, deserializer);

		config.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, deserializer.getClass());
		config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		// return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
		// new JsonDeserializer<>(User.class));
		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
				new ErrorHandlingDeserializer<ReviewResponseDto>(deserializer));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, ReviewResponseDto> userKafkaListenerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, ReviewResponseDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(userConsumerFactory());
		return factory;
	}

//    @Bean
//    public ConsumerFactory<String, Object> userConsumerFactory() {
//        Map<String, Object> config = new HashMap<>();
//
// 
//
//        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
//        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
//        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        config.put(JsonDeserializer.TRUSTED_PACKAGES, "com.eatza.review.dto");
////      return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),new JsonDeserializer<>(User.class));
//        return new DefaultKafkaConsumerFactory<>(config,  new StringDeserializer(),
//                new JsonDeserializer<>(Object.class,false));
//    }
//    
//    
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, CustomerDto> userKafkaListenerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, CustomerDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(userConsumerFactory());
//        return factory;
//    }
}
