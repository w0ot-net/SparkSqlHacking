package breeze.signal.support;

import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.signal.OptDesignMethod;
import breeze.signal.OptOverhang;
import breeze.signal.OptPadding;
import breeze.signal.OptWindowFunction;
import java.lang.invoke.SerializedLambda;
import scala.runtime.ScalaRunTime.;

public final class CanFilterLPHP$ {
   public static final CanFilterLPHP$ MODULE$ = new CanFilterLPHP$();
   private static final CanFilterLPHP dvDouble1DFilterLPHP = new CanFilterLPHP() {
      public DenseVector apply(final DenseVector data, final double omega, final double sampleRate, final int taps, final boolean lowPass, final OptDesignMethod kernelType, final OptOverhang overhang, final OptPadding padding) {
         FIRKernel1D kernel;
         if (OptDesignMethod.Firwin$.MODULE$.equals(kernelType)) {
            DenseVector x$2 = (DenseVector)DenseVector$.MODULE$.apply(.MODULE$.wrapDoubleArray(new double[]{omega}), scala.reflect.ClassTag..MODULE$.Double());
            double x$4 = sampleRate / (double)2.0F;
            boolean x$5 = breeze.signal.package$.MODULE$.designFilterFirwin$default$5();
            double x$6 = breeze.signal.package$.MODULE$.designFilterFirwin$default$6();
            OptWindowFunction x$7 = breeze.signal.package$.MODULE$.designFilterFirwin$default$7();
            kernel = breeze.signal.package$.MODULE$.designFilterFirwin(taps, x$2, x$4, lowPass, x$5, x$6, x$7, CanFirwin$.MODULE$.firwinDouble());
         } else {
            scala.Predef..MODULE$.require(false, () -> (new StringBuilder(27)).append("Cannot handle option value ").append(kernelType).toString());
            kernel = new FIRKernel1D((DenseVector)DenseVector$.MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.Double()), (double)1.0F, "null kernel!");
         }

         return (DenseVector)breeze.signal.package$.MODULE$.filter(data, kernel, overhang, padding, CanFilter$.MODULE$.dvDouble1DFilter());
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   };

   public CanFilterLPHP dvDouble1DFilterLPHP() {
      return dvDouble1DFilterLPHP;
   }

   private CanFilterLPHP$() {
   }
}
