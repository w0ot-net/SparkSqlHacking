package shaded.parquet.it.unimi.dsi.fastutil.ints;

import java.util.function.Function;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2ShortFunction;
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
public interface Int2ShortFunction extends shaded.parquet.it.unimi.dsi.fastutil.Function, java.util.function.IntUnaryOperator {
   default int applyAsInt(int operand) {
      return this.get(operand);
   }

   default short put(int key, short value) {
      throw new UnsupportedOperationException();
   }

   short get(int var1);

   default short getOrDefault(int key, short defaultValue) {
      short v;
      return (v = this.get(key)) == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   default short remove(int key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default Short put(Integer key, Short value) {
      int k = key;
      boolean containsKey = this.containsKey(k);
      short v = this.put(k, value);
      return containsKey ? v : null;
   }

   /** @deprecated */
   @Deprecated
   default Short get(Object key) {
      if (key == null) {
         return null;
      } else {
         int k = (Integer)key;
         short v;
         return (v = this.get(k)) == this.defaultReturnValue() && !this.containsKey(k) ? null : v;
      }
   }

   /** @deprecated */
   @Deprecated
   default Short getOrDefault(Object key, Short defaultValue) {
      if (key == null) {
         return defaultValue;
      } else {
         int k = (Integer)key;
         short v = this.get(k);
         return v == this.defaultReturnValue() && !this.containsKey(k) ? defaultValue : v;
      }
   }

   /** @deprecated */
   @Deprecated
   default Short remove(Object key) {
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

   default void defaultReturnValue(short rv) {
      throw new UnsupportedOperationException();
   }

   default short defaultReturnValue() {
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

   default Int2ByteFunction andThenByte(Short2ByteFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Byte2ShortFunction composeByte(Byte2IntFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Int2ShortFunction andThenShort(Short2ShortFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Short2ShortFunction composeShort(Short2IntFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Int2IntFunction andThenInt(Short2IntFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Int2ShortFunction composeInt(Int2IntFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Int2LongFunction andThenLong(Short2LongFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Long2ShortFunction composeLong(Long2IntFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Int2CharFunction andThenChar(Short2CharFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Char2ShortFunction composeChar(Char2IntFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Int2FloatFunction andThenFloat(Short2FloatFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Float2ShortFunction composeFloat(Float2IntFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Int2DoubleFunction andThenDouble(Short2DoubleFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Double2ShortFunction composeDouble(Double2IntFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Int2ObjectFunction andThenObject(Short2ObjectFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Object2ShortFunction composeObject(Object2IntFunction before) {
      return (k) -> this.get(before.getInt(k));
   }

   default Int2ReferenceFunction andThenReference(Short2ReferenceFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Reference2ShortFunction composeReference(Reference2IntFunction before) {
      return (k) -> this.get(before.getInt(k));
   }
}
