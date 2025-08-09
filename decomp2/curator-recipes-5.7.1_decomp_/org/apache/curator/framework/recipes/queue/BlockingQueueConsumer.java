package org.apache.curator.framework.recipes.queue;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.shaded.com.google.common.collect.ImmutableList;

public class BlockingQueueConsumer implements QueueConsumer {
   private final ConnectionStateListener connectionStateListener;
   private final BlockingQueue items;

   public BlockingQueueConsumer(ConnectionStateListener connectionStateListener) {
      this(connectionStateListener, new LinkedBlockingQueue());
   }

   public BlockingQueueConsumer(ConnectionStateListener connectionStateListener, int capacity) {
      this(connectionStateListener, new ArrayBlockingQueue(capacity));
   }

   public BlockingQueueConsumer(ConnectionStateListener connectionStateListener, BlockingQueue queue) {
      this.connectionStateListener = connectionStateListener;
      this.items = queue;
   }

   public void consumeMessage(Object message) throws Exception {
      this.items.add(message);
   }

   public List getItems() {
      return ImmutableList.copyOf(this.items);
   }

   public int size() {
      return this.items.size();
   }

   public Object take() throws InterruptedException {
      return this.items.take();
   }

   public Object take(int time, TimeUnit unit) throws InterruptedException {
      return this.items.poll((long)time, unit);
   }

   public int drainTo(Collection c) {
      return this.items.drainTo(c);
   }

   public void stateChanged(CuratorFramework client, ConnectionState newState) {
      this.connectionStateListener.stateChanged(client, newState);
   }
}
