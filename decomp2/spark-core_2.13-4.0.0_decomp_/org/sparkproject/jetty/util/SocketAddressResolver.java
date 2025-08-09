package org.sparkproject.jetty.util;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.thread.Scheduler;

public interface SocketAddressResolver {
   void resolve(String var1, int var2, Promise var3);

   @ManagedObject("The synchronous address resolver")
   public static class Sync implements SocketAddressResolver {
      public void resolve(String host, int port, Promise promise) {
         try {
            InetAddress[] addresses = InetAddress.getAllByName(host);
            List<InetSocketAddress> result = new ArrayList(addresses.length);

            for(InetAddress address : addresses) {
               result.add(new InetSocketAddress(address, port));
            }

            if (result.isEmpty()) {
               promise.failed(new UnknownHostException());
            } else {
               promise.succeeded(result);
            }
         } catch (Throwable x) {
            promise.failed(x);
         }

      }
   }

   @ManagedObject("The asynchronous address resolver")
   public static class Async implements SocketAddressResolver {
      private static final Logger LOG = LoggerFactory.getLogger(SocketAddressResolver.class);
      private final Executor executor;
      private final Scheduler scheduler;
      private final long timeout;

      public Async(Executor executor, Scheduler scheduler, long timeout) {
         this.executor = executor;
         this.scheduler = scheduler;
         this.timeout = timeout;
      }

      public Executor getExecutor() {
         return this.executor;
      }

      public Scheduler getScheduler() {
         return this.scheduler;
      }

      @ManagedAttribute(
         value = "The timeout, in milliseconds, to resolve an address",
         readonly = true
      )
      public long getTimeout() {
         return this.timeout;
      }

      public void resolve(String host, int port, Promise promise) {
         this.executor.execute(() -> {
            Scheduler.Task task = null;
            AtomicBoolean complete = new AtomicBoolean();
            if (this.timeout > 0L) {
               Thread thread = Thread.currentThread();
               task = this.scheduler.schedule(() -> {
                  if (complete.compareAndSet(false, true)) {
                     promise.failed(new TimeoutException("DNS timeout " + this.getTimeout() + " ms"));
                     thread.interrupt();
                  }

               }, this.timeout, TimeUnit.MILLISECONDS);
            }

            try {
               long start = NanoTime.now();
               InetAddress[] addresses = InetAddress.getAllByName(host);
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Resolved {} in {} ms", host, NanoTime.millisSince(start));
               }

               List<InetSocketAddress> result = new ArrayList(addresses.length);

               for(InetAddress address : addresses) {
                  result.add(new InetSocketAddress(address, port));
               }

               if (complete.compareAndSet(false, true)) {
                  if (result.isEmpty()) {
                     promise.failed(new UnknownHostException());
                  } else {
                     promise.succeeded(result);
                  }
               }
            } catch (Throwable x) {
               if (complete.compareAndSet(false, true)) {
                  promise.failed(x);
               }
            } finally {
               if (task != null) {
                  task.cancel();
               }

            }

         });
      }
   }
}
