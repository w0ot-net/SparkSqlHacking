package breeze.signal.support;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.operators.HasOps$;
import java.lang.invoke.SerializedLambda;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.ArrayOps.;
import scala.collection.immutable.List;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

public final class CanIHaarTr$ {
   public static final CanIHaarTr$ MODULE$ = new CanIHaarTr$();
   private static final double breeze$signal$support$CanIHaarTr$$nFactor = (double)1.0F / Math.sqrt((double)2.0F);
   private static final CanIHaarTr dvDouble1IFHT = new CanIHaarTr() {
      public DenseVector apply(final DenseVector v) {
         return _ifht$1(v.copy$mcD$sp());
      }

      private static final DenseVector _ifht$1(final DenseVector v) {
         DenseVector var10000;
         if (v.length() > 1) {
            int hs = v.length() / 2;
            v.slice$mcD$sp(0, hs, v.slice$default$3()).$colon$eq(_ifht$1(v.slice$mcD$sp(0, hs, v.slice$default$3())), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
            Tuple2[] x = .MODULE$.zip$extension(scala.Predef..MODULE$.doubleArrayOps(v.slice$mcD$sp(0, hs, v.slice$default$3()).toArray$mcD$sp(scala.reflect.ClassTag..MODULE$.Double())), scala.Predef..MODULE$.wrapDoubleArray(v.slice$mcD$sp(hs, v.length(), v.slice$default$3()).toArray$mcD$sp(scala.reflect.ClassTag..MODULE$.Double())));
            var10000 = DenseVector$.MODULE$.apply$mDc$sp((double[]).MODULE$.toArray$extension(scala.Predef..MODULE$.doubleArrayOps((double[]).MODULE$.flatten$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])x), (e) -> (List)scala.package..MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapDoubleArray(new double[]{(e._1$mcD$sp() + e._2$mcD$sp()) * CanIHaarTr$.MODULE$.breeze$signal$support$CanIHaarTr$$nFactor(), (e._1$mcD$sp() - e._2$mcD$sp()) * CanIHaarTr$.MODULE$.breeze$signal$support$CanIHaarTr$$nFactor()})), scala.reflect.ClassTag..MODULE$.apply(List.class))), scala.Predef..MODULE$.$conforms(), scala.reflect.ClassTag..MODULE$.Double())), scala.reflect.ClassTag..MODULE$.Double()));
         } else {
            var10000 = v;
         }

         return var10000;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   };
   private static final CanIHaarTr dmDouble2IFHT = new CanIHaarTr() {
      public DenseMatrix apply(final DenseMatrix m) {
         DenseMatrix r = m.copy$mcD$sp();
         _ifht$2(r, Math.max(r.cols(), r.rows()));
         return r;
      }

      private static final void _ifht$2(final DenseMatrix m, final int limit) {
         if (limit > 1) {
            int hs = limit / 2;
            _ifht$2(m, hs);
            scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), limit).foreach$mVc$sp((JFunction1.mcVI.sp)(r) -> {
               double[] rv = ((DenseVector)m.t(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), limit), BoxesRunTime.boxToInteger(r), HasOps$.MODULE$.canTranspose_DM(), HasOps$.MODULE$.canSlicePartOfCol())).toArray$mcD$sp(scala.reflect.ClassTag..MODULE$.Double());
               List x = scala.Predef..MODULE$.wrapRefArray((Object[]).MODULE$.zip$extension(scala.Predef..MODULE$.doubleArrayOps((double[]).MODULE$.slice$extension(scala.Predef..MODULE$.doubleArrayOps(rv), 0, hs)), scala.Predef..MODULE$.wrapDoubleArray((double[]).MODULE$.slice$extension(scala.Predef..MODULE$.doubleArrayOps(rv), hs, limit)))).toList();
               double[] v = (double[])((IterableOnceOps)x.map((e) -> (List)scala.package..MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapDoubleArray(new double[]{(e._1$mcD$sp() + e._2$mcD$sp()) * CanIHaarTr$.MODULE$.breeze$signal$support$CanIHaarTr$$nFactor(), (e._1$mcD$sp() - e._2$mcD$sp()) * CanIHaarTr$.MODULE$.breeze$signal$support$CanIHaarTr$$nFactor()}))).flatten(scala.Predef..MODULE$.$conforms())).toArray(scala.reflect.ClassTag..MODULE$.Double());
               scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), limit).foreach$mVc$sp((JFunction1.mcVI.sp)(c) -> m.update$mcD$sp(r, c, v[c]));
            });
            scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), limit).foreach$mVc$sp((JFunction1.mcVI.sp)(c) -> {
               double[] cv = ((DenseVector)m.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), limit), BoxesRunTime.boxToInteger(c), HasOps$.MODULE$.canSlicePartOfCol())).toArray$mcD$sp(scala.reflect.ClassTag..MODULE$.Double());
               List x = scala.Predef..MODULE$.wrapRefArray((Object[]).MODULE$.zip$extension(scala.Predef..MODULE$.doubleArrayOps((double[]).MODULE$.slice$extension(scala.Predef..MODULE$.doubleArrayOps(cv), 0, hs)), scala.Predef..MODULE$.wrapDoubleArray((double[]).MODULE$.slice$extension(scala.Predef..MODULE$.doubleArrayOps(cv), hs, limit)))).toList();
               double[] v = (double[])((IterableOnceOps)x.map((e) -> (List)scala.package..MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapDoubleArray(new double[]{(e._1$mcD$sp() + e._2$mcD$sp()) * CanIHaarTr$.MODULE$.breeze$signal$support$CanIHaarTr$$nFactor(), (e._1$mcD$sp() - e._2$mcD$sp()) * CanIHaarTr$.MODULE$.breeze$signal$support$CanIHaarTr$$nFactor()}))).flatten(scala.Predef..MODULE$.$conforms())).toArray(scala.reflect.ClassTag..MODULE$.Double());
               scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), limit).foreach$mVc$sp((JFunction1.mcVI.sp)(r) -> m.update$mcD$sp(r, c, v[r]));
            });
         }

      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   };

   public double breeze$signal$support$CanIHaarTr$$nFactor() {
      return breeze$signal$support$CanIHaarTr$$nFactor;
   }

   public CanIHaarTr dvDouble1IFHT() {
      return dvDouble1IFHT;
   }

   public CanIHaarTr dmDouble2IFHT() {
      return dmDouble2IFHT;
   }

   private CanIHaarTr$() {
   }
}
