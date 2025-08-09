package shaded.parquet.it.unimi.dsi.fastutil.longs;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.LongToIntFunction;

public final class Long2IntFunctions {
   public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();

   private Long2IntFunctions() {
   }

   public static Long2IntFunction singleton(long key, int value) {
      return new Singleton(key, value);
   }

   public static Long2IntFunction singleton(Long key, Integer value) {
      return new Singleton(key, value);
   }

   public static Long2IntFunction synchronize(Long2IntFunction f) {
      return new SynchronizedFunction(f);
   }

   public static Long2IntFunction synchronize(Long2IntFunction f, Object sync) {
      return new SynchronizedFunction(f, sync);
   }

   public static Long2IntFunction unmodifiable(Long2IntFunction f) {
      return new UnmodifiableFunction(f);
   }

   public static Long2IntFunction primitive(Function f) {
      Objects.requireNonNull(f);
      if (f instanceof Long2IntFunction) {
         return (Long2IntFunction)f;
      } else if (f instanceof LongToIntFunction) {
         LongToIntFunction var10000 = (LongToIntFunction)f;
         Objects.requireNonNull((LongToIntFunction)f);
         return var10000::applyAsInt;
      } else {
         return new PrimitiveFunction(f);
      }
   }

   public static class EmptyFunction extends AbstractLong2IntFunction implements Serializable, Cloneable {
      private static final long serialVersionUID = -7046029254386353129L;

      protected EmptyFunction() {
      }

      public int get(long k) {
         return 0;
      }

      public int getOrDefault(long k, int defaultValue) {
         return defaultValue;
      }

      public boolean containsKey(long k) {
         return false;
      }

      public int defaultReturnValue() {
         return 0;
      }

      public void defaultReturnValue(int defRetValue) {
         throw new UnsupportedOperationException();
      }

      public int size() {
         return 0;
      }

      public void clear() {
      }

      public Object clone() {
         return Long2IntFunctions.EMPTY_FUNCTION;
      }

      public int hashCode() {
         return 0;
      }

      public boolean equals(Object o) {
         if (!(o instanceof shaded.parquet.it.unimi.dsi.fastutil.Function)) {
            return false;
         } else {
            return ((shaded.parquet.it.unimi.dsi.fastutil.Function)o).size() == 0;
         }
      }

      public String toString() {
         return "{}";
      }

      private Object readResolve() {
         return Long2IntFunctions.EMPTY_FUNCTION;
      }
   }

   public static class Singleton extends AbstractLong2IntFunction implements Serializable, Cloneable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final long key;
      protected final int value;

      protected Singleton(long key, int value) {
         this.key = key;
         this.value = value;
      }

      public boolean containsKey(long k) {
         return this.key == k;
      }

      public int get(long k) {
         return this.key == k ? this.value : this.defRetValue;
      }

      public int getOrDefault(long k, int defaultValue) {
         return this.key == k ? this.value : defaultValue;
      }

      public int size() {
         return 1;
      }

      public Object clone() {
         return this;
      }
   }

   public static class SynchronizedFunction implements Long2IntFunction, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final Long2IntFunction function;
      protected final Object sync;

      protected SynchronizedFunction(Long2IntFunction f, Object sync) {
         if (f == null) {
            throw new NullPointerException();
         } else {
            this.function = f;
            this.sync = sync;
         }
      }

      protected SynchronizedFunction(Long2IntFunction f) {
         if (f == null) {
            throw new NullPointerException();
         } else {
            this.function = f;
            this.sync = this;
         }
      }

      public int applyAsInt(long operand) {
         synchronized(this.sync) {
            return this.function.applyAsInt(operand);
         }
      }

      /** @deprecated */
      @Deprecated
      public Integer apply(Long key) {
         synchronized(this.sync) {
            return (Integer)this.function.apply(key);
         }
      }

      public int size() {
         synchronized(this.sync) {
            return this.function.size();
         }
      }

      public int defaultReturnValue() {
         synchronized(this.sync) {
            return this.function.defaultReturnValue();
         }
      }

      public void defaultReturnValue(int defRetValue) {
         synchronized(this.sync) {
            this.function.defaultReturnValue(defRetValue);
         }
      }

      public boolean containsKey(long k) {
         synchronized(this.sync) {
            return this.function.containsKey(k);
         }
      }

      /** @deprecated */
      @Deprecated
      public boolean containsKey(Object k) {
         synchronized(this.sync) {
            return this.function.containsKey(k);
         }
      }

      public int put(long k, int v) {
         synchronized(this.sync) {
            return this.function.put(k, v);
         }
      }

      public int get(long k) {
         synchronized(this.sync) {
            return this.function.get(k);
         }
      }

      public int getOrDefault(long k, int defaultValue) {
         synchronized(this.sync) {
            return this.function.getOrDefault(k, defaultValue);
         }
      }

      public int remove(long k) {
         synchronized(this.sync) {
            return this.function.remove(k);
         }
      }

      public void clear() {
         synchronized(this.sync) {
            this.function.clear();
         }
      }

      /** @deprecated */
      @Deprecated
      public Integer put(Long k, Integer v) {
         synchronized(this.sync) {
            return this.function.put(k, v);
         }
      }

      /** @deprecated */
      @Deprecated
      public Integer get(Object k) {
         synchronized(this.sync) {
            return this.function.get(k);
         }
      }

      /** @deprecated */
      @Deprecated
      public Integer getOrDefault(Object k, Integer defaultValue) {
         synchronized(this.sync) {
            return this.function.getOrDefault(k, defaultValue);
         }
      }

      /** @deprecated */
      @Deprecated
      public Integer remove(Object k) {
         synchronized(this.sync) {
            return this.function.remove(k);
         }
      }

      public int hashCode() {
         synchronized(this.sync) {
            return this.function.hashCode();
         }
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else {
            synchronized(this.sync) {
               return this.function.equals(o);
            }
         }
      }

      public String toString() {
         synchronized(this.sync) {
            return this.function.toString();
         }
      }

      private void writeObject(ObjectOutputStream s) throws IOException {
         synchronized(this.sync) {
            s.defaultWriteObject();
         }
      }
   }

   public static class UnmodifiableFunction extends AbstractLong2IntFunction implements Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final Long2IntFunction function;

      protected UnmodifiableFunction(Long2IntFunction f) {
         if (f == null) {
            throw new NullPointerException();
         } else {
            this.function = f;
         }
      }

      public int size() {
         return this.function.size();
      }

      public int defaultReturnValue() {
         return this.function.defaultReturnValue();
      }

      public void defaultReturnValue(int defRetValue) {
         throw new UnsupportedOperationException();
      }

      public boolean containsKey(long k) {
         return this.function.containsKey(k);
      }

      public int put(long k, int v) {
         throw new UnsupportedOperationException();
      }

      public int get(long k) {
         return this.function.get(k);
      }

      public int getOrDefault(long k, int defaultValue) {
         return this.function.getOrDefault(k, defaultValue);
      }

      public int remove(long k) {
         throw new UnsupportedOperationException();
      }

      public void clear() {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Integer put(Long k, Integer v) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Integer get(Object k) {
         return this.function.get(k);
      }

      /** @deprecated */
      @Deprecated
      public Integer getOrDefault(Object k, Integer defaultValue) {
         return this.function.getOrDefault(k, defaultValue);
      }

      /** @deprecated */
      @Deprecated
      public Integer remove(Object k) {
         throw new UnsupportedOperationException();
      }

      public int hashCode() {
         return this.function.hashCode();
      }

      public boolean equals(Object o) {
         return o == this || this.function.equals(o);
      }

      public String toString() {
         return this.function.toString();
      }
   }

   public static class PrimitiveFunction implements Long2IntFunction {
      protected final Function function;

      protected PrimitiveFunction(Function function) {
         this.function = function;
      }

      public boolean containsKey(long key) {
         return this.function.apply(key) != null;
      }

      /** @deprecated */
      @Deprecated
      public boolean containsKey(Object key) {
         if (key == null) {
            return false;
         } else {
            return this.function.apply((Long)key) != null;
         }
      }

      public int get(long key) {
         Integer v = (Integer)this.function.apply(key);
         return v == null ? this.defaultReturnValue() : v;
      }

      public int getOrDefault(long key, int defaultValue) {
         Integer v = (Integer)this.function.apply(key);
         return v == null ? defaultValue : v;
      }

      /** @deprecated */
      @Deprecated
      public Integer get(Object key) {
         return key == null ? null : (Integer)this.function.apply((Long)key);
      }

      /** @deprecated */
      @Deprecated
      public Integer getOrDefault(Object key, Integer defaultValue) {
         if (key == null) {
            return defaultValue;
         } else {
            Integer v;
            return (v = (Integer)this.function.apply((Long)key)) == null ? defaultValue : v;
         }
      }

      /** @deprecated */
      @Deprecated
      public Integer put(Long key, Integer value) {
         throw new UnsupportedOperationException();
      }
   }
}
