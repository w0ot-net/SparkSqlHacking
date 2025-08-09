package scala;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;

public final class PartialFunction$ {
   public static final PartialFunction$ MODULE$ = new PartialFunction$();
   private static final Function1 fallback_fn = (x$2) -> fallback_fn;
   public static final Function1 scala$PartialFunction$$constFalse = (x$3) -> BoxesRunTime.boxToBoolean($anonfun$constFalse$1(x$3));
   private static final PartialFunction empty_pf = new PartialFunction() {
      private final Function1 lift = (x) -> None$.MODULE$;

      public Option unapply(final Object a) {
         return PartialFunction.unapply$(this, a);
      }

      public PartialFunction elementWise() {
         return PartialFunction.elementWise$(this);
      }

      public PartialFunction andThen(final PartialFunction k) {
         return PartialFunction.andThen$(this, (PartialFunction)k);
      }

      public PartialFunction compose(final PartialFunction k) {
         return PartialFunction.compose$(this, k);
      }

      public Object applyOrElse(final Object x, final Function1 default) {
         return PartialFunction.applyOrElse$(this, x, default);
      }

      public boolean apply$mcZD$sp(final double v1) {
         return Function1.apply$mcZD$sp$(this, v1);
      }

      public double apply$mcDD$sp(final double v1) {
         return Function1.apply$mcDD$sp$(this, v1);
      }

      public float apply$mcFD$sp(final double v1) {
         return Function1.apply$mcFD$sp$(this, v1);
      }

      public int apply$mcID$sp(final double v1) {
         return Function1.apply$mcID$sp$(this, v1);
      }

      public long apply$mcJD$sp(final double v1) {
         return Function1.apply$mcJD$sp$(this, v1);
      }

      public void apply$mcVD$sp(final double v1) {
         Function1.apply$mcVD$sp$(this, v1);
      }

      public boolean apply$mcZF$sp(final float v1) {
         return Function1.apply$mcZF$sp$(this, v1);
      }

      public double apply$mcDF$sp(final float v1) {
         return Function1.apply$mcDF$sp$(this, v1);
      }

      public float apply$mcFF$sp(final float v1) {
         return Function1.apply$mcFF$sp$(this, v1);
      }

      public int apply$mcIF$sp(final float v1) {
         return Function1.apply$mcIF$sp$(this, v1);
      }

      public long apply$mcJF$sp(final float v1) {
         return Function1.apply$mcJF$sp$(this, v1);
      }

      public void apply$mcVF$sp(final float v1) {
         Function1.apply$mcVF$sp$(this, v1);
      }

      public boolean apply$mcZI$sp(final int v1) {
         return Function1.apply$mcZI$sp$(this, v1);
      }

      public double apply$mcDI$sp(final int v1) {
         return Function1.apply$mcDI$sp$(this, v1);
      }

      public float apply$mcFI$sp(final int v1) {
         return Function1.apply$mcFI$sp$(this, v1);
      }

      public int apply$mcII$sp(final int v1) {
         return Function1.apply$mcII$sp$(this, v1);
      }

      public long apply$mcJI$sp(final int v1) {
         return Function1.apply$mcJI$sp$(this, v1);
      }

      public void apply$mcVI$sp(final int v1) {
         Function1.apply$mcVI$sp$(this, v1);
      }

      public boolean apply$mcZJ$sp(final long v1) {
         return Function1.apply$mcZJ$sp$(this, v1);
      }

      public double apply$mcDJ$sp(final long v1) {
         return Function1.apply$mcDJ$sp$(this, v1);
      }

      public float apply$mcFJ$sp(final long v1) {
         return Function1.apply$mcFJ$sp$(this, v1);
      }

      public int apply$mcIJ$sp(final long v1) {
         return Function1.apply$mcIJ$sp$(this, v1);
      }

      public long apply$mcJJ$sp(final long v1) {
         return Function1.apply$mcJJ$sp$(this, v1);
      }

      public void apply$mcVJ$sp(final long v1) {
         Function1.apply$mcVJ$sp$(this, v1);
      }

      public Function1 compose(final Function1 g) {
         return Function1.compose$(this, g);
      }

      public String toString() {
         return Function1.toString$(this);
      }

      public boolean isDefinedAt(final Object x) {
         return false;
      }

      public Nothing$ apply(final Object x) {
         throw new MatchError(x);
      }

      public PartialFunction orElse(final PartialFunction that) {
         return that;
      }

      public PartialFunction andThen(final Function1 k) {
         return this;
      }

      public Function1 lift() {
         return this.lift;
      }

      public Function1 runWith(final Function1 action) {
         return PartialFunction$.scala$PartialFunction$$constFalse;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   };

   public Function1 scala$PartialFunction$$checkFallback() {
      return fallback_fn;
   }

   public boolean scala$PartialFunction$$fallbackOccurred(final Object x) {
      return fallback_fn == x;
   }

   public PartialFunction unlifted(final Function1 f) {
      return (PartialFunction)(f instanceof PartialFunction.Lifted ? ((PartialFunction.Lifted)f).pf() : new PartialFunction.Unlifted(f));
   }

   public PartialFunction fromFunction(final Function1 f) {
      return new Serializable(f) {
         private static final long serialVersionUID = 0L;
         private final Function1 f$1;

         public final Object applyOrElse(final Object x1, final Function1 default) {
            return this.f$1.apply(x1);
         }

         public final boolean isDefinedAt(final Object x1) {
            return true;
         }

         public {
            this.f$1 = f$1;
         }
      };
   }

   public PartialFunction empty() {
      return empty_pf;
   }

   public boolean cond(final Object x, final PartialFunction pf) {
      return BoxesRunTime.unboxToBoolean(pf.applyOrElse(x, scala$PartialFunction$$constFalse));
   }

   public Option condOpt(final Object x, final PartialFunction pf) {
      Object z = pf.applyOrElse(x, this.scala$PartialFunction$$checkFallback());
      return (Option)(!this.scala$PartialFunction$$fallbackOccurred(z) ? new Some(z) : None$.MODULE$);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$constFalse$1(final Object x$3) {
      return false;
   }

   private PartialFunction$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
