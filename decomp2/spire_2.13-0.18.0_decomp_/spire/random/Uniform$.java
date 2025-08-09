package spire.random;

import java.lang.invoke.SerializedLambda;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.package.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import spire.math.Rational;
import spire.math.Rational$;
import spire.math.UInt;
import spire.math.UInt$;
import spire.math.ULong;
import spire.math.ULong$;
import spire.math.package$;

public final class Uniform$ {
   public static final Uniform$ MODULE$ = new Uniform$();
   private static final Uniform UniformInt = new Uniform$mcI$sp() {
      public Dist apply$mcZ$sp(final boolean min, final boolean max) {
         return Uniform.apply$mcZ$sp$(this, min, max);
      }

      public Dist apply$mcB$sp(final byte min, final byte max) {
         return Uniform.apply$mcB$sp$(this, min, max);
      }

      public Dist apply$mcC$sp(final char min, final char max) {
         return Uniform.apply$mcC$sp$(this, min, max);
      }

      public Dist apply$mcD$sp(final double min, final double max) {
         return Uniform.apply$mcD$sp$(this, min, max);
      }

      public Dist apply$mcF$sp(final float min, final float max) {
         return Uniform.apply$mcF$sp$(this, min, max);
      }

      public Dist apply$mcJ$sp(final long min, final long max) {
         return Uniform.apply$mcJ$sp$(this, min, max);
      }

      public Dist apply$mcS$sp(final short min, final short max) {
         return Uniform.apply$mcS$sp$(this, min, max);
      }

      public Dist apply$mcV$sp(final BoxedUnit min, final BoxedUnit max) {
         return Uniform.apply$mcV$sp$(this, min, max);
      }

      public Dist apply(final int min, final int max) {
         return this.apply$mcI$sp(min, max);
      }

      public Dist apply$mcI$sp(final int min, final int max) {
         return new DistFromGen$mcI$sp((x$1) -> BoxesRunTime.boxToInteger($anonfun$apply$1(min, max, x$1)));
      }

      // $FF: synthetic method
      public static final int $anonfun$apply$1(final int min$1, final int max$1, final Generator x$1) {
         return x$1.nextInt(min$1, max$1);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   };
   private static final Uniform UniformLong = new Uniform$mcJ$sp() {
      public Dist apply$mcZ$sp(final boolean min, final boolean max) {
         return Uniform.apply$mcZ$sp$(this, min, max);
      }

      public Dist apply$mcB$sp(final byte min, final byte max) {
         return Uniform.apply$mcB$sp$(this, min, max);
      }

      public Dist apply$mcC$sp(final char min, final char max) {
         return Uniform.apply$mcC$sp$(this, min, max);
      }

      public Dist apply$mcD$sp(final double min, final double max) {
         return Uniform.apply$mcD$sp$(this, min, max);
      }

      public Dist apply$mcF$sp(final float min, final float max) {
         return Uniform.apply$mcF$sp$(this, min, max);
      }

      public Dist apply$mcI$sp(final int min, final int max) {
         return Uniform.apply$mcI$sp$(this, min, max);
      }

      public Dist apply$mcS$sp(final short min, final short max) {
         return Uniform.apply$mcS$sp$(this, min, max);
      }

      public Dist apply$mcV$sp(final BoxedUnit min, final BoxedUnit max) {
         return Uniform.apply$mcV$sp$(this, min, max);
      }

      public Dist apply(final long min, final long max) {
         return this.apply$mcJ$sp(min, max);
      }

      public Dist apply$mcJ$sp(final long min, final long max) {
         return new DistFromGen$mcJ$sp((x$2) -> BoxesRunTime.boxToLong($anonfun$apply$2(min, max, x$2)));
      }

      // $FF: synthetic method
      public static final long $anonfun$apply$2(final long min$2, final long max$2, final Generator x$2) {
         return x$2.nextLong(min$2, max$2);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   };
   private static final Uniform UniformUInt = new Uniform() {
      public Dist apply$mcZ$sp(final boolean min, final boolean max) {
         return Uniform.apply$mcZ$sp$(this, min, max);
      }

      public Dist apply$mcB$sp(final byte min, final byte max) {
         return Uniform.apply$mcB$sp$(this, min, max);
      }

      public Dist apply$mcC$sp(final char min, final char max) {
         return Uniform.apply$mcC$sp$(this, min, max);
      }

      public Dist apply$mcD$sp(final double min, final double max) {
         return Uniform.apply$mcD$sp$(this, min, max);
      }

      public Dist apply$mcF$sp(final float min, final float max) {
         return Uniform.apply$mcF$sp$(this, min, max);
      }

      public Dist apply$mcI$sp(final int min, final int max) {
         return Uniform.apply$mcI$sp$(this, min, max);
      }

      public Dist apply$mcJ$sp(final long min, final long max) {
         return Uniform.apply$mcJ$sp$(this, min, max);
      }

      public Dist apply$mcS$sp(final short min, final short max) {
         return Uniform.apply$mcS$sp$(this, min, max);
      }

      public Dist apply$mcV$sp(final BoxedUnit min, final BoxedUnit max) {
         return Uniform.apply$mcV$sp$(this, min, max);
      }

      public Dist apply(final int min, final int max) {
         return new DistFromGen((g) -> new UInt($anonfun$apply$3(min, max, g)));
      }

      // $FF: synthetic method
      public static final int $anonfun$apply$3(final int min$3, final int max$3, final Generator g) {
         return UInt$.MODULE$.apply(g.nextInt(min$3, max$3));
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   };
   private static final Uniform UniformULong = new Uniform() {
      public Dist apply$mcZ$sp(final boolean min, final boolean max) {
         return Uniform.apply$mcZ$sp$(this, min, max);
      }

      public Dist apply$mcB$sp(final byte min, final byte max) {
         return Uniform.apply$mcB$sp$(this, min, max);
      }

      public Dist apply$mcC$sp(final char min, final char max) {
         return Uniform.apply$mcC$sp$(this, min, max);
      }

      public Dist apply$mcD$sp(final double min, final double max) {
         return Uniform.apply$mcD$sp$(this, min, max);
      }

      public Dist apply$mcF$sp(final float min, final float max) {
         return Uniform.apply$mcF$sp$(this, min, max);
      }

      public Dist apply$mcI$sp(final int min, final int max) {
         return Uniform.apply$mcI$sp$(this, min, max);
      }

      public Dist apply$mcJ$sp(final long min, final long max) {
         return Uniform.apply$mcJ$sp$(this, min, max);
      }

      public Dist apply$mcS$sp(final short min, final short max) {
         return Uniform.apply$mcS$sp$(this, min, max);
      }

      public Dist apply$mcV$sp(final BoxedUnit min, final BoxedUnit max) {
         return Uniform.apply$mcV$sp$(this, min, max);
      }

      public Dist apply(final long min, final long max) {
         return new DistFromGen((g) -> new ULong($anonfun$apply$4(min, max, g)));
      }

      // $FF: synthetic method
      public static final long $anonfun$apply$4(final long min$4, final long max$4, final Generator g) {
         return ULong$.MODULE$.apply(g.nextLong(min$4, max$4));
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   };
   private static final Uniform UniformFloat = new Uniform$mcF$sp() {
      public Dist apply$mcZ$sp(final boolean min, final boolean max) {
         return Uniform.apply$mcZ$sp$(this, min, max);
      }

      public Dist apply$mcB$sp(final byte min, final byte max) {
         return Uniform.apply$mcB$sp$(this, min, max);
      }

      public Dist apply$mcC$sp(final char min, final char max) {
         return Uniform.apply$mcC$sp$(this, min, max);
      }

      public Dist apply$mcD$sp(final double min, final double max) {
         return Uniform.apply$mcD$sp$(this, min, max);
      }

      public Dist apply$mcI$sp(final int min, final int max) {
         return Uniform.apply$mcI$sp$(this, min, max);
      }

      public Dist apply$mcJ$sp(final long min, final long max) {
         return Uniform.apply$mcJ$sp$(this, min, max);
      }

      public Dist apply$mcS$sp(final short min, final short max) {
         return Uniform.apply$mcS$sp$(this, min, max);
      }

      public Dist apply$mcV$sp(final BoxedUnit min, final BoxedUnit max) {
         return Uniform.apply$mcV$sp$(this, min, max);
      }

      public Dist apply(final float min, final float max) {
         return this.apply$mcF$sp(min, max);
      }

      public Dist apply$mcF$sp(final float min, final float max) {
         return new DistFromGen$mcF$sp((x$3) -> BoxesRunTime.boxToFloat($anonfun$apply$5(min, max, x$3)));
      }

      // $FF: synthetic method
      public static final float $anonfun$apply$5(final float min$5, final float max$5, final Generator x$3) {
         return x$3.nextFloat(min$5, max$5);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   };
   private static final Uniform UniformDouble = new Uniform$mcD$sp() {
      public Dist apply$mcZ$sp(final boolean min, final boolean max) {
         return Uniform.apply$mcZ$sp$(this, min, max);
      }

      public Dist apply$mcB$sp(final byte min, final byte max) {
         return Uniform.apply$mcB$sp$(this, min, max);
      }

      public Dist apply$mcC$sp(final char min, final char max) {
         return Uniform.apply$mcC$sp$(this, min, max);
      }

      public Dist apply$mcF$sp(final float min, final float max) {
         return Uniform.apply$mcF$sp$(this, min, max);
      }

      public Dist apply$mcI$sp(final int min, final int max) {
         return Uniform.apply$mcI$sp$(this, min, max);
      }

      public Dist apply$mcJ$sp(final long min, final long max) {
         return Uniform.apply$mcJ$sp$(this, min, max);
      }

      public Dist apply$mcS$sp(final short min, final short max) {
         return Uniform.apply$mcS$sp$(this, min, max);
      }

      public Dist apply$mcV$sp(final BoxedUnit min, final BoxedUnit max) {
         return Uniform.apply$mcV$sp$(this, min, max);
      }

      public Dist apply(final double min, final double max) {
         return this.apply$mcD$sp(min, max);
      }

      public Dist apply$mcD$sp(final double min, final double max) {
         return new DistFromGen$mcD$sp((x$4) -> BoxesRunTime.boxToDouble($anonfun$apply$6(min, max, x$4)));
      }

      // $FF: synthetic method
      public static final double $anonfun$apply$6(final double min$6, final double max$6, final Generator x$4) {
         return x$4.nextDouble(min$6, max$6);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   };
   private static final Uniform UniformBigInt = new Uniform() {
      public Dist apply$mcZ$sp(final boolean min, final boolean max) {
         return Uniform.apply$mcZ$sp$(this, min, max);
      }

      public Dist apply$mcB$sp(final byte min, final byte max) {
         return Uniform.apply$mcB$sp$(this, min, max);
      }

      public Dist apply$mcC$sp(final char min, final char max) {
         return Uniform.apply$mcC$sp$(this, min, max);
      }

      public Dist apply$mcD$sp(final double min, final double max) {
         return Uniform.apply$mcD$sp$(this, min, max);
      }

      public Dist apply$mcF$sp(final float min, final float max) {
         return Uniform.apply$mcF$sp$(this, min, max);
      }

      public Dist apply$mcI$sp(final int min, final int max) {
         return Uniform.apply$mcI$sp$(this, min, max);
      }

      public Dist apply$mcJ$sp(final long min, final long max) {
         return Uniform.apply$mcJ$sp$(this, min, max);
      }

      public Dist apply$mcS$sp(final short min, final short max) {
         return Uniform.apply$mcS$sp$(this, min, max);
      }

      public Dist apply$mcV$sp(final BoxedUnit min, final BoxedUnit max) {
         return Uniform.apply$mcV$sp$(this, min, max);
      }

      public Dist apply(final BigInt min, final BigInt max) {
         BigInt range = max.$minus(min);
         int width = range.bitLength();
         DistFromGen var10000;
         if (width < 64) {
            long range0 = range.toLong();
            var10000 = new DistFromGen((gen) -> min.$plus(.MODULE$.BigInt().apply(gen.nextLong(0L, range0))));
         } else {
            int mask0 = (1 << width % 8) - 1;
            int mask = mask0 == 0 ? 255 : mask0;
            var10000 = new DistFromGen((gen) -> {
               byte[] bytes = new byte[(width + 7) / 8];
               return min.$plus(this.loop$1(gen, bytes, mask, range));
            });
         }

         return var10000;
      }

      private final BigInt loop$1(final Generator gen$1, final byte[] bytes$1, final int mask$1, final BigInt range$1) {
         BigInt n;
         do {
            gen$1.fillBytes(bytes$1);
            bytes$1[0] = (byte)(bytes$1[0] & mask$1);
            n = .MODULE$.BigInt().apply(1, bytes$1);
         } while(n.$greater(range$1));

         return n;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   };
   private static final Uniform UniformBigDecimal = new Uniform() {
      public Dist apply$mcZ$sp(final boolean min, final boolean max) {
         return Uniform.apply$mcZ$sp$(this, min, max);
      }

      public Dist apply$mcB$sp(final byte min, final byte max) {
         return Uniform.apply$mcB$sp$(this, min, max);
      }

      public Dist apply$mcC$sp(final char min, final char max) {
         return Uniform.apply$mcC$sp$(this, min, max);
      }

      public Dist apply$mcD$sp(final double min, final double max) {
         return Uniform.apply$mcD$sp$(this, min, max);
      }

      public Dist apply$mcF$sp(final float min, final float max) {
         return Uniform.apply$mcF$sp$(this, min, max);
      }

      public Dist apply$mcI$sp(final int min, final int max) {
         return Uniform.apply$mcI$sp$(this, min, max);
      }

      public Dist apply$mcJ$sp(final long min, final long max) {
         return Uniform.apply$mcJ$sp$(this, min, max);
      }

      public Dist apply$mcS$sp(final short min, final short max) {
         return Uniform.apply$mcS$sp$(this, min, max);
      }

      public Dist apply$mcV$sp(final BoxedUnit min, final BoxedUnit max) {
         return Uniform.apply$mcV$sp$(this, min, max);
      }

      public Dist apply(final BigDecimal min, final BigDecimal max) {
         int precision = package$.MODULE$.max(min.mc().getPrecision(), max.mc().getPrecision());
         if (precision == 0) {
            throw new IllegalArgumentException("Both min and max provided to UniformBigDecimal have unlimited precision. Cannot produce uniform distributions with unlimited precision.");
         } else {
            BigDecimal range = max.$minus(min);
            Dist dist = Uniform$.MODULE$.UniformBigInt().apply(scala.math.BigInt..MODULE$.int2bigInt(0), .MODULE$.BigInt().apply(10).pow(precision));
            return new DistFromGen((gen) -> min.$plus(range.$times(.MODULE$.BigDecimal().apply((BigInt)dist.apply(gen), precision))));
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   };

   public final Uniform apply(final Uniform u) {
      return u;
   }

   public Dist apply(final Object min, final Object max, final Uniform u) {
      return u.apply(min, max);
   }

   public Uniform UniformInt() {
      return UniformInt;
   }

   public Uniform UniformLong() {
      return UniformLong;
   }

   public Uniform UniformUInt() {
      return UniformUInt;
   }

   public Uniform UniformULong() {
      return UniformULong;
   }

   public Uniform UniformFloat() {
      return UniformFloat;
   }

   public Uniform UniformDouble() {
      return UniformDouble;
   }

   public Uniform UniformBigInt() {
      return UniformBigInt;
   }

   public Uniform UniformBigDecimal() {
      return UniformBigDecimal;
   }

   public Uniform uniformRational(final Rational eps) {
      return new Uniform(eps) {
         private final Rational eps$1;

         public Dist apply$mcZ$sp(final boolean min, final boolean max) {
            return Uniform.apply$mcZ$sp$(this, min, max);
         }

         public Dist apply$mcB$sp(final byte min, final byte max) {
            return Uniform.apply$mcB$sp$(this, min, max);
         }

         public Dist apply$mcC$sp(final char min, final char max) {
            return Uniform.apply$mcC$sp$(this, min, max);
         }

         public Dist apply$mcD$sp(final double min, final double max) {
            return Uniform.apply$mcD$sp$(this, min, max);
         }

         public Dist apply$mcF$sp(final float min, final float max) {
            return Uniform.apply$mcF$sp$(this, min, max);
         }

         public Dist apply$mcI$sp(final int min, final int max) {
            return Uniform.apply$mcI$sp$(this, min, max);
         }

         public Dist apply$mcJ$sp(final long min, final long max) {
            return Uniform.apply$mcJ$sp$(this, min, max);
         }

         public Dist apply$mcS$sp(final short min, final short max) {
            return Uniform.apply$mcS$sp$(this, min, max);
         }

         public Dist apply$mcV$sp(final BoxedUnit min, final BoxedUnit max) {
            return Uniform.apply$mcV$sp$(this, min, max);
         }

         public Dist apply(final Rational min, final Rational max) {
            BigInt num = max.$minus(min).$div(this.eps$1).toBigInt();
            return Uniform$.MODULE$.apply(Uniform$.MODULE$.UniformBigInt()).apply(scala.math.BigInt..MODULE$.int2bigInt(0), num).map((n) -> min.$plus(Rational$.MODULE$.apply(n).$times(this.eps$1)));
         }

         public {
            this.eps$1 = eps$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public final Uniform apply$mZc$sp(final Uniform u) {
      return u;
   }

   public final Uniform apply$mBc$sp(final Uniform u) {
      return u;
   }

   public final Uniform apply$mCc$sp(final Uniform u) {
      return u;
   }

   public final Uniform apply$mDc$sp(final Uniform u) {
      return u;
   }

   public final Uniform apply$mFc$sp(final Uniform u) {
      return u;
   }

   public final Uniform apply$mIc$sp(final Uniform u) {
      return u;
   }

   public final Uniform apply$mJc$sp(final Uniform u) {
      return u;
   }

   public final Uniform apply$mSc$sp(final Uniform u) {
      return u;
   }

   public final Uniform apply$mVc$sp(final Uniform u) {
      return u;
   }

   public Dist apply$mZc$sp(final boolean min, final boolean max, final Uniform u) {
      return u.apply$mcZ$sp(min, max);
   }

   public Dist apply$mBc$sp(final byte min, final byte max, final Uniform u) {
      return u.apply$mcB$sp(min, max);
   }

   public Dist apply$mCc$sp(final char min, final char max, final Uniform u) {
      return u.apply$mcC$sp(min, max);
   }

   public Dist apply$mDc$sp(final double min, final double max, final Uniform u) {
      return u.apply$mcD$sp(min, max);
   }

   public Dist apply$mFc$sp(final float min, final float max, final Uniform u) {
      return u.apply$mcF$sp(min, max);
   }

   public Dist apply$mIc$sp(final int min, final int max, final Uniform u) {
      return u.apply$mcI$sp(min, max);
   }

   public Dist apply$mJc$sp(final long min, final long max, final Uniform u) {
      return u.apply$mcJ$sp(min, max);
   }

   public Dist apply$mSc$sp(final short min, final short max, final Uniform u) {
      return u.apply$mcS$sp(min, max);
   }

   public Dist apply$mVc$sp(final BoxedUnit min, final BoxedUnit max, final Uniform u) {
      return u.apply$mcV$sp(min, max);
   }

   private Uniform$() {
   }
}
