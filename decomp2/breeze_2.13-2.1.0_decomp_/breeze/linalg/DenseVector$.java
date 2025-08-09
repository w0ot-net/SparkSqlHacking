package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.operators.HasOps$;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanCreateZeros;
import breeze.linalg.support.CanCreateZerosLike;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.ScalarOf;
import breeze.linalg.support.ScalarOf$;
import breeze.math.Field;
import breeze.math.MutableFiniteCoordinateField;
import breeze.math.MutableFiniteCoordinateField$;
import breeze.math.Ring$;
import breeze.math.Semiring;
import breeze.stats.distributions.Rand;
import breeze.storage.Zero;
import breeze.storage.Zero$;
import breeze.util.ArrayUtil$;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.IterableOps;
import scala.collection.immutable.Range;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuilder;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ModuleSerializationProxy;

public final class DenseVector$ implements VectorConstructors, Serializable {
   public static final DenseVector$ MODULE$ = new DenseVector$();
   private static final DenseVector.CanZipMapValuesDenseVector zipMap_d;
   private static final DenseVector.CanZipMapValuesDenseVector zipMap_f;
   private static final DenseVector.CanZipMapValuesDenseVector zipMap_i;
   private static final MutableFiniteCoordinateField space_Double;
   private static final MutableFiniteCoordinateField space_Float;
   private static final MutableFiniteCoordinateField space_Int;
   private static final MutableFiniteCoordinateField space_Long;

   static {
      VectorConstructors.$init$(MODULE$);
      zipMap_d = new DenseVector$CanZipMapValuesDenseVector$mcDD$sp(.MODULE$.Double());
      zipMap_f = new DenseVector.CanZipMapValuesDenseVector(.MODULE$.Float());
      zipMap_i = new DenseVector$CanZipMapValuesDenseVector$mcII$sp(.MODULE$.Int());
      space_Double = MutableFiniteCoordinateField$.MODULE$.make(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Double()), norm$.MODULE$.normDoubleToNormalNorm(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Double())), Field.fieldDouble$.MODULE$, HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpAdd(), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpSub(), HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Double_OpMulScalar(), HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Double_OpDiv(), MODULE$.canCopyDenseVector(.MODULE$.Double()), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpMulScalar(), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpDiv(), HasOps$.MODULE$.impl_OpAdd_InPlace_DV_DV_Double(), HasOps$.MODULE$.impl_OpSub_InPlace_DV_DV_Double(), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpAdd(), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpSub(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpMulScalar(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpDiv(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet(), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpSet(), HasOps$.MODULE$.impl_scaleAdd_InPlace_DV_T_DV_Double(), MODULE$.canCreateZerosLike(.MODULE$.Double(), Zero$.MODULE$.DoubleZero()), MODULE$.canCreateZeros(.MODULE$.Double(), Zero$.MODULE$.DoubleZero()), HasOps$.MODULE$.impl_dim_DV_eq_I(), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulScalar(), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpDiv(), HasOps$.MODULE$.impl_OpAdd_DV_DV_eq_DV_Double(), HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double(), HasOps$.MODULE$.impl_OpNeg_T_Generic_from_OpMulScalar(MODULE$.DV_scalarOf(), Ring$.MODULE$.ringD(), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulScalar()), scala..less.colon.less..MODULE$.refl(), HasOps$.MODULE$.canDotD(), MODULE$.zipMap_d(), MODULE$.zipMapKV(.MODULE$.Double()), HasOps$.MODULE$.DV_canIterateValues(), MODULE$.DV_canMapValues$mDDc$sp(.MODULE$.Double()), MODULE$.DV_scalarOf());
      space_Float = MutableFiniteCoordinateField$.MODULE$.make(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Float()), norm$.MODULE$.normDoubleToNormalNorm(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Float())), Field.fieldFloat$.MODULE$, HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Float_OpAdd(), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Float_OpSub(), HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Float_OpMulScalar(), HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Float_OpDiv(), MODULE$.canCopyDenseVector(.MODULE$.Float()), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Float_OpMulScalar(), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Float_OpDiv(), HasOps$.MODULE$.impl_OpAdd_InPlace_DV_DV_Float(), HasOps$.MODULE$.impl_OpSub_InPlace_DV_DV_Float(), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Float_OpAdd(), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Float_OpSub(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Float_OpMulScalar(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Float_OpDiv(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Float_OpSet(), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Float_OpSet(), HasOps$.MODULE$.impl_scaledAdd_InPlace_DV_S_DV_Float(), MODULE$.canCreateZerosLike(.MODULE$.Float(), Zero$.MODULE$.FloatZero()), MODULE$.canCreateZeros(.MODULE$.Float(), Zero$.MODULE$.FloatZero()), HasOps$.MODULE$.impl_dim_DV_eq_I(), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Float_OpMulScalar(), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Float_OpDiv(), HasOps$.MODULE$.impl_OpAdd_DV_DV_eq_DV_Float(), HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Float(), HasOps$.MODULE$.impl_OpNeg_T_Generic_from_OpMulScalar(MODULE$.DV_scalarOf(), Ring$.MODULE$.ringFloat(), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Float_OpMulScalar()), scala..less.colon.less..MODULE$.refl(), HasOps$.MODULE$.impl_OpMulInner_DV_DV_eq_S_Float(), MODULE$.zipMap_f(), MODULE$.zipMapKV(.MODULE$.Float()), HasOps$.MODULE$.DV_canIterateValues(), MODULE$.DV_canMapValues$mFFc$sp(.MODULE$.Float()), MODULE$.DV_scalarOf());
      space_Int = MutableFiniteCoordinateField$.MODULE$.make(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Int()), norm$.MODULE$.normDoubleToNormalNorm(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Int())), Field.fieldInt$.MODULE$, HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Int_OpAdd(), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Int_OpSub(), HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Int_OpMulScalar(), HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Int_OpDiv(), MODULE$.canCopyDenseVector(.MODULE$.Int()), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Int_OpMulScalar(), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Int_OpDiv(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Int_OpAdd(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Int_OpSub(), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Int_OpAdd(), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Int_OpSub(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Int_OpMulScalar(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Int_OpDiv(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Int_OpSet(), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Int_OpSet(), HasOps$.MODULE$.impl_scaleAdd_InPlace_DV_S_DV_Int(), MODULE$.canCreateZerosLike(.MODULE$.Int(), Zero$.MODULE$.IntZero()), MODULE$.canCreateZeros(.MODULE$.Int(), Zero$.MODULE$.IntZero()), HasOps$.MODULE$.impl_dim_DV_eq_I(), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Int_OpMulScalar(), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Int_OpDiv(), HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Int_OpAdd(), HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Int_OpSub(), HasOps$.MODULE$.impl_OpNeg_T_Generic_from_OpMulScalar(MODULE$.DV_scalarOf(), Ring$.MODULE$.ringInt(), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Int_OpMulScalar()), scala..less.colon.less..MODULE$.refl(), HasOps$.MODULE$.impl_OpMulInner_DV_DV_eq_S_Int(), MODULE$.zipMap_i(), MODULE$.zipMapKV(.MODULE$.Int()), HasOps$.MODULE$.DV_canIterateValues(), MODULE$.DV_canMapValues$mIIc$sp(.MODULE$.Int()), MODULE$.DV_scalarOf());
      space_Long = MutableFiniteCoordinateField$.MODULE$.make(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Long()), norm$.MODULE$.normDoubleToNormalNorm(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Long())), Field.fieldLong$.MODULE$, HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Long_OpAdd(), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Long_OpSub(), HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Long_OpMulScalar(), HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Long_OpDiv(), MODULE$.canCopyDenseVector(.MODULE$.Long()), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Long_OpMulScalar(), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Long_OpDiv(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Long_OpAdd(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Long_OpSub(), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Long_OpAdd(), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Long_OpSub(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Long_OpMulScalar(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Long_OpDiv(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Long_OpSet(), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Long_OpSet(), HasOps$.MODULE$.impl_scaleAdd_InPlace_DV_S_DV_Long(), MODULE$.canCreateZerosLike(.MODULE$.Long(), Zero$.MODULE$.LongZero()), MODULE$.canCreateZeros(.MODULE$.Long(), Zero$.MODULE$.LongZero()), HasOps$.MODULE$.impl_dim_DV_eq_I(), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Long_OpMulScalar(), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Long_OpDiv(), HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Long_OpAdd(), HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Long_OpSub(), HasOps$.MODULE$.impl_OpNeg_T_Generic_from_OpMulScalar(MODULE$.DV_scalarOf(), Ring$.MODULE$.ringLong(), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Long_OpMulScalar()), scala..less.colon.less..MODULE$.refl(), HasOps$.MODULE$.impl_OpMulInner_DV_DV_eq_S_Long(), MODULE$.zipMap(.MODULE$.Long()), MODULE$.zipMapKV(.MODULE$.Long()), HasOps$.MODULE$.DV_canIterateValues(), MODULE$.DV_canMapValues(.MODULE$.Long()), MODULE$.DV_scalarOf());
   }

   public Vector apply(final Seq values, final ClassTag evidence$8) {
      return VectorConstructors.apply$(this, values, evidence$8);
   }

   public CanCreateZeros canCreateZeros(final ClassTag evidence$9, final Zero evidence$10) {
      return VectorConstructors.canCreateZeros$(this, evidence$9, evidence$10);
   }

   public Vector rand(final int size, final Rand rand, final ClassTag evidence$11) {
      return VectorConstructors.rand$(this, size, rand, evidence$11);
   }

   public Rand rand$default$2() {
      return VectorConstructors.rand$default$2$(this);
   }

   public Vector range(final int start, final int end) {
      return VectorConstructors.range$(this, start, end);
   }

   public Vector range(final int start, final int end, final int step) {
      return VectorConstructors.range$(this, start, end, step);
   }

   public Vector rangeF(final float start, final float end, final float step) {
      return VectorConstructors.rangeF$(this, start, end, step);
   }

   public float rangeF$default$3() {
      return VectorConstructors.rangeF$default$3$(this);
   }

   public Vector rangeD(final double start, final double end, final double step) {
      return VectorConstructors.rangeD$(this, start, end, step);
   }

   public double rangeD$default$3() {
      return VectorConstructors.rangeD$default$3$(this);
   }

   public DenseVector zeros(final int size, final ClassTag evidence$1, final Zero evidence$2) {
      Object data = evidence$1.newArray(size);
      if (size != 0 && !BoxesRunTime.equals(scala.runtime.ScalaRunTime..MODULE$.array_apply(data, 0), ((Zero)scala.Predef..MODULE$.implicitly(evidence$2)).zero())) {
         ArrayUtil$.MODULE$.fill(data, 0, scala.runtime.ScalaRunTime..MODULE$.array_length(data), ((Zero)scala.Predef..MODULE$.implicitly(evidence$2)).zero());
      }

      return this.apply(data);
   }

   public DenseVector apply(final Object values) {
      Object var2;
      if (values instanceof double[]) {
         double[] var4 = (double[])values;
         var2 = new DenseVector$mcD$sp(var4);
      } else if (values instanceof float[]) {
         float[] var5 = (float[])values;
         var2 = new DenseVector$mcF$sp(var5);
      } else if (values instanceof int[]) {
         int[] var6 = (int[])values;
         var2 = new DenseVector$mcI$sp(var6);
      } else if (values instanceof long[]) {
         long[] var7 = (long[])values;
         var2 = new DenseVector$mcJ$sp(var7);
      } else {
         var2 = new DenseVector(values);
      }

      return (DenseVector)var2;
   }

   public DenseVector tabulate(final int size, final Function1 f, final ClassTag evidence$3) {
      ArrayBuilder b = scala.collection.mutable.ArrayBuilder..MODULE$.make(evidence$3);
      b.sizeHint(size);

      for(int i = 0; i < size; ++i) {
         b.$plus$eq(f.apply(BoxesRunTime.boxToInteger(i)));
      }

      return this.apply(b.result());
   }

   public DenseVector tabulate(final Range range, final Function1 f, final ClassTag evidence$4) {
      ArrayBuilder b = scala.collection.mutable.ArrayBuilder..MODULE$.make(evidence$4);
      b.sizeHint(range.length());

      for(int i = 0; i < range.length(); ++i) {
         b.$plus$eq(f.apply(BoxesRunTime.boxToInteger(range.apply$mcII$sp(i))));
      }

      return this.apply(b.result());
   }

   public DenseVector fill(final int size, final Function0 v, final ClassTag evidence$5) {
      return this.apply(scala.Array..MODULE$.fill(size, v, evidence$5));
   }

   public DenseVector create(final Object data, final int offset, final int stride, final int length) {
      Object var5;
      if (data instanceof double[]) {
         double[] var7 = (double[])data;
         var5 = new DenseVector$mcD$sp(var7, offset, stride, length);
      } else if (data instanceof float[]) {
         float[] var8 = (float[])data;
         var5 = new DenseVector$mcF$sp(var8, offset, stride, length);
      } else if (data instanceof int[]) {
         int[] var9 = (int[])data;
         var5 = new DenseVector$mcI$sp(var9, offset, stride, length);
      } else if (data instanceof long[]) {
         long[] var10 = (long[])data;
         var5 = new DenseVector$mcJ$sp(var10, offset, stride, length);
      } else {
         var5 = new DenseVector(data, offset, stride, length);
      }

      return (DenseVector)var5;
   }

   public DenseVector ones(final int size, final ClassTag evidence$6, final Semiring evidence$7) {
      return this.fill(size, ((Semiring)scala.Predef..MODULE$.implicitly(evidence$7)).one(), evidence$6, evidence$7);
   }

   public DenseVector fill(final int size, final Object v, final ClassTag evidence$8, final Semiring evidence$9) {
      DenseVector r = this.apply(evidence$8.newArray(size));
      int left$macro$1 = r.stride();
      int right$macro$2 = 1;
      if (left$macro$1 != 1) {
         throw new AssertionError((new StringBuilder(49)).append("assertion failed: ").append("r.stride == 1 (").append(left$macro$1).append(" ").append("!=").append(" ").append(1).append(")").toString());
      } else {
         ArrayUtil$.MODULE$.fill(r.data(), r.offset(), r.length(), v);
         return r;
      }
   }

   public DenseMatrix horzcat(final Seq vectors, final ClassTag evidence$10, final Zero evidence$11) {
      int size = ((Vector)vectors.head()).size();
      if (!vectors.forall((x$1) -> BoxesRunTime.boxToBoolean($anonfun$horzcat$1(size, x$1)))) {
         throw new IllegalArgumentException("All vectors must have the same size!");
      } else {
         DenseMatrix result = DenseMatrix$.MODULE$.zeros(size, vectors.size(), evidence$10, evidence$11);
         ((IterableOps)vectors.zipWithIndex()).withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$horzcat$2(check$ifrefutable$1))).foreach((x$2) -> {
            if (x$2 != null) {
               DenseVector v = (DenseVector)x$2._1();
               int col = x$2._2$mcI$sp();
               DenseVector var2 = (DenseVector)((NumericOps)result.apply(scala.package..MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(col), HasOps$.MODULE$.canSliceCol())).$colon$eq(v, HasOps$.MODULE$.impl_OpSet_InPlace_DV_DV());
               return var2;
            } else {
               throw new MatchError(x$2);
            }
         });
         return result;
      }
   }

   public DenseVector vertcat(final Seq vectors, final UFunc.InPlaceImpl2 canSet, final ClassTag vman, final Zero zero) {
      int size = BoxesRunTime.unboxToInt(vectors.foldLeft(BoxesRunTime.boxToInteger(0), (x$3, x$4) -> BoxesRunTime.boxToInteger($anonfun$vertcat$1(BoxesRunTime.unboxToInt(x$3), x$4))));
      DenseVector result = this.zeros(size, vman, zero);
      IntRef offset = IntRef.create(0);
      vectors.foreach((v) -> {
         $anonfun$vertcat$2(result, offset, canSet, v);
         return BoxedUnit.UNIT;
      });
      return result;
   }

   public CanCreateZerosLike canCreateZerosLike(final ClassTag evidence$12, final Zero evidence$13) {
      return new CanCreateZerosLike(evidence$12, evidence$13) {
         private final ClassTag evidence$12$1;
         private final Zero evidence$13$1;

         public DenseVector apply(final DenseVector v1) {
            return DenseVector$.MODULE$.zeros(v1.length(), this.evidence$12$1, this.evidence$13$1);
         }

         public {
            this.evidence$12$1 = evidence$12$1;
            this.evidence$13$1 = evidence$13$1;
         }
      };
   }

   public CanCopy canCopyDenseVector(final ClassTag evidence$14) {
      return DenseVectorDeps$.MODULE$.canCopyDenseVector(evidence$14);
   }

   public CanMapValues DV_canMapValues(final ClassTag man) {
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

         public DenseVector map(final DenseVector from, final Function1 fn) {
            Object out = this.man$1.newArray(from.length());
            if (from.noOffsetOrStride()) {
               this.fastestPath(out, fn, from.data());
            } else if (from.stride() == 1) {
               this.mediumPath(out, fn, from.data(), from.offset());
            } else {
               this.slowPath(out, fn, from.data(), from.offset(), from.stride());
            }

            return DenseVector$.MODULE$.apply(out);
         }

         private void mediumPath(final Object out, final Function1 fn, final Object data, final int off) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = scala.runtime.ScalaRunTime..MODULE$.array_length(out); index$macro$2 < limit$macro$4; ++index$macro$2) {
               scala.runtime.ScalaRunTime..MODULE$.array_update(out, index$macro$2, fn.apply(scala.runtime.ScalaRunTime..MODULE$.array_apply(data, index$macro$2 + off)));
            }

         }

         private void fastestPath(final Object out, final Function1 fn, final Object data) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = scala.runtime.ScalaRunTime..MODULE$.array_length(out); index$macro$2 < limit$macro$4; ++index$macro$2) {
               scala.runtime.ScalaRunTime..MODULE$.array_update(out, index$macro$2, fn.apply(scala.runtime.ScalaRunTime..MODULE$.array_apply(data, index$macro$2)));
            }

         }

         private final void slowPath(final Object out, final Function1 fn, final Object data, final int off, final int stride) {
            int i = 0;

            for(int j = off; i < scala.runtime.ScalaRunTime..MODULE$.array_length(out); j += stride) {
               scala.runtime.ScalaRunTime..MODULE$.array_update(out, i, fn.apply(scala.runtime.ScalaRunTime..MODULE$.array_apply(data, j)));
               ++i;
            }

         }

         public {
            this.man$1 = man$1;
            CanMapValues.DenseCanMapValues.$init$(this);
         }
      };
   }

   public ScalarOf DV_scalarOf() {
      return ScalarOf$.MODULE$.dummy();
   }

   public DenseVector.CanZipMapValuesDenseVector zipMap(final ClassTag evidence$16) {
      return new DenseVector.CanZipMapValuesDenseVector(evidence$16);
   }

   public DenseVector.CanZipMapValuesDenseVector zipMap_d() {
      return zipMap_d;
   }

   public DenseVector.CanZipMapValuesDenseVector zipMap_f() {
      return zipMap_f;
   }

   public DenseVector.CanZipMapValuesDenseVector zipMap_i() {
      return zipMap_i;
   }

   public DenseVector.CanZipMapKeyValuesDenseVector zipMapKV(final ClassTag evidence$18) {
      return new DenseVector.CanZipMapKeyValuesDenseVector(evidence$18);
   }

   public MutableFiniteCoordinateField space(final Field field, final ClassTag man) {
      CanMapValues cmv = this.DV_canMapValues(man);
      return MutableFiniteCoordinateField$.MODULE$.make(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), field.normImpl()), norm$.MODULE$.normDoubleToNormalNorm(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), field.normImpl())), field, HasOps$.MODULE$.pureFromUpdate(HasOps$.MODULE$.impl_OpAdd_InPlace_DV_S_Generic(field), this.canCopyDenseVector(man)), HasOps$.MODULE$.pureFromUpdate(HasOps$.MODULE$.impl_OpSub_InPlace_DV_S_Generic(field), this.canCopyDenseVector(man)), HasOps$.MODULE$.pureFromUpdate(HasOps$.MODULE$.impl_OpMulScalar_InPlace_DV_DV_Generic(field), this.canCopyDenseVector(man)), HasOps$.MODULE$.pureFromUpdate(HasOps$.MODULE$.impl_OpDiv_InPlace_DV_DV_Generic(field), this.canCopyDenseVector(man)), this.canCopyDenseVector(man), HasOps$.MODULE$.impl_OpMulScalar_InPlace_DV_S_Generic(field), HasOps$.MODULE$.impl_OpDiv_InPlace_DV_S_Generic(field), HasOps$.MODULE$.impl_OpAdd_InPlace_DV_DV_Generic(field), HasOps$.MODULE$.impl_OpSub_InPlace_DV_DV_Generic(field), HasOps$.MODULE$.impl_OpAdd_InPlace_DV_S_Generic(field), HasOps$.MODULE$.impl_OpSub_InPlace_DV_S_Generic(field), HasOps$.MODULE$.impl_OpMulScalar_InPlace_DV_DV_Generic(field), HasOps$.MODULE$.impl_OpDiv_InPlace_DV_DV_Generic(field), HasOps$.MODULE$.impl_OpSet_InPlace_DV_DV(), HasOps$.MODULE$.impl_OpSet_InPlace_DV_V_Generic(), HasOps$.MODULE$.impl_scaleAdd_InPlace_DV_S_DV_Generic(field), this.canCreateZerosLike(man, Zero$.MODULE$.zeroFromSemiring(field)), this.canCreateZeros(man, Zero$.MODULE$.zeroFromSemiring(field)), HasOps$.MODULE$.impl_dim_DV_eq_I(), HasOps$.MODULE$.pureFromUpdate(HasOps$.MODULE$.impl_OpMulScalar_InPlace_DV_S_Generic(field), this.canCopyDenseVector(man)), HasOps$.MODULE$.pureFromUpdate(HasOps$.MODULE$.impl_OpDiv_InPlace_DV_S_Generic(field), this.canCopyDenseVector(man)), HasOps$.MODULE$.pureFromUpdate(HasOps$.MODULE$.impl_OpAdd_InPlace_DV_DV_Generic(field), this.canCopyDenseVector(man)), HasOps$.MODULE$.pureFromUpdate(HasOps$.MODULE$.impl_OpSub_InPlace_DV_DV_Generic(field), this.canCopyDenseVector(man)), HasOps$.MODULE$.impl_OpNeg_T_Generic_from_OpMulScalar(this.DV_scalarOf(), field, HasOps$.MODULE$.pureFromUpdate(HasOps$.MODULE$.impl_OpMulScalar_InPlace_DV_S_Generic(field), this.canCopyDenseVector(man))), scala..less.colon.less..MODULE$.refl(), HasOps$.MODULE$.impl_OpMulInner_DV_DV_eq_S_Generic(field), this.zipMap(man), this.zipMapKV(man), HasOps$.MODULE$.DV_canIterateValues(), cmv, this.DV_scalarOf());
   }

   public MutableFiniteCoordinateField space_Double() {
      return space_Double;
   }

   public MutableFiniteCoordinateField space_Float() {
      return space_Float;
   }

   public MutableFiniteCoordinateField space_Int() {
      return space_Int;
   }

   public MutableFiniteCoordinateField space_Long() {
      return space_Long;
   }

   public void breeze$linalg$DenseVector$$init() {
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DenseVector$.class);
   }

   public DenseVector zeros$mDc$sp(final int size, final ClassTag evidence$1, final Zero evidence$2) {
      double[] data = (double[])evidence$1.newArray(size);
      if (size != 0 && data[0] != ((Zero)scala.Predef..MODULE$.implicitly(evidence$2)).zero$mcD$sp()) {
         ArrayUtil$.MODULE$.fill(data, 0, data.length, BoxesRunTime.boxToDouble(((Zero)scala.Predef..MODULE$.implicitly(evidence$2)).zero$mcD$sp()));
      }

      return this.apply$mDc$sp(data);
   }

   public DenseVector zeros$mFc$sp(final int size, final ClassTag evidence$1, final Zero evidence$2) {
      float[] data = (float[])evidence$1.newArray(size);
      if (size != 0 && data[0] != ((Zero)scala.Predef..MODULE$.implicitly(evidence$2)).zero$mcF$sp()) {
         ArrayUtil$.MODULE$.fill(data, 0, data.length, BoxesRunTime.boxToFloat(((Zero)scala.Predef..MODULE$.implicitly(evidence$2)).zero$mcF$sp()));
      }

      return this.apply$mFc$sp(data);
   }

   public DenseVector zeros$mIc$sp(final int size, final ClassTag evidence$1, final Zero evidence$2) {
      int[] data = (int[])evidence$1.newArray(size);
      if (size != 0 && data[0] != ((Zero)scala.Predef..MODULE$.implicitly(evidence$2)).zero$mcI$sp()) {
         ArrayUtil$.MODULE$.fill(data, 0, data.length, BoxesRunTime.boxToInteger(((Zero)scala.Predef..MODULE$.implicitly(evidence$2)).zero$mcI$sp()));
      }

      return this.apply$mIc$sp(data);
   }

   public DenseVector zeros$mJc$sp(final int size, final ClassTag evidence$1, final Zero evidence$2) {
      long[] data = (long[])evidence$1.newArray(size);
      if (size != 0 && data[0] != ((Zero)scala.Predef..MODULE$.implicitly(evidence$2)).zero$mcJ$sp()) {
         ArrayUtil$.MODULE$.fill(data, 0, data.length, BoxesRunTime.boxToLong(((Zero)scala.Predef..MODULE$.implicitly(evidence$2)).zero$mcJ$sp()));
      }

      return this.apply$mJc$sp(data);
   }

   public DenseVector apply$mDc$sp(final double[] values) {
      Object var2;
      if (values instanceof double[]) {
         double[] var4 = values;
         var2 = new DenseVector$mcD$sp(var4);
      } else if (values instanceof float[]) {
         float[] var5 = (float[])values;
         var2 = new DenseVector$mcF$sp(var5);
      } else if (values instanceof int[]) {
         int[] var6 = (int[])values;
         var2 = new DenseVector$mcI$sp(var6);
      } else if (values instanceof long[]) {
         long[] var7 = (long[])values;
         var2 = new DenseVector$mcJ$sp(var7);
      } else {
         var2 = new DenseVector$mcD$sp(values);
      }

      return (DenseVector)var2;
   }

   public DenseVector apply$mFc$sp(final float[] values) {
      Object var2;
      if (values instanceof double[]) {
         double[] var4 = (double[])values;
         var2 = new DenseVector$mcD$sp(var4);
      } else if (values instanceof float[]) {
         float[] var5 = values;
         var2 = new DenseVector$mcF$sp(var5);
      } else if (values instanceof int[]) {
         int[] var6 = (int[])values;
         var2 = new DenseVector$mcI$sp(var6);
      } else if (values instanceof long[]) {
         long[] var7 = (long[])values;
         var2 = new DenseVector$mcJ$sp(var7);
      } else {
         var2 = new DenseVector$mcF$sp(values);
      }

      return (DenseVector)var2;
   }

   public DenseVector apply$mIc$sp(final int[] values) {
      Object var2;
      if (values instanceof double[]) {
         double[] var4 = (double[])values;
         var2 = new DenseVector$mcD$sp(var4);
      } else if (values instanceof float[]) {
         float[] var5 = (float[])values;
         var2 = new DenseVector$mcF$sp(var5);
      } else if (values instanceof int[]) {
         int[] var6 = values;
         var2 = new DenseVector$mcI$sp(var6);
      } else if (values instanceof long[]) {
         long[] var7 = (long[])values;
         var2 = new DenseVector$mcJ$sp(var7);
      } else {
         var2 = new DenseVector$mcI$sp(values);
      }

      return (DenseVector)var2;
   }

   public DenseVector apply$mJc$sp(final long[] values) {
      Object var2;
      if (values instanceof double[]) {
         double[] var4 = (double[])values;
         var2 = new DenseVector$mcD$sp(var4);
      } else if (values instanceof float[]) {
         float[] var5 = (float[])values;
         var2 = new DenseVector$mcF$sp(var5);
      } else if (values instanceof int[]) {
         int[] var6 = (int[])values;
         var2 = new DenseVector$mcI$sp(var6);
      } else if (values instanceof long[]) {
         long[] var7 = values;
         var2 = new DenseVector$mcJ$sp(var7);
      } else {
         var2 = new DenseVector$mcJ$sp(values);
      }

      return (DenseVector)var2;
   }

   public DenseVector tabulate$mDc$sp(final int size, final Function1 f, final ClassTag evidence$3) {
      ArrayBuilder b = scala.collection.mutable.ArrayBuilder..MODULE$.make(evidence$3);
      b.sizeHint(size);

      for(int i = 0; i < size; ++i) {
         b.$plus$eq(BoxesRunTime.boxToDouble(f.apply$mcDI$sp(i)));
      }

      return this.apply$mDc$sp((double[])b.result());
   }

   public DenseVector tabulate$mFc$sp(final int size, final Function1 f, final ClassTag evidence$3) {
      ArrayBuilder b = scala.collection.mutable.ArrayBuilder..MODULE$.make(evidence$3);
      b.sizeHint(size);

      for(int i = 0; i < size; ++i) {
         b.$plus$eq(BoxesRunTime.boxToFloat(f.apply$mcFI$sp(i)));
      }

      return this.apply$mFc$sp((float[])b.result());
   }

   public DenseVector tabulate$mIc$sp(final int size, final Function1 f, final ClassTag evidence$3) {
      ArrayBuilder b = scala.collection.mutable.ArrayBuilder..MODULE$.make(evidence$3);
      b.sizeHint(size);

      for(int i = 0; i < size; ++i) {
         b.$plus$eq(BoxesRunTime.boxToInteger(f.apply$mcII$sp(i)));
      }

      return this.apply$mIc$sp((int[])b.result());
   }

   public DenseVector tabulate$mJc$sp(final int size, final Function1 f, final ClassTag evidence$3) {
      ArrayBuilder b = scala.collection.mutable.ArrayBuilder..MODULE$.make(evidence$3);
      b.sizeHint(size);

      for(int i = 0; i < size; ++i) {
         b.$plus$eq(BoxesRunTime.boxToLong(f.apply$mcJI$sp(i)));
      }

      return this.apply$mJc$sp((long[])b.result());
   }

   public DenseVector tabulate$mDc$sp(final Range range, final Function1 f, final ClassTag evidence$4) {
      ArrayBuilder b = scala.collection.mutable.ArrayBuilder..MODULE$.make(evidence$4);
      b.sizeHint(range.length());

      for(int i = 0; i < range.length(); ++i) {
         b.$plus$eq(BoxesRunTime.boxToDouble(f.apply$mcDI$sp(range.apply$mcII$sp(i))));
      }

      return this.apply$mDc$sp((double[])b.result());
   }

   public DenseVector tabulate$mFc$sp(final Range range, final Function1 f, final ClassTag evidence$4) {
      ArrayBuilder b = scala.collection.mutable.ArrayBuilder..MODULE$.make(evidence$4);
      b.sizeHint(range.length());

      for(int i = 0; i < range.length(); ++i) {
         b.$plus$eq(BoxesRunTime.boxToFloat(f.apply$mcFI$sp(range.apply$mcII$sp(i))));
      }

      return this.apply$mFc$sp((float[])b.result());
   }

   public DenseVector tabulate$mIc$sp(final Range range, final Function1 f, final ClassTag evidence$4) {
      ArrayBuilder b = scala.collection.mutable.ArrayBuilder..MODULE$.make(evidence$4);
      b.sizeHint(range.length());

      for(int i = 0; i < range.length(); ++i) {
         b.$plus$eq(BoxesRunTime.boxToInteger(f.apply$mcII$sp(range.apply$mcII$sp(i))));
      }

      return this.apply$mIc$sp((int[])b.result());
   }

   public DenseVector tabulate$mJc$sp(final Range range, final Function1 f, final ClassTag evidence$4) {
      ArrayBuilder b = scala.collection.mutable.ArrayBuilder..MODULE$.make(evidence$4);
      b.sizeHint(range.length());

      for(int i = 0; i < range.length(); ++i) {
         b.$plus$eq(BoxesRunTime.boxToLong(f.apply$mcJI$sp(range.apply$mcII$sp(i))));
      }

      return this.apply$mJc$sp((long[])b.result());
   }

   public DenseVector fill$mDc$sp(final int size, final Function0 v, final ClassTag evidence$5) {
      return this.apply$mDc$sp((double[])scala.Array..MODULE$.fill(size, v, evidence$5));
   }

   public DenseVector fill$mFc$sp(final int size, final Function0 v, final ClassTag evidence$5) {
      return this.apply$mFc$sp((float[])scala.Array..MODULE$.fill(size, v, evidence$5));
   }

   public DenseVector fill$mIc$sp(final int size, final Function0 v, final ClassTag evidence$5) {
      return this.apply$mIc$sp((int[])scala.Array..MODULE$.fill(size, v, evidence$5));
   }

   public DenseVector fill$mJc$sp(final int size, final Function0 v, final ClassTag evidence$5) {
      return this.apply$mJc$sp((long[])scala.Array..MODULE$.fill(size, v, evidence$5));
   }

   public DenseVector ones$mDc$sp(final int size, final ClassTag evidence$6, final Semiring evidence$7) {
      return this.fill$mDc$sp(size, ((Semiring)scala.Predef..MODULE$.implicitly(evidence$7)).one$mcD$sp(), evidence$6, evidence$7);
   }

   public DenseVector ones$mFc$sp(final int size, final ClassTag evidence$6, final Semiring evidence$7) {
      return this.fill$mFc$sp(size, ((Semiring)scala.Predef..MODULE$.implicitly(evidence$7)).one$mcF$sp(), evidence$6, evidence$7);
   }

   public DenseVector ones$mIc$sp(final int size, final ClassTag evidence$6, final Semiring evidence$7) {
      return this.fill$mIc$sp(size, ((Semiring)scala.Predef..MODULE$.implicitly(evidence$7)).one$mcI$sp(), evidence$6, evidence$7);
   }

   public DenseVector ones$mJc$sp(final int size, final ClassTag evidence$6, final Semiring evidence$7) {
      return this.fill$mJc$sp(size, ((Semiring)scala.Predef..MODULE$.implicitly(evidence$7)).one$mcJ$sp(), evidence$6, evidence$7);
   }

   public DenseVector fill$mDc$sp(final int size, final double v, final ClassTag evidence$8, final Semiring evidence$9) {
      DenseVector r = this.apply$mDc$sp((double[])evidence$8.newArray(size));
      int left$macro$1 = r.stride();
      int right$macro$2 = 1;
      if (left$macro$1 != 1) {
         throw new AssertionError((new StringBuilder(49)).append("assertion failed: ").append("r.stride == 1 (").append(left$macro$1).append(" ").append("!=").append(" ").append(1).append(")").toString());
      } else {
         ArrayUtil$.MODULE$.fill(r.data$mcD$sp(), r.offset(), r.length(), BoxesRunTime.boxToDouble(v));
         return r;
      }
   }

   public DenseVector fill$mFc$sp(final int size, final float v, final ClassTag evidence$8, final Semiring evidence$9) {
      DenseVector r = this.apply$mFc$sp((float[])evidence$8.newArray(size));
      int left$macro$1 = r.stride();
      int right$macro$2 = 1;
      if (left$macro$1 != 1) {
         throw new AssertionError((new StringBuilder(49)).append("assertion failed: ").append("r.stride == 1 (").append(left$macro$1).append(" ").append("!=").append(" ").append(1).append(")").toString());
      } else {
         ArrayUtil$.MODULE$.fill(r.data$mcF$sp(), r.offset(), r.length(), BoxesRunTime.boxToFloat(v));
         return r;
      }
   }

   public DenseVector fill$mIc$sp(final int size, final int v, final ClassTag evidence$8, final Semiring evidence$9) {
      DenseVector r = this.apply$mIc$sp((int[])evidence$8.newArray(size));
      int left$macro$1 = r.stride();
      int right$macro$2 = 1;
      if (left$macro$1 != 1) {
         throw new AssertionError((new StringBuilder(49)).append("assertion failed: ").append("r.stride == 1 (").append(left$macro$1).append(" ").append("!=").append(" ").append(1).append(")").toString());
      } else {
         ArrayUtil$.MODULE$.fill(r.data$mcI$sp(), r.offset(), r.length(), BoxesRunTime.boxToInteger(v));
         return r;
      }
   }

   public DenseVector fill$mJc$sp(final int size, final long v, final ClassTag evidence$8, final Semiring evidence$9) {
      DenseVector r = this.apply$mJc$sp((long[])evidence$8.newArray(size));
      int left$macro$1 = r.stride();
      int right$macro$2 = 1;
      if (left$macro$1 != 1) {
         throw new AssertionError((new StringBuilder(49)).append("assertion failed: ").append("r.stride == 1 (").append(left$macro$1).append(" ").append("!=").append(" ").append(1).append(")").toString());
      } else {
         ArrayUtil$.MODULE$.fill(r.data$mcJ$sp(), r.offset(), r.length(), BoxesRunTime.boxToLong(v));
         return r;
      }
   }

   public CanMapValues DV_canMapValues$mDDc$sp(final ClassTag man) {
      return new CanMapValues.DenseCanMapValues(man) {
         private final ClassTag man$2;

         public final Object mapActive(final Object from, final Function1 fn) {
            return CanMapValues.DenseCanMapValues.mapActive$(this, from, fn);
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

         public DenseVector map(final DenseVector from, final Function1 fn) {
            return this.map$mcDD$sp(from, fn);
         }

         public void breeze$linalg$DenseVector$$anon$$mediumPath(final double[] out, final Function1 fn, final double[] data, final int off) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = out.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               out[index$macro$2] = fn.apply$mcDD$sp(data[index$macro$2 + off]);
            }

         }

         public void breeze$linalg$DenseVector$$anon$$fastestPath(final double[] out, final Function1 fn, final double[] data) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = out.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               out[index$macro$2] = fn.apply$mcDD$sp(data[index$macro$2]);
            }

         }

         public final void breeze$linalg$DenseVector$$anon$$slowPath(final double[] out, final Function1 fn, final double[] data, final int off, final int stride) {
            int i = 0;

            for(int j = off; i < out.length; j += stride) {
               out[i] = fn.apply$mcDD$sp(data[j]);
               ++i;
            }

         }

         public DenseVector map$mcDD$sp(final DenseVector from, final Function1 fn) {
            double[] out = (double[])this.man$2.newArray(from.length());
            if (from.noOffsetOrStride()) {
               this.breeze$linalg$DenseVector$$anon$$fastestPath(out, fn, from.data$mcD$sp());
            } else if (from.stride() == 1) {
               this.breeze$linalg$DenseVector$$anon$$mediumPath(out, fn, from.data$mcD$sp(), from.offset());
            } else {
               this.breeze$linalg$DenseVector$$anon$$slowPath(out, fn, from.data$mcD$sp(), from.offset(), from.stride());
            }

            return DenseVector$.MODULE$.apply$mDc$sp(out);
         }

         public {
            this.man$2 = man$2;
            CanMapValues.DenseCanMapValues.$init$(this);
         }
      };
   }

   public CanMapValues DV_canMapValues$mDFc$sp(final ClassTag man) {
      return new CanMapValues.DenseCanMapValues(man) {
         private final ClassTag man$3;

         public final Object mapActive(final Object from, final Function1 fn) {
            return CanMapValues.DenseCanMapValues.mapActive$(this, from, fn);
         }

         public Object map$mcDD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDD$sp$(this, from, fn);
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

         public DenseVector map(final DenseVector from, final Function1 fn) {
            return this.map$mcDF$sp(from, fn);
         }

         public void breeze$linalg$DenseVector$$anon$$mediumPath(final float[] out, final Function1 fn, final double[] data, final int off) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = out.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               out[index$macro$2] = fn.apply$mcFD$sp(data[index$macro$2 + off]);
            }

         }

         public void breeze$linalg$DenseVector$$anon$$fastestPath(final float[] out, final Function1 fn, final double[] data) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = out.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               out[index$macro$2] = fn.apply$mcFD$sp(data[index$macro$2]);
            }

         }

         public final void breeze$linalg$DenseVector$$anon$$slowPath(final float[] out, final Function1 fn, final double[] data, final int off, final int stride) {
            int i = 0;

            for(int j = off; i < out.length; j += stride) {
               out[i] = fn.apply$mcFD$sp(data[j]);
               ++i;
            }

         }

         public DenseVector map$mcDF$sp(final DenseVector from, final Function1 fn) {
            float[] out = (float[])this.man$3.newArray(from.length());
            if (from.noOffsetOrStride()) {
               this.breeze$linalg$DenseVector$$anon$$fastestPath(out, fn, from.data$mcD$sp());
            } else if (from.stride() == 1) {
               this.breeze$linalg$DenseVector$$anon$$mediumPath(out, fn, from.data$mcD$sp(), from.offset());
            } else {
               this.breeze$linalg$DenseVector$$anon$$slowPath(out, fn, from.data$mcD$sp(), from.offset(), from.stride());
            }

            return DenseVector$.MODULE$.apply$mFc$sp(out);
         }

         public {
            this.man$3 = man$3;
            CanMapValues.DenseCanMapValues.$init$(this);
         }
      };
   }

   public CanMapValues DV_canMapValues$mDIc$sp(final ClassTag man) {
      return new CanMapValues.DenseCanMapValues(man) {
         private final ClassTag man$4;

         public final Object mapActive(final Object from, final Function1 fn) {
            return CanMapValues.DenseCanMapValues.mapActive$(this, from, fn);
         }

         public Object map$mcDD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDD$sp$(this, from, fn);
         }

         public Object map$mcDF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDF$sp$(this, from, fn);
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

         public DenseVector map(final DenseVector from, final Function1 fn) {
            return this.map$mcDI$sp(from, fn);
         }

         public void breeze$linalg$DenseVector$$anon$$mediumPath(final int[] out, final Function1 fn, final double[] data, final int off) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = out.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               out[index$macro$2] = fn.apply$mcID$sp(data[index$macro$2 + off]);
            }

         }

         public void breeze$linalg$DenseVector$$anon$$fastestPath(final int[] out, final Function1 fn, final double[] data) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = out.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               out[index$macro$2] = fn.apply$mcID$sp(data[index$macro$2]);
            }

         }

         public final void breeze$linalg$DenseVector$$anon$$slowPath(final int[] out, final Function1 fn, final double[] data, final int off, final int stride) {
            int i = 0;

            for(int j = off; i < out.length; j += stride) {
               out[i] = fn.apply$mcID$sp(data[j]);
               ++i;
            }

         }

         public DenseVector map$mcDI$sp(final DenseVector from, final Function1 fn) {
            int[] out = (int[])this.man$4.newArray(from.length());
            if (from.noOffsetOrStride()) {
               this.breeze$linalg$DenseVector$$anon$$fastestPath(out, fn, from.data$mcD$sp());
            } else if (from.stride() == 1) {
               this.breeze$linalg$DenseVector$$anon$$mediumPath(out, fn, from.data$mcD$sp(), from.offset());
            } else {
               this.breeze$linalg$DenseVector$$anon$$slowPath(out, fn, from.data$mcD$sp(), from.offset(), from.stride());
            }

            return DenseVector$.MODULE$.apply$mIc$sp(out);
         }

         public {
            this.man$4 = man$4;
            CanMapValues.DenseCanMapValues.$init$(this);
         }
      };
   }

   public CanMapValues DV_canMapValues$mFDc$sp(final ClassTag man) {
      return new CanMapValues.DenseCanMapValues(man) {
         private final ClassTag man$5;

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

         public DenseVector map(final DenseVector from, final Function1 fn) {
            return this.map$mcFD$sp(from, fn);
         }

         public void breeze$linalg$DenseVector$$anon$$mediumPath(final double[] out, final Function1 fn, final float[] data, final int off) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = out.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               out[index$macro$2] = fn.apply$mcDF$sp(data[index$macro$2 + off]);
            }

         }

         public void breeze$linalg$DenseVector$$anon$$fastestPath(final double[] out, final Function1 fn, final float[] data) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = out.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               out[index$macro$2] = fn.apply$mcDF$sp(data[index$macro$2]);
            }

         }

         public final void breeze$linalg$DenseVector$$anon$$slowPath(final double[] out, final Function1 fn, final float[] data, final int off, final int stride) {
            int i = 0;

            for(int j = off; i < out.length; j += stride) {
               out[i] = fn.apply$mcDF$sp(data[j]);
               ++i;
            }

         }

         public DenseVector map$mcFD$sp(final DenseVector from, final Function1 fn) {
            double[] out = (double[])this.man$5.newArray(from.length());
            if (from.noOffsetOrStride()) {
               this.breeze$linalg$DenseVector$$anon$$fastestPath(out, fn, from.data$mcF$sp());
            } else if (from.stride() == 1) {
               this.breeze$linalg$DenseVector$$anon$$mediumPath(out, fn, from.data$mcF$sp(), from.offset());
            } else {
               this.breeze$linalg$DenseVector$$anon$$slowPath(out, fn, from.data$mcF$sp(), from.offset(), from.stride());
            }

            return DenseVector$.MODULE$.apply$mDc$sp(out);
         }

         public {
            this.man$5 = man$5;
            CanMapValues.DenseCanMapValues.$init$(this);
         }
      };
   }

   public CanMapValues DV_canMapValues$mFFc$sp(final ClassTag man) {
      return new CanMapValues.DenseCanMapValues(man) {
         private final ClassTag man$6;

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

         public DenseVector map(final DenseVector from, final Function1 fn) {
            return this.map$mcFF$sp(from, fn);
         }

         public void breeze$linalg$DenseVector$$anon$$mediumPath(final float[] out, final Function1 fn, final float[] data, final int off) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = out.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               out[index$macro$2] = fn.apply$mcFF$sp(data[index$macro$2 + off]);
            }

         }

         public void breeze$linalg$DenseVector$$anon$$fastestPath(final float[] out, final Function1 fn, final float[] data) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = out.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               out[index$macro$2] = fn.apply$mcFF$sp(data[index$macro$2]);
            }

         }

         public final void breeze$linalg$DenseVector$$anon$$slowPath(final float[] out, final Function1 fn, final float[] data, final int off, final int stride) {
            int i = 0;

            for(int j = off; i < out.length; j += stride) {
               out[i] = fn.apply$mcFF$sp(data[j]);
               ++i;
            }

         }

         public DenseVector map$mcFF$sp(final DenseVector from, final Function1 fn) {
            float[] out = (float[])this.man$6.newArray(from.length());
            if (from.noOffsetOrStride()) {
               this.breeze$linalg$DenseVector$$anon$$fastestPath(out, fn, from.data$mcF$sp());
            } else if (from.stride() == 1) {
               this.breeze$linalg$DenseVector$$anon$$mediumPath(out, fn, from.data$mcF$sp(), from.offset());
            } else {
               this.breeze$linalg$DenseVector$$anon$$slowPath(out, fn, from.data$mcF$sp(), from.offset(), from.stride());
            }

            return DenseVector$.MODULE$.apply$mFc$sp(out);
         }

         public {
            this.man$6 = man$6;
            CanMapValues.DenseCanMapValues.$init$(this);
         }
      };
   }

   public CanMapValues DV_canMapValues$mFIc$sp(final ClassTag man) {
      return new CanMapValues.DenseCanMapValues(man) {
         private final ClassTag man$7;

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

         public DenseVector map(final DenseVector from, final Function1 fn) {
            return this.map$mcFI$sp(from, fn);
         }

         public void breeze$linalg$DenseVector$$anon$$mediumPath(final int[] out, final Function1 fn, final float[] data, final int off) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = out.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               out[index$macro$2] = fn.apply$mcIF$sp(data[index$macro$2 + off]);
            }

         }

         public void breeze$linalg$DenseVector$$anon$$fastestPath(final int[] out, final Function1 fn, final float[] data) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = out.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               out[index$macro$2] = fn.apply$mcIF$sp(data[index$macro$2]);
            }

         }

         public final void breeze$linalg$DenseVector$$anon$$slowPath(final int[] out, final Function1 fn, final float[] data, final int off, final int stride) {
            int i = 0;

            for(int j = off; i < out.length; j += stride) {
               out[i] = fn.apply$mcIF$sp(data[j]);
               ++i;
            }

         }

         public DenseVector map$mcFI$sp(final DenseVector from, final Function1 fn) {
            int[] out = (int[])this.man$7.newArray(from.length());
            if (from.noOffsetOrStride()) {
               this.breeze$linalg$DenseVector$$anon$$fastestPath(out, fn, from.data$mcF$sp());
            } else if (from.stride() == 1) {
               this.breeze$linalg$DenseVector$$anon$$mediumPath(out, fn, from.data$mcF$sp(), from.offset());
            } else {
               this.breeze$linalg$DenseVector$$anon$$slowPath(out, fn, from.data$mcF$sp(), from.offset(), from.stride());
            }

            return DenseVector$.MODULE$.apply$mIc$sp(out);
         }

         public {
            this.man$7 = man$7;
            CanMapValues.DenseCanMapValues.$init$(this);
         }
      };
   }

   public CanMapValues DV_canMapValues$mIDc$sp(final ClassTag man) {
      return new CanMapValues.DenseCanMapValues(man) {
         private final ClassTag man$8;

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

         public DenseVector map(final DenseVector from, final Function1 fn) {
            return this.map$mcID$sp(from, fn);
         }

         public void breeze$linalg$DenseVector$$anon$$mediumPath(final double[] out, final Function1 fn, final int[] data, final int off) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = out.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               out[index$macro$2] = fn.apply$mcDI$sp(data[index$macro$2 + off]);
            }

         }

         public void breeze$linalg$DenseVector$$anon$$fastestPath(final double[] out, final Function1 fn, final int[] data) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = out.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               out[index$macro$2] = fn.apply$mcDI$sp(data[index$macro$2]);
            }

         }

         public final void breeze$linalg$DenseVector$$anon$$slowPath(final double[] out, final Function1 fn, final int[] data, final int off, final int stride) {
            int i = 0;

            for(int j = off; i < out.length; j += stride) {
               out[i] = fn.apply$mcDI$sp(data[j]);
               ++i;
            }

         }

         public DenseVector map$mcID$sp(final DenseVector from, final Function1 fn) {
            double[] out = (double[])this.man$8.newArray(from.length());
            if (from.noOffsetOrStride()) {
               this.breeze$linalg$DenseVector$$anon$$fastestPath(out, fn, from.data$mcI$sp());
            } else if (from.stride() == 1) {
               this.breeze$linalg$DenseVector$$anon$$mediumPath(out, fn, from.data$mcI$sp(), from.offset());
            } else {
               this.breeze$linalg$DenseVector$$anon$$slowPath(out, fn, from.data$mcI$sp(), from.offset(), from.stride());
            }

            return DenseVector$.MODULE$.apply$mDc$sp(out);
         }

         public {
            this.man$8 = man$8;
            CanMapValues.DenseCanMapValues.$init$(this);
         }
      };
   }

   public CanMapValues DV_canMapValues$mIFc$sp(final ClassTag man) {
      return new CanMapValues.DenseCanMapValues(man) {
         private final ClassTag man$9;

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

         public DenseVector map(final DenseVector from, final Function1 fn) {
            return this.map$mcIF$sp(from, fn);
         }

         public void breeze$linalg$DenseVector$$anon$$mediumPath(final float[] out, final Function1 fn, final int[] data, final int off) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = out.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               out[index$macro$2] = fn.apply$mcFI$sp(data[index$macro$2 + off]);
            }

         }

         public void breeze$linalg$DenseVector$$anon$$fastestPath(final float[] out, final Function1 fn, final int[] data) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = out.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               out[index$macro$2] = fn.apply$mcFI$sp(data[index$macro$2]);
            }

         }

         public final void breeze$linalg$DenseVector$$anon$$slowPath(final float[] out, final Function1 fn, final int[] data, final int off, final int stride) {
            int i = 0;

            for(int j = off; i < out.length; j += stride) {
               out[i] = fn.apply$mcFI$sp(data[j]);
               ++i;
            }

         }

         public DenseVector map$mcIF$sp(final DenseVector from, final Function1 fn) {
            float[] out = (float[])this.man$9.newArray(from.length());
            if (from.noOffsetOrStride()) {
               this.breeze$linalg$DenseVector$$anon$$fastestPath(out, fn, from.data$mcI$sp());
            } else if (from.stride() == 1) {
               this.breeze$linalg$DenseVector$$anon$$mediumPath(out, fn, from.data$mcI$sp(), from.offset());
            } else {
               this.breeze$linalg$DenseVector$$anon$$slowPath(out, fn, from.data$mcI$sp(), from.offset(), from.stride());
            }

            return DenseVector$.MODULE$.apply$mFc$sp(out);
         }

         public {
            this.man$9 = man$9;
            CanMapValues.DenseCanMapValues.$init$(this);
         }
      };
   }

   public CanMapValues DV_canMapValues$mIIc$sp(final ClassTag man) {
      return new CanMapValues.DenseCanMapValues(man) {
         private final ClassTag man$10;

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

         public DenseVector map(final DenseVector from, final Function1 fn) {
            return this.map$mcII$sp(from, fn);
         }

         public void breeze$linalg$DenseVector$$anon$$mediumPath(final int[] out, final Function1 fn, final int[] data, final int off) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = out.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               out[index$macro$2] = fn.apply$mcII$sp(data[index$macro$2 + off]);
            }

         }

         public void breeze$linalg$DenseVector$$anon$$fastestPath(final int[] out, final Function1 fn, final int[] data) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = out.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               out[index$macro$2] = fn.apply$mcII$sp(data[index$macro$2]);
            }

         }

         public final void breeze$linalg$DenseVector$$anon$$slowPath(final int[] out, final Function1 fn, final int[] data, final int off, final int stride) {
            int i = 0;

            for(int j = off; i < out.length; j += stride) {
               out[i] = fn.apply$mcII$sp(data[j]);
               ++i;
            }

         }

         public DenseVector map$mcII$sp(final DenseVector from, final Function1 fn) {
            int[] out = (int[])this.man$10.newArray(from.length());
            if (from.noOffsetOrStride()) {
               this.breeze$linalg$DenseVector$$anon$$fastestPath(out, fn, from.data$mcI$sp());
            } else if (from.stride() == 1) {
               this.breeze$linalg$DenseVector$$anon$$mediumPath(out, fn, from.data$mcI$sp(), from.offset());
            } else {
               this.breeze$linalg$DenseVector$$anon$$slowPath(out, fn, from.data$mcI$sp(), from.offset(), from.stride());
            }

            return DenseVector$.MODULE$.apply$mIc$sp(out);
         }

         public {
            this.man$10 = man$10;
            CanMapValues.DenseCanMapValues.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   public static final boolean $anonfun$horzcat$1(final int size$1, final DenseVector x$1) {
      return x$1.size() == size$1;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$horzcat$2(final Tuple2 check$ifrefutable$1) {
      boolean var1;
      if (check$ifrefutable$1 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   public static final int $anonfun$vertcat$1(final int x$3, final DenseVector x$4) {
      return x$3 + x$4.size();
   }

   // $FF: synthetic method
   public static final void $anonfun$vertcat$2(final DenseVector result$2, final IntRef offset$1, final UFunc.InPlaceImpl2 canSet$1, final DenseVector v) {
      result$2.slice(offset$1.elem, offset$1.elem + v.size(), result$2.slice$default$3()).$colon$eq(v, canSet$1);
      offset$1.elem += v.size();
   }

   private DenseVector$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
