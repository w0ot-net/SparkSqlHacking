package shaded.parquet.it.unimi.dsi.fastutil.doubles;

import java.util.function.DoubleToIntFunction;
import java.util.function.Function;
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
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2DoubleFunction;

@FunctionalInterface
public interface Double2ByteFunction extends shaded.parquet.it.unimi.dsi.fastutil.Function, DoubleToIntFunction {
   default int applyAsInt(double operand) {
      return this.get(operand);
   }

   default byte put(double key, byte value) {
      throw new UnsupportedOperationException();
   }

   byte get(double var1);

   default byte getOrDefault(double key, byte defaultValue) {
      byte v;
      return (v = this.get(key)) == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   default byte remove(double key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default Byte put(Double key, Byte value) {
      double k = key;
      boolean containsKey = this.containsKey(k);
      byte v = this.put(k, value);
      return containsKey ? v : null;
   }

   /** @deprecated */
   @Deprecated
   default Byte get(Object key) {
      if (key == null) {
         return null;
      } else {
         double k = (Double)key;
         byte v;
         return (v = this.get(k)) == this.defaultReturnValue() && !this.containsKey(k) ? null : v;
      }
   }

   /** @deprecated */
   @Deprecated
   default Byte getOrDefault(Object key, Byte defaultValue) {
      if (key == null) {
         return defaultValue;
      } else {
         double k = (Double)key;
         byte v = this.get(k);
         return v == this.defaultReturnValue() && !this.containsKey(k) ? defaultValue : v;
      }
   }

   /** @deprecated */
   @Deprecated
   default Byte remove(Object key) {
      if (key == null) {
         return null;
      } else {
         double k = (Double)key;
         return this.containsKey(k) ? this.remove(k) : null;
      }
   }

   default boolean containsKey(double key) {
      return true;
   }

   /** @deprecated */
   @Deprecated
   default boolean containsKey(Object key) {
      return key == null ? false : this.containsKey((Double)key);
   }

   default void defaultReturnValue(byte rv) {
      throw new UnsupportedOperationException();
   }

   default byte defaultReturnValue() {
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

   default Double2ByteFunction andThenByte(Byte2ByteFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Byte2ByteFunction composeByte(Byte2DoubleFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Double2ShortFunction andThenShort(Byte2ShortFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Short2ByteFunction composeShort(Short2DoubleFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Double2IntFunction andThenInt(Byte2IntFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Int2ByteFunction composeInt(Int2DoubleFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Double2LongFunction andThenLong(Byte2LongFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Long2ByteFunction composeLong(Long2DoubleFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Double2CharFunction andThenChar(Byte2CharFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Char2ByteFunction composeChar(Char2DoubleFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Double2FloatFunction andThenFloat(Byte2FloatFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Float2ByteFunction composeFloat(Float2DoubleFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Double2DoubleFunction andThenDouble(Byte2DoubleFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Double2ByteFunction composeDouble(Double2DoubleFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Double2ObjectFunction andThenObject(Byte2ObjectFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Object2ByteFunction composeObject(Object2DoubleFunction before) {
      return (k) -> this.get(before.getDouble(k));
   }

   default Double2ReferenceFunction andThenReference(Byte2ReferenceFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Reference2ByteFunction composeReference(Reference2DoubleFunction before) {
      return (k) -> this.get(before.getDouble(k));
   }
}
