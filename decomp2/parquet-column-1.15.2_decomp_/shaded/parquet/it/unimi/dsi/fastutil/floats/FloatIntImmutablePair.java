package shaded.parquet.it.unimi.dsi.fastutil.floats;

import java.io.Serializable;
import java.util.Objects;
import shaded.parquet.it.unimi.dsi.fastutil.HashCommon;
import shaded.parquet.it.unimi.dsi.fastutil.Pair;

public class FloatIntImmutablePair implements FloatIntPair, Serializable {
   private static final long serialVersionUID = 0L;
   protected final float left;
   protected final int right;

   public FloatIntImmutablePair(float left, int right) {
      this.left = left;
      this.right = right;
   }

   public static FloatIntImmutablePair of(float left, int right) {
      return new FloatIntImmutablePair(left, right);
   }

   public float leftFloat() {
      return this.left;
   }

   public int rightInt() {
      return this.right;
   }

   public boolean equals(Object other) {
      if (other == null) {
         return false;
      } else if (other instanceof FloatIntPair) {
         return this.left == ((FloatIntPair)other).leftFloat() && this.right == ((FloatIntPair)other).rightInt();
      } else if (!(other instanceof Pair)) {
         return false;
      } else {
         return Objects.equals(this.left, ((Pair)other).left()) && Objects.equals(this.right, ((Pair)other).right());
      }
   }

   public int hashCode() {
      return HashCommon.float2int(this.left) * 19 + this.right;
   }

   public String toString() {
      return "<" + this.leftFloat() + "," + this.rightInt() + ">";
   }
}
