package shaded.parquet.it.unimi.dsi.fastutil.ints;

import java.util.function.Function;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.chars.Char2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2IntFunction;

@FunctionalInterface
public interface Int2IntFunction extends shaded.parquet.it.unimi.dsi.fastutil.Function, java.util.function.IntUnaryOperator {
   default int applyAsInt(int operand) {
      return this.get(operand);
   }

   default int put(int key, int value) {
      throw new UnsupportedOperationException();
   }

   int get(int var1);

   default int getOrDefault(int key, int defaultValue) {
      int v;
      return (v = this.get(key)) == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   default int remove(int key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default Integer put(Integer key, Integer value) {
      int k = key;
      boolean containsKey = this.containsKey(k);
      int v = this.put(k, value);
      return containsKey ? v : null;
   }

   /** @deprecated */
   @Deprecated
   default Integer get(Object key) {
      if (key == null) {
         return null;
      } else {
         int k = (Integer)key;
         int v;
         return (v = this.get(k)) == this.defaultReturnValue() && !this.containsKey(k) ? null : v;
      }
   }

   /** @deprecated */
   @Deprecated
   default Integer getOrDefault(Object key, Integer defaultValue) {
      if (key == null) {
         return defaultValue;
      } else {
         int k = (Integer)key;
         int v = this.get(k);
         return v == this.defaultReturnValue() && !this.containsKey(k) ? defaultValue : v;
      }
   }

   /** @deprecated */
   @Deprecated
   default Integer remove(Object key) {
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

   default void defaultReturnValue(int rv) {
      throw new UnsupportedOperationException();
   }

   default int defaultReturnValue() {
      return 0;
   }

   static Int2IntFunction identity() {
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

   default Int2ByteFunction andThenByte(Int2ByteFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Byte2IntFunction composeByte(Byte2IntFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Int2ShortFunction andThenShort(Int2ShortFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Short2IntFunction composeShort(Short2IntFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Int2IntFunction andThenInt(Int2IntFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Int2IntFunction composeInt(Int2IntFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Int2LongFunction andThenLong(Int2LongFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Long2IntFunction composeLong(Long2IntFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Int2CharFunction andThenChar(Int2CharFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Char2IntFunction composeChar(Char2IntFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Int2FloatFunction andThenFloat(Int2FloatFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Float2IntFunction composeFloat(Float2IntFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Int2DoubleFunction andThenDouble(Int2DoubleFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Double2IntFunction composeDouble(Double2IntFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Int2ObjectFunction andThenObject(Int2ObjectFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Object2IntFunction composeObject(Object2IntFunction before) {
      return (k) -> this.get(before.getInt(k));
   }

   default Int2ReferenceFunction andThenReference(Int2ReferenceFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Reference2IntFunction composeReference(Reference2IntFunction before) {
      return (k) -> this.get(before.getInt(k));
   }
}
