package org.apache.commons.math3.optimization.fitting;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.analysis.DifferentiableMultivariateVectorFunction;
import org.apache.commons.math3.analysis.MultivariateMatrixFunction;
import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.MultivariateDifferentiableVectorFunction;
import org.apache.commons.math3.optimization.DifferentiableMultivariateVectorOptimizer;
import org.apache.commons.math3.optimization.MultivariateDifferentiableVectorOptimizer;
import org.apache.commons.math3.optimization.PointVectorValuePair;

/** @deprecated */
@Deprecated
public class CurveFitter {
   /** @deprecated */
   @Deprecated
   private final DifferentiableMultivariateVectorOptimizer oldOptimizer;
   private final MultivariateDifferentiableVectorOptimizer optimizer;
   private final List observations;

   /** @deprecated */
   @Deprecated
   public CurveFitter(DifferentiableMultivariateVectorOptimizer optimizer) {
      this.oldOptimizer = optimizer;
      this.optimizer = null;
      this.observations = new ArrayList();
   }

   public CurveFitter(MultivariateDifferentiableVectorOptimizer optimizer) {
      this.oldOptimizer = null;
      this.optimizer = optimizer;
      this.observations = new ArrayList();
   }

   public void addObservedPoint(double x, double y) {
      this.addObservedPoint((double)1.0F, x, y);
   }

   public void addObservedPoint(double weight, double x, double y) {
      this.observations.add(new WeightedObservedPoint(weight, x, y));
   }

   public void addObservedPoint(WeightedObservedPoint observed) {
      this.observations.add(observed);
   }

   public WeightedObservedPoint[] getObservations() {
      return (WeightedObservedPoint[])this.observations.toArray(new WeightedObservedPoint[this.observations.size()]);
   }

   public void clearObservations() {
      this.observations.clear();
   }

   public double[] fit(ParametricUnivariateFunction f, double[] initialGuess) {
      return this.fit(Integer.MAX_VALUE, f, initialGuess);
   }

   public double[] fit(int maxEval, ParametricUnivariateFunction f, double[] initialGuess) {
      double[] target = new double[this.observations.size()];
      double[] weights = new double[this.observations.size()];
      int i = 0;

      for(WeightedObservedPoint point : this.observations) {
         target[i] = point.getY();
         weights[i] = point.getWeight();
         ++i;
      }

      PointVectorValuePair optimum;
      if (this.optimizer == null) {
         optimum = this.oldOptimizer.optimize(maxEval, new OldTheoreticalValuesFunction(f), target, weights, initialGuess);
      } else {
         optimum = this.optimizer.optimize(maxEval, new TheoreticalValuesFunction(f), target, weights, initialGuess);
      }

      return optimum.getPointRef();
   }

   /** @deprecated */
   @Deprecated
   private class OldTheoreticalValuesFunction implements DifferentiableMultivariateVectorFunction {
      private final ParametricUnivariateFunction f;

      OldTheoreticalValuesFunction(ParametricUnivariateFunction f) {
         this.f = f;
      }

      public MultivariateMatrixFunction jacobian() {
         // $FF: Couldn't be decompiled
      }

      public double[] value(double[] point) {
         double[] values = new double[CurveFitter.this.observations.size()];
         int i = 0;

         for(WeightedObservedPoint observed : CurveFitter.this.observations) {
            values[i++] = this.f.value(observed.getX(), point);
         }

         return values;
      }

      // $FF: synthetic method
      static ParametricUnivariateFunction access$100(OldTheoreticalValuesFunction x0) {
         return x0.f;
      }
   }

   private class TheoreticalValuesFunction implements MultivariateDifferentiableVectorFunction {
      private final ParametricUnivariateFunction f;

      TheoreticalValuesFunction(ParametricUnivariateFunction f) {
         this.f = f;
      }

      public double[] value(double[] point) {
         double[] values = new double[CurveFitter.this.observations.size()];
         int i = 0;

         for(WeightedObservedPoint observed : CurveFitter.this.observations) {
            values[i++] = this.f.value(observed.getX(), point);
         }

         return values;
      }

      public DerivativeStructure[] value(DerivativeStructure[] point) {
         double[] parameters = new double[point.length];

         for(int k = 0; k < point.length; ++k) {
            parameters[k] = point[k].getValue();
         }

         DerivativeStructure[] values = new DerivativeStructure[CurveFitter.this.observations.size()];
         int i = 0;

         for(WeightedObservedPoint observed : CurveFitter.this.observations) {
            DerivativeStructure vi = new DerivativeStructure(point.length, 1, this.f.value(observed.getX(), parameters));

            for(int k = 0; k < point.length; ++k) {
               vi = vi.add(new DerivativeStructure(point.length, 1, k, (double)0.0F));
            }

            values[i++] = vi;
         }

         return values;
      }
   }
}
