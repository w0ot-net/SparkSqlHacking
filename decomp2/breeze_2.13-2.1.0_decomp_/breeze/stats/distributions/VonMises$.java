package breeze.stats.distributions;

import breeze.generic.UFunc;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.DenseVector$TupleIsomorphisms$pdoubleIsVector$;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.numerics.Bessel;
import breeze.numerics.Bessel$i0$ImplDouble$;
import breeze.optimize.DiffFunction;
import breeze.optimize.OptimizationPackage$;
import breeze.optimize.StochasticDiffFunction;
import breeze.optimize.package$;
import breeze.util.Isomorphism;
import java.io.Serializable;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.math.package.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class VonMises$ implements ExponentialFamily, Serializable {
   public static final VonMises$ MODULE$ = new VonMises$();

   public VonMises.SufficientStatistic emptySufficientStatistic() {
      return new VonMises.SufficientStatistic((double)0.0F, (double)0.0F, (double)0.0F);
   }

   public VonMises.SufficientStatistic sufficientStatisticFor(final double t) {
      return new VonMises.SufficientStatistic((double)1.0F, .MODULE$.sin(t), .MODULE$.cos(t));
   }

   public VonMises distribution(final Tuple2 p, final RandBasis rand) {
      return new VonMises(p._1$mcD$sp(), p._2$mcD$sp(), rand);
   }

   public Tuple2 mle(final VonMises.SufficientStatistic stats) {
      DiffFunction lensed = this.likelihoodFunction(stats).throughLens(DenseVector$TupleIsomorphisms$pdoubleIsVector$.MODULE$);
      double cosineSum = stats.cosines();
      double sineSum = stats.sines();
      double muPart = .MODULE$.signum(cosineSum) * .MODULE$.signum(sineSum) * .MODULE$.atan(.MODULE$.abs(sineSum / cosineSum));
      double mu = (muPart + (cosineSum < (double)0 ? Math.PI : (cosineSum > (double)0 && sineSum < (double)0 ? (Math.PI * 2D) : (double)0.0F))) % (Math.PI * 2D);
      double t = .MODULE$.sqrt(.MODULE$.pow(cosineSum / stats.n(), (double)2.0F) + .MODULE$.pow(sineSum / stats.n(), (double)2.0F));
      double k = (1.28 - 0.53 * .MODULE$.pow(t, (double)2.0F)) * .MODULE$.tan((Math.PI / 2D) * t);
      double kx = t < 0.53 ? t * ((double)2 + t * t * ((double)1 + (double)5 * t * t / (double)6)) : (t < 0.85 ? -0.4 + 1.39 * t + 0.43 / ((double)1 - t) : (double)1 / (t * ((double)3 + t * ((double)-4 + t))));
      DenseVector result = (DenseVector)package$.MODULE$.minimize(lensed, DenseVector$.MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapDoubleArray(new double[]{mu, kx}), scala.reflect.ClassTag..MODULE$.Double()), scala.collection.immutable.Nil..MODULE$, OptimizationPackage$.MODULE$.lbfgsMinimizationPackage(DenseVector$.MODULE$.space_Double(), scala..less.colon.less..MODULE$.refl()));
      Tuple2.mcDD.sp var20 = new Tuple2.mcDD.sp(result.apply$mcD$sp(0), result.apply$mcD$sp(1));
      if (var20 != null) {
         double a = ((Tuple2)var20)._1$mcD$sp();
         double b = ((Tuple2)var20)._2$mcD$sp();
         Tuple3 var2 = new Tuple3(var20, BoxesRunTime.boxToDouble(a), BoxesRunTime.boxToDouble(b));
         Tuple2 res = (Tuple2)var2._1();
         double var26 = BoxesRunTime.unboxToDouble(var2._2());
         double var28 = BoxesRunTime.unboxToDouble(var2._3());
         return res;
      } else {
         throw new MatchError(var20);
      }
   }

   public DiffFunction likelihoodFunction(final VonMises.SufficientStatistic stats) {
      return new DiffFunction(stats) {
         private final VonMises.SufficientStatistic stats$1;

         public DiffFunction repr() {
            return DiffFunction.repr$(this);
         }

         public DiffFunction cached(final CanCopy copy) {
            return DiffFunction.cached$(this, copy);
         }

         public DiffFunction throughLens(final Isomorphism l) {
            return DiffFunction.throughLens$(this, l);
         }

         public Object gradientAt(final Object x) {
            return StochasticDiffFunction.gradientAt$(this, x);
         }

         public double valueAt(final Object x) {
            return StochasticDiffFunction.valueAt$(this, x);
         }

         public final double apply(final Object x) {
            return StochasticDiffFunction.apply$(this, x);
         }

         public final Object $plus(final Object b, final UFunc.UImpl2 op) {
            return NumericOps.$plus$(this, b, op);
         }

         public final Object $colon$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$colon$eq$(this, b, op);
         }

         public final Object $colon$plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$colon$plus$eq$(this, b, op);
         }

         public final Object $colon$times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$colon$times$eq$(this, b, op);
         }

         public final Object $plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$plus$eq$(this, b, op);
         }

         public final Object $times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$times$eq$(this, b, op);
         }

         public final Object $colon$minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$colon$minus$eq$(this, b, op);
         }

         public final Object $colon$percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$colon$percent$eq$(this, b, op);
         }

         public final Object $percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$percent$eq$(this, b, op);
         }

         public final Object $minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$minus$eq$(this, b, op);
         }

         public final Object $colon$div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$colon$div$eq$(this, b, op);
         }

         public final Object $colon$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$colon$up$eq$(this, b, op);
         }

         public final Object $div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$div$eq$(this, b, op);
         }

         public final Object $less$colon$less(final Object b, final UFunc.UImpl2 op) {
            return NumericOps.$less$colon$less$(this, b, op);
         }

         public final Object $less$colon$eq(final Object b, final UFunc.UImpl2 op) {
            return NumericOps.$less$colon$eq$(this, b, op);
         }

         public final Object $greater$colon$greater(final Object b, final UFunc.UImpl2 op) {
            return NumericOps.$greater$colon$greater$(this, b, op);
         }

         public final Object $greater$colon$eq(final Object b, final UFunc.UImpl2 op) {
            return NumericOps.$greater$colon$eq$(this, b, op);
         }

         public final Object $colon$amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$colon$amp$eq$(this, b, op);
         }

         public final Object $colon$bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$colon$bar$eq$(this, b, op);
         }

         public final Object $colon$up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$colon$up$up$eq$(this, b, op);
         }

         public final Object $amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$amp$eq$(this, b, op);
         }

         public final Object $bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$bar$eq$(this, b, op);
         }

         public final Object $up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$up$up$eq$(this, b, op);
         }

         public final Object $plus$colon$plus(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$plus$colon$plus$(this, b, op);
         }

         public final Object $times$colon$times(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$times$colon$times$(this, b, op);
         }

         public final Object $colon$eq$eq(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$colon$eq$eq$(this, b, op);
         }

         public final Object $colon$bang$eq(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$colon$bang$eq$(this, b, op);
         }

         public final Object unary_$minus(final UFunc.UImpl op) {
            return ImmutableNumericOps.unary_$minus$(this, op);
         }

         public final Object $minus$colon$minus(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$minus$colon$minus$(this, b, op);
         }

         public final Object $minus(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$minus$(this, b, op);
         }

         public final Object $percent$colon$percent(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$percent$colon$percent$(this, b, op);
         }

         public final Object $percent(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$percent$(this, b, op);
         }

         public final Object $div$colon$div(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$div$colon$div$(this, b, op);
         }

         public final Object $div(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$div$(this, b, op);
         }

         public final Object $up$colon$up(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$up$colon$up$(this, b, op);
         }

         public final Object dot(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.dot$(this, b, op);
         }

         public final Object unary_$bang(final UFunc.UImpl op) {
            return ImmutableNumericOps.unary_$bang$(this, op);
         }

         public final Object $amp$colon$amp(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$amp$colon$amp$(this, b, op);
         }

         public final Object $bar$colon$bar(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$bar$colon$bar$(this, b, op);
         }

         public final Object $up$up$colon$up$up(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$up$up$colon$up$up$(this, b, op);
         }

         public final Object $amp(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$amp$(this, b, op);
         }

         public final Object $bar(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$bar$(this, b, op);
         }

         public final Object $up$up(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$up$up$(this, b, op);
         }

         public final Object $times(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$times$(this, b, op);
         }

         public final Object t(final CanTranspose op) {
            return ImmutableNumericOps.t$(this, op);
         }

         public Object $bslash(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$bslash$(this, b, op);
         }

         public final Object t(final Object a, final Object b, final CanTranspose op, final CanSlice2 canSlice) {
            return ImmutableNumericOps.t$(this, a, b, op, canSlice);
         }

         public final Object t(final Object a, final CanTranspose op, final CanSlice canSlice) {
            return ImmutableNumericOps.t$(this, a, op, canSlice);
         }

         public boolean apply$mcZD$sp(final double v1) {
            return Function1.apply$mcZD$sp$(this, v1);
         }

         public double apply$mcDD$sp(final double v1) {
            return Function1.apply$mcDD$sp$(this, v1);
         }

         public float apply$mcFD$sp(final double v1) {
            return Function1.apply$mcFD$sp$(this, v1);
         }

         public int apply$mcID$sp(final double v1) {
            return Function1.apply$mcID$sp$(this, v1);
         }

         public long apply$mcJD$sp(final double v1) {
            return Function1.apply$mcJD$sp$(this, v1);
         }

         public void apply$mcVD$sp(final double v1) {
            Function1.apply$mcVD$sp$(this, v1);
         }

         public boolean apply$mcZF$sp(final float v1) {
            return Function1.apply$mcZF$sp$(this, v1);
         }

         public double apply$mcDF$sp(final float v1) {
            return Function1.apply$mcDF$sp$(this, v1);
         }

         public float apply$mcFF$sp(final float v1) {
            return Function1.apply$mcFF$sp$(this, v1);
         }

         public int apply$mcIF$sp(final float v1) {
            return Function1.apply$mcIF$sp$(this, v1);
         }

         public long apply$mcJF$sp(final float v1) {
            return Function1.apply$mcJF$sp$(this, v1);
         }

         public void apply$mcVF$sp(final float v1) {
            Function1.apply$mcVF$sp$(this, v1);
         }

         public boolean apply$mcZI$sp(final int v1) {
            return Function1.apply$mcZI$sp$(this, v1);
         }

         public double apply$mcDI$sp(final int v1) {
            return Function1.apply$mcDI$sp$(this, v1);
         }

         public float apply$mcFI$sp(final int v1) {
            return Function1.apply$mcFI$sp$(this, v1);
         }

         public int apply$mcII$sp(final int v1) {
            return Function1.apply$mcII$sp$(this, v1);
         }

         public long apply$mcJI$sp(final int v1) {
            return Function1.apply$mcJI$sp$(this, v1);
         }

         public void apply$mcVI$sp(final int v1) {
            Function1.apply$mcVI$sp$(this, v1);
         }

         public boolean apply$mcZJ$sp(final long v1) {
            return Function1.apply$mcZJ$sp$(this, v1);
         }

         public double apply$mcDJ$sp(final long v1) {
            return Function1.apply$mcDJ$sp$(this, v1);
         }

         public float apply$mcFJ$sp(final long v1) {
            return Function1.apply$mcFJ$sp$(this, v1);
         }

         public int apply$mcIJ$sp(final long v1) {
            return Function1.apply$mcIJ$sp$(this, v1);
         }

         public long apply$mcJJ$sp(final long v1) {
            return Function1.apply$mcJJ$sp$(this, v1);
         }

         public void apply$mcVJ$sp(final long v1) {
            Function1.apply$mcVJ$sp$(this, v1);
         }

         public Function1 compose(final Function1 g) {
            return Function1.compose$(this, g);
         }

         public Function1 andThen(final Function1 g) {
            return Function1.andThen$(this, g);
         }

         public String toString() {
            return Function1.toString$(this);
         }

         public Tuple2 calculate(final Tuple2 x) {
            double DELTA = 1.0E-5;
            if (x == null) {
               throw new MatchError(x);
            } else {
               double mu = x._1$mcD$sp();
               double k = x._2$mcD$sp();
               Tuple2.mcDD.sp var3 = new Tuple2.mcDD.sp(mu, k);
               double mu = ((Tuple2)var3)._1$mcD$sp();
               double k = ((Tuple2)var3)._2$mcD$sp();
               Tuple2 var10000;
               if (!(mu < (double)0) && !(mu > (Math.PI * 2D)) && !(k < (double)0)) {
                  Tuple2.mcDD.sp var17 = new Tuple2.mcDD.sp(.MODULE$.sin(mu), .MODULE$.cos(mu));
                  if (var17 == null) {
                     throw new MatchError(var17);
                  }

                  double sinx = ((Tuple2)var17)._1$mcD$sp();
                  double cosx = ((Tuple2)var17)._2$mcD$sp();
                  Tuple2.mcDD.sp var2 = new Tuple2.mcDD.sp(sinx, cosx);
                  double sinx = ((Tuple2)var2)._1$mcD$sp();
                  double cosx = ((Tuple2)var2)._2$mcD$sp();
                  double bessel_k = Bessel.i0$.MODULE$.apply$mDDc$sp(k, Bessel$i0$ImplDouble$.MODULE$);
                  double logprob = this.stats$1.n() * .MODULE$.log(bessel_k * (double)2 * Math.PI) - (this.stats$1.sines() * sinx + this.stats$1.cosines() * cosx) * k;
                  double mugrad = -k * (this.stats$1.sines() * .MODULE$.cos(mu) - this.stats$1.cosines() * .MODULE$.sin(mu));
                  double kgrad = this.stats$1.n() * (Bessel.i1$.MODULE$.apply(k) / bessel_k) - (this.stats$1.sines() * sinx + this.stats$1.cosines() * cosx);
                  var10000 = new Tuple2(BoxesRunTime.boxToDouble(logprob), new Tuple2.mcDD.sp(mugrad, kgrad));
               } else {
                  var10000 = new Tuple2(BoxesRunTime.boxToDouble(Double.POSITIVE_INFINITY), new Tuple2.mcDD.sp((double)0.0F, (double)0.0F));
               }

               return var10000;
            }
         }

         public {
            this.stats$1 = stats$1;
            Function1.$init$(this);
            ImmutableNumericOps.$init$(this);
            NumericOps.$init$(this);
            StochasticDiffFunction.$init$(this);
            DiffFunction.$init$(this);
         }
      };
   }

   public VonMises apply(final double mu, final double k, final RandBasis rand) {
      return new VonMises(mu, k, rand);
   }

   public Option unapply(final VonMises x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2.mcDD.sp(x$0.mu(), x$0.k())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(VonMises$.class);
   }

   private VonMises$() {
   }
}
