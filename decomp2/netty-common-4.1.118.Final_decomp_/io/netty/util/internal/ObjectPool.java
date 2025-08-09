package io.netty.util.internal;

import io.netty.util.Recycler;

public abstract class ObjectPool {
   ObjectPool() {
   }

   public abstract Object get();

   public static ObjectPool newPool(ObjectCreator creator) {
      return new RecyclerObjectPool((ObjectCreator)ObjectUtil.checkNotNull(creator, "creator"));
   }

   private static final class RecyclerObjectPool extends ObjectPool {
      private final Recycler recycler;

      RecyclerObjectPool(final ObjectCreator creator) {
         this.recycler = new Recycler() {
            protected Object newObject(Recycler.Handle handle) {
               return creator.newObject(handle);
            }
         };
      }

      public Object get() {
         return this.recycler.get();
      }
   }

   public interface Handle {
      void recycle(Object var1);
   }

   public interface ObjectCreator {
      Object newObject(Handle var1);
   }
}
