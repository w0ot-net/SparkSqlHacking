package breeze.optimize;

import breeze.generic.UFunc;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.util.Isomorphism;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.Predef.ArrowAssoc.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005Q3AAC\u0006\u0001!!Aq\u0005\u0001B\u0001B\u0003%\u0001\u0004\u0003\u0005)\u0001\t\r\t\u0015a\u0003*\u0011\u0015\t\u0004\u0001\"\u00013\u0011\u00159\u0004\u0001\"\u00119\u0011\u0015Y\u0004\u0001\"\u0011=\u0011\u001d\t\u0005\u00011A\u0005\n\tCqA\u0012\u0001A\u0002\u0013%q\t\u0003\u0004N\u0001\u0001\u0006Ka\u0011\u0005\u0006\u001d\u0002!\ta\u0014\u0002\u0013\u0007\u0006\u001c\u0007.\u001a3ES\u001a4g)\u001e8di&|gN\u0003\u0002\r\u001b\u0005Aq\u000e\u001d;j[&TXMC\u0001\u000f\u0003\u0019\u0011'/Z3{K\u000e\u0001QCA\t\u001f'\r\u0001!\u0003\u0007\t\u0003'Yi\u0011\u0001\u0006\u0006\u0002+\u0005)1oY1mC&\u0011q\u0003\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0007eQB$D\u0001\f\u0013\tY2B\u0001\u0007ES\u001a4g)\u001e8di&|g\u000e\u0005\u0002\u001e=1\u0001A!B\u0010\u0001\u0005\u0004\u0001#!\u0001+\u0012\u0005\u0005\"\u0003CA\n#\u0013\t\u0019CCA\u0004O_RD\u0017N\\4\u0011\u0005M)\u0013B\u0001\u0014\u0015\u0005\r\te._\u0001\u0004_\nT\u0017AC3wS\u0012,gnY3%cA\u0019!f\f\u000f\u000e\u0003-R!\u0001L\u0017\u0002\u000fM,\b\u000f]8si*\u0011a&D\u0001\u0007Y&t\u0017\r\\4\n\u0005AZ#aB\"b]\u000e{\u0007/_\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005M2DC\u0001\u001b6!\rI\u0002\u0001\b\u0005\u0006Q\r\u0001\u001d!\u000b\u0005\u0006O\r\u0001\r\u0001G\u0001\u000bOJ\fG-[3oi\u0006#HC\u0001\u000f:\u0011\u0015QD\u00011\u0001\u001d\u0003\u0005A\u0018a\u0002<bYV,\u0017\t\u001e\u000b\u0003{\u0001\u0003\"a\u0005 \n\u0005}\"\"A\u0002#pk\ndW\rC\u0003;\u000b\u0001\u0007A$\u0001\u0005mCN$H)\u0019;b+\u0005\u0019\u0005#B\nE9ub\u0012BA#\u0015\u0005\u0019!V\u000f\u001d7fg\u0005aA.Y:u\t\u0006$\u0018m\u0018\u0013fcR\u0011\u0001j\u0013\t\u0003'%K!A\u0013\u000b\u0003\tUs\u0017\u000e\u001e\u0005\b\u0019\u001e\t\t\u00111\u0001D\u0003\rAH%M\u0001\nY\u0006\u001cH\u000fR1uC\u0002\n\u0011bY1mGVd\u0017\r^3\u0015\u0005A\u001b\u0006\u0003B\nR{qI!A\u0015\u000b\u0003\rQ+\b\u000f\\33\u0011\u0015Q\u0014\u00021\u0001\u001d\u0001"
)
public class CachedDiffFunction implements DiffFunction {
   private final DiffFunction obj;
   private final CanCopy evidence$1;
   private Tuple3 lastData;

   public DiffFunction repr() {
      return DiffFunction.repr$(this);
   }

   public DiffFunction cached(final CanCopy copy) {
      return DiffFunction.cached$(this, copy);
   }

   public DiffFunction throughLens(final Isomorphism l) {
      return DiffFunction.throughLens$(this, l);
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

   public Object gradientAt(final Object x) {
      return this.calculate(x)._2();
   }

   public double valueAt(final Object x) {
      return this.calculate(x)._1$mcD$sp();
   }

   private Tuple3 lastData() {
      return this.lastData;
   }

   private void lastData_$eq(final Tuple3 x$1) {
      this.lastData = x$1;
   }

   public Tuple2 calculate(final Object x) {
      Tuple3 ld = this.lastData();
      if (ld == null || !BoxesRunTime.equals(x, ld._1())) {
         Tuple2 newData = this.obj.calculate(x);
         ld = new Tuple3(breeze.linalg.package$.MODULE$.copy(x, this.evidence$1), BoxesRunTime.boxToDouble(newData._1$mcD$sp()), newData._2());
         this.lastData_$eq(ld);
      }

      if (ld != null) {
         double v = BoxesRunTime.unboxToDouble(ld._2());
         Object g = ld._3();
         Tuple2 var2 = new Tuple2(BoxesRunTime.boxToDouble(v), g);
         double v = var2._1$mcD$sp();
         Object g = var2._2();
         return .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToDouble(v)), g);
      } else {
         throw new MatchError(ld);
      }
   }

   public CachedDiffFunction(final DiffFunction obj, final CanCopy evidence$1) {
      this.obj = obj;
      this.evidence$1 = evidence$1;
      Function1.$init$(this);
      ImmutableNumericOps.$init$(this);
      NumericOps.$init$(this);
      StochasticDiffFunction.$init$(this);
      DiffFunction.$init$(this);
      this.lastData = null;
   }
}
