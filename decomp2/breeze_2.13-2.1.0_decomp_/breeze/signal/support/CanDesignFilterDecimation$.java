package breeze.signal.support;

import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.convert$;
import breeze.linalg.operators.HasOps$;
import breeze.signal.OptDesignMethod;
import breeze.signal.OptFilterTaps;
import breeze.signal.OptWindowFunction;
import scala.MatchError;
import scala.runtime.ScalaRunTime.;

public final class CanDesignFilterDecimation$ {
   public static final CanDesignFilterDecimation$ MODULE$ = new CanDesignFilterDecimation$();

   public CanDesignFilterDecimation decimationFilterDouble() {
      return new CanDesignFilterDecimation() {
         public FIRKernel1D apply(final int factor, final double multiplier, final OptDesignMethod optDesignMethod, final OptWindowFunction optWindow, final OptFilterTaps optFilterOrder) {
            if (OptDesignMethod.Firwin$.MODULE$.equals(optDesignMethod)) {
               int realOrder;
               if (OptFilterTaps.Automatic$.MODULE$.equals(optFilterOrder)) {
                  realOrder = 31;
               } else {
                  if (!(optFilterOrder instanceof OptFilterTaps.IntOpt)) {
                     throw new MatchError(optFilterOrder);
                  }

                  OptFilterTaps.IntOpt var12 = (OptFilterTaps.IntOpt)optFilterOrder;
                  int ord = var12.n();
                  realOrder = ord;
               }

               FIRKernel1D var7 = breeze.signal.package$.MODULE$.designFilterFirwin(realOrder, (DenseVector)DenseVector$.MODULE$.apply(.MODULE$.wrapDoubleArray(new double[]{(double)1.0F / (double)factor}), scala.reflect.ClassTag..MODULE$.Double()), (double)1.0F, true, true, multiplier, optWindow, CanFirwin$.MODULE$.firwinDouble());
               return var7;
            } else if (optDesignMethod != null) {
               throw new IllegalArgumentException((new StringBuilder(35)).append("Design method ").append(optDesignMethod).append("is not supported yet!").toString());
            } else {
               throw new MatchError(optDesignMethod);
            }
         }
      };
   }

   public CanDesignFilterDecimation decimationFilterLong() {
      return new CanDesignFilterDecimation() {
         public FIRKernel1D apply(final int factor, final double multiplier, final OptDesignMethod optDesignMethod, final OptWindowFunction optWindow, final OptFilterTaps optFilterOrder) {
            FIRKernel1D temp = (FIRKernel1D)breeze.signal.package$.MODULE$.designFilterDecimation(factor, multiplier, optDesignMethod, optWindow, optFilterOrder, CanDesignFilterDecimation$.MODULE$.decimationFilterDouble());
            return new FIRKernel1D((DenseVector)convert$.MODULE$.apply(temp.kernel(), scala.Long..MODULE$, HasOps$.MODULE$.canMapV1DV(DenseVector$.MODULE$.DV_scalarOf(), convert$.MODULE$.impl2_Double_Long(), DenseVector$.MODULE$.DV_canMapValues(scala.reflect.ClassTag..MODULE$.Long()))), (double)((long)temp.multiplier()), temp.designText());
         }
      };
   }

   private CanDesignFilterDecimation$() {
   }
}
