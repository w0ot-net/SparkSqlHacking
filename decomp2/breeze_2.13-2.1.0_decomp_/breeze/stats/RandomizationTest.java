package breeze.stats;

import breeze.stats.distributions.Rand$;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Stream;
import scala.collection.mutable.ArrayBuffer;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005Q3A!\u0003\u0006\u0001\u001f!AA\u0007\u0001BC\u0002\u0013\u0005Q\u0007\u0003\u0005:\u0001\t\u0005\t\u0015!\u00037\u0011!Q\u0004A!b\u0001\n\u0003Y\u0004\u0002C \u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001f\t\u000b\u0001\u0003A\u0011A!\t\u000b\u0001\u0003A\u0011\u0001$\t\u000b!\u0003A\u0011A%\t\u000b9\u0003A\u0011A(\u0003#I\u000bg\u000eZ8nSj\fG/[8o)\u0016\u001cHO\u0003\u0002\f\u0019\u0005)1\u000f^1ug*\tQ\"\u0001\u0004ce\u0016,'0Z\u0002\u0001+\t\u0001\u0002fE\u0002\u0001#]\u0001\"AE\u000b\u000e\u0003MQ\u0011\u0001F\u0001\u0006g\u000e\fG.Y\u0005\u0003-M\u0011a!\u00118z%\u00164\u0007#\u0002\n\u00195i\t\u0014BA\r\u0014\u0005%1UO\\2uS>t'\u0007E\u0002\u001cG\u0019r!\u0001H\u0011\u000f\u0005u\u0001S\"\u0001\u0010\u000b\u0005}q\u0011A\u0002\u001fs_>$h(C\u0001\u0015\u0013\t\u00113#A\u0004qC\u000e\\\u0017mZ3\n\u0005\u0011*#aA*fc*\u0011!e\u0005\t\u0003O!b\u0001\u0001B\u0003*\u0001\t\u0007!FA\u0001M#\tYc\u0006\u0005\u0002\u0013Y%\u0011Qf\u0005\u0002\b\u001d>$\b.\u001b8h!\t\u0011r&\u0003\u00021'\t\u0019\u0011I\\=\u0011\u0005I\u0011\u0014BA\u001a\u0014\u0005\u0019!u.\u001e2mK\u0006Qa.^7TC6\u0004H.Z:\u0016\u0003Y\u0002\"AE\u001c\n\u0005a\u001a\"aA%oi\u0006Ya.^7TC6\u0004H.Z:!\u00031)'O]8s\u001b\u0016\f7/\u001e:f+\u0005a\u0004\u0003\u0002\n>5EJ!AP\n\u0003\u0013\u0019+hn\u0019;j_:\f\u0014!D3se>\u0014X*Z1tkJ,\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0004\u0005\u0012+\u0005cA\"\u0001M5\t!\u0002C\u00035\u000b\u0001\u0007a\u0007C\u0003;\u000b\u0001\u0007A\b\u0006\u0002C\u000f\")!H\u0002a\u0001y\u0005!A-\u001b4g)\r\t$\n\u0014\u0005\u0006\u0017\u001e\u0001\rAG\u0001\u0003YFBQ!T\u0004A\u0002i\t!\u0001\u001c\u001a\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007E\u0002&\u000bC\u0003R\u0011\u0001\u0007!$A\u0005mC\n,G.\u001b8hc!)1\u000b\u0003a\u00015\u0005IA.\u00192fY&twM\r"
)
public class RandomizationTest implements Function2 {
   private final int numSamples;
   private final Function1 errorMeasure;

   public boolean apply$mcZDD$sp(final double v1, final double v2) {
      return Function2.apply$mcZDD$sp$(this, v1, v2);
   }

   public double apply$mcDDD$sp(final double v1, final double v2) {
      return Function2.apply$mcDDD$sp$(this, v1, v2);
   }

   public float apply$mcFDD$sp(final double v1, final double v2) {
      return Function2.apply$mcFDD$sp$(this, v1, v2);
   }

   public int apply$mcIDD$sp(final double v1, final double v2) {
      return Function2.apply$mcIDD$sp$(this, v1, v2);
   }

   public long apply$mcJDD$sp(final double v1, final double v2) {
      return Function2.apply$mcJDD$sp$(this, v1, v2);
   }

   public void apply$mcVDD$sp(final double v1, final double v2) {
      Function2.apply$mcVDD$sp$(this, v1, v2);
   }

   public boolean apply$mcZDI$sp(final double v1, final int v2) {
      return Function2.apply$mcZDI$sp$(this, v1, v2);
   }

   public double apply$mcDDI$sp(final double v1, final int v2) {
      return Function2.apply$mcDDI$sp$(this, v1, v2);
   }

   public float apply$mcFDI$sp(final double v1, final int v2) {
      return Function2.apply$mcFDI$sp$(this, v1, v2);
   }

   public int apply$mcIDI$sp(final double v1, final int v2) {
      return Function2.apply$mcIDI$sp$(this, v1, v2);
   }

   public long apply$mcJDI$sp(final double v1, final int v2) {
      return Function2.apply$mcJDI$sp$(this, v1, v2);
   }

   public void apply$mcVDI$sp(final double v1, final int v2) {
      Function2.apply$mcVDI$sp$(this, v1, v2);
   }

   public boolean apply$mcZDJ$sp(final double v1, final long v2) {
      return Function2.apply$mcZDJ$sp$(this, v1, v2);
   }

   public double apply$mcDDJ$sp(final double v1, final long v2) {
      return Function2.apply$mcDDJ$sp$(this, v1, v2);
   }

   public float apply$mcFDJ$sp(final double v1, final long v2) {
      return Function2.apply$mcFDJ$sp$(this, v1, v2);
   }

   public int apply$mcIDJ$sp(final double v1, final long v2) {
      return Function2.apply$mcIDJ$sp$(this, v1, v2);
   }

   public long apply$mcJDJ$sp(final double v1, final long v2) {
      return Function2.apply$mcJDJ$sp$(this, v1, v2);
   }

   public void apply$mcVDJ$sp(final double v1, final long v2) {
      Function2.apply$mcVDJ$sp$(this, v1, v2);
   }

   public boolean apply$mcZID$sp(final int v1, final double v2) {
      return Function2.apply$mcZID$sp$(this, v1, v2);
   }

   public double apply$mcDID$sp(final int v1, final double v2) {
      return Function2.apply$mcDID$sp$(this, v1, v2);
   }

   public float apply$mcFID$sp(final int v1, final double v2) {
      return Function2.apply$mcFID$sp$(this, v1, v2);
   }

   public int apply$mcIID$sp(final int v1, final double v2) {
      return Function2.apply$mcIID$sp$(this, v1, v2);
   }

   public long apply$mcJID$sp(final int v1, final double v2) {
      return Function2.apply$mcJID$sp$(this, v1, v2);
   }

   public void apply$mcVID$sp(final int v1, final double v2) {
      Function2.apply$mcVID$sp$(this, v1, v2);
   }

   public boolean apply$mcZII$sp(final int v1, final int v2) {
      return Function2.apply$mcZII$sp$(this, v1, v2);
   }

   public double apply$mcDII$sp(final int v1, final int v2) {
      return Function2.apply$mcDII$sp$(this, v1, v2);
   }

   public float apply$mcFII$sp(final int v1, final int v2) {
      return Function2.apply$mcFII$sp$(this, v1, v2);
   }

   public int apply$mcIII$sp(final int v1, final int v2) {
      return Function2.apply$mcIII$sp$(this, v1, v2);
   }

   public long apply$mcJII$sp(final int v1, final int v2) {
      return Function2.apply$mcJII$sp$(this, v1, v2);
   }

   public void apply$mcVII$sp(final int v1, final int v2) {
      Function2.apply$mcVII$sp$(this, v1, v2);
   }

   public boolean apply$mcZIJ$sp(final int v1, final long v2) {
      return Function2.apply$mcZIJ$sp$(this, v1, v2);
   }

   public double apply$mcDIJ$sp(final int v1, final long v2) {
      return Function2.apply$mcDIJ$sp$(this, v1, v2);
   }

   public float apply$mcFIJ$sp(final int v1, final long v2) {
      return Function2.apply$mcFIJ$sp$(this, v1, v2);
   }

   public int apply$mcIIJ$sp(final int v1, final long v2) {
      return Function2.apply$mcIIJ$sp$(this, v1, v2);
   }

   public long apply$mcJIJ$sp(final int v1, final long v2) {
      return Function2.apply$mcJIJ$sp$(this, v1, v2);
   }

   public void apply$mcVIJ$sp(final int v1, final long v2) {
      Function2.apply$mcVIJ$sp$(this, v1, v2);
   }

   public boolean apply$mcZJD$sp(final long v1, final double v2) {
      return Function2.apply$mcZJD$sp$(this, v1, v2);
   }

   public double apply$mcDJD$sp(final long v1, final double v2) {
      return Function2.apply$mcDJD$sp$(this, v1, v2);
   }

   public float apply$mcFJD$sp(final long v1, final double v2) {
      return Function2.apply$mcFJD$sp$(this, v1, v2);
   }

   public int apply$mcIJD$sp(final long v1, final double v2) {
      return Function2.apply$mcIJD$sp$(this, v1, v2);
   }

   public long apply$mcJJD$sp(final long v1, final double v2) {
      return Function2.apply$mcJJD$sp$(this, v1, v2);
   }

   public void apply$mcVJD$sp(final long v1, final double v2) {
      Function2.apply$mcVJD$sp$(this, v1, v2);
   }

   public boolean apply$mcZJI$sp(final long v1, final int v2) {
      return Function2.apply$mcZJI$sp$(this, v1, v2);
   }

   public double apply$mcDJI$sp(final long v1, final int v2) {
      return Function2.apply$mcDJI$sp$(this, v1, v2);
   }

   public float apply$mcFJI$sp(final long v1, final int v2) {
      return Function2.apply$mcFJI$sp$(this, v1, v2);
   }

   public int apply$mcIJI$sp(final long v1, final int v2) {
      return Function2.apply$mcIJI$sp$(this, v1, v2);
   }

   public long apply$mcJJI$sp(final long v1, final int v2) {
      return Function2.apply$mcJJI$sp$(this, v1, v2);
   }

   public void apply$mcVJI$sp(final long v1, final int v2) {
      Function2.apply$mcVJI$sp$(this, v1, v2);
   }

   public boolean apply$mcZJJ$sp(final long v1, final long v2) {
      return Function2.apply$mcZJJ$sp$(this, v1, v2);
   }

   public double apply$mcDJJ$sp(final long v1, final long v2) {
      return Function2.apply$mcDJJ$sp$(this, v1, v2);
   }

   public float apply$mcFJJ$sp(final long v1, final long v2) {
      return Function2.apply$mcFJJ$sp$(this, v1, v2);
   }

   public int apply$mcIJJ$sp(final long v1, final long v2) {
      return Function2.apply$mcIJJ$sp$(this, v1, v2);
   }

   public long apply$mcJJJ$sp(final long v1, final long v2) {
      return Function2.apply$mcJJJ$sp$(this, v1, v2);
   }

   public void apply$mcVJJ$sp(final long v1, final long v2) {
      Function2.apply$mcVJJ$sp$(this, v1, v2);
   }

   public Function1 curried() {
      return Function2.curried$(this);
   }

   public Function1 tupled() {
      return Function2.tupled$(this);
   }

   public String toString() {
      return Function2.toString$(this);
   }

   public int numSamples() {
      return this.numSamples;
   }

   public Function1 errorMeasure() {
      return this.errorMeasure;
   }

   public double diff(final Seq l1, final Seq l2) {
      return .MODULE$.abs(BoxesRunTime.unboxToDouble(this.errorMeasure().apply(l1)) - BoxesRunTime.unboxToDouble(this.errorMeasure().apply(l2)));
   }

   public double apply(final Seq labeling1, final Seq labeling2) {
      scala.Predef..MODULE$.assume(labeling1.length() == labeling2.length());
      Stream lpairs = labeling1.iterator().zip(labeling2.iterator()).filter((a) -> BoxesRunTime.boxToBoolean($anonfun$apply$1(a))).toStream();
      double baseDiff = this.diff(lpairs.map((x$1) -> x$1._1()), lpairs.map((x$2) -> x$2._2()));
      IntRef numBetter = IntRef.create(0);
      scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(1), this.numSamples()).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
         ArrayBuffer l1 = new ArrayBuffer();
         ArrayBuffer l2 = new ArrayBuffer();
         lpairs.withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$apply$5(check$ifrefutable$1))).foreach((x$3) -> {
            if (x$3 != null) {
               Object a = x$3._1();
               Object b = x$3._2();
               ArrayBuffer var10000;
               if (Rand$.MODULE$.uniform().draw$mcD$sp() < (double)0.5F) {
                  l1.$plus$eq(a);
                  var10000 = (ArrayBuffer)l2.$plus$eq(b);
               } else {
                  l1.$plus$eq(b);
                  var10000 = (ArrayBuffer)l2.$plus$eq(a);
               }

               ArrayBuffer var3 = var10000;
               return var3;
            } else {
               throw new MatchError(x$3);
            }
         });
         if (baseDiff < this.diff(l1.toSeq(), l2.toSeq())) {
            ++numBetter.elem;
         }

      });
      return ((double)numBetter.elem + (double)1.0F) / ((double)this.numSamples() + (double)1.0F);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$1(final Tuple2 a) {
      return !BoxesRunTime.equals(a._1(), a._2());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$5(final Tuple2 check$ifrefutable$1) {
      boolean var1;
      if (check$ifrefutable$1 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public RandomizationTest(final int numSamples, final Function1 errorMeasure) {
      this.numSamples = numSamples;
      this.errorMeasure = errorMeasure;
      Function2.$init$(this);
   }

   public RandomizationTest(final Function1 errorMeasure) {
      this(5000, errorMeasure);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
