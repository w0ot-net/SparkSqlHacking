package shaded.parquet.it.unimi.dsi.fastutil.longs;

import java.util.function.Function;
import java.util.function.LongToIntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2LongFunction;

@FunctionalInterface
public interface Long2CharFunction extends shaded.parquet.it.unimi.dsi.fastutil.Function, LongToIntFunction {
   default int applyAsInt(long operand) {
      return this.get(operand);
   }

   default char put(long key, char value) {
      throw new UnsupportedOperationException();
   }

   char get(long var1);

   default char getOrDefault(long key, char defaultValue) {
      char v;
      return (v = this.get(key)) == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   default char remove(long key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default Character put(Long key, Character value) {
      long k = key;
      boolean containsKey = this.containsKey(k);
      char v = this.put(k, value);
      return containsKey ? v : null;
   }

   /** @deprecated */
   @Deprecated
   default Character get(Object key) {
      if (key == null) {
         return null;
      } else {
         long k = (Long)key;
         char v;
         return (v = this.get(k)) == this.defaultReturnValue() && !this.containsKey(k) ? null : v;
      }
   }

   /** @deprecated */
   @Deprecated
   default Character getOrDefault(Object key, Character defaultValue) {
      if (key == null) {
         return defaultValue;
      } else {
         long k = (Long)key;
         char v = this.get(k);
         return v == this.defaultReturnValue() && !this.containsKey(k) ? defaultValue : v;
      }
   }

   /** @deprecated */
   @Deprecated
   default Character remove(Object key) {
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

   default void defaultReturnValue(char rv) {
      throw new UnsupportedOperationException();
   }

   default char defaultReturnValue() {
      return '\u0000';
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

   default Long2ByteFunction andThenByte(Char2ByteFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Byte2CharFunction composeByte(Byte2LongFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Long2ShortFunction andThenShort(Char2ShortFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Short2CharFunction composeShort(Short2LongFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Long2IntFunction andThenInt(Char2IntFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Int2CharFunction composeInt(Int2LongFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Long2LongFunction andThenLong(Char2LongFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Long2CharFunction composeLong(Long2LongFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Long2CharFunction andThenChar(Char2CharFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Char2CharFunction composeChar(Char2LongFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Long2FloatFunction andThenFloat(Char2FloatFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Float2CharFunction composeFloat(Float2LongFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Long2DoubleFunction andThenDouble(Char2DoubleFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Double2CharFunction composeDouble(Double2LongFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Long2ObjectFunction andThenObject(Char2ObjectFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Object2CharFunction composeObject(Object2LongFunction before) {
      return (k) -> this.get(before.getLong(k));
   }

   default Long2ReferenceFunction andThenReference(Char2ReferenceFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Reference2CharFunction composeReference(Reference2LongFunction before) {
      return (k) -> this.get(before.getLong(k));
   }
}
