package org.apache.commons.math3.fitting;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.analysis.MultivariateMatrixFunction;
import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.optim.InitialGuess;
import org.apache.commons.math3.optim.MaxEval;
import org.apache.commons.math3.optim.PointVectorValuePair;
import org.apache.commons.math3.optim.nonlinear.vector.ModelFunction;
import org.apache.commons.math3.optim.nonlinear.vector.ModelFunctionJacobian;
import org.apache.commons.math3.optim.nonlinear.vector.MultivariateVectorOptimizer;
import org.apache.commons.math3.optim.nonlinear.vector.Target;
import org.apache.commons.math3.optim.nonlinear.vector.Weight;

/** @deprecated */
@Deprecated
public class CurveFitter {
   private final MultivariateVectorOptimizer optimizer;
   private final List observations;

   public CurveFitter(MultivariateVectorOptimizer optimizer) {
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

      CurveFitter<T>.TheoreticalValuesFunction model = new TheoreticalValuesFunction(f);
      PointVectorValuePair optimum = this.optimizer.optimize(new MaxEval(maxEval), model.getModelFunction(), model.getModelFunctionJacobian(), new Target(target), new Weight(weights), new InitialGuess(initialGuess));
      return optimum.getPointRef();
   }

   // $FF: synthetic method
   static List access$000(CurveFitter x0) {
      return x0.observations;
   }

   private class TheoreticalValuesFunction {
      private final ParametricUnivariateFunction f;

      TheoreticalValuesFunction(ParametricUnivariateFunction f) {
         this.f = f;
      }

      public ModelFunction getModelFunction() {
         // $FF: Couldn't be decompiled
      }

      public ModelFunctionJacobian getModelFunctionJacobian() {
         // $FF: Couldn't be decompiled
      }

      // $FF: synthetic method
      static ParametricUnivariateFunction access$100(TheoreticalValuesFunction x0) {
         return x0.f;
      }
   }
}
