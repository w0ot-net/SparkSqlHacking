package shaded.parquet.it.unimi.dsi.fastutil.chars;

import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import shaded.parquet.it.unimi.dsi.fastutil.SafeMath;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.Byte2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Reference2CharFunction;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.Short2CharFunction;

@FunctionalInterface
public interface Char2CharFunction extends shaded.parquet.it.unimi.dsi.fastutil.Function, IntUnaryOperator {
   /** @deprecated */
   @Deprecated
   default int applyAsInt(int operand) {
      return this.get(SafeMath.safeIntToChar(operand));
   }

   default char put(char key, char value) {
      throw new UnsupportedOperationException();
   }

   char get(char var1);

   default char getOrDefault(char key, char defaultValue) {
      char v;
      return (v = this.get(key)) == this.defaultReturnValue() && !this.containsKey(key) ? defaultValue : v;
   }

   default char remove(char key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default Character put(Character key, Character value) {
      char k = key;
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
         char k = (Character)key;
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
         char k = (Character)key;
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

   default void defaultReturnValue(char rv) {
      throw new UnsupportedOperationException();
   }

   default char defaultReturnValue() {
      return '\u0000';
   }

   static Char2CharFunction identity() {
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

   default Char2ByteFunction andThenByte(Char2ByteFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Byte2CharFunction composeByte(Byte2CharFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Char2ShortFunction andThenShort(Char2ShortFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Short2CharFunction composeShort(Short2CharFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Char2IntFunction andThenInt(Char2IntFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Int2CharFunction composeInt(Int2CharFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Char2LongFunction andThenLong(Char2LongFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Long2CharFunction composeLong(Long2CharFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Char2CharFunction andThenChar(Char2CharFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Char2CharFunction composeChar(Char2CharFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Char2FloatFunction andThenFloat(Char2FloatFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Float2CharFunction composeFloat(Float2CharFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Char2DoubleFunction andThenDouble(Char2DoubleFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Double2CharFunction composeDouble(Double2CharFunction before) {
      return (k) -> this.get(before.get(k));
   }

   default Char2ObjectFunction andThenObject(Char2ObjectFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Object2CharFunction composeObject(Object2CharFunction before) {
      return (k) -> this.get(before.getChar(k));
   }

   default Char2ReferenceFunction andThenReference(Char2ReferenceFunction after) {
      return (k) -> after.get(this.get(k));
   }

   default Reference2CharFunction composeReference(Reference2CharFunction before) {
      return (k) -> this.get(before.getChar(k));
   }
}
