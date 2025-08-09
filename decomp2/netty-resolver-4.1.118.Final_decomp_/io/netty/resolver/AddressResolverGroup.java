package io.netty.resolver;

import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.Closeable;
import java.util.IdentityHashMap;
import java.util.Map;

public abstract class AddressResolverGroup implements Closeable {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(AddressResolverGroup.class);
   private final Map resolvers = new IdentityHashMap();
   private final Map executorTerminationListeners = new IdentityHashMap();

   protected AddressResolverGroup() {
   }

   public AddressResolver getResolver(final EventExecutor executor) {
      ObjectUtil.checkNotNull(executor, "executor");
      if (executor.isShuttingDown()) {
         throw new IllegalStateException("executor not accepting a task");
      } else {
         synchronized(this.resolvers) {
            AddressResolver<T> r = (AddressResolver)this.resolvers.get(executor);
            if (r == null) {
               final AddressResolver<T> newResolver;
               try {
                  newResolver = this.newResolver(executor);
               } catch (Exception e) {
                  throw new IllegalStateException("failed to create a new resolver", e);
               }

               this.resolvers.put(executor, newResolver);
               FutureListener<Object> terminationListener = new FutureListener() {
                  public void operationComplete(Future future) {
                     synchronized(AddressResolverGroup.this.resolvers) {
                        AddressResolverGroup.this.resolvers.remove(executor);
                        AddressResolverGroup.this.executorTerminationListeners.remove(executor);
                     }

                     newResolver.close();
                  }
               };
               this.executorTerminationListeners.put(executor, terminationListener);
               executor.terminationFuture().addListener(terminationListener);
               r = newResolver;
            }

            return r;
         }
      }
   }

   protected abstract AddressResolver newResolver(EventExecutor var1) throws Exception;

   public void close() {
      AddressResolver<T>[] rArray;
      Map.Entry<EventExecutor, GenericFutureListener<Future<Object>>>[] listeners;
      synchronized(this.resolvers) {
         rArray = (AddressResolver[])this.resolvers.values().toArray(new AddressResolver[0]);
         this.resolvers.clear();
         listeners = (Map.Entry[])this.executorTerminationListeners.entrySet().toArray(new Map.Entry[0]);
         this.executorTerminationListeners.clear();
      }

      for(Map.Entry entry : listeners) {
         ((EventExecutor)entry.getKey()).terminationFuture().removeListener((GenericFutureListener)entry.getValue());
      }

      for(AddressResolver r : rArray) {
         try {
            r.close();
         } catch (Throwable t) {
            logger.warn("Failed to close a resolver:", t);
         }
      }

   }
}
