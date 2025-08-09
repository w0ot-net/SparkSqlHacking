package com.sun.istack;

import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentLinkedQueue;

public interface Pool {
   @NotNull
   Object take();

   void recycle(@NotNull Object var1);

   public abstract static class Impl implements Pool {
      private volatile WeakReference queue;

      protected Impl() {
      }

      @NotNull
      public final Object take() {
         T t = (T)this.getQueue().poll();
         return t == null ? this.create() : t;
      }

      public final void recycle(Object t) {
         this.getQueue().offer(t);
      }

      private ConcurrentLinkedQueue getQueue() {
         WeakReference<ConcurrentLinkedQueue<T>> q = this.queue;
         if (q != null) {
            ConcurrentLinkedQueue<T> d = (ConcurrentLinkedQueue)q.get();
            if (d != null) {
               return d;
            }
         }

         ConcurrentLinkedQueue<T> d = new ConcurrentLinkedQueue();
         this.queue = new WeakReference(d);
         return d;
      }

      @NotNull
      protected abstract Object create();
   }
}
