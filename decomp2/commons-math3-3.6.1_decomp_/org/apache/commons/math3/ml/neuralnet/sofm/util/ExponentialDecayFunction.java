package org.apache.commons.math3.ml.neuralnet.sofm.util;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.util.FastMath;

public class ExponentialDecayFunction {
   private final double a;
   private final double oneOverB;

   public ExponentialDecayFunction(double initValue, double valueAtNumCall, long numCall) {
      if (initValue <= (double)0.0F) {
         throw new NotStrictlyPositiveException(initValue);
      } else if (valueAtNumCall <= (double)0.0F) {
         throw new NotStrictlyPositiveException(valueAtNumCall);
      } else if (valueAtNumCall >= initValue) {
         throw new NumberIsTooLargeException(valueAtNumCall, initValue, false);
      } else if (numCall <= 0L) {
         throw new NotStrictlyPositiveException(numCall);
      } else {
         this.a = initValue;
         this.oneOverB = -FastMath.log(valueAtNumCall / initValue) / (double)numCall;
      }
   }

   public double value(long numCall) {
      return this.a * FastMath.exp((double)(-numCall) * this.oneOverB);
   }
}
