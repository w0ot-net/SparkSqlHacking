package com.esotericsoftware.kryo.pool;

import com.esotericsoftware.kryo.Kryo;
import java.util.Queue;

class KryoPoolQueueImpl implements KryoPool {
   private final Queue queue;
   private final KryoFactory factory;

   KryoPoolQueueImpl(KryoFactory factory, Queue queue) {
      this.factory = factory;
      this.queue = queue;
   }

   public int size() {
      return this.queue.size();
   }

   public Kryo borrow() {
      Kryo res;
      return (res = (Kryo)this.queue.poll()) != null ? res : this.factory.create();
   }

   public void release(Kryo kryo) {
      this.queue.offer(kryo);
   }

   public Object run(KryoCallback callback) {
      Kryo kryo = this.borrow();

      Object var3;
      try {
         var3 = callback.execute(kryo);
      } finally {
         this.release(kryo);
      }

      return var3;
   }

   public void clear() {
      this.queue.clear();
   }
}
