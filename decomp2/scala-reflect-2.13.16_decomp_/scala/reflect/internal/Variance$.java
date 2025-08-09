package scala.reflect.internal;

import scala.collection.immutable.List;

public final class Variance$ {
   public static final Variance$ MODULE$ = new Variance$();
   private static final int Bivariant = 2;
   private static final int Covariant = 1;
   private static final int Contravariant = -1;
   private static final int Invariant = 0;

   public Variance.SbtCompat SbtCompat(final int v) {
      return new Variance.SbtCompat(v);
   }

   public int Bivariant() {
      return Bivariant;
   }

   public int Covariant() {
      return Covariant;
   }

   public int Contravariant() {
      return Contravariant;
   }

   public int Invariant() {
      return Invariant;
   }

   public int foldExtract(final List as, final Variance.Extractor f) {
      int loop$1_acc = this.Bivariant();

      List var10000;
      for(List loop$1_xs = as; !this.isInvariant$extension(loop$1_acc) && !loop$1_xs.isEmpty(); loop$1_xs = var10000) {
         var10000 = (List)loop$1_xs.tail();
         loop$1_acc = this.$amp$extension(loop$1_acc, f.apply(loop$1_xs.head()));
      }

      return loop$1_acc;
   }

   public int foldExtract2(final List as, final List bs, final Variance.Extractor2 f) {
      return this.loop$2(as, bs, this.Bivariant(), f);
   }

   public final boolean isBivariant$extension(final int $this) {
      return $this == 2;
   }

   public final boolean isCovariant$extension(final int $this) {
      return $this == 1;
   }

   public final boolean isInvariant$extension(final int $this) {
      return $this == 0;
   }

   public final boolean isContravariant$extension(final int $this) {
      return $this == -1;
   }

   public final boolean isPositive$extension(final int $this) {
      return $this > 0;
   }

   public final int $amp$extension(final int $this, final int other) {
      if ($this == other) {
         return $this;
      } else if (this.isBivariant$extension($this)) {
         return other;
      } else {
         return this.isBivariant$extension(other) ? $this : this.Invariant();
      }
   }

   public final int $times$extension(final int $this, final int other) {
      if (this.isPositive$extension(other)) {
         return $this;
      } else {
         return this.isContravariant$extension(other) ? this.flip$extension($this) : this.cut$extension($this);
      }
   }

   public final int flip$extension(final int $this) {
      if (this.isCovariant$extension($this)) {
         return this.Contravariant();
      } else {
         return this.isContravariant$extension($this) ? this.Covariant() : $this;
      }
   }

   public final int cut$extension(final int $this) {
      return this.isBivariant$extension($this) ? $this : this.Invariant();
   }

   public final String symbolicString$extension(final int $this) {
      if (this.isCovariant$extension($this)) {
         return "+";
      } else {
         return this.isContravariant$extension($this) ? "-" : "";
      }
   }

   public final String toString$extension(final int $this) {
      if (this.isContravariant$extension($this)) {
         return "contravariant";
      } else if (this.isCovariant$extension($this)) {
         return "covariant";
      } else {
         return this.isInvariant$extension($this) ? "invariant" : "";
      }
   }

   public final int hashCode$extension(final int $this) {
      return Integer.hashCode($this);
   }

   public final boolean equals$extension(final int $this, final Object x$1) {
      if (x$1 instanceof Variance) {
         int var3 = ((Variance)x$1).flags();
         if ($this == var3) {
            return true;
         }
      }

      return false;
   }

   private final int loop$1(final List xs, final int acc, final Variance.Extractor f$1) {
      while(!this.isInvariant$extension(acc) && !xs.isEmpty()) {
         List var10000 = (List)xs.tail();
         acc = this.$amp$extension(acc, f$1.apply(xs.head()));
         xs = var10000;
      }

      return acc;
   }

   private final int loop$2(final List xs, final List ys, final int acc, final Variance.Extractor2 f$2) {
      while(!this.isInvariant$extension(acc) && !xs.isEmpty() && !ys.isEmpty()) {
         List var10000 = (List)xs.tail();
         List var10001 = (List)ys.tail();
         acc = this.$amp$extension(acc, f$2.apply(xs.head(), ys.head()));
         ys = var10001;
         xs = var10000;
      }

      return acc;
   }

   private Variance$() {
   }
}
