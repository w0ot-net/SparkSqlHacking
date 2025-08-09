package shaded.parquet.it.unimi.dsi.fastutil.doubles;

import java.util.function.DoubleFunction;
import java.util.function.Function;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2ByteFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2FloatFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2LongFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2ObjectFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2ShortFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2DoubleFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2ReferenceFunction;

@FunctionalInterface
public interface Double2ReferenceFunction extends shaded.parquet.it.unimi.dsi.fastutil.Function, DoubleFunction {
   default Object apply(double operand) {
      return this.get(operand);
   }

   default Object put(double key, Object value) {
      throw new UnsupportedOperationException();
   }

   Object get(double var1);

   default Object getOrDefault(double key, Object defaultValue) {
      V v;
      return (v = (V)this.get(key)) == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   default Object remove(double key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default Object put(Double key, Object value) {
      double k = key;
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
         double k = (Double)key;
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
         double k = (Double)key;
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
         double k = (Double)key;
         return this.containsKey(k) ? this.remove(k) : null;
      }
   }

   default boolean containsKey(double key) {
      return true;
   }

   /** @deprecated */
   @Deprecated
   default boolean containsKey(Object key) {
      return key == null ? false : this.containsKey((Double)key);
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

   default Double2ByteFunction andThenByte(Reference2ByteFunction after) {
      return (k) -> after.getByte(this.get(k));
   }

   default Byte2ReferenceFunction composeByte(Byte2DoubleFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Double2ShortFunction andThenShort(Reference2ShortFunction after) {
      return (k) -> after.getShort(this.get(k));
   }

   default Short2ReferenceFunction composeShort(Short2DoubleFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Double2IntFunction andThenInt(Reference2IntFunction after) {
      return (k) -> after.getInt(this.get(k));
   }

   default Int2ReferenceFunction composeInt(Int2DoubleFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Double2LongFunction andThenLong(Reference2LongFunction after) {
      return (k) -> after.getLong(this.get(k));
   }

   default Long2ReferenceFunction composeLong(Long2DoubleFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Double2CharFunction andThenChar(Reference2CharFunction after) {
      return (k) -> after.getChar(this.get(k));
   }

   default Char2ReferenceFunction composeChar(Char2DoubleFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Double2FloatFunction andThenFloat(Reference2FloatFunction after) {
      return (k) -> after.getFloat(this.get(k));
   }

   default Float2ReferenceFunction composeFloat(Float2DoubleFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Double2DoubleFunction andThenDouble(Reference2DoubleFunction after) {
      return (k) -> after.getDouble(this.get(k));
   }

   default Double2ReferenceFunction composeDouble(Double2DoubleFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Double2ObjectFunction andThenObject(Reference2ObjectFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Object2ReferenceFunction composeObject(Object2DoubleFunction before) {
      return (k) -> this.get(before.getDouble(k));
   }

   default Double2ReferenceFunction andThenReference(Reference2ReferenceFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Reference2ReferenceFunction composeReference(Reference2DoubleFunction before) {
      return (k) -> this.get(before.getDouble(k));
   }
}
