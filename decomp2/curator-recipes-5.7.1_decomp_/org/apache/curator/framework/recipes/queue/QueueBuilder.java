package org.apache.curator.framework.recipes.queue;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.util.concurrent.MoreExecutors;
import org.apache.curator.utils.PathUtils;
import org.apache.curator.utils.ThreadUtils;

public class QueueBuilder {
   private final CuratorFramework client;
   private final QueueConsumer consumer;
   private final QueueSerializer serializer;
   private final String queuePath;
   private ThreadFactory factory;
   private Executor executor;
   private String lockPath;
   private int maxItems = Integer.MAX_VALUE;
   private boolean putInBackground = true;
   private int finalFlushMs = 5000;
   static final ThreadFactory defaultThreadFactory = ThreadUtils.newThreadFactory("QueueBuilder");
   static final int NOT_SET = Integer.MAX_VALUE;

   public static QueueBuilder builder(CuratorFramework client, QueueConsumer consumer, QueueSerializer serializer, String queuePath) {
      return new QueueBuilder(client, consumer, serializer, queuePath);
   }

   public DistributedQueue buildQueue() {
      return new DistributedQueue(this.client, this.consumer, this.serializer, this.queuePath, this.factory, this.executor, Integer.MAX_VALUE, false, this.lockPath, this.maxItems, this.putInBackground, this.finalFlushMs);
   }

   public DistributedIdQueue buildIdQueue() {
      return new DistributedIdQueue(this.client, this.consumer, this.serializer, this.queuePath, this.factory, this.executor, Integer.MAX_VALUE, false, this.lockPath, this.maxItems, this.putInBackground, this.finalFlushMs);
   }

   public DistributedPriorityQueue buildPriorityQueue(int minItemsBeforeRefresh) {
      return new DistributedPriorityQueue(this.client, this.consumer, this.serializer, this.queuePath, this.factory, this.executor, minItemsBeforeRefresh, this.lockPath, this.maxItems, this.putInBackground, this.finalFlushMs);
   }

   public DistributedDelayQueue buildDelayQueue() {
      return new DistributedDelayQueue(this.client, this.consumer, this.serializer, this.queuePath, this.factory, this.executor, Integer.MAX_VALUE, this.lockPath, this.maxItems, this.putInBackground, this.finalFlushMs);
   }

   public QueueBuilder threadFactory(ThreadFactory factory) {
      Preconditions.checkNotNull(factory, "factory cannot be null");
      this.factory = factory;
      return this;
   }

   public QueueBuilder executor(Executor executor) {
      Preconditions.checkNotNull(executor, "executor cannot be null");
      this.executor = executor;
      return this;
   }

   public QueueBuilder lockPath(String path) {
      this.lockPath = PathUtils.validatePath(path);
      return this;
   }

   public QueueBuilder maxItems(int maxItems) {
      this.maxItems = maxItems;
      this.putInBackground = false;
      return this;
   }

   public QueueBuilder putInBackground(boolean putInBackground) {
      this.putInBackground = putInBackground;
      return this;
   }

   public QueueBuilder finalFlushTime(int time, TimeUnit unit) {
      this.finalFlushMs = (int)unit.toMillis((long)time);
      return this;
   }

   private QueueBuilder(CuratorFramework client, QueueConsumer consumer, QueueSerializer serializer, String queuePath) {
      this.client = client;
      this.consumer = consumer;
      this.serializer = serializer;
      this.queuePath = PathUtils.validatePath(queuePath);
      this.factory = defaultThreadFactory;
      this.executor = MoreExecutors.directExecutor();
   }
}
