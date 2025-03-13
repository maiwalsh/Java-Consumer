package src.main.java.com.maiwalsh;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class JavaConsumer {
    public static void main(String[] args) {
        String topic = "test-topic"; // Set your Kafka topic

        // Kafka consumer configuration
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "broker:29092"); // Kafka broker address
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        // Create Kafka consumer instance
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        // Subscribe to the topic
        consumer.subscribe(Collections.singletonList(topic));

        // Poll and consume messages
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("Consumed record with key " + record.key() + " and value " + record.value());
            }
        }
    }
}
