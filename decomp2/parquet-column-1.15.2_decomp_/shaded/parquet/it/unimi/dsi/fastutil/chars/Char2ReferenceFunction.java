package shaded.parquet.it.unimi.dsi.fastutil.chars;

import java.util.function.Function;
import java.util.function.IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.SafeMath;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2ReferenceFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2CharFunction;
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
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2ReferenceFunction;

@FunctionalInterface
public interface Char2ReferenceFunction extends shaded.parquet.it.unimi.dsi.fastutil.Function, IntFunction {
   /** @deprecated */
   @Deprecated
   default Object apply(int operand) {
      return this.get(SafeMath.safeIntToChar(operand));
   }

   default Object put(char key, Object value) {
      throw new UnsupportedOperationException();
   }

   Object get(char var1);

   default Object getOrDefault(char key, Object defaultValue) {
      V v;
      return (v = (V)this.get(key)) == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   default Object remove(char key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default Object put(Character key, Object value) {
      char k = key;
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
         char k = (Character)key;
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
         char k = (Character)key;
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

   default Char2ByteFunction andThenByte(Reference2ByteFunction after) {
      return (k) -> after.getByte(this.get(k));
   }

   default Byte2ReferenceFunction composeByte(Byte2CharFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Char2ShortFunction andThenShort(Reference2ShortFunction after) {
      return (k) -> after.getShort(this.get(k));
   }

   default Short2ReferenceFunction composeShort(Short2CharFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Char2IntFunction andThenInt(Reference2IntFunction after) {
      return (k) -> after.getInt(this.get(k));
   }

   default Int2ReferenceFunction composeInt(Int2CharFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Char2LongFunction andThenLong(Reference2LongFunction after) {
      return (k) -> after.getLong(this.get(k));
   }

   default Long2ReferenceFunction composeLong(Long2CharFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Char2CharFunction andThenChar(Reference2CharFunction after) {
      return (k) -> after.getChar(this.get(k));
   }

   default Char2ReferenceFunction composeChar(Char2CharFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Char2FloatFunction andThenFloat(Reference2FloatFunction after) {
      return (k) -> after.getFloat(this.get(k));
   }

   default Float2ReferenceFunction composeFloat(Float2CharFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Char2DoubleFunction andThenDouble(Reference2DoubleFunction after) {
      return (k) -> after.getDouble(this.get(k));
   }

   default Double2ReferenceFunction composeDouble(Double2CharFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Char2ObjectFunction andThenObject(Reference2ObjectFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Object2ReferenceFunction composeObject(Object2CharFunction before) {
      return (k) -> this.get(before.getChar(k));
   }

   default Char2ReferenceFunction andThenReference(Reference2ReferenceFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Reference2ReferenceFunction composeReference(Reference2CharFunction before) {
      return (k) -> this.get(before.getChar(k));
   }
}
