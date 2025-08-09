package shaded.parquet.it.unimi.dsi.fastutil.floats;

import java.util.Comparator;
import shaded.parquet.it.unimi.dsi.fastutil.Pair;

public interface FloatIntPair extends Pair {
   float leftFloat();

   /** @deprecated */
   @Deprecated
   default Float left() {
      return this.leftFloat();
   }

   default FloatIntPair left(float l) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default FloatIntPair left(Float l) {
      return this.left(l);
   }

   default float firstFloat() {
      return this.leftFloat();
   }

   /** @deprecated */
   @Deprecated
   default Float first() {
      return this.firstFloat();
   }

   default FloatIntPair first(float l) {
      return this.left(l);
   }

   /** @deprecated */
   @Deprecated
   default FloatIntPair first(Float l) {
      return this.first(l);
   }

   default float keyFloat() {
      return this.firstFloat();
   }

   /** @deprecated */
   @Deprecated
   default Float key() {
      return this.keyFloat();
   }

   default FloatIntPair key(float l) {
      return this.left(l);
   }

   /** @deprecated */
   @Deprecated
   default FloatIntPair key(Float l) {
      return this.key(l);
   }

   int rightInt();

   /** @deprecated */
   @Deprecated
   default Integer right() {
      return this.rightInt();
   }

   default FloatIntPair right(int r) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default FloatIntPair right(Integer l) {
      return this.right(l);
   }

   default int secondInt() {
      return this.rightInt();
   }

   /** @deprecated */
   @Deprecated
   default Integer second() {
      return this.secondInt();
   }

   default FloatIntPair second(int r) {
      return this.right(r);
   }

   /** @deprecated */
   @Deprecated
   default FloatIntPair second(Integer l) {
      return this.second(l);
   }

   default int valueInt() {
      return this.rightInt();
   }

   /** @deprecated */
   @Deprecated
   default Integer value() {
      return this.valueInt();
   }

   default FloatIntPair value(int r) {
      return this.right(r);
   }

   /** @deprecated */
   @Deprecated
   default FloatIntPair value(Integer l) {
      return this.value(l);
   }

   static FloatIntPair of(float left, int right) {
      return new FloatIntImmutablePair(left, right);
   }

   static Comparator lexComparator() {
      return (x, y) -> {
         int t = Float.compare(x.leftFloat(), y.leftFloat());
         return t != 0 ? t : Integer.compare(x.rightInt(), y.rightInt());
      };
   }
}
