package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.operators.VectorBuilderOps;
import breeze.linalg.support.CanCreateZeros;
import breeze.math.Field;
import breeze.math.MutableModule;
import breeze.math.Ring;
import breeze.math.Semiring;
import breeze.storage.Zero;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class VectorBuilder$ implements VectorBuilderOps, Serializable {
   public static final VectorBuilder$ MODULE$ = new VectorBuilder$();
   private static UFunc.InPlaceImpl2 canOpInto_V_S_OpMulScalar_Double;
   private static UFunc.InPlaceImpl2 canOpInto_V_S_OpDiv_Double;
   private static UFunc.InPlaceImpl2 canOpInto_V_S_OpMulScalar_Long;
   private static UFunc.InPlaceImpl2 canOpInto_V_S_OpDiv_Long;
   private static UFunc.InPlaceImpl2 canOpInto_V_S_OpMulScalar_Float;
   private static UFunc.InPlaceImpl2 canOpInto_V_S_OpDiv_Float;
   private static UFunc.InPlaceImpl2 canOpInto_V_S_OpMulScalar_Int;
   private static UFunc.InPlaceImpl2 canOpInto_V_S_OpDiv_Int;
   private static UFunc.InPlaceImpl2 canOpInto_V_V_OpAdd_Double;
   private static UFunc.InPlaceImpl2 canOpInto_V_V_OpSub_Double;
   private static UFunc.InPlaceImpl2 canOpInto_V_V_OpAdd_Long;
   private static UFunc.InPlaceImpl2 canOpInto_V_V_OpSub_Long;
   private static UFunc.InPlaceImpl2 canOpInto_V_V_OpAdd_Float;
   private static UFunc.InPlaceImpl2 canOpInto_V_V_OpSub_Float;
   private static UFunc.InPlaceImpl2 canOpInto_V_V_OpAdd_Int;
   private static UFunc.InPlaceImpl2 canOpInto_V_V_OpSub_Int;
   private static UFunc.InPlaceImpl2 canSet_Double;
   private static UFunc.InPlaceImpl2 canSet_Long;
   private static UFunc.InPlaceImpl2 canSet_Float;
   private static UFunc.InPlaceImpl2 canSet_Int;
   private static UFunc.InPlaceImpl3 canAxpy_Double;
   private static UFunc.InPlaceImpl3 canAxpy_Long;
   private static UFunc.InPlaceImpl3 canAxpy_Float;
   private static UFunc.InPlaceImpl3 canAxpy_Int;
   private static UFunc.UImpl2 canMulDMVB_Double;
   private static UFunc.UImpl2 canMulDMVB_Int;
   private static UFunc.UImpl2 canMulDMVB_Float;
   private static UFunc.UImpl2 canMulDMVB_Long;

   static {
      VectorBuilderOps.$init$(MODULE$);
   }

   public UFunc.InPlaceImpl2 canMulInto_V_S(final Semiring evidence$1, final ClassTag evidence$2) {
      return VectorBuilderOps.canMulInto_V_S$(this, evidence$1, evidence$2);
   }

   public UFunc.InPlaceImpl2 canDivInto_V_S(final Field evidence$3, final ClassTag evidence$4) {
      return VectorBuilderOps.canDivInto_V_S$(this, evidence$3, evidence$4);
   }

   public UFunc.InPlaceImpl2 canOpInto_V_V_OpAdd(final Ring evidence$5, final ClassTag evidence$6) {
      return VectorBuilderOps.canOpInto_V_V_OpAdd$(this, evidence$5, evidence$6);
   }

   public UFunc.InPlaceImpl2 canOpInto_V_V_OpSub(final Ring evidence$7, final ClassTag evidence$8) {
      return VectorBuilderOps.canOpInto_V_V_OpSub$(this, evidence$7, evidence$8);
   }

   public UFunc.InPlaceImpl2 canOpInto_V_S_OpAdd(final Ring evidence$9, final ClassTag evidence$10) {
      return VectorBuilderOps.canOpInto_V_S_OpAdd$(this, evidence$9, evidence$10);
   }

   public UFunc.InPlaceImpl2 canOpInto_V_S_OpSub(final Ring evidence$11, final ClassTag evidence$12) {
      return VectorBuilderOps.canOpInto_V_S_OpSub$(this, evidence$11, evidence$12);
   }

   public UFunc.InPlaceImpl2 canSet() {
      return VectorBuilderOps.canSet$(this);
   }

   public UFunc.InPlaceImpl3 canAxpy(final Semiring evidence$13, final ClassTag evidence$14) {
      return VectorBuilderOps.canAxpy$(this, evidence$13, evidence$14);
   }

   public MutableModule space(final Field evidence$15, final ClassTag evidence$16) {
      return VectorBuilderOps.space$(this, evidence$15, evidence$16);
   }

   public UFunc.InPlaceImpl2 canAddInto_V_VB(final .less.colon.less ev, final Semiring semi) {
      return VectorBuilderOps.canAddInto_V_VB$(this, ev, semi);
   }

   public UFunc.InPlaceImpl2 canSubInto_V_VB(final .less.colon.less ev, final Ring semi) {
      return VectorBuilderOps.canSubInto_V_VB$(this, ev, semi);
   }

   public UFunc.InPlaceImpl2 canAddInto_VV_V(final .less.colon.less ev) {
      return VectorBuilderOps.canAddInto_VV_V$(this, ev);
   }

   public UFunc.InPlaceImpl2 canSubInto_VV_V(final .less.colon.less ev, final Ring ring) {
      return VectorBuilderOps.canSubInto_VV_V$(this, ev, ring);
   }

   public UFunc.UImpl2 canDot_V_VB(final .less.colon.less ev, final Semiring semi) {
      return VectorBuilderOps.canDot_V_VB$(this, ev, semi);
   }

   public UFunc.InPlaceImpl3 canAxpy_V_VB_Semi(final .less.colon.less ev, final Semiring semi) {
      return VectorBuilderOps.canAxpy_V_VB_Semi$(this, ev, semi);
   }

   public UFunc.UImpl2 canDot_VB_V(final .less.colon.less ev, final Semiring semi) {
      return VectorBuilderOps.canDot_VB_V$(this, ev, semi);
   }

   public UFunc.UImpl2 canMulDMVB_Semi(final ClassTag evidence$17, final Semiring semi) {
      return VectorBuilderOps.canMulDMVB_Semi$(this, evidence$17, semi);
   }

   public UFunc.InPlaceImpl2 canOpInto_V_S_OpMulScalar_Double() {
      return canOpInto_V_S_OpMulScalar_Double;
   }

   public UFunc.InPlaceImpl2 canOpInto_V_S_OpDiv_Double() {
      return canOpInto_V_S_OpDiv_Double;
   }

   public UFunc.InPlaceImpl2 canOpInto_V_S_OpMulScalar_Long() {
      return canOpInto_V_S_OpMulScalar_Long;
   }

   public UFunc.InPlaceImpl2 canOpInto_V_S_OpDiv_Long() {
      return canOpInto_V_S_OpDiv_Long;
   }

   public UFunc.InPlaceImpl2 canOpInto_V_S_OpMulScalar_Float() {
      return canOpInto_V_S_OpMulScalar_Float;
   }

   public UFunc.InPlaceImpl2 canOpInto_V_S_OpDiv_Float() {
      return canOpInto_V_S_OpDiv_Float;
   }

   public UFunc.InPlaceImpl2 canOpInto_V_S_OpMulScalar_Int() {
      return canOpInto_V_S_OpMulScalar_Int;
   }

   public UFunc.InPlaceImpl2 canOpInto_V_S_OpDiv_Int() {
      return canOpInto_V_S_OpDiv_Int;
   }

   public UFunc.InPlaceImpl2 canOpInto_V_V_OpAdd_Double() {
      return canOpInto_V_V_OpAdd_Double;
   }

   public UFunc.InPlaceImpl2 canOpInto_V_V_OpSub_Double() {
      return canOpInto_V_V_OpSub_Double;
   }

   public UFunc.InPlaceImpl2 canOpInto_V_V_OpAdd_Long() {
      return canOpInto_V_V_OpAdd_Long;
   }

   public UFunc.InPlaceImpl2 canOpInto_V_V_OpSub_Long() {
      return canOpInto_V_V_OpSub_Long;
   }

   public UFunc.InPlaceImpl2 canOpInto_V_V_OpAdd_Float() {
      return canOpInto_V_V_OpAdd_Float;
   }

   public UFunc.InPlaceImpl2 canOpInto_V_V_OpSub_Float() {
      return canOpInto_V_V_OpSub_Float;
   }

   public UFunc.InPlaceImpl2 canOpInto_V_V_OpAdd_Int() {
      return canOpInto_V_V_OpAdd_Int;
   }

   public UFunc.InPlaceImpl2 canOpInto_V_V_OpSub_Int() {
      return canOpInto_V_V_OpSub_Int;
   }

   public UFunc.InPlaceImpl2 canSet_Double() {
      return canSet_Double;
   }

   public UFunc.InPlaceImpl2 canSet_Long() {
      return canSet_Long;
   }

   public UFunc.InPlaceImpl2 canSet_Float() {
      return canSet_Float;
   }

   public UFunc.InPlaceImpl2 canSet_Int() {
      return canSet_Int;
   }

   public UFunc.InPlaceImpl3 canAxpy_Double() {
      return canAxpy_Double;
   }

   public UFunc.InPlaceImpl3 canAxpy_Long() {
      return canAxpy_Long;
   }

   public UFunc.InPlaceImpl3 canAxpy_Float() {
      return canAxpy_Float;
   }

   public UFunc.InPlaceImpl3 canAxpy_Int() {
      return canAxpy_Int;
   }

   public UFunc.UImpl2 canMulDMVB_Double() {
      return canMulDMVB_Double;
   }

   public UFunc.UImpl2 canMulDMVB_Int() {
      return canMulDMVB_Int;
   }

   public UFunc.UImpl2 canMulDMVB_Float() {
      return canMulDMVB_Float;
   }

   public UFunc.UImpl2 canMulDMVB_Long() {
      return canMulDMVB_Long;
   }

   public void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_S_OpMulScalar_Double_$eq(final UFunc.InPlaceImpl2 x$1) {
      canOpInto_V_S_OpMulScalar_Double = x$1;
   }

   public void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_S_OpDiv_Double_$eq(final UFunc.InPlaceImpl2 x$1) {
      canOpInto_V_S_OpDiv_Double = x$1;
   }

   public void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_S_OpMulScalar_Long_$eq(final UFunc.InPlaceImpl2 x$1) {
      canOpInto_V_S_OpMulScalar_Long = x$1;
   }

   public void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_S_OpDiv_Long_$eq(final UFunc.InPlaceImpl2 x$1) {
      canOpInto_V_S_OpDiv_Long = x$1;
   }

   public void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_S_OpMulScalar_Float_$eq(final UFunc.InPlaceImpl2 x$1) {
      canOpInto_V_S_OpMulScalar_Float = x$1;
   }

   public void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_S_OpDiv_Float_$eq(final UFunc.InPlaceImpl2 x$1) {
      canOpInto_V_S_OpDiv_Float = x$1;
   }

   public void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_S_OpMulScalar_Int_$eq(final UFunc.InPlaceImpl2 x$1) {
      canOpInto_V_S_OpMulScalar_Int = x$1;
   }

   public void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_S_OpDiv_Int_$eq(final UFunc.InPlaceImpl2 x$1) {
      canOpInto_V_S_OpDiv_Int = x$1;
   }

   public void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_V_OpAdd_Double_$eq(final UFunc.InPlaceImpl2 x$1) {
      canOpInto_V_V_OpAdd_Double = x$1;
   }

   public void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_V_OpSub_Double_$eq(final UFunc.InPlaceImpl2 x$1) {
      canOpInto_V_V_OpSub_Double = x$1;
   }

   public void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_V_OpAdd_Long_$eq(final UFunc.InPlaceImpl2 x$1) {
      canOpInto_V_V_OpAdd_Long = x$1;
   }

   public void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_V_OpSub_Long_$eq(final UFunc.InPlaceImpl2 x$1) {
      canOpInto_V_V_OpSub_Long = x$1;
   }

   public void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_V_OpAdd_Float_$eq(final UFunc.InPlaceImpl2 x$1) {
      canOpInto_V_V_OpAdd_Float = x$1;
   }

   public void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_V_OpSub_Float_$eq(final UFunc.InPlaceImpl2 x$1) {
      canOpInto_V_V_OpSub_Float = x$1;
   }

   public void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_V_OpAdd_Int_$eq(final UFunc.InPlaceImpl2 x$1) {
      canOpInto_V_V_OpAdd_Int = x$1;
   }

   public void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_V_OpSub_Int_$eq(final UFunc.InPlaceImpl2 x$1) {
      canOpInto_V_V_OpSub_Int = x$1;
   }

   public void breeze$linalg$operators$VectorBuilderOps$_setter_$canSet_Double_$eq(final UFunc.InPlaceImpl2 x$1) {
      canSet_Double = x$1;
   }

   public void breeze$linalg$operators$VectorBuilderOps$_setter_$canSet_Long_$eq(final UFunc.InPlaceImpl2 x$1) {
      canSet_Long = x$1;
   }

   public void breeze$linalg$operators$VectorBuilderOps$_setter_$canSet_Float_$eq(final UFunc.InPlaceImpl2 x$1) {
      canSet_Float = x$1;
   }

   public void breeze$linalg$operators$VectorBuilderOps$_setter_$canSet_Int_$eq(final UFunc.InPlaceImpl2 x$1) {
      canSet_Int = x$1;
   }

   public void breeze$linalg$operators$VectorBuilderOps$_setter_$canAxpy_Double_$eq(final UFunc.InPlaceImpl3 x$1) {
      canAxpy_Double = x$1;
   }

   public void breeze$linalg$operators$VectorBuilderOps$_setter_$canAxpy_Long_$eq(final UFunc.InPlaceImpl3 x$1) {
      canAxpy_Long = x$1;
   }

   public void breeze$linalg$operators$VectorBuilderOps$_setter_$canAxpy_Float_$eq(final UFunc.InPlaceImpl3 x$1) {
      canAxpy_Float = x$1;
   }

   public void breeze$linalg$operators$VectorBuilderOps$_setter_$canAxpy_Int_$eq(final UFunc.InPlaceImpl3 x$1) {
      canAxpy_Int = x$1;
   }

   public void breeze$linalg$operators$VectorBuilderOps$_setter_$canMulDMVB_Double_$eq(final UFunc.UImpl2 x$1) {
      canMulDMVB_Double = x$1;
   }

   public void breeze$linalg$operators$VectorBuilderOps$_setter_$canMulDMVB_Int_$eq(final UFunc.UImpl2 x$1) {
      canMulDMVB_Int = x$1;
   }

   public void breeze$linalg$operators$VectorBuilderOps$_setter_$canMulDMVB_Float_$eq(final UFunc.UImpl2 x$1) {
      canMulDMVB_Float = x$1;
   }

   public void breeze$linalg$operators$VectorBuilderOps$_setter_$canMulDMVB_Long_$eq(final UFunc.UImpl2 x$1) {
      canMulDMVB_Long = x$1;
   }

   public int $lessinit$greater$default$2() {
      return 0;
   }

   public VectorBuilder zeros(final int size, final int initialNonZero, final ClassTag evidence$1, final Semiring evidence$2, final Zero evidence$3) {
      return new VectorBuilder(size, initialNonZero, evidence$2, evidence$1);
   }

   public int zeros$default$2() {
      return 16;
   }

   public VectorBuilder apply(final Object values, final Semiring evidence$4, final Zero evidence$5) {
      return new VectorBuilder(scala.Array..MODULE$.range(0, scala.runtime.ScalaRunTime..MODULE$.array_length(values)), values, scala.runtime.ScalaRunTime..MODULE$.array_length(values), scala.runtime.ScalaRunTime..MODULE$.array_length(values), evidence$4);
   }

   public VectorBuilder apply(final Seq values, final ClassTag evidence$6, final Semiring evidence$7, final Zero evidence$8) {
      return this.apply(values.toArray(evidence$6), evidence$7, evidence$8);
   }

   public VectorBuilder fill(final int size, final Function0 v, final ClassTag evidence$9, final Semiring evidence$10, final Zero evidence$11) {
      return this.apply(scala.Array..MODULE$.fill(size, v, evidence$9), evidence$10, evidence$11);
   }

   public VectorBuilder tabulate(final int size, final Function1 f, final ClassTag evidence$12, final Semiring evidence$13, final Zero evidence$14) {
      return this.apply(scala.Array..MODULE$.tabulate(size, f, evidence$12), evidence$13, evidence$14);
   }

   public VectorBuilder apply(final int length, final Seq values, final ClassTag evidence$15, final Semiring evidence$16, final Zero evidence$17) {
      VectorBuilder r = this.zeros(length, this.zeros$default$2(), evidence$15, evidence$16, evidence$17);
      values.withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$apply$1(check$ifrefutable$1))).foreach((x$1) -> {
         $anonfun$apply$2(r, x$1);
         return BoxedUnit.UNIT;
      });
      return r;
   }

   public VectorBuilder.CanCopyBuilder canCopyBuilder(final ClassTag evidence$24, final Semiring evidence$25, final Zero evidence$26) {
      return new VectorBuilder.CanCopyBuilder(evidence$24, evidence$25, evidence$26);
   }

   public VectorBuilder.CanZerosBuilder canZerosBuilder(final ClassTag evidence$27, final Semiring evidence$28, final Zero evidence$29) {
      return new VectorBuilder.CanZerosBuilder(evidence$27, evidence$28, evidence$29);
   }

   public CanCreateZeros canZeroBuilder(final Semiring evidence$30, final Zero evidence$31, final ClassTag evidence$32) {
      return new CanCreateZeros(evidence$32, evidence$30, evidence$31) {
         private final ClassTag evidence$32$1;
         private final Semiring evidence$30$1;
         private final Zero evidence$31$1;

         public VectorBuilder apply(final int d) {
            return VectorBuilder$.MODULE$.zeros(d, VectorBuilder$.MODULE$.zeros$default$2(), this.evidence$32$1, this.evidence$30$1, this.evidence$31$1);
         }

         public {
            this.evidence$32$1 = evidence$32$1;
            this.evidence$30$1 = evidence$30$1;
            this.evidence$31$1 = evidence$31$1;
         }
      };
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(VectorBuilder$.class);
   }

   public VectorBuilder zeros$mDc$sp(final int size, final int initialNonZero, final ClassTag evidence$1, final Semiring evidence$2, final Zero evidence$3) {
      return new VectorBuilder$mcD$sp(size, initialNonZero, evidence$2, evidence$1);
   }

   public VectorBuilder zeros$mFc$sp(final int size, final int initialNonZero, final ClassTag evidence$1, final Semiring evidence$2, final Zero evidence$3) {
      return new VectorBuilder$mcF$sp(size, initialNonZero, evidence$2, evidence$1);
   }

   public VectorBuilder zeros$mIc$sp(final int size, final int initialNonZero, final ClassTag evidence$1, final Semiring evidence$2, final Zero evidence$3) {
      return new VectorBuilder$mcI$sp(size, initialNonZero, evidence$2, evidence$1);
   }

   public VectorBuilder zeros$mJc$sp(final int size, final int initialNonZero, final ClassTag evidence$1, final Semiring evidence$2, final Zero evidence$3) {
      return new VectorBuilder$mcJ$sp(size, initialNonZero, evidence$2, evidence$1);
   }

   public VectorBuilder apply$mDc$sp(final double[] values, final Semiring evidence$4, final Zero evidence$5) {
      return new VectorBuilder$mcD$sp(scala.Array..MODULE$.range(0, values.length), values, values.length, values.length, evidence$4);
   }

   public VectorBuilder apply$mFc$sp(final float[] values, final Semiring evidence$4, final Zero evidence$5) {
      return new VectorBuilder$mcF$sp(scala.Array..MODULE$.range(0, values.length), values, values.length, values.length, evidence$4);
   }

   public VectorBuilder apply$mIc$sp(final int[] values, final Semiring evidence$4, final Zero evidence$5) {
      return new VectorBuilder$mcI$sp(scala.Array..MODULE$.range(0, values.length), values, values.length, values.length, evidence$4);
   }

   public VectorBuilder apply$mJc$sp(final long[] values, final Semiring evidence$4, final Zero evidence$5) {
      return new VectorBuilder$mcJ$sp(scala.Array..MODULE$.range(0, values.length), values, values.length, values.length, evidence$4);
   }

   public VectorBuilder fill$mDc$sp(final int size, final Function0 v, final ClassTag evidence$9, final Semiring evidence$10, final Zero evidence$11) {
      return this.apply$mDc$sp((double[])scala.Array..MODULE$.fill(size, v, evidence$9), evidence$10, evidence$11);
   }

   public VectorBuilder fill$mFc$sp(final int size, final Function0 v, final ClassTag evidence$9, final Semiring evidence$10, final Zero evidence$11) {
      return this.apply$mFc$sp((float[])scala.Array..MODULE$.fill(size, v, evidence$9), evidence$10, evidence$11);
   }

   public VectorBuilder fill$mIc$sp(final int size, final Function0 v, final ClassTag evidence$9, final Semiring evidence$10, final Zero evidence$11) {
      return this.apply$mIc$sp((int[])scala.Array..MODULE$.fill(size, v, evidence$9), evidence$10, evidence$11);
   }

   public VectorBuilder fill$mJc$sp(final int size, final Function0 v, final ClassTag evidence$9, final Semiring evidence$10, final Zero evidence$11) {
      return this.apply$mJc$sp((long[])scala.Array..MODULE$.fill(size, v, evidence$9), evidence$10, evidence$11);
   }

   public VectorBuilder tabulate$mDc$sp(final int size, final Function1 f, final ClassTag evidence$12, final Semiring evidence$13, final Zero evidence$14) {
      return this.apply$mDc$sp((double[])scala.Array..MODULE$.tabulate(size, f, evidence$12), evidence$13, evidence$14);
   }

   public VectorBuilder tabulate$mFc$sp(final int size, final Function1 f, final ClassTag evidence$12, final Semiring evidence$13, final Zero evidence$14) {
      return this.apply$mFc$sp((float[])scala.Array..MODULE$.tabulate(size, f, evidence$12), evidence$13, evidence$14);
   }

   public VectorBuilder tabulate$mIc$sp(final int size, final Function1 f, final ClassTag evidence$12, final Semiring evidence$13, final Zero evidence$14) {
      return this.apply$mIc$sp((int[])scala.Array..MODULE$.tabulate(size, f, evidence$12), evidence$13, evidence$14);
   }

   public VectorBuilder tabulate$mJc$sp(final int size, final Function1 f, final ClassTag evidence$12, final Semiring evidence$13, final Zero evidence$14) {
      return this.apply$mJc$sp((long[])scala.Array..MODULE$.tabulate(size, f, evidence$12), evidence$13, evidence$14);
   }

   public VectorBuilder.CanCopyBuilder canCopyBuilder$mDc$sp(final ClassTag evidence$24, final Semiring evidence$25, final Zero evidence$26) {
      return new VectorBuilder$CanCopyBuilder$mcD$sp(evidence$24, evidence$25, evidence$26);
   }

   public VectorBuilder.CanCopyBuilder canCopyBuilder$mFc$sp(final ClassTag evidence$24, final Semiring evidence$25, final Zero evidence$26) {
      return new VectorBuilder$CanCopyBuilder$mcF$sp(evidence$24, evidence$25, evidence$26);
   }

   public VectorBuilder.CanCopyBuilder canCopyBuilder$mIc$sp(final ClassTag evidence$24, final Semiring evidence$25, final Zero evidence$26) {
      return new VectorBuilder$CanCopyBuilder$mcI$sp(evidence$24, evidence$25, evidence$26);
   }

   public VectorBuilder.CanCopyBuilder canCopyBuilder$mJc$sp(final ClassTag evidence$24, final Semiring evidence$25, final Zero evidence$26) {
      return new VectorBuilder$CanCopyBuilder$mcJ$sp(evidence$24, evidence$25, evidence$26);
   }

   public VectorBuilder.CanZerosBuilder canZerosBuilder$mDc$sp(final ClassTag evidence$27, final Semiring evidence$28, final Zero evidence$29) {
      return new VectorBuilder$CanZerosBuilder$mcD$sp(evidence$27, evidence$28, evidence$29);
   }

   public VectorBuilder.CanZerosBuilder canZerosBuilder$mFc$sp(final ClassTag evidence$27, final Semiring evidence$28, final Zero evidence$29) {
      return new VectorBuilder$CanZerosBuilder$mcF$sp(evidence$27, evidence$28, evidence$29);
   }

   public VectorBuilder.CanZerosBuilder canZerosBuilder$mIc$sp(final ClassTag evidence$27, final Semiring evidence$28, final Zero evidence$29) {
      return new VectorBuilder$CanZerosBuilder$mcI$sp(evidence$27, evidence$28, evidence$29);
   }

   public VectorBuilder.CanZerosBuilder canZerosBuilder$mJc$sp(final ClassTag evidence$27, final Semiring evidence$28, final Zero evidence$29) {
      return new VectorBuilder$CanZerosBuilder$mcJ$sp(evidence$27, evidence$28, evidence$29);
   }

   public CanCreateZeros canZeroBuilder$mDc$sp(final Semiring evidence$30, final Zero evidence$31, final ClassTag evidence$32) {
      return new CanCreateZeros(evidence$32, evidence$30, evidence$31) {
         private final ClassTag evidence$32$2;
         private final Semiring evidence$30$2;
         private final Zero evidence$31$2;

         public VectorBuilder apply(final int d) {
            return VectorBuilder$.MODULE$.zeros$mDc$sp(d, VectorBuilder$.MODULE$.zeros$default$2(), this.evidence$32$2, this.evidence$30$2, this.evidence$31$2);
         }

         public {
            this.evidence$32$2 = evidence$32$2;
            this.evidence$30$2 = evidence$30$2;
            this.evidence$31$2 = evidence$31$2;
         }
      };
   }

   public CanCreateZeros canZeroBuilder$mFc$sp(final Semiring evidence$30, final Zero evidence$31, final ClassTag evidence$32) {
      return new CanCreateZeros(evidence$32, evidence$30, evidence$31) {
         private final ClassTag evidence$32$3;
         private final Semiring evidence$30$3;
         private final Zero evidence$31$3;

         public VectorBuilder apply(final int d) {
            return VectorBuilder$.MODULE$.zeros$mFc$sp(d, VectorBuilder$.MODULE$.zeros$default$2(), this.evidence$32$3, this.evidence$30$3, this.evidence$31$3);
         }

         public {
            this.evidence$32$3 = evidence$32$3;
            this.evidence$30$3 = evidence$30$3;
            this.evidence$31$3 = evidence$31$3;
         }
      };
   }

   public CanCreateZeros canZeroBuilder$mIc$sp(final Semiring evidence$30, final Zero evidence$31, final ClassTag evidence$32) {
      return new CanCreateZeros(evidence$32, evidence$30, evidence$31) {
         private final ClassTag evidence$32$4;
         private final Semiring evidence$30$4;
         private final Zero evidence$31$4;

         public VectorBuilder apply(final int d) {
            return VectorBuilder$.MODULE$.zeros$mIc$sp(d, VectorBuilder$.MODULE$.zeros$default$2(), this.evidence$32$4, this.evidence$30$4, this.evidence$31$4);
         }

         public {
            this.evidence$32$4 = evidence$32$4;
            this.evidence$30$4 = evidence$30$4;
            this.evidence$31$4 = evidence$31$4;
         }
      };
   }

   public CanCreateZeros canZeroBuilder$mJc$sp(final Semiring evidence$30, final Zero evidence$31, final ClassTag evidence$32) {
      return new CanCreateZeros(evidence$32, evidence$30, evidence$31) {
         private final ClassTag evidence$32$5;
         private final Semiring evidence$30$5;
         private final Zero evidence$31$5;

         public VectorBuilder apply(final int d) {
            return VectorBuilder$.MODULE$.zeros$mJc$sp(d, VectorBuilder$.MODULE$.zeros$default$2(), this.evidence$32$5, this.evidence$30$5, this.evidence$31$5);
         }

         public {
            this.evidence$32$5 = evidence$32$5;
            this.evidence$30$5 = evidence$30$5;
            this.evidence$31$5 = evidence$31$5;
         }
      };
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
   public static final void $anonfun$apply$2(final VectorBuilder r$1, final Tuple2 x$1) {
      if (x$1 != null) {
         int i = x$1._1$mcI$sp();
         Object v = x$1._2();
         r$1.add(i, v);
         BoxedUnit var2 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$1);
      }
   }

   private VectorBuilder$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
