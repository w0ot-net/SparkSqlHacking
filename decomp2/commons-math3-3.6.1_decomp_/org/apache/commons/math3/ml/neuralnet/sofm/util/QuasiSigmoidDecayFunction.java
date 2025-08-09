package org.apache.commons.math3.ml.neuralnet.sofm.util;

import org.apache.commons.math3.analysis.function.Logistic;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;

public class QuasiSigmoidDecayFunction {
   private final Logistic sigmoid;
   private final double scale;

   public QuasiSigmoidDecayFunction(double initValue, double slope, long numCall) {
      if (initValue <= (double)0.0F) {
         throw new NotStrictlyPositiveException(initValue);
      } else if (slope >= (double)0.0F) {
         throw new NumberIsTooLargeException(slope, 0, false);
      } else if (numCall <= 1L) {
         throw new NotStrictlyPositiveException(numCall);
      } else {
         double m = (double)numCall;
         double b = (double)4.0F * slope / initValue;
         double q = (double)1.0F;
         double a = (double)0.0F;
         double n = (double)1.0F;
         this.sigmoid = new Logistic(initValue, m, b, (double)1.0F, (double)0.0F, (double)1.0F);
         double y0 = this.sigmoid.value((double)0.0F);
         this.scale = initValue / y0;
      }
   }

   public double value(long numCall) {
      return this.scale * this.sigmoid.value((double)numCall);
   }
}
