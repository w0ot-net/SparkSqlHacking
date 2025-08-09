package shaded.parquet.it.unimi.dsi.fastutil.objects;

import shaded.parquet.it.unimi.dsi.fastutil.Function;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2ReferenceFunction;

@FunctionalInterface
public interface Reference2ReferenceFunction extends Function {
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

   default Reference2ByteFunction andThenByte(Reference2ByteFunction after) {
      return (k) -> after.getByte(this.get(k));
   }

   default Byte2ReferenceFunction composeByte(Byte2ReferenceFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Reference2ShortFunction andThenShort(Reference2ShortFunction after) {
      return (k) -> after.getShort(this.get(k));
   }

   default Short2ReferenceFunction composeShort(Short2ReferenceFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Reference2IntFunction andThenInt(Reference2IntFunction after) {
      return (k) -> after.getInt(this.get(k));
   }

   default Int2ReferenceFunction composeInt(Int2ReferenceFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Reference2LongFunction andThenLong(Reference2LongFunction after) {
      return (k) -> after.getLong(this.get(k));
   }

   default Long2ReferenceFunction composeLong(Long2ReferenceFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Reference2CharFunction andThenChar(Reference2CharFunction after) {
      return (k) -> after.getChar(this.get(k));
   }

   default Char2ReferenceFunction composeChar(Char2ReferenceFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Reference2FloatFunction andThenFloat(Reference2FloatFunction after) {
      return (k) -> after.getFloat(this.get(k));
   }

   default Float2ReferenceFunction composeFloat(Float2ReferenceFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Reference2DoubleFunction andThenDouble(Reference2DoubleFunction after) {
      return (k) -> after.getDouble(this.get(k));
   }

   default Double2ReferenceFunction composeDouble(Double2ReferenceFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Reference2ObjectFunction andThenObject(Reference2ObjectFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Object2ReferenceFunction composeObject(Object2ReferenceFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Reference2ReferenceFunction andThenReference(Reference2ReferenceFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Reference2ReferenceFunction composeReference(Reference2ReferenceFunction before) {
      return (k) -> this.get(before.get(k));
   }
}
