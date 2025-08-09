package io.vertx.core.net.impl;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoop;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.EventExecutor;
import io.vertx.core.Handler;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

class ServerChannelLoadBalancer extends ChannelInitializer {
   private final VertxEventLoopGroup workers = new VertxEventLoopGroup();
   private final ConcurrentMap workerMap = new ConcurrentHashMap();
   private final ChannelGroup channelGroup;
   private volatile boolean hasHandlers;

   ServerChannelLoadBalancer(EventExecutor executor) {
      this.channelGroup = new DefaultChannelGroup(executor, true);
   }

   public VertxEventLoopGroup workers() {
      return this.workers;
   }

   public boolean hasHandlers() {
      return this.hasHandlers;
   }

   protected void initChannel(Channel ch) {
      Handler<Channel> handler = this.chooseInitializer(ch.eventLoop());
      if (handler == null) {
         ch.close();
      } else {
         this.channelGroup.add(ch);
         handler.handle(ch);
      }

   }

   private Handler chooseInitializer(EventLoop worker) {
      WorkerList handlers = (WorkerList)this.workerMap.get(worker);
      return handlers == null ? null : handlers.chooseHandler();
   }

   public synchronized void addWorker(EventLoop eventLoop, Handler handler) {
      this.workers.addWorker(eventLoop);
      WorkerList handlers = new WorkerList();
      WorkerList prev = (WorkerList)this.workerMap.putIfAbsent(eventLoop, handlers);
      if (prev != null) {
         handlers = prev;
      }

      handlers.addWorker(handler);
      this.hasHandlers = true;
   }

   public synchronized boolean removeWorker(EventLoop worker, Handler handler) {
      WorkerList handlers = (WorkerList)this.workerMap.get(worker);
      if (handlers != null && handlers.removeWorker(handler)) {
         if (handlers.isEmpty()) {
            this.workerMap.remove(worker);
         }

         if (this.workerMap.isEmpty()) {
            this.hasHandlers = false;
         }

         this.workers.removeWorker(worker);
         return true;
      } else {
         return false;
      }
   }

   public void close() {
      this.channelGroup.close();
   }

   private static final class WorkerList {
      private int pos;
      private final List list;

      private WorkerList() {
         this.list = new CopyOnWriteArrayList();
      }

      Handler chooseHandler() {
         Handler<Channel> handler = (Handler)this.list.get(this.pos);
         ++this.pos;
         this.checkPos();
         return handler;
      }

      void addWorker(Handler handler) {
         this.list.add(handler);
      }

      boolean removeWorker(Handler handler) {
         if (this.list.remove(handler)) {
            this.checkPos();
            return true;
         } else {
            return false;
         }
      }

      boolean isEmpty() {
         return this.list.isEmpty();
      }

      void checkPos() {
         if (this.pos == this.list.size()) {
            this.pos = 0;
         }

      }
   }
}
