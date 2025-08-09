package shaded.parquet.it.unimi.dsi.fastutil.doubles;

import java.util.function.DoubleToIntFunction;
import java.util.function.Function;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2DoubleFunction;
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
public interface Double2ShortFunction extends shaded.parquet.it.unimi.dsi.fastutil.Function, DoubleToIntFunction {
   default int applyAsInt(double operand) {
      return this.get(operand);
   }

   default short put(double key, short value) {
      throw new UnsupportedOperationException();
   }

   short get(double var1);

   default short getOrDefault(double key, short defaultValue) {
      short v;
      return (v = this.get(key)) == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   default short remove(double key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default Short put(Double key, Short value) {
      double k = key;
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
         double k = (Double)key;
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
         double k = (Double)key;
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

   default Double2ByteFunction andThenByte(Short2ByteFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Byte2ShortFunction composeByte(Byte2DoubleFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Double2ShortFunction andThenShort(Short2ShortFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Short2ShortFunction composeShort(Short2DoubleFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Double2IntFunction andThenInt(Short2IntFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Int2ShortFunction composeInt(Int2DoubleFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Double2LongFunction andThenLong(Short2LongFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Long2ShortFunction composeLong(Long2DoubleFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Double2CharFunction andThenChar(Short2CharFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Char2ShortFunction composeChar(Char2DoubleFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Double2FloatFunction andThenFloat(Short2FloatFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Float2ShortFunction composeFloat(Float2DoubleFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Double2DoubleFunction andThenDouble(Short2DoubleFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Double2ShortFunction composeDouble(Double2DoubleFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Double2ObjectFunction andThenObject(Short2ObjectFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Object2ShortFunction composeObject(Object2DoubleFunction before) {
      return (k) -> this.get(before.getDouble(k));
   }

   default Double2ReferenceFunction andThenReference(Short2ReferenceFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Reference2ShortFunction composeReference(Reference2DoubleFunction before) {
      return (k) -> this.get(before.getDouble(k));
   }
}
