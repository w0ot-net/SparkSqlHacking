package shaded.parquet.it.unimi.dsi.fastutil.floats;

import java.util.function.DoubleToIntFunction;
import java.util.function.Function;
import shaded.parquet.it.unimi.dsi.fastutil.SafeMath;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2FloatFunction;
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
public interface Float2ShortFunction extends shaded.parquet.it.unimi.dsi.fastutil.Function, DoubleToIntFunction {
   /** @deprecated */
   @Deprecated
   default int applyAsInt(double operand) {
      return this.get(SafeMath.safeDoubleToFloat(operand));
   }

   default short put(float key, short value) {
      throw new UnsupportedOperationException();
   }

   short get(float var1);

   default short getOrDefault(float key, short defaultValue) {
      short v;
      return (v = this.get(key)) == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   default short remove(float key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default Short put(Float key, Short value) {
      float k = key;
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
         float k = (Float)key;
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
         float k = (Float)key;
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
         float k = (Float)key;
         return this.containsKey(k) ? this.remove(k) : null;
      }
   }

   default boolean containsKey(float key) {
      return true;
   }

   /** @deprecated */
   @Deprecated
   default boolean containsKey(Object key) {
      return key == null ? false : this.containsKey((Float)key);
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

   default Float2ByteFunction andThenByte(Short2ByteFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Byte2ShortFunction composeByte(Byte2FloatFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Float2ShortFunction andThenShort(Short2ShortFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Short2ShortFunction composeShort(Short2FloatFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Float2IntFunction andThenInt(Short2IntFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Int2ShortFunction composeInt(Int2FloatFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Float2LongFunction andThenLong(Short2LongFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Long2ShortFunction composeLong(Long2FloatFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Float2CharFunction andThenChar(Short2CharFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Char2ShortFunction composeChar(Char2FloatFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Float2FloatFunction andThenFloat(Short2FloatFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Float2ShortFunction composeFloat(Float2FloatFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Float2DoubleFunction andThenDouble(Short2DoubleFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Double2ShortFunction composeDouble(Double2FloatFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Float2ObjectFunction andThenObject(Short2ObjectFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Object2ShortFunction composeObject(Object2FloatFunction before) {
      return (k) -> this.get(before.getFloat(k));
   }

   default Float2ReferenceFunction andThenReference(Short2ReferenceFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Reference2ShortFunction composeReference(Reference2FloatFunction before) {
      return (k) -> this.get(before.getFloat(k));
   }
}
