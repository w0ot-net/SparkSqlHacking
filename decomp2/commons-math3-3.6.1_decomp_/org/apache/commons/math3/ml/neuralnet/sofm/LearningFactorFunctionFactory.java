package org.apache.commons.math3.ml.neuralnet.sofm;

import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.ml.neuralnet.sofm.util.ExponentialDecayFunction;
import org.apache.commons.math3.ml.neuralnet.sofm.util.QuasiSigmoidDecayFunction;

public class LearningFactorFunctionFactory {
   private LearningFactorFunctionFactory() {
   }

   public static LearningFactorFunction exponentialDecay(final double initValue, final double valueAtNumCall, final long numCall) {
      if (!(initValue <= (double)0.0F) && !(initValue > (double)1.0F)) {
         return new LearningFactorFunction() {
            private final ExponentialDecayFunction decay = new ExponentialDecayFunction(initValue, valueAtNumCall, numCall);

            public double value(long n) {
               return this.decay.value(n);
            }
         };
      } else {
         throw new OutOfRangeException(initValue, 0, 1);
      }
   }

   public static LearningFactorFunction quasiSigmoidDecay(final double initValue, final double slope, final long numCall) {
      if (!(initValue <= (double)0.0F) && !(initValue > (double)1.0F)) {
         return new LearningFactorFunction() {
            private final QuasiSigmoidDecayFunction decay = new QuasiSigmoidDecayFunction(initValue, slope, numCall);

            public double value(long n) {
               return this.decay.value(n);
            }
         };
      } else {
         throw new OutOfRangeException(initValue, 0, 1);
      }
   }
}
