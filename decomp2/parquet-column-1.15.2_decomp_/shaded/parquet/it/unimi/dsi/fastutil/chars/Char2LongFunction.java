package shaded.parquet.it.unimi.dsi.fastutil.chars;

import java.util.function.Function;
import java.util.function.IntToLongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.SafeMath;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2LongFunction;

@FunctionalInterface
public interface Char2LongFunction extends shaded.parquet.it.unimi.dsi.fastutil.Function, IntToLongFunction {
   /** @deprecated */
   @Deprecated
   default long applyAsLong(int operand) {
      return this.get(SafeMath.safeIntToChar(operand));
   }

   default long put(char key, long value) {
      throw new UnsupportedOperationException();
   }

   long get(char var1);

   default long getOrDefault(char key, long defaultValue) {
      long v;
      return (v = this.get(key)) == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   default long remove(char key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default Long put(Character key, Long value) {
      char k = key;
      boolean containsKey = this.containsKey(k);
      long v = this.put(k, value);
      return containsKey ? v : null;
   }

   /** @deprecated */
   @Deprecated
   default Long get(Object key) {
      if (key == null) {
         return null;
      } else {
         char k = (Character)key;
         long v;
         return (v = this.get(k)) == this.defaultReturnValue() && !this.containsKey(k) ? null : v;
      }
   }

   /** @deprecated */
   @Deprecated
   default Long getOrDefault(Object key, Long defaultValue) {
      if (key == null) {
         return defaultValue;
      } else {
         char k = (Character)key;
         long v = this.get(k);
         return v == this.defaultReturnValue() && !this.containsKey(k) ? defaultValue : v;
      }
   }

   /** @deprecated */
   @Deprecated
   default Long remove(Object key) {
      if (key == null) {
         return null;
      } else {
         char k = (Character)key;
         return this.containsKey(k) ? this.remove(k) : null;
      }
   }

   default boolean containsKey(char key) {
      return true;
   }

   /** @deprecated */
   @Deprecated
   default boolean containsKey(Object key) {
      return key == null ? false : this.containsKey((Character)key);
   }

   default void defaultReturnValue(long rv) {
      throw new UnsupportedOperationException();
   }

   default long defaultReturnValue() {
      return 0L;
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

   default Char2ByteFunction andThenByte(Long2ByteFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Byte2LongFunction composeByte(Byte2CharFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Char2ShortFunction andThenShort(Long2ShortFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Short2LongFunction composeShort(Short2CharFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Char2IntFunction andThenInt(Long2IntFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Int2LongFunction composeInt(Int2CharFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Char2LongFunction andThenLong(Long2LongFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Long2LongFunction composeLong(Long2CharFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Char2CharFunction andThenChar(Long2CharFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Char2LongFunction composeChar(Char2CharFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Char2FloatFunction andThenFloat(Long2FloatFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Float2LongFunction composeFloat(Float2CharFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Char2DoubleFunction andThenDouble(Long2DoubleFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Double2LongFunction composeDouble(Double2CharFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Char2ObjectFunction andThenObject(Long2ObjectFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Object2LongFunction composeObject(Object2CharFunction before) {
      return (k) -> this.get(before.getChar(k));
   }

   default Char2ReferenceFunction andThenReference(Long2ReferenceFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Reference2LongFunction composeReference(Reference2CharFunction before) {
      return (k) -> this.get(before.getChar(k));
   }
}
