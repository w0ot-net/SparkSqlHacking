package breeze.linalg;

import breeze.collection.mutable.SparseArray;
import breeze.generic.UFunc;
import breeze.linalg.operators.HasOps$;
import breeze.linalg.operators.OpDiv$;
import breeze.linalg.support.CanCreateZeros;
import breeze.linalg.support.CanCreateZerosLike;
import breeze.linalg.support.CanMapKeyValuePairs;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanTransformValues;
import breeze.linalg.support.CanTraverseKeyValuePairs;
import breeze.linalg.support.ScalarOf;
import breeze.linalg.support.ScalarOf$;
import breeze.math.Field;
import breeze.math.MutableFiniteCoordinateField;
import breeze.math.MutableFiniteCoordinateField$;
import breeze.math.Semiring;
import breeze.storage.Zero;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Tuple2;
import scala.Array.;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuilder;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction1;

public final class SparseVector$ implements Serializable {
   public static final SparseVector$ MODULE$ = new SparseVector$();

   public SparseVector zeros(final int size, final ClassTag evidence$1, final Zero evidence$2) {
      return new SparseVector((int[]).MODULE$.empty(scala.reflect.ClassTag..MODULE$.Int()), .MODULE$.empty(evidence$1), 0, size, evidence$2);
   }

   public SparseVector apply(final Object values, final Zero evidence$3) {
      return new SparseVector(.MODULE$.range(0, scala.runtime.ScalaRunTime..MODULE$.array_length(values)), values, scala.runtime.ScalaRunTime..MODULE$.array_length(values), scala.runtime.ScalaRunTime..MODULE$.array_length(values), evidence$3);
   }

   public SparseVector apply(final Seq values, final ClassTag evidence$4, final Zero evidence$5) {
      return this.apply(values.toArray(evidence$4), evidence$5);
   }

   public SparseVector fill(final int size, final Function0 v, final ClassTag evidence$6, final Zero evidence$7) {
      return this.apply(.MODULE$.fill(size, v, evidence$6), evidence$7);
   }

   public SparseVector tabulate(final int size, final Function1 f, final ClassTag evidence$8, final Zero evidence$9) {
      return this.apply(.MODULE$.tabulate(size, f, evidence$8), evidence$9);
   }

   public SparseVector apply(final int length, final Seq values, final ClassTag evidence$10, final Zero evidence$11, final Semiring evidence$12) {
      VectorBuilder b = new VectorBuilder(length, VectorBuilder$.MODULE$.$lessinit$greater$default$2(), evidence$12, evidence$10);
      values.withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$apply$1(check$ifrefutable$1))).foreach((x$1) -> {
         $anonfun$apply$2(b, x$1);
         return BoxedUnit.UNIT;
      });
      return b.toSparseVector();
   }

   public SparseVector vertcat(final Seq vectors, final Zero evidence$13, final ClassTag evidence$14) {
      SparseArray resultArray = (SparseArray)((IterableOnceOps)vectors.map((x$2) -> x$2.array())).foldLeft(new SparseArray(0, evidence$14, evidence$13), (x$3, x$4) -> x$3.concatenate(x$4, evidence$14));
      return new SparseVector(resultArray, evidence$13);
   }

   public CSCMatrix horzcat(final Seq vectors, final Zero evidence$15, final ClassTag evidence$16) {
      if (!vectors.forall((x$5) -> BoxesRunTime.boxToBoolean($anonfun$horzcat$1(vectors, x$5)))) {
         throw new IllegalArgumentException((new StringBuilder(39)).append("vector lengths must be equal, but got: ").append(((IterableOnceOps)vectors.map((x$6) -> BoxesRunTime.boxToInteger($anonfun$horzcat$2(x$6)))).mkString(", ")).toString());
      } else {
         int rows = ((SparseVector)vectors.apply(0)).length();
         int cols = vectors.length();
         Object data = evidence$16.newArray(BoxesRunTime.unboxToInt(((IterableOnceOps)vectors.map((x$7) -> BoxesRunTime.boxToInteger($anonfun$horzcat$3(x$7)))).sum(scala.math.Numeric.IntIsIntegral..MODULE$)));
         int[] rowIndices = new int[scala.runtime.ScalaRunTime..MODULE$.array_length(data)];
         int[] colPtrs = new int[vectors.length() + 1];
         int used = scala.runtime.ScalaRunTime..MODULE$.array_length(data);
         int vec = 0;

         int off;
         for(off = 0; vec < vectors.length(); ++vec) {
            colPtrs[vec] = off;
            System.arraycopy(((SparseVector)vectors.apply(vec)).data(), 0, data, off, ((SparseVector)vectors.apply(vec)).activeSize());
            System.arraycopy(((SparseVector)vectors.apply(vec)).index(), 0, rowIndices, off, ((SparseVector)vectors.apply(vec)).activeSize());
            off += ((SparseVector)vectors.apply(vec)).activeSize();
         }

         colPtrs[vec] = off;
         return new CSCMatrix(data, rows, cols, colPtrs, used, rowIndices, evidence$15);
      }
   }

   public SparseVector.CanCopySparseVector canCopySparse(final ClassTag evidence$19, final Zero evidence$20) {
      return new SparseVector.CanCopySparseVector(evidence$19, evidence$20);
   }

   public CanMapValues canMapValues(final ClassTag evidence$21, final Zero evidence$22) {
      return new CanMapValues(evidence$21, evidence$22) {
         private final ClassTag evidence$21$1;
         private final Zero evidence$22$1;

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

         public SparseVector map(final SparseVector from, final Function1 fn) {
            return SparseVector$.MODULE$.tabulate(from.length(), (i) -> $anonfun$map$1(fn, from, BoxesRunTime.unboxToInt(i)), this.evidence$21$1, this.evidence$22$1);
         }

         public SparseVector mapActive(final SparseVector from, final Function1 fn) {
            Object out = this.evidence$21$1.newArray(from.activeSize());
            int index$macro$2 = 0;

            for(int limit$macro$4 = from.activeSize(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               scala.runtime.ScalaRunTime..MODULE$.array_update(out, index$macro$2, fn.apply(scala.runtime.ScalaRunTime..MODULE$.array_apply(from.data(), index$macro$2)));
            }

            return new SparseVector((int[])scala.collection.ArrayOps..MODULE$.take$extension(scala.Predef..MODULE$.intArrayOps(from.index()), from.activeSize()), out, from.activeSize(), from.length(), this.evidence$22$1);
         }

         // $FF: synthetic method
         public static final Object $anonfun$map$1(final Function1 fn$1, final SparseVector from$1, final int i) {
            return fn$1.apply(from$1.apply(i));
         }

         public {
            this.evidence$21$1 = evidence$21$1;
            this.evidence$22$1 = evidence$22$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public CanTraverseKeyValuePairs canTraverseKeyValuePairs() {
      return new CanTraverseKeyValuePairs() {
         public boolean isTraversableAgain(final SparseVector from) {
            return true;
         }

         public void traverse(final SparseVector from, final CanTraverseKeyValuePairs.KeyValuePairsVisitor fn) {
            fn.visitArray(scala.Predef..MODULE$.wrapIntArray(from.index()), from.data(), 0, from.activeSize(), 1);
            if (from.activeSize() != from.size()) {
               fn.zeros(from.size() - from.activeSize(), scala.package..MODULE$.Iterator().range(0, from.size()).filterNot((JFunction1.mcZI.sp)(x$8) -> scala.collection.ArrayOps..MODULE$.contains$extension(scala.Predef..MODULE$.intArrayOps(from.index()), BoxesRunTime.boxToInteger(x$8))), from.default());
            }

         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public CanCreateZeros canCreateZeros(final ClassTag evidence$23, final Zero evidence$24) {
      return new CanCreateZeros(evidence$23, evidence$24) {
         private final ClassTag evidence$23$1;
         private final Zero evidence$24$1;

         public SparseVector apply(final int d) {
            return SparseVector$.MODULE$.zeros(d, this.evidence$23$1, this.evidence$24$1);
         }

         public {
            this.evidence$23$1 = evidence$23$1;
            this.evidence$24$1 = evidence$24$1;
         }
      };
   }

   public CanCreateZerosLike canCreateZerosLike(final ClassTag evidence$25, final Zero evidence$26) {
      return new CanCreateZerosLike(evidence$25, evidence$26) {
         private final ClassTag evidence$25$1;
         private final Zero evidence$26$1;

         public SparseVector apply(final SparseVector d) {
            return SparseVector$.MODULE$.zeros(d.length(), this.evidence$25$1, this.evidence$26$1);
         }

         public {
            this.evidence$25$1 = evidence$25$1;
            this.evidence$26$1 = evidence$26$1;
         }
      };
   }

   public CanTransformValues canTransformValues(final Zero evidence$27, final ClassTag evidence$28) {
      return new CanTransformValues(evidence$27, evidence$28) {
         private final Zero z;
         private final ClassTag evidence$28$1;

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

         private Zero z() {
            return this.z;
         }

         public void transform(final SparseVector from, final Function1 fn) {
            ArrayBuilder newData = scala.collection.mutable.ArrayBuilder..MODULE$.make(this.evidence$28$1);
            ArrayBuilder newIndex = scala.collection.mutable.ArrayBuilder..MODULE$.make(scala.reflect.ClassTag..MODULE$.Int());
            int used = 0;

            for(int i = 0; i < from.length(); ++i) {
               Object vv = fn.apply(from.apply(i));
               Zero var8 = this.z();
               if (vv == null) {
                  if (var8 == null) {
                     continue;
                  }
               } else if (vv.equals(var8)) {
                  continue;
               }

               newData.$plus$eq(vv);
               newIndex.$plus$eq(BoxesRunTime.boxToInteger(i));
               ++used;
            }

            from.array().use((int[])newIndex.result(), newData.result(), used);
         }

         public void transformActive(final SparseVector from, final Function1 fn) {
            for(int i = 0; i < from.activeSize(); ++i) {
               scala.runtime.ScalaRunTime..MODULE$.array_update(from.data(), i, fn.apply(scala.runtime.ScalaRunTime..MODULE$.array_apply(from.data(), i)));
            }

         }

         public {
            this.evidence$28$1 = evidence$28$1;
            this.z = (Zero)scala.Predef..MODULE$.implicitly(evidence$27$1);
         }
      };
   }

   public CanMapKeyValuePairs canMapPairs(final ClassTag evidence$29, final Zero evidence$30) {
      return new CanMapKeyValuePairs(evidence$29, evidence$30) {
         private final ClassTag evidence$29$1;
         private final Zero evidence$30$1;

         public SparseVector map(final SparseVector from, final Function2 fn) {
            return SparseVector$.MODULE$.tabulate(from.length(), (i) -> $anonfun$map$2(fn, from, BoxesRunTime.unboxToInt(i)), this.evidence$29$1, this.evidence$30$1);
         }

         public SparseVector mapActive(final SparseVector from, final Function2 fn) {
            Object out = this.evidence$29$1.newArray(from.used());

            for(int i = 0; i < from.used(); ++i) {
               scala.runtime.ScalaRunTime..MODULE$.array_update(out, i, fn.apply(BoxesRunTime.boxToInteger(from.index()[i]), scala.runtime.ScalaRunTime..MODULE$.array_apply(from.data(), i)));
            }

            return new SparseVector((int[])scala.collection.ArrayOps..MODULE$.take$extension(scala.Predef..MODULE$.intArrayOps(from.index()), from.used()), out, from.used(), from.length(), this.evidence$30$1);
         }

         // $FF: synthetic method
         public static final Object $anonfun$map$2(final Function2 fn$2, final SparseVector from$3, final int i) {
            return fn$2.apply(BoxesRunTime.boxToInteger(i), from$3.apply(i));
         }

         public {
            this.evidence$29$1 = evidence$29$1;
            this.evidence$30$1 = evidence$30$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public UFunc.UImpl canDim() {
      return new UFunc.UImpl() {
         public double apply$mcDD$sp(final double v) {
            return UFunc.UImpl.apply$mcDD$sp$(this, v);
         }

         public float apply$mcDF$sp(final double v) {
            return UFunc.UImpl.apply$mcDF$sp$(this, v);
         }

         public int apply$mcDI$sp(final double v) {
            return UFunc.UImpl.apply$mcDI$sp$(this, v);
         }

         public double apply$mcFD$sp(final float v) {
            return UFunc.UImpl.apply$mcFD$sp$(this, v);
         }

         public float apply$mcFF$sp(final float v) {
            return UFunc.UImpl.apply$mcFF$sp$(this, v);
         }

         public int apply$mcFI$sp(final float v) {
            return UFunc.UImpl.apply$mcFI$sp$(this, v);
         }

         public double apply$mcID$sp(final int v) {
            return UFunc.UImpl.apply$mcID$sp$(this, v);
         }

         public float apply$mcIF$sp(final int v) {
            return UFunc.UImpl.apply$mcIF$sp$(this, v);
         }

         public int apply$mcII$sp(final int v) {
            return UFunc.UImpl.apply$mcII$sp$(this, v);
         }

         public int apply(final SparseVector v) {
            return v.size();
         }
      };
   }

   public MutableFiniteCoordinateField space(final Field evidence$31, final ClassTag evidence$32, final Zero evidence$33) {
      return MutableFiniteCoordinateField$.MODULE$.make(norm$.MODULE$.canNorm(HasOps$.MODULE$.canIterateValues_SV(), norm$.MODULE$.scalarNorm(evidence$31)), norm$.MODULE$.normDoubleToNormalNorm(norm$.MODULE$.canNorm(HasOps$.MODULE$.canIterateValues_SV(), norm$.MODULE$.scalarNorm(evidence$31))), evidence$31, HasOps$.MODULE$.impl_OpAdd_SV_S_eq_SV_Generic(evidence$31), HasOps$.MODULE$.impl_OpSub_SV_S_eq_SV_Generic(evidence$31), HasOps$.MODULE$.impl_Op_SV_SV_eq_SV_Generic_OpMulScalar(evidence$31), HasOps$.MODULE$.impl_Op_SV_SV_eq_SV_Generic(OpDiv$.MODULE$.opDivFromField(evidence$31), evidence$31), this.canCopySparse(evidence$32, evidence$33), HasOps$.MODULE$.castUpdateOps_V_S(this.scalarOf(), scala..less.colon.less..MODULE$.refl(), breeze.gymnastics.NotGiven..MODULE$.neq(), HasOps$.MODULE$.impl_Op_InPlace_V_S_Generic_OpMulScalar(evidence$31, evidence$32)), HasOps$.MODULE$.castUpdateOps_V_S(this.scalarOf(), scala..less.colon.less..MODULE$.refl(), breeze.gymnastics.NotGiven..MODULE$.neq(), HasOps$.MODULE$.impl_Op_InPlace_V_S_Generic_OpDiv(evidence$31, evidence$32)), HasOps$.MODULE$.impl_OpAdd_InPlace_T_U_Generic_from_scaleAdd_InPlace(HasOps$.MODULE$.impl_scaleAdd_SV_S_SV_InPlace_Generic(evidence$31, evidence$32), evidence$31), HasOps$.MODULE$.impl_OpSub_InPlace_T_U_Generic_from_scaleAdd_InPlace(HasOps$.MODULE$.impl_scaleAdd_SV_S_SV_InPlace_Generic(evidence$31, evidence$32), evidence$31), HasOps$.MODULE$.castUpdateOps_V_S(this.scalarOf(), scala..less.colon.less..MODULE$.refl(), breeze.gymnastics.NotGiven..MODULE$.neq(), HasOps$.MODULE$.impl_Op_InPlace_V_S_Generic_OpAdd(evidence$31, evidence$32)), HasOps$.MODULE$.castUpdateOps_V_S(this.scalarOf(), scala..less.colon.less..MODULE$.refl(), breeze.gymnastics.NotGiven..MODULE$.neq(), HasOps$.MODULE$.impl_Op_InPlace_V_S_Generic_OpSub(evidence$31, evidence$32)), HasOps$.MODULE$.castUpdateOps_V_V(scala..less.colon.less..MODULE$.refl(), scala..less.colon.less..MODULE$.refl(), breeze.gymnastics.NotGiven..MODULE$.neq(), HasOps$.MODULE$.impl_OpMulScalar_InPlace_V_V_Generic(evidence$31)), HasOps$.MODULE$.castUpdateOps_V_V(scala..less.colon.less..MODULE$.refl(), scala..less.colon.less..MODULE$.refl(), breeze.gymnastics.NotGiven..MODULE$.neq(), HasOps$.MODULE$.impl_OpDiv_InPlace_V_V_Generic(evidence$31)), HasOps$.MODULE$.impl_OpSet_InPlace_SV_SV_Generic(), HasOps$.MODULE$.impl_OpSet_InPlace_SV_S_Generic(evidence$33), HasOps$.MODULE$.impl_scaleAdd_SV_S_SV_InPlace_Generic(evidence$31, evidence$32), this.canCreateZerosLike(evidence$32, evidence$33), this.canCreateZeros(evidence$32, evidence$33), this.canDim(), HasOps$.MODULE$.impl_OpMulScalar_SV_S_eq_SV_Generic(evidence$31, evidence$32), HasOps$.MODULE$.impl_Op_SV_S_eq_SV_Generic(evidence$31, OpDiv$.MODULE$.opDivFromField(evidence$31)), HasOps$.MODULE$.implAddOp_SV_SV_eq_SV(evidence$31, evidence$32), HasOps$.MODULE$.implSubOp_SV_SV_eq_SV(evidence$31, evidence$32), HasOps$.MODULE$.impl_OpNeg_T_Generic_from_OpMulScalar(this.scalarOf(), evidence$31, HasOps$.MODULE$.impl_OpMulScalar_SV_S_eq_SV_Generic(evidence$31, evidence$32)), scala..less.colon.less..MODULE$.refl(), HasOps$.MODULE$.impl_OpMulInner_SV_SV_eq_T(evidence$32, evidence$33, evidence$31), HasOps$.MODULE$.zipMap(evidence$32, evidence$33, evidence$31), HasOps$.MODULE$.zipMapKV(evidence$32, evidence$33, evidence$31), HasOps$.MODULE$.canIterateValues_SV(), this.canMapValues(evidence$32, evidence$33), this.scalarOf());
   }

   public ScalarOf scalarOf() {
      return ScalarOf$.MODULE$.dummy();
   }

   public void breeze$linalg$SparseVector$$init() {
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparseVector$.class);
   }

   public SparseVector zeros$mDc$sp(final int size, final ClassTag evidence$1, final Zero evidence$2) {
      return new SparseVector$mcD$sp((int[]).MODULE$.empty(scala.reflect.ClassTag..MODULE$.Int()), (double[]).MODULE$.empty(evidence$1), 0, size, evidence$2);
   }

   public SparseVector zeros$mFc$sp(final int size, final ClassTag evidence$1, final Zero evidence$2) {
      return new SparseVector$mcF$sp((int[]).MODULE$.empty(scala.reflect.ClassTag..MODULE$.Int()), (float[]).MODULE$.empty(evidence$1), 0, size, evidence$2);
   }

   public SparseVector zeros$mIc$sp(final int size, final ClassTag evidence$1, final Zero evidence$2) {
      return new SparseVector$mcI$sp((int[]).MODULE$.empty(scala.reflect.ClassTag..MODULE$.Int()), (int[]).MODULE$.empty(evidence$1), 0, size, evidence$2);
   }

   public SparseVector zeros$mJc$sp(final int size, final ClassTag evidence$1, final Zero evidence$2) {
      return new SparseVector$mcJ$sp((int[]).MODULE$.empty(scala.reflect.ClassTag..MODULE$.Int()), (long[]).MODULE$.empty(evidence$1), 0, size, evidence$2);
   }

   public SparseVector apply$mDc$sp(final double[] values, final Zero evidence$3) {
      return new SparseVector$mcD$sp(.MODULE$.range(0, values.length), values, values.length, values.length, evidence$3);
   }

   public SparseVector apply$mFc$sp(final float[] values, final Zero evidence$3) {
      return new SparseVector$mcF$sp(.MODULE$.range(0, values.length), values, values.length, values.length, evidence$3);
   }

   public SparseVector apply$mIc$sp(final int[] values, final Zero evidence$3) {
      return new SparseVector$mcI$sp(.MODULE$.range(0, values.length), values, values.length, values.length, evidence$3);
   }

   public SparseVector apply$mJc$sp(final long[] values, final Zero evidence$3) {
      return new SparseVector$mcJ$sp(.MODULE$.range(0, values.length), values, values.length, values.length, evidence$3);
   }

   public SparseVector fill$mDc$sp(final int size, final Function0 v, final ClassTag evidence$6, final Zero evidence$7) {
      return this.apply$mDc$sp((double[]).MODULE$.fill(size, v, evidence$6), evidence$7);
   }

   public SparseVector fill$mFc$sp(final int size, final Function0 v, final ClassTag evidence$6, final Zero evidence$7) {
      return this.apply$mFc$sp((float[]).MODULE$.fill(size, v, evidence$6), evidence$7);
   }

   public SparseVector fill$mIc$sp(final int size, final Function0 v, final ClassTag evidence$6, final Zero evidence$7) {
      return this.apply$mIc$sp((int[]).MODULE$.fill(size, v, evidence$6), evidence$7);
   }

   public SparseVector fill$mJc$sp(final int size, final Function0 v, final ClassTag evidence$6, final Zero evidence$7) {
      return this.apply$mJc$sp((long[]).MODULE$.fill(size, v, evidence$6), evidence$7);
   }

   public SparseVector tabulate$mDc$sp(final int size, final Function1 f, final ClassTag evidence$8, final Zero evidence$9) {
      return this.apply$mDc$sp((double[]).MODULE$.tabulate(size, f, evidence$8), evidence$9);
   }

   public SparseVector tabulate$mFc$sp(final int size, final Function1 f, final ClassTag evidence$8, final Zero evidence$9) {
      return this.apply$mFc$sp((float[]).MODULE$.tabulate(size, f, evidence$8), evidence$9);
   }

   public SparseVector tabulate$mIc$sp(final int size, final Function1 f, final ClassTag evidence$8, final Zero evidence$9) {
      return this.apply$mIc$sp((int[]).MODULE$.tabulate(size, f, evidence$8), evidence$9);
   }

   public SparseVector tabulate$mJc$sp(final int size, final Function1 f, final ClassTag evidence$8, final Zero evidence$9) {
      return this.apply$mJc$sp((long[]).MODULE$.tabulate(size, f, evidence$8), evidence$9);
   }

   public SparseVector.CanCopySparseVector canCopySparse$mDc$sp(final ClassTag evidence$19, final Zero evidence$20) {
      return new SparseVector$CanCopySparseVector$mcD$sp(evidence$19, evidence$20);
   }

   public SparseVector.CanCopySparseVector canCopySparse$mFc$sp(final ClassTag evidence$19, final Zero evidence$20) {
      return new SparseVector$CanCopySparseVector$mcF$sp(evidence$19, evidence$20);
   }

   public SparseVector.CanCopySparseVector canCopySparse$mIc$sp(final ClassTag evidence$19, final Zero evidence$20) {
      return new SparseVector$CanCopySparseVector$mcI$sp(evidence$19, evidence$20);
   }

   public SparseVector.CanCopySparseVector canCopySparse$mJc$sp(final ClassTag evidence$19, final Zero evidence$20) {
      return new SparseVector$CanCopySparseVector$mcJ$sp(evidence$19, evidence$20);
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
   public static final void $anonfun$apply$2(final VectorBuilder b$1, final Tuple2 x$1) {
      if (x$1 != null) {
         int i = x$1._1$mcI$sp();
         Object v = x$1._2();
         b$1.add(i, v);
         BoxedUnit var2 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$horzcat$1(final Seq vectors$1, final SparseVector x$5) {
      return x$5.size() == ((Vector)vectors$1.apply(0)).size();
   }

   // $FF: synthetic method
   public static final int $anonfun$horzcat$2(final SparseVector x$6) {
      return x$6.length();
   }

   // $FF: synthetic method
   public static final int $anonfun$horzcat$3(final SparseVector x$7) {
      return x$7.activeSize();
   }

   private SparseVector$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
