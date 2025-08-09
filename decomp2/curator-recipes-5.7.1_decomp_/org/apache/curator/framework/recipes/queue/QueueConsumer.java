package org.apache.curator.framework.recipes.queue;

import org.apache.curator.framework.state.ConnectionStateListener;

public interface QueueConsumer extends ConnectionStateListener {
   void consumeMessage(Object var1) throws Exception;
}
