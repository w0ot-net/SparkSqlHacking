package org.apache.spark.ml.optim;

import breeze.generic.UFunc;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.DenseVector.;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.optimize.CachedDiffFunction;
import breeze.optimize.DiffFunction;
import breeze.optimize.FirstOrderMinimizer;
import breeze.optimize.LBFGS;
import breeze.optimize.OWLQN;
import breeze.optimize.StochasticDiffFunction;
import breeze.util.Isomorphism;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.DenseVector;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.mutable.ArrayBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005y4Qa\u0005\u000b\u0001)yA\u0001\"\u000b\u0001\u0003\u0002\u0003\u0006Ia\u000b\u0005\t]\u0001\u0011\t\u0011)A\u0005_!A!\u0007\u0001B\u0001B\u0003%1\u0007\u0003\u00057\u0001\t\u0005\t\u0015!\u00038\u0011\u0015i\u0004\u0001\"\u0001?\u0011\u0015!\u0005\u0001\"\u0011F\r\u0011I\u0006\u0001\u0002.\t\u0011);!\u0011!Q\u0001\nMB\u0001\u0002T\u0004\u0003\u0002\u0003\u0006Ia\r\u0005\tO\u001e\u0011\t\u0011)A\u0005\u001f\"A\u0001n\u0002B\u0001B\u0003%q\n\u0003\u0005Y\u000f\t\u0005\t\u0015!\u0003P\u0011!IsA!A!\u0002\u0013Y\u0003\u0002C5\b\u0005\u0003\u0005\u000b\u0011B\u0018\t\u000bu:A\u0011\u00016\t\u000fQ<!\u0019!C\u0005k\"1ao\u0002Q\u0001\n=BQa^\u0004\u0005Ba\u0014\u0011#U;bg&tUm\u001e;p]N{GN^3s\u0015\t)b#A\u0003paRLWN\u0003\u0002\u00181\u0005\u0011Q\u000e\u001c\u0006\u00033i\tQa\u001d9be.T!a\u0007\u000f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005i\u0012aA8sON\u0019\u0001aH\u0013\u0011\u0005\u0001\u001aS\"A\u0011\u000b\u0003\t\nQa]2bY\u0006L!\u0001J\u0011\u0003\r\u0005s\u0017PU3g!\t1s%D\u0001\u0015\u0013\tACC\u0001\u000bO_Jl\u0017\r\\#rk\u0006$\u0018n\u001c8T_24XM]\u0001\rM&$\u0018J\u001c;fe\u000e,\u0007\u000f^\u0002\u0001!\t\u0001C&\u0003\u0002.C\t9!i\\8mK\u0006t\u0017aB7bq&#XM\u001d\t\u0003AAJ!!M\u0011\u0003\u0007%sG/A\u0002u_2\u0004\"\u0001\t\u001b\n\u0005U\n#A\u0002#pk\ndW-A\u0005mcI+wMR;oGB\u0019\u0001\u0005\u000f\u001e\n\u0005e\n#AB(qi&|g\u000e\u0005\u0003!w=\u001a\u0014B\u0001\u001f\"\u0005%1UO\\2uS>t\u0017'\u0001\u0004=S:LGO\u0010\u000b\u0006\u007f\u0001\u000b%i\u0011\t\u0003M\u0001AQ!K\u0003A\u0002-BQAL\u0003A\u0002=BQAM\u0003A\u0002MBQAN\u0003A\u0002]\nQa]8mm\u0016$bAR%L\u001bV;\u0006C\u0001\u0014H\u0013\tAEC\u0001\fO_Jl\u0017\r\\#rk\u0006$\u0018n\u001c8T_2,H/[8o\u0011\u0015Qe\u00011\u00014\u0003\u0011\u0011')\u0019:\t\u000b13\u0001\u0019A\u001a\u0002\u000b\t\u0014')\u0019:\t\u000b93\u0001\u0019A(\u0002\u000b\u0005\u0014')\u0019:\u0011\u0005A\u001bV\"A)\u000b\u0005I3\u0012A\u00027j]\u0006dw-\u0003\u0002U#\nYA)\u001a8tKZ+7\r^8s\u0011\u00151f\u00011\u0001P\u0003\u0015\t\u0017MQ1s\u0011\u0015Af\u00011\u0001P\u0003\u0011\t')\u0019:\u0003+9{'/\\1m\u000bF,\u0018\r^5p]\u000e{7\u000f\u001e$v]N\u0019qaH.\u0011\u0007q\u000b7-D\u0001^\u0015\tqv,\u0001\u0005paRLW.\u001b>f\u0015\u0005\u0001\u0017A\u00022sK\u0016TX-\u0003\u0002c;\naA)\u001b4g\rVt7\r^5p]B\u0019AMZ\u001a\u000e\u0003\u0015T!AU0\n\u0005Q+\u0017AA1c\u0003\t\t\u0017-A\u0006ok64U-\u0019;ve\u0016\u001cH\u0003C6n]>\u0004\u0018O]:\u0011\u00051<Q\"\u0001\u0001\t\u000b){\u0001\u0019A\u001a\t\u000b1{\u0001\u0019A\u001a\t\u000b\u001d|\u0001\u0019A(\t\u000b!|\u0001\u0019A(\t\u000ba{\u0001\u0019A(\t\u000b%z\u0001\u0019A\u0016\t\u000b%|\u0001\u0019A\u0018\u000219,XNR3biV\u0014Xm\u001d)mkNLe\u000e^3sG\u0016\u0004H/F\u00010\u0003eqW/\u001c$fCR,(/Z:QYV\u001c\u0018J\u001c;fe\u000e,\u0007\u000f\u001e\u0011\u0002\u0013\r\fGnY;mCR,GCA=}!\u0011\u0001#pM2\n\u0005m\f#A\u0002+va2,'\u0007C\u0003~%\u0001\u00071-\u0001\u0007d_\u00164g-[2jK:$8\u000f"
)
public class QuasiNewtonSolver implements NormalEquationSolver {
   private final boolean fitIntercept;
   private final int maxIter;
   private final double tol;
   private final Option l1RegFunc;

   public NormalEquationSolution solve(final double bBar, final double bbBar, final DenseVector abBar, final DenseVector aaBar, final DenseVector aBar) {
      int numFeatures = aBar.size();
      int numFeaturesPlusIntercept = this.fitIntercept ? numFeatures + 1 : numFeatures;
      double[] initialCoefficientsWithIntercept = new double[numFeaturesPlusIntercept];
      if (this.fitIntercept) {
         initialCoefficientsWithIntercept[numFeaturesPlusIntercept - 1] = bBar;
      }

      NormalEquationCostFun costFun = new NormalEquationCostFun(bBar, bbBar, abBar, aaBar, aBar, this.fitIntercept, numFeatures);
      LBFGS optimizer = (LBFGS)this.l1RegFunc.map((func) -> new OWLQN(this.maxIter, 10, func, this.tol, .MODULE$.space_Double())).getOrElse(() -> new LBFGS(this.maxIter, 10, this.tol, .MODULE$.space_Double()));
      Iterator states = optimizer.iterations(new CachedDiffFunction(costFun, .MODULE$.canCopyDenseVector(scala.reflect.ClassTag..MODULE$.Double())), new breeze.linalg.DenseVector.mcD.sp(initialCoefficientsWithIntercept));
      ArrayBuilder arrayBuilder = scala.collection.mutable.ArrayBuilder..MODULE$.make(scala.reflect.ClassTag..MODULE$.Double());
      FirstOrderMinimizer.State state = null;

      while(states.hasNext()) {
         state = (FirstOrderMinimizer.State)states.next();
         arrayBuilder.$plus$eq(BoxesRunTime.boxToDouble(state.adjustedValue()));
      }

      double[] x = (double[])((breeze.linalg.DenseVector)state.x()).toArray$mcD$sp(scala.reflect.ClassTag..MODULE$.Double()).clone();
      return new NormalEquationSolution(x, scala.None..MODULE$, new Some(arrayBuilder.result()));
   }

   public QuasiNewtonSolver(final boolean fitIntercept, final int maxIter, final double tol, final Option l1RegFunc) {
      this.fitIntercept = fitIntercept;
      this.maxIter = maxIter;
      this.tol = tol;
      this.l1RegFunc = l1RegFunc;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class NormalEquationCostFun implements DiffFunction {
      private final double bBar;
      private final double bbBar;
      private final DenseVector ab;
      private final DenseVector aa;
      private final DenseVector aBar;
      private final boolean fitIntercept;
      private final int numFeatures;
      private final int numFeaturesPlusIntercept;
      // $FF: synthetic field
      public final QuasiNewtonSolver $outer;

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

      private int numFeaturesPlusIntercept() {
         return this.numFeaturesPlusIntercept;
      }

      public Tuple2 calculate(final breeze.linalg.DenseVector coefficients) {
         DenseVector coef = org.apache.spark.ml.linalg.Vectors..MODULE$.fromBreeze(coefficients).toDense();
         if (this.fitIntercept) {
            int j = 0;
            double dotProd = (double)0.0F;
            double[] coefValues = coef.values();

            for(double[] aBarValues = this.aBar.values(); j < this.numFeatures; ++j) {
               dotProd += coefValues[j] * aBarValues[j];
            }

            coefValues[this.numFeatures] = this.bBar - dotProd;
         }

         DenseVector aax = new DenseVector(new double[this.numFeaturesPlusIntercept()]);
         org.apache.spark.ml.linalg.BLAS..MODULE$.dspmv(this.numFeaturesPlusIntercept(), (double)1.0F, this.aa, coef, (double)1.0F, aax);
         double loss = (double)0.5F * this.bbBar - org.apache.spark.ml.linalg.BLAS..MODULE$.dot(this.ab, coef) + (double)0.5F * org.apache.spark.ml.linalg.BLAS..MODULE$.dot(coef, aax);
         org.apache.spark.ml.linalg.BLAS..MODULE$.axpy((double)-1.0F, this.ab, aax);
         return new Tuple2(BoxesRunTime.boxToDouble(loss), aax.asBreeze().toDenseVector$mcD$sp(scala.reflect.ClassTag..MODULE$.Double()));
      }

      // $FF: synthetic method
      public QuasiNewtonSolver org$apache$spark$ml$optim$QuasiNewtonSolver$NormalEquationCostFun$$$outer() {
         return this.$outer;
      }

      public NormalEquationCostFun(final double bBar, final double bbBar, final DenseVector ab, final DenseVector aa, final DenseVector aBar, final boolean fitIntercept, final int numFeatures) {
         this.bBar = bBar;
         this.bbBar = bbBar;
         this.ab = ab;
         this.aa = aa;
         this.aBar = aBar;
         this.fitIntercept = fitIntercept;
         this.numFeatures = numFeatures;
         if (QuasiNewtonSolver.this == null) {
            throw null;
         } else {
            this.$outer = QuasiNewtonSolver.this;
            super();
            Function1.$init$(this);
            ImmutableNumericOps.$init$(this);
            NumericOps.$init$(this);
            StochasticDiffFunction.$init$(this);
            DiffFunction.$init$(this);
            this.numFeaturesPlusIntercept = fitIntercept ? numFeatures + 1 : numFeatures;
         }
      }
   }
}
