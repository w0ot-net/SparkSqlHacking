package shaded.parquet.it.unimi.dsi.fastutil.ints;

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
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2IntFunction;

@FunctionalInterface
public interface Int2ByteFunction extends shaded.parquet.it.unimi.dsi.fastutil.Function, java.util.function.IntUnaryOperator {
   default int applyAsInt(int operand) {
      return this.get(operand);
   }

   default byte put(int key, byte value) {
      throw new UnsupportedOperationException();
   }

   byte get(int var1);

   default byte getOrDefault(int key, byte defaultValue) {
      byte v;
      return (v = this.get(key)) == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   default byte remove(int key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default Byte put(Integer key, Byte value) {
      int k = key;
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
         int k = (Integer)key;
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
         int k = (Integer)key;
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
         int k = (Integer)key;
         return this.containsKey(k) ? this.remove(k) : null;
      }
   }

   default boolean containsKey(int key) {
      return true;
   }

   /** @deprecated */
   @Deprecated
   default boolean containsKey(Object key) {
      return key == null ? false : this.containsKey((Integer)key);
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

   default Int2ByteFunction andThenByte(Byte2ByteFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Byte2ByteFunction composeByte(Byte2IntFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Int2ShortFunction andThenShort(Byte2ShortFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Short2ByteFunction composeShort(Short2IntFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Int2IntFunction andThenInt(Byte2IntFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Int2ByteFunction composeInt(Int2IntFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Int2LongFunction andThenLong(Byte2LongFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Long2ByteFunction composeLong(Long2IntFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Int2CharFunction andThenChar(Byte2CharFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Char2ByteFunction composeChar(Char2IntFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Int2FloatFunction andThenFloat(Byte2FloatFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Float2ByteFunction composeFloat(Float2IntFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Int2DoubleFunction andThenDouble(Byte2DoubleFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Double2ByteFunction composeDouble(Double2IntFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Int2ObjectFunction andThenObject(Byte2ObjectFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Object2ByteFunction composeObject(Object2IntFunction before) {
      return (k) -> this.get(before.getInt(k));
   }

   default Int2ReferenceFunction andThenReference(Byte2ReferenceFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Reference2ByteFunction composeReference(Reference2IntFunction before) {
      return (k) -> this.get(before.getInt(k));
   }
}
