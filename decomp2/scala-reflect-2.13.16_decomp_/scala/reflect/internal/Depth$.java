package scala.reflect.internal;

import scala.collection.immutable.List;

public final class Depth$ {
   public static final Depth$ MODULE$ = new Depth$();
   private static final int AnyDepth = -3;
   private static final int Zero = 0;

   public final int AnyDepthValue() {
      return -3;
   }

   public final int AnyDepth() {
      return AnyDepth;
   }

   public final int Zero() {
      return Zero;
   }

   public final int apply(final int depth) {
      return depth < -3 ? this.AnyDepth() : depth;
   }

   public int maximumBy(final List xs, final DepthFunction ff) {
      List ys = xs;

      int mm;
      for(mm = this.Zero(); !ys.isEmpty(); ys = (List)ys.tail()) {
         mm = this.max$extension(mm, ff.apply(ys.head()));
      }

      return mm;
   }

   public final int max$extension(final int $this, final int that) {
      return $this < that ? that : $this;
   }

   public final int decr$extension(final int $this, final int n) {
      if (this.isAnyDepth$extension($this)) {
         return $this;
      } else {
         int apply_depth = $this - n;
         return apply_depth < -3 ? this.AnyDepth() : apply_depth;
      }
   }

   public final int incr$extension(final int $this, final int n) {
      if (this.isAnyDepth$extension($this)) {
         return $this;
      } else {
         int apply_depth = $this + n;
         return apply_depth < -3 ? this.AnyDepth() : apply_depth;
      }
   }

   public final int decr$extension(final int $this) {
      return this.decr$extension($this, 1);
   }

   public final int incr$extension(final int $this) {
      return this.incr$extension($this, 1);
   }

   public final boolean isNegative$extension(final int $this) {
      return $this < 0;
   }

   public final boolean isZero$extension(final int $this) {
      return $this == 0;
   }

   public final boolean isAnyDepth$extension(final int $this) {
      return $this == this.AnyDepth();
   }

   public final int compare$extension(final int $this, final int that) {
      if ($this < that) {
         return -1;
      } else {
         return $this == that ? 0 : 1;
      }
   }

   public final String toString$extension(final int $this) {
      return (new StringBuilder(7)).append("Depth(").append($this).append(")").toString();
   }

   public final int hashCode$extension(final int $this) {
      return Integer.hashCode($this);
   }

   public final boolean equals$extension(final int $this, final Object x$1) {
      if (x$1 instanceof Depth) {
         int var3 = ((Depth)x$1).depth();
         if ($this == var3) {
            return true;
         }
      }

      return false;
   }

   private Depth$() {
   }
}
