package breeze.optimize;

import breeze.generic.UFunc;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.math.MutableInnerProductVectorSpace;
import breeze.stats.distributions.Rand$;
import breeze.util.Isomorphism;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.immutable.IndexedSeq;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005q3AAC\u0006\u0001!!A!\u0006\u0001B\u0001B\u0003%1\u0006\u0003\u0005/\u0001\t\u0005\t\u0015!\u00030\u0011!\u0011\u0004A!A!\u0002\u0017\u0019\u0004\"\u0002\u001f\u0001\t\u0003i\u0004\"B\"\u0001\t\u0003!ua\u0002&\f\u0003\u0003E\ta\u0013\u0004\b\u0015-\t\t\u0011#\u0001M\u0011\u0015at\u0001\"\u0001N\u0011\u001dqu!%A\u0005\u0002=\u0013!CR5tQ\u0016\u0014H)\u001b4g\rVt7\r^5p]*\u0011A\"D\u0001\t_B$\u0018.\\5{K*\ta\"\u0001\u0004ce\u0016,'0Z\u0002\u0001+\t\tbdE\u0002\u0001%a\u0001\"a\u0005\f\u000e\u0003QQ\u0011!F\u0001\u0006g\u000e\fG.Y\u0005\u0003/Q\u0011a!\u00118z%\u00164\u0007\u0003B\r\u001b9\u001dj\u0011aC\u0005\u00037-\u00111cU3d_:$wJ\u001d3fe\u001a+hn\u0019;j_:\u0004\"!\b\u0010\r\u0001\u0011)q\u0004\u0001b\u0001A\t\tA+\u0005\u0002\"IA\u00111CI\u0005\u0003GQ\u0011qAT8uQ&tw\r\u0005\u0002\u0014K%\u0011a\u0005\u0006\u0002\u0004\u0003:L\bcA\r)9%\u0011\u0011f\u0003\u0002\r\r&\u001c\b.\u001a:NCR\u0014\u0018\u000e_\u0001\u0003I\u001a\u00042!\u0007\u0017\u001d\u0013\ti3BA\tCCR\u001c\u0007\u000eR5gM\u001a+hn\u0019;j_:\fqb\u001a:bI&,g\u000e^:U_.+W\r\u001d\t\u0003'AJ!!\r\u000b\u0003\u0007%sG/\u0001\u0002wgB!Ag\u000e\u000f:\u001b\u0005)$B\u0001\u001c\u000e\u0003\u0011i\u0017\r\u001e5\n\u0005a*$AH'vi\u0006\u0014G.Z%o]\u0016\u0014\bK]8ek\u000e$h+Z2u_J\u001c\u0006/Y2f!\t\u0019\"(\u0003\u0002<)\t1Ai\\;cY\u0016\fa\u0001P5oSRtDc\u0001 B\u0005R\u0011q\b\u0011\t\u00043\u0001a\u0002\"\u0002\u001a\u0005\u0001\b\u0019\u0004\"\u0002\u0016\u0005\u0001\u0004Y\u0003b\u0002\u0018\u0005!\u0003\u0005\raL\u0001\u000bG\u0006d7-\u001e7bi\u0016\u0014DCA#I!\u0015\u0019b)\u000f\u000f(\u0013\t9EC\u0001\u0004UkBdWm\r\u0005\u0006\u0013\u0016\u0001\r\u0001H\u0001\u0002q\u0006\u0011b)[:iKJ$\u0015N\u001a4Gk:\u001cG/[8o!\tIra\u0005\u0002\b%Q\t1*A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEM\u000b\u0003!n+\u0012!\u0015\u0016\u0003_I[\u0013a\u0015\t\u0003)fk\u0011!\u0016\u0006\u0003-^\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005a#\u0012AC1o]>$\u0018\r^5p]&\u0011!,\u0016\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,G!B\u0010\n\u0005\u0004\u0001\u0003"
)
public class FisherDiffFunction implements SecondOrderFunction {
   private final BatchDiffFunction df;
   private final int gradientsToKeep;
   private final MutableInnerProductVectorSpace vs;

   public static int $lessinit$greater$default$2() {
      return FisherDiffFunction$.MODULE$.$lessinit$greater$default$2();
   }

   public Tuple2 calculate(final Object x) {
      return SecondOrderFunction.calculate$(this, x);
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

   public Tuple3 calculate2(final Object x) {
      IndexedSeq subset = (IndexedSeq)Rand$.MODULE$.subsetsOfSize(this.df.fullRange(), this.gradientsToKeep).draw();
      IndexedSeq toKeep = (IndexedSeq)subset.map((i) -> $anonfun$calculate2$1(this, x, BoxesRunTime.unboxToInt(i)));
      Tuple2 var6 = this.df.calculate(x);
      if (var6 != null) {
         double v = var6._1$mcD$sp();
         Object otherGradient = var6._2();
         Tuple2 var2 = new Tuple2(BoxesRunTime.boxToDouble(v), otherGradient);
         double v = var2._1$mcD$sp();
         Object otherGradient = var2._2();
         return new Tuple3(BoxesRunTime.boxToDouble(v), otherGradient, new FisherMatrix(((IndexedSeq)toKeep.map((x$5) -> x$5._2())).toIndexedSeq(), this.vs));
      } else {
         throw new MatchError(var6);
      }
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$calculate2$1(final FisherDiffFunction $this, final Object x$9, final int i) {
      return $this.df.calculate(x$9, (IndexedSeq).MODULE$.IndexedSeq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{i})));
   }

   public FisherDiffFunction(final BatchDiffFunction df, final int gradientsToKeep, final MutableInnerProductVectorSpace vs) {
      this.df = df;
      this.gradientsToKeep = gradientsToKeep;
      this.vs = vs;
      Function1.$init$(this);
      ImmutableNumericOps.$init$(this);
      NumericOps.$init$(this);
      StochasticDiffFunction.$init$(this);
      DiffFunction.$init$(this);
      SecondOrderFunction.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
