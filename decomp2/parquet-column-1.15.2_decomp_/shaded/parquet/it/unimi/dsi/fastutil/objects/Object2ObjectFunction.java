package shaded.parquet.it.unimi.dsi.fastutil.objects;

import shaded.parquet.it.unimi.dsi.fastutil.Function;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2ObjectFunction;

@FunctionalInterface
public interface Object2ObjectFunction extends Function {
   default Object put(Object key, Object value) {
      throw new UnsupportedOperationException();
   }

   Object get(Object var1);

   default Object getOrDefault(Object key, Object defaultValue) {
      V v;
      return (v = (V)this.get(key)) == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   default Object remove(Object key) {
      throw new UnsupportedOperationException();
   }

   default void defaultReturnValue(Object rv) {
      throw new UnsupportedOperationException();
   }

   default Object defaultReturnValue() {
      return null;
   }

   default Object2ByteFunction andThenByte(Object2ByteFunction after) {
      return (k) -> after.getByte(this.get(k));
   }

   default Byte2ObjectFunction composeByte(Byte2ObjectFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Object2ShortFunction andThenShort(Object2ShortFunction after) {
      return (k) -> after.getShort(this.get(k));
   }

   default Short2ObjectFunction composeShort(Short2ObjectFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Object2IntFunction andThenInt(Object2IntFunction after) {
      return (k) -> after.getInt(this.get(k));
   }

   default Int2ObjectFunction composeInt(Int2ObjectFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Object2LongFunction andThenLong(Object2LongFunction after) {
      return (k) -> after.getLong(this.get(k));
   }

   default Long2ObjectFunction composeLong(Long2ObjectFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Object2CharFunction andThenChar(Object2CharFunction after) {
      return (k) -> after.getChar(this.get(k));
   }

   default Char2ObjectFunction composeChar(Char2ObjectFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Object2FloatFunction andThenFloat(Object2FloatFunction after) {
      return (k) -> after.getFloat(this.get(k));
   }

   default Float2ObjectFunction composeFloat(Float2ObjectFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Object2DoubleFunction andThenDouble(Object2DoubleFunction after) {
      return (k) -> after.getDouble(this.get(k));
   }

   default Double2ObjectFunction composeDouble(Double2ObjectFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Object2ObjectFunction andThenObject(Object2ObjectFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Object2ObjectFunction composeObject(Object2ObjectFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Object2ReferenceFunction andThenReference(Object2ReferenceFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Reference2ObjectFunction composeReference(Reference2ObjectFunction before) {
      return (k) -> this.get(before.get(k));
   }
}
