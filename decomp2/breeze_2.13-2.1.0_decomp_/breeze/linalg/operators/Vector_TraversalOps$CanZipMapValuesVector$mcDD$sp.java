package breeze.linalg.operators;

import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.Vector;
import breeze.linalg.support.CanZipMapValues$mcDD$sp;
import java.lang.invoke.SerializedLambda;
import scala.Function2;
import scala.Predef.;
import scala.reflect.ClassTag;

public class Vector_TraversalOps$CanZipMapValuesVector$mcDD$sp extends Vector_TraversalOps.CanZipMapValuesVector implements CanZipMapValues$mcDD$sp {
   private final ClassTag evidence$1;

   public DenseVector create(final int length) {
      return this.create$mcD$sp(length);
   }

   public DenseVector create$mcD$sp(final int length) {
      return DenseVector$.MODULE$.apply$mDc$sp((double[])this.breeze$linalg$operators$Vector_TraversalOps$CanZipMapValuesVector$$evidence$1.newArray(length));
   }

   public DenseVector map(final Vector from, final Vector from2, final Function2 fn) {
      return this.map$mcDD$sp(from, from2, fn);
   }

   public DenseVector map$mcDD$sp(final Vector from, final Vector from2, final Function2 fn) {
      .MODULE$.require(from.length() == from2.length(), () -> "Vector lengths must match!");
      DenseVector result = this.create$mcD$sp(from.length());
      int index$macro$2 = 0;

      for(int limit$macro$4 = from.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
         result.data$mcD$sp()[index$macro$2] = fn.apply$mcDDD$sp(from.apply$mcID$sp(index$macro$2), from2.apply$mcID$sp(index$macro$2));
      }

      return result;
   }

   // $FF: synthetic method
   public Vector_TraversalOps breeze$linalg$operators$Vector_TraversalOps$CanZipMapValuesVector$mcDD$sp$$$outer() {
      return this.$outer;
   }

   public Vector_TraversalOps$CanZipMapValuesVector$mcDD$sp(final Vector_TraversalOps $outer, final ClassTag evidence$1) {
      super(evidence$1);
      this.evidence$1 = evidence$1;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
