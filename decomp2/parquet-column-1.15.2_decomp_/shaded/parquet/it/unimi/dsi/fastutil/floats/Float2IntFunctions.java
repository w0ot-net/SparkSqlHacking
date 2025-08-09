package shaded.parquet.it.unimi.dsi.fastutil.floats;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;
import java.util.function.DoubleToIntFunction;
import java.util.function.Function;

public final class Float2IntFunctions {
   public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();

   private Float2IntFunctions() {
   }

   public static Float2IntFunction singleton(float key, int value) {
      return new Singleton(key, value);
   }

   public static Float2IntFunction singleton(Float key, Integer value) {
      return new Singleton(key, value);
   }

   public static Float2IntFunction synchronize(Float2IntFunction f) {
      return new SynchronizedFunction(f);
   }

   public static Float2IntFunction synchronize(Float2IntFunction f, Object sync) {
      return new SynchronizedFunction(f, sync);
   }

   public static Float2IntFunction unmodifiable(Float2IntFunction f) {
      return new UnmodifiableFunction(f);
   }

   public static Float2IntFunction primitive(Function f) {
      Objects.requireNonNull(f);
      if (f instanceof Float2IntFunction) {
         return (Float2IntFunction)f;
      } else if (f instanceof DoubleToIntFunction) {
         DoubleToIntFunction var10000 = (DoubleToIntFunction)f;
         Objects.requireNonNull((DoubleToIntFunction)f);
         return var10000::applyAsInt;
      } else {
         return new PrimitiveFunction(f);
      }
   }

   public static class EmptyFunction extends AbstractFloat2IntFunction implements Serializable, Cloneable {
      private static final long serialVersionUID = -7046029254386353129L;

      protected EmptyFunction() {
      }

      public int get(float k) {
         return 0;
      }

      public int getOrDefault(float k, int defaultValue) {
         return defaultValue;
      }

      public boolean containsKey(float k) {
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
         return Float2IntFunctions.EMPTY_FUNCTION;
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
         return Float2IntFunctions.EMPTY_FUNCTION;
      }
   }

   public static class Singleton extends AbstractFloat2IntFunction implements Serializable, Cloneable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final float key;
      protected final int value;

      protected Singleton(float key, int value) {
         this.key = key;
         this.value = value;
      }

      public boolean containsKey(float k) {
         return Float.floatToIntBits(this.key) == Float.floatToIntBits(k);
      }

      public int get(float k) {
         return Float.floatToIntBits(this.key) == Float.floatToIntBits(k) ? this.value : this.defRetValue;
      }

      public int getOrDefault(float k, int defaultValue) {
         return Float.floatToIntBits(this.key) == Float.floatToIntBits(k) ? this.value : defaultValue;
      }

      public int size() {
         return 1;
      }

      public Object clone() {
         return this;
      }
   }

   public static class SynchronizedFunction implements Float2IntFunction, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final Float2IntFunction function;
      protected final Object sync;

      protected SynchronizedFunction(Float2IntFunction f, Object sync) {
         if (f == null) {
            throw new NullPointerException();
         } else {
            this.function = f;
            this.sync = sync;
         }
      }

      protected SynchronizedFunction(Float2IntFunction f) {
         if (f == null) {
            throw new NullPointerException();
         } else {
            this.function = f;
            this.sync = this;
         }
      }

      /** @deprecated */
      @Deprecated
      public int applyAsInt(double operand) {
         synchronized(this.sync) {
            return this.function.applyAsInt(operand);
         }
      }

      /** @deprecated */
      @Deprecated
      public Integer apply(Float key) {
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

      public boolean containsKey(float k) {
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

      public int put(float k, int v) {
         synchronized(this.sync) {
            return this.function.put(k, v);
         }
      }

      public int get(float k) {
         synchronized(this.sync) {
            return this.function.get(k);
         }
      }

      public int getOrDefault(float k, int defaultValue) {
         synchronized(this.sync) {
            return this.function.getOrDefault(k, defaultValue);
         }
      }

      public int remove(float k) {
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
      public Integer put(Float k, Integer v) {
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

   public static class UnmodifiableFunction extends AbstractFloat2IntFunction implements Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final Float2IntFunction function;

      protected UnmodifiableFunction(Float2IntFunction f) {
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

      public boolean containsKey(float k) {
         return this.function.containsKey(k);
      }

      public int put(float k, int v) {
         throw new UnsupportedOperationException();
      }

      public int get(float k) {
         return this.function.get(k);
      }

      public int getOrDefault(float k, int defaultValue) {
         return this.function.getOrDefault(k, defaultValue);
      }

      public int remove(float k) {
         throw new UnsupportedOperationException();
      }

      public void clear() {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Integer put(Float k, Integer v) {
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

   public static class PrimitiveFunction implements Float2IntFunction {
      protected final Function function;

      protected PrimitiveFunction(Function function) {
         this.function = function;
      }

      public boolean containsKey(float key) {
         return this.function.apply(key) != null;
      }

      /** @deprecated */
      @Deprecated
      public boolean containsKey(Object key) {
         if (key == null) {
            return false;
         } else {
            return this.function.apply((Float)key) != null;
         }
      }

      public int get(float key) {
         Integer v = (Integer)this.function.apply(key);
         return v == null ? this.defaultReturnValue() : v;
      }

      public int getOrDefault(float key, int defaultValue) {
         Integer v = (Integer)this.function.apply(key);
         return v == null ? defaultValue : v;
      }

      /** @deprecated */
      @Deprecated
      public Integer get(Object key) {
         return key == null ? null : (Integer)this.function.apply((Float)key);
      }

      /** @deprecated */
      @Deprecated
      public Integer getOrDefault(Object key, Integer defaultValue) {
         if (key == null) {
            return defaultValue;
         } else {
            Integer v;
            return (v = (Integer)this.function.apply((Float)key)) == null ? defaultValue : v;
         }
      }

      /** @deprecated */
      @Deprecated
      public Integer put(Float key, Integer value) {
         throw new UnsupportedOperationException();
      }
   }
}
