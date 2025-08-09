package breeze.signal.support;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.math.Complex;
import breeze.math.Complex$;
import java.lang.invoke.SerializedLambda;
import org.jtransforms.fft.DoubleFFT_1D;
import org.jtransforms.fft.DoubleFFT_2D;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

public final class JTransformsSupport$ {
   public static final JTransformsSupport$ MODULE$ = new JTransformsSupport$();
   private static final ThreadLocal fft_instD1D = new ThreadLocal();
   private static final ThreadLocal fft_instD2D = new ThreadLocal();

   private ThreadLocal fft_instD1D() {
      return fft_instD1D;
   }

   public DoubleFFT_1D getD1DInstance(final int length) {
      DoubleFFT_1D var10000;
      if (this.fft_instD1D().get() != null && length == ((Tuple2)this.fft_instD1D().get())._1$mcI$sp()) {
         var10000 = (DoubleFFT_1D)((Tuple2)this.fft_instD1D().get())._2();
      } else {
         this.fft_instD1D().set(new Tuple2(BoxesRunTime.boxToInteger(length), new DoubleFFT_1D((long)length)));
         var10000 = (DoubleFFT_1D)((Tuple2)this.fft_instD1D().get())._2();
      }

      return var10000;
   }

   private ThreadLocal fft_instD2D() {
      return fft_instD2D;
   }

   public DoubleFFT_2D getD2DInstance(final int rows, final int columns) {
      Tuple3 inst = (Tuple3)this.fft_instD2D().get();
      DoubleFFT_2D var10000;
      if (inst != null && rows == BoxesRunTime.unboxToInt(inst._1()) && columns == BoxesRunTime.unboxToInt(inst._2())) {
         var10000 = (DoubleFFT_2D)inst._3();
      } else {
         this.fft_instD2D().set(new Tuple3(BoxesRunTime.boxToInteger(rows), BoxesRunTime.boxToInteger(columns), new DoubleFFT_2D((long)rows, (long)columns)));
         var10000 = (DoubleFFT_2D)((Tuple3)this.fft_instD2D().get())._3();
      }

      return var10000;
   }

   public DenseVector tempToDenseVector(final double[] tempArr) {
      DenseVector tempRet = DenseVector$.MODULE$.zeros(tempArr.length / 2, .MODULE$.apply(Complex.class), Complex$.MODULE$.ComplexZero());
      int index$macro$2 = 0;

      for(int limit$macro$4 = tempRet.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
         tempRet.update(index$macro$2, new Complex(tempArr[2 * index$macro$2], tempArr[2 * index$macro$2 + 1]));
      }

      return tempRet;
   }

   public double[] denseVectorCToTemp(final DenseVector tempDV) {
      double[] tempRet = new double[tempDV.length() * 2];
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), tempDV.length()).foreach$mVc$sp((JFunction1.mcVI.sp)(n) -> {
         Complex var4 = (Complex)tempDV.apply(n);
         if (var4 != null) {
            double re = var4.real();
            double im = var4.imag();
            tempRet[2 * n] = re;
            tempRet[2 * n + 1] = im;
            BoxedUnit var3 = BoxedUnit.UNIT;
         } else {
            throw new MatchError(var4);
         }
      });
      return tempRet;
   }

   public double[] denseVectorDToTemp(final DenseVector tempDV) {
      double[] tempArr = new double[tempDV.length() * 2];
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), tempDV.length()).foreach$mVc$sp((JFunction1.mcVI.sp)(n) -> tempArr[n] = tempDV.apply$mcD$sp(n));
      return tempArr;
   }

   public double[] denseMatrixCToTemp(final DenseMatrix tempDM) {
      int tempCols = tempDM.cols();
      double[] tempRet = new double[tempDM.rows() * tempCols * 2];
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), tempDM.rows()).foreach$mVc$sp((JFunction1.mcVI.sp)(r) -> scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), tempDM.cols()).foreach$mVc$sp((JFunction1.mcVI.sp)(c) -> {
            Complex var6 = (Complex)tempDM.apply(r, c);
            if (var6 != null) {
               double re = var6.real();
               double im = var6.imag();
               int ind = r * 2 * tempCols + 2 * c;
               tempRet[ind] = re;
               tempRet[ind + 1] = im;
               BoxedUnit var5 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(var6);
            }
         }));
      return tempRet;
   }

   public double[] denseMatrixDToTemp(final DenseMatrix tempDM) {
      int tempCols = tempDM.cols();
      double[] tempRet = new double[tempDM.rows() * tempCols * 2];
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), tempDM.rows()).foreach$mVc$sp((JFunction1.mcVI.sp)(r) -> scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), tempDM.cols()).foreach$mVc$sp((JFunction1.mcVI.sp)(c) -> tempRet[r * 2 * tempCols + 2 * c] = tempDM.apply$mcD$sp(r, c)));
      return tempRet;
   }

   public DenseMatrix tempToDenseMatrix(final double[] tempArr, final int rows, final int cols) {
      DenseMatrix tempRet = DenseMatrix$.MODULE$.zeros(rows, cols, .MODULE$.apply(Complex.class), Complex$.MODULE$.ComplexZero());
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), rows).foreach$mVc$sp((JFunction1.mcVI.sp)(r) -> scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), cols).foreach$mVc$sp((JFunction1.mcVI.sp)(c) -> {
            int ind = r * 2 * cols + 2 * c;
            tempRet.update(r, c, new Complex(tempArr[ind], tempArr[ind + 1]));
         }));
      return tempRet;
   }

   private JTransformsSupport$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
