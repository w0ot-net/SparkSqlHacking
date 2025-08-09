package shaded.parquet.it.unimi.dsi.fastutil.ints;

import java.util.function.Function;
import java.util.function.IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2IntFunction;
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
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2ReferenceFunction;

@FunctionalInterface
public interface Int2ReferenceFunction extends shaded.parquet.it.unimi.dsi.fastutil.Function, IntFunction {
   default Object apply(int operand) {
      return this.get(operand);
   }

   default Object put(int key, Object value) {
      throw new UnsupportedOperationException();
   }

   Object get(int var1);

   default Object getOrDefault(int key, Object defaultValue) {
      V v;
      return (v = (V)this.get(key)) == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   default Object remove(int key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default Object put(Integer key, Object value) {
      int k = key;
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
         int k = (Integer)key;
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
         int k = (Integer)key;
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
         int k = (Integer)key;
         return this.containsKey(k) ? this.remove(k) : null;
      }
   }

   default boolean containsKey(int key) {
      return true;
   }

   /** @deprecated */
   @Deprecated
   default boolean containsKey(Object key) {
      return key == null ? false : this.containsKey((Integer)key);
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

   default Int2ByteFunction andThenByte(Reference2ByteFunction after) {
      return (k) -> after.getByte(this.get(k));
   }

   default Byte2ReferenceFunction composeByte(Byte2IntFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Int2ShortFunction andThenShort(Reference2ShortFunction after) {
      return (k) -> after.getShort(this.get(k));
   }

   default Short2ReferenceFunction composeShort(Short2IntFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Int2IntFunction andThenInt(Reference2IntFunction after) {
      return (k) -> after.getInt(this.get(k));
   }

   default Int2ReferenceFunction composeInt(Int2IntFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Int2LongFunction andThenLong(Reference2LongFunction after) {
      return (k) -> after.getLong(this.get(k));
   }

   default Long2ReferenceFunction composeLong(Long2IntFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Int2CharFunction andThenChar(Reference2CharFunction after) {
      return (k) -> after.getChar(this.get(k));
   }

   default Char2ReferenceFunction composeChar(Char2IntFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Int2FloatFunction andThenFloat(Reference2FloatFunction after) {
      return (k) -> after.getFloat(this.get(k));
   }

   default Float2ReferenceFunction composeFloat(Float2IntFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Int2DoubleFunction andThenDouble(Reference2DoubleFunction after) {
      return (k) -> after.getDouble(this.get(k));
   }

   default Double2ReferenceFunction composeDouble(Double2IntFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Int2ObjectFunction andThenObject(Reference2ObjectFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Object2ReferenceFunction composeObject(Object2IntFunction before) {
      return (k) -> this.get(before.getInt(k));
   }

   default Int2ReferenceFunction andThenReference(Reference2ReferenceFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Reference2ReferenceFunction composeReference(Reference2IntFunction before) {
      return (k) -> this.get(before.getInt(k));
   }
}
