package org.apache.spark.ml.optim.loss;

import breeze.generic.UFunc;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.optimize.DiffFunction;
import breeze.optimize.StochasticDiffFunction;
import breeze.util.Isomorphism;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.SparseVector;
import org.apache.spark.ml.linalg.Vector;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.DoubleRef;

@ScalaSignature(
   bytes = "\u0006\u0005!3Qa\u0002\u0005\u0001\u0019QA\u0001\"\n\u0001\u0003\u0006\u0004%\te\n\u0005\tW\u0001\u0011\t\u0011)A\u0005Q!AA\u0006\u0001B\u0001B\u0003%Q\u0006\u0003\u00057\u0001\t\u0005\t\u0015!\u00038\u0011\u0015Y\u0004\u0001\"\u0001=\u0011\u0015\t\u0005\u0001\"\u0011C\u0005Aa%GU3hk2\f'/\u001b>bi&|gN\u0003\u0002\n\u0015\u0005!An\\:t\u0015\tYA\"A\u0003paRLWN\u0003\u0002\u000e\u001d\u0005\u0011Q\u000e\u001c\u0006\u0003\u001fA\tQa\u001d9be.T!!\u0005\n\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0019\u0012aA8sON\u0019\u0001!F\u000e\u0011\u0005YIR\"A\f\u000b\u0003a\tQa]2bY\u0006L!AG\f\u0003\r\u0005s\u0017PU3g!\raRdH\u0007\u0002\u0011%\u0011a\u0004\u0003\u0002\u001d\t&4g-\u001a:f]RL\u0017M\u00197f%\u0016<W\u000f\\1sSj\fG/[8o!\t\u00013%D\u0001\"\u0015\t\u0011C\"\u0001\u0004mS:\fGnZ\u0005\u0003I\u0005\u0012aAV3di>\u0014\u0018\u0001\u0003:fOB\u000b'/Y7\u0004\u0001U\t\u0001\u0006\u0005\u0002\u0017S%\u0011!f\u0006\u0002\u0007\t>,(\r\\3\u0002\u0013I,w\rU1sC6\u0004\u0013aC:i_VdG-\u00119qYf\u0004BA\u0006\u00181g%\u0011qf\u0006\u0002\n\rVt7\r^5p]F\u0002\"AF\u0019\n\u0005I:\"aA%oiB\u0011a\u0003N\u0005\u0003k]\u0011qAQ8pY\u0016\fg.\u0001\tbaBd\u0017PR3biV\u0014Xm]*uIB\u0019a\u0003\u000f\u001e\n\u0005e:\"AB(qi&|g\u000e\u0005\u0003\u0017]AB\u0013A\u0002\u001fj]&$h\b\u0006\u0003>}}\u0002\u0005C\u0001\u000f\u0001\u0011\u0015)S\u00011\u0001)\u0011\u0015aS\u00011\u0001.\u0011\u00151T\u00011\u00018\u0003%\u0019\u0017\r\\2vY\u0006$X\r\u0006\u0002D\rB!a\u0003\u0012\u0015 \u0013\t)uC\u0001\u0004UkBdWM\r\u0005\u0006\u000f\u001a\u0001\raH\u0001\rG>,gMZ5dS\u0016tGo\u001d"
)
public class L2Regularization implements DifferentiableRegularization {
   private final double regParam;
   private final Function1 shouldApply;
   private final Option applyFeaturesStd;

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

   public double regParam() {
      return this.regParam;
   }

   public Tuple2 calculate(final Vector coefficients) {
      if (coefficients instanceof DenseVector var4) {
         DoubleRef sum = DoubleRef.create((double)0.0F);
         double[] gradient = new double[var4.size()];
         ((IterableOnceOps).MODULE$.indices$extension(scala.Predef..MODULE$.doubleArrayOps(var4.values())).filter(this.shouldApply)).foreach((j) -> $anonfun$calculate$1(this, coefficients, sum, gradient, BoxesRunTime.unboxToInt(j)));
         return new Tuple2(BoxesRunTime.boxToDouble((double)0.5F * sum.elem * this.regParam()), org.apache.spark.ml.linalg.Vectors..MODULE$.dense(gradient));
      } else if (coefficients instanceof SparseVector) {
         throw new IllegalArgumentException("Sparse coefficients are not currently supported.");
      } else {
         throw new MatchError(coefficients);
      }
   }

   // $FF: synthetic method
   public static final Object $anonfun$calculate$1(final L2Regularization $this, final Vector coefficients$1, final DoubleRef sum$1, final double[] gradient$1, final int j) {
      double coef = coefficients$1.apply(j);
      Option var8 = $this.applyFeaturesStd;
      if (var8 instanceof Some var9) {
         Function1 getStd = (Function1)var9.value();
         double std = getStd.apply$mcDI$sp(j);
         if (std != (double)0.0F) {
            double temp = coef / (std * std);
            sum$1.elem += coef * temp;
            gradient$1[j] = $this.regParam() * temp;
            return BoxedUnit.UNIT;
         } else {
            return BoxesRunTime.boxToDouble((double)0.0F);
         }
      } else if (scala.None..MODULE$.equals(var8)) {
         sum$1.elem += coef * coef;
         gradient$1[j] = coef * $this.regParam();
         return BoxedUnit.UNIT;
      } else {
         throw new MatchError(var8);
      }
   }

   public L2Regularization(final double regParam, final Function1 shouldApply, final Option applyFeaturesStd) {
      this.regParam = regParam;
      this.shouldApply = shouldApply;
      this.applyFeaturesStd = applyFeaturesStd;
      Function1.$init$(this);
      ImmutableNumericOps.$init$(this);
      NumericOps.$init$(this);
      StochasticDiffFunction.$init$(this);
      DiffFunction.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
