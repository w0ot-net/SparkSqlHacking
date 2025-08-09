package org.apache.commons.lang3.tuple;

import java.util.Objects;

public class ImmutableTriple extends Triple {
   public static final ImmutableTriple[] EMPTY_ARRAY = new ImmutableTriple[0];
   private static final ImmutableTriple NULL = new ImmutableTriple((Object)null, (Object)null, (Object)null);
   private static final long serialVersionUID = 1L;
   public final Object left;
   public final Object middle;
   public final Object right;

   public static ImmutableTriple[] emptyArray() {
      return EMPTY_ARRAY;
   }

   public static ImmutableTriple nullTriple() {
      return NULL;
   }

   public static ImmutableTriple of(Object left, Object middle, Object right) {
      return !(left != null | middle != null) && right == null ? nullTriple() : new ImmutableTriple(left, middle, right);
   }

   public static ImmutableTriple ofNonNull(Object left, Object middle, Object right) {
      return of(Objects.requireNonNull(left, "left"), Objects.requireNonNull(middle, "middle"), Objects.requireNonNull(right, "right"));
   }

   public ImmutableTriple(Object left, Object middle, Object right) {
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
}
