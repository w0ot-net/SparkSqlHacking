package org.glassfish.jersey.internal.util.collection;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/** @deprecated */
@Deprecated
public interface TransferQueue extends BlockingQueue {
   boolean tryTransfer(Object var1);

   void transfer(Object var1) throws InterruptedException;

   boolean tryTransfer(Object var1, long var2, TimeUnit var4) throws InterruptedException;

   boolean hasWaitingConsumer();

   int getWaitingConsumerCount();
}
