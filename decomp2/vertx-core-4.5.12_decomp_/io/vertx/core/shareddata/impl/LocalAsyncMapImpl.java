package io.vertx.core.shareddata.impl;

import io.vertx.core.Future;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.shareddata.AsyncMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class LocalAsyncMapImpl implements AsyncMap {
   private final VertxInternal vertx;
   private final ConcurrentMap map;

   public LocalAsyncMapImpl(VertxInternal vertx) {
      this.vertx = vertx;
      this.map = new ConcurrentHashMap();
   }

   public Future get(Object k) {
      ContextInternal ctx = this.vertx.getOrCreateContext();
      Holder<V> h = (Holder)this.map.get(k);
      return h != null && h.hasNotExpired() ? ctx.succeededFuture(h.value) : ctx.succeededFuture();
   }

   public Future put(Object k, Object v) {
      ContextInternal ctx = this.vertx.getOrCreateContext();
      Holder<V> previous = (Holder)this.map.put(k, new Holder(v));
      if (previous != null && previous.expires()) {
         this.vertx.cancelTimer(previous.timerId);
      }

      return ctx.succeededFuture();
   }

   public Future putIfAbsent(Object k, Object v) {
      ContextInternal ctx = this.vertx.getOrCreateContext();
      Holder<V> h = (Holder)this.map.putIfAbsent(k, new Holder(v));
      return ctx.succeededFuture(h == null ? null : h.value);
   }

   public Future put(Object k, Object v, long ttl) {
      ContextInternal ctx = this.vertx.getOrCreateContext();
      long timestamp = System.nanoTime();
      long timerId = this.vertx.setTimer(ttl, (l) -> this.removeIfExpired(k));
      Holder<V> previous = (Holder)this.map.put(k, new Holder(v, timerId, ttl, timestamp));
      if (previous != null && previous.expires()) {
         this.vertx.cancelTimer(previous.timerId);
      }

      return ctx.succeededFuture();
   }

   private void removeIfExpired(Object k) {
      this.map.computeIfPresent(k, (key, holder) -> holder.hasNotExpired() ? holder : null);
   }

   public Future putIfAbsent(Object k, Object v, long ttl) {
      ContextInternal ctx = this.vertx.getOrCreateContext();
      long timestamp = System.nanoTime();
      long timerId = this.vertx.setTimer(ttl, (l) -> this.removeIfExpired(k));
      Holder<V> existing = (Holder)this.map.putIfAbsent(k, new Holder(v, timerId, ttl, timestamp));
      if (existing != null) {
         this.vertx.cancelTimer(timerId);
         return ctx.succeededFuture(existing.value);
      } else {
         return ctx.succeededFuture();
      }
   }

   public Future removeIfPresent(Object k, Object v) {
      ContextInternal ctx = this.vertx.getOrCreateContext();
      AtomicBoolean result = new AtomicBoolean();
      this.map.computeIfPresent(k, (key, holder) -> {
         if (holder.value.equals(v)) {
            result.compareAndSet(false, true);
            if (holder.expires()) {
               this.vertx.cancelTimer(holder.timerId);
            }

            return null;
         } else {
            return holder;
         }
      });
      return ctx.succeededFuture(result.get());
   }

   public Future replace(Object k, Object v) {
      ContextInternal ctx = this.vertx.getOrCreateContext();
      Holder<V> previous = (Holder)this.map.replace(k, new Holder(v));
      if (previous != null) {
         if (previous.expires()) {
            this.vertx.cancelTimer(previous.timerId);
         }

         return ctx.succeededFuture(previous.value);
      } else {
         return ctx.succeededFuture();
      }
   }

   public Future replace(Object k, Object v, long ttl) {
      ContextInternal ctx = this.vertx.getOrCreateContext();
      long timestamp = System.nanoTime();
      long timerId = this.vertx.setTimer(ttl, (l) -> this.removeIfExpired(k));
      Holder<V> previous = (Holder)this.map.replace(k, new Holder(v, timerId, ttl, timestamp));
      if (previous != null) {
         if (previous.expires()) {
            this.vertx.cancelTimer(previous.timerId);
         }

         return ctx.succeededFuture(previous.value);
      } else {
         return ctx.succeededFuture();
      }
   }

   public Future replaceIfPresent(Object k, Object oldValue, Object newValue) {
      ContextInternal ctx = this.vertx.getOrCreateContext();
      Holder<V> h = new Holder(newValue);
      Holder<V> result = (Holder)this.map.computeIfPresent(k, (key, holder) -> {
         if (holder.value.equals(oldValue)) {
            if (holder.expires()) {
               this.vertx.cancelTimer(holder.timerId);
            }

            return h;
         } else {
            return holder;
         }
      });
      return ctx.succeededFuture(h == result);
   }

   public Future replaceIfPresent(Object k, Object oldValue, Object newValue, long ttl) {
      ContextInternal ctx = this.vertx.getOrCreateContext();
      long timestamp = System.nanoTime();
      long timerId = this.vertx.setTimer(ttl, (l) -> this.removeIfExpired(k));
      Holder<V> h = new Holder(newValue, timerId, ttl, timestamp);
      Holder<V> result = (Holder)this.map.computeIfPresent(k, (key, holder) -> {
         if (holder.value.equals(oldValue)) {
            if (holder.expires()) {
               this.vertx.cancelTimer(holder.timerId);
            }

            return h;
         } else {
            return holder;
         }
      });
      if (h == result) {
         return ctx.succeededFuture(true);
      } else {
         this.vertx.cancelTimer(timerId);
         return ctx.succeededFuture(false);
      }
   }

   public Future clear() {
      ContextInternal ctx = this.vertx.getOrCreateContext();
      this.map.clear();
      return ctx.succeededFuture();
   }

   public Future size() {
      ContextInternal ctx = this.vertx.getOrCreateContext();
      return ctx.succeededFuture(this.map.size());
   }

   public Future keys() {
      ContextInternal ctx = this.vertx.getOrCreateContext();
      return ctx.succeededFuture(new HashSet(this.map.keySet()));
   }

   public Future values() {
      ContextInternal ctx = this.vertx.getOrCreateContext();
      List<V> result = (List)this.map.values().stream().filter(Holder::hasNotExpired).map((h) -> h.value).collect(Collectors.toList());
      return ctx.succeededFuture(result);
   }

   public Future entries() {
      ContextInternal ctx = this.vertx.getOrCreateContext();
      Map<K, V> result = new HashMap(this.map.size());
      this.map.forEach((key, holder) -> {
         if (holder.hasNotExpired()) {
            result.put(key, holder.value);
         }

      });
      return ctx.succeededFuture(result);
   }

   public Future remove(Object k) {
      ContextInternal ctx = this.vertx.getOrCreateContext();
      Holder<V> previous = (Holder)this.map.remove(k);
      if (previous != null) {
         if (previous.expires()) {
            this.vertx.cancelTimer(previous.timerId);
         }

         return ctx.succeededFuture(previous.value);
      } else {
         return ctx.succeededFuture();
      }
   }

   private static class Holder {
      final Object value;
      final long timerId;
      final long ttl;
      final long timestamp;

      Holder(Object value) {
         Objects.requireNonNull(value);
         this.value = value;
         this.timestamp = this.ttl = this.timerId = 0L;
      }

      Holder(Object value, long timerId, long ttl, long timestamp) {
         Objects.requireNonNull(value);
         if (ttl < 1L) {
            throw new IllegalArgumentException("ttl must be positive: " + ttl);
         } else {
            this.value = value;
            this.timerId = timerId;
            this.ttl = ttl;
            this.timestamp = timestamp;
         }
      }

      boolean expires() {
         return this.ttl > 0L;
      }

      boolean hasNotExpired() {
         return !this.expires() || TimeUnit.MILLISECONDS.convert(System.nanoTime() - this.timestamp, TimeUnit.NANOSECONDS) < this.ttl;
      }

      public String toString() {
         return "Holder{value=" + this.value + ", timerId=" + this.timerId + ", ttl=" + this.ttl + ", timestamp=" + this.timestamp + '}';
      }
   }
}
