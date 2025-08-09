package breeze.numerics.financial;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.SliceVector$;
import breeze.linalg.argmin$;
import breeze.linalg.eig;
import breeze.linalg.eig$;
import breeze.linalg.reverse$;
import breeze.linalg.operators.HasOps$;
import breeze.math.Complex;
import breeze.math.Complex$;
import breeze.numerics.package$abs$absDoubleImpl$;
import breeze.numerics.package$log$logDoubleImpl$;
import breeze.numerics.package$pow$powDoubleDoubleImpl$;
import breeze.storage.Zero$;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.immutable.IndexedSeq;
import scala.math.package.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

public final class package$ {
   public static final package$ MODULE$ = new package$();

   public double futureValue(final double rate, final int numPeriods, final double payment, final double presentValue, final package.PaymentTime when) {
      boolean cond$macro$1 = numPeriods >= 0;
      if (!cond$macro$1) {
         throw new IllegalArgumentException("requirement failed: numPeriods.>=(0)");
      } else {
         double var10000;
         if (rate == (double)0) {
            var10000 = (double)-1 * (presentValue + payment * (double)numPeriods);
         } else {
            double fromPv = presentValue * .MODULE$.pow((double)1.0F + rate, (double)numPeriods);
            double fromPayments = payment * (((double)1.0F + rate * (double)when.t()) / rate) * (.MODULE$.pow((double)1.0F + rate, (double)numPeriods) - (double)1.0F);
            var10000 = (double)-1 * (fromPv + fromPayments);
         }

         return var10000;
      }
   }

   public package.PaymentTime futureValue$default$5() {
      return package.End$.MODULE$;
   }

   public double presentValue(final double rate, final int numPeriods, final double payment, final double futureValue, final package.PaymentTime when) {
      boolean cond$macro$1 = numPeriods >= 0;
      if (!cond$macro$1) {
         throw new IllegalArgumentException("requirement failed: numPeriods.>=(0)");
      } else {
         double var10000;
         if (rate == (double)0) {
            var10000 = (double)-1 * (futureValue + payment * (double)numPeriods);
         } else {
            double denominator = .MODULE$.pow((double)1.0F + rate, (double)numPeriods);
            double fromPayments = payment * (((double)1.0F + rate * (double)when.t()) / rate) * (.MODULE$.pow((double)1.0F + rate, (double)numPeriods) - (double)1.0F);
            var10000 = (double)-1 * (futureValue + fromPayments) / denominator;
         }

         return var10000;
      }
   }

   public package.PaymentTime presentValue$default$5() {
      return package.End$.MODULE$;
   }

   public double payment(final double rate, final int numPeriods, final double presentValue, final double futureValue, final package.PaymentTime when) {
      double var10000;
      if (rate == (double)0) {
         var10000 = (double)-1 * (futureValue + presentValue) / (double)numPeriods;
      } else {
         double denominator = ((double)1.0F + rate * (double)when.t()) / rate * (.MODULE$.pow((double)1.0F + rate, (double)numPeriods) - (double)1.0F);
         var10000 = (double)-1 * (futureValue + presentValue * .MODULE$.pow((double)1.0F + rate, (double)numPeriods)) / denominator;
      }

      return var10000;
   }

   public double payment$default$4() {
      return (double)0.0F;
   }

   public package.PaymentTime payment$default$5() {
      return package.End$.MODULE$;
   }

   public Tuple3 principalInterest(final double rate, final int numPeriods, final double presentValue, final double futureValue, final package.PaymentTime when) {
      package.Start$ var9 = package.Start$.MODULE$;
      if (when == null) {
         if (var9 == null) {
            throw new IllegalArgumentException("This method is broken for payment at the start of the period!");
         }
      } else if (when.equals(var9)) {
         throw new IllegalArgumentException("This method is broken for payment at the start of the period!");
      }

      double pmt = this.payment(rate, numPeriods, presentValue, futureValue, when);
      DenseVector interestPayment = DenseVector$.MODULE$.zeros$mDc$sp(numPeriods, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      DenseVector principalPayment = DenseVector$.MODULE$.zeros$mDc$sp(numPeriods, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      DenseVector principalRemaining = DenseVector$.MODULE$.zeros$mDc$sp(numPeriods, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      double principal = presentValue;
      double interest = presentValue * rate;
      int index$macro$2 = 0;

      for(int limit$macro$4 = numPeriods; index$macro$2 < limit$macro$4; ++index$macro$2) {
         double ip = (double)-1 * .MODULE$.max(interest, (double)0.0F);
         interest += ip;
         principal += pmt - ip;
         principalRemaining.update$mcD$sp(index$macro$2, principal);
         interestPayment.update$mcD$sp(index$macro$2, ip);
         principalPayment.update$mcD$sp(index$macro$2, pmt - ip);
         interest += (principal + interest) * rate;
      }

      return new Tuple3(principalPayment, interestPayment, principalRemaining);
   }

   public double principalInterest$default$4() {
      return (double)0.0F;
   }

   public package.PaymentTime principalInterest$default$5() {
      return package.End$.MODULE$;
   }

   public DenseVector interestPayments(final double rate, final int numPeriods, final double presentValue, final double futureValue, final package.PaymentTime when) {
      return (DenseVector)this.principalInterest(rate, numPeriods, presentValue, futureValue, when)._1();
   }

   public double interestPayments$default$4() {
      return (double)0.0F;
   }

   public package.PaymentTime interestPayments$default$5() {
      return package.End$.MODULE$;
   }

   public DenseVector principalPayments(final double rate, final int numPeriods, final double presentValue, final double futureValue, final package.PaymentTime when) {
      return (DenseVector)this.principalInterest(rate, numPeriods, presentValue, futureValue, when)._2();
   }

   public double principalPayments$default$4() {
      return (double)0.0F;
   }

   public package.PaymentTime principalPayments$default$5() {
      return package.End$.MODULE$;
   }

   public DenseVector principalRemaining(final double rate, final int numPeriods, final double presentValue, final double futureValue, final package.PaymentTime when) {
      return (DenseVector)this.principalInterest(rate, numPeriods, presentValue, futureValue, when)._3();
   }

   public double principalRemaining$default$4() {
      return (double)0.0F;
   }

   public package.PaymentTime principalRemaining$default$5() {
      return package.End$.MODULE$;
   }

   private DenseVector roots(final DenseVector coeffs) {
      double[] coeffsArray = coeffs.toArray$mcD$sp(scala.reflect.ClassTag..MODULE$.Double());
      Object qual$1 = scala.Predef..MODULE$.doubleArrayOps(coeffsArray);
      Function1 x$1 = (x$1x) -> (double)0 != x$1x;
      int x$2 = scala.collection.ArrayOps..MODULE$.indexWhere$default$2$extension(qual$1);
      int trailingZeros = scala.collection.ArrayOps..MODULE$.indexWhere$extension(qual$1, x$1, x$2);
      Object qual$2 = scala.Predef..MODULE$.doubleArrayOps(coeffsArray);
      Function1 x$3 = (x$2x) -> (double)0 != x$2x;
      int x$4 = scala.collection.ArrayOps..MODULE$.lastIndexWhere$default$2$extension(qual$2);
      int tailZerosIdx = scala.collection.ArrayOps..MODULE$.lastIndexWhere$extension(qual$2, x$3, x$4);
      DenseVector nonZeroCoeffs = coeffs.slice$mcD$sp(trailingZeros, tailZerosIdx + 1, coeffs.slice$default$3());
      int N = nonZeroCoeffs.length() - 1;
      DenseVector var10000;
      if (0 < N) {
         DenseMatrix A = DenseMatrix$.MODULE$.zeros$mDc$sp(N, N, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
         IndexedSeq downDiagIdxs = scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(1), N).map((i) -> $anonfun$roots$3(BoxesRunTime.unboxToInt(i)));
         ((NumericOps)A.apply(downDiagIdxs, HasOps$.MODULE$.canSliceTensor(scala.reflect.ClassTag..MODULE$.Double()))).$colon$eq(BoxesRunTime.boxToDouble((double)1.0F), HasOps$.MODULE$.castUpdateOps_V_S(SliceVector$.MODULE$.scalarOf(), scala..less.colon.less..MODULE$.refl(), breeze.gymnastics.NotGiven..MODULE$.neq(), HasOps$.MODULE$.impl_Op_InPlace_V_S_Double_OpSet()));
         ((NumericOps)A.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), 1), scala.package..MODULE$.$colon$colon(), HasOps$.MODULE$.canSliceRows())).$colon$eq(((ImmutableNumericOps)nonZeroCoeffs.apply(scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(1), N), HasOps$.MODULE$.canSlice_DV_Range_eq_DV())).$div$colon$div(BoxesRunTime.boxToDouble(-nonZeroCoeffs.apply$mcD$sp(0)), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpDiv()), HasOps$.MODULE$.setMV_D());
         eig.Eig rootEig = (eig.Eig)eig$.MODULE$.apply(A, eig.Eig_DM_Impl$.MODULE$);
         int nonZeroEigNum = ((DenseVector)rootEig.eigenvalues()).length();
         DenseVector complexEig = DenseVector$.MODULE$.zeros(nonZeroEigNum, scala.reflect.ClassTag..MODULE$.apply(Complex.class), Complex$.MODULE$.ComplexZero());
         scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), nonZeroEigNum).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> complexEig.update(i, new Complex(((DenseVector)rootEig.eigenvalues()).apply$mcD$sp(i), ((DenseVector)rootEig.eigenvaluesComplex()).apply$mcD$sp(i))));
         var10000 = complexEig;
      } else {
         var10000 = DenseVector$.MODULE$.zeros(N + 1, scala.reflect.ClassTag..MODULE$.apply(Complex.class), Complex$.MODULE$.ComplexZero());
      }

      DenseVector complexRoots = var10000;
      DenseVector fullRoots = 0 < trailingZeros ? DenseVector$.MODULE$.vertcat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new DenseVector[]{complexRoots, DenseVector$.MODULE$.zeros(trailingZeros, scala.reflect.ClassTag..MODULE$.apply(Complex.class), Complex$.MODULE$.ComplexZero())}), HasOps$.MODULE$.impl_OpSet_InPlace_DV_DV(), scala.reflect.ClassTag..MODULE$.apply(Complex.class), Complex$.MODULE$.ComplexZero()) : complexRoots;
      return fullRoots;
   }

   public Option interalRateReturn(final DenseVector cashflow) {
      boolean cond$macro$1 = cashflow.apply$mcD$sp(0) < (double)0;
      if (!cond$macro$1) {
         throw new IllegalArgumentException("requirement failed: Input cash flows per time period. The cashflow(0) represent the initial invesment which should be negative!: cashflow.apply(0).<(0)");
      } else {
         DenseVector res = this.roots((DenseVector)reverse$.MODULE$.apply(cashflow, reverse$.MODULE$.dvReverse(scala.reflect.ClassTag..MODULE$.Double())));
         DenseVector realRes = DenseVector$.MODULE$.apply(scala.collection.ArrayOps..MODULE$.withFilter$extension(scala.Predef..MODULE$.refArrayOps(res.toArray(scala.reflect.ClassTag..MODULE$.apply(Complex.class))), (check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$interalRateReturn$1(check$ifrefutable$1))).withFilter((c) -> BoxesRunTime.boxToBoolean($anonfun$interalRateReturn$2(c))).map((c) -> BoxesRunTime.boxToDouble($anonfun$interalRateReturn$3(c)), scala.reflect.ClassTag..MODULE$.Double()));
         DenseVector rates = (DenseVector)realRes.mapValues$mcD$sp((JFunction1.mcDD.sp)(v) -> (double)1.0F / v - (double)1.0F, DenseVector$.MODULE$.DV_canMapValues$mDDc$sp(scala.reflect.ClassTag..MODULE$.Double()));
         Option rate = (Option)(rates.length() <= 0 ? scala.None..MODULE$ : scala.Option..MODULE$.apply(BoxesRunTime.boxToDouble(rates.apply$mcD$sp(BoxesRunTime.unboxToInt(argmin$.MODULE$.apply(breeze.numerics.package.abs$.MODULE$.apply(rates, HasOps$.MODULE$.fromLowOrderCanMapActiveValues(DenseVector$.MODULE$.DV_scalarOf(), package$abs$absDoubleImpl$.MODULE$, DenseVector$.MODULE$.DV_canMapValues$mDDc$sp(scala.reflect.ClassTag..MODULE$.Double()))), argmin$.MODULE$.reduce_Double(HasOps$.MODULE$.DV_canTraverseKeyValuePairs())))))));
         return rate;
      }
   }

   public double modifiedInternalRateReturn(final DenseVector values, final double financeRate, final double reinvestRate) {
      int n = values.length();
      int posCnt = values.valuesIterator().count((JFunction1.mcZD.sp)(x$3) -> (double)0 < x$3);
      DenseVector positives = (DenseVector)values.mapValues$mcD$sp((JFunction1.mcDD.sp)(x) -> (double)0 < x ? x : (double)0.0F, DenseVector$.MODULE$.DV_canMapValues$mDDc$sp(scala.reflect.ClassTag..MODULE$.Double()));
      int negCnt = values.valuesIterator().count((JFunction1.mcZD.sp)(x$4) -> x$4 < (double)0);
      DenseVector negatives = (DenseVector)values.mapValues$mcD$sp((JFunction1.mcDD.sp)(x) -> x < (double)0 ? x : (double)0.0F, DenseVector$.MODULE$.DV_canMapValues$mDDc$sp(scala.reflect.ClassTag..MODULE$.Double()));
      if (posCnt != 0 && negCnt != 0) {
         double inflowNPV = BoxesRunTime.unboxToDouble(package.netPresentValue$.MODULE$.apply(BoxesRunTime.boxToDouble(reinvestRate), positives, package.netPresentValue$.MODULE$.reduce_Double(HasOps$.MODULE$.DV_canIterateValues())));
         double outflowNPV = BoxesRunTime.unboxToDouble(package.netPresentValue$.MODULE$.apply(BoxesRunTime.boxToDouble(financeRate), negatives, package.netPresentValue$.MODULE$.reduce_Double(HasOps$.MODULE$.DV_canIterateValues())));
         double mirr = breeze.numerics.package.pow$.MODULE$.apply$mDDDc$sp(.MODULE$.abs(inflowNPV / outflowNPV), (double)1.0F / (double)(n - 1), package$pow$powDoubleDoubleImpl$.MODULE$) * ((double)1.0F + reinvestRate) - (double)1.0F;
         return mirr;
      } else {
         throw new IllegalArgumentException("The values must has one positive and negative value!");
      }
   }

   public double modifiedInternalRateReturn$default$3() {
      return (double)0.0F;
   }

   public double numberPeriodicPayments(final double rate, final double pmt, final double pv, final double fv, final package.PaymentTime when) {
      int right$macro$2 = 0;
      if (pmt == (double)0) {
         throw new IllegalArgumentException("requirement failed: The payment of annuity(pmt) can not be zero!: pmt != 0");
      } else {
         double var10000;
         if ((double)0 == rate) {
            var10000 = (-fv + pv) / pmt;
         } else {
            double z = pmt * ((double)1.0F + rate * (double)when.t()) / rate;
            var10000 = breeze.numerics.package.log$.MODULE$.apply$mDDc$sp((z - fv) / (z + pv), package$log$logDoubleImpl$.MODULE$) / breeze.numerics.package.log$.MODULE$.apply$mDDc$sp((double)1.0F + rate, package$log$logDoubleImpl$.MODULE$);
         }

         double nper = var10000;
         return nper;
      }
   }

   public double numberPeriodicPayments$default$4() {
      return (double)0.0F;
   }

   public package.PaymentTime numberPeriodicPayments$default$5() {
      return package.End$.MODULE$;
   }

   public Option ratePeriodicPayments(final double nper, final double pmt, final double pv, final double fv, final package.PaymentTime when, final double guess, final double tol, final int maxiter) {
      double rate = guess;
      int iter = 0;

      boolean close;
      double nextRate;
      for(close = false; iter < maxiter && !close; rate = nextRate) {
         nextRate = rate - this.annuityFDivGradf(nper, pmt, pv, fv, when, rate);
         double diff = breeze.numerics.package.abs$.MODULE$.apply$mDDc$sp(nextRate - rate, package$abs$absDoubleImpl$.MODULE$);
         close = diff < tol;
         ++iter;
      }

      return (Option)(close ? scala.Option..MODULE$.apply(BoxesRunTime.boxToDouble(rate)) : scala.None..MODULE$);
   }

   public package.PaymentTime ratePeriodicPayments$default$5() {
      return package.End$.MODULE$;
   }

   public double ratePeriodicPayments$default$6() {
      return 0.1;
   }

   public double ratePeriodicPayments$default$7() {
      return 1.0E-6;
   }

   public int ratePeriodicPayments$default$8() {
      return 100;
   }

   private double annuityFDivGradf(final double nper, final double pmt, final double pv, final double fv, final package.PaymentTime when, final double rate) {
      double t1 = breeze.numerics.package.pow$.MODULE$.apply$mDDDc$sp((double)1.0F + rate, nper, package$pow$powDoubleDoubleImpl$.MODULE$);
      double t2 = breeze.numerics.package.pow$.MODULE$.apply$mDDDc$sp((double)1.0F + rate, nper - (double)1.0F, package$pow$powDoubleDoubleImpl$.MODULE$);
      double annuityF = fv + pv * t1 + pmt * (t1 - (double)1) * ((double)1.0F + rate * (double)when.t()) / rate;
      double gradAnnuityF = nper * t2 * pv - pmt * (t1 - (double)1.0F) * ((double)1.0F + rate * (double)when.t()) / breeze.numerics.package.pow$.MODULE$.apply$mDDDc$sp(rate, (double)2.0F, package$pow$powDoubleDoubleImpl$.MODULE$) + nper * pmt * t2 * ((double)1.0F + rate * (double)when.t()) / rate + pmt * (t1 - (double)1) * (double)when.t() / rate;
      double fDivGradF = annuityF / gradAnnuityF;
      return fDivGradF;
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$roots$3(final int i) {
      return new Tuple2.mcII.sp(i, i - 1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$interalRateReturn$1(final Complex check$ifrefutable$1) {
      boolean var1;
      if (check$ifrefutable$1 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$interalRateReturn$2(final Complex c) {
      return c.im() == (double)0 && c.re() > (double)0;
   }

   // $FF: synthetic method
   public static final double $anonfun$interalRateReturn$3(final Complex c) {
      return c.re();
   }

   private package$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
