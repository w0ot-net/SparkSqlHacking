package org.apache.spark.mllib.tree.impurity;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=aAB\n\u0015\u0003\u0003Q\u0002\u0005\u0003\u00055\u0001\t\u0015\r\u0011\"\u00016\u0011!a\u0004A!A!\u0002\u00131\u0004\"B\u001f\u0001\t\u0003q\u0004\"\u0002\"\u0001\r\u0003\u0019\u0005\"\u0002#\u0001\r\u0003)\u0005\"\u0002$\u0001\t\u00039\u0005\"\u0002&\u0001\t\u0003Y\u0005\"B'\u0001\r\u0003q\u0005bB(\u0001\u0001\u00045\t\u0001\u0015\u0005\b)\u0002\u0001\rQ\"\u0001V\u0011\u0015Y\u0006A\"\u0001O\u0011\u0015a\u0006\u0001\"\u0001^\u0011\u0015\u0001\u0007\u0001\"\u0005b\u000f\u00199G\u0003#\u0001\u001bQ\u001a11\u0003\u0006E\u00015%DQ!P\b\u0005\u0002EDQA]\b\u0005\u0002MD\u0001b`\b\u0002\u0002\u0013%\u0011\u0011\u0001\u0002\u0013\u00136\u0004XO]5us\u000e\u000bGnY;mCR|'O\u0003\u0002\u0016-\u0005A\u0011.\u001c9ve&$\u0018P\u0003\u0002\u00181\u0005!AO]3f\u0015\tI\"$A\u0003nY2L'M\u0003\u0002\u001c9\u0005)1\u000f]1sW*\u0011QDH\u0001\u0007CB\f7\r[3\u000b\u0003}\t1a\u001c:h'\r\u0001\u0011e\n\t\u0003E\u0015j\u0011a\t\u0006\u0002I\u0005)1oY1mC&\u0011ae\t\u0002\u0007\u0003:L(+\u001a4\u0011\u0005!\ndBA\u00150\u001d\tQc&D\u0001,\u0015\taS&\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005!\u0013B\u0001\u0019$\u0003\u001d\u0001\u0018mY6bO\u0016L!AM\u001a\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005A\u001a\u0013!B:uCR\u001cX#\u0001\u001c\u0011\u0007\t:\u0014(\u0003\u00029G\t)\u0011I\u001d:bsB\u0011!EO\u0005\u0003w\r\u0012a\u0001R8vE2,\u0017AB:uCR\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\u0003\u007f\u0005\u0003\"\u0001\u0011\u0001\u000e\u0003QAQ\u0001N\u0002A\u0002Y\nAaY8qsV\tq(A\u0005dC2\u001cW\u000f\\1uKR\t\u0011(A\u0002bI\u0012$\"a\u0010%\t\u000b%3\u0001\u0019A \u0002\u000b=$\b.\u001a:\u0002\u0011M,(\r\u001e:bGR$\"a\u0010'\t\u000b%;\u0001\u0019A \u0002\u000b\r|WO\u001c;\u0016\u0003e\n\u0001B]1x\u0007>,h\u000e^\u000b\u0002#B\u0011!EU\u0005\u0003'\u000e\u0012A\u0001T8oO\u0006a!/Y<D_VtGo\u0018\u0013fcR\u0011a+\u0017\t\u0003E]K!\u0001W\u0012\u0003\tUs\u0017\u000e\u001e\u0005\b5*\t\t\u00111\u0001R\u0003\rAH%M\u0001\baJ,G-[2u\u0003\u0011\u0001(o\u001c2\u0015\u0005er\u0006\"B0\r\u0001\u0004I\u0014!\u00027bE\u0016d\u0017AG5oI\u0016DxJ\u001a'be\u001e,7\u000f^!se\u0006LX\t\\3nK:$HC\u00012f!\t\u00113-\u0003\u0002eG\t\u0019\u0011J\u001c;\t\u000b\u0019l\u0001\u0019\u0001\u001c\u0002\u000b\u0005\u0014(/Y=\u0002%%k\u0007/\u001e:jif\u001c\u0015\r\\2vY\u0006$xN\u001d\t\u0003\u0001>\u00192aD\u0011k!\tY\u0007/D\u0001m\u0015\tig.\u0001\u0002j_*\tq.\u0001\u0003kCZ\f\u0017B\u0001\u001am)\u0005A\u0017!D4fi\u000e\u000bGnY;mCR|'\u000f\u0006\u0003@ivt\b\"B\u000b\u0012\u0001\u0004)\bC\u0001<{\u001d\t9\b\u0010\u0005\u0002+G%\u0011\u0011pI\u0001\u0007!J,G-\u001a4\n\u0005md(AB*ue&twM\u0003\u0002zG!)A'\u0005a\u0001m!)q*\u0005a\u0001#\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u0001\t\u0005\u0003\u000b\tY!\u0004\u0002\u0002\b)\u0019\u0011\u0011\u00028\u0002\t1\fgnZ\u0005\u0005\u0003\u001b\t9A\u0001\u0004PE*,7\r\u001e"
)
public abstract class ImpurityCalculator implements Serializable {
   private final double[] stats;

   public static ImpurityCalculator getCalculator(final String impurity, final double[] stats, final long rawCount) {
      return ImpurityCalculator$.MODULE$.getCalculator(impurity, stats, rawCount);
   }

   public double[] stats() {
      return this.stats;
   }

   public abstract ImpurityCalculator copy();

   public abstract double calculate();

   public ImpurityCalculator add(final ImpurityCalculator other) {
      .MODULE$.require(this.stats().length == other.stats().length, () -> {
         int var10000 = this.stats().length;
         return "Two ImpurityCalculator instances cannot be added with different counts sizes.  Sizes are " + var10000 + " and " + other.stats().length + ".";
      });
      int i = 0;

      for(int len = other.stats().length; i < len; ++i) {
         this.stats()[i] += other.stats()[i];
      }

      this.rawCount_$eq(this.rawCount() + other.rawCount());
      return this;
   }

   public ImpurityCalculator subtract(final ImpurityCalculator other) {
      .MODULE$.require(this.stats().length == other.stats().length, () -> {
         int var10000 = this.stats().length;
         return "Two ImpurityCalculator instances cannot be subtracted with different counts sizes.  Sizes are " + var10000 + " and " + other.stats().length + ".";
      });
      int i = 0;

      for(int len = other.stats().length; i < len; ++i) {
         this.stats()[i] -= other.stats()[i];
      }

      this.rawCount_$eq(this.rawCount() - other.rawCount());
      return this;
   }

   public abstract double count();

   public abstract long rawCount();

   public abstract void rawCount_$eq(final long x$1);

   public abstract double predict();

   public double prob(final double label) {
      return (double)-1.0F;
   }

   public int indexOfLargestArrayElement(final double[] array) {
      Tuple3 result = (Tuple3)scala.collection.ArrayOps..MODULE$.foldLeft$extension(.MODULE$.doubleArrayOps(array), new Tuple3(BoxesRunTime.boxToInteger(-1), BoxesRunTime.boxToDouble(-Double.MAX_VALUE), BoxesRunTime.boxToInteger(0)), (x0$1, x1$1) -> $anonfun$indexOfLargestArrayElement$1(x0$1, BoxesRunTime.unboxToDouble(x1$1)));
      if (BoxesRunTime.unboxToInt(result._1()) < 0) {
         throw new RuntimeException("ImpurityCalculator internal error: indexOfLargestArrayElement failed");
      } else {
         return BoxesRunTime.unboxToInt(result._1());
      }
   }

   // $FF: synthetic method
   public static final Tuple3 $anonfun$indexOfLargestArrayElement$1(final Tuple3 x0$1, final double x1$1) {
      Tuple2 var4 = new Tuple2(x0$1, BoxesRunTime.boxToDouble(x1$1));
      if (var4 != null) {
         Tuple3 var5 = (Tuple3)var4._1();
         double currentValue = var4._2$mcD$sp();
         if (var5 != null) {
            int maxIndex = BoxesRunTime.unboxToInt(var5._1());
            double maxValue = BoxesRunTime.unboxToDouble(var5._2());
            int currentIndex = BoxesRunTime.unboxToInt(var5._3());
            if (currentValue > maxValue) {
               return new Tuple3(BoxesRunTime.boxToInteger(currentIndex), BoxesRunTime.boxToDouble(currentValue), BoxesRunTime.boxToInteger(currentIndex + 1));
            }

            return new Tuple3(BoxesRunTime.boxToInteger(maxIndex), BoxesRunTime.boxToDouble(maxValue), BoxesRunTime.boxToInteger(currentIndex + 1));
         }
      }

      throw new MatchError(var4);
   }

   public ImpurityCalculator(final double[] stats) {
      this.stats = stats;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
