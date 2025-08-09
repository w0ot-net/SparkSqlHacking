package org.apache.logging.log4j.core.async;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(
   name = "ArrayBlockingQueue",
   category = "Core",
   elementType = "BlockingQueueFactory"
)
public class ArrayBlockingQueueFactory implements BlockingQueueFactory {
   public BlockingQueue create(final int capacity) {
      return new ArrayBlockingQueue(capacity);
   }

   @PluginFactory
   public static ArrayBlockingQueueFactory createFactory() {
      return new ArrayBlockingQueueFactory();
   }
}
