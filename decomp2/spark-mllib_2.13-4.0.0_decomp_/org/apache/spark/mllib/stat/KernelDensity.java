package org.apache.spark.mllib.stat;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.rdd.RDD;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005b\u0001B\t\u0013\u0001uAQ\u0001\r\u0001\u0005\u0002EBq\u0001\u000e\u0001A\u0002\u0013%Q\u0007C\u0004:\u0001\u0001\u0007I\u0011\u0002\u001e\t\r\u0001\u0003\u0001\u0015)\u00037\u0011%\t\u0005\u00011AA\u0002\u0013%!\tC\u0005J\u0001\u0001\u0007\t\u0019!C\u0005\u0015\"IA\n\u0001a\u0001\u0002\u0003\u0006Ka\u0011\u0005\u0006\u001b\u0002!\tA\u0014\u0005\u00065\u0002!\ta\u0017\u0005\u00065\u0002!\tA\u0018\u0005\u0006_\u0002!\t\u0001]\u0004\u0006qJAI!\u001f\u0004\u0006#IAIA\u001f\u0005\u0007a5!\t!!\u0001\t\u000f\u0005\rQ\u0002\"\u0001\u0002\u0006!I\u0011qC\u0007\u0002\u0002\u0013%\u0011\u0011\u0004\u0002\u000e\u0017\u0016\u0014h.\u001a7EK:\u001c\u0018\u000e^=\u000b\u0005M!\u0012\u0001B:uCRT!!\u0006\f\u0002\u000b5dG.\u001b2\u000b\u0005]A\u0012!B:qCJ\\'BA\r\u001b\u0003\u0019\t\u0007/Y2iK*\t1$A\u0002pe\u001e\u001c\u0001aE\u0002\u0001=\u0011\u0002\"a\b\u0012\u000e\u0003\u0001R\u0011!I\u0001\u0006g\u000e\fG.Y\u0005\u0003G\u0001\u0012a!\u00118z%\u00164\u0007CA\u0013.\u001d\t13F\u0004\u0002(U5\t\u0001F\u0003\u0002*9\u00051AH]8pizJ\u0011!I\u0005\u0003Y\u0001\nq\u0001]1dW\u0006<W-\u0003\u0002/_\ta1+\u001a:jC2L'0\u00192mK*\u0011A\u0006I\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003I\u0002\"a\r\u0001\u000e\u0003I\t\u0011BY1oI^LG\r\u001e5\u0016\u0003Y\u0002\"aH\u001c\n\u0005a\u0002#A\u0002#pk\ndW-A\u0007cC:$w/\u001b3uQ~#S-\u001d\u000b\u0003wy\u0002\"a\b\u001f\n\u0005u\u0002#\u0001B+oSRDqaP\u0002\u0002\u0002\u0003\u0007a'A\u0002yIE\n!BY1oI^LG\r\u001e5!\u0003\u0019\u0019\u0018-\u001c9mKV\t1\tE\u0002E\u000fZj\u0011!\u0012\u0006\u0003\rZ\t1A\u001d3e\u0013\tAUIA\u0002S\t\u0012\u000b!b]1na2,w\fJ3r)\tY4\nC\u0004@\r\u0005\u0005\t\u0019A\"\u0002\u000fM\fW\u000e\u001d7fA\u0005a1/\u001a;CC:$w/\u001b3uQR\u0011q\nU\u0007\u0002\u0001!)A\u0007\u0003a\u0001m!\u001a\u0001B\u0015-\u0011\u0005M3V\"\u0001+\u000b\u0005U3\u0012AC1o]>$\u0018\r^5p]&\u0011q\u000b\u0016\u0002\u0006'&t7-Z\u0011\u00023\u0006)\u0011G\f\u001b/a\u0005I1/\u001a;TC6\u0004H.\u001a\u000b\u0003\u001frCQ!Q\u0005A\u0002\rC3!\u0003*Y)\tyu\fC\u0003B\u0015\u0001\u0007\u0001\rE\u0002bM\"l\u0011A\u0019\u0006\u0003G\u0012\fAA[1wC*\u0011QMF\u0001\u0004CBL\u0017BA4c\u0005\u001dQ\u0015M^1S\t\u0012\u0003\"![7\u000e\u0003)T!a\u001b7\u0002\t1\fgn\u001a\u0006\u0002G&\u0011\u0001H\u001b\u0015\u0004\u0015IC\u0016\u0001C3ti&l\u0017\r^3\u0015\u0005E$\bcA\u0010sm%\u00111\u000f\t\u0002\u0006\u0003J\u0014\u0018-\u001f\u0005\u0006k.\u0001\r!]\u0001\u0007a>Lg\u000e^:)\u0007-\u0011\u0006\fK\u0002\u0001%b\u000bQbS3s]\u0016dG)\u001a8tSRL\bCA\u001a\u000e'\riad\u001f\t\u0003y~l\u0011! \u0006\u0003}2\f!![8\n\u00059jH#A=\u0002\u000f9|'/\u001c)eMRIa'a\u0002\u0002\f\u0005=\u00111\u0003\u0005\u0007\u0003\u0013y\u0001\u0019\u0001\u001c\u0002\t5,\u0017M\u001c\u0005\u0007\u0003\u001by\u0001\u0019\u0001\u001c\u0002#M$\u0018M\u001c3be\u0012$UM^5bi&|g\u000e\u0003\u0004\u0002\u0012=\u0001\rAN\u0001#Y><7\u000b^1oI\u0006\u0014H\rR3wS\u0006$\u0018n\u001c8QYV\u001c\b*\u00197g\u0019><'\u0007U5\t\r\u0005Uq\u00021\u00017\u0003\u0005A\u0018\u0001D<sSR,'+\u001a9mC\u000e,GCAA\u000e!\rI\u0017QD\u0005\u0004\u0003?Q'AB(cU\u0016\u001cG\u000f"
)
public class KernelDensity implements Serializable {
   private double bandwidth = (double)1.0F;
   private RDD sample;

   public static double normPdf(final double mean, final double standardDeviation, final double logStandardDeviationPlusHalfLog2Pi, final double x) {
      return KernelDensity$.MODULE$.normPdf(mean, standardDeviation, logStandardDeviationPlusHalfLog2Pi, x);
   }

   private double bandwidth() {
      return this.bandwidth;
   }

   private void bandwidth_$eq(final double x$1) {
      this.bandwidth = x$1;
   }

   private RDD sample() {
      return this.sample;
   }

   private void sample_$eq(final RDD x$1) {
      this.sample = x$1;
   }

   public KernelDensity setBandwidth(final double bandwidth) {
      .MODULE$.require(bandwidth > (double)0, () -> "Bandwidth must be positive, but got " + bandwidth + ".");
      this.bandwidth_$eq(bandwidth);
      return this;
   }

   public KernelDensity setSample(final RDD sample) {
      this.sample_$eq(sample);
      return this;
   }

   public KernelDensity setSample(final JavaRDD sample) {
      this.sample_$eq(sample.rdd());
      return this;
   }

   public double[] estimate(final double[] points) {
      RDD sample = this.sample();
      double bandwidth = this.bandwidth();
      .MODULE$.require(sample != null, () -> "Must set sample before calling estimate.");
      int n = points.length;
      double logStandardDeviationPlusHalfLog2Pi = scala.math.package..MODULE$.log(bandwidth) + (double)0.5F * scala.math.package..MODULE$.log((Math.PI * 2D));
      Tuple2 var10 = (Tuple2)sample.aggregate(new Tuple2(new double[n], BoxesRunTime.boxToLong(0L)), (x, y) -> $anonfun$estimate$2(n, bandwidth, logStandardDeviationPlusHalfLog2Pi, points, x, BoxesRunTime.unboxToDouble(y)), (x, y) -> {
         org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().daxpy(n, (double)1.0F, (double[])y._1(), 1, (double[])x._1(), 1);
         return new Tuple2(x._1(), BoxesRunTime.boxToLong(x._2$mcJ$sp() + y._2$mcJ$sp()));
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      if (var10 != null) {
         double[] densities = (double[])var10._1();
         long count = var10._2$mcJ$sp();
         Tuple2 var9 = new Tuple2(densities, BoxesRunTime.boxToLong(count));
         double[] densities = (double[])var9._1();
         long count = var9._2$mcJ$sp();
         org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().dscal(n, (double)1.0F / (double)count, densities, 1);
         return densities;
      } else {
         throw new MatchError(var10);
      }
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$estimate$2(final int n$1, final double bandwidth$2, final double logStandardDeviationPlusHalfLog2Pi$1, final double[] points$1, final Tuple2 x, final double y) {
      for(int i = 0; i < n$1; ++i) {
         ((double[])x._1())[i] += KernelDensity$.MODULE$.normPdf(y, bandwidth$2, logStandardDeviationPlusHalfLog2Pi$1, points$1[i]);
      }

      return new Tuple2(x._1(), BoxesRunTime.boxToLong(x._2$mcJ$sp() + 1L));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
