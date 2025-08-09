package shaded.parquet.it.unimi.dsi.fastutil.shorts;

import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import shaded.parquet.it.unimi.dsi.fastutil.SafeMath;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2ShortFunction;

@FunctionalInterface
public interface Short2ShortFunction extends shaded.parquet.it.unimi.dsi.fastutil.Function, IntUnaryOperator {
   /** @deprecated */
   @Deprecated
   default int applyAsInt(int operand) {
      return this.get(SafeMath.safeIntToShort(operand));
   }

   default short put(short key, short value) {
      throw new UnsupportedOperationException();
   }

   short get(short var1);

   default short getOrDefault(short key, short defaultValue) {
      short v;
      return (v = this.get(key)) == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   default short remove(short key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default Short put(Short key, Short value) {
      short k = key;
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
         short k = (Short)key;
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
         short k = (Short)key;
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
         short k = (Short)key;
         return this.containsKey(k) ? this.remove(k) : null;
      }
   }

   default boolean containsKey(short key) {
      return true;
   }

   /** @deprecated */
   @Deprecated
   default boolean containsKey(Object key) {
      return key == null ? false : this.containsKey((Short)key);
   }

   default void defaultReturnValue(short rv) {
      throw new UnsupportedOperationException();
   }

   default short defaultReturnValue() {
      return 0;
   }

   static Short2ShortFunction identity() {
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

   default Short2ByteFunction andThenByte(Short2ByteFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Byte2ShortFunction composeByte(Byte2ShortFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Short2ShortFunction andThenShort(Short2ShortFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Short2ShortFunction composeShort(Short2ShortFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Short2IntFunction andThenInt(Short2IntFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Int2ShortFunction composeInt(Int2ShortFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Short2LongFunction andThenLong(Short2LongFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Long2ShortFunction composeLong(Long2ShortFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Short2CharFunction andThenChar(Short2CharFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Char2ShortFunction composeChar(Char2ShortFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Short2FloatFunction andThenFloat(Short2FloatFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Float2ShortFunction composeFloat(Float2ShortFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Short2DoubleFunction andThenDouble(Short2DoubleFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Double2ShortFunction composeDouble(Double2ShortFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Short2ObjectFunction andThenObject(Short2ObjectFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Object2ShortFunction composeObject(Object2ShortFunction before) {
      return (k) -> this.get(before.getShort(k));
   }

   default Short2ReferenceFunction andThenReference(Short2ReferenceFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Reference2ShortFunction composeReference(Reference2ShortFunction before) {
      return (k) -> this.get(before.getShort(k));
   }
}
