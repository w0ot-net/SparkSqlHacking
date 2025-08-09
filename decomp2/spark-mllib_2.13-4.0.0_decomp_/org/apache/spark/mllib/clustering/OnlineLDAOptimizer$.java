package org.apache.spark.mllib.clustering;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.Matrix;
import breeze.linalg.Vector;
import breeze.stats.distributions.Gamma;
import breeze.stats.distributions.RandBasis;
import org.apache.commons.math3.random.MersenneTwister;
import org.apache.spark.mllib.linalg.SparseVector;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.immutable.List;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

public final class OnlineLDAOptimizer$ {
   public static final OnlineLDAOptimizer$ MODULE$ = new OnlineLDAOptimizer$();

   public Tuple3 variationalTopicInference(final List indices, final double[] values, final DenseMatrix expElogbeta, final Vector alpha, final double gammaShape, final int k, final long seed) {
      RandBasis randBasis = new RandBasis(new MersenneTwister(seed));
      DenseVector gammad = (new Gamma(gammaShape, (double)1.0F / gammaShape, randBasis)).samplesVector(k, .MODULE$.Double());
      DenseVector expElogthetad = (DenseVector)breeze.numerics.package.exp..MODULE$.apply(LDAUtils$.MODULE$.dirichletExpectation(gammad), breeze.linalg.operators.HasOps..MODULE$.fromLowOrderCanMapValues(breeze.linalg.DenseVector..MODULE$.DV_scalarOf(), breeze.numerics.package.exp.expDoubleImpl..MODULE$, breeze.linalg.DenseVector..MODULE$.DV_canMapValues$mDDc$sp(.MODULE$.Double())));
      DenseMatrix expElogbetad = ((Matrix)expElogbeta.apply(indices, scala.package..MODULE$.$colon$colon(), breeze.linalg.operators.HasOps..MODULE$.canSliceWeirdRows(breeze.math.Semiring..MODULE$.semiringD(), .MODULE$.Double()))).toDenseMatrix$mcD$sp(.MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero());
      DenseVector phiNorm = (DenseVector)((ImmutableNumericOps)expElogbetad.$times(expElogthetad, breeze.linalg.operators.HasOps..MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD())).$plus$colon$plus(BoxesRunTime.boxToDouble(1.0E-100), breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_S_eq_DV_Double_OpAdd());
      double meanGammaChange = (double)1.0F;

      DenseVector ctsVector;
      DenseVector lastgamma;
      for(ctsVector = new DenseVector.mcD.sp(values); meanGammaChange > 0.001; meanGammaChange = BoxesRunTime.unboxToDouble(breeze.linalg.sum..MODULE$.apply(breeze.numerics.package.abs..MODULE$.apply(gammad.$minus(lastgamma, breeze.linalg.operators.HasOps..MODULE$.impl_OpSub_DV_DV_eq_DV_Double()), breeze.linalg.operators.HasOps..MODULE$.fromLowOrderCanMapActiveValues(breeze.linalg.DenseVector..MODULE$.DV_scalarOf(), breeze.numerics.package.abs.absDoubleImpl..MODULE$, breeze.linalg.DenseVector..MODULE$.DV_canMapValues$mDDc$sp(.MODULE$.Double()))), breeze.linalg.sum..MODULE$.reduce_Double(breeze.linalg.operators.HasOps..MODULE$.DV_canIterateValues()))) / (double)k) {
         lastgamma = gammad.copy$mcD$sp();
         gammad.$colon$eq(((ImmutableNumericOps)expElogthetad.$times$colon$times(((ImmutableNumericOps)expElogbetad.t(breeze.linalg.operators.HasOps..MODULE$.canTranspose_DM())).$times(ctsVector.$div$colon$div(phiNorm, breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_DV_eq_DV_Double_OpDiv()), breeze.linalg.operators.HasOps..MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD()), breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_DV_eq_DV_Double_OpMulScalar())).$plus$colon$plus(alpha, breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_V_eq_V_Double_OpAdd()), breeze.linalg.operators.HasOps..MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
         expElogthetad.$colon$eq(breeze.numerics.package.exp..MODULE$.apply(LDAUtils$.MODULE$.dirichletExpectation(gammad), breeze.linalg.operators.HasOps..MODULE$.fromLowOrderCanMapValues(breeze.linalg.DenseVector..MODULE$.DV_scalarOf(), breeze.numerics.package.exp.expDoubleImpl..MODULE$, breeze.linalg.DenseVector..MODULE$.DV_canMapValues$mDDc$sp(.MODULE$.Double()))), breeze.linalg.operators.HasOps..MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
         phiNorm.$colon$eq(((ImmutableNumericOps)expElogbetad.$times(expElogthetad, breeze.linalg.operators.HasOps..MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD())).$plus$colon$plus(BoxesRunTime.boxToDouble(1.0E-100), breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_S_eq_DV_Double_OpAdd()), breeze.linalg.operators.HasOps..MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
      }

      DenseMatrix sstatsd = (DenseMatrix)((ImmutableNumericOps)expElogthetad.asDenseMatrix$mcD$sp().t(breeze.linalg.operators.HasOps..MODULE$.canTranspose_DM())).$times(((DenseVector)ctsVector.$div$colon$div(phiNorm, breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_DV_eq_DV_Double_OpDiv())).asDenseMatrix$mcD$sp(), breeze.linalg.operators.HasOps..MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD());
      return new Tuple3(gammad, sstatsd, indices);
   }

   public Tuple3 variationalTopicInference(final org.apache.spark.mllib.linalg.Vector termCounts, final DenseMatrix expElogbeta, final Vector alpha, final double gammaShape, final int k, final long seed) {
      Tuple2 var10000;
      if (termCounts instanceof org.apache.spark.mllib.linalg.DenseVector var14) {
         var10000 = new Tuple2(scala.package..MODULE$.List().range(BoxesRunTime.boxToInteger(0), BoxesRunTime.boxToInteger(var14.size()), scala.math.Numeric.IntIsIntegral..MODULE$), var14.values());
      } else {
         if (!(termCounts instanceof SparseVector)) {
            throw new MatchError(termCounts);
         }

         SparseVector var15 = (SparseVector)termCounts;
         var10000 = new Tuple2(scala.Predef..MODULE$.wrapIntArray(var15.indices()).toList(), var15.values());
      }

      Tuple2 var12 = var10000;
      if (var12 != null) {
         List ids = (List)var12._1();
         double[] cts = (double[])var12._2();
         if (ids != null && cts != null) {
            Tuple2 var11 = new Tuple2(ids, cts);
            List ids = (List)var11._1();
            double[] cts = (double[])var11._2();
            return this.variationalTopicInference(ids, cts, expElogbeta, alpha, gammaShape, k, seed);
         }
      }

      throw new MatchError(var12);
   }

   private OnlineLDAOptimizer$() {
   }
}
