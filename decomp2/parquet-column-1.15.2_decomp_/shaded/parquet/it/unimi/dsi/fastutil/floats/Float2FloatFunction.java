package shaded.parquet.it.unimi.dsi.fastutil.floats;

import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import shaded.parquet.it.unimi.dsi.fastutil.SafeMath;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2FloatFunction;

@FunctionalInterface
public interface Float2FloatFunction extends shaded.parquet.it.unimi.dsi.fastutil.Function, DoubleUnaryOperator {
   /** @deprecated */
   @Deprecated
   default double applyAsDouble(double operand) {
      return (double)this.get(SafeMath.safeDoubleToFloat(operand));
   }

   default float put(float key, float value) {
      throw new UnsupportedOperationException();
   }

   float get(float var1);

   default float getOrDefault(float key, float defaultValue) {
      float v;
      return (v = this.get(key)) == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   default float remove(float key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default Float put(Float key, Float value) {
      float k = key;
      boolean containsKey = this.containsKey(k);
      float v = this.put(k, value);
      return containsKey ? v : null;
   }

   /** @deprecated */
   @Deprecated
   default Float get(Object key) {
      if (key == null) {
         return null;
      } else {
         float k = (Float)key;
         float v;
         return (v = this.get(k)) == this.defaultReturnValue() && !this.containsKey(k) ? null : v;
      }
   }

   /** @deprecated */
   @Deprecated
   default Float getOrDefault(Object key, Float defaultValue) {
      if (key == null) {
         return defaultValue;
      } else {
         float k = (Float)key;
         float v = this.get(k);
         return v == this.defaultReturnValue() && !this.containsKey(k) ? defaultValue : v;
      }
   }

   /** @deprecated */
   @Deprecated
   default Float remove(Object key) {
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

   default void defaultReturnValue(float rv) {
      throw new UnsupportedOperationException();
   }

   default float defaultReturnValue() {
      return 0.0F;
   }

   static Float2FloatFunction identity() {
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

   default Float2ByteFunction andThenByte(Float2ByteFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Byte2FloatFunction composeByte(Byte2FloatFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Float2ShortFunction andThenShort(Float2ShortFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Short2FloatFunction composeShort(Short2FloatFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Float2IntFunction andThenInt(Float2IntFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Int2FloatFunction composeInt(Int2FloatFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Float2LongFunction andThenLong(Float2LongFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Long2FloatFunction composeLong(Long2FloatFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Float2CharFunction andThenChar(Float2CharFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Char2FloatFunction composeChar(Char2FloatFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Float2FloatFunction andThenFloat(Float2FloatFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Float2FloatFunction composeFloat(Float2FloatFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Float2DoubleFunction andThenDouble(Float2DoubleFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Double2FloatFunction composeDouble(Double2FloatFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Float2ObjectFunction andThenObject(Float2ObjectFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Object2FloatFunction composeObject(Object2FloatFunction before) {
      return (k) -> this.get(before.getFloat(k));
   }

   default Float2ReferenceFunction andThenReference(Float2ReferenceFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Reference2FloatFunction composeReference(Reference2FloatFunction before) {
      return (k) -> this.get(before.getFloat(k));
   }
}
