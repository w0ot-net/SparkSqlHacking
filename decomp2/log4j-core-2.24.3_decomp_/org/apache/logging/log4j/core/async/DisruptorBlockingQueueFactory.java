package org.apache.logging.log4j.core.async;

import com.conversantmedia.util.concurrent.DisruptorBlockingQueue;
import com.conversantmedia.util.concurrent.SpinPolicy;
import java.util.concurrent.BlockingQueue;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(
   name = "DisruptorBlockingQueue",
   category = "Core",
   elementType = "BlockingQueueFactory"
)
public class DisruptorBlockingQueueFactory implements BlockingQueueFactory {
   private final SpinPolicy spinPolicy;

   private DisruptorBlockingQueueFactory(final SpinPolicy spinPolicy) {
      this.spinPolicy = spinPolicy;
   }

   public BlockingQueue create(final int capacity) {
      return new DisruptorBlockingQueue(capacity, this.spinPolicy);
   }

   @PluginFactory
   public static DisruptorBlockingQueueFactory createFactory(@PluginAttribute(value = "SpinPolicy",defaultString = "WAITING") final SpinPolicy spinPolicy) {
      return new DisruptorBlockingQueueFactory(spinPolicy);
   }
}
