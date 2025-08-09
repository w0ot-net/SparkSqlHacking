package org.apache.logging.log4j.core.async;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(
   name = "LinkedTransferQueue",
   category = "Core",
   elementType = "BlockingQueueFactory"
)
public class LinkedTransferQueueFactory implements BlockingQueueFactory {
   public BlockingQueue create(final int capacity) {
      return new LinkedTransferQueue();
   }

   @PluginFactory
   public static LinkedTransferQueueFactory createFactory() {
      return new LinkedTransferQueueFactory();
   }
}
