package io.vertx.core.shareddata.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.impl.Arguments;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.shareddata.AsyncMap;
import io.vertx.core.shareddata.Counter;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.core.shareddata.Lock;
import io.vertx.core.shareddata.SharedData;
import io.vertx.core.spi.cluster.ClusterManager;
import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SharedDataImpl implements SharedData {
   private static final long DEFAULT_LOCK_TIMEOUT = 10000L;
   private final VertxInternal vertx;
   private final ClusterManager clusterManager;
   private final LocalAsyncLocks localAsyncLocks;
   private final ConcurrentMap localAsyncMaps = new ConcurrentHashMap();
   private final ConcurrentMap localCounters = new ConcurrentHashMap();
   private final ConcurrentMap localMaps = new ConcurrentHashMap();

   public SharedDataImpl(VertxInternal vertx, ClusterManager clusterManager) {
      this.vertx = vertx;
      this.clusterManager = clusterManager;
      this.localAsyncLocks = new LocalAsyncLocks();
   }

   public void getClusterWideMap(String name, Handler resultHandler) {
      Objects.requireNonNull(resultHandler, "resultHandler");
      this.getClusterWideMap(name).onComplete(resultHandler);
   }

   public Future getClusterWideMap(String name) {
      Objects.requireNonNull(name, "name");
      if (this.clusterManager == null) {
         throw new IllegalStateException("Can't get cluster wide map if not clustered");
      } else {
         Promise<AsyncMap<K, V>> promise = this.vertx.promise();
         this.clusterManager.getAsyncMap(name, promise);
         return promise.future().map(WrappedAsyncMap::new);
      }
   }

   public void getAsyncMap(String name, Handler resultHandler) {
      Objects.requireNonNull(resultHandler, "resultHandler");
      this.getAsyncMap(name).onComplete(resultHandler);
   }

   public Future getAsyncMap(String name) {
      Objects.requireNonNull(name, "name");
      if (this.clusterManager == null) {
         return this.getLocalAsyncMap(name);
      } else {
         Promise<AsyncMap<K, V>> promise = this.vertx.promise();
         this.clusterManager.getAsyncMap(name, promise);
         return promise.future().map(WrappedAsyncMap::new);
      }
   }

   public void getLock(String name, Handler resultHandler) {
      this.getLockWithTimeout(name, 10000L, resultHandler);
   }

   public Future getLock(String name) {
      return this.getLockWithTimeout(name, 10000L);
   }

   public void getLockWithTimeout(String name, long timeout, Handler resultHandler) {
      Objects.requireNonNull(resultHandler, "resultHandler");
      this.getLockWithTimeout(name, timeout).onComplete(resultHandler);
   }

   public Future getLockWithTimeout(String name, long timeout) {
      Objects.requireNonNull(name, "name");
      Arguments.require(timeout >= 0L, "timeout must be >= 0");
      if (this.clusterManager == null) {
         return this.getLocalLockWithTimeout(name, timeout);
      } else {
         Promise<Lock> promise = this.vertx.promise();
         this.clusterManager.getLockWithTimeout(name, timeout, promise);
         return promise.future();
      }
   }

   public void getLocalLock(String name, Handler resultHandler) {
      this.getLocalLockWithTimeout(name, 10000L, resultHandler);
   }

   public Future getLocalLock(String name) {
      return this.getLocalLockWithTimeout(name, 10000L);
   }

   public void getLocalLockWithTimeout(String name, long timeout, Handler resultHandler) {
      Objects.requireNonNull(resultHandler, "resultHandler");
      this.getLocalLockWithTimeout(name, timeout).onComplete(resultHandler);
   }

   public Future getLocalLockWithTimeout(String name, long timeout) {
      Objects.requireNonNull(name, "name");
      Arguments.require(timeout >= 0L, "timeout must be >= 0");
      return this.localAsyncLocks.acquire(this.vertx.getOrCreateContext(), name, timeout);
   }

   public Future getCounter(String name) {
      Objects.requireNonNull(name, "name");
      if (this.clusterManager == null) {
         return this.getLocalCounter(name);
      } else {
         Promise<Counter> promise = this.vertx.promise();
         this.clusterManager.getCounter(name, promise);
         return promise.future();
      }
   }

   public void getCounter(String name, Handler resultHandler) {
      Objects.requireNonNull(resultHandler, "resultHandler");
      this.getCounter(name).onComplete(resultHandler);
   }

   public LocalMap getLocalMap(String name) {
      return (LocalMap)this.localMaps.computeIfAbsent(name, (n) -> new LocalMapImpl(n, this.localMaps));
   }

   public void getLocalAsyncMap(String name, Handler resultHandler) {
      Objects.requireNonNull(resultHandler, "resultHandler");
      this.getLocalAsyncMap(name).onComplete(resultHandler);
   }

   public Future getLocalAsyncMap(String name) {
      LocalAsyncMapImpl<K, V> asyncMap = (LocalAsyncMapImpl)this.localAsyncMaps.computeIfAbsent(name, (n) -> new LocalAsyncMapImpl(this.vertx));
      ContextInternal context = this.vertx.getOrCreateContext();
      return context.succeededFuture(new WrappedAsyncMap(asyncMap));
   }

   public void getLocalCounter(String name, Handler resultHandler) {
      Objects.requireNonNull(resultHandler, "resultHandler");
      this.getLocalCounter(name).onComplete(resultHandler);
   }

   public Future getLocalCounter(String name) {
      Counter counter = (Counter)this.localCounters.computeIfAbsent(name, (n) -> new AsynchronousCounter(this.vertx));
      ContextInternal context = this.vertx.getOrCreateContext();
      return context.succeededFuture(counter);
   }

   private static void checkType(Object obj) {
      if (obj == null) {
         throw new IllegalArgumentException("Cannot put null in key or value of async map");
      } else if (!(obj instanceof Serializable) && !(obj instanceof ClusterSerializable)) {
         throw new IllegalArgumentException("Invalid type: " + obj.getClass().getName() + " to put in async map");
      }
   }

   public static final class WrappedAsyncMap implements AsyncMap {
      private final AsyncMap delegate;

      WrappedAsyncMap(AsyncMap other) {
         this.delegate = other;
      }

      public Future get(Object k) {
         SharedDataImpl.checkType(k);
         return this.delegate.get(k);
      }

      public Future put(Object k, Object v) {
         SharedDataImpl.checkType(k);
         SharedDataImpl.checkType(v);
         return this.delegate.put(k, v);
      }

      public Future put(Object k, Object v, long ttl) {
         SharedDataImpl.checkType(k);
         SharedDataImpl.checkType(v);
         return this.delegate.put(k, v, ttl);
      }

      public Future putIfAbsent(Object k, Object v) {
         SharedDataImpl.checkType(k);
         SharedDataImpl.checkType(v);
         return this.delegate.putIfAbsent(k, v);
      }

      public Future putIfAbsent(Object k, Object v, long ttl) {
         SharedDataImpl.checkType(k);
         SharedDataImpl.checkType(v);
         return this.delegate.putIfAbsent(k, v, ttl);
      }

      public Future remove(Object k) {
         SharedDataImpl.checkType(k);
         return this.delegate.remove(k);
      }

      public Future removeIfPresent(Object k, Object v) {
         SharedDataImpl.checkType(k);
         SharedDataImpl.checkType(v);
         return this.delegate.removeIfPresent(k, v);
      }

      public Future replace(Object k, Object v) {
         SharedDataImpl.checkType(k);
         SharedDataImpl.checkType(v);
         return this.delegate.replace(k, v);
      }

      public Future replace(Object k, Object v, long ttl) {
         SharedDataImpl.checkType(k);
         SharedDataImpl.checkType(v);
         return this.delegate.replace(k, v, ttl);
      }

      public Future replaceIfPresent(Object k, Object oldValue, Object newValue) {
         SharedDataImpl.checkType(k);
         SharedDataImpl.checkType(oldValue);
         SharedDataImpl.checkType(newValue);
         return this.delegate.replaceIfPresent(k, oldValue, newValue);
      }

      public Future replaceIfPresent(Object k, Object oldValue, Object newValue, long ttl) {
         SharedDataImpl.checkType(k);
         SharedDataImpl.checkType(oldValue);
         SharedDataImpl.checkType(newValue);
         return this.delegate.replaceIfPresent(k, oldValue, newValue, ttl);
      }

      public Future clear() {
         return this.delegate.clear();
      }

      public Future size() {
         return this.delegate.size();
      }

      public Future keys() {
         return this.delegate.keys();
      }

      public Future values() {
         return this.delegate.values();
      }

      public Future entries() {
         return this.delegate.entries();
      }

      public AsyncMap getDelegate() {
         return this.delegate;
      }
   }
}
