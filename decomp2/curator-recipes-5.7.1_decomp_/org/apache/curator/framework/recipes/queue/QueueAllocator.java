package org.apache.curator.framework.recipes.queue;

import org.apache.curator.framework.CuratorFramework;

public interface QueueAllocator {
   QueueBase allocateQueue(CuratorFramework var1, String var2);
}
