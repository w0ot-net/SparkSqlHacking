package shaded.parquet.it.unimi.dsi.fastutil.bytes;

import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import shaded.parquet.it.unimi.dsi.fastutil.SafeMath;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2CharFunction;

@FunctionalInterface
public interface Byte2CharFunction extends shaded.parquet.it.unimi.dsi.fastutil.Function, IntUnaryOperator {
   /** @deprecated */
   @Deprecated
   default int applyAsInt(int operand) {
      return this.get(SafeMath.safeIntToByte(operand));
   }

   default char put(byte key, char value) {
      throw new UnsupportedOperationException();
   }

   char get(byte var1);

   default char getOrDefault(byte key, char defaultValue) {
      char v;
      return (v = this.get(key)) == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   default char remove(byte key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default Character put(Byte key, Character value) {
      byte k = key;
      boolean containsKey = this.containsKey(k);
      char v = this.put(k, value);
      return containsKey ? v : null;
   }

   /** @deprecated */
   @Deprecated
   default Character get(Object key) {
      if (key == null) {
         return null;
      } else {
         byte k = (Byte)key;
         char v;
         return (v = this.get(k)) == this.defaultReturnValue() && !this.containsKey(k) ? null : v;
      }
   }

   /** @deprecated */
   @Deprecated
   default Character getOrDefault(Object key, Character defaultValue) {
      if (key == null) {
         return defaultValue;
      } else {
         byte k = (Byte)key;
         char v = this.get(k);
         return v == this.defaultReturnValue() && !this.containsKey(k) ? defaultValue : v;
      }
   }

   /** @deprecated */
   @Deprecated
   default Character remove(Object key) {
      if (key == null) {
         return null;
      } else {
         byte k = (Byte)key;
         return this.containsKey(k) ? this.remove(k) : null;
      }
   }

   default boolean containsKey(byte key) {
      return true;
   }

   /** @deprecated */
   @Deprecated
   default boolean containsKey(Object key) {
      return key == null ? false : this.containsKey((Byte)key);
   }

   default void defaultReturnValue(char rv) {
      throw new UnsupportedOperationException();
   }

   default char defaultReturnValue() {
      return '\u0000';
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

   default Byte2ByteFunction andThenByte(Char2ByteFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Byte2CharFunction composeByte(Byte2ByteFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Byte2ShortFunction andThenShort(Char2ShortFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Short2CharFunction composeShort(Short2ByteFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Byte2IntFunction andThenInt(Char2IntFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Int2CharFunction composeInt(Int2ByteFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Byte2LongFunction andThenLong(Char2LongFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Long2CharFunction composeLong(Long2ByteFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Byte2CharFunction andThenChar(Char2CharFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Char2CharFunction composeChar(Char2ByteFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Byte2FloatFunction andThenFloat(Char2FloatFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Float2CharFunction composeFloat(Float2ByteFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Byte2DoubleFunction andThenDouble(Char2DoubleFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Double2CharFunction composeDouble(Double2ByteFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Byte2ObjectFunction andThenObject(Char2ObjectFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Object2CharFunction composeObject(Object2ByteFunction before) {
      return (k) -> this.get(before.getByte(k));
   }

   default Byte2ReferenceFunction andThenReference(Char2ReferenceFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Reference2CharFunction composeReference(Reference2ByteFunction before) {
      return (k) -> this.get(before.getByte(k));
   }
}
