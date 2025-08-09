package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.operators.CounterOps;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanCreateZeros;
import breeze.linalg.support.CanCreateZerosLike;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanTransformValues;
import breeze.linalg.support.CanTraverseKeyValuePairs;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.ScalarOf;
import breeze.linalg.support.ScalarOf$;
import breeze.math.Field;
import breeze.math.MutableEnumeratedCoordinateField;
import breeze.math.MutableEnumeratedCoordinateField$;
import breeze.math.Ring;
import breeze.math.Semiring;
import breeze.storage.Zero;
import breeze.storage.Zero$;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Map;
import scala.collection.mutable.HashMap.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.DoubleRef;
import scala.runtime.ModuleSerializationProxy;

public final class Counter$ implements CounterOps, Serializable {
   public static final Counter$ MODULE$ = new Counter$();

   static {
      CounterOps.$init$(MODULE$);
   }

   public CanCopy canCopy(final Zero evidence$1, final Semiring evidence$2) {
      return CounterOps.canCopy$(this, evidence$1, evidence$2);
   }

   public UFunc.UImpl2 binaryOpFromBinaryUpdateOp(final CanCopy copy, final UFunc.InPlaceImpl2 op) {
      return CounterOps.binaryOpFromBinaryUpdateOp$(this, copy, op);
   }

   public UFunc.InPlaceImpl2 addIntoVV(final Semiring evidence$3) {
      return CounterOps.addIntoVV$(this, evidence$3);
   }

   public UFunc.InPlaceImpl3 canAxpy(final Semiring evidence$4) {
      return CounterOps.canAxpy$(this, evidence$4);
   }

   public UFunc.UImpl2 addVV(final Semiring evidence$5, final Zero evidence$6) {
      return CounterOps.addVV$(this, evidence$5, evidence$6);
   }

   public UFunc.InPlaceImpl2 addIntoVS(final Semiring evidence$7) {
      return CounterOps.addIntoVS$(this, evidence$7);
   }

   public UFunc.UImpl2 addVS(final Semiring evidence$8, final Zero evidence$9) {
      return CounterOps.addVS$(this, evidence$8, evidence$9);
   }

   public UFunc.InPlaceImpl2 subIntoVV(final Ring evidence$10) {
      return CounterOps.subIntoVV$(this, evidence$10);
   }

   public UFunc.UImpl2 subVV(final Ring evidence$11, final Zero evidence$12) {
      return CounterOps.subVV$(this, evidence$11, evidence$12);
   }

   public UFunc.InPlaceImpl2 subIntoVS(final Ring evidence$13) {
      return CounterOps.subIntoVS$(this, evidence$13);
   }

   public UFunc.UImpl2 subVS(final Ring evidence$14, final Zero evidence$15) {
      return CounterOps.subVS$(this, evidence$14, evidence$15);
   }

   public UFunc.InPlaceImpl2 canMulIntoVV(final Semiring evidence$16) {
      return CounterOps.canMulIntoVV$(this, evidence$16);
   }

   public UFunc.UImpl2 canMulVV(final Semiring semiring) {
      return CounterOps.canMulVV$(this, semiring);
   }

   public UFunc.InPlaceImpl2 canMulIntoVS(final Semiring evidence$17) {
      return CounterOps.canMulIntoVS$(this, evidence$17);
   }

   public UFunc.InPlaceImpl2 canMulIntoVS_M(final Semiring evidence$18) {
      return CounterOps.canMulIntoVS_M$(this, evidence$18);
   }

   public UFunc.UImpl2 canMulVS(final Semiring semiring) {
      return CounterOps.canMulVS$(this, semiring);
   }

   public UFunc.UImpl2 canMulVS_M(final Semiring semiring) {
      return CounterOps.canMulVS_M$(this, semiring);
   }

   public UFunc.InPlaceImpl2 canDivIntoVV(final Field evidence$19) {
      return CounterOps.canDivIntoVV$(this, evidence$19);
   }

   public UFunc.UImpl2 canDivVV(final CanCopy copy, final Field semiring) {
      return CounterOps.canDivVV$(this, copy, semiring);
   }

   public UFunc.UImpl2 canDivVS(final CanCopy copy, final Field semiring) {
      return CounterOps.canDivVS$(this, copy, semiring);
   }

   public UFunc.InPlaceImpl2 canDivIntoVS(final Field evidence$20) {
      return CounterOps.canDivIntoVS$(this, evidence$20);
   }

   public UFunc.InPlaceImpl2 impl_OpSet_InPlace_C_C() {
      return CounterOps.impl_OpSet_InPlace_C_C$(this);
   }

   public UFunc.InPlaceImpl2 canSetIntoVS() {
      return CounterOps.canSetIntoVS$(this);
   }

   public UFunc.UImpl canNegate(final Ring ring) {
      return CounterOps.canNegate$(this, ring);
   }

   public UFunc.UImpl2 canMulInner(final CanCopy copy, final Semiring semiring) {
      return CounterOps.canMulInner$(this, copy, semiring);
   }

   public UFunc.UImpl2 canNorm(final UFunc.UImpl normImpl) {
      return CounterOps.canNorm$(this, normImpl);
   }

   public CounterOps.CanZipMapValuesCounter zipMap(final Zero evidence$23, final Semiring evidence$24) {
      return CounterOps.zipMap$(this, evidence$23, evidence$24);
   }

   public CounterOps.CanZipMapKeyValuesCounter zipMapKeyValues(final Zero evidence$27, final Semiring evidence$28) {
      return CounterOps.zipMapKeyValues$(this, evidence$27, evidence$28);
   }

   public CanTransformValues canTransformValues() {
      return CounterOps.canTransformValues$(this);
   }

   public CanTraverseValues canTraverseValues() {
      return CounterOps.canTraverseValues$(this);
   }

   public Counter apply(final Zero evidence$1) {
      return new Counter.Impl((Map).MODULE$.apply(scala.collection.immutable.Nil..MODULE$), evidence$1);
   }

   public Counter apply(final Seq values, final Zero evidence$2, final Semiring evidence$3) {
      return this.apply((IterableOnce)values, evidence$2, evidence$3);
   }

   public Counter apply(final IterableOnce values, final Zero evidence$4, final Semiring evidence$5) {
      Counter rv = this.apply(evidence$4);
      Semiring field = (Semiring)scala.Predef..MODULE$.implicitly(evidence$5);
      values.iterator().foreach((x0$1) -> {
         $anonfun$apply$2(rv, field, x0$1);
         return BoxedUnit.UNIT;
      });
      return rv;
   }

   public Counter countTraversable(final IterableOnce items) {
      Counter rv = this.apply(Zero$.MODULE$.IntZero());
      items.iterator().foreach((x$1) -> {
         $anonfun$countTraversable$1(rv, x$1);
         return BoxedUnit.UNIT;
      });
      return rv;
   }

   public Counter count(final Seq items) {
      return this.countTraversable(items);
   }

   public CanMapValues canMapValues(final Zero evidence$6) {
      return new CanMapValues.DenseCanMapValues(evidence$6) {
         private final Zero evidence$6$1;

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

         public Counter map(final Counter from, final Function1 fn) {
            Counter rv = Counter$.MODULE$.apply(this.evidence$6$1);
            from.iterator().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$map$1(check$ifrefutable$1))).foreach((x$2) -> {
               $anonfun$map$2(rv, fn, x$2);
               return BoxedUnit.UNIT;
            });
            return rv;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$map$1(final Tuple2 check$ifrefutable$1) {
            boolean var1;
            if (check$ifrefutable$1 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$map$2(final Counter rv$3, final Function1 fn$1, final Tuple2 x$2) {
            if (x$2 != null) {
               Object k = x$2._1();
               Object v = x$2._2();
               rv$3.update(k, fn$1.apply(v));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$2);
            }
         }

         public {
            this.evidence$6$1 = evidence$6$1;
            CanMapValues.DenseCanMapValues.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public CanTraverseValues canIterateValues() {
      return new CanTraverseValues() {
         public Object foldLeft(final Object from, final Object b, final Function2 fn) {
            return CanTraverseValues.foldLeft$(this, from, b, fn);
         }

         public boolean isTraversableAgain(final Counter from) {
            return true;
         }

         public CanTraverseValues.ValuesVisitor traverse(final Counter from, final CanTraverseValues.ValuesVisitor fn) {
            from.valuesIterator().foreach((v) -> {
               $anonfun$traverse$1(fn, v);
               return BoxedUnit.UNIT;
            });
            return fn;
         }

         // $FF: synthetic method
         public static final void $anonfun$traverse$1(final CanTraverseValues.ValuesVisitor fn$2, final Object v) {
            fn$2.visit(v);
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

   public ScalarOf scalarOf() {
      return ScalarOf$.MODULE$.dummy();
   }

   public CanTraverseKeyValuePairs canTraverseKeyValuePairs() {
      return new CanTraverseKeyValuePairs() {
         public void traverse(final Counter from, final CanTraverseKeyValuePairs.KeyValuePairsVisitor fn) {
            from.activeIterator().withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$traverse$2(check$ifrefutable$2))).foreach((x$3) -> {
               $anonfun$traverse$3(fn, x$3);
               return BoxedUnit.UNIT;
            });
         }

         public boolean isTraversableAgain(final Counter from) {
            return true;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$traverse$2(final Tuple2 check$ifrefutable$2) {
            boolean var1;
            if (check$ifrefutable$2 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$traverse$3(final CanTraverseKeyValuePairs.KeyValuePairsVisitor fn$3, final Tuple2 x$3) {
            if (x$3 != null) {
               Object k = x$3._1();
               Object v = x$3._2();
               fn$3.visit(k, v);
               BoxedUnit var2 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$3);
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public UFunc.UImpl2 normImplDouble(final Field evidence$7) {
      return new UFunc.UImpl2(evidence$7) {
         private final Field evidence$7$1;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public double apply(final Counter ctr, final double p) {
            DoubleRef result = DoubleRef.create((double)0.0F);
            ctr.valuesIterator().foreach((v) -> {
               $anonfun$apply$3(this, result, p, v);
               return BoxedUnit.UNIT;
            });
            return scala.math.package..MODULE$.pow(result.elem, (double)1 / p);
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$3(final Object $this, final DoubleRef result$1, final double p$1, final Object v) {
            result$1.elem += scala.math.package..MODULE$.pow(BoxesRunTime.unboxToDouble(((Ring)scala.Predef..MODULE$.implicitly($this.evidence$7$1)).normImpl().apply(v)), p$1);
         }

         public {
            this.evidence$7$1 = evidence$7$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public CanCreateZeros canCreateZeros(final Zero evidence$8, final Semiring evidence$9) {
      return new CanCreateZeros(evidence$8) {
         private final Zero evidence$8$1;

         public Counter apply(final Object d) {
            return Counter$.MODULE$.apply(this.evidence$8$1);
         }

         public {
            this.evidence$8$1 = evidence$8$1;
         }
      };
   }

   public CanCreateZerosLike canCreateZerosLike(final Zero evidence$10, final Semiring evidence$11) {
      return new CanCreateZerosLike(evidence$10) {
         private final Zero evidence$10$1;

         public Counter apply(final Counter d) {
            Counter r = Counter$.MODULE$.apply(this.evidence$10$1);
            Object z = ((Zero)scala.Predef..MODULE$.implicitly(this.evidence$10$1)).zero();
            d.iterator().withFilter((check$ifrefutable$3) -> BoxesRunTime.boxToBoolean($anonfun$apply$4(check$ifrefutable$3))).foreach((x$4) -> {
               $anonfun$apply$5(r, z, x$4);
               return BoxedUnit.UNIT;
            });
            return r;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$4(final Tuple2 check$ifrefutable$3) {
            boolean var1;
            if (check$ifrefutable$3 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$5(final Counter r$1, final Object z$1, final Tuple2 x$4) {
            if (x$4 != null) {
               Object k = x$4._1();
               r$1.update(k, z$1);
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$4);
            }
         }

         public {
            this.evidence$10$1 = evidence$10$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public MutableEnumeratedCoordinateField space(final Field field) {
      return MutableEnumeratedCoordinateField$.MODULE$.make(this.normImplDouble(field), norm$.MODULE$.normDoubleToNormalNorm(this.normImplDouble(field)), field, this.canMulVV(field), this.canDivVV(this.canCopy(Zero$.MODULE$.zeroFromSemiring(field), field), field), this.canCopy(Zero$.MODULE$.zeroFromSemiring(field), field), this.canMulIntoVS(field), this.canDivIntoVS(field), this.addIntoVV(field), this.subIntoVV(field), this.canMulIntoVV(field), this.canDivIntoVV(field), this.impl_OpSet_InPlace_C_C(), this.canAxpy(field), this.canCreateZerosLike(Zero$.MODULE$.zeroFromSemiring(field), field), this.canMulVS(field), this.canDivVS(this.canCopy(Zero$.MODULE$.zeroFromSemiring(field), field), field), this.addVV(field, Zero$.MODULE$.zeroFromSemiring(field)), this.subVV(field, Zero$.MODULE$.zeroFromSemiring(field)), this.canNegate(field), scala..less.colon.less..MODULE$.refl(), this.canMulInner(this.canCopy(Zero$.MODULE$.zeroFromSemiring(field), field), field), zipMap$1(field), this.zipMapKeyValues(Zero$.MODULE$.zeroFromSemiring(field), field), this.canIterateValues(), this.canMapValues(Zero$.MODULE$.zeroFromSemiring(field)), this.scalarOf());
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Counter$.class);
   }

   // $FF: synthetic method
   public static final void $anonfun$apply$2(final Counter rv$1, final Semiring field$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Object k = x0$1._1();
         Object v = x0$1._2();
         rv$1.update(k, field$1.$plus(v, rv$1.apply(k)));
         BoxedUnit var3 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$countTraversable$1(final Counter rv$2, final Object x$1) {
      rv$2.update(x$1, BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(rv$2.apply(x$1)) + 1));
   }

   private static final CounterOps.CanZipMapValuesCounter zipMap$1(final Field field$2) {
      return MODULE$.zipMap(Zero$.MODULE$.zeroFromSemiring(field$2), field$2);
   }

   private Counter$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
