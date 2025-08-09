package breeze.signal.support;

import breeze.linalg.DenseVector;

public final class FIRKernel1D$ {
   public static final FIRKernel1D$ MODULE$ = new FIRKernel1D$();

   public FIRKernel1D apply(final DenseVector kernel, final double multiplier, final String designText) {
      return new FIRKernel1D(kernel, multiplier, designText);
   }

   private FIRKernel1D$() {
   }
}
