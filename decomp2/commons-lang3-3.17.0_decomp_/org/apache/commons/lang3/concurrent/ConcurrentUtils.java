package org.apache.commons.lang3.concurrent;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class ConcurrentUtils {
   static Throwable checkedException(Throwable ex) {
      Validate.isTrue(ExceptionUtils.isChecked(ex), "Not a checked exception: " + ex);
      return ex;
   }

   public static Future constantFuture(Object value) {
      return new ConstantFuture(value);
   }

   public static Object createIfAbsent(ConcurrentMap map, Object key, ConcurrentInitializer init) throws ConcurrentException {
      if (map != null && init != null) {
         V value = (V)map.get(key);
         return value == null ? putIfAbsent(map, key, init.get()) : value;
      } else {
         return null;
      }
   }

   public static Object createIfAbsentUnchecked(ConcurrentMap map, Object key, ConcurrentInitializer init) {
      try {
         return createIfAbsent(map, key, init);
      } catch (ConcurrentException cex) {
         throw new ConcurrentRuntimeException(cex.getCause());
      }
   }

   public static ConcurrentException extractCause(ExecutionException ex) {
      if (ex != null && ex.getCause() != null) {
         ExceptionUtils.throwUnchecked(ex.getCause());
         return new ConcurrentException(ex.getMessage(), ex.getCause());
      } else {
         return null;
      }
   }

   public static ConcurrentRuntimeException extractCauseUnchecked(ExecutionException ex) {
      if (ex != null && ex.getCause() != null) {
         ExceptionUtils.throwUnchecked(ex.getCause());
         return new ConcurrentRuntimeException(ex.getMessage(), ex.getCause());
      } else {
         return null;
      }
   }

   public static void handleCause(ExecutionException ex) throws ConcurrentException {
      ConcurrentException cause = extractCause(ex);
      if (cause != null) {
         throw cause;
      }
   }

   public static void handleCauseUnchecked(ExecutionException ex) {
      ConcurrentRuntimeException cause = extractCauseUnchecked(ex);
      if (cause != null) {
         throw cause;
      }
   }

   public static Object initialize(ConcurrentInitializer initializer) throws ConcurrentException {
      return initializer != null ? initializer.get() : null;
   }

   public static Object initializeUnchecked(ConcurrentInitializer initializer) {
      try {
         return initialize(initializer);
      } catch (ConcurrentException cex) {
         throw new ConcurrentRuntimeException(cex.getCause());
      }
   }

   public static Object putIfAbsent(ConcurrentMap map, Object key, Object value) {
      if (map == null) {
         return null;
      } else {
         V result = (V)map.putIfAbsent(key, value);
         return result != null ? result : value;
      }
   }

   private ConcurrentUtils() {
   }

   static final class ConstantFuture implements Future {
      private final Object value;

      ConstantFuture(Object value) {
         this.value = value;
      }

      public boolean cancel(boolean mayInterruptIfRunning) {
         return false;
      }

      public Object get() {
         return this.value;
      }

      public Object get(long timeout, TimeUnit unit) {
         return this.value;
      }

      public boolean isCancelled() {
         return false;
      }

      public boolean isDone() {
         return true;
      }
   }
}
