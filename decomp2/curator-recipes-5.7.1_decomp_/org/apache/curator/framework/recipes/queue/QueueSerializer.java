package org.apache.curator.framework.recipes.queue;

public interface QueueSerializer {
   byte[] serialize(Object var1);

   Object deserialize(byte[] var1);
}
