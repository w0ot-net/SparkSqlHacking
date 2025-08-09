package shaded.parquet.it.unimi.dsi.fastutil.bytes;

import java.util.function.Function;
import java.util.function.IntToDoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.SafeMath;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2FloatFunction;

@FunctionalInterface
public interface Byte2FloatFunction extends shaded.parquet.it.unimi.dsi.fastutil.Function, IntToDoubleFunction {
   /** @deprecated */
   @Deprecated
   default double applyAsDouble(int operand) {
      return (double)this.get(SafeMath.safeIntToByte(operand));
   }

   default float put(byte key, float value) {
      throw new UnsupportedOperationException();
   }

   float get(byte var1);

   default float getOrDefault(byte key, float defaultValue) {
      float v;
      return (v = this.get(key)) == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   default float remove(byte key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default Float put(Byte key, Float value) {
      byte k = key;
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
         byte k = (Byte)key;
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
         byte k = (Byte)key;
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
         byte k = (Byte)key;
         return this.containsKey(k) ? this.remove(k) : null;
      }
   }

   default boolean containsKey(byte key) {
      return true;
   }

   /** @deprecated */
   @Deprecated
   default boolean containsKey(Object key) {
      return key == null ? false : this.containsKey((Byte)key);
   }

   default void defaultReturnValue(float rv) {
      throw new UnsupportedOperationException();
   }

   default float defaultReturnValue() {
      return 0.0F;
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

   default Byte2ByteFunction andThenByte(Float2ByteFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Byte2FloatFunction composeByte(Byte2ByteFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Byte2ShortFunction andThenShort(Float2ShortFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Short2FloatFunction composeShort(Short2ByteFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Byte2IntFunction andThenInt(Float2IntFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Int2FloatFunction composeInt(Int2ByteFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Byte2LongFunction andThenLong(Float2LongFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Long2FloatFunction composeLong(Long2ByteFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Byte2CharFunction andThenChar(Float2CharFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Char2FloatFunction composeChar(Char2ByteFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Byte2FloatFunction andThenFloat(Float2FloatFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Float2FloatFunction composeFloat(Float2ByteFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Byte2DoubleFunction andThenDouble(Float2DoubleFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Double2FloatFunction composeDouble(Double2ByteFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Byte2ObjectFunction andThenObject(Float2ObjectFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Object2FloatFunction composeObject(Object2ByteFunction before) {
      return (k) -> this.get(before.getByte(k));
   }

   default Byte2ReferenceFunction andThenReference(Float2ReferenceFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Reference2FloatFunction composeReference(Reference2ByteFunction before) {
      return (k) -> this.get(before.getByte(k));
   }
}
