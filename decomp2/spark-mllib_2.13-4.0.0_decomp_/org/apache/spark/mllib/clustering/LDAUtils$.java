package org.apache.spark.mllib.clustering;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.max.;
import scala.runtime.BoxesRunTime;

public final class LDAUtils$ {
   public static final LDAUtils$ MODULE$ = new LDAUtils$();

   public double logSumExp(final DenseVector x) {
      double a = BoxesRunTime.unboxToDouble(.MODULE$.apply(x, .MODULE$.reduce_Double(breeze.linalg.operators.HasOps..MODULE$.DV_canIterateValues())));
      return a + breeze.numerics.package.log..MODULE$.apply$mDDc$sp(BoxesRunTime.unboxToDouble(breeze.linalg.sum..MODULE$.apply(breeze.numerics.package.exp..MODULE$.apply(x.$minus$colon$minus(BoxesRunTime.boxToDouble(a), breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_S_eq_DV_Double_OpSub()), breeze.linalg.operators.HasOps..MODULE$.fromLowOrderCanMapValues(breeze.linalg.DenseVector..MODULE$.DV_scalarOf(), breeze.numerics.package.exp.expDoubleImpl..MODULE$, breeze.linalg.DenseVector..MODULE$.DV_canMapValues$mDDc$sp(scala.reflect.ClassTag..MODULE$.Double()))), breeze.linalg.sum..MODULE$.reduce_Double(breeze.linalg.operators.HasOps..MODULE$.DV_canIterateValues()))), breeze.numerics.package.log.logDoubleImpl..MODULE$);
   }

   public DenseVector dirichletExpectation(final DenseVector alpha) {
      return (DenseVector)((ImmutableNumericOps)breeze.numerics.package.digamma..MODULE$.apply(alpha, breeze.linalg.operators.HasOps..MODULE$.fromLowOrderCanMapValues(breeze.linalg.DenseVector..MODULE$.DV_scalarOf(), breeze.numerics.package.digamma.digammaImplDouble..MODULE$, breeze.linalg.DenseVector..MODULE$.DV_canMapValues$mDDc$sp(scala.reflect.ClassTag..MODULE$.Double())))).$minus(BoxesRunTime.boxToDouble(breeze.numerics.package.digamma..MODULE$.apply$mDDc$sp(BoxesRunTime.unboxToDouble(breeze.linalg.sum..MODULE$.apply(alpha, breeze.linalg.sum..MODULE$.reduce_Double(breeze.linalg.operators.HasOps..MODULE$.DV_canIterateValues()))), breeze.numerics.package.digamma.digammaImplDouble..MODULE$)), breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_S_eq_DV_Double_OpSub());
   }

   public DenseMatrix dirichletExpectation(final DenseMatrix alpha) {
      DenseVector rowSum = (DenseVector)breeze.linalg.sum..MODULE$.apply(alpha.apply(breeze.linalg..times..MODULE$, scala.package..MODULE$.$colon$colon(), breeze.linalg.Broadcaster..MODULE$.canBroadcastRows(breeze.linalg.operators.HasOps..MODULE$.handholdCanMapCols_DM())), breeze.linalg.sum..MODULE$.vectorizeRows(scala.reflect.ClassTag..MODULE$.Double(), breeze.linalg.sum..MODULE$.helper_Double(), breeze.linalg.operators.HasOps..MODULE$.impl_OpAdd_InPlace_DV_DV_Double()));
      DenseMatrix digAlpha = (DenseMatrix)breeze.numerics.package.digamma..MODULE$.apply(alpha, breeze.linalg.operators.HasOps..MODULE$.fromLowOrderCanMapValues(breeze.linalg.DenseMatrix..MODULE$.scalarOf(), breeze.numerics.package.digamma.digammaImplDouble..MODULE$, breeze.linalg.operators.HasOps..MODULE$.canMapValues_DM$mDDc$sp(scala.reflect.ClassTag..MODULE$.Double())));
      DenseVector digRowSum = (DenseVector)breeze.numerics.package.digamma..MODULE$.apply(rowSum, breeze.linalg.operators.HasOps..MODULE$.fromLowOrderCanMapValues(breeze.linalg.DenseVector..MODULE$.DV_scalarOf(), breeze.numerics.package.digamma.digammaImplDouble..MODULE$, breeze.linalg.DenseVector..MODULE$.DV_canMapValues$mDDc$sp(scala.reflect.ClassTag..MODULE$.Double())));
      DenseMatrix result = (DenseMatrix)((ImmutableNumericOps)digAlpha.apply(scala.package..MODULE$.$colon$colon(), breeze.linalg..times..MODULE$, breeze.linalg.Broadcaster..MODULE$.canBroadcastColumns(breeze.linalg.operators.HasOps..MODULE$.handholdCanMapRows_DM()))).$minus(digRowSum, breeze.linalg.operators.HasOps..MODULE$.broadcastOp2_BCols(breeze.linalg.operators.HasOps..MODULE$.handholdCanMapRows_DM(), breeze.linalg.operators.HasOps..MODULE$.impl_OpSub_DV_DV_eq_DV_Double(), breeze.linalg.operators.HasOps..MODULE$.canMapRows_DM(scala.reflect.ClassTag..MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero(), breeze.linalg.operators.HasOps..MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet())));
      return result;
   }

   private LDAUtils$() {
   }
}
