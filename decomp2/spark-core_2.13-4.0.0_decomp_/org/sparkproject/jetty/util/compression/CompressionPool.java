package org.sparkproject.jetty.util.compression;

import java.io.Closeable;
import org.sparkproject.jetty.util.Pool;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.component.ContainerLifeCycle;

@ManagedObject
public abstract class CompressionPool extends ContainerLifeCycle {
   public static final int DEFAULT_CAPACITY = 1024;
   private int _capacity;
   private Pool _pool;

   public CompressionPool(int capacity) {
      this._capacity = capacity;
   }

   public int getCapacity() {
      return this._capacity;
   }

   public void setCapacity(int capacity) {
      if (this.isStarted()) {
         throw new IllegalStateException("Already Started");
      } else {
         this._capacity = capacity;
      }
   }

   public Pool getPool() {
      return this._pool;
   }

   protected abstract Object newPooled();

   protected abstract void end(Object var1);

   protected abstract void reset(Object var1);

   public Entry acquire() {
      CompressionPool<T>.Entry entry = null;
      if (this._pool != null) {
         Pool<CompressionPool<T>.Entry>.Entry acquiredEntry = this._pool.acquire((e) -> new Entry(this.newPooled(), e));
         if (acquiredEntry != null) {
            entry = (Entry)acquiredEntry.getPooled();
         }
      }

      return entry == null ? new Entry(this.newPooled()) : entry;
   }

   public void release(Entry entry) {
      entry.release();
   }

   protected void doStart() throws Exception {
      if (this._capacity > 0) {
         this._pool = new Pool(Pool.StrategyType.RANDOM, this._capacity, true);
         this.addBean(this._pool);
      }

      super.doStart();
   }

   public void doStop() throws Exception {
      if (this._pool != null) {
         this._pool.close();
         this.removeBean(this._pool);
         this._pool = null;
      }

      super.doStop();
   }

   public String toString() {
      return String.format("%s@%x{%s,size=%d,capacity=%s}", this.getClass().getSimpleName(), this.hashCode(), this.getState(), this._pool == null ? -1 : this._pool.size(), this._capacity);
   }

   public class Entry implements Closeable {
      private final Object _value;
      private final Pool.Entry _entry;

      Entry(Object value) {
         this(value, (Pool.Entry)null);
      }

      Entry(Object value, Pool.Entry entry) {
         this._value = value;
         this._entry = entry;
      }

      public Object get() {
         return this._value;
      }

      public void release() {
         CompressionPool.this.reset(this._value);
         if (this._entry != null) {
            if (!CompressionPool.this._pool.release(this._entry) && CompressionPool.this._pool.remove(this._entry)) {
               this.close();
            }
         } else {
            this.close();
         }

      }

      public void close() {
         CompressionPool.this.end(this._value);
      }
   }
}
