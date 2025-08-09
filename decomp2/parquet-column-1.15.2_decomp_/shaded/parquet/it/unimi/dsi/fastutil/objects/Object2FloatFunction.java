package shaded.parquet.it.unimi.dsi.fastutil.objects;

import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2ObjectFunction;

@FunctionalInterface
public interface Object2FloatFunction extends shaded.parquet.it.unimi.dsi.fastutil.Function, ToDoubleFunction {
   default double applyAsDouble(Object operand) {
      return (double)this.getFloat(operand);
   }

   default float put(Object key, float value) {
      throw new UnsupportedOperationException();
   }

   float getFloat(Object var1);

   default float getOrDefault(Object key, float defaultValue) {
      float v;
      return (v = this.getFloat(key)) == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   default float removeFloat(Object key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default Float put(Object key, Float value) {
      boolean containsKey = this.containsKey(key);
      float v = this.put(key, value);
      return containsKey ? v : null;
   }

   /** @deprecated */
   @Deprecated
   default Float get(Object key) {
      float v;
      return (v = this.getFloat(key)) == this.defaultReturnValue() && !this.containsKey(key) ? null : v;
   }

   /** @deprecated */
   @Deprecated
   default Float getOrDefault(Object key, Float defaultValue) {
      float v = this.getFloat(key);
      return v == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   /** @deprecated */
   @Deprecated
   default Float remove(Object key) {
      return this.containsKey(key) ? this.removeFloat(key) : null;
   }

   default void defaultReturnValue(float rv) {
      throw new UnsupportedOperationException();
   }

   default float defaultReturnValue() {
      return 0.0F;
   }

   /** @deprecated */
   @Deprecated
   default Function andThen(Function after) {
      return shaded.parquet.it.unimi.dsi.fastutil.Function.super.andThen(after);
   }

   default Object2ByteFunction andThenByte(Float2ByteFunction after) {
      return (k) -> after.get(this.getFloat(k));
   }

   default Byte2FloatFunction composeByte(Byte2ObjectFunction before) {
      return (k) -> this.getFloat(before.get(k));
   }

   default Object2ShortFunction andThenShort(Float2ShortFunction after) {
      return (k) -> after.get(this.getFloat(k));
   }

   default Short2FloatFunction composeShort(Short2ObjectFunction before) {
      return (k) -> this.getFloat(before.get(k));
   }

   default Object2IntFunction andThenInt(Float2IntFunction after) {
      return (k) -> after.get(this.getFloat(k));
   }

   default Int2FloatFunction composeInt(Int2ObjectFunction before) {
      return (k) -> this.getFloat(before.get(k));
   }

   default Object2LongFunction andThenLong(Float2LongFunction after) {
      return (k) -> after.get(this.getFloat(k));
   }

   default Long2FloatFunction composeLong(Long2ObjectFunction before) {
      return (k) -> this.getFloat(before.get(k));
   }

   default Object2CharFunction andThenChar(Float2CharFunction after) {
      return (k) -> after.get(this.getFloat(k));
   }

   default Char2FloatFunction composeChar(Char2ObjectFunction before) {
      return (k) -> this.getFloat(before.get(k));
   }

   default Object2FloatFunction andThenFloat(Float2FloatFunction after) {
      return (k) -> after.get(this.getFloat(k));
   }

   default Float2FloatFunction composeFloat(Float2ObjectFunction before) {
      return (k) -> this.getFloat(before.get(k));
   }

   default Object2DoubleFunction andThenDouble(Float2DoubleFunction after) {
      return (k) -> after.get(this.getFloat(k));
   }

   default Double2FloatFunction composeDouble(Double2ObjectFunction before) {
      return (k) -> this.getFloat(before.get(k));
   }

   default Object2ObjectFunction andThenObject(Float2ObjectFunction after) {
      return (k) -> after.get(this.getFloat(k));
   }

   default Object2FloatFunction composeObject(Object2ObjectFunction before) {
      return (k) -> this.getFloat(before.get(k));
   }

   default Object2ReferenceFunction andThenReference(Float2ReferenceFunction after) {
      return (k) -> after.get(this.getFloat(k));
   }

   default Reference2FloatFunction composeReference(Reference2ObjectFunction before) {
      return (k) -> this.getFloat(before.get(k));
   }
}
