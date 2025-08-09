package shaded.parquet.it.unimi.dsi.fastutil.objects;

import java.util.function.Function;
import java.util.function.ToIntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2ObjectFunction;

@FunctionalInterface
public interface Object2CharFunction extends shaded.parquet.it.unimi.dsi.fastutil.Function, ToIntFunction {
   default int applyAsInt(Object operand) {
      return this.getChar(operand);
   }

   default char put(Object key, char value) {
      throw new UnsupportedOperationException();
   }

   char getChar(Object var1);

   default char getOrDefault(Object key, char defaultValue) {
      char v;
      return (v = this.getChar(key)) == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   default char removeChar(Object key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default Character put(Object key, Character value) {
      boolean containsKey = this.containsKey(key);
      char v = this.put(key, value);
      return containsKey ? v : null;
   }

   /** @deprecated */
   @Deprecated
   default Character get(Object key) {
      char v;
      return (v = this.getChar(key)) == this.defaultReturnValue() && !this.containsKey(key) ? null : v;
   }

   /** @deprecated */
   @Deprecated
   default Character getOrDefault(Object key, Character defaultValue) {
      char v = this.getChar(key);
      return v == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   /** @deprecated */
   @Deprecated
   default Character remove(Object key) {
      return this.containsKey(key) ? this.removeChar(key) : null;
   }

   default void defaultReturnValue(char rv) {
      throw new UnsupportedOperationException();
   }

   default char defaultReturnValue() {
      return '\u0000';
   }

   /** @deprecated */
   @Deprecated
   default Function andThen(Function after) {
      return shaded.parquet.it.unimi.dsi.fastutil.Function.super.andThen(after);
   }

   default Object2ByteFunction andThenByte(Char2ByteFunction after) {
      return (k) -> after.get(this.getChar(k));
   }

   default Byte2CharFunction composeByte(Byte2ObjectFunction before) {
      return (k) -> this.getChar(before.get(k));
   }

   default Object2ShortFunction andThenShort(Char2ShortFunction after) {
      return (k) -> after.get(this.getChar(k));
   }

   default Short2CharFunction composeShort(Short2ObjectFunction before) {
      return (k) -> this.getChar(before.get(k));
   }

   default Object2IntFunction andThenInt(Char2IntFunction after) {
      return (k) -> after.get(this.getChar(k));
   }

   default Int2CharFunction composeInt(Int2ObjectFunction before) {
      return (k) -> this.getChar(before.get(k));
   }

   default Object2LongFunction andThenLong(Char2LongFunction after) {
      return (k) -> after.get(this.getChar(k));
   }

   default Long2CharFunction composeLong(Long2ObjectFunction before) {
      return (k) -> this.getChar(before.get(k));
   }

   default Object2CharFunction andThenChar(Char2CharFunction after) {
      return (k) -> after.get(this.getChar(k));
   }

   default Char2CharFunction composeChar(Char2ObjectFunction before) {
      return (k) -> this.getChar(before.get(k));
   }

   default Object2FloatFunction andThenFloat(Char2FloatFunction after) {
      return (k) -> after.get(this.getChar(k));
   }

   default Float2CharFunction composeFloat(Float2ObjectFunction before) {
      return (k) -> this.getChar(before.get(k));
   }

   default Object2DoubleFunction andThenDouble(Char2DoubleFunction after) {
      return (k) -> after.get(this.getChar(k));
   }

   default Double2CharFunction composeDouble(Double2ObjectFunction before) {
      return (k) -> this.getChar(before.get(k));
   }

   default Object2ObjectFunction andThenObject(Char2ObjectFunction after) {
      return (k) -> after.get(this.getChar(k));
   }

   default Object2CharFunction composeObject(Object2ObjectFunction before) {
      return (k) -> this.getChar(before.get(k));
   }

   default Object2ReferenceFunction andThenReference(Char2ReferenceFunction after) {
      return (k) -> after.get(this.getChar(k));
   }

   default Reference2CharFunction composeReference(Reference2ObjectFunction before) {
      return (k) -> this.getChar(before.get(k));
   }
}
