package breeze.linalg;

import breeze.collection.mutable.OpenAddressHashArray;
import breeze.collection.mutable.OpenAddressHashArray$mcD$sp;
import breeze.collection.mutable.OpenAddressHashArray$mcF$sp;
import breeze.collection.mutable.OpenAddressHashArray$mcI$sp;
import breeze.collection.mutable.OpenAddressHashArray$mcJ$sp;
import breeze.generic.UFunc;
import breeze.linalg.operators.HasOps$;
import breeze.linalg.support.CanCreateZeros;
import breeze.linalg.support.CanCreateZerosLike$;
import breeze.linalg.support.CanMapKeyValuePairs;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanTraverseKeyValuePairs;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.ScalarOf;
import breeze.linalg.support.ScalarOf$;
import breeze.math.Field;
import breeze.math.MutableFiniteCoordinateField;
import breeze.math.MutableFiniteCoordinateField$;
import breeze.math.Ring;
import breeze.storage.Zero;
import breeze.util.ReflectionUtil$;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction1;

public final class HashVector$ implements Serializable {
   public static final HashVector$ MODULE$ = new HashVector$();

   public HashVector zeros(final int size, final ClassTag evidence$1, final Zero evidence$2) {
      return new HashVector(new OpenAddressHashArray(size, evidence$1, evidence$2));
   }

   public HashVector apply(final Object values, final Zero evidence$3) {
      ClassTag ctg = ReflectionUtil$.MODULE$.elemClassTagFromArray(values);
      OpenAddressHashArray oah = new OpenAddressHashArray(.MODULE$.array_length(values), ctg, evidence$3);
      scala.collection.ArrayOps..MODULE$.withFilter$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.genericArrayOps(values))), (check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$apply$1(check$ifrefutable$1))).foreach((x$1) -> {
         $anonfun$apply$2(oah, x$1);
         return BoxedUnit.UNIT;
      });
      return new HashVector(oah);
   }

   public HashVector apply(final Seq values, final ClassTag evidence$4, final Zero evidence$5) {
      return this.apply(values.toArray(evidence$4), evidence$5);
   }

   public HashVector fill(final int size, final Function0 v, final ClassTag evidence$6, final Zero evidence$7) {
      return this.apply(scala.Array..MODULE$.fill(size, v, evidence$6), evidence$7);
   }

   public HashVector tabulate(final int size, final Function1 f, final ClassTag evidence$8, final Zero evidence$9) {
      return this.apply(scala.Array..MODULE$.tabulate(size, f, evidence$8), evidence$9);
   }

   public HashVector apply(final int length, final Seq values, final ClassTag evidence$10, final Zero evidence$11) {
      HashVector r = this.zeros(length, evidence$10, evidence$11);
      values.withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$apply$3(check$ifrefutable$2))).foreach((x$2) -> {
         $anonfun$apply$4(r, x$2);
         return BoxedUnit.UNIT;
      });
      return r;
   }

   public CanCreateZeros canCreateZeros(final ClassTag evidence$12, final Zero evidence$13) {
      return new CanCreateZeros(evidence$12, evidence$13) {
         private final ClassTag evidence$12$1;
         private final Zero evidence$13$1;

         public HashVector apply(final int d) {
            return HashVector$.MODULE$.zeros(d, this.evidence$12$1, this.evidence$13$1);
         }

         public {
            this.evidence$12$1 = evidence$12$1;
            this.evidence$13$1 = evidence$13$1;
         }
      };
   }

   public HashVector.CanCopyHashVector canCopyHash(final ClassTag evidence$16, final Zero evidence$17) {
      return new HashVector.CanCopyHashVector(evidence$16, evidence$17);
   }

   public CanMapValues canMapValues(final ClassTag evidence$18, final Zero evidence$19) {
      return new CanMapValues(evidence$18, evidence$19) {
         private final ClassTag evidence$18$1;
         private final Zero evidence$19$1;

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

         public HashVector map(final HashVector from, final Function1 fn) {
            return HashVector$.MODULE$.tabulate(from.length(), (i) -> $anonfun$map$1(fn, from, BoxesRunTime.unboxToInt(i)), this.evidence$18$1, this.evidence$19$1);
         }

         public HashVector mapActive(final HashVector from, final Function1 fn) {
            Object z = ((Zero)scala.Predef..MODULE$.implicitly(this.evidence$19$1)).zero();
            OpenAddressHashArray out = new OpenAddressHashArray(from.length(), this.evidence$18$1, this.evidence$19$1);
            int index$macro$2 = 0;

            for(int limit$macro$4 = from.iterableSize(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               if (from.isActive(index$macro$2)) {
                  Object vv = fn.apply(.MODULE$.array_apply(from.data(), index$macro$2));
                  if (!BoxesRunTime.equals(vv, z)) {
                     out.update(from.index()[index$macro$2], fn.apply(.MODULE$.array_apply(from.data(), index$macro$2)));
                  }
               }
            }

            return new HashVector(out);
         }

         // $FF: synthetic method
         public static final Object $anonfun$map$1(final Function1 fn$1, final HashVector from$1, final int i) {
            return fn$1.apply(from$1.apply(i));
         }

         public {
            this.evidence$18$1 = evidence$18$1;
            this.evidence$19$1 = evidence$19$1;
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

         public boolean isTraversableAgain(final HashVector from) {
            return true;
         }

         public CanTraverseValues.ValuesVisitor traverse(final HashVector from, final CanTraverseValues.ValuesVisitor fn) {
            fn.zeros(from.size() - from.activeSize(), from.default());
            int index$macro$2 = 0;

            for(int limit$macro$4 = from.iterableSize(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               if (from.isActive(index$macro$2)) {
                  fn.visit(.MODULE$.array_apply(from.data(), index$macro$2));
               }
            }

            return fn;
         }

         public {
            CanTraverseValues.$init$(this);
         }
      };
   }

   public CanTraverseKeyValuePairs canTraverseKeyValuePairs() {
      return new CanTraverseKeyValuePairs() {
         public void traverse(final HashVector from, final CanTraverseKeyValuePairs.KeyValuePairsVisitor fn) {
            fn.zeros(from.size() - from.activeSize(), scala.package..MODULE$.Iterator().range(0, from.size()).filterNot((JFunction1.mcZI.sp)(x$3) -> scala.collection.ArrayOps..MODULE$.contains$extension(scala.Predef..MODULE$.intArrayOps(from.index()), BoxesRunTime.boxToInteger(x$3))), from.default());

            for(int i = 0; i < from.iterableSize(); ++i) {
               if (from.isActive(i)) {
                  fn.visit(BoxesRunTime.boxToInteger(from.index()[i]), .MODULE$.array_apply(from.data(), i));
               }
            }

         }

         public boolean isTraversableAgain(final HashVector from) {
            return true;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public CanMapKeyValuePairs canMapPairs(final ClassTag evidence$20, final Zero evidence$21) {
      return new CanMapKeyValuePairs(evidence$20, evidence$21) {
         private final ClassTag evidence$20$1;
         private final Zero evidence$21$1;

         public HashVector map(final HashVector from, final Function2 fn) {
            return HashVector$.MODULE$.tabulate(from.length(), (i) -> $anonfun$map$2(fn, from, BoxesRunTime.unboxToInt(i)), this.evidence$20$1, this.evidence$21$1);
         }

         public HashVector mapActive(final HashVector from, final Function2 fn) {
            OpenAddressHashArray out = new OpenAddressHashArray(from.length(), this.evidence$20$1, this.evidence$21$1);

            for(int i = 0; i < from.iterableSize(); ++i) {
               if (from.isActive(i)) {
                  out.update(from.index()[i], fn.apply(BoxesRunTime.boxToInteger(from.index()[i]), .MODULE$.array_apply(from.data(), i)));
               }
            }

            return new HashVector(out);
         }

         // $FF: synthetic method
         public static final Object $anonfun$map$2(final Function2 fn$2, final HashVector from$3, final int i) {
            return fn$2.apply(BoxesRunTime.boxToInteger(i), from$3.apply(i));
         }

         public {
            this.evidence$20$1 = evidence$20$1;
            this.evidence$21$1 = evidence$21$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public MutableFiniteCoordinateField space(final Field evidence$22, final ClassTag evidence$23, final Zero evidence$24) {
      UFunc.UImpl _dim = dim$.MODULE$.implVDim(scala..less.colon.less..MODULE$.refl());
      UFunc.UImpl2 n = norm$.MODULE$.canNorm(HasOps$.MODULE$.impl_CanTraverseValues_HV_Generic(), ((Ring)scala.Predef..MODULE$.implicitly(evidence$22)).normImpl());
      UFunc.InPlaceImpl2 add = HasOps$.MODULE$.castUpdateOps_V_S(this.scalarOf(), scala..less.colon.less..MODULE$.refl(), breeze.gymnastics.NotGiven..MODULE$.neq(), HasOps$.MODULE$.impl_Op_InPlace_V_S_Generic_OpAdd(evidence$22, evidence$23));
      return MutableFiniteCoordinateField$.MODULE$.make(n, norm$.MODULE$.normDoubleToNormalNorm(n), evidence$22, HasOps$.MODULE$.impl_OpAdd_HV_S_eq_HV_Generic(evidence$22), HasOps$.MODULE$.impl_OpSub_HV_S_eq_HV_Generic(evidence$22), HasOps$.MODULE$.pureFromUpdate(HasOps$.MODULE$.impl_OpMulScalar_InPlace_HV_HV_Generic(evidence$22, evidence$23), this.canCopyHash(evidence$23, evidence$24)), HasOps$.MODULE$.pureFromUpdate(HasOps$.MODULE$.impl_OpDiv_InPlace_HV_HV_Generic(evidence$22), this.canCopyHash(evidence$23, evidence$24)), this.canCopyHash(evidence$23, evidence$24), HasOps$.MODULE$.impl_OpMulScalar_InPlace_HV_S_Generic(evidence$22), HasOps$.MODULE$.impl_OpDiv_InPlace_HV_S_Generic(evidence$22), HasOps$.MODULE$.impl_OpAdd_InPlace_T_U_Generic_from_scaleAdd_InPlace(HasOps$.MODULE$.impl_scaleAdd_InPlace_HV_V_HV(evidence$22), evidence$22), HasOps$.MODULE$.impl_OpSub_InPlace_T_U_Generic_from_scaleAdd_InPlace(HasOps$.MODULE$.impl_scaleAdd_InPlace_HV_V_HV(evidence$22), evidence$22), add, HasOps$.MODULE$.castUpdateOps_V_S(this.scalarOf(), scala..less.colon.less..MODULE$.refl(), breeze.gymnastics.NotGiven..MODULE$.neq(), HasOps$.MODULE$.impl_Op_InPlace_V_S_Generic_OpSub(evidence$22, evidence$23)), HasOps$.MODULE$.impl_OpMulScalar_InPlace_HV_HV_Generic(evidence$22, evidence$23), HasOps$.MODULE$.impl_OpDiv_InPlace_HV_HV_Generic(evidence$22), HasOps$.MODULE$.impl_OpSet_InPlace_HV_HV_Generic(), HasOps$.MODULE$.impl_OpSet_InPlace_HV_S_Generic(), HasOps$.MODULE$.impl_scaleAdd_InPlace_HV_V_HV(evidence$22), CanCreateZerosLike$.MODULE$.opMapValues(this.canMapValues(evidence$23, evidence$24), evidence$22), this.canCreateZeros(evidence$23, evidence$24), _dim, HasOps$.MODULE$.pureFromUpdate(HasOps$.MODULE$.impl_OpMulScalar_InPlace_HV_S_Generic(evidence$22), this.canCopyHash(evidence$23, evidence$24)), HasOps$.MODULE$.pureFromUpdate(HasOps$.MODULE$.impl_OpDiv_InPlace_HV_S_Generic(evidence$22), this.canCopyHash(evidence$23, evidence$24)), HasOps$.MODULE$.pureFromUpdate(HasOps$.MODULE$.impl_OpAdd_InPlace_T_U_Generic_from_scaleAdd_InPlace(HasOps$.MODULE$.impl_scaleAdd_InPlace_HV_V_HV(evidence$22), evidence$22), this.canCopyHash(evidence$23, evidence$24)), HasOps$.MODULE$.pureFromUpdate(HasOps$.MODULE$.impl_OpSub_InPlace_T_U_Generic_from_scaleAdd_InPlace(HasOps$.MODULE$.impl_scaleAdd_InPlace_HV_V_HV(evidence$22), evidence$22), this.canCopyHash(evidence$23, evidence$24)), HasOps$.MODULE$.impl_OpNeg_T_Generic_from_OpMulScalar(this.scalarOf(), evidence$22, HasOps$.MODULE$.pureFromUpdate(HasOps$.MODULE$.impl_OpMulScalar_InPlace_HV_S_Generic(evidence$22), this.canCopyHash(evidence$23, evidence$24))), scala..less.colon.less..MODULE$.refl(), HasOps$.MODULE$.castOps_V_V(scala..less.colon.less..MODULE$.refl(), scala..less.colon.less..MODULE$.refl(), breeze.gymnastics.NotGiven..MODULE$.neq(), HasOps$.MODULE$.impl_OpMulInner_V_V_eq_S_Generic(evidence$22)), HasOps$.MODULE$.HV_zipMap(evidence$23, evidence$24), HasOps$.MODULE$.HV_zipMapKV(evidence$23, evidence$24), this.canIterateValues(), this.canMapValues(evidence$23, evidence$24), this.scalarOf());
   }

   public void breeze$linalg$HashVector$$init() {
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(HashVector$.class);
   }

   public HashVector zeros$mDc$sp(final int size, final ClassTag evidence$1, final Zero evidence$2) {
      return new HashVector$mcD$sp(new OpenAddressHashArray$mcD$sp(size, evidence$1, evidence$2));
   }

   public HashVector zeros$mFc$sp(final int size, final ClassTag evidence$1, final Zero evidence$2) {
      return new HashVector$mcF$sp(new OpenAddressHashArray$mcF$sp(size, evidence$1, evidence$2));
   }

   public HashVector zeros$mIc$sp(final int size, final ClassTag evidence$1, final Zero evidence$2) {
      return new HashVector$mcI$sp(new OpenAddressHashArray$mcI$sp(size, evidence$1, evidence$2));
   }

   public HashVector zeros$mJc$sp(final int size, final ClassTag evidence$1, final Zero evidence$2) {
      return new HashVector$mcJ$sp(new OpenAddressHashArray$mcJ$sp(size, evidence$1, evidence$2));
   }

   public HashVector apply$mDc$sp(final double[] values, final Zero evidence$3) {
      ClassTag ctg = ReflectionUtil$.MODULE$.elemClassTagFromArray(values);
      OpenAddressHashArray oah = new OpenAddressHashArray$mcD$sp(values.length, ctg, evidence$3);
      scala.collection.ArrayOps..MODULE$.withFilter$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.genericArrayOps(values))), (check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$apply$5(check$ifrefutable$1))).foreach((x$1) -> {
         $anonfun$apply$6(oah, x$1);
         return BoxedUnit.UNIT;
      });
      return new HashVector$mcD$sp(oah);
   }

   public HashVector apply$mFc$sp(final float[] values, final Zero evidence$3) {
      ClassTag ctg = ReflectionUtil$.MODULE$.elemClassTagFromArray(values);
      OpenAddressHashArray oah = new OpenAddressHashArray$mcF$sp(values.length, ctg, evidence$3);
      scala.collection.ArrayOps..MODULE$.withFilter$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.genericArrayOps(values))), (check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$apply$7(check$ifrefutable$1))).foreach((x$1) -> {
         $anonfun$apply$8(oah, x$1);
         return BoxedUnit.UNIT;
      });
      return new HashVector$mcF$sp(oah);
   }

   public HashVector apply$mIc$sp(final int[] values, final Zero evidence$3) {
      ClassTag ctg = ReflectionUtil$.MODULE$.elemClassTagFromArray(values);
      OpenAddressHashArray oah = new OpenAddressHashArray$mcI$sp(values.length, ctg, evidence$3);
      scala.collection.ArrayOps..MODULE$.withFilter$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.genericArrayOps(values))), (check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$apply$9(check$ifrefutable$1))).foreach((x$1) -> {
         $anonfun$apply$10(oah, x$1);
         return BoxedUnit.UNIT;
      });
      return new HashVector$mcI$sp(oah);
   }

   public HashVector apply$mJc$sp(final long[] values, final Zero evidence$3) {
      ClassTag ctg = ReflectionUtil$.MODULE$.elemClassTagFromArray(values);
      OpenAddressHashArray oah = new OpenAddressHashArray$mcJ$sp(values.length, ctg, evidence$3);
      scala.collection.ArrayOps..MODULE$.withFilter$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.genericArrayOps(values))), (check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$apply$11(check$ifrefutable$1))).foreach((x$1) -> {
         $anonfun$apply$12(oah, x$1);
         return BoxedUnit.UNIT;
      });
      return new HashVector$mcJ$sp(oah);
   }

   public HashVector fill$mDc$sp(final int size, final Function0 v, final ClassTag evidence$6, final Zero evidence$7) {
      return this.apply$mDc$sp((double[])scala.Array..MODULE$.fill(size, v, evidence$6), evidence$7);
   }

   public HashVector fill$mFc$sp(final int size, final Function0 v, final ClassTag evidence$6, final Zero evidence$7) {
      return this.apply$mFc$sp((float[])scala.Array..MODULE$.fill(size, v, evidence$6), evidence$7);
   }

   public HashVector fill$mIc$sp(final int size, final Function0 v, final ClassTag evidence$6, final Zero evidence$7) {
      return this.apply$mIc$sp((int[])scala.Array..MODULE$.fill(size, v, evidence$6), evidence$7);
   }

   public HashVector fill$mJc$sp(final int size, final Function0 v, final ClassTag evidence$6, final Zero evidence$7) {
      return this.apply$mJc$sp((long[])scala.Array..MODULE$.fill(size, v, evidence$6), evidence$7);
   }

   public HashVector tabulate$mDc$sp(final int size, final Function1 f, final ClassTag evidence$8, final Zero evidence$9) {
      return this.apply$mDc$sp((double[])scala.Array..MODULE$.tabulate(size, f, evidence$8), evidence$9);
   }

   public HashVector tabulate$mFc$sp(final int size, final Function1 f, final ClassTag evidence$8, final Zero evidence$9) {
      return this.apply$mFc$sp((float[])scala.Array..MODULE$.tabulate(size, f, evidence$8), evidence$9);
   }

   public HashVector tabulate$mIc$sp(final int size, final Function1 f, final ClassTag evidence$8, final Zero evidence$9) {
      return this.apply$mIc$sp((int[])scala.Array..MODULE$.tabulate(size, f, evidence$8), evidence$9);
   }

   public HashVector tabulate$mJc$sp(final int size, final Function1 f, final ClassTag evidence$8, final Zero evidence$9) {
      return this.apply$mJc$sp((long[])scala.Array..MODULE$.tabulate(size, f, evidence$8), evidence$9);
   }

   public HashVector.CanCopyHashVector canCopyHash$mDc$sp(final ClassTag evidence$16, final Zero evidence$17) {
      return new HashVector$CanCopyHashVector$mcD$sp(evidence$16, evidence$17);
   }

   public HashVector.CanCopyHashVector canCopyHash$mFc$sp(final ClassTag evidence$16, final Zero evidence$17) {
      return new HashVector$CanCopyHashVector$mcF$sp(evidence$16, evidence$17);
   }

   public HashVector.CanCopyHashVector canCopyHash$mIc$sp(final ClassTag evidence$16, final Zero evidence$17) {
      return new HashVector$CanCopyHashVector$mcI$sp(evidence$16, evidence$17);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$1(final Tuple2 check$ifrefutable$1) {
      boolean var1;
      if (check$ifrefutable$1 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   public static final void $anonfun$apply$2(final OpenAddressHashArray oah$1, final Tuple2 x$1) {
      if (x$1 != null) {
         Object v = x$1._1();
         int i = x$1._2$mcI$sp();
         oah$1.update(i, v);
         BoxedUnit var2 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$3(final Tuple2 check$ifrefutable$2) {
      boolean var1;
      if (check$ifrefutable$2 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   public static final void $anonfun$apply$4(final HashVector r$1, final Tuple2 x$2) {
      if (x$2 != null) {
         int i = x$2._1$mcI$sp();
         Object v = x$2._2();
         r$1.update(i, v);
         BoxedUnit var2 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$2);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$5(final Tuple2 check$ifrefutable$1) {
      boolean var1;
      if (check$ifrefutable$1 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   public static final void $anonfun$apply$6(final OpenAddressHashArray oah$2, final Tuple2 x$1) {
      if (x$1 != null) {
         double v = x$1._1$mcD$sp();
         int i = x$1._2$mcI$sp();
         oah$2.update$mcD$sp(i, v);
         BoxedUnit var2 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$7(final Tuple2 check$ifrefutable$1) {
      boolean var1;
      if (check$ifrefutable$1 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   public static final void $anonfun$apply$8(final OpenAddressHashArray oah$3, final Tuple2 x$1) {
      if (x$1 != null) {
         float v = BoxesRunTime.unboxToFloat(x$1._1());
         int i = x$1._2$mcI$sp();
         oah$3.update$mcF$sp(i, v);
         BoxedUnit var2 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$9(final Tuple2 check$ifrefutable$1) {
      boolean var1;
      if (check$ifrefutable$1 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   public static final void $anonfun$apply$10(final OpenAddressHashArray oah$4, final Tuple2 x$1) {
      if (x$1 != null) {
         int v = x$1._1$mcI$sp();
         int i = x$1._2$mcI$sp();
         oah$4.update$mcI$sp(i, v);
         BoxedUnit var2 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$11(final Tuple2 check$ifrefutable$1) {
      boolean var1;
      if (check$ifrefutable$1 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   public static final void $anonfun$apply$12(final OpenAddressHashArray oah$5, final Tuple2 x$1) {
      if (x$1 != null) {
         long v = x$1._1$mcJ$sp();
         int i = x$1._2$mcI$sp();
         oah$5.update$mcJ$sp(i, v);
         BoxedUnit var2 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$1);
      }
   }

   private HashVector$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
