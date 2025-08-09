package shaded.parquet.it.unimi.dsi.fastutil.longs;

import java.util.function.Function;
import java.util.function.LongToIntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2LongFunction;
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
public interface Long2ShortFunction extends shaded.parquet.it.unimi.dsi.fastutil.Function, LongToIntFunction {
   default int applyAsInt(long operand) {
      return this.get(operand);
   }

   default short put(long key, short value) {
      throw new UnsupportedOperationException();
   }

   short get(long var1);

   default short getOrDefault(long key, short defaultValue) {
      short v;
      return (v = this.get(key)) == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   default short remove(long key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default Short put(Long key, Short value) {
      long k = key;
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
         long k = (Long)key;
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
         long k = (Long)key;
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
         long k = (Long)key;
         return this.containsKey(k) ? this.remove(k) : null;
      }
   }

   default boolean containsKey(long key) {
      return true;
   }

   /** @deprecated */
   @Deprecated
   default boolean containsKey(Object key) {
      return key == null ? false : this.containsKey((Long)key);
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

   default Long2ByteFunction andThenByte(Short2ByteFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Byte2ShortFunction composeByte(Byte2LongFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Long2ShortFunction andThenShort(Short2ShortFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Short2ShortFunction composeShort(Short2LongFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Long2IntFunction andThenInt(Short2IntFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Int2ShortFunction composeInt(Int2LongFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Long2LongFunction andThenLong(Short2LongFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Long2ShortFunction composeLong(Long2LongFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Long2CharFunction andThenChar(Short2CharFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Char2ShortFunction composeChar(Char2LongFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Long2FloatFunction andThenFloat(Short2FloatFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Float2ShortFunction composeFloat(Float2LongFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Long2DoubleFunction andThenDouble(Short2DoubleFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Double2ShortFunction composeDouble(Double2LongFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Long2ObjectFunction andThenObject(Short2ObjectFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Object2ShortFunction composeObject(Object2LongFunction before) {
      return (k) -> this.get(before.getLong(k));
   }

   default Long2ReferenceFunction andThenReference(Short2ReferenceFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Reference2ShortFunction composeReference(Reference2LongFunction before) {
      return (k) -> this.get(before.getLong(k));
   }
}
