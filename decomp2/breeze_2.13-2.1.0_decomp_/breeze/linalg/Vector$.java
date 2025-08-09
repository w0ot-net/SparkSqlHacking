package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.operators.HasOps$;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanCreateZeros;
import breeze.linalg.support.CanCreateZerosLike$;
import breeze.linalg.support.ScalarOf;
import breeze.linalg.support.ScalarOf$;
import breeze.math.Field;
import breeze.math.MutableFiniteCoordinateField;
import breeze.math.MutableFiniteCoordinateField$;
import breeze.stats.distributions.Rand;
import breeze.storage.Zero;
import scala.Predef.;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;

public final class Vector$ implements VectorConstructors {
   public static final Vector$ MODULE$ = new Vector$();

   static {
      VectorConstructors.$init$(MODULE$);
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

   public Vector zeros(final int size, final ClassTag evidence$1, final Zero evidence$2) {
      return DenseVector$.MODULE$.zeros(size, evidence$1, evidence$2);
   }

   public Vector apply(final Object values) {
      return DenseVector$.MODULE$.apply(values);
   }

   public CanCopy canCopy() {
      return new CanCopy() {
         public Vector apply(final Vector t) {
            return t.copy();
         }
      };
   }

   public MutableFiniteCoordinateField space(final Field evidence$3, final Zero evidence$4, final ClassTag evidence$5) {
      Field f = (Field).MODULE$.implicitly(evidence$3);
      UFunc.UImpl _dim = dim$.MODULE$.implVDim(scala..less.colon.less..MODULE$.refl());
      return MutableFiniteCoordinateField$.MODULE$.make(norm$.MODULE$.canNorm(HasOps$.MODULE$.canIterateValues_V(), f.normImpl()), norm$.MODULE$.normDoubleToNormalNorm(norm$.MODULE$.canNorm(HasOps$.MODULE$.canIterateValues_V(), f.normImpl())), evidence$3, HasOps$.MODULE$.impl_Op_V_S_eq_V_Generic_OpAdd(evidence$3, evidence$5), HasOps$.MODULE$.impl_Op_V_S_eq_V_Generic_OpSub(evidence$3, evidence$5), HasOps$.MODULE$.pureFromUpdate(HasOps$.MODULE$.impl_OpMulScalar_InPlace_V_V_Generic(evidence$3), this.canCopy()), HasOps$.MODULE$.pureFromUpdate(HasOps$.MODULE$.impl_OpDiv_InPlace_V_V_Generic(evidence$3), this.canCopy()), this.canCopy(), HasOps$.MODULE$.impl_Op_InPlace_V_S_Generic_OpMulScalar(evidence$3, evidence$5), HasOps$.MODULE$.impl_Op_InPlace_V_S_Generic_OpDiv(evidence$3, evidence$5), HasOps$.MODULE$.impl_OpAdd_InPlace_T_U_Generic_from_scaleAdd_InPlace(HasOps$.MODULE$.impl_scaleAdd_InPlace_V_T_V_Generic(evidence$3), evidence$3), HasOps$.MODULE$.impl_OpSub_InPlace_T_U_Generic_from_scaleAdd_InPlace(HasOps$.MODULE$.impl_scaleAdd_InPlace_V_T_V_Generic(evidence$3), evidence$3), HasOps$.MODULE$.impl_Op_InPlace_V_S_Generic_OpAdd(evidence$3, evidence$5), HasOps$.MODULE$.impl_Op_InPlace_V_S_Generic_OpSub(evidence$3, evidence$5), HasOps$.MODULE$.impl_OpMulScalar_InPlace_V_V_Generic(evidence$3), HasOps$.MODULE$.impl_OpDiv_InPlace_V_V_Generic(evidence$3), HasOps$.MODULE$.impl_OpSet_V_V_InPlace(), HasOps$.MODULE$.impl_Op_InPlace_V_S_Generic_OpSet(evidence$3, evidence$5), HasOps$.MODULE$.impl_scaleAdd_InPlace_V_T_V_Generic(evidence$3), CanCreateZerosLike$.MODULE$.opMapValues(HasOps$.MODULE$.canMapValues_V(evidence$4, evidence$5), evidence$3), this.canCreateZeros(evidence$5, evidence$4), _dim, HasOps$.MODULE$.impl_Op_V_S_eq_V_Generic_OpMulScalar(evidence$3, evidence$5), HasOps$.MODULE$.impl_Op_V_S_eq_V_Generic_OpDiv(evidence$3, evidence$5), HasOps$.MODULE$.impl_OpAdd_V_V_eq_V_Generic(evidence$3), HasOps$.MODULE$.impl_OpSub_V_V_eq_V_Generic(evidence$3), HasOps$.MODULE$.impl_OpNeg_T_Generic_from_OpMulScalar(this.scalarOf(), evidence$3, HasOps$.MODULE$.impl_Op_V_S_eq_V_Generic_OpMulScalar(evidence$3, evidence$5)), scala..less.colon.less..MODULE$.refl(), HasOps$.MODULE$.impl_OpMulInner_V_V_eq_S_Generic(evidence$3), HasOps$.MODULE$.canZipMapValues_V(evidence$5), HasOps$.MODULE$.zipMapKV_V(evidence$5), HasOps$.MODULE$.canIterateValues_V(), HasOps$.MODULE$.canMapValues_V(evidence$4, evidence$5), this.scalarOf());
   }

   public ScalarOf scalarOf() {
      return ScalarOf$.MODULE$.dummy();
   }

   public Vector apply$mDc$sp(final double[] values) {
      return DenseVector$.MODULE$.apply$mDc$sp(values);
   }

   public Vector apply$mFc$sp(final float[] values) {
      return DenseVector$.MODULE$.apply$mFc$sp(values);
   }

   public Vector apply$mIc$sp(final int[] values) {
      return DenseVector$.MODULE$.apply$mIc$sp(values);
   }

   public Vector apply$mJc$sp(final long[] values) {
      return DenseVector$.MODULE$.apply$mJc$sp(values);
   }

   private Vector$() {
   }
}
