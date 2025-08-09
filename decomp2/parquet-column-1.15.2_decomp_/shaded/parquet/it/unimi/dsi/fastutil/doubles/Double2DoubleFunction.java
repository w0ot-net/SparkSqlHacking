package shaded.parquet.it.unimi.dsi.fastutil.doubles;

import java.util.function.Function;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2DoubleFunction;

@FunctionalInterface
public interface Double2DoubleFunction extends shaded.parquet.it.unimi.dsi.fastutil.Function, java.util.function.DoubleUnaryOperator {
   default double applyAsDouble(double operand) {
      return this.get(operand);
   }

   default double put(double key, double value) {
      throw new UnsupportedOperationException();
   }

   double get(double var1);

   default double getOrDefault(double key, double defaultValue) {
      double v;
      return (v = this.get(key)) == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   default double remove(double key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default Double put(Double key, Double value) {
      double k = key;
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
         double k = (Double)key;
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
         double k = (Double)key;
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

   default void defaultReturnValue(double rv) {
      throw new UnsupportedOperationException();
   }

   default double defaultReturnValue() {
      return (double)0.0F;
   }

   static Double2DoubleFunction identity() {
      return (k) -> k;
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

   default Double2ByteFunction andThenByte(Double2ByteFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Byte2DoubleFunction composeByte(Byte2DoubleFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Double2ShortFunction andThenShort(Double2ShortFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Short2DoubleFunction composeShort(Short2DoubleFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Double2IntFunction andThenInt(Double2IntFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Int2DoubleFunction composeInt(Int2DoubleFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Double2LongFunction andThenLong(Double2LongFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Long2DoubleFunction composeLong(Long2DoubleFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Double2CharFunction andThenChar(Double2CharFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Char2DoubleFunction composeChar(Char2DoubleFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Double2FloatFunction andThenFloat(Double2FloatFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Float2DoubleFunction composeFloat(Float2DoubleFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Double2DoubleFunction andThenDouble(Double2DoubleFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Double2DoubleFunction composeDouble(Double2DoubleFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Double2ObjectFunction andThenObject(Double2ObjectFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Object2DoubleFunction composeObject(Object2DoubleFunction before) {
      return (k) -> this.get(before.getDouble(k));
   }

   default Double2ReferenceFunction andThenReference(Double2ReferenceFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Reference2DoubleFunction composeReference(Reference2DoubleFunction before) {
      return (k) -> this.get(before.getDouble(k));
   }
}
