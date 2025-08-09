package shaded.parquet.it.unimi.dsi.fastutil.longs;

import java.util.function.Function;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2LongFunction;

@FunctionalInterface
public interface Long2LongFunction extends shaded.parquet.it.unimi.dsi.fastutil.Function, java.util.function.LongUnaryOperator {
   default long applyAsLong(long operand) {
      return this.get(operand);
   }

   default long put(long key, long value) {
      throw new UnsupportedOperationException();
   }

   long get(long var1);

   default long getOrDefault(long key, long defaultValue) {
      long v;
      return (v = this.get(key)) == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   default long remove(long key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default Long put(Long key, Long value) {
      long k = key;
      boolean containsKey = this.containsKey(k);
      long v = this.put(k, value);
      return containsKey ? v : null;
   }

   /** @deprecated */
   @Deprecated
   default Long get(Object key) {
      if (key == null) {
         return null;
      } else {
         long k = (Long)key;
         long v;
         return (v = this.get(k)) == this.defaultReturnValue() && !this.containsKey(k) ? null : v;
      }
   }

   /** @deprecated */
   @Deprecated
   default Long getOrDefault(Object key, Long defaultValue) {
      if (key == null) {
         return defaultValue;
      } else {
         long k = (Long)key;
         long v = this.get(k);
         return v == this.defaultReturnValue() && !this.containsKey(k) ? defaultValue : v;
      }
   }

   /** @deprecated */
   @Deprecated
   default Long remove(Object key) {
      if (key == null) {
         return null;
      } else {
         long k = (Long)key;
         return this.containsKey(k) ? this.remove(k) : null;
      }
   }

   default boolean containsKey(long key) {
      return true;
   }

   /** @deprecated */
   @Deprecated
   default boolean containsKey(Object key) {
      return key == null ? false : this.containsKey((Long)key);
   }

   default void defaultReturnValue(long rv) {
      throw new UnsupportedOperationException();
   }

   default long defaultReturnValue() {
      return 0L;
   }

   static Long2LongFunction identity() {
      return (k) -> k;
   }

   /** @deprecated */
   @Deprecated
   default Function compose(Function before) {
      return shaded.parquet.it.unimi.dsi.fastutil.Function.super.compose(before);
   }

   /** @deprecated */
   @Deprecated
   default Function andThen(Function after) {
      return shaded.parquet.it.unimi.dsi.fastutil.Function.super.andThen(after);
   }

   default Long2ByteFunction andThenByte(Long2ByteFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Byte2LongFunction composeByte(Byte2LongFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Long2ShortFunction andThenShort(Long2ShortFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Short2LongFunction composeShort(Short2LongFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Long2IntFunction andThenInt(Long2IntFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Int2LongFunction composeInt(Int2LongFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Long2LongFunction andThenLong(Long2LongFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Long2LongFunction composeLong(Long2LongFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Long2CharFunction andThenChar(Long2CharFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Char2LongFunction composeChar(Char2LongFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Long2FloatFunction andThenFloat(Long2FloatFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Float2LongFunction composeFloat(Float2LongFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Long2DoubleFunction andThenDouble(Long2DoubleFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Double2LongFunction composeDouble(Double2LongFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Long2ObjectFunction andThenObject(Long2ObjectFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Object2LongFunction composeObject(Object2LongFunction before) {
      return (k) -> this.get(before.getLong(k));
   }

   default Long2ReferenceFunction andThenReference(Long2ReferenceFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Reference2LongFunction composeReference(Reference2LongFunction before) {
      return (k) -> this.get(before.getLong(k));
   }
}
