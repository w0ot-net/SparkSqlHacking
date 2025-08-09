package breeze.signal;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import breeze.linalg.norm$;
import breeze.linalg.operators.HasOps$;
import breeze.math.Complex;
import breeze.math.Complex$;
import breeze.signal.support.CanConvolve$;
import breeze.signal.support.CanFilterBPBS$;
import breeze.signal.support.CanFilterLPHP$;
import breeze.signal.support.CanFilterMedian$;
import breeze.signal.support.CanHaarTr$;
import breeze.storage.Zero$;
import breeze.util.JavaArrayOps$;
import scala.Tuple2;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

public final class JavaCompatible$ {
   public static final JavaCompatible$ MODULE$ = new JavaCompatible$();

   public double[] convolve(final double[] data, final double[] kernel) {
      return JavaArrayOps$.MODULE$.dvDToArray((DenseVector)package$.MODULE$.convolve(JavaArrayOps$.MODULE$.arrayDToDv(data), JavaArrayOps$.MODULE$.arrayDToDv(kernel), package$.MODULE$.convolve$default$3(), package$.MODULE$.convolve$default$4(), package$.MODULE$.convolve$default$5(), package$.MODULE$.convolve$default$6(), CanConvolve$.MODULE$.dvT1DConvolve_Double()));
   }

   public double[] correlate(final double[] data, final double[] kernel) {
      return JavaArrayOps$.MODULE$.dvDToArray((DenseVector)package$.MODULE$.correlate(JavaArrayOps$.MODULE$.arrayDToDv(data), JavaArrayOps$.MODULE$.arrayDToDv(kernel), package$.MODULE$.correlate$default$3(), package$.MODULE$.correlate$default$4(), package$.MODULE$.correlate$default$5(), package$.MODULE$.correlate$default$6(), CanConvolve$.MODULE$.dvT1DConvolve_Double()));
   }

   public Complex[] fourierTrD(final double[] data) {
      return JavaArrayOps$.MODULE$.dvCToArray((DenseVector)fourierTr$.MODULE$.apply(JavaArrayOps$.MODULE$.arrayDToDv(data), fourierTr$.MODULE$.dvDouble1DFFT()));
   }

   public Complex[] fourierTrC(final Complex[] data) {
      return JavaArrayOps$.MODULE$.dvCToArray((DenseVector)fourierTr$.MODULE$.apply(JavaArrayOps$.MODULE$.arrayCToDv(data), fourierTr$.MODULE$.dvComplex1DFFT()));
   }

   public Complex[] iFourierTrC(final Complex[] data) {
      return JavaArrayOps$.MODULE$.dvCToArray((DenseVector)iFourierTr$.MODULE$.apply(JavaArrayOps$.MODULE$.arrayCToDv(data), iFourierTr$.MODULE$.dvComplexIFFT()));
   }

   public Complex[][] fourierTr2C(final Complex[][] data) {
      return JavaArrayOps$.MODULE$.dmCToArray2((DenseMatrix)fourierTr$.MODULE$.apply(JavaArrayOps$.MODULE$.array2CToDm(data), fourierTr$.MODULE$.dmComplex2DFFT()));
   }

   public double[] fourierShiftD(final double[] data) {
      return JavaArrayOps$.MODULE$.dvDToArray((DenseVector)fourierShift$.MODULE$.apply(JavaArrayOps$.MODULE$.arrayDToDv(data), fourierShift$.MODULE$.implFourierShift(Zero$.MODULE$.DoubleZero(), .MODULE$.Double())));
   }

   public Complex[] fourierShiftC(final Complex[] data) {
      return JavaArrayOps$.MODULE$.dvCToArray((DenseVector)fourierShift$.MODULE$.apply(JavaArrayOps$.MODULE$.arrayCToDv(data), fourierShift$.MODULE$.implFourierShift(Complex$.MODULE$.ComplexZero(), .MODULE$.apply(Complex.class))));
   }

   public double[] iFourierShiftD(final double[] data) {
      return JavaArrayOps$.MODULE$.dvDToArray((DenseVector)iFourierShift$.MODULE$.apply(JavaArrayOps$.MODULE$.arrayDToDv(data), iFourierShift$.MODULE$.implIFourierShift(Zero$.MODULE$.DoubleZero(), .MODULE$.Double())));
   }

   public Complex[] iFourierShiftC(final Complex[] data) {
      return JavaArrayOps$.MODULE$.dvCToArray((DenseVector)iFourierShift$.MODULE$.apply(JavaArrayOps$.MODULE$.arrayCToDv(data), iFourierShift$.MODULE$.implIFourierShift(Complex$.MODULE$.ComplexZero(), .MODULE$.apply(Complex.class))));
   }

   public double[] fourierFreqD(final int windowLength, final double fs, final boolean shifted) {
      return JavaArrayOps$.MODULE$.dvDToArray(package$.MODULE$.fourierFreq(windowLength, fs, (double)-1.0F, shifted));
   }

   public double[] fourierFreqD(final int windowLength, final double fs) {
      return this.fourierFreqD(windowLength, fs, false);
   }

   public double[] filterBP(final double[] data, final double omegaLow, final double omegaHigh, final double sampleRate, final int taps) {
      return JavaArrayOps$.MODULE$.dvDToArray((DenseVector)package$.MODULE$.filterBP(JavaArrayOps$.MODULE$.arrayDToDv(data), new Tuple2.mcDD.sp(omegaLow, omegaHigh), sampleRate, taps, package$.MODULE$.filterBP$default$5(), package$.MODULE$.filterBP$default$6(), package$.MODULE$.filterBP$default$7(), CanFilterBPBS$.MODULE$.dvDouble1DFilterBPBS()));
   }

   public double[] filterBP(final double[] data, final double omegaLow, final double omegaHigh, final double sampleRate) {
      return this.filterBP(data, omegaLow, omegaHigh, sampleRate, 512);
   }

   public double[] filterBP(final double[] data, final double omegaLow, final double omegaHigh) {
      return this.filterBP(data, omegaLow, omegaHigh, (double)2.0F, 512);
   }

   public double[] filterBS(final double[] data, final double omegaLow, final double omegaHigh, final double sampleRate, final int taps) {
      return JavaArrayOps$.MODULE$.dvDToArray((DenseVector)package$.MODULE$.filterBS(JavaArrayOps$.MODULE$.arrayDToDv(data), new Tuple2.mcDD.sp(omegaLow, omegaHigh), sampleRate, taps, package$.MODULE$.filterBS$default$5(), package$.MODULE$.filterBS$default$6(), package$.MODULE$.filterBS$default$7(), CanFilterBPBS$.MODULE$.dvDouble1DFilterBPBS()));
   }

   public double[] filterBS(final double[] data, final double omegaLow, final double omegaHigh, final double sampleRate) {
      return this.filterBS(data, omegaLow, omegaHigh, sampleRate, 512);
   }

   public double[] filterBS(final double[] data, final double omegaLow, final double omegaHigh) {
      return this.filterBS(data, omegaLow, omegaHigh, (double)2.0F, 512);
   }

   public double[] filterLP(final double[] data, final double omega, final double sampleRate, final int taps) {
      return JavaArrayOps$.MODULE$.dvDToArray((DenseVector)package$.MODULE$.filterLP(JavaArrayOps$.MODULE$.arrayDToDv(data), omega, sampleRate, taps, package$.MODULE$.filterLP$default$5(), package$.MODULE$.filterLP$default$6(), package$.MODULE$.filterLP$default$7(), CanFilterLPHP$.MODULE$.dvDouble1DFilterLPHP()));
   }

   public double[] filterLP(final double[] data, final double omega, final double sampleRate) {
      return this.filterLP(data, omega, sampleRate, 512);
   }

   public double[] filterLP(final double[] data, final double omega) {
      return this.filterLP(data, omega, (double)2.0F, 512);
   }

   public double[] filterHP(final double[] data, final double omega, final double sampleRate, final int taps) {
      return JavaArrayOps$.MODULE$.dvDToArray((DenseVector)package$.MODULE$.filterHP(JavaArrayOps$.MODULE$.arrayDToDv(data), omega, sampleRate, taps, package$.MODULE$.filterHP$default$5(), package$.MODULE$.filterHP$default$6(), package$.MODULE$.filterHP$default$7(), CanFilterLPHP$.MODULE$.dvDouble1DFilterLPHP()));
   }

   public double[] filterHP(final double[] data, final double omega, final double sampleRate) {
      return this.filterHP(data, omega, sampleRate, 512);
   }

   public double[] filterHP(final double[] data, final double omega) {
      return this.filterHP(data, omega, (double)2.0F, 512);
   }

   public double[] haarTrD(final double[] data) {
      return JavaArrayOps$.MODULE$.dvDToArray((DenseVector)package$.MODULE$.haarTr(JavaArrayOps$.MODULE$.arrayDToDv(data), CanHaarTr$.MODULE$.dvDouble1FHT()));
   }

   public double[][] haarTr2D(final double[][] data) {
      return JavaArrayOps$.MODULE$.dmDToArray2((DenseMatrix)package$.MODULE$.haarTr(JavaArrayOps$.MODULE$.array2DToDm(data), CanHaarTr$.MODULE$.dmDouble1FHT()));
   }

   public double rootMeanSquareD(final double[] data) {
      return BoxesRunTime.unboxToDouble(rootMeanSquare$.MODULE$.apply(JavaArrayOps$.MODULE$.arrayDToDv(data), rootMeanSquare$.MODULE$.rms1D(norm$.MODULE$.fromCanNormInt(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Double())), HasOps$.MODULE$.impl_dim_DV_eq_I())));
   }

   public double[] filterMedianD(final double[] data, final int windowLength) {
      return JavaArrayOps$.MODULE$.dvDToArray(package$.MODULE$.filterMedian(JavaArrayOps$.MODULE$.arrayDToDv(data), windowLength, CanFilterMedian$.MODULE$.dvFilterMedianT_Double()));
   }

   private JavaCompatible$() {
   }
}
