package breeze.signal.support;

import breeze.linalg.DenseVector;
import breeze.signal.OptOverhang;
import breeze.signal.OptPadding;
import breeze.signal.OptRange;

public final class CanFilter$ {
   public static final CanFilter$ MODULE$ = new CanFilter$();
   private static final CanFilter dvDouble1DFilter = new CanFilter() {
      public DenseVector apply(final DenseVector data, final FIRKernel1D kernel, final OptOverhang overhang, final OptPadding padding) {
         return (DenseVector)breeze.signal.package$.MODULE$.convolve(data, kernel.kernel(), OptRange.All$.MODULE$, overhang, padding, breeze.signal.package$.MODULE$.convolve$default$6(), CanConvolve$.MODULE$.dvT1DConvolve_Double());
      }
   };
   private static final CanFilter dvInt1DFilter = new CanFilter() {
      public DenseVector apply(final DenseVector data, final FIRKernel1D kernel, final OptOverhang overhang, final OptPadding padding) {
         return (DenseVector)breeze.signal.package$.MODULE$.convolve(data, kernel.kernel(), OptRange.All$.MODULE$, overhang, padding, breeze.signal.package$.MODULE$.convolve$default$6(), CanConvolve$.MODULE$.dvT1DConvolve_Int());
      }
   };
   private static final CanFilter dvDouble1DFilterVectorKernel = new CanFilter() {
      public DenseVector apply(final DenseVector data, final DenseVector kernel, final OptOverhang overhang, final OptPadding padding) {
         return (DenseVector)breeze.signal.package$.MODULE$.convolve(data, kernel, OptRange.All$.MODULE$, overhang, padding, breeze.signal.package$.MODULE$.convolve$default$6(), CanConvolve$.MODULE$.dvT1DConvolve_Double());
      }
   };

   public CanFilter dvDouble1DFilter() {
      return dvDouble1DFilter;
   }

   public CanFilter dvInt1DFilter() {
      return dvInt1DFilter;
   }

   public CanFilter dvDouble1DFilterVectorKernel() {
      return dvDouble1DFilterVectorKernel;
   }

   private CanFilter$() {
   }
}
