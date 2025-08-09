package shaded.parquet.it.unimi.dsi.fastutil.longs;

import java.util.Comparator;
import shaded.parquet.it.unimi.dsi.fastutil.Pair;

public interface LongIntPair extends Pair {
   long leftLong();

   /** @deprecated */
   @Deprecated
   default Long left() {
      return this.leftLong();
   }

   default LongIntPair left(long l) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default LongIntPair left(Long l) {
      return this.left(l);
   }

   default long firstLong() {
      return this.leftLong();
   }

   /** @deprecated */
   @Deprecated
   default Long first() {
      return this.firstLong();
   }

   default LongIntPair first(long l) {
      return this.left(l);
   }

   /** @deprecated */
   @Deprecated
   default LongIntPair first(Long l) {
      return this.first(l);
   }

   default long keyLong() {
      return this.firstLong();
   }

   /** @deprecated */
   @Deprecated
   default Long key() {
      return this.keyLong();
   }

   default LongIntPair key(long l) {
      return this.left(l);
   }

   /** @deprecated */
   @Deprecated
   default LongIntPair key(Long l) {
      return this.key(l);
   }

   int rightInt();

   /** @deprecated */
   @Deprecated
   default Integer right() {
      return this.rightInt();
   }

   default LongIntPair right(int r) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default LongIntPair right(Integer l) {
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

   default LongIntPair second(int r) {
      return this.right(r);
   }

   /** @deprecated */
   @Deprecated
   default LongIntPair second(Integer l) {
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

   default LongIntPair value(int r) {
      return this.right(r);
   }

   /** @deprecated */
   @Deprecated
   default LongIntPair value(Integer l) {
      return this.value(l);
   }

   static LongIntPair of(long left, int right) {
      return new LongIntImmutablePair(left, right);
   }

   static Comparator lexComparator() {
      return (x, y) -> {
         int t = Long.compare(x.leftLong(), y.leftLong());
         return t != 0 ? t : Integer.compare(x.rightInt(), y.rightInt());
      };
   }
}
