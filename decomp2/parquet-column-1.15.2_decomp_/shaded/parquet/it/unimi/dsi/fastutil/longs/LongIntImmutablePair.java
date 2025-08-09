package shaded.parquet.it.unimi.dsi.fastutil.longs;

import java.io.Serializable;
import java.util.Objects;
import shaded.parquet.it.unimi.dsi.fastutil.HashCommon;
import shaded.parquet.it.unimi.dsi.fastutil.Pair;

public class LongIntImmutablePair implements LongIntPair, Serializable {
   private static final long serialVersionUID = 0L;
   protected final long left;
   protected final int right;

   public LongIntImmutablePair(long left, int right) {
      this.left = left;
      this.right = right;
   }

   public static LongIntImmutablePair of(long left, int right) {
      return new LongIntImmutablePair(left, right);
   }

   public long leftLong() {
      return this.left;
   }

   public int rightInt() {
      return this.right;
   }

   public boolean equals(Object other) {
      if (other == null) {
         return false;
      } else if (other instanceof LongIntPair) {
         return this.left == ((LongIntPair)other).leftLong() && this.right == ((LongIntPair)other).rightInt();
      } else if (!(other instanceof Pair)) {
         return false;
      } else {
         return Objects.equals(this.left, ((Pair)other).left()) && Objects.equals(this.right, ((Pair)other).right());
      }
   }

   public int hashCode() {
      return HashCommon.long2int(this.left) * 19 + this.right;
   }

   public String toString() {
      return "<" + this.leftLong() + "," + this.rightInt() + ">";
   }
}
