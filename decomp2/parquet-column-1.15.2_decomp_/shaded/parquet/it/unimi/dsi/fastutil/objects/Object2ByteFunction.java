package shaded.parquet.it.unimi.dsi.fastutil.objects;

import java.util.function.Function;
import java.util.function.ToIntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2ObjectFunction;

@FunctionalInterface
public interface Object2ByteFunction extends shaded.parquet.it.unimi.dsi.fastutil.Function, ToIntFunction {
   default int applyAsInt(Object operand) {
      return this.getByte(operand);
   }

   default byte put(Object key, byte value) {
      throw new UnsupportedOperationException();
   }

   byte getByte(Object var1);

   default byte getOrDefault(Object key, byte defaultValue) {
      byte v;
      return (v = this.getByte(key)) == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   default byte removeByte(Object key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default Byte put(Object key, Byte value) {
      boolean containsKey = this.containsKey(key);
      byte v = this.put(key, value);
      return containsKey ? v : null;
   }

   /** @deprecated */
   @Deprecated
   default Byte get(Object key) {
      byte v;
      return (v = this.getByte(key)) == this.defaultReturnValue() && !this.containsKey(key) ? null : v;
   }

   /** @deprecated */
   @Deprecated
   default Byte getOrDefault(Object key, Byte defaultValue) {
      byte v = this.getByte(key);
      return v == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   /** @deprecated */
   @Deprecated
   default Byte remove(Object key) {
      return this.containsKey(key) ? this.removeByte(key) : null;
   }

   default void defaultReturnValue(byte rv) {
      throw new UnsupportedOperationException();
   }

   default byte defaultReturnValue() {
      return 0;
   }

   /** @deprecated */
   @Deprecated
   default Function andThen(Function after) {
      return shaded.parquet.it.unimi.dsi.fastutil.Function.super.andThen(after);
   }

   default Object2ByteFunction andThenByte(Byte2ByteFunction after) {
      return (k) -> after.get(this.getByte(k));
   }

   default Byte2ByteFunction composeByte(Byte2ObjectFunction before) {
      return (k) -> this.getByte(before.get(k));
   }

   default Object2ShortFunction andThenShort(Byte2ShortFunction after) {
      return (k) -> after.get(this.getByte(k));
   }

   default Short2ByteFunction composeShort(Short2ObjectFunction before) {
      return (k) -> this.getByte(before.get(k));
   }

   default Object2IntFunction andThenInt(Byte2IntFunction after) {
      return (k) -> after.get(this.getByte(k));
   }

   default Int2ByteFunction composeInt(Int2ObjectFunction before) {
      return (k) -> this.getByte(before.get(k));
   }

   default Object2LongFunction andThenLong(Byte2LongFunction after) {
      return (k) -> after.get(this.getByte(k));
   }

   default Long2ByteFunction composeLong(Long2ObjectFunction before) {
      return (k) -> this.getByte(before.get(k));
   }

   default Object2CharFunction andThenChar(Byte2CharFunction after) {
      return (k) -> after.get(this.getByte(k));
   }

   default Char2ByteFunction composeChar(Char2ObjectFunction before) {
      return (k) -> this.getByte(before.get(k));
   }

   default Object2FloatFunction andThenFloat(Byte2FloatFunction after) {
      return (k) -> after.get(this.getByte(k));
   }

   default Float2ByteFunction composeFloat(Float2ObjectFunction before) {
      return (k) -> this.getByte(before.get(k));
   }

   default Object2DoubleFunction andThenDouble(Byte2DoubleFunction after) {
      return (k) -> after.get(this.getByte(k));
   }

   default Double2ByteFunction composeDouble(Double2ObjectFunction before) {
      return (k) -> this.getByte(before.get(k));
   }

   default Object2ObjectFunction andThenObject(Byte2ObjectFunction after) {
      return (k) -> after.get(this.getByte(k));
   }

   default Object2ByteFunction composeObject(Object2ObjectFunction before) {
      return (k) -> this.getByte(before.get(k));
   }

   default Object2ReferenceFunction andThenReference(Byte2ReferenceFunction after) {
      return (k) -> after.get(this.getByte(k));
   }

   default Reference2ByteFunction composeReference(Reference2ObjectFunction before) {
      return (k) -> this.getByte(before.get(k));
   }
}
