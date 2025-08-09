package breeze.linalg;

import breeze.linalg.support.CanMapKeyValuePairs;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanTransformValues;
import breeze.linalg.support.CanTraverseKeyValuePairs;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.ScalarOf;
import breeze.linalg.support.ScalarOf$;
import breeze.storage.Zero;
import java.lang.invoke.SerializedLambda;
import java.util.BitSet;
import scala.Function1;
import scala.Function2;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt.;
import scala.runtime.java8.JFunction1;

public final class BitVector$ {
   public static final BitVector$ MODULE$ = new BitVector$();

   public boolean $lessinit$greater$default$3() {
      return true;
   }

   public BitVector apply(final Seq bools) {
      BitSet bs = new BitSet();
      .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), bools.length()).withFilter((JFunction1.mcZI.sp)(i) -> BoxesRunTime.unboxToBoolean(bools.apply(i))).foreach((JFunction1.mcVI.sp)(i) -> bs.set(i));
      return new BitVector(bs, bools.length(), this.$lessinit$greater$default$3());
   }

   public BitVector apply(final int length, final boolean enforceLength, final Seq trues) {
      BitSet bs = new BitSet();
      trues.foreach((JFunction1.mcVI.sp)(i) -> {
         if (enforceLength && i >= length) {
            throw new IndexOutOfBoundsException((new StringBuilder(16)).append(i).append(" is bigger than ").append(length).toString());
         } else {
            bs.set(i);
         }
      });
      return new BitVector(bs, length, enforceLength && length >= 0);
   }

   public boolean apply$default$2() {
      return true;
   }

   public BitVector zeros(final int length, final boolean enforceLength) {
      return new BitVector(new BitSet(), length, enforceLength);
   }

   public boolean zeros$default$2() {
      return true;
   }

   public BitVector ones(final int length, final boolean enforceLength) {
      BitSet bs = new BitSet(length);
      bs.set(0, length);
      return new BitVector(bs, length, enforceLength);
   }

   public boolean ones$default$2() {
      return true;
   }

   public CanMapValues canMapValues(final ClassTag man) {
      return new CanMapValues.DenseCanMapValues(man) {
         private final ClassTag man$1;

         public final Object mapActive(final Object from, final Function1 fn) {
            return CanMapValues.DenseCanMapValues.mapActive$(this, from, fn);
         }

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

         public DenseVector map(final BitVector from, final Function1 fn) {
            return DenseVector$.MODULE$.tabulate(from.length(), (i) -> $anonfun$map$1(fn, from, BoxesRunTime.unboxToInt(i)), this.man$1);
         }

         // $FF: synthetic method
         public static final Object $anonfun$map$1(final Function1 fn$1, final BitVector from$1, final int i) {
            return fn$1.apply(BoxesRunTime.boxToBoolean(from$1.apply(i)));
         }

         public {
            this.man$1 = man$1;
            CanMapValues.DenseCanMapValues.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public ScalarOf scalarOf() {
      return ScalarOf$.MODULE$.dummy();
   }

   public CanTraverseValues canIterateValues() {
      return new CanTraverseValues() {
         public Object foldLeft(final Object from, final Object b, final Function2 fn) {
            return CanTraverseValues.foldLeft$(this, from, b, fn);
         }

         public boolean isTraversableAgain(final BitVector from) {
            return true;
         }

         public CanTraverseValues.ValuesVisitor traverse(final BitVector from, final CanTraverseValues.ValuesVisitor fn) {
            .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), from.length()).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> fn.visit$mcZ$sp(from.apply(i)));
            return fn;
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

   public CanTraverseKeyValuePairs canTraverseKeyValuePairs() {
      return new CanTraverseKeyValuePairs() {
         public boolean isTraversableAgain(final BitVector from) {
            return true;
         }

         public void traverse(final BitVector from, final CanTraverseKeyValuePairs.KeyValuePairsVisitor fn) {
            .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), from.length()).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> fn.visit$mcZI$sp(i, from.apply(i)));
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

         public void transform(final BitVector from, final Function1 fn) {
            .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), from.length()).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> from.update(i, BoxesRunTime.unboxToBoolean(fn.apply(BoxesRunTime.boxToBoolean(from.apply(i))))));
         }

         public void transformActive(final BitVector from, final Function1 fn) {
            this.transform(from, fn);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public CanMapKeyValuePairs canMapPairs(final ClassTag man, final Zero zero) {
      return new CanMapKeyValuePairs(man, zero) {
         private final ClassTag man$2;
         private final Zero zero$1;

         public DenseVector map(final BitVector from, final Function2 fn) {
            return DenseVector$.MODULE$.tabulate(from.length(), (i) -> $anonfun$map$2(fn, from, BoxesRunTime.unboxToInt(i)), this.man$2);
         }

         public DenseVector mapActive(final BitVector from, final Function2 fn) {
            DenseVector result = DenseVector$.MODULE$.zeros(from.length(), this.man$2, this.zero$1);
            from.activeKeysIterator().foreach((JFunction1.mcVI.sp)(i) -> result.update(i, fn.apply(BoxesRunTime.boxToInteger(i), BoxesRunTime.boxToBoolean(true))));
            return result;
         }

         // $FF: synthetic method
         public static final Object $anonfun$map$2(final Function2 fn$5, final BitVector from$5, final int i) {
            return fn$5.apply(BoxesRunTime.boxToInteger(i), BoxesRunTime.boxToBoolean(from$5.apply(i)));
         }

         public {
            this.man$2 = man$2;
            this.zero$1 = zero$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   private BitVector$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
