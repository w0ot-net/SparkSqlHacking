package breeze.linalg.operators;

import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.Vector;
import breeze.linalg.support.CanZipMapKeyValues$mcIDI$sp;
import java.lang.invoke.SerializedLambda;
import scala.Function3;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public class Vector_TraversalOps$CanZipMapKeyValuesVector$mcDI$sp extends Vector_TraversalOps.CanZipMapKeyValuesVector implements CanZipMapKeyValues$mcIDI$sp {
   private final ClassTag evidence$4;

   public DenseVector create(final int length) {
      return this.create$mcD$sp(length);
   }

   public DenseVector create$mcD$sp(final int length) {
      return DenseVector$.MODULE$.apply$mDc$sp((double[])this.breeze$linalg$operators$Vector_TraversalOps$CanZipMapKeyValuesVector$$evidence$4.newArray(length));
   }

   public Vector map(final Vector from, final Vector from2, final Function3 fn) {
      return this.map$mcDI$sp(from, from2, fn);
   }

   public Vector map$mcDI$sp(final Vector from, final Vector from2, final Function3 fn) {
      .MODULE$.require(from.length() == from2.length(), () -> "Vector lengths must match!");
      DenseVector result = this.create$mcD$sp(from.length());

      for(int i = 0; i < from.length(); ++i) {
         result.data$mcD$sp()[i] = BoxesRunTime.unboxToDouble(fn.apply(BoxesRunTime.boxToInteger(i), BoxesRunTime.boxToInteger(from.apply$mcII$sp(i)), BoxesRunTime.boxToInteger(from2.apply$mcII$sp(i))));
      }

      return result;
   }

   public Vector mapActive(final Vector from, final Vector from2, final Function3 fn) {
      return this.mapActive$mcDI$sp(from, from2, fn);
   }

   public Vector mapActive$mcDI$sp(final Vector from, final Vector from2, final Function3 fn) {
      return this.map$mcDI$sp(from, from2, fn);
   }

   // $FF: synthetic method
   public Vector_TraversalOps breeze$linalg$operators$Vector_TraversalOps$CanZipMapKeyValuesVector$mcDI$sp$$$outer() {
      return this.$outer;
   }

   public Vector_TraversalOps$CanZipMapKeyValuesVector$mcDI$sp(final Vector_TraversalOps $outer, final ClassTag evidence$4) {
      super(evidence$4);
      this.evidence$4 = evidence$4;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
