package spire.optional;

import spire.algebra.Trig;
import spire.math.Rational;
import spire.math.Rational$;
import spire.math.package$;
import spire.std.package;

public final class rationalTrig$ {
   public static final rationalTrig$ MODULE$ = new rationalTrig$();
   private static final Trig trigRational = new Trig() {
      private final Rational r180;
      private final Rational e;
      private final Rational pi;

      public double e$mcD$sp() {
         return Trig.e$mcD$sp$(this);
      }

      public float e$mcF$sp() {
         return Trig.e$mcF$sp$(this);
      }

      public double pi$mcD$sp() {
         return Trig.pi$mcD$sp$(this);
      }

      public float pi$mcF$sp() {
         return Trig.pi$mcF$sp$(this);
      }

      public double exp$mcD$sp(final double a) {
         return Trig.exp$mcD$sp$(this, a);
      }

      public float exp$mcF$sp(final float a) {
         return Trig.exp$mcF$sp$(this, a);
      }

      public double expm1$mcD$sp(final double a) {
         return Trig.expm1$mcD$sp$(this, a);
      }

      public float expm1$mcF$sp(final float a) {
         return Trig.expm1$mcF$sp$(this, a);
      }

      public double log$mcD$sp(final double a) {
         return Trig.log$mcD$sp$(this, a);
      }

      public float log$mcF$sp(final float a) {
         return Trig.log$mcF$sp$(this, a);
      }

      public double log1p$mcD$sp(final double a) {
         return Trig.log1p$mcD$sp$(this, a);
      }

      public float log1p$mcF$sp(final float a) {
         return Trig.log1p$mcF$sp$(this, a);
      }

      public double sin$mcD$sp(final double a) {
         return Trig.sin$mcD$sp$(this, a);
      }

      public float sin$mcF$sp(final float a) {
         return Trig.sin$mcF$sp$(this, a);
      }

      public double cos$mcD$sp(final double a) {
         return Trig.cos$mcD$sp$(this, a);
      }

      public float cos$mcF$sp(final float a) {
         return Trig.cos$mcF$sp$(this, a);
      }

      public double tan$mcD$sp(final double a) {
         return Trig.tan$mcD$sp$(this, a);
      }

      public float tan$mcF$sp(final float a) {
         return Trig.tan$mcF$sp$(this, a);
      }

      public double asin$mcD$sp(final double a) {
         return Trig.asin$mcD$sp$(this, a);
      }

      public float asin$mcF$sp(final float a) {
         return Trig.asin$mcF$sp$(this, a);
      }

      public double acos$mcD$sp(final double a) {
         return Trig.acos$mcD$sp$(this, a);
      }

      public float acos$mcF$sp(final float a) {
         return Trig.acos$mcF$sp$(this, a);
      }

      public double atan$mcD$sp(final double a) {
         return Trig.atan$mcD$sp$(this, a);
      }

      public float atan$mcF$sp(final float a) {
         return Trig.atan$mcF$sp$(this, a);
      }

      public double atan2$mcD$sp(final double y, final double x) {
         return Trig.atan2$mcD$sp$(this, y, x);
      }

      public float atan2$mcF$sp(final float y, final float x) {
         return Trig.atan2$mcF$sp$(this, y, x);
      }

      public double sinh$mcD$sp(final double x) {
         return Trig.sinh$mcD$sp$(this, x);
      }

      public float sinh$mcF$sp(final float x) {
         return Trig.sinh$mcF$sp$(this, x);
      }

      public double cosh$mcD$sp(final double x) {
         return Trig.cosh$mcD$sp$(this, x);
      }

      public float cosh$mcF$sp(final float x) {
         return Trig.cosh$mcF$sp$(this, x);
      }

      public double tanh$mcD$sp(final double x) {
         return Trig.tanh$mcD$sp$(this, x);
      }

      public float tanh$mcF$sp(final float x) {
         return Trig.tanh$mcF$sp$(this, x);
      }

      public double toRadians$mcD$sp(final double a) {
         return Trig.toRadians$mcD$sp$(this, a);
      }

      public float toRadians$mcF$sp(final float a) {
         return Trig.toRadians$mcF$sp$(this, a);
      }

      public double toDegrees$mcD$sp(final double a) {
         return Trig.toDegrees$mcD$sp$(this, a);
      }

      public float toDegrees$mcF$sp(final float a) {
         return Trig.toDegrees$mcF$sp$(this, a);
      }

      private Rational r180() {
         return this.r180;
      }

      public Rational acos(final Rational a) {
         return Rational$.MODULE$.apply(package$.MODULE$.acos$mDc$sp(a.toDouble(), (Trig)package.double$.MODULE$.DoubleAlgebra()));
      }

      public Rational asin(final Rational a) {
         return Rational$.MODULE$.apply(package$.MODULE$.asin$mDc$sp(a.toDouble(), (Trig)package.double$.MODULE$.DoubleAlgebra()));
      }

      public Rational atan(final Rational a) {
         return Rational$.MODULE$.apply(package$.MODULE$.atan$mDc$sp(a.toDouble(), (Trig)package.double$.MODULE$.DoubleAlgebra()));
      }

      public Rational atan2(final Rational y, final Rational x) {
         return Rational$.MODULE$.apply(package$.MODULE$.atan2$mDc$sp(y.toDouble(), x.toDouble(), (Trig)package.double$.MODULE$.DoubleAlgebra()));
      }

      public Rational cos(final Rational a) {
         return Rational$.MODULE$.apply(package$.MODULE$.cos$mDc$sp(a.toDouble(), (Trig)package.double$.MODULE$.DoubleAlgebra()));
      }

      public Rational cosh(final Rational x) {
         return Rational$.MODULE$.apply(package$.MODULE$.cosh(x.toDouble()));
      }

      public Rational e() {
         return this.e;
      }

      public Rational exp(final Rational a) {
         return Rational$.MODULE$.apply(package$.MODULE$.exp(a.toDouble()));
      }

      public Rational expm1(final Rational a) {
         return Rational$.MODULE$.apply(package$.MODULE$.expm1(a.toDouble()));
      }

      public Rational log(final Rational a) {
         return Rational$.MODULE$.apply(package$.MODULE$.log(a.toDouble()));
      }

      public Rational log1p(final Rational a) {
         return Rational$.MODULE$.apply(package$.MODULE$.log1p(a.toDouble()));
      }

      public Rational pi() {
         return this.pi;
      }

      public Rational sin(final Rational a) {
         return Rational$.MODULE$.apply(package$.MODULE$.sin$mDc$sp(a.toDouble(), (Trig)package.double$.MODULE$.DoubleAlgebra()));
      }

      public Rational sinh(final Rational x) {
         return Rational$.MODULE$.apply(package$.MODULE$.sinh$mDc$sp(x.toDouble(), (Trig)package.double$.MODULE$.DoubleAlgebra()));
      }

      public Rational tan(final Rational a) {
         return Rational$.MODULE$.apply(package$.MODULE$.tan$mDc$sp(a.toDouble(), (Trig)package.double$.MODULE$.DoubleAlgebra()));
      }

      public Rational tanh(final Rational x) {
         return Rational$.MODULE$.apply(package$.MODULE$.tanh$mDc$sp(x.toDouble(), (Trig)package.double$.MODULE$.DoubleAlgebra()));
      }

      public Rational toDegrees(final Rational a) {
         return a.$times(this.r180()).$div(this.pi());
      }

      public Rational toRadians(final Rational a) {
         return a.$div(this.r180()).$times(this.pi());
      }

      public {
         this.r180 = Rational$.MODULE$.apply(180);
         this.e = Rational$.MODULE$.apply(package$.MODULE$.e());
         this.pi = Rational$.MODULE$.apply(package$.MODULE$.pi());
      }
   };

   public Trig trigRational() {
      return trigRational;
   }

   private rationalTrig$() {
   }
}
