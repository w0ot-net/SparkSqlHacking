package spire.math;

import algebra.lattice.DeMorgan;
import cats.kernel.Eq;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.None.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.util.Try;

public final class Trilean$ {
   public static final Trilean$ MODULE$ = new Trilean$();
   private static final int True = -1;
   private static final int False = 0;
   private static final int Unknown = 1;
   private static final DeMorgan algebra = new TrileanAlgebra();
   private static final Eq trileanEq = new Eq() {
      public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
         return Eq.eqv$mcZ$sp$(this, x, y);
      }

      public boolean eqv$mcB$sp(final byte x, final byte y) {
         return Eq.eqv$mcB$sp$(this, x, y);
      }

      public boolean eqv$mcC$sp(final char x, final char y) {
         return Eq.eqv$mcC$sp$(this, x, y);
      }

      public boolean eqv$mcD$sp(final double x, final double y) {
         return Eq.eqv$mcD$sp$(this, x, y);
      }

      public boolean eqv$mcF$sp(final float x, final float y) {
         return Eq.eqv$mcF$sp$(this, x, y);
      }

      public boolean eqv$mcI$sp(final int x, final int y) {
         return Eq.eqv$mcI$sp$(this, x, y);
      }

      public boolean eqv$mcJ$sp(final long x, final long y) {
         return Eq.eqv$mcJ$sp$(this, x, y);
      }

      public boolean eqv$mcS$sp(final short x, final short y) {
         return Eq.eqv$mcS$sp$(this, x, y);
      }

      public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Eq.eqv$mcV$sp$(this, x, y);
      }

      public boolean neqv(final Object x, final Object y) {
         return Eq.neqv$(this, x, y);
      }

      public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
         return Eq.neqv$mcZ$sp$(this, x, y);
      }

      public boolean neqv$mcB$sp(final byte x, final byte y) {
         return Eq.neqv$mcB$sp$(this, x, y);
      }

      public boolean neqv$mcC$sp(final char x, final char y) {
         return Eq.neqv$mcC$sp$(this, x, y);
      }

      public boolean neqv$mcD$sp(final double x, final double y) {
         return Eq.neqv$mcD$sp$(this, x, y);
      }

      public boolean neqv$mcF$sp(final float x, final float y) {
         return Eq.neqv$mcF$sp$(this, x, y);
      }

      public boolean neqv$mcI$sp(final int x, final int y) {
         return Eq.neqv$mcI$sp$(this, x, y);
      }

      public boolean neqv$mcJ$sp(final long x, final long y) {
         return Eq.neqv$mcJ$sp$(this, x, y);
      }

      public boolean neqv$mcS$sp(final short x, final short y) {
         return Eq.neqv$mcS$sp$(this, x, y);
      }

      public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Eq.neqv$mcV$sp$(this, x, y);
      }

      public boolean eqv(final int x, final int y) {
         return x == y;
      }

      public {
         Eq.$init$(this);
      }
   };

   public final int True() {
      return True;
   }

   public final int False() {
      return False;
   }

   public final int Unknown() {
      return Unknown;
   }

   public final int apply(final boolean b) {
      return b ? this.True() : this.False();
   }

   public final int apply(final Option o) {
      return ((Trilean)o.map((x$1) -> new Trilean($anonfun$apply$1(BoxesRunTime.unboxToBoolean(x$1)))).getOrElse(() -> new Trilean($anonfun$apply$2()))).value();
   }

   public final int apply(final Try t) {
      return ((Trilean)t.map((x$2) -> new Trilean($anonfun$apply$3(BoxesRunTime.unboxToBoolean(x$2)))).getOrElse(() -> new Trilean($anonfun$apply$4()))).value();
   }

   public final Function1 liftPf(final PartialFunction p0) {
      PartialFunction p = p0.andThen((x$3) -> new Trilean($anonfun$liftPf$1(BoxesRunTime.unboxToBoolean(x$3))));
      return (a) -> new Trilean($anonfun$liftPf$2(p, a));
   }

   public final int testRef(final Object a, final Function1 f) {
      return a == null ? this.Unknown() : this.apply(BoxesRunTime.unboxToBoolean(f.apply(a)));
   }

   public final int testFloat(final float n, final Function1 f) {
      return Float.isNaN(n) ? this.Unknown() : this.apply(f.apply$mcZF$sp(n));
   }

   public final int testDouble(final double n, final Function1 f) {
      return Double.isNaN(n) ? this.Unknown() : this.apply(f.apply$mcZD$sp(n));
   }

   public final int run(final Function0 body) {
      int var10000;
      try {
         var10000 = this.apply(body.apply$mcZ$sp());
      } catch (Exception var2) {
         var10000 = this.Unknown();
      }

      return var10000;
   }

   public DeMorgan algebra() {
      return algebra;
   }

   public Eq trileanEq() {
      return trileanEq;
   }

   public final boolean isTrue$extension(final int $this) {
      return $this == -1;
   }

   public final boolean isFalse$extension(final int $this) {
      return $this == 0;
   }

   public final boolean isUnknown$extension(final int $this) {
      return $this == 1;
   }

   public final boolean isKnown$extension(final int $this) {
      return $this != 1;
   }

   public final boolean isNotTrue$extension(final int $this) {
      return $this != -1;
   }

   public final boolean isNotFalse$extension(final int $this) {
      return $this != 0;
   }

   public final Object fold$extension(final int $this, final Function1 f, final Function0 unknown) {
      return $this == 1 ? unknown.apply() : f.apply(BoxesRunTime.boxToBoolean($this == -1));
   }

   public final boolean assumeTrue$extension(final int $this) {
      return $this != 0;
   }

   public final boolean assumeFalse$extension(final int $this) {
      return $this == -1;
   }

   public final boolean assume$extension(final int $this, final boolean b) {
      return $this == 1 ? b : $this == -1;
   }

   public final boolean toBoolean$extension(final int $this, final Function0 b) {
      return $this == 1 ? b.apply$mcZ$sp() : $this == -1;
   }

   public final Option toOption$extension(final int $this) {
      return (Option)($this == 1 ? .MODULE$ : new Some(BoxesRunTime.boxToBoolean($this == -1)));
   }

   public final String toString$extension(final int $this) {
      return $this == -1 ? "true" : ($this == 0 ? "false" : "unknown");
   }

   public final int $amp$amp$extension(final int $this, final Function0 rhs) {
      return $this == 0 ? $this : this.$amp$extension($this, ((Trilean)rhs.apply()).value());
   }

   public final int $bar$bar$extension(final int $this, final Function0 rhs) {
      return $this == -1 ? $this : this.$bar$extension($this, ((Trilean)rhs.apply()).value());
   }

   public final int unary_$bang$extension(final int $this) {
      return $this == 1 ? $this : ~$this;
   }

   public final int $amp$extension(final int $this, final int rhs) {
      return $this & rhs;
   }

   public final int $bar$extension(final int $this, final int rhs) {
      return $this | rhs;
   }

   public final int $up$extension(final int $this, final int rhs) {
      return $this == 1 ? $this : (rhs == 1 ? rhs : $this ^ rhs);
   }

   public final int imp$extension(final int $this, final int rhs) {
      return this.$bar$extension(this.unary_$bang$extension($this), rhs);
   }

   public final int nand$extension(final int $this, final int rhs) {
      return this.unary_$bang$extension(this.$amp$extension($this, rhs));
   }

   public final int nor$extension(final int $this, final int rhs) {
      return this.unary_$bang$extension(this.$bar$extension($this, rhs));
   }

   public final int nxor$extension(final int $this, final int rhs) {
      return $this == 1 ? $this : (rhs == 1 ? rhs : ~($this ^ rhs));
   }

   public final int hashCode$extension(final int $this) {
      return Integer.hashCode($this);
   }

   public final boolean equals$extension(final int $this, final Object x$1) {
      boolean var3;
      if (x$1 instanceof Trilean) {
         var3 = true;
      } else {
         var3 = false;
      }

      boolean var10000;
      if (var3) {
         int var5 = ((Trilean)x$1).value();
         if ($this == var5) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   // $FF: synthetic method
   public static final int $anonfun$apply$1(final boolean x$1) {
      return MODULE$.apply(x$1);
   }

   // $FF: synthetic method
   public static final int $anonfun$apply$2() {
      return MODULE$.Unknown();
   }

   // $FF: synthetic method
   public static final int $anonfun$apply$3(final boolean x$2) {
      return MODULE$.apply(x$2);
   }

   // $FF: synthetic method
   public static final int $anonfun$apply$4() {
      return MODULE$.Unknown();
   }

   // $FF: synthetic method
   public static final int $anonfun$liftPf$1(final boolean x$3) {
      return MODULE$.apply(x$3);
   }

   // $FF: synthetic method
   public static final int $anonfun$liftPf$3(final Object x$4) {
      return MODULE$.Unknown();
   }

   // $FF: synthetic method
   public static final int $anonfun$liftPf$2(final PartialFunction p$1, final Object a) {
      return ((Trilean)p$1.applyOrElse(a, (x$4) -> new Trilean($anonfun$liftPf$3(x$4)))).value();
   }

   private Trilean$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
