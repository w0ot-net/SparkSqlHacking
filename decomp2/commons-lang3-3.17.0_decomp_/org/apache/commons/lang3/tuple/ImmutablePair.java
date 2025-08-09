package org.apache.commons.lang3.tuple;

import java.util.Map;
import java.util.Objects;

public class ImmutablePair extends Pair {
   public static final ImmutablePair[] EMPTY_ARRAY = new ImmutablePair[0];
   private static final ImmutablePair NULL = new ImmutablePair((Object)null, (Object)null);
   private static final long serialVersionUID = 4954918890077093841L;
   public final Object left;
   public final Object right;

   public static ImmutablePair[] emptyArray() {
      return EMPTY_ARRAY;
   }

   public static Pair left(Object left) {
      return of(left, (Object)null);
   }

   public static ImmutablePair nullPair() {
      return NULL;
   }

   public static ImmutablePair of(Object left, Object right) {
      return left == null && right == null ? nullPair() : new ImmutablePair(left, right);
   }

   public static ImmutablePair of(Map.Entry pair) {
      return pair != null ? new ImmutablePair(pair.getKey(), pair.getValue()) : nullPair();
   }

   public static ImmutablePair ofNonNull(Object left, Object right) {
      return of(Objects.requireNonNull(left, "left"), Objects.requireNonNull(right, "right"));
   }

   public static Pair right(Object right) {
      return of((Object)null, right);
   }

   public ImmutablePair(Object left, Object right) {
      this.left = left;
      this.right = right;
   }

   public Object getLeft() {
      return this.left;
   }

   public Object getRight() {
      return this.right;
   }

   public Object setValue(Object value) {
      throw new UnsupportedOperationException();
   }
}
