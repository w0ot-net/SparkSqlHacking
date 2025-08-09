package com.esotericsoftware.kryo.pool;

import com.esotericsoftware.kryo.Kryo;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public interface KryoPool {
   Kryo borrow();

   void release(Kryo var1);

   Object run(KryoCallback var1);

   public static class Builder {
      private final KryoFactory factory;
      private Queue queue = new ConcurrentLinkedQueue();
      private boolean softReferences;

      public Builder(KryoFactory factory) {
         if (factory == null) {
            throw new IllegalArgumentException("factory must not be null");
         } else {
            this.factory = factory;
         }
      }

      public Builder queue(Queue queue) {
         if (queue == null) {
            throw new IllegalArgumentException("queue must not be null");
         } else {
            this.queue = queue;
            return this;
         }
      }

      public Builder softReferences() {
         this.softReferences = true;
         return this;
      }

      public KryoPool build() {
         Queue<Kryo> q = (Queue<Kryo>)(this.softReferences ? new SoftReferenceQueue(this.queue) : this.queue);
         return new KryoPoolQueueImpl(this.factory, q);
      }

      public String toString() {
         return this.getClass().getName() + "[queue.class=" + this.queue.getClass() + ", softReferences=" + this.softReferences + "]";
      }
   }
}
