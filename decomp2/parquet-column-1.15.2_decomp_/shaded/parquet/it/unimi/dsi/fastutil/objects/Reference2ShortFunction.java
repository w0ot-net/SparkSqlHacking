package shaded.parquet.it.unimi.dsi.fastutil.objects;

import java.util.function.Function;
import java.util.function.ToIntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2ShortFunction;

@FunctionalInterface
public interface Reference2ShortFunction extends shaded.parquet.it.unimi.dsi.fastutil.Function, ToIntFunction {
   default int applyAsInt(Object operand) {
      return this.getShort(operand);
   }

   default short put(Object key, short value) {
      throw new UnsupportedOperationException();
   }

   short getShort(Object var1);

   default short getOrDefault(Object key, short defaultValue) {
      short v;
      return (v = this.getShort(key)) == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   default short removeShort(Object key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default Short put(Object key, Short value) {
      boolean containsKey = this.containsKey(key);
      short v = this.put(key, value);
      return containsKey ? v : null;
   }

   /** @deprecated */
   @Deprecated
   default Short get(Object key) {
      short v;
      return (v = this.getShort(key)) == this.defaultReturnValue() && !this.containsKey(key) ? null : v;
   }

   /** @deprecated */
   @Deprecated
   default Short getOrDefault(Object key, Short defaultValue) {
      short v = this.getShort(key);
      return v == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   /** @deprecated */
   @Deprecated
   default Short remove(Object key) {
      return this.containsKey(key) ? this.removeShort(key) : null;
   }

   default void defaultReturnValue(short rv) {
      throw new UnsupportedOperationException();
   }

   default short defaultReturnValue() {
      return 0;
   }

   /** @deprecated */
   @Deprecated
   default Function andThen(Function after) {
      return shaded.parquet.it.unimi.dsi.fastutil.Function.super.andThen(after);
   }

   default Reference2ByteFunction andThenByte(Short2ByteFunction after) {
      return (k) -> after.get(this.getShort(k));
   }

   default Byte2ShortFunction composeByte(Byte2ReferenceFunction before) {
      return (k) -> this.getShort(before.get(k));
   }

   default Reference2ShortFunction andThenShort(Short2ShortFunction after) {
      return (k) -> after.get(this.getShort(k));
   }

   default Short2ShortFunction composeShort(Short2ReferenceFunction before) {
      return (k) -> this.getShort(before.get(k));
   }

   default Reference2IntFunction andThenInt(Short2IntFunction after) {
      return (k) -> after.get(this.getShort(k));
   }

   default Int2ShortFunction composeInt(Int2ReferenceFunction before) {
      return (k) -> this.getShort(before.get(k));
   }

   default Reference2LongFunction andThenLong(Short2LongFunction after) {
      return (k) -> after.get(this.getShort(k));
   }

   default Long2ShortFunction composeLong(Long2ReferenceFunction before) {
      return (k) -> this.getShort(before.get(k));
   }

   default Reference2CharFunction andThenChar(Short2CharFunction after) {
      return (k) -> after.get(this.getShort(k));
   }

   default Char2ShortFunction composeChar(Char2ReferenceFunction before) {
      return (k) -> this.getShort(before.get(k));
   }

   default Reference2FloatFunction andThenFloat(Short2FloatFunction after) {
      return (k) -> after.get(this.getShort(k));
   }

   default Float2ShortFunction composeFloat(Float2ReferenceFunction before) {
      return (k) -> this.getShort(before.get(k));
   }

   default Reference2DoubleFunction andThenDouble(Short2DoubleFunction after) {
      return (k) -> after.get(this.getShort(k));
   }

   default Double2ShortFunction composeDouble(Double2ReferenceFunction before) {
      return (k) -> this.getShort(before.get(k));
   }

   default Reference2ObjectFunction andThenObject(Short2ObjectFunction after) {
      return (k) -> after.get(this.getShort(k));
   }

   default Object2ShortFunction composeObject(Object2ReferenceFunction before) {
      return (k) -> this.getShort(before.get(k));
   }

   default Reference2ReferenceFunction andThenReference(Short2ReferenceFunction after) {
      return (k) -> after.get(this.getShort(k));
   }

   default Reference2ShortFunction composeReference(Reference2ReferenceFunction before) {
      return (k) -> this.getShort(before.get(k));
   }
}
