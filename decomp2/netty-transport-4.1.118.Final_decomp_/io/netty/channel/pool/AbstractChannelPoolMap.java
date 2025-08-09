package io.netty.channel.pool;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.GlobalEventExecutor;
import io.netty.util.concurrent.Promise;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.ReadOnlyIterator;
import java.io.Closeable;
import java.util.Iterator;
import java.util.concurrent.ConcurrentMap;

public abstract class AbstractChannelPoolMap implements ChannelPoolMap, Iterable, Closeable {
   private final ConcurrentMap map = PlatformDependent.newConcurrentHashMap();

   public final ChannelPool get(Object key) {
      P pool = (P)((ChannelPool)this.map.get(ObjectUtil.checkNotNull(key, "key")));
      if (pool == null) {
         pool = (P)this.newPool(key);
         P old = (P)((ChannelPool)this.map.putIfAbsent(key, pool));
         if (old != null) {
            poolCloseAsyncIfSupported(pool);
            pool = old;
         }
      }

      return pool;
   }

   public final boolean remove(Object key) {
      P pool = (P)((ChannelPool)this.map.remove(ObjectUtil.checkNotNull(key, "key")));
      if (pool != null) {
         poolCloseAsyncIfSupported(pool);
         return true;
      } else {
         return false;
      }
   }

   private Future removeAsyncIfSupported(Object key) {
      P pool = (P)((ChannelPool)this.map.remove(ObjectUtil.checkNotNull(key, "key")));
      if (pool != null) {
         final Promise<Boolean> removePromise = GlobalEventExecutor.INSTANCE.newPromise();
         poolCloseAsyncIfSupported(pool).addListener(new GenericFutureListener() {
            public void operationComplete(Future future) throws Exception {
               if (future.isSuccess()) {
                  removePromise.setSuccess(Boolean.TRUE);
               } else {
                  removePromise.setFailure(future.cause());
               }

            }
         });
         return removePromise;
      } else {
         return GlobalEventExecutor.INSTANCE.newSucceededFuture(Boolean.FALSE);
      }
   }

   private static Future poolCloseAsyncIfSupported(ChannelPool pool) {
      if (pool instanceof SimpleChannelPool) {
         return ((SimpleChannelPool)pool).closeAsync();
      } else {
         try {
            pool.close();
            return GlobalEventExecutor.INSTANCE.newSucceededFuture((Object)null);
         } catch (Exception e) {
            return GlobalEventExecutor.INSTANCE.newFailedFuture(e);
         }
      }
   }

   public final Iterator iterator() {
      return new ReadOnlyIterator(this.map.entrySet().iterator());
   }

   public final int size() {
      return this.map.size();
   }

   public final boolean isEmpty() {
      return this.map.isEmpty();
   }

   public final boolean contains(Object key) {
      return this.map.containsKey(ObjectUtil.checkNotNull(key, "key"));
   }

   protected abstract ChannelPool newPool(Object var1);

   public final void close() {
      for(Object key : this.map.keySet()) {
         this.removeAsyncIfSupported(key).syncUninterruptibly();
      }

   }
}
