package org.apache.logging.log4j.core.appender.mom.kafka;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

public class DefaultKafkaProducerFactory implements KafkaProducerFactory {
   public Producer newKafkaProducer(final Properties config) {
      return new KafkaProducer(config);
   }
}
