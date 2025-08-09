package breeze.linalg;

import breeze.linalg.support.CanCreateZerosLike;
import breeze.linalg.support.CanMapKeyValuePairs;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanTransformValues;
import breeze.linalg.support.CanTraverseKeyValuePairs;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.ScalarOf;
import breeze.linalg.support.ScalarOf$;
import breeze.storage.Zero;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Tuple2;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt.;
import scala.runtime.java8.JFunction1;

public final class SliceVector$ {
   public static final SliceVector$ MODULE$ = new SliceVector$();

   public ScalarOf scalarOf() {
      return ScalarOf$.MODULE$.dummy();
   }

   public CanMapKeyValuePairs canMapKeyValuePairs(final ClassTag evidence$2) {
      return new CanMapKeyValuePairs(evidence$2) {
         private final ClassTag evidence$2$1;

         public DenseVector map(final SliceVector from, final Function2 fn) {
            return DenseVector$.MODULE$.tabulate(from.length(), (i) -> $anonfun$map$1(fn, from, BoxesRunTime.unboxToInt(i)), this.evidence$2$1);
         }

         public DenseVector mapActive(final SliceVector from, final Function2 fn) {
            return this.map(from, fn);
         }

         // $FF: synthetic method
         public static final Object $anonfun$map$1(final Function2 fn$1, final SliceVector from$1, final int i) {
            return fn$1.apply(BoxesRunTime.boxToInteger(i), from$1.apply(i));
         }

         public {
            this.evidence$2$1 = evidence$2$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public CanMapValues canMapValues(final ClassTag evidence$3) {
      return new CanMapValues(evidence$3) {
         private final ClassTag evidence$3$1;

         public Object map$mcDD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDD$sp$(this, from, fn);
         }

         public Object map$mcDF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDF$sp$(this, from, fn);
         }

         public Object map$mcDI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDI$sp$(this, from, fn);
         }

         public Object map$mcDJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDJ$sp$(this, from, fn);
         }

         public Object map$mcFD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFD$sp$(this, from, fn);
         }

         public Object map$mcFF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFF$sp$(this, from, fn);
         }

         public Object map$mcFI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFI$sp$(this, from, fn);
         }

         public Object map$mcFJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFJ$sp$(this, from, fn);
         }

         public Object map$mcID$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcID$sp$(this, from, fn);
         }

         public Object map$mcIF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcIF$sp$(this, from, fn);
         }

         public Object map$mcII$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcII$sp$(this, from, fn);
         }

         public Object map$mcIJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcIJ$sp$(this, from, fn);
         }

         public Object map$mcJD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJD$sp$(this, from, fn);
         }

         public Object map$mcJF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJF$sp$(this, from, fn);
         }

         public Object map$mcJI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJI$sp$(this, from, fn);
         }

         public Object map$mcJJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJJ$sp$(this, from, fn);
         }

         public Object mapActive$mcDD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDD$sp$(this, from, fn);
         }

         public Object mapActive$mcDF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDF$sp$(this, from, fn);
         }

         public Object mapActive$mcDI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDI$sp$(this, from, fn);
         }

         public Object mapActive$mcDJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDJ$sp$(this, from, fn);
         }

         public Object mapActive$mcFD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFD$sp$(this, from, fn);
         }

         public Object mapActive$mcFF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFF$sp$(this, from, fn);
         }

         public Object mapActive$mcFI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFI$sp$(this, from, fn);
         }

         public Object mapActive$mcFJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFJ$sp$(this, from, fn);
         }

         public Object mapActive$mcID$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcID$sp$(this, from, fn);
         }

         public Object mapActive$mcIF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcIF$sp$(this, from, fn);
         }

         public Object mapActive$mcII$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcII$sp$(this, from, fn);
         }

         public Object mapActive$mcIJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcIJ$sp$(this, from, fn);
         }

         public Object mapActive$mcJD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJD$sp$(this, from, fn);
         }

         public Object mapActive$mcJF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJF$sp$(this, from, fn);
         }

         public Object mapActive$mcJI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJI$sp$(this, from, fn);
         }

         public Object mapActive$mcJJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJJ$sp$(this, from, fn);
         }

         public DenseVector map(final SliceVector from, final Function1 fn) {
            return DenseVector$.MODULE$.tabulate(from.length(), (i) -> $anonfun$map$2(fn, from, BoxesRunTime.unboxToInt(i)), this.evidence$3$1);
         }

         public DenseVector mapActive(final SliceVector from, final Function1 fn) {
            return this.map(from, fn);
         }

         // $FF: synthetic method
         public static final Object $anonfun$map$2(final Function1 fn$2, final SliceVector from$2, final int i) {
            return fn$2.apply(from$2.apply(i));
         }

         public {
            this.evidence$3$1 = evidence$3$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public CanCreateZerosLike canCreateZerosLike(final ClassTag evidence$4, final Zero evidence$5) {
      return new CanCreateZerosLike(evidence$4, evidence$5) {
         private final ClassTag evidence$4$1;
         private final Zero evidence$5$1;

         public DenseVector apply(final SliceVector v1) {
            return DenseVector$.MODULE$.zeros(v1.length(), this.evidence$4$1, this.evidence$5$1);
         }

         public {
            this.evidence$4$1 = evidence$4$1;
            this.evidence$5$1 = evidence$5$1;
         }
      };
   }

   public CanTraverseValues canIterateValues() {
      return new CanTraverseValues() {
         public Object foldLeft(final Object from, final Object b, final Function2 fn) {
            return CanTraverseValues.foldLeft$(this, from, b, fn);
         }

         public boolean isTraversableAgain(final SliceVector from) {
            return true;
         }

         public CanTraverseValues.ValuesVisitor traverse(final SliceVector from, final CanTraverseValues.ValuesVisitor fn) {
            from.valuesIterator().foreach((x$1) -> {
               $anonfun$traverse$1(fn, x$1);
               return BoxedUnit.UNIT;
            });
            return fn;
         }

         // $FF: synthetic method
         public static final void $anonfun$traverse$1(final CanTraverseValues.ValuesVisitor fn$3, final Object x$1) {
            fn$3.visit(x$1);
         }

         public {
            CanTraverseValues.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public CanTraverseKeyValuePairs canIterateKeyValuePairs() {
      return new CanTraverseKeyValuePairs() {
         public void traverse(final SliceVector from, final CanTraverseKeyValuePairs.KeyValuePairsVisitor fn) {
            from.iterator().foreach((x0$1) -> {
               $anonfun$traverse$2(fn, x0$1);
               return BoxedUnit.UNIT;
            });
         }

         public boolean isTraversableAgain(final SliceVector from) {
            return true;
         }

         // $FF: synthetic method
         public static final void $anonfun$traverse$2(final CanTraverseKeyValuePairs.KeyValuePairsVisitor fn$4, final Tuple2 x0$1) {
            if (x0$1 != null) {
               int k = x0$1._1$mcI$sp();
               Object v = x0$1._2();
               fn$4.visit(BoxesRunTime.boxToInteger(k), v);
               BoxedUnit var2 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x0$1);
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public CanTransformValues canTransformValues() {
      return new CanTransformValues() {
         public void transform$mcD$sp(final Object from, final Function1 fn) {
            CanTransformValues.transform$mcD$sp$(this, from, fn);
         }

         public void transform$mcF$sp(final Object from, final Function1 fn) {
            CanTransformValues.transform$mcF$sp$(this, from, fn);
         }

         public void transform$mcI$sp(final Object from, final Function1 fn) {
            CanTransformValues.transform$mcI$sp$(this, from, fn);
         }

         public void transformActive$mcD$sp(final Object from, final Function1 fn) {
            CanTransformValues.transformActive$mcD$sp$(this, from, fn);
         }

         public void transformActive$mcF$sp(final Object from, final Function1 fn) {
            CanTransformValues.transformActive$mcF$sp$(this, from, fn);
         }

         public void transformActive$mcI$sp(final Object from, final Function1 fn) {
            CanTransformValues.transformActive$mcI$sp$(this, from, fn);
         }

         public void transform(final SliceVector from, final Function1 fn) {
            .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), from.length()).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> from.update(i, fn.apply(from.apply(i))));
         }

         public void transformActive(final SliceVector from, final Function1 fn) {
            this.transform(from, fn);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   private SliceVector$() {
   }
}
