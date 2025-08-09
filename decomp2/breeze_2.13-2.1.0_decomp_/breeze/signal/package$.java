package breeze.signal;

import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.operators.HasOps$;
import breeze.signal.support.CanConvolve;
import breeze.signal.support.CanDesignFilterDecimation;
import breeze.signal.support.CanFilter;
import breeze.signal.support.CanFilterBPBS;
import breeze.signal.support.CanFilterLPHP;
import breeze.signal.support.CanFilterMedian;
import breeze.signal.support.CanFirwin;
import breeze.signal.support.CanHaarTr;
import breeze.signal.support.CanIHaarTr;
import breeze.signal.support.FIRKernel1D;
import breeze.storage.Zero$;
import java.lang.invoke.SerializedLambda;
import scala.Tuple2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction1;

public final class package$ {
   public static final package$ MODULE$ = new package$();
   private static final fourierTr$ fourierTransform;
   private static final iFourierTr$ inverseFourierTransform;

   static {
      fourierTransform = fourierTr$.MODULE$;
      inverseFourierTransform = iFourierTr$.MODULE$;
   }

   public fourierTr$ fourierTransform() {
      return fourierTransform;
   }

   public iFourierTr$ inverseFourierTransform() {
      return inverseFourierTransform;
   }

   public DenseVector fourierFreq(final int windowLength, final double fs, final double dt, final boolean shifted) {
      boolean cond$macro$1 = fs > (double)0 || dt > (double)0;
      if (!cond$macro$1) {
         throw new IllegalArgumentException("requirement failed: Must specify either a valid fs or a valid dt argument.: fs.>(0).||(dt.>(0))");
      } else {
         if (fs > (double)0 && dt > (double)0) {
            double right$macro$3 = (double)1.0F / dt;
            if (fs != right$macro$3) {
               throw new IllegalArgumentException((new StringBuilder(136)).append("requirement failed: If fs and dt are both specified, fs == 1.0/dt must be true. Otherwise, they are incompatible: ").append("fs == 1.0./(dt) (").append(fs).append(" ").append("!=").append(" ").append(right$macro$3).append(")").toString());
            }
         }

         double realFs = fs < (double)0 && dt > (double)0 ? (double)1.0F / dt : fs;
         DenseVector shiftedFreq = BoxesRunTime.unboxToBoolean(breeze.numerics.package.isEven$.MODULE$.apply(BoxesRunTime.boxToInteger(windowLength), breeze.numerics.package.isEven$.MODULE$.isEvenImpl_Int())) ? DenseVector$.MODULE$.vertcat(.MODULE$.wrapRefArray(new DenseVector[]{DenseVector$.MODULE$.tabulate$mDc$sp(scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(0), windowLength / 2 - 1), (JFunction1.mcDI.sp)(i) -> (double)i * realFs / (double)windowLength, scala.reflect.ClassTag..MODULE$.Double()), DenseVector$.MODULE$.tabulate$mDc$sp(scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(-windowLength / 2), -1), (JFunction1.mcDI.sp)(i) -> (double)i * realFs / (double)windowLength, scala.reflect.ClassTag..MODULE$.Double())}), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero()) : DenseVector$.MODULE$.vertcat(.MODULE$.wrapRefArray(new DenseVector[]{DenseVector$.MODULE$.tabulate$mDc$sp(scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(0), (windowLength - 1) / 2), (JFunction1.mcDI.sp)(i) -> (double)i * realFs / (double)windowLength, scala.reflect.ClassTag..MODULE$.Double()), DenseVector$.MODULE$.tabulate$mDc$sp(scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(-(windowLength - 1) / 2), -1), (JFunction1.mcDI.sp)(i) -> (double)i * realFs / (double)windowLength, scala.reflect.ClassTag..MODULE$.Double())}), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
         return shifted ? (DenseVector)fourierShift$.MODULE$.apply(shiftedFreq, fourierShift$.MODULE$.implFourierShift(Zero$.MODULE$.DoubleZero(), scala.reflect.ClassTag..MODULE$.Double())) : shiftedFreq;
      }
   }

   public double fourierFreq$default$2() {
      return (double)-1.0F;
   }

   public double fourierFreq$default$3() {
      return (double)-1.0F;
   }

   public boolean fourierFreq$default$4() {
      return false;
   }

   public Object convolve(final Object data, final Object kernel, final OptRange range, final OptOverhang overhang, final OptPadding padding, final OptMethod method, final CanConvolve canConvolve) {
      return canConvolve.apply(data, kernel, range, false, overhang, padding, method);
   }

   public OptRange convolve$default$3() {
      return OptRange.All$.MODULE$;
   }

   public OptOverhang convolve$default$4() {
      return OptOverhang.None$.MODULE$;
   }

   public OptPadding convolve$default$5() {
      return OptPadding.Zero$.MODULE$;
   }

   public OptMethod convolve$default$6() {
      return OptMethod.Automatic$.MODULE$;
   }

   public Object correlate(final Object data, final Object kernel, final OptRange range, final OptOverhang overhang, final OptPadding padding, final OptMethod method, final CanConvolve canConvolve) {
      return canConvolve.apply(data, kernel, range, true, overhang, padding, method);
   }

   public OptRange correlate$default$3() {
      return OptRange.All$.MODULE$;
   }

   public OptOverhang correlate$default$4() {
      return OptOverhang.None$.MODULE$;
   }

   public OptPadding correlate$default$5() {
      return OptPadding.Zero$.MODULE$;
   }

   public OptMethod correlate$default$6() {
      return OptMethod.Automatic$.MODULE$;
   }

   public Object filter(final Object data, final Object kernel, final OptOverhang overhang, final OptPadding padding, final CanFilter canFilter) {
      return canFilter.apply(data, kernel, overhang, padding);
   }

   public OptOverhang filter$default$3() {
      return OptOverhang.PreserveLength$.MODULE$;
   }

   public OptPadding filter$default$4() {
      return OptPadding.Zero$.MODULE$;
   }

   public Object filterBP(final Object data, final Tuple2 omegas, final double sampleRate, final int taps, final OptDesignMethod kernelDesign, final OptOverhang overhang, final OptPadding padding, final CanFilterBPBS canFilterBPBS) {
      return canFilterBPBS.apply(data, omegas, sampleRate, taps, false, kernelDesign, overhang, padding);
   }

   public double filterBP$default$3() {
      return (double)2.0F;
   }

   public int filterBP$default$4() {
      return 512;
   }

   public OptDesignMethod filterBP$default$5() {
      return OptDesignMethod.Firwin$.MODULE$;
   }

   public OptOverhang filterBP$default$6() {
      return OptOverhang.None$.MODULE$;
   }

   public OptPadding filterBP$default$7() {
      return OptPadding.Boundary$.MODULE$;
   }

   public Object filterBS(final Object data, final Tuple2 omegas, final double sampleRate, final int taps, final OptDesignMethod kernelDesign, final OptOverhang overhang, final OptPadding padding, final CanFilterBPBS canFilterBPBS) {
      return canFilterBPBS.apply(data, omegas, sampleRate, taps, true, kernelDesign, overhang, padding);
   }

   public double filterBS$default$3() {
      return (double)2.0F;
   }

   public int filterBS$default$4() {
      return 512;
   }

   public OptDesignMethod filterBS$default$5() {
      return OptDesignMethod.Firwin$.MODULE$;
   }

   public OptOverhang filterBS$default$6() {
      return OptOverhang.None$.MODULE$;
   }

   public OptPadding filterBS$default$7() {
      return OptPadding.Boundary$.MODULE$;
   }

   public Object filterLP(final Object data, final double omega, final double sampleRate, final int taps, final OptDesignMethod kernelDesign, final OptOverhang overhang, final OptPadding padding, final CanFilterLPHP canFilterLPHP) {
      return canFilterLPHP.apply(data, omega, sampleRate, taps, true, kernelDesign, overhang, padding);
   }

   public double filterLP$default$3() {
      return (double)2.0F;
   }

   public int filterLP$default$4() {
      return 512;
   }

   public OptDesignMethod filterLP$default$5() {
      return OptDesignMethod.Firwin$.MODULE$;
   }

   public OptOverhang filterLP$default$6() {
      return OptOverhang.None$.MODULE$;
   }

   public OptPadding filterLP$default$7() {
      return OptPadding.Boundary$.MODULE$;
   }

   public Object filterHP(final Object data, final double omega, final double sampleRate, final int taps, final OptDesignMethod kernelDesign, final OptOverhang overhang, final OptPadding padding, final CanFilterLPHP canFilterLPHP) {
      return canFilterLPHP.apply(data, omega, sampleRate, taps, false, kernelDesign, overhang, padding);
   }

   public double filterHP$default$3() {
      return (double)2.0F;
   }

   public int filterHP$default$4() {
      return 512;
   }

   public OptDesignMethod filterHP$default$5() {
      return OptDesignMethod.Firwin$.MODULE$;
   }

   public OptOverhang filterHP$default$6() {
      return OptOverhang.None$.MODULE$;
   }

   public OptPadding filterHP$default$7() {
      return OptPadding.Boundary$.MODULE$;
   }

   public FIRKernel1D designFilterFirwin(final int taps, final DenseVector omegas, final double nyquist, final boolean zeroPass, final boolean scale, final double multiplier, final OptWindowFunction optWindow, final CanFirwin canFirwin) {
      return canFirwin.apply(taps, omegas, nyquist, zeroPass, scale, multiplier, optWindow);
   }

   public double designFilterFirwin$default$3() {
      return (double)1.0F;
   }

   public boolean designFilterFirwin$default$4() {
      return true;
   }

   public boolean designFilterFirwin$default$5() {
      return true;
   }

   public double designFilterFirwin$default$6() {
      return (double)1.0F;
   }

   public OptWindowFunction designFilterFirwin$default$7() {
      return new OptWindowFunction.Hamming(OptWindowFunction.Hamming$.MODULE$.apply$default$1(), OptWindowFunction.Hamming$.MODULE$.apply$default$2());
   }

   public Object designFilterDecimation(final int factor, final double multiplier, final OptDesignMethod optDesignMethod, final OptWindowFunction optWindow, final OptFilterTaps optFilterOrder, final CanDesignFilterDecimation canDesignFilterDecimation) {
      return canDesignFilterDecimation.apply(factor, multiplier, optDesignMethod, optWindow, optFilterOrder);
   }

   public double designFilterDecimation$default$2() {
      return (double)1.0F;
   }

   public OptDesignMethod designFilterDecimation$default$3() {
      return OptDesignMethod.Firwin$.MODULE$;
   }

   public OptWindowFunction designFilterDecimation$default$4() {
      return new OptWindowFunction.Hamming(OptWindowFunction.Hamming$.MODULE$.apply$default$1(), OptWindowFunction.Hamming$.MODULE$.apply$default$2());
   }

   public OptFilterTaps designFilterDecimation$default$5() {
      return OptFilterTaps.Automatic$.MODULE$;
   }

   public DenseVector filterMedian(final DenseVector data, final int windowLength, final OptOverhang overhang, final CanFilterMedian canFilterMedian) {
      return canFilterMedian.apply(data, windowLength, overhang);
   }

   public DenseVector filterMedian(final DenseVector data, final int windowLength, final CanFilterMedian canFilterMedian) {
      return canFilterMedian.apply(data, windowLength, OptOverhang.PreserveLength$.MODULE$);
   }

   public OptOverhang filterMedian$default$3() {
      return OptOverhang.PreserveLength$.MODULE$;
   }

   public Object haarTr(final Object v, final CanHaarTr canHaarTransform) {
      return canHaarTransform.apply(v);
   }

   public Object haarTransform(final Object v, final CanHaarTr canHaarTransform) {
      return canHaarTransform.apply(v);
   }

   public Object iHaarTr(final Object v, final CanIHaarTr canInverseHaarTransform) {
      return canInverseHaarTransform.apply(v);
   }

   public Object inverseHaarTransform(final Object v, final CanIHaarTr canInverseHaarTransform) {
      return canInverseHaarTransform.apply(v);
   }

   private package$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
