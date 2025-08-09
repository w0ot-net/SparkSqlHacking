package io.netty.resolver.dns;

import io.netty.channel.EventLoop;
import io.netty.util.internal.PlatformDependent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Delayed;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

abstract class Cache {
   private static final AtomicReferenceFieldUpdater FUTURE_UPDATER = AtomicReferenceFieldUpdater.newUpdater(Entries.class, ScheduledFuture.class, "expirationFuture");
   private static final ScheduledFuture CANCELLED = new ScheduledFuture() {
      public boolean cancel(boolean mayInterruptIfRunning) {
         return false;
      }

      public long getDelay(TimeUnit unit) {
         return Long.MIN_VALUE;
      }

      public int compareTo(Delayed o) {
         throw new UnsupportedOperationException();
      }

      public boolean isCancelled() {
         return true;
      }

      public boolean isDone() {
         return true;
      }

      public Object get() {
         throw new UnsupportedOperationException();
      }

      public Object get(long timeout, TimeUnit unit) {
         throw new UnsupportedOperationException();
      }
   };
   static final int MAX_SUPPORTED_TTL_SECS;
   private final ConcurrentMap resolveCache = PlatformDependent.newConcurrentHashMap();

   final void clear() {
      label16:
      while(true) {
         if (!this.resolveCache.isEmpty()) {
            Iterator<Map.Entry<String, Cache<E>.Entries>> i = this.resolveCache.entrySet().iterator();

            while(true) {
               if (!i.hasNext()) {
                  continue label16;
               }

               Map.Entry<String, Cache<E>.Entries> e = (Map.Entry)i.next();
               i.remove();
               ((Entries)e.getValue()).clearAndCancel();
            }
         }

         return;
      }
   }

   final boolean clear(String hostname) {
      Cache<E>.Entries entries = (Entries)this.resolveCache.remove(hostname);
      return entries != null && entries.clearAndCancel();
   }

   final List get(String hostname) {
      Cache<E>.Entries entries = (Entries)this.resolveCache.get(hostname);
      return entries == null ? null : (List)entries.get();
   }

   final void cache(String hostname, Object value, int ttl, EventLoop loop) {
      Cache<E>.Entries entries = (Entries)this.resolveCache.get(hostname);
      if (entries == null) {
         entries = new Entries(hostname);
         Cache<E>.Entries oldEntries = (Entries)this.resolveCache.putIfAbsent(hostname, entries);
         if (oldEntries != null) {
            entries = oldEntries;
         }
      }

      entries.add(value, ttl, loop);
   }

   final int size() {
      return this.resolveCache.size();
   }

   protected abstract boolean shouldReplaceAll(Object var1);

   protected void sortEntries(String hostname, List entries) {
   }

   protected abstract boolean equals(Object var1, Object var2);

   static {
      MAX_SUPPORTED_TTL_SECS = (int)TimeUnit.DAYS.toSeconds(730L);
   }

   private final class Entries extends AtomicReference implements Runnable {
      private final String hostname;
      volatile ScheduledFuture expirationFuture;

      Entries(String hostname) {
         super(Collections.emptyList());
         this.hostname = hostname;
      }

      void add(Object e, int ttl, EventLoop loop) {
         if (Cache.this.shouldReplaceAll(e)) {
            this.set(Collections.singletonList(e));
            this.scheduleCacheExpirationIfNeeded(ttl, loop);
         } else {
            while(true) {
               List<E> entries = (List)this.get();
               if (!entries.isEmpty()) {
                  E firstEntry = (E)entries.get(0);
                  if (Cache.this.shouldReplaceAll(firstEntry)) {
                     assert entries.size() == 1;

                     if (this.compareAndSet(entries, Collections.singletonList(e))) {
                        this.scheduleCacheExpirationIfNeeded(ttl, loop);
                        return;
                     }
                  } else {
                     List<E> newEntries = new ArrayList(entries.size() + 1);
                     int i = 0;
                     E replacedEntry = (E)null;

                     label54:
                     do {
                        E entry = (E)entries.get(i);
                        if (Cache.this.equals(e, entry)) {
                           replacedEntry = entry;
                           newEntries.add(e);
                           ++i;

                           while(true) {
                              if (i >= entries.size()) {
                                 break label54;
                              }

                              newEntries.add(entries.get(i));
                              ++i;
                           }
                        }

                        newEntries.add(entry);
                        ++i;
                     } while(i < entries.size());

                     if (replacedEntry == null) {
                        newEntries.add(e);
                     }

                     Cache.this.sortEntries(this.hostname, newEntries);
                     if (this.compareAndSet(entries, Collections.unmodifiableList(newEntries))) {
                        this.scheduleCacheExpirationIfNeeded(ttl, loop);
                        return;
                     }
                  }
               } else if (this.compareAndSet(entries, Collections.singletonList(e))) {
                  this.scheduleCacheExpirationIfNeeded(ttl, loop);
                  return;
               }
            }
         }
      }

      private void scheduleCacheExpirationIfNeeded(int ttl, EventLoop loop) {
         while(true) {
            ScheduledFuture<?> oldFuture = (ScheduledFuture)Cache.FUTURE_UPDATER.get(this);
            if (oldFuture != null && oldFuture.getDelay(TimeUnit.SECONDS) <= (long)ttl) {
               break;
            }

            ScheduledFuture<?> newFuture = loop.schedule(this, (long)ttl, TimeUnit.SECONDS);
            if (Cache.FUTURE_UPDATER.compareAndSet(this, oldFuture, newFuture)) {
               if (oldFuture != null) {
                  oldFuture.cancel(true);
               }
               break;
            }

            newFuture.cancel(true);
         }

      }

      boolean clearAndCancel() {
         List<E> entries = (List)this.getAndSet(Collections.emptyList());
         if (entries.isEmpty()) {
            return false;
         } else {
            ScheduledFuture<?> expirationFuture = (ScheduledFuture)Cache.FUTURE_UPDATER.getAndSet(this, Cache.CANCELLED);
            if (expirationFuture != null) {
               expirationFuture.cancel(false);
            }

            return true;
         }
      }

      public void run() {
         Cache.this.resolveCache.remove(this.hostname, this);
         this.clearAndCancel();
      }
   }
}
