package breeze.optimize;

import breeze.generic.UFunc;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.util.Isomorphism;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005U3q\u0001C\u0005\u0011\u0002\u0007\u0005a\u0002C\u00030\u0001\u0011\u0005\u0001\u0007C\u00035\u0001\u0011\u0005S\u0007C\u00037\u0001\u0011\u0005q\u0007C\u0003;\u0001\u0011\u00051\bC\u0003>\u0001\u0011\u0015a\bC\u0003A\u0001\u0019\u0005\u0011\tC\u0003G\u0001\u0011\u0005qI\u0001\fTi>\u001c\u0007.Y:uS\u000e$\u0015N\u001a4Gk:\u001cG/[8o\u0015\tQ1\"\u0001\u0005paRLW.\u001b>f\u0015\u0005a\u0011A\u00022sK\u0016TXm\u0001\u0001\u0016\u0005=Y2\u0003\u0002\u0001\u0011-\u001d\u0002\"!\u0005\u000b\u000e\u0003IQ\u0011aE\u0001\u0006g\u000e\fG.Y\u0005\u0003+I\u0011a!\u00118z%\u00164\u0007\u0003B\t\u00183\u0011J!\u0001\u0007\n\u0003\u0013\u0019+hn\u0019;j_:\f\u0004C\u0001\u000e\u001c\u0019\u0001!Q\u0001\b\u0001C\u0002u\u0011\u0011\u0001V\t\u0003=\u0005\u0002\"!E\u0010\n\u0005\u0001\u0012\"a\u0002(pi\"Lgn\u001a\t\u0003#\tJ!a\t\n\u0003\u0007\u0005s\u0017\u0010\u0005\u0002\u0012K%\u0011aE\u0005\u0002\u0007\t>,(\r\\3\u0011\u0007!ZS&D\u0001*\u0015\tQ3\"\u0001\u0004mS:\fGnZ\u0005\u0003Y%\u0012!BT;nKJL7m\u00149t!\rq\u0003!G\u0007\u0002\u0013\u00051A%\u001b8ji\u0012\"\u0012!\r\t\u0003#IJ!a\r\n\u0003\tUs\u0017\u000e^\u0001\u0005e\u0016\u0004(/F\u0001.\u0003)9'/\u00193jK:$\u0018\t\u001e\u000b\u00033aBQ!O\u0002A\u0002e\t\u0011\u0001_\u0001\bm\u0006dW/Z!u)\t!C\bC\u0003:\t\u0001\u0007\u0011$A\u0003baBd\u0017\u0010\u0006\u0002%\u007f!)\u0011(\u0002a\u00013\u0005I1-\u00197dk2\fG/\u001a\u000b\u0003\u0005\u0016\u0003B!E\"%3%\u0011AI\u0005\u0002\u0007)V\u0004H.\u001a\u001a\t\u000be2\u0001\u0019A\r\u0002\u0017QD'o\\;hQ2+gn]\u000b\u0003\u0011.#\"!S'\u0011\u00079\u0002!\n\u0005\u0002\u001b\u0017\u0012)Aj\u0002b\u0001;\t\tQ\u000bC\u0003O\u000f\u0001\u000fq*A\u0001m!\u0011\u00016+\u0007&\u000e\u0003ES!AU\u0006\u0002\tU$\u0018\u000e\\\u0005\u0003)F\u00131\"S:p[>\u0014\b\u000f[5t[\u0002"
)
public interface StochasticDiffFunction extends Function1, NumericOps {
   default StochasticDiffFunction repr() {
      return this;
   }

   default Object gradientAt(final Object x) {
      return this.calculate(x)._2();
   }

   default double valueAt(final Object x) {
      return this.calculate(x)._1$mcD$sp();
   }

   default double apply(final Object x) {
      return this.valueAt(x);
   }

   Tuple2 calculate(final Object x);

   default StochasticDiffFunction throughLens(final Isomorphism l) {
      return new StochasticDiffFunction(l) {
         // $FF: synthetic field
         private final StochasticDiffFunction $outer;
         private final Isomorphism l$1;

         public StochasticDiffFunction repr() {
            return StochasticDiffFunction.super.repr();
         }

         public Object gradientAt(final Object x) {
            return StochasticDiffFunction.super.gradientAt(x);
         }

         public double valueAt(final Object x) {
            return StochasticDiffFunction.super.valueAt(x);
         }

         public final double apply(final Object x) {
            return StochasticDiffFunction.super.apply(x);
         }

         public StochasticDiffFunction throughLens(final Isomorphism l) {
            return StochasticDiffFunction.super.throughLens(l);
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

         public Tuple2 calculate(final Object u) {
            Object t = this.l$1.backward(u);
            Tuple2 var5 = this.$outer.calculate(t);
            if (var5 != null) {
               double obj = var5._1$mcD$sp();
               Object gu = var5._2();
               Tuple2 var2 = new Tuple2(BoxesRunTime.boxToDouble(obj), gu);
               double objx = var2._1$mcD$sp();
               Object gu = var2._2();
               return new Tuple2(BoxesRunTime.boxToDouble(objx), this.l$1.forward(gu));
            } else {
               throw new MatchError(var5);
            }
         }

         public {
            if (StochasticDiffFunction.this == null) {
               throw null;
            } else {
               this.$outer = StochasticDiffFunction.this;
               this.l$1 = l$1;
               Function1.$init$(this);
               ImmutableNumericOps.$init$(this);
               NumericOps.$init$(this);
               StochasticDiffFunction.$init$(this);
            }
         }
      };
   }

   static void $init$(final StochasticDiffFunction $this) {
   }
}
