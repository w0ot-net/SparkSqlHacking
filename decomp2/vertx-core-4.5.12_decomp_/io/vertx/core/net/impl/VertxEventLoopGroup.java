package io.vertx.core.net.impl;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.util.concurrent.AbstractEventExecutorGroup;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public final class VertxEventLoopGroup extends AbstractEventExecutorGroup implements EventLoopGroup {
   private int pos;
   private final List workers = new ArrayList();
   private final Set children = new Set() {
      public Iterator iterator() {
         return new EventLoopIterator(VertxEventLoopGroup.this.workers.iterator());
      }

      public int size() {
         return VertxEventLoopGroup.this.workers.size();
      }

      public boolean isEmpty() {
         return VertxEventLoopGroup.this.workers.isEmpty();
      }

      public boolean contains(Object o) {
         return VertxEventLoopGroup.this.workers.contains(o);
      }

      public Object[] toArray() {
         return VertxEventLoopGroup.this.workers.toArray();
      }

      public Object[] toArray(Object[] a) {
         return VertxEventLoopGroup.this.workers.toArray(a);
      }

      public boolean add(EventExecutor eventExecutor) {
         throw new UnsupportedOperationException();
      }

      public boolean remove(Object o) {
         throw new UnsupportedOperationException();
      }

      public boolean containsAll(Collection c) {
         return VertxEventLoopGroup.this.workers.containsAll(c);
      }

      public boolean addAll(Collection c) {
         throw new UnsupportedOperationException();
      }

      public boolean retainAll(Collection c) {
         throw new UnsupportedOperationException();
      }

      public boolean removeAll(Collection c) {
         throw new UnsupportedOperationException();
      }

      public void clear() {
         throw new UnsupportedOperationException();
      }
   };

   public synchronized EventLoop next() {
      if (this.workers.isEmpty()) {
         throw new IllegalStateException();
      } else {
         EventLoop worker = ((EventLoopHolder)this.workers.get(this.pos)).worker;
         ++this.pos;
         this.checkPos();
         return worker;
      }
   }

   public Iterator iterator() {
      return this.children.iterator();
   }

   public ChannelFuture register(Channel channel) {
      return this.next().register(channel);
   }

   public ChannelFuture register(Channel channel, ChannelPromise promise) {
      return this.next().register(channel, promise);
   }

   public ChannelFuture register(ChannelPromise promise) {
      return this.next().register(promise);
   }

   public boolean isShutdown() {
      return false;
   }

   public boolean isTerminated() {
      return this.isShutdown();
   }

   public synchronized boolean awaitTermination(long timeout, TimeUnit unit) {
      return false;
   }

   public synchronized void addWorker(EventLoop worker) {
      EventLoopHolder holder = this.findHolder(worker);
      if (holder == null) {
         this.workers.add(new EventLoopHolder(worker));
      } else {
         ++holder.count;
      }

   }

   public synchronized void shutdown() {
      throw new UnsupportedOperationException("Should never be called");
   }

   public boolean isShuttingDown() {
      return false;
   }

   public Future shutdownGracefully(long quietPeriod, long timeout, TimeUnit unit) {
      throw new UnsupportedOperationException("Should never be called");
   }

   public Future terminationFuture() {
      throw new UnsupportedOperationException("Should never be called");
   }

   private EventLoopHolder findHolder(EventLoop worker) {
      EventLoopHolder wh = new EventLoopHolder(worker);

      for(EventLoopHolder holder : this.workers) {
         if (holder.equals(wh)) {
            return holder;
         }
      }

      return null;
   }

   public synchronized void removeWorker(EventLoop worker) {
      EventLoopHolder holder = this.findHolder(worker);
      if (holder != null) {
         --holder.count;
         if (holder.count == 0) {
            this.workers.remove(holder);
         }

         this.checkPos();
      } else {
         throw new IllegalStateException("Can't find worker to remove");
      }
   }

   public synchronized int workerCount() {
      return this.workers.size();
   }

   private void checkPos() {
      if (this.pos == this.workers.size()) {
         this.pos = 0;
      }

   }

   private static class EventLoopHolder {
      int count = 1;
      final EventLoop worker;

      EventLoopHolder(EventLoop worker) {
         this.worker = worker;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            EventLoopHolder that = (EventLoopHolder)o;
            return Objects.equals(this.worker, that.worker);
         } else {
            return false;
         }
      }

      public int hashCode() {
         return Objects.hashCode(this.worker);
      }
   }

   private static final class EventLoopIterator implements Iterator {
      private final Iterator holderIt;

      public EventLoopIterator(Iterator holderIt) {
         this.holderIt = holderIt;
      }

      public boolean hasNext() {
         return this.holderIt.hasNext();
      }

      public EventExecutor next() {
         return ((EventLoopHolder)this.holderIt.next()).worker;
      }

      public void remove() {
         throw new UnsupportedOperationException("read-only");
      }
   }
}
