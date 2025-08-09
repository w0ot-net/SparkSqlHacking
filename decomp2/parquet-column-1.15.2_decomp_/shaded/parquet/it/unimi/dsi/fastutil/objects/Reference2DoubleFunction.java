package shaded.parquet.it.unimi.dsi.fastutil.objects;

import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2ReferenceFunction;

@FunctionalInterface
public interface Reference2DoubleFunction extends shaded.parquet.it.unimi.dsi.fastutil.Function, ToDoubleFunction {
   default double applyAsDouble(Object operand) {
      return this.getDouble(operand);
   }

   default double put(Object key, double value) {
      throw new UnsupportedOperationException();
   }

   double getDouble(Object var1);

   default double getOrDefault(Object key, double defaultValue) {
      double v;
      return (v = this.getDouble(key)) == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   default double removeDouble(Object key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default Double put(Object key, Double value) {
      boolean containsKey = this.containsKey(key);
      double v = this.put(key, value);
      return containsKey ? v : null;
   }

   /** @deprecated */
   @Deprecated
   default Double get(Object key) {
      double v;
      return (v = this.getDouble(key)) == this.defaultReturnValue() && !this.containsKey(key) ? null : v;
   }

   /** @deprecated */
   @Deprecated
   default Double getOrDefault(Object key, Double defaultValue) {
      double v = this.getDouble(key);
      return v == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   /** @deprecated */
   @Deprecated
   default Double remove(Object key) {
      return this.containsKey(key) ? this.removeDouble(key) : null;
   }

   default void defaultReturnValue(double rv) {
      throw new UnsupportedOperationException();
   }

   default double defaultReturnValue() {
      return (double)0.0F;
   }

   /** @deprecated */
   @Deprecated
   default Function andThen(Function after) {
      return shaded.parquet.it.unimi.dsi.fastutil.Function.super.andThen(after);
   }

   default Reference2ByteFunction andThenByte(Double2ByteFunction after) {
      return (k) -> after.get(this.getDouble(k));
   }

   default Byte2DoubleFunction composeByte(Byte2ReferenceFunction before) {
      return (k) -> this.getDouble(before.get(k));
   }

   default Reference2ShortFunction andThenShort(Double2ShortFunction after) {
      return (k) -> after.get(this.getDouble(k));
   }

   default Short2DoubleFunction composeShort(Short2ReferenceFunction before) {
      return (k) -> this.getDouble(before.get(k));
   }

   default Reference2IntFunction andThenInt(Double2IntFunction after) {
      return (k) -> after.get(this.getDouble(k));
   }

   default Int2DoubleFunction composeInt(Int2ReferenceFunction before) {
      return (k) -> this.getDouble(before.get(k));
   }

   default Reference2LongFunction andThenLong(Double2LongFunction after) {
      return (k) -> after.get(this.getDouble(k));
   }

   default Long2DoubleFunction composeLong(Long2ReferenceFunction before) {
      return (k) -> this.getDouble(before.get(k));
   }

   default Reference2CharFunction andThenChar(Double2CharFunction after) {
      return (k) -> after.get(this.getDouble(k));
   }

   default Char2DoubleFunction composeChar(Char2ReferenceFunction before) {
      return (k) -> this.getDouble(before.get(k));
   }

   default Reference2FloatFunction andThenFloat(Double2FloatFunction after) {
      return (k) -> after.get(this.getDouble(k));
   }

   default Float2DoubleFunction composeFloat(Float2ReferenceFunction before) {
      return (k) -> this.getDouble(before.get(k));
   }

   default Reference2DoubleFunction andThenDouble(Double2DoubleFunction after) {
      return (k) -> after.get(this.getDouble(k));
   }

   default Double2DoubleFunction composeDouble(Double2ReferenceFunction before) {
      return (k) -> this.getDouble(before.get(k));
   }

   default Reference2ObjectFunction andThenObject(Double2ObjectFunction after) {
      return (k) -> after.get(this.getDouble(k));
   }

   default Object2DoubleFunction composeObject(Object2ReferenceFunction before) {
      return (k) -> this.getDouble(before.get(k));
   }

   default Reference2ReferenceFunction andThenReference(Double2ReferenceFunction after) {
      return (k) -> after.get(this.getDouble(k));
   }

   default Reference2DoubleFunction composeReference(Reference2ReferenceFunction before) {
      return (k) -> this.getDouble(before.get(k));
   }
}
