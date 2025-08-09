package io.vertx.core.eventbus.impl.clustered;

import io.vertx.core.AsyncResult;
import io.vertx.core.Closeable;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.Message;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;

public class Serializer implements Closeable {
   private final ContextInternal ctx;
   private final Map queues;

   private Serializer(ContextInternal context) {
      ContextInternal unwrapped = context.unwrap();
      if (unwrapped.isEventLoopContext()) {
         this.ctx = unwrapped;
      } else {
         VertxInternal vertx = unwrapped.owner();
         this.ctx = vertx.createEventLoopContext(unwrapped.nettyEventLoop(), unwrapped.workerPool(), unwrapped.classLoader());
      }

      this.queues = new HashMap();
      if (unwrapped.isDeployment()) {
         unwrapped.addCloseHook(this);
      }

   }

   public static Serializer get(ContextInternal context) {
      ConcurrentMap<Object, Object> contextData = context.contextData();
      Serializer serializer = (Serializer)contextData.get(Serializer.class);
      if (serializer == null) {
         Serializer candidate = new Serializer(context);
         Serializer previous = (Serializer)contextData.putIfAbsent(Serializer.class, candidate);
         if (previous == null) {
            serializer = candidate;
         } else {
            serializer = previous;
         }
      }

      return serializer;
   }

   public void queue(Message message, BiConsumer selectHandler, Promise promise) {
      this.ctx.emit((v) -> {
         String address = message.address();
         SerializerQueue queue = (SerializerQueue)this.queues.computeIfAbsent(address, (x$0) -> new SerializerQueue(x$0));
         queue.add(message, selectHandler, promise);
      });
   }

   public void close(Promise completion) {
      this.ctx.emit((v) -> {
         for(SerializerQueue queue : this.queues.values()) {
            queue.close();
         }

         completion.complete();
      });
   }

   private class SerializerQueue {
      private final Queue tasks;
      private final String address;
      private boolean running;
      private boolean closed;

      SerializerQueue(String address) {
         this.address = address;
         this.tasks = new LinkedList();
      }

      void checkPending() {
         if (!this.running) {
            this.running = true;

            SerializedTask<?> task;
            do {
               task = (SerializedTask)this.tasks.peek();
               if (task == null) {
                  Serializer.this.queues.remove(this.address);
                  break;
               }

               task.process();
            } while(this.tasks.peek() != task);

            this.running = false;
         }

      }

      void add(Message msg, BiConsumer selectHandler, Promise promise) {
         SerializedTask<U> serializedTask = new SerializedTask(Serializer.this.ctx, msg, selectHandler);
         Future<U> fut = serializedTask.internalPromise.future();
         fut.onComplete(promise);
         fut.onComplete(serializedTask);
         this.tasks.add(serializedTask);
         this.checkPending();
      }

      void processed() {
         if (!this.closed) {
            this.tasks.poll();
            this.checkPending();
         }

      }

      void close() {
         this.closed = true;

         while(!this.tasks.isEmpty()) {
            ((SerializedTask)this.tasks.remove()).internalPromise.tryFail("Context is closing");
         }

      }

      private class SerializedTask implements Handler {
         final Message msg;
         final BiConsumer selectHandler;
         final Promise internalPromise;

         SerializedTask(ContextInternal context, Message msg, BiConsumer selectHandler) {
            this.msg = msg;
            this.selectHandler = selectHandler;
            this.internalPromise = context.promise();
         }

         void process() {
            this.selectHandler.accept(this.msg, this.internalPromise);
         }

         public void handle(AsyncResult ar) {
            SerializerQueue.this.processed();
         }
      }
   }
}
