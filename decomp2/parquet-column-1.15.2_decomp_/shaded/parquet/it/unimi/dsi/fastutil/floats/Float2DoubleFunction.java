package shaded.parquet.it.unimi.dsi.fastutil.floats;

import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import shaded.parquet.it.unimi.dsi.fastutil.SafeMath;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2FloatFunction;

@FunctionalInterface
public interface Float2DoubleFunction extends shaded.parquet.it.unimi.dsi.fastutil.Function, DoubleUnaryOperator {
   /** @deprecated */
   @Deprecated
   default double applyAsDouble(double operand) {
      return this.get(SafeMath.safeDoubleToFloat(operand));
   }

   default double put(float key, double value) {
      throw new UnsupportedOperationException();
   }

   double get(float var1);

   default double getOrDefault(float key, double defaultValue) {
      double v;
      return (v = this.get(key)) == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   default double remove(float key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default Double put(Float key, Double value) {
      float k = key;
      boolean containsKey = this.containsKey(k);
      double v = this.put(k, value);
      return containsKey ? v : null;
   }

   /** @deprecated */
   @Deprecated
   default Double get(Object key) {
      if (key == null) {
         return null;
      } else {
         float k = (Float)key;
         double v;
         return (v = this.get(k)) == this.defaultReturnValue() && !this.containsKey(k) ? null : v;
      }
   }

   /** @deprecated */
   @Deprecated
   default Double getOrDefault(Object key, Double defaultValue) {
      if (key == null) {
         return defaultValue;
      } else {
         float k = (Float)key;
         double v = this.get(k);
         return v == this.defaultReturnValue() && !this.containsKey(k) ? defaultValue : v;
      }
   }

   /** @deprecated */
   @Deprecated
   default Double remove(Object key) {
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

   default void defaultReturnValue(double rv) {
      throw new UnsupportedOperationException();
   }

   default double defaultReturnValue() {
      return (double)0.0F;
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

   default Float2ByteFunction andThenByte(Double2ByteFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Byte2DoubleFunction composeByte(Byte2FloatFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Float2ShortFunction andThenShort(Double2ShortFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Short2DoubleFunction composeShort(Short2FloatFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Float2IntFunction andThenInt(Double2IntFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Int2DoubleFunction composeInt(Int2FloatFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Float2LongFunction andThenLong(Double2LongFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Long2DoubleFunction composeLong(Long2FloatFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Float2CharFunction andThenChar(Double2CharFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Char2DoubleFunction composeChar(Char2FloatFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Float2FloatFunction andThenFloat(Double2FloatFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Float2DoubleFunction composeFloat(Float2FloatFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Float2DoubleFunction andThenDouble(Double2DoubleFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Double2DoubleFunction composeDouble(Double2FloatFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Float2ObjectFunction andThenObject(Double2ObjectFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Object2DoubleFunction composeObject(Object2FloatFunction before) {
      return (k) -> this.get(before.getFloat(k));
   }

   default Float2ReferenceFunction andThenReference(Double2ReferenceFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Reference2DoubleFunction composeReference(Reference2FloatFunction before) {
      return (k) -> this.get(before.getFloat(k));
   }
}
