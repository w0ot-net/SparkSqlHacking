package shaded.parquet.it.unimi.dsi.fastutil.doubles;

import java.io.Serializable;
import java.util.Objects;
import shaded.parquet.it.unimi.dsi.fastutil.HashCommon;
import shaded.parquet.it.unimi.dsi.fastutil.Pair;

public class DoubleIntImmutablePair implements DoubleIntPair, Serializable {
   private static final long serialVersionUID = 0L;
   protected final double left;
   protected final int right;

   public DoubleIntImmutablePair(double left, int right) {
      this.left = left;
      this.right = right;
   }

   public static DoubleIntImmutablePair of(double left, int right) {
      return new DoubleIntImmutablePair(left, right);
   }

   public double leftDouble() {
      return this.left;
   }

   public int rightInt() {
      return this.right;
   }

   public boolean equals(Object other) {
      if (other == null) {
         return false;
      } else if (other instanceof DoubleIntPair) {
         return this.left == ((DoubleIntPair)other).leftDouble() && this.right == ((DoubleIntPair)other).rightInt();
      } else if (!(other instanceof Pair)) {
         return false;
      } else {
         return Objects.equals(this.left, ((Pair)other).left()) && Objects.equals(this.right, ((Pair)other).right());
      }
   }

   public int hashCode() {
      return HashCommon.double2int(this.left) * 19 + this.right;
   }

   public String toString() {
      return "<" + this.leftDouble() + "," + this.rightInt() + ">";
   }
}
