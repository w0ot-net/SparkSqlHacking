package org.apache.spark.mllib.clustering;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.mllib.linalg.BLAS$;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.rdd.RDD;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Predef;
import scala.Tuple2;
import scala.Tuple3;
import scala.Array.;
import scala.collection.ArrayOps;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015hA\u0002\u000f\u001e\u0003\u0003\ts\u0005C\u0003<\u0001\u0011\u0005A\bC\u0003@\u0001\u0019\u0005\u0001\tC\u0003@\u0001\u0011\u0005a\tC\u0003Q\u0001\u0011\u0005\u0011\u000bC\u0003a\u0001\u0011\u0005\u0011\rC\u0003a\u0001\u0019\u0005\u0001\u000fC\u0003a\u0001\u0011\u0005A\u000fC\u0003x\u0001\u0011\u0005\u0001\u0010C\u0003|\u0001\u0011\u0005A\u0010\u0003\u0004F\u0001\u0019\u0005\u0011Q\u0002\u0005\b\u0003/\u0001a\u0011AA\r\u0011\u001d\tY\u0003\u0001C\u0001\u0003[Aq!!\b\u0001\t\u0003\t9\u0005C\u0004\u0002N\u0001!\t!a\u0014\t\u000f\u0005u\u0003\u0001\"\u0001\u0002`\u001d9\u0011QM\u000f\t\u0002\u0005\u001ddA\u0002\u000f\u001e\u0011\u0003\tI\u0007\u0003\u0004<#\u0011\u0005\u0011\u0011\u0010\u0005\n\u0003w\n\"\u0019!C\u0001\u0003{B\u0001\"!(\u0012A\u0003%\u0011q\u0010\u0005\n\u0003C\u000b\"\u0019!C\u0001\u0003{B\u0001\"!*\u0012A\u0003%\u0011q\u0010\u0005\t\u0003S\u000bB\u0011A\u0011\u0002,\"A\u0011qX\t\u0005\u0002\u0005\n\t\r\u0003\u0005\u0002FF!\t!HAd\u0011!\ti-\u0005C\u0001;\u0005=\u0007\"CAl#\u0005\u0005I\u0011BAm\u0005=!\u0015n\u001d;b]\u000e,W*Z1tkJ,'B\u0001\u0010 \u0003)\u0019G.^:uKJLgn\u001a\u0006\u0003A\u0005\nQ!\u001c7mS\nT!AI\u0012\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u0011*\u0013AB1qC\u000eDWMC\u0001'\u0003\ry'oZ\n\u0004\u0001!r\u0003CA\u0015-\u001b\u0005Q#\"A\u0016\u0002\u000bM\u001c\u0017\r\\1\n\u00055R#AB!osJ+g\r\u0005\u00020q9\u0011\u0001G\u000e\b\u0003cUj\u0011A\r\u0006\u0003gQ\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002W%\u0011qGK\u0001\ba\u0006\u001c7.Y4f\u0013\tI$H\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00028U\u00051A(\u001b8jiz\"\u0012!\u0010\t\u0003}\u0001i\u0011!H\u0001\u0012G>l\u0007/\u001e;f'R\fG/[:uS\u000e\u001cHCA!E!\tI#)\u0003\u0002DU\t1Ai\\;cY\u0016DQ!\u0012\u0002A\u0002\u0005\u000b\u0001\u0002Z5ti\u0006t7-\u001a\u000b\u0003\u000f*\u00032!\u000b%B\u0013\tI%FA\u0003BeJ\f\u0017\u0010C\u0003L\u0007\u0001\u0007A*A\u0004dK:$XM]:\u0011\u0007%BU\n\u0005\u0002?\u001d&\u0011q*\b\u0002\u000f-\u0016\u001cGo\u001c:XSRDgj\u001c:n\u0003y\u0019w.\u001c9vi\u0016\u001cF/\u0019;jgRL7m\u001d#jgR\u0014\u0018NY;uK\u0012d\u0017\u0010F\u0002H%bCQa\u0015\u0003A\u0002Q\u000b!a]2\u0011\u0005U3V\"A\u0011\n\u0005]\u000b#\u0001D*qCJ\\7i\u001c8uKb$\b\"B-\u0005\u0001\u0004Q\u0016!\u00032d\u0007\u0016tG/\u001a:t!\rYf\fT\u0007\u00029*\u0011Q,I\u0001\nEJ|\u0017\rZ2bgRL!a\u0018/\u0003\u0013\t\u0013x.\u00193dCN$\u0018a\u00034j]\u0012\u001cEn\\:fgR$BA\u00195j]B!\u0011fY3B\u0013\t!'F\u0001\u0004UkBdWM\r\t\u0003S\u0019L!a\u001a\u0016\u0003\u0007%sG\u000fC\u0003L\u000b\u0001\u0007A\nC\u0003k\u000b\u0001\u00071.\u0001\u0006ti\u0006$\u0018n\u001d;jGN\u00042!\u000b7H\u0013\ti'F\u0001\u0004PaRLwN\u001c\u0005\u0006_\u0016\u0001\r!T\u0001\u0006a>Lg\u000e\u001e\u000b\u0005EF\u00148\u000fC\u0003L\r\u0001\u0007A\nC\u0003k\r\u0001\u0007q\tC\u0003p\r\u0001\u0007Q\nF\u0002ckZDQaS\u0004A\u00021CQa\\\u0004A\u00025\u000b\u0011\u0002]8j]R\u001cun\u001d;\u0015\u0007\u0005K(\u0010C\u0003L\u0011\u0001\u0007A\nC\u0003p\u0011\u0001\u0007Q*A\tjg\u000e+g\u000e^3s\u0007>tg/\u001a:hK\u0012$r!`A\u0001\u0003\u000b\tI\u0001\u0005\u0002*}&\u0011qP\u000b\u0002\b\u0005>|G.Z1o\u0011\u0019\t\u0019!\u0003a\u0001\u001b\u0006Iq\u000e\u001c3DK:$XM\u001d\u0005\u0007\u0003\u000fI\u0001\u0019A'\u0002\u00139,woQ3oi\u0016\u0014\bBBA\u0006\u0013\u0001\u0007\u0011)A\u0004faNLGn\u001c8\u0015\u000b\u0005\u000by!a\u0005\t\r\u0005E!\u00021\u0001N\u0003\t1\u0018\u0007\u0003\u0004\u0002\u0016)\u0001\r!T\u0001\u0003mJ\n1b\u00197vgR,'oQ8tiRI\u0011)a\u0007\u0002 \u0005\r\u0012q\u0005\u0005\u0007\u0003;Y\u0001\u0019A'\u0002\u0011\r,g\u000e\u001e:pS\u0012Da!!\t\f\u0001\u0004i\u0015!\u00039pS:$8oU;n\u0011\u0019\t)c\u0003a\u0001\u0003\u0006Iq/Z5hQR\u001cV/\u001c\u0005\u0007\u0003SY\u0001\u0019A!\u0002#A|\u0017N\u001c;t'F,\u0018M]3e\u001d>\u0014X.\u0001\tva\u0012\fG/Z\"mkN$XM]*v[R1\u0011qFA\u001b\u0003o\u00012!KA\u0019\u0013\r\t\u0019D\u000b\u0002\u0005+:LG\u000fC\u0003p\u0019\u0001\u0007Q\nC\u0004\u0002:1\u0001\r!a\u000f\u0002\u0007M,X\u000e\u0005\u0003\u0002>\u0005\rSBAA \u0015\r\t\teH\u0001\u0007Y&t\u0017\r\\4\n\t\u0005\u0015\u0013q\b\u0002\u0007-\u0016\u001cGo\u001c:\u0015\u000b5\u000bI%a\u0013\t\u000f\u0005eR\u00021\u0001\u0002<!1\u0011QE\u0007A\u0002\u0005\u000b!c]=n[\u0016$(/[2DK:$(o\\5egRA\u0011\u0011KA*\u0003/\nY\u0006\u0005\u0003*G6k\u0005BBA+\u001d\u0001\u0007\u0011)A\u0003mKZ,G\u000eC\u0004\u0002Z9\u0001\r!a\u000f\u0002\u000b9|\u0017n]3\t\u000f\u0005ua\u00021\u0001\u0002<\u0005!1m\\:u)\u0015\t\u0015\u0011MA2\u0011\u0015yw\u00021\u0001N\u0011\u0019\tib\u0004a\u0001\u001b\u0006yA)[:uC:\u001cW-T3bgV\u0014X\r\u0005\u0002?#M!\u0011\u0003KA6!\u0011\ti'a\u001e\u000e\u0005\u0005=$\u0002BA9\u0003g\n!![8\u000b\u0005\u0005U\u0014\u0001\u00026bm\u0006L1!OA8)\t\t9'A\u0005F+\u000ec\u0015\nR#B\u001dV\u0011\u0011q\u0010\t\u0005\u0003\u0003\u000b9)\u0004\u0002\u0002\u0004*!\u0011QQA:\u0003\u0011a\u0017M\\4\n\t\u0005%\u00151\u0011\u0002\u0007'R\u0014\u0018N\\4)\u000bM\ti)!'\u0011\t\u0005=\u0015QS\u0007\u0003\u0003#S1!a%\"\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003/\u000b\tJA\u0003TS:\u001cW-\t\u0002\u0002\u001c\u0006)!G\f\u001b/a\u0005QQ)V\"M\u0013\u0012+\u0015I\u0014\u0011)\u000bQ\ti)!'\u0002\r\r{5+\u0013(FQ\u0015)\u0012QRAM\u0003\u001d\u0019ujU%O\u000b\u0002BSAFAG\u00033\u000b\u0001\u0003Z3d_\u0012,gI]8n'R\u0014\u0018N\\4\u0015\u0007u\ni\u000bC\u0004\u00020^\u0001\r!!-\u0002\u001f\u0011L7\u000f^1oG\u0016lU-Y:ve\u0016\u0004B!a-\u0002<:!\u0011QWA\\!\t\t$&C\u0002\u0002:*\na\u0001\u0015:fI\u00164\u0017\u0002BAE\u0003{S1!!/+\u0003]1\u0018\r\\5eCR,G)[:uC:\u001cW-T3bgV\u0014X\rF\u0002~\u0003\u0007Dq!a,\u0019\u0001\u0004\t\t,A\ftQ>,H\u000eZ\"p[B,H/Z*uCRL7\u000f^5dgR\u0019Q0!3\t\r\u0005-\u0017\u00041\u0001f\u0003\u0005Y\u0017AH:i_VdGmQ8naV$Xm\u0015;bi&\u001cH/[2t\u0019>\u001c\u0017\r\u001c7z)\u0015i\u0018\u0011[Aj\u0011\u0019\tYM\u0007a\u0001K\"1\u0011Q\u001b\u000eA\u0002\u0015\f1B\\;n\r\u0016\fG/\u001e:fg\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u001c\t\u0005\u0003\u0003\u000bi.\u0003\u0003\u0002`\u0006\r%AB(cU\u0016\u001cG\u000fK\u0003\u0012\u0003\u001b\u000bI\nK\u0003\u0011\u0003\u001b\u000bI\n"
)
public abstract class DistanceMeasure implements Serializable {
   public static String COSINE() {
      return DistanceMeasure$.MODULE$.COSINE();
   }

   public static String EUCLIDEAN() {
      return DistanceMeasure$.MODULE$.EUCLIDEAN();
   }

   public abstract double computeStatistics(final double distance);

   public double[] computeStatistics(final VectorWithNorm[] centers) {
      int k = centers.length;
      if (k == 1) {
         return new double[]{Double.NaN};
      } else {
         double[] packedValues = (double[]).MODULE$.ofDim(k * (k + 1) / 2, scala.reflect.ClassTag..MODULE$.Double());
         double[] diagValues = (double[]).MODULE$.fill(k, (JFunction0.mcD.sp)() -> Double.POSITIVE_INFINITY, scala.reflect.ClassTag..MODULE$.Double());

         for(int i = 0; i < k; ++i) {
            for(int j = i + 1; j < k; ++j) {
               double d = this.distance(centers[i], centers[j]);
               double s = this.computeStatistics(d);
               int index = org.apache.spark.ml.impl.Utils..MODULE$.indexUpperTriangular(k, i, j);
               packedValues[index] = s;
               if (s < diagValues[i]) {
                  diagValues[i] = s;
               }

               if (s < diagValues[j]) {
                  diagValues[j] = s;
               }
            }
         }

         for(int var13 = 0; var13 < k; ++var13) {
            int index = org.apache.spark.ml.impl.Utils..MODULE$.indexUpperTriangular(k, var13, var13);
            packedValues[index] = diagValues[var13];
         }

         return packedValues;
      }
   }

   public double[] computeStatisticsDistributedly(final SparkContext sc, final Broadcast bcCenters) {
      int k = ((VectorWithNorm[])bcCenters.value()).length;
      if (k == 1) {
         return new double[]{Double.NaN};
      } else {
         double[] packedValues = (double[]).MODULE$.ofDim(k * (k + 1) / 2, scala.reflect.ClassTag..MODULE$.Double());
         double[] diagValues = (double[]).MODULE$.fill(k, (JFunction0.mcD.sp)() -> Double.POSITIVE_INFINITY, scala.reflect.ClassTag..MODULE$.Double());
         int numParts = scala.math.package..MODULE$.min(k, 1024);
         ArrayOps var10000 = scala.collection.ArrayOps..MODULE$;
         Predef var10001 = scala.Predef..MODULE$;
         RDD qual$1 = sc.range(0L, (long)numParts, 1L, numParts);
         Function2 x$1 = (x0$1, x1$1) -> $anonfun$computeStatisticsDistributedly$2(this, bcCenters, k, numParts, BoxesRunTime.unboxToInt(x0$1), x1$1);
         boolean x$2 = qual$1.mapPartitionsWithIndex$default$2();
         var10000.foreach$extension(var10001.refArrayOps(qual$1.mapPartitionsWithIndex(x$1, x$2, scala.reflect.ClassTag..MODULE$.apply(Tuple3.class)).collect()), (x0$2) -> {
            $anonfun$computeStatisticsDistributedly$5(k, packedValues, diagValues, x0$2);
            return BoxedUnit.UNIT;
         });

         for(int i = 0; i < k; ++i) {
            int index = org.apache.spark.ml.impl.Utils..MODULE$.indexUpperTriangular(k, i, i);
            packedValues[index] = diagValues[i];
         }

         return packedValues;
      }
   }

   public Tuple2 findClosest(final VectorWithNorm[] centers, final Option statistics, final VectorWithNorm point) {
      return statistics.nonEmpty() ? this.findClosest(centers, (double[])statistics.get(), point) : this.findClosest(centers, point);
   }

   public abstract Tuple2 findClosest(final VectorWithNorm[] centers, final double[] statistics, final VectorWithNorm point);

   public Tuple2 findClosest(final VectorWithNorm[] centers, final VectorWithNorm point) {
      double bestDistance = Double.POSITIVE_INFINITY;
      int bestIndex = 0;

      for(int i = 0; i < centers.length; ++i) {
         VectorWithNorm center = centers[i];
         double currentDistance = this.distance(center, point);
         if (currentDistance < bestDistance) {
            bestDistance = currentDistance;
            bestIndex = i;
         }
      }

      return new Tuple2.mcID.sp(bestIndex, bestDistance);
   }

   public double pointCost(final VectorWithNorm[] centers, final VectorWithNorm point) {
      return this.findClosest(centers, point)._2$mcD$sp();
   }

   public boolean isCenterConverged(final VectorWithNorm oldCenter, final VectorWithNorm newCenter, final double epsilon) {
      return this.distance(oldCenter, newCenter) <= epsilon;
   }

   public abstract double distance(final VectorWithNorm v1, final VectorWithNorm v2);

   public abstract double clusterCost(final VectorWithNorm centroid, final VectorWithNorm pointsSum, final double weightSum, final double pointsSquaredNorm);

   public void updateClusterSum(final VectorWithNorm point, final Vector sum) {
      BLAS$.MODULE$.axpy(point.weight(), point.vector(), sum);
   }

   public VectorWithNorm centroid(final Vector sum, final double weightSum) {
      BLAS$.MODULE$.scal((double)1.0F / weightSum, sum);
      return new VectorWithNorm(sum);
   }

   public Tuple2 symmetricCentroids(final double level, final Vector noise, final Vector centroid) {
      Vector left = centroid.copy();
      BLAS$.MODULE$.axpy(-level, noise, left);
      Vector right = centroid.copy();
      BLAS$.MODULE$.axpy(level, noise, right);
      return new Tuple2(new VectorWithNorm(left), new VectorWithNorm(right));
   }

   public double cost(final VectorWithNorm point, final VectorWithNorm centroid) {
      return this.distance(point, centroid);
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$computeStatisticsDistributedly$4(final DistanceMeasure $this, final int i$1, final int numParts$1, final int pid$1, final VectorWithNorm[] centers$1, final int j) {
      int hash = scala.runtime.RichInt..MODULE$.abs$extension(scala.Predef..MODULE$.intWrapper((new Tuple2.mcII.sp(i$1, j)).hashCode()));
      if (hash % numParts$1 == pid$1) {
         double d = $this.distance(centers$1[i$1], centers$1[j]);
         double s = $this.computeStatistics(d);
         return scala.package..MODULE$.Iterator().single(new Tuple3(BoxesRunTime.boxToInteger(i$1), BoxesRunTime.boxToInteger(j), BoxesRunTime.boxToDouble(s)));
      } else {
         return scala.package..MODULE$.Iterator().empty();
      }
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$computeStatisticsDistributedly$3(final DistanceMeasure $this, final int k$1, final int numParts$1, final int pid$1, final VectorWithNorm[] centers$1, final int i) {
      return scala.package..MODULE$.Iterator().range(i + 1, k$1).flatMap((j) -> $anonfun$computeStatisticsDistributedly$4($this, i, numParts$1, pid$1, centers$1, BoxesRunTime.unboxToInt(j)));
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$computeStatisticsDistributedly$2(final DistanceMeasure $this, final Broadcast bcCenters$1, final int k$1, final int numParts$1, final int x0$1, final Iterator x1$1) {
      Tuple2 var7 = new Tuple2(BoxesRunTime.boxToInteger(x0$1), x1$1);
      if (var7 != null) {
         int pid = var7._1$mcI$sp();
         VectorWithNorm[] centers = (VectorWithNorm[])bcCenters$1.value();
         return scala.package..MODULE$.Iterator().range(0, k$1).flatMap((i) -> $anonfun$computeStatisticsDistributedly$3($this, k$1, numParts$1, pid, centers, BoxesRunTime.unboxToInt(i)));
      } else {
         throw new MatchError(var7);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$computeStatisticsDistributedly$5(final int k$1, final double[] packedValues$1, final double[] diagValues$1, final Tuple3 x0$2) {
      if (x0$2 != null) {
         int i = BoxesRunTime.unboxToInt(x0$2._1());
         int j = BoxesRunTime.unboxToInt(x0$2._2());
         double s = BoxesRunTime.unboxToDouble(x0$2._3());
         int index = org.apache.spark.ml.impl.Utils..MODULE$.indexUpperTriangular(k$1, i, j);
         packedValues$1[index] = s;
         if (s < diagValues$1[i]) {
            diagValues$1[i] = s;
         }

         if (s < diagValues$1[j]) {
            diagValues$1[j] = s;
            BoxedUnit var11 = BoxedUnit.UNIT;
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
