package org.apache.logging.log4j.util;

import java.lang.ref.WeakReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

final class LazyUtil {
   private static final Object NULL = new Object() {
      public String toString() {
         return "null";
      }
   };

   static Object wrapNull(final Object value) {
      return value == null ? NULL : value;
   }

   static Object unwrapNull(final Object value) {
      return value == NULL ? null : Cast.cast(value);
   }

   static class Constant implements Lazy {
      private final Object value;

      Constant(final Object value) {
         this.value = value;
      }

      public Object value() {
         return this.value;
      }

      public boolean isInitialized() {
         return true;
      }

      public void set(final Object newValue) {
         throw new UnsupportedOperationException();
      }

      public String toString() {
         return String.valueOf(this.value);
      }
   }

   static class WeakConstant implements Lazy {
      private final WeakReference reference;

      WeakConstant(final Object value) {
         this.reference = new WeakReference(value);
      }

      public Object value() {
         return this.reference.get();
      }

      public boolean isInitialized() {
         return true;
      }

      public void set(final Object newValue) {
         throw new UnsupportedOperationException();
      }

      public String toString() {
         return String.valueOf(this.value());
      }
   }

   static class SafeLazy implements Lazy {
      private final Lock lock = new ReentrantLock();
      private final java.util.function.Supplier supplier;
      private volatile Object value;

      SafeLazy(final java.util.function.Supplier supplier) {
         this.supplier = supplier;
      }

      public Object value() {
         Object value = this.value;
         if (value == null) {
            this.lock.lock();

            try {
               value = this.value;
               if (value == null) {
                  value = this.supplier.get();
                  this.value = LazyUtil.wrapNull(value);
               }
            } finally {
               this.lock.unlock();
            }
         }

         return LazyUtil.unwrapNull(value);
      }

      public void set(final Object newValue) {
         this.value = newValue;
      }

      public void reset() {
         this.value = null;
      }

      public boolean isInitialized() {
         return this.value != null;
      }

      public String toString() {
         return this.isInitialized() ? String.valueOf(this.value) : "Lazy value not initialized";
      }
   }

   static class PureLazy implements Lazy {
      private final java.util.function.Supplier supplier;
      private Object value;

      public PureLazy(final java.util.function.Supplier supplier) {
         this.supplier = supplier;
      }

      public Object value() {
         Object value = this.value;
         if (value == null) {
            value = this.supplier.get();
            this.value = LazyUtil.wrapNull(value);
         }

         return LazyUtil.unwrapNull(value);
      }

      public boolean isInitialized() {
         return this.value != null;
      }

      public void set(final Object newValue) {
         this.value = newValue;
      }
   }
}
