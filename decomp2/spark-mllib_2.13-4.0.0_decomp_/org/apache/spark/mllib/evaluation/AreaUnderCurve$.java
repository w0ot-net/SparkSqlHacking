package org.apache.spark.mllib.evaluation;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.rdd.RDD;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.DoubleRef;
import scala.runtime.ObjectRef;

public final class AreaUnderCurve$ {
   public static final AreaUnderCurve$ MODULE$ = new AreaUnderCurve$();

   private double trapezoid(final Seq points) {
      .MODULE$.require(points.length() == 2);
      Tuple2 x = (Tuple2)points.head();
      Tuple2 y = (Tuple2)points.last();
      return (y._1$mcD$sp() - x._1$mcD$sp()) * (y._2$mcD$sp() + x._2$mcD$sp()) / (double)2.0F;
   }

   public double of(final RDD curve) {
      Tuple2[] localAreas = (Tuple2[])curve.mapPartitions((iter) -> {
         if (iter.nonEmpty()) {
            DoubleRef localArea = DoubleRef.create((double)0.0F);
            BooleanRef head = BooleanRef.create(true);
            ObjectRef firstPoint = ObjectRef.create(new Tuple2.mcDD.sp(Double.NaN, Double.NaN));
            ObjectRef lastPoint = ObjectRef.create(new Tuple2.mcDD.sp(Double.NaN, Double.NaN));
            iter.sliding(2, iter.sliding$default$2()).foreach((points) -> {
               $anonfun$of$2(head, firstPoint, lastPoint, localArea, points);
               return BoxedUnit.UNIT;
            });
            return scala.package..MODULE$.Iterator().single(new Tuple2(BoxesRunTime.boxToDouble(localArea.elem), new Tuple2((Tuple2)firstPoint.elem, (Tuple2)lastPoint.elem)));
         } else {
            return scala.package..MODULE$.Iterator().empty();
         }
      }, curve.mapPartitions$default$2(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)).collect();
      double var10000 = BoxesRunTime.unboxToDouble(.MODULE$.wrapDoubleArray((double[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])localAreas), (x$1x) -> BoxesRunTime.boxToDouble($anonfun$of$3(x$1x)), scala.reflect.ClassTag..MODULE$.Double())).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
      Iterator qual$1 = scala.collection.ArrayOps..MODULE$.iterator$extension(.MODULE$.refArrayOps((Object[])localAreas)).map((x$2x) -> (Tuple2)x$2x._2());
      int x$1 = 2;
      int x$2 = qual$1.sliding$default$2();
      return var10000 + BoxesRunTime.unboxToDouble(qual$1.sliding(2, x$2).withPartial(false).map((x0$1) -> BoxesRunTime.boxToDouble($anonfun$of$5(x0$1))).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
   }

   public double of(final Iterable curve) {
      Iterator qual$1 = curve.iterator();
      int x$1 = 2;
      int x$2 = qual$1.sliding$default$2();
      return BoxesRunTime.unboxToDouble(qual$1.sliding(2, x$2).withPartial(false).foldLeft(BoxesRunTime.boxToDouble((double)0.0F), (auc, points) -> BoxesRunTime.boxToDouble($anonfun$of$6(BoxesRunTime.unboxToDouble(auc), points))));
   }

   // $FF: synthetic method
   public static final void $anonfun$of$2(final BooleanRef head$1, final ObjectRef firstPoint$1, final ObjectRef lastPoint$1, final DoubleRef localArea$1, final Seq points) {
      if (head$1.elem) {
         firstPoint$1.elem = (Tuple2)points.head();
         head$1.elem = false;
      }

      lastPoint$1.elem = (Tuple2)points.last();
      if (points.length() == 2) {
         localArea$1.elem += MODULE$.trapezoid(points);
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$of$3(final Tuple2 x$1) {
      return x$1._1$mcD$sp();
   }

   // $FF: synthetic method
   public static final double $anonfun$of$5(final Seq x0$1) {
      if (x0$1 != null) {
         SeqOps var4 = scala.package..MODULE$.Seq().unapplySeq(x0$1);
         if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var4) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var4)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var4), 2) == 0) {
            Tuple2 var5 = (Tuple2)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var4), 0);
            Tuple2 var6 = (Tuple2)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var4), 1);
            if (var5 != null) {
               Tuple2 last1 = (Tuple2)var5._2();
               if (var6 != null) {
                  Tuple2 first2 = (Tuple2)var6._1();
                  return MODULE$.trapezoid(new scala.collection.immutable..colon.colon(last1, new scala.collection.immutable..colon.colon(first2, scala.collection.immutable.Nil..MODULE$)));
               }
            }
         }
      }

      throw new MatchError(x0$1);
   }

   // $FF: synthetic method
   public static final double $anonfun$of$6(final double auc, final Seq points) {
      return auc + MODULE$.trapezoid(points);
   }

   private AreaUnderCurve$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
