package com.twitter.chill;

import java.util.concurrent.ArrayBlockingQueue;

public abstract class ResourcePool {
   private ArrayBlockingQueue pool;

   protected abstract Object newInstance();

   public ResourcePool(int var1) {
      this.pool = new ArrayBlockingQueue(var1);
   }

   public Object borrow() {
      try {
         Object var1 = this.pool.poll();
         return null == var1 ? this.newInstance() : var1;
      } catch (Exception var2) {
         throw new RuntimeException(var2);
      }
   }

   public void release(Object var1) {
      try {
         this.pool.offer(var1);
      } catch (Exception var3) {
         throw new RuntimeException(var3);
      }
   }
}
