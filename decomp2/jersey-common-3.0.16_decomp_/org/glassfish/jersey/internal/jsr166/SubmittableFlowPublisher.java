package org.glassfish.jersey.internal.jsr166;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

public interface SubmittableFlowPublisher extends Flow.Publisher, AutoCloseable {
   CompletableFuture consume(Consumer var1);

   void close();

   void closeExceptionally(Throwable var1);

   long estimateMinimumDemand();

   int estimateMaximumLag();

   Throwable getClosedException();

   int getMaxBufferCapacity();

   int offer(Object var1, long var2, TimeUnit var4, BiPredicate var5);

   int offer(Object var1, BiPredicate var2);

   int submit(Object var1);

   void subscribe(Flow.Subscriber var1);
}
