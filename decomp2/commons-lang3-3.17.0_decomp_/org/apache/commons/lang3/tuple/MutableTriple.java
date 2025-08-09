package org.apache.commons.lang3.tuple;

import java.util.Objects;

public class MutableTriple extends Triple {
   public static final MutableTriple[] EMPTY_ARRAY = new MutableTriple[0];
   private static final long serialVersionUID = 1L;
   public Object left;
   public Object middle;
   public Object right;

   public static MutableTriple[] emptyArray() {
      return EMPTY_ARRAY;
   }

   public static MutableTriple of(Object left, Object middle, Object right) {
      return new MutableTriple(left, middle, right);
   }

   public static MutableTriple ofNonNull(Object left, Object middle, Object right) {
      return of(Objects.requireNonNull(left, "left"), Objects.requireNonNull(middle, "middle"), Objects.requireNonNull(right, "right"));
   }

   public MutableTriple() {
   }

   public MutableTriple(Object left, Object middle, Object right) {
      this.left = left;
      this.middle = middle;
      this.right = right;
   }

   public Object getLeft() {
      return this.left;
   }

   public Object getMiddle() {
      return this.middle;
   }

   public Object getRight() {
      return this.right;
   }

   public void setLeft(Object left) {
      this.left = left;
   }

   public void setMiddle(Object middle) {
      this.middle = middle;
   }

   public void setRight(Object right) {
      this.right = right;
   }
}
