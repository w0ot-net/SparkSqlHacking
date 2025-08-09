package breeze.signal.support;

import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.convert$;
import breeze.linalg.diff$;
import breeze.linalg.max$;
import breeze.linalg.min$;
import breeze.linalg.sum$;
import breeze.linalg.operators.HasOps$;
import breeze.math.Semiring$;
import breeze.numerics.package$cos$cosDoubleImpl$;
import breeze.numerics.package$sincpi$sincpiDoubleImpl$;
import breeze.signal.OptWindowFunction;
import breeze.storage.Zero$;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Int.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

public final class CanFirwin$ {
   public static final CanFirwin$ MODULE$ = new CanFirwin$();

   public CanFirwin firwinDouble() {
      return new CanFirwin() {
         public FIRKernel1D apply(final int taps, final DenseVector omegas, final double nyquist, final boolean zeroPass, final boolean scale, final double multiplier, final OptWindowFunction optWindow) {
            return new FIRKernel1D((DenseVector)CanFirwin$.MODULE$.firwinDoubleImpl(taps, omegas, nyquist, zeroPass, scale, optWindow).$times(BoxesRunTime.boxToDouble(multiplier), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulMatrix()), multiplier, (new StringBuilder(59)).append("FIRKernel1D(firwin): ").append(taps).append(" taps, ").append(omegas).append(", ").append(optWindow).append(", zeroPass=").append(zeroPass).append(", nyquist=").append(nyquist).append(", scale=").append(scale).toString());
         }
      };
   }

   public CanFirwin firwinT_Int() {
      return new CanFirwin() {
         public FIRKernel1D apply(final int taps, final DenseVector omegas, final double nyquist, final boolean zeroPass, final boolean scale, final double multiplier, final OptWindowFunction optWindow) {
            return new FIRKernel1D((DenseVector)convert$.MODULE$.apply(CanFirwin$.MODULE$.firwinDoubleImpl(taps, omegas, nyquist, zeroPass, scale, optWindow).$times(BoxesRunTime.boxToDouble(multiplier), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulMatrix()), .MODULE$, HasOps$.MODULE$.canMapV1DV(DenseVector$.MODULE$.DV_scalarOf(), convert$.MODULE$.impl2_Double_Int(), DenseVector$.MODULE$.DV_canMapValues$mDIc$sp(scala.reflect.ClassTag..MODULE$.Int()))), multiplier, (new StringBuilder(59)).append("FIRKernel1D(firwin): ").append(taps).append(" taps, ").append(omegas).append(", ").append(optWindow).append(", zeroPass=").append(zeroPass).append(", nyquist=").append(nyquist).append(", scale=").append(scale).toString());
         }
      };
   }

   public CanFirwin firwinT_Long() {
      return new CanFirwin() {
         public FIRKernel1D apply(final int taps, final DenseVector omegas, final double nyquist, final boolean zeroPass, final boolean scale, final double multiplier, final OptWindowFunction optWindow) {
            return new FIRKernel1D((DenseVector)convert$.MODULE$.apply(CanFirwin$.MODULE$.firwinDoubleImpl(taps, omegas, nyquist, zeroPass, scale, optWindow).$times(BoxesRunTime.boxToDouble(multiplier), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulMatrix()), scala.Long..MODULE$, HasOps$.MODULE$.canMapV1DV(DenseVector$.MODULE$.DV_scalarOf(), convert$.MODULE$.impl2_Double_Long(), DenseVector$.MODULE$.DV_canMapValues(scala.reflect.ClassTag..MODULE$.Long()))), multiplier, (new StringBuilder(59)).append("FIRKernel1D(firwin): ").append(taps).append(" taps, ").append(omegas).append(", ").append(optWindow).append(", zeroPass=").append(zeroPass).append(", nyquist=").append(nyquist).append(", scale=").append(scale).toString());
         }
      };
   }

   public CanFirwin firwinT_Float() {
      return new CanFirwin() {
         public FIRKernel1D apply(final int taps, final DenseVector omegas, final double nyquist, final boolean zeroPass, final boolean scale, final double multiplier, final OptWindowFunction optWindow) {
            return new FIRKernel1D((DenseVector)convert$.MODULE$.apply(CanFirwin$.MODULE$.firwinDoubleImpl(taps, omegas, nyquist, zeroPass, scale, optWindow).$times(BoxesRunTime.boxToDouble(multiplier), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulMatrix()), scala.Float..MODULE$, HasOps$.MODULE$.canMapV1DV(DenseVector$.MODULE$.DV_scalarOf(), convert$.MODULE$.impl2_Double_Float(), DenseVector$.MODULE$.DV_canMapValues$mDFc$sp(scala.reflect.ClassTag..MODULE$.Float()))), multiplier, (new StringBuilder(59)).append("FIRKernel1D(firwin): ").append(taps).append(" taps, ").append(omegas).append(", ").append(optWindow).append(", zeroPass=").append(zeroPass).append(", nyquist=").append(nyquist).append(", scale=").append(scale).toString());
         }
      };
   }

   public DenseVector firwinDoubleImpl(final int taps, final DenseVector omegas, final double nyquist, final boolean zeroPass, final boolean scale, final OptWindowFunction optWindow) {
      scala.Predef..MODULE$.require(omegas.length() > 0, () -> "At least one cutoff frequency must be given!");
      scala.Predef..MODULE$.require(BoxesRunTime.unboxToDouble(min$.MODULE$.apply(omegas, min$.MODULE$.reduce_Double(HasOps$.MODULE$.DV_canIterateValues()))) >= (double)0, () -> "The cutoff frequencies must be bigger than zero!");
      scala.Predef..MODULE$.require(BoxesRunTime.unboxToDouble(max$.MODULE$.apply(omegas, max$.MODULE$.reduce_Double(HasOps$.MODULE$.DV_canIterateValues()))) <= nyquist, () -> "The cutoff frequencies must be smaller than the nyquist frequency!");
      if (omegas.length() > 1) {
         scala.Predef..MODULE$.require(BoxesRunTime.unboxToDouble(min$.MODULE$.apply(diff$.MODULE$.apply(omegas, diff$.MODULE$.implDV_DV_Double()), min$.MODULE$.reduce_Double(HasOps$.MODULE$.DV_canIterateValues()))) > (double)0, () -> "The cutoff frequency must be monotonically increasing.");
      }

      boolean nyquistPass = zeroPass != BoxesRunTime.unboxToBoolean(breeze.numerics.package.isOdd$.MODULE$.apply(BoxesRunTime.boxToInteger(omegas.length()), breeze.numerics.package.isOdd$.MODULE$.isOddImpl_Int()));
      double[] tempCutoff = ((DenseVector)omegas.$div(BoxesRunTime.boxToDouble(nyquist), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpDiv())).toArray$mcD$sp(scala.reflect.ClassTag..MODULE$.Double());
      if (zeroPass) {
         tempCutoff = (double[])scala.collection.ArrayOps..MODULE$.$plus$colon$extension(scala.Predef..MODULE$.doubleArrayOps(tempCutoff), BoxesRunTime.boxToDouble((double)0.0F), scala.reflect.ClassTag..MODULE$.Double());
      }

      if (nyquistPass) {
         tempCutoff = (double[])scala.collection.ArrayOps..MODULE$.$colon$plus$extension(scala.Predef..MODULE$.doubleArrayOps(tempCutoff), BoxesRunTime.boxToDouble((double)1.0F), scala.reflect.ClassTag..MODULE$.Double());
      }

      DenseVector scaledCutoff = DenseVector$.MODULE$.apply$mDc$sp(tempCutoff);
      scala.Predef..MODULE$.require(!nyquistPass || !BoxesRunTime.unboxToBoolean(breeze.numerics.package.isEven$.MODULE$.apply(BoxesRunTime.boxToInteger(taps), breeze.numerics.package.isEven$.MODULE$.isEvenImpl_Int())), () -> "A filter with an even number of taps must have zero response at the Nyquist rate.");
      double alpha = (double)0.5F * (double)(taps - 1);
      DenseVector m = (DenseVector)DenseVector$.MODULE$.tabulate$mDc$sp(taps, (JFunction1.mcDI.sp)(i) -> (double)i, scala.reflect.ClassTag..MODULE$.Double()).$minus(BoxesRunTime.boxToDouble(alpha), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpSub());
      DenseVector h = DenseVector$.MODULE$.zeros$mDc$sp(m.length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.doubleArrayOps(scaledCutoff.toArray$mcD$sp(scala.reflect.ClassTag..MODULE$.Double())))), (band) -> BoxesRunTime.unboxToBoolean(breeze.numerics.package.isEven$.MODULE$.apply(BoxesRunTime.boxToInteger(band._2$mcI$sp()), breeze.numerics.package.isEven$.MODULE$.isEvenImpl_Int())) ? (DenseVector)h.$minus$eq(((ImmutableNumericOps)breeze.numerics.package.sincpi$.MODULE$.apply(m.$times$colon$times(BoxesRunTime.boxToDouble(band._1$mcD$sp()), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulScalar()), HasOps$.MODULE$.fromLowOrderCanMapValues(DenseVector$.MODULE$.DV_scalarOf(), package$sincpi$sincpiDoubleImpl$.MODULE$, DenseVector$.MODULE$.DV_canMapValues$mDDc$sp(scala.reflect.ClassTag..MODULE$.Double())))).$times$colon$times(BoxesRunTime.boxToDouble(band._1$mcD$sp()), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulScalar()), HasOps$.MODULE$.impl_OpSub_InPlace_DV_DV_Double()) : (DenseVector)h.$plus$eq(((ImmutableNumericOps)breeze.numerics.package.sincpi$.MODULE$.apply(m.$times$colon$times(BoxesRunTime.boxToDouble(band._1$mcD$sp()), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulScalar()), HasOps$.MODULE$.fromLowOrderCanMapValues(DenseVector$.MODULE$.DV_scalarOf(), package$sincpi$sincpiDoubleImpl$.MODULE$, DenseVector$.MODULE$.DV_canMapValues$mDDc$sp(scala.reflect.ClassTag..MODULE$.Double())))).$times$colon$times(BoxesRunTime.boxToDouble(band._1$mcD$sp()), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulScalar()), HasOps$.MODULE$.impl_OpAdd_InPlace_DV_DV_Double()));
      DenseVector win;
      if (optWindow instanceof OptWindowFunction.Hamming) {
         OptWindowFunction.Hamming var18 = (OptWindowFunction.Hamming)optWindow;
         double alpha = var18.alpha();
         double beta = var18.beta();
         win = WindowFunctions$.MODULE$.hammingWindow(taps, alpha, beta);
      } else if (optWindow instanceof OptWindowFunction.Hanning) {
         OptWindowFunction.Hanning var23 = (OptWindowFunction.Hanning)optWindow;
         double alpha = var23.alpha();
         double beta = var23.beta();
         win = WindowFunctions$.MODULE$.hammingWindow(taps, alpha, beta);
      } else if (optWindow instanceof OptWindowFunction.Blackman) {
         OptWindowFunction.Blackman var28 = (OptWindowFunction.Blackman)optWindow;
         double a0 = var28.a0();
         double a1 = var28.a1();
         double a2 = var28.a2();
         win = WindowFunctions$.MODULE$.blackmanWindow(taps, a0, a1, a2);
      } else if (OptWindowFunction.None$.MODULE$.equals(optWindow)) {
         win = DenseVector$.MODULE$.ones$mDc$sp(taps, scala.reflect.ClassTag..MODULE$.Double(), Semiring$.MODULE$.semiringD());
      } else {
         if (!(optWindow instanceof OptWindowFunction.User)) {
            throw new MatchError(optWindow);
         }

         OptWindowFunction.User var35 = (OptWindowFunction.User)optWindow;
         DenseVector dv = var35.dv();
         scala.Predef..MODULE$.require(dv.length() == taps, () -> "Length of specified window function is not the same as taps option!");
         win = dv;
      }

      h.$times$eq(win, HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpMulScalar());
      if (scale) {
         double scaleFrequency = scaledCutoff.apply$mcD$sp(0) == (double)0.0F ? (double)0.0F : (scaledCutoff.apply$mcD$sp(1) == (double)1.0F ? (double)1.0F : (scaledCutoff.apply$mcD$sp(0) + scaledCutoff.apply$mcD$sp(1)) / (double)2.0F);
         DenseVector c = (DenseVector)breeze.numerics.package.cos$.MODULE$.apply(m.$times$colon$times(BoxesRunTime.boxToDouble(Math.PI * scaleFrequency), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulScalar()), HasOps$.MODULE$.fromLowOrderCanMapValues(DenseVector$.MODULE$.DV_scalarOf(), package$cos$cosDoubleImpl$.MODULE$, DenseVector$.MODULE$.DV_canMapValues$mDDc$sp(scala.reflect.ClassTag..MODULE$.Double())));
         double s = BoxesRunTime.unboxToDouble(sum$.MODULE$.apply(h.$times$colon$times(c, HasOps$.MODULE$.impl_Op_DV_DV_eq_DV_Double_OpMulScalar()), sum$.MODULE$.reduce_Double(HasOps$.MODULE$.DV_canIterateValues())));
         h.$div$eq(BoxesRunTime.boxToDouble(s), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpDiv());
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return h;
   }

   private CanFirwin$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
