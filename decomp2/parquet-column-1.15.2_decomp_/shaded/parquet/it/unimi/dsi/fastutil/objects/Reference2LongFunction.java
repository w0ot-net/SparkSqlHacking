package shaded.parquet.it.unimi.dsi.fastutil.objects;

import java.util.function.Function;
import java.util.function.ToLongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2ReferenceFunction;

@FunctionalInterface
public interface Reference2LongFunction extends shaded.parquet.it.unimi.dsi.fastutil.Function, ToLongFunction {
   default long applyAsLong(Object operand) {
      return this.getLong(operand);
   }

   default long put(Object key, long value) {
      throw new UnsupportedOperationException();
   }

   long getLong(Object var1);

   default long getOrDefault(Object key, long defaultValue) {
      long v;
      return (v = this.getLong(key)) == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   default long removeLong(Object key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default Long put(Object key, Long value) {
      boolean containsKey = this.containsKey(key);
      long v = this.put(key, value);
      return containsKey ? v : null;
   }

   /** @deprecated */
   @Deprecated
   default Long get(Object key) {
      long v;
      return (v = this.getLong(key)) == this.defaultReturnValue() && !this.containsKey(key) ? null : v;
   }

   /** @deprecated */
   @Deprecated
   default Long getOrDefault(Object key, Long defaultValue) {
      long v = this.getLong(key);
      return v == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   /** @deprecated */
   @Deprecated
   default Long remove(Object key) {
      return this.containsKey(key) ? this.removeLong(key) : null;
   }

   default void defaultReturnValue(long rv) {
      throw new UnsupportedOperationException();
   }

   default long defaultReturnValue() {
      return 0L;
   }

   /** @deprecated */
   @Deprecated
   default Function andThen(Function after) {
      return shaded.parquet.it.unimi.dsi.fastutil.Function.super.andThen(after);
   }

   default Reference2ByteFunction andThenByte(Long2ByteFunction after) {
      return (k) -> after.get(this.getLong(k));
   }

   default Byte2LongFunction composeByte(Byte2ReferenceFunction before) {
      return (k) -> this.getLong(before.get(k));
   }

   default Reference2ShortFunction andThenShort(Long2ShortFunction after) {
      return (k) -> after.get(this.getLong(k));
   }

   default Short2LongFunction composeShort(Short2ReferenceFunction before) {
      return (k) -> this.getLong(before.get(k));
   }

   default Reference2IntFunction andThenInt(Long2IntFunction after) {
      return (k) -> after.get(this.getLong(k));
   }

   default Int2LongFunction composeInt(Int2ReferenceFunction before) {
      return (k) -> this.getLong(before.get(k));
   }

   default Reference2LongFunction andThenLong(Long2LongFunction after) {
      return (k) -> after.get(this.getLong(k));
   }

   default Long2LongFunction composeLong(Long2ReferenceFunction before) {
      return (k) -> this.getLong(before.get(k));
   }

   default Reference2CharFunction andThenChar(Long2CharFunction after) {
      return (k) -> after.get(this.getLong(k));
   }

   default Char2LongFunction composeChar(Char2ReferenceFunction before) {
      return (k) -> this.getLong(before.get(k));
   }

   default Reference2FloatFunction andThenFloat(Long2FloatFunction after) {
      return (k) -> after.get(this.getLong(k));
   }

   default Float2LongFunction composeFloat(Float2ReferenceFunction before) {
      return (k) -> this.getLong(before.get(k));
   }

   default Reference2DoubleFunction andThenDouble(Long2DoubleFunction after) {
      return (k) -> after.get(this.getLong(k));
   }

   default Double2LongFunction composeDouble(Double2ReferenceFunction before) {
      return (k) -> this.getLong(before.get(k));
   }

   default Reference2ObjectFunction andThenObject(Long2ObjectFunction after) {
      return (k) -> after.get(this.getLong(k));
   }

   default Object2LongFunction composeObject(Object2ReferenceFunction before) {
      return (k) -> this.getLong(before.get(k));
   }

   default Reference2ReferenceFunction andThenReference(Long2ReferenceFunction after) {
      return (k) -> after.get(this.getLong(k));
   }

   default Reference2LongFunction composeReference(Reference2ReferenceFunction before) {
      return (k) -> this.getLong(before.get(k));
   }
}
