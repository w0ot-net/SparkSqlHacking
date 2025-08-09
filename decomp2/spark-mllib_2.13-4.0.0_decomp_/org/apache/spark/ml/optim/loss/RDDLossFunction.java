package org.apache.spark.ml.optim.loss;

import breeze.generic.UFunc;
import breeze.linalg.DenseVector;
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
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.Vectors.;
import org.apache.spark.ml.optim.aggregator.DifferentiableLossAggregator;
import org.apache.spark.rdd.RDD;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005eb!B\u0007\u000f\u0001IQ\u0002\u0002C\u001a\u0001\u0005\u0003\u0005\u000b\u0011B\u001b\t\u0011\u0019\u0003!\u0011!Q\u0001\n\u001dC\u0001b\u0018\u0001\u0003\u0002\u0003\u0006I\u0001\u0019\u0005\tO\u0002\u0011\t\u0011)A\u0005Q\"A1\u000e\u0001B\u0002B\u0003-A\u000e\u0003\u0005s\u0001\t\r\t\u0015a\u0003t\u0011\u0015!\b\u0001\"\u0001v\u0011\u0015q\b\u0001\"\u0011\u0000\u000f)\tYADA\u0001\u0012\u0003\u0011\u0012Q\u0002\u0004\n\u001b9\t\t\u0011#\u0001\u0013\u0003\u001fAa\u0001\u001e\u0006\u0005\u0002\u0005E\u0001\"CA\n\u0015E\u0005I\u0011AA\u000b\u0005=\u0011F\t\u0012'pgN4UO\\2uS>t'BA\b\u0011\u0003\u0011awn]:\u000b\u0005E\u0011\u0012!B8qi&l'BA\n\u0015\u0003\tiGN\u0003\u0002\u0016-\u0005)1\u000f]1sW*\u0011q\u0003G\u0001\u0007CB\f7\r[3\u000b\u0003e\t1a\u001c:h+\rYRHV\n\u0004\u0001q\u0011\u0003CA\u000f!\u001b\u0005q\"\"A\u0010\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0005r\"AB!osJ+g\rE\u0002$Q)j\u0011\u0001\n\u0006\u0003K\u0019\n\u0001b\u001c9uS6L'0\u001a\u0006\u0002O\u00051!M]3fu\u0016L!!\u000b\u0013\u0003\u0019\u0011KgM\u001a$v]\u000e$\u0018n\u001c8\u0011\u0007-r\u0003'D\u0001-\u0015\tic%\u0001\u0004mS:\fGnZ\u0005\u0003_1\u00121\u0002R3og\u00164Vm\u0019;peB\u0011Q$M\u0005\u0003ey\u0011a\u0001R8vE2,\u0017!C5ogR\fgnY3t\u0007\u0001\u00012AN\u001d<\u001b\u00059$B\u0001\u001d\u0015\u0003\r\u0011H\rZ\u0005\u0003u]\u00121A\u0015#E!\taT\b\u0004\u0001\u0005\u000by\u0002!\u0019A \u0003\u0003Q\u000b\"\u0001Q\"\u0011\u0005u\t\u0015B\u0001\"\u001f\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!\b#\n\u0005\u0015s\"aA!os\u0006iq-\u001a;BO\u001e\u0014XmZ1u_J\u0004B!\b%K+&\u0011\u0011J\b\u0002\n\rVt7\r^5p]F\u00022a\u0013(Q\u001b\u0005a%BA'\u0015\u0003%\u0011'o\\1eG\u0006\u001cH/\u0003\u0002P\u0019\nI!I]8bI\u000e\f7\u000f\u001e\t\u0003#Nk\u0011A\u0015\u0006\u0003[II!\u0001\u0016*\u0003\rY+7\r^8s!\tad\u000bB\u0003X\u0001\t\u0007\u0001LA\u0002BO\u001e\f\"\u0001Q-\u0011\tik6(V\u0007\u00027*\u0011A\fE\u0001\u000bC\u001e<'/Z4bi>\u0014\u0018B\u00010\\\u0005q!\u0015N\u001a4fe\u0016tG/[1cY\u0016dun]:BO\u001e\u0014XmZ1u_J\faB]3hk2\f'/\u001b>bi&|g\u000eE\u0002\u001eC\u000eL!A\u0019\u0010\u0003\r=\u0003H/[8o!\r!W\rU\u0007\u0002\u001d%\u0011aM\u0004\u0002\u001d\t&4g-\u001a:f]RL\u0017M\u00197f%\u0016<W\u000f\\1sSj\fG/[8o\u0003A\twm\u001a:fO\u0006$\u0018n\u001c8EKB$\b\u000e\u0005\u0002\u001eS&\u0011!N\b\u0002\u0004\u0013:$\u0018AC3wS\u0012,gnY3%cA\u0019Q\u000e]\u001e\u000e\u00039T!a\u001c\u0010\u0002\u000fI,g\r\\3di&\u0011\u0011O\u001c\u0002\t\u00072\f7o\u001d+bO\u0006QQM^5eK:\u001cW\r\n\u001a\u0011\u00075\u0004X+\u0001\u0004=S:LGO\u0010\u000b\u0006mj\\H0 \u000b\u0004obL\b\u0003\u00023\u0001wUCQa[\u0004A\u00041DQA]\u0004A\u0004MDQaM\u0004A\u0002UBQAR\u0004A\u0002\u001dCQaX\u0004A\u0002\u0001DqaZ\u0004\u0011\u0002\u0003\u0007\u0001.A\u0005dC2\u001cW\u000f\\1uKR!\u0011\u0011AA\u0004!\u0015i\u00121\u0001\u0019+\u0013\r\t)A\b\u0002\u0007)V\u0004H.\u001a\u001a\t\r\u0005%\u0001\u00021\u0001+\u00031\u0019w.\u001a4gS\u000eLWM\u001c;t\u0003=\u0011F\t\u0012'pgN4UO\\2uS>t\u0007C\u00013\u000b'\tQA\u0004\u0006\u0002\u0002\u000e\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIQ*b!a\u0006\u0002.\u0005=RCAA\rU\rA\u00171D\u0016\u0003\u0003;\u0001B!a\b\u0002*5\u0011\u0011\u0011\u0005\u0006\u0005\u0003G\t)#A\u0005v]\u000eDWmY6fI*\u0019\u0011q\u0005\u0010\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002,\u0005\u0005\"!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0012)a\b\u0004b\u0001\u007f\u00111q\u000b\u0004b\u0001\u0003c\t2\u0001QA\u001a!\u0019QV,!\u000e\u00028A\u0019A(!\f\u0011\u0007q\ny\u0003"
)
public class RDDLossFunction implements DiffFunction {
   private final RDD instances;
   private final Function1 getAggregator;
   private final Option regularization;
   private final int aggregationDepth;
   private final ClassTag evidence$2;

   public static int $lessinit$greater$default$4() {
      return RDDLossFunction$.MODULE$.$lessinit$greater$default$4();
   }

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

   public Tuple2 calculate(final DenseVector coefficients) {
      Broadcast bcCoefficients = this.instances.context().broadcast(.MODULE$.fromBreeze(coefficients), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
      DifferentiableLossAggregator thisAgg = (DifferentiableLossAggregator)this.getAggregator.apply(bcCoefficients);
      Function2 seqOp = (agg, x) -> agg.add(x);
      Function2 combOp = (agg1, agg2) -> agg1.merge(agg2);
      DifferentiableLossAggregator newAgg = (DifferentiableLossAggregator)this.instances.treeAggregate(thisAgg, seqOp, combOp, this.aggregationDepth, this.evidence$2);
      Vector gradient = newAgg.gradient();
      double regLoss = BoxesRunTime.unboxToDouble(this.regularization.map((regFun) -> BoxesRunTime.boxToDouble($anonfun$calculate$3(coefficients, gradient, regFun))).getOrElse((JFunction0.mcD.sp)() -> (double)0.0F));
      bcCoefficients.destroy();
      return new Tuple2(BoxesRunTime.boxToDouble(newAgg.loss() + regLoss), gradient.asBreeze().toDenseVector$mcD$sp(scala.reflect.ClassTag..MODULE$.Double()));
   }

   // $FF: synthetic method
   public static final double $anonfun$calculate$3(final DenseVector coefficients$1, final Vector gradient$1, final DifferentiableRegularization regFun) {
      Tuple2 var5 = regFun.calculate(.MODULE$.fromBreeze(coefficients$1));
      if (var5 != null) {
         double regLoss = var5._1$mcD$sp();
         Vector regGradient = (Vector)var5._2();
         Tuple2 var4 = new Tuple2(BoxesRunTime.boxToDouble(regLoss), regGradient);
         double regLoss = var4._1$mcD$sp();
         Vector regGradient = (Vector)var4._2();
         org.apache.spark.ml.linalg.BLAS..MODULE$.axpy((double)1.0F, regGradient, gradient$1);
         return regLoss;
      } else {
         throw new MatchError(var5);
      }
   }

   public RDDLossFunction(final RDD instances, final Function1 getAggregator, final Option regularization, final int aggregationDepth, final ClassTag evidence$1, final ClassTag evidence$2) {
      this.instances = instances;
      this.getAggregator = getAggregator;
      this.regularization = regularization;
      this.aggregationDepth = aggregationDepth;
      this.evidence$2 = evidence$2;
      Function1.$init$(this);
      ImmutableNumericOps.$init$(this);
      NumericOps.$init$(this);
      StochasticDiffFunction.$init$(this);
      DiffFunction.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
