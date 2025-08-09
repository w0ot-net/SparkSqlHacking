package shaded.parquet.it.unimi.dsi.fastutil.objects;

import java.util.function.Function;
import java.util.function.ToIntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2ReferenceFunction;

@FunctionalInterface
public interface Reference2IntFunction extends shaded.parquet.it.unimi.dsi.fastutil.Function, ToIntFunction {
   default int applyAsInt(Object operand) {
      return this.getInt(operand);
   }

   default int put(Object key, int value) {
      throw new UnsupportedOperationException();
   }

   int getInt(Object var1);

   default int getOrDefault(Object key, int defaultValue) {
      int v;
      return (v = this.getInt(key)) == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   default int removeInt(Object key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default Integer put(Object key, Integer value) {
      boolean containsKey = this.containsKey(key);
      int v = this.put(key, value);
      return containsKey ? v : null;
   }

   /** @deprecated */
   @Deprecated
   default Integer get(Object key) {
      int v;
      return (v = this.getInt(key)) == this.defaultReturnValue() && !this.containsKey(key) ? null : v;
   }

   /** @deprecated */
   @Deprecated
   default Integer getOrDefault(Object key, Integer defaultValue) {
      int v = this.getInt(key);
      return v == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   /** @deprecated */
   @Deprecated
   default Integer remove(Object key) {
      return this.containsKey(key) ? this.removeInt(key) : null;
   }

   default void defaultReturnValue(int rv) {
      throw new UnsupportedOperationException();
   }

   default int defaultReturnValue() {
      return 0;
   }

   /** @deprecated */
   @Deprecated
   default Function andThen(Function after) {
      return shaded.parquet.it.unimi.dsi.fastutil.Function.super.andThen(after);
   }

   default Reference2ByteFunction andThenByte(Int2ByteFunction after) {
      return (k) -> after.get(this.getInt(k));
   }

   default Byte2IntFunction composeByte(Byte2ReferenceFunction before) {
      return (k) -> this.getInt(before.get(k));
   }

   default Reference2ShortFunction andThenShort(Int2ShortFunction after) {
      return (k) -> after.get(this.getInt(k));
   }

   default Short2IntFunction composeShort(Short2ReferenceFunction before) {
      return (k) -> this.getInt(before.get(k));
   }

   default Reference2IntFunction andThenInt(Int2IntFunction after) {
      return (k) -> after.get(this.getInt(k));
   }

   default Int2IntFunction composeInt(Int2ReferenceFunction before) {
      return (k) -> this.getInt(before.get(k));
   }

   default Reference2LongFunction andThenLong(Int2LongFunction after) {
      return (k) -> after.get(this.getInt(k));
   }

   default Long2IntFunction composeLong(Long2ReferenceFunction before) {
      return (k) -> this.getInt(before.get(k));
   }

   default Reference2CharFunction andThenChar(Int2CharFunction after) {
      return (k) -> after.get(this.getInt(k));
   }

   default Char2IntFunction composeChar(Char2ReferenceFunction before) {
      return (k) -> this.getInt(before.get(k));
   }

   default Reference2FloatFunction andThenFloat(Int2FloatFunction after) {
      return (k) -> after.get(this.getInt(k));
   }

   default Float2IntFunction composeFloat(Float2ReferenceFunction before) {
      return (k) -> this.getInt(before.get(k));
   }

   default Reference2DoubleFunction andThenDouble(Int2DoubleFunction after) {
      return (k) -> after.get(this.getInt(k));
   }

   default Double2IntFunction composeDouble(Double2ReferenceFunction before) {
      return (k) -> this.getInt(before.get(k));
   }

   default Reference2ObjectFunction andThenObject(Int2ObjectFunction after) {
      return (k) -> after.get(this.getInt(k));
   }

   default Object2IntFunction composeObject(Object2ReferenceFunction before) {
      return (k) -> this.getInt(before.get(k));
   }

   default Reference2ReferenceFunction andThenReference(Int2ReferenceFunction after) {
      return (k) -> after.get(this.getInt(k));
   }

   default Reference2IntFunction composeReference(Reference2ReferenceFunction before) {
      return (k) -> this.getInt(before.get(k));
   }
}
