package shaded.parquet.it.unimi.dsi.fastutil.bytes;

import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import shaded.parquet.it.unimi.dsi.fastutil.SafeMath;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2IntFunction;

@FunctionalInterface
public interface Byte2IntFunction extends shaded.parquet.it.unimi.dsi.fastutil.Function, IntUnaryOperator {
   /** @deprecated */
   @Deprecated
   default int applyAsInt(int operand) {
      return this.get(SafeMath.safeIntToByte(operand));
   }

   default int put(byte key, int value) {
      throw new UnsupportedOperationException();
   }

   int get(byte var1);

   default int getOrDefault(byte key, int defaultValue) {
      int v;
      return (v = this.get(key)) == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   default int remove(byte key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default Integer put(Byte key, Integer value) {
      byte k = key;
      boolean containsKey = this.containsKey(k);
      int v = this.put(k, value);
      return containsKey ? v : null;
   }

   /** @deprecated */
   @Deprecated
   default Integer get(Object key) {
      if (key == null) {
         return null;
      } else {
         byte k = (Byte)key;
         int v;
         return (v = this.get(k)) == this.defaultReturnValue() && !this.containsKey(k) ? null : v;
      }
   }

   /** @deprecated */
   @Deprecated
   default Integer getOrDefault(Object key, Integer defaultValue) {
      if (key == null) {
         return defaultValue;
      } else {
         byte k = (Byte)key;
         int v = this.get(k);
         return v == this.defaultReturnValue() && !this.containsKey(k) ? defaultValue : v;
      }
   }

   /** @deprecated */
   @Deprecated
   default Integer remove(Object key) {
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

   default void defaultReturnValue(int rv) {
      throw new UnsupportedOperationException();
   }

   default int defaultReturnValue() {
      return 0;
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

   default Byte2ByteFunction andThenByte(Int2ByteFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Byte2IntFunction composeByte(Byte2ByteFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Byte2ShortFunction andThenShort(Int2ShortFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Short2IntFunction composeShort(Short2ByteFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Byte2IntFunction andThenInt(Int2IntFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Int2IntFunction composeInt(Int2ByteFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Byte2LongFunction andThenLong(Int2LongFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Long2IntFunction composeLong(Long2ByteFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Byte2CharFunction andThenChar(Int2CharFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Char2IntFunction composeChar(Char2ByteFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Byte2FloatFunction andThenFloat(Int2FloatFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Float2IntFunction composeFloat(Float2ByteFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Byte2DoubleFunction andThenDouble(Int2DoubleFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Double2IntFunction composeDouble(Double2ByteFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Byte2ObjectFunction andThenObject(Int2ObjectFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Object2IntFunction composeObject(Object2ByteFunction before) {
      return (k) -> this.get(before.getByte(k));
   }

   default Byte2ReferenceFunction andThenReference(Int2ReferenceFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Reference2IntFunction composeReference(Reference2ByteFunction before) {
      return (k) -> this.get(before.getByte(k));
   }
}
