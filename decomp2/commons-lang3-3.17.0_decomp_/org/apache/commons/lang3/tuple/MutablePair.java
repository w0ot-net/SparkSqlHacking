package org.apache.commons.lang3.tuple;

import java.util.Map;
import java.util.Objects;

public class MutablePair extends Pair {
   public static final MutablePair[] EMPTY_ARRAY = new MutablePair[0];
   private static final long serialVersionUID = 4954918890077093841L;
   public Object left;
   public Object right;

   public static MutablePair[] emptyArray() {
      return EMPTY_ARRAY;
   }

   public static MutablePair of(Object left, Object right) {
      return new MutablePair(left, right);
   }

   public static MutablePair of(Map.Entry pair) {
      L left;
      R right;
      if (pair != null) {
         left = (L)pair.getKey();
         right = (R)pair.getValue();
      } else {
         left = (L)null;
         right = (R)null;
      }

      return new MutablePair(left, right);
   }

   public static MutablePair ofNonNull(Object left, Object right) {
      return of(Objects.requireNonNull(left, "left"), Objects.requireNonNull(right, "right"));
   }

   public MutablePair() {
   }

   public MutablePair(Object left, Object right) {
      this.left = left;
      this.right = right;
   }

   public Object getLeft() {
      return this.left;
   }

   public Object getRight() {
      return this.right;
   }

   public void setLeft(Object left) {
      this.left = left;
   }

   public void setRight(Object right) {
      this.right = right;
   }

   public Object setValue(Object value) {
      R result = (R)this.getRight();
      this.setRight(value);
      return result;
   }
}
