package spire.math;

import algebra.ring.CommutativeRing;
import algebra.ring.Field;
import algebra.ring.Signed;
import cats.kernel.Eq;
import cats.kernel.Order;
import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.package.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import spire.algebra.Trig;
import spire.algebra.Trig$;

public final class Complex$ implements ComplexInstances, Serializable {
   public static final Complex$ MODULE$ = new Complex$();

   static {
      ComplexInstances0.$init$(MODULE$);
      ComplexInstances1.$init$(MODULE$);
      ComplexInstances.$init$(MODULE$);
   }

   public ComplexOnTrigImpl ComplexOnTrig(final Fractional evidence$9, final Order evidence$10, final Trig evidence$11, final Signed evidence$12) {
      return ComplexInstances.ComplexOnTrig$(this, evidence$9, evidence$10, evidence$11, evidence$12);
   }

   public ComplexOnTrigImpl ComplexOnTrig$mDc$sp(final Fractional evidence$9, final Order evidence$10, final Trig evidence$11, final Signed evidence$12) {
      return ComplexInstances.ComplexOnTrig$mDc$sp$(this, evidence$9, evidence$10, evidence$11, evidence$12);
   }

   public ComplexOnTrigImpl ComplexOnTrig$mFc$sp(final Fractional evidence$9, final Order evidence$10, final Trig evidence$11, final Signed evidence$12) {
      return ComplexInstances.ComplexOnTrig$mFc$sp$(this, evidence$9, evidence$10, evidence$11, evidence$12);
   }

   public Eq ComplexEq(final Eq evidence$13) {
      return ComplexInstances.ComplexEq$(this, evidence$13);
   }

   public ComplexOnField ComplexOnField(final Field evidence$6, final Order evidence$7, final Signed evidence$8) {
      return ComplexInstances1.ComplexOnField$(this, evidence$6, evidence$7, evidence$8);
   }

   public ComplexOnCRing ComplexOnCRing(final CommutativeRing evidence$4, final Signed evidence$5) {
      return ComplexInstances0.ComplexOnCRing$(this, evidence$4, evidence$5);
   }

   public Complex i(final CommutativeRing T) {
      return new Complex(T.zero(), T.one());
   }

   public Complex one(final CommutativeRing T) {
      return new Complex(T.one(), T.zero());
   }

   public Complex zero(final CommutativeRing T) {
      return new Complex(T.zero(), T.zero());
   }

   public Complex fromInt(final int n, final CommutativeRing f) {
      return new Complex(f.fromInt(n), f.zero());
   }

   public Complex intToComplex(final int n) {
      return new Complex$mcD$sp((double)n, (double)0.0F);
   }

   public Complex longToComplex(final long n) {
      return new Complex$mcD$sp((double)n, (double)0.0F);
   }

   public Complex floatToComplex(final float n) {
      return new Complex$mcF$sp(n, 0.0F);
   }

   public Complex doubleToComplex(final double n) {
      return new Complex$mcD$sp(n, (double)0.0F);
   }

   public Complex bigIntToComplex(final BigInt n) {
      return this.bigDecimalToComplex(.MODULE$.BigDecimal().apply(n));
   }

   public Complex bigDecimalToComplex(final BigDecimal n) {
      return new Complex(n, .MODULE$.BigDecimal().apply(0));
   }

   public Complex polar(final Object magnitude, final Object angle, final Field evidence$1, final Trig evidence$2) {
      return new Complex(evidence$1.times(magnitude, Trig$.MODULE$.apply(evidence$2).cos(angle)), evidence$1.times(magnitude, Trig$.MODULE$.apply(evidence$2).sin(angle)));
   }

   public Complex apply(final Object real, final CommutativeRing evidence$3) {
      return new Complex(real, spire.algebra.package$.MODULE$.CRing().apply(evidence$3).zero());
   }

   public Complex rootOfUnity(final int n, final int x, final Field f, final Trig t) {
      if (x == 0) {
         return this.one(f);
      } else {
         if (n % 2 == 0) {
            if (x == n / 2) {
               return this.one(f).unary_$minus(f);
            }

            if (n % 4 == 0) {
               if (x == n / 4) {
                  return this.i(f);
               }

               if (x == n * 3 / 4) {
                  return this.i(f).unary_$minus(f);
               }
            }
         }

         return this.polar(f.one(), f.div(f.times(f.times(t.pi(), f.fromInt(2)), f.fromInt(x)), f.fromInt(n)), f, t);
      }
   }

   public Complex[] rootsOfUnity(final int n, final Field f, final Trig t) {
      Complex[] roots = new Complex[n];
      Complex sum = this.one(f);
      roots[0] = sum;
      int west = n % 2 == 0 ? n / 2 : -1;
      int north = n % 4 == 0 ? n / 4 : -1;
      int south = n % 4 == 0 ? 3 * n / 4 : -1;
      int x = 1;

      int last;
      for(last = n - 1; x < last; ++x) {
         Complex c;
         if (north == x) {
            c = this.i(f);
         } else if (west == x) {
            c = this.one(f).unary_$minus(f);
         } else if (south == x) {
            c = this.i(f).unary_$minus(f);
         } else {
            c = this.polar(f.one(), f.div(f.times(f.times(t.pi(), f.fromInt(2)), f.fromInt(x)), f.fromInt(n)), f, t);
         }

         roots[x] = c;
         sum = sum.$plus((Complex)c, f);
      }

      roots[last] = this.zero(f).$minus((Complex)sum, f);
      return roots;
   }

   public Complex apply(final Object real, final Object imag) {
      return new Complex(real, imag);
   }

   public Option unapply(final Complex x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.real(), x$0.imag())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Complex$.class);
   }

   public Complex i$mDc$sp(final CommutativeRing T) {
      return new Complex$mcD$sp(T.zero$mcD$sp(), T.one$mcD$sp());
   }

   public Complex i$mFc$sp(final CommutativeRing T) {
      return new Complex$mcF$sp(T.zero$mcF$sp(), T.one$mcF$sp());
   }

   public Complex one$mDc$sp(final CommutativeRing T) {
      return new Complex$mcD$sp(T.one$mcD$sp(), T.zero$mcD$sp());
   }

   public Complex one$mFc$sp(final CommutativeRing T) {
      return new Complex$mcF$sp(T.one$mcF$sp(), T.zero$mcF$sp());
   }

   public Complex zero$mDc$sp(final CommutativeRing T) {
      return new Complex$mcD$sp(T.zero$mcD$sp(), T.zero$mcD$sp());
   }

   public Complex zero$mFc$sp(final CommutativeRing T) {
      return new Complex$mcF$sp(T.zero$mcF$sp(), T.zero$mcF$sp());
   }

   public Complex fromInt$mDc$sp(final int n, final CommutativeRing f) {
      return new Complex$mcD$sp(f.fromInt$mcD$sp(n), f.zero$mcD$sp());
   }

   public Complex fromInt$mFc$sp(final int n, final CommutativeRing f) {
      return new Complex$mcF$sp(f.fromInt$mcF$sp(n), f.zero$mcF$sp());
   }

   public Complex polar$mDc$sp(final double magnitude, final double angle, final Field evidence$1, final Trig evidence$2) {
      return new Complex$mcD$sp(evidence$1.times$mcD$sp(magnitude, Trig$.MODULE$.apply(evidence$2).cos$mcD$sp(angle)), evidence$1.times$mcD$sp(magnitude, Trig$.MODULE$.apply(evidence$2).sin$mcD$sp(angle)));
   }

   public Complex polar$mFc$sp(final float magnitude, final float angle, final Field evidence$1, final Trig evidence$2) {
      return new Complex$mcF$sp(evidence$1.times$mcF$sp(magnitude, Trig$.MODULE$.apply(evidence$2).cos$mcF$sp(angle)), evidence$1.times$mcF$sp(magnitude, Trig$.MODULE$.apply(evidence$2).sin$mcF$sp(angle)));
   }

   public Complex apply$mDc$sp(final double real, final CommutativeRing evidence$3) {
      return new Complex$mcD$sp(real, spire.algebra.package$.MODULE$.CRing().apply(evidence$3).zero$mcD$sp());
   }

   public Complex apply$mFc$sp(final float real, final CommutativeRing evidence$3) {
      return new Complex$mcF$sp(real, spire.algebra.package$.MODULE$.CRing().apply(evidence$3).zero$mcF$sp());
   }

   public Complex rootOfUnity$mDc$sp(final int n, final int x, final Field f, final Trig t) {
      if (x == 0) {
         return this.one$mDc$sp(f);
      } else {
         if (n % 2 == 0) {
            if (x == n / 2) {
               return this.one$mDc$sp(f).unary_$minus$mcD$sp(f);
            }

            if (n % 4 == 0) {
               if (x == n / 4) {
                  return this.i$mDc$sp(f);
               }

               if (x == n * 3 / 4) {
                  return this.i$mDc$sp(f).unary_$minus$mcD$sp(f);
               }
            }
         }

         return this.polar$mDc$sp(f.one$mcD$sp(), f.div$mcD$sp(f.times$mcD$sp(f.times$mcD$sp(t.pi$mcD$sp(), f.fromInt$mcD$sp(2)), f.fromInt$mcD$sp(x)), f.fromInt$mcD$sp(n)), f, t);
      }
   }

   public Complex rootOfUnity$mFc$sp(final int n, final int x, final Field f, final Trig t) {
      if (x == 0) {
         return this.one$mFc$sp(f);
      } else {
         if (n % 2 == 0) {
            if (x == n / 2) {
               return this.one$mFc$sp(f).unary_$minus$mcF$sp(f);
            }

            if (n % 4 == 0) {
               if (x == n / 4) {
                  return this.i$mFc$sp(f);
               }

               if (x == n * 3 / 4) {
                  return this.i$mFc$sp(f).unary_$minus$mcF$sp(f);
               }
            }
         }

         return this.polar$mFc$sp(f.one$mcF$sp(), f.div$mcF$sp(f.times$mcF$sp(f.times$mcF$sp(t.pi$mcF$sp(), f.fromInt$mcF$sp(2)), f.fromInt$mcF$sp(x)), f.fromInt$mcF$sp(n)), f, t);
      }
   }

   public Complex[] rootsOfUnity$mDc$sp(final int n, final Field f, final Trig t) {
      Complex[] roots = new Complex[n];
      Complex sum = this.one$mDc$sp(f);
      roots[0] = sum;
      int west = n % 2 == 0 ? n / 2 : -1;
      int north = n % 4 == 0 ? n / 4 : -1;
      int south = n % 4 == 0 ? 3 * n / 4 : -1;
      int x = 1;

      int last;
      for(last = n - 1; x < last; ++x) {
         Complex c;
         if (north == x) {
            c = this.i$mDc$sp(f);
         } else if (west == x) {
            c = this.one$mDc$sp(f).unary_$minus$mcD$sp(f);
         } else if (south == x) {
            c = this.i$mDc$sp(f).unary_$minus$mcD$sp(f);
         } else {
            c = this.polar$mDc$sp(f.one$mcD$sp(), f.div$mcD$sp(f.times$mcD$sp(f.times$mcD$sp(t.pi$mcD$sp(), f.fromInt$mcD$sp(2)), f.fromInt$mcD$sp(x)), f.fromInt$mcD$sp(n)), f, t);
         }

         roots[x] = c;
         sum = sum.$plus$mcD$sp(c, f);
      }

      roots[last] = this.zero$mDc$sp(f).$minus$mcD$sp(sum, f);
      return roots;
   }

   public Complex[] rootsOfUnity$mFc$sp(final int n, final Field f, final Trig t) {
      Complex[] roots = new Complex[n];
      Complex sum = this.one$mFc$sp(f);
      roots[0] = sum;
      int west = n % 2 == 0 ? n / 2 : -1;
      int north = n % 4 == 0 ? n / 4 : -1;
      int south = n % 4 == 0 ? 3 * n / 4 : -1;
      int x = 1;

      int last;
      for(last = n - 1; x < last; ++x) {
         Complex c;
         if (north == x) {
            c = this.i$mFc$sp(f);
         } else if (west == x) {
            c = this.one$mFc$sp(f).unary_$minus$mcF$sp(f);
         } else if (south == x) {
            c = this.i$mFc$sp(f).unary_$minus$mcF$sp(f);
         } else {
            c = this.polar$mFc$sp(f.one$mcF$sp(), f.div$mcF$sp(f.times$mcF$sp(f.times$mcF$sp(t.pi$mcF$sp(), f.fromInt$mcF$sp(2)), f.fromInt$mcF$sp(x)), f.fromInt$mcF$sp(n)), f, t);
         }

         roots[x] = c;
         sum = sum.$plus$mcF$sp(c, f);
      }

      roots[last] = this.zero$mFc$sp(f).$minus$mcF$sp(sum, f);
      return roots;
   }

   public Complex apply$mDc$sp(final double real, final double imag) {
      return new Complex$mcD$sp(real, imag);
   }

   public Complex apply$mFc$sp(final float real, final float imag) {
      return new Complex$mcF$sp(real, imag);
   }

   public Option unapply$mDc$sp(final Complex x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2.mcDD.sp(x$0.real$mcD$sp(), x$0.imag$mcD$sp())));
   }

   public Option unapply$mFc$sp(final Complex x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToFloat(x$0.real$mcF$sp()), BoxesRunTime.boxToFloat(x$0.imag$mcF$sp()))));
   }

   private Complex$() {
   }
}
