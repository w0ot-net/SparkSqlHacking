package shaded.parquet.it.unimi.dsi.fastutil.objects;

import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2ReferenceFunction;
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
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2ReferenceFunction;

@FunctionalInterface
public interface Reference2FloatFunction extends shaded.parquet.it.unimi.dsi.fastutil.Function, ToDoubleFunction {
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

   default Reference2ByteFunction andThenByte(Float2ByteFunction after) {
      return (k) -> after.get(this.getFloat(k));
   }

   default Byte2FloatFunction composeByte(Byte2ReferenceFunction before) {
      return (k) -> this.getFloat(before.get(k));
   }

   default Reference2ShortFunction andThenShort(Float2ShortFunction after) {
      return (k) -> after.get(this.getFloat(k));
   }

   default Short2FloatFunction composeShort(Short2ReferenceFunction before) {
      return (k) -> this.getFloat(before.get(k));
   }

   default Reference2IntFunction andThenInt(Float2IntFunction after) {
      return (k) -> after.get(this.getFloat(k));
   }

   default Int2FloatFunction composeInt(Int2ReferenceFunction before) {
      return (k) -> this.getFloat(before.get(k));
   }

   default Reference2LongFunction andThenLong(Float2LongFunction after) {
      return (k) -> after.get(this.getFloat(k));
   }

   default Long2FloatFunction composeLong(Long2ReferenceFunction before) {
      return (k) -> this.getFloat(before.get(k));
   }

   default Reference2CharFunction andThenChar(Float2CharFunction after) {
      return (k) -> after.get(this.getFloat(k));
   }

   default Char2FloatFunction composeChar(Char2ReferenceFunction before) {
      return (k) -> this.getFloat(before.get(k));
   }

   default Reference2FloatFunction andThenFloat(Float2FloatFunction after) {
      return (k) -> after.get(this.getFloat(k));
   }

   default Float2FloatFunction composeFloat(Float2ReferenceFunction before) {
      return (k) -> this.getFloat(before.get(k));
   }

   default Reference2DoubleFunction andThenDouble(Float2DoubleFunction after) {
      return (k) -> after.get(this.getFloat(k));
   }

   default Double2FloatFunction composeDouble(Double2ReferenceFunction before) {
      return (k) -> this.getFloat(before.get(k));
   }

   default Reference2ObjectFunction andThenObject(Float2ObjectFunction after) {
      return (k) -> after.get(this.getFloat(k));
   }

   default Object2FloatFunction composeObject(Object2ReferenceFunction before) {
      return (k) -> this.getFloat(before.get(k));
   }

   default Reference2ReferenceFunction andThenReference(Float2ReferenceFunction after) {
      return (k) -> after.get(this.getFloat(k));
   }

   default Reference2FloatFunction composeReference(Reference2ReferenceFunction before) {
      return (k) -> this.getFloat(before.get(k));
   }
}
