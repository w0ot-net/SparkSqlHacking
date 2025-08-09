package org.apache.curator.framework.recipes.queue;

public interface QueuePutListener {
   void putCompleted(Object var1);

   void putMultiCompleted(MultiItem var1);
}
