package breeze.linalg.operators;

import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.Vector;
import breeze.linalg.support.CanZipMapKeyValues$mcIII$sp;
import java.lang.invoke.SerializedLambda;
import scala.Function3;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public class Vector_TraversalOps$CanZipMapKeyValuesVector$mcII$sp extends Vector_TraversalOps.CanZipMapKeyValuesVector implements CanZipMapKeyValues$mcIII$sp {
   private final ClassTag evidence$4;

   public DenseVector create(final int length) {
      return this.create$mcI$sp(length);
   }

   public DenseVector create$mcI$sp(final int length) {
      return DenseVector$.MODULE$.apply$mIc$sp((int[])this.breeze$linalg$operators$Vector_TraversalOps$CanZipMapKeyValuesVector$$evidence$4.newArray(length));
   }

   public Vector map(final Vector from, final Vector from2, final Function3 fn) {
      return this.map$mcII$sp(from, from2, fn);
   }

   public Vector map$mcII$sp(final Vector from, final Vector from2, final Function3 fn) {
      .MODULE$.require(from.length() == from2.length(), () -> "Vector lengths must match!");
      DenseVector result = this.create$mcI$sp(from.length());

      for(int i = 0; i < from.length(); ++i) {
         result.data$mcI$sp()[i] = BoxesRunTime.unboxToInt(fn.apply(BoxesRunTime.boxToInteger(i), BoxesRunTime.boxToInteger(from.apply$mcII$sp(i)), BoxesRunTime.boxToInteger(from2.apply$mcII$sp(i))));
      }

      return result;
   }

   public Vector mapActive(final Vector from, final Vector from2, final Function3 fn) {
      return this.mapActive$mcII$sp(from, from2, fn);
   }

   public Vector mapActive$mcII$sp(final Vector from, final Vector from2, final Function3 fn) {
      return this.map$mcII$sp(from, from2, fn);
   }

   // $FF: synthetic method
   public Vector_TraversalOps breeze$linalg$operators$Vector_TraversalOps$CanZipMapKeyValuesVector$mcII$sp$$$outer() {
      return this.$outer;
   }

   public Vector_TraversalOps$CanZipMapKeyValuesVector$mcII$sp(final Vector_TraversalOps $outer, final ClassTag evidence$4) {
      super(evidence$4);
      this.evidence$4 = evidence$4;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
