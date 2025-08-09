package com.clearspring.analytics.util;

public class Pair {
   public final Object left;
   public final Object right;

   public Pair(Object left, Object right) {
      this.left = left;
      this.right = right;
   }

   public final int hashCode() {
      int hashCode = 31 + (this.left == null ? 0 : this.left.hashCode());
      return 31 * hashCode + (this.right == null ? 0 : this.right.hashCode());
   }

   public final boolean equals(Object o) {
      if (!(o instanceof Pair)) {
         return false;
      } else {
         Pair that = (Pair)o;
         return this.equal(this.left, that.left) && this.equal(this.right, that.right);
      }
   }

   private boolean equal(Object a, Object b) {
      return a == b || a != null && a.equals(b);
   }

   public String toString() {
      return "(" + this.left + "," + this.right + ")";
   }

   public static Pair create(Object x, Object y) {
      return new Pair(x, y);
   }
}
