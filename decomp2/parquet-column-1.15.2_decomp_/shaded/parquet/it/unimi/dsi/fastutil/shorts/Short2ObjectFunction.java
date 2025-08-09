package shaded.parquet.it.unimi.dsi.fastutil.shorts;

import java.util.function.Function;
import java.util.function.IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.SafeMath;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2ShortFunction;

@FunctionalInterface
public interface Short2ObjectFunction extends shaded.parquet.it.unimi.dsi.fastutil.Function, IntFunction {
   /** @deprecated */
   @Deprecated
   default Object apply(int operand) {
      return this.get(SafeMath.safeIntToShort(operand));
   }

   default Object put(short key, Object value) {
      throw new UnsupportedOperationException();
   }

   Object get(short var1);

   default Object getOrDefault(short key, Object defaultValue) {
      V v;
      return (v = (V)this.get(key)) == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   default Object remove(short key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default Object put(Short key, Object value) {
      short k = key;
      boolean containsKey = this.containsKey(k);
      V v = (V)this.put(k, value);
      return containsKey ? v : null;
   }

   /** @deprecated */
   @Deprecated
   default Object get(Object key) {
      if (key == null) {
         return null;
      } else {
         short k = (Short)key;
         V v;
         return (v = (V)this.get(k)) == this.defaultReturnValue() && !this.containsKey(k) ? null : v;
      }
   }

   /** @deprecated */
   @Deprecated
   default Object getOrDefault(Object key, Object defaultValue) {
      if (key == null) {
         return defaultValue;
      } else {
         short k = (Short)key;
         V v = (V)this.get(k);
         return v == this.defaultReturnValue() && !this.containsKey(k) ? defaultValue : v;
      }
   }

   /** @deprecated */
   @Deprecated
   default Object remove(Object key) {
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

   default void defaultReturnValue(Object rv) {
      throw new UnsupportedOperationException();
   }

   default Object defaultReturnValue() {
      return null;
   }

   /** @deprecated */
   @Deprecated
   default Function compose(Function before) {
      return shaded.parquet.it.unimi.dsi.fastutil.Function.super.compose(before);
   }

   default Short2ByteFunction andThenByte(Object2ByteFunction after) {
      return (k) -> after.getByte(this.get(k));
   }

   default Byte2ObjectFunction composeByte(Byte2ShortFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Short2ShortFunction andThenShort(Object2ShortFunction after) {
      return (k) -> after.getShort(this.get(k));
   }

   default Short2ObjectFunction composeShort(Short2ShortFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Short2IntFunction andThenInt(Object2IntFunction after) {
      return (k) -> after.getInt(this.get(k));
   }

   default Int2ObjectFunction composeInt(Int2ShortFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Short2LongFunction andThenLong(Object2LongFunction after) {
      return (k) -> after.getLong(this.get(k));
   }

   default Long2ObjectFunction composeLong(Long2ShortFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Short2CharFunction andThenChar(Object2CharFunction after) {
      return (k) -> after.getChar(this.get(k));
   }

   default Char2ObjectFunction composeChar(Char2ShortFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Short2FloatFunction andThenFloat(Object2FloatFunction after) {
      return (k) -> after.getFloat(this.get(k));
   }

   default Float2ObjectFunction composeFloat(Float2ShortFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Short2DoubleFunction andThenDouble(Object2DoubleFunction after) {
      return (k) -> after.getDouble(this.get(k));
   }

   default Double2ObjectFunction composeDouble(Double2ShortFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Short2ObjectFunction andThenObject(Object2ObjectFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Object2ObjectFunction composeObject(Object2ShortFunction before) {
      return (k) -> this.get(before.getShort(k));
   }

   default Short2ReferenceFunction andThenReference(Object2ReferenceFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Reference2ObjectFunction composeReference(Reference2ShortFunction before) {
      return (k) -> this.get(before.getShort(k));
   }
}
