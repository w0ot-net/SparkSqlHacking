package breeze.optimize;

import breeze.generic.UFunc;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.math.InnerProductModule;
import breeze.util.Isomorphism;
import scala.;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005y4qAC\u0006\u0011\u0002\u0007\u0005\u0001\u0003C\u0003/\u0001\u0011\u0005q\u0006C\u00034\u0001\u0011\u0005C\u0007C\u00036\u0001\u0011\u0005a\u0007C\u0003@\u0001\u0011\u0005\u0003iB\u0003O\u0017!\u0005qJB\u0003\u000b\u0017!\u0005\u0001\u000bC\u0003U\r\u0011\u0005Q\u000bC\u0003W\r\u0011\u0005q\u000bC\u0003W\r\u0011\u0005\u0011O\u0001\u0007ES\u001a4g)\u001e8di&|gN\u0003\u0002\r\u001b\u0005Aq\u000e\u001d;j[&TXMC\u0001\u000f\u0003\u0019\u0011'/Z3{K\u000e\u0001QCA\t\u001f'\u0011\u0001!\u0003G\u0014\u0011\u0005M1R\"\u0001\u000b\u000b\u0003U\tQa]2bY\u0006L!a\u0006\u000b\u0003\r\u0005s\u0017PU3g!\rI\"\u0004H\u0007\u0002\u0017%\u00111d\u0003\u0002\u0017'R|7\r[1ti&\u001cG)\u001b4g\rVt7\r^5p]B\u0011QD\b\u0007\u0001\t\u0015y\u0002A1\u0001!\u0005\u0005!\u0016CA\u0011%!\t\u0019\"%\u0003\u0002$)\t9aj\u001c;iS:<\u0007CA\n&\u0013\t1CCA\u0002B]f\u00042\u0001K\u0016.\u001b\u0005I#B\u0001\u0016\u000e\u0003\u0019a\u0017N\\1mO&\u0011A&\u000b\u0002\u000b\u001dVlWM]5d\u001fB\u001c\bcA\r\u00019\u00051A%\u001b8ji\u0012\"\u0012\u0001\r\t\u0003'EJ!A\r\u000b\u0003\tUs\u0017\u000e^\u0001\u0005e\u0016\u0004(/F\u0001.\u0003\u0019\u0019\u0017m\u00195fIR\u0011Qf\u000e\u0005\u0006q\r\u0001\u001d!O\u0001\u0005G>\u0004\u0018\u0010E\u0002;{qi\u0011a\u000f\u0006\u0003y%\nqa];qa>\u0014H/\u0003\u0002?w\t91)\u00198D_BL\u0018a\u0003;ie>,x\r\u001b'f]N,\"!\u0011#\u0015\u0005\t3\u0005cA\r\u0001\u0007B\u0011Q\u0004\u0012\u0003\u0006\u000b\u0012\u0011\r\u0001\t\u0002\u0002+\")q\t\u0002a\u0002\u0011\u0006\tA\u000e\u0005\u0003J\u0019r\u0019U\"\u0001&\u000b\u0005-k\u0011\u0001B;uS2L!!\u0014&\u0003\u0017%\u001bx.\\8sa\"L7/\\\u0001\r\t&4gMR;oGRLwN\u001c\t\u00033\u0019\u00192A\u0002\nR!\tI\"+\u0003\u0002T\u0017\t9B)\u001b4g\rVt7\r^5p]>\u0003\u0018*\u001c9mS\u000eLGo]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003=\u000bAc^5uQ2\u0013$+Z4vY\u0006\u0014\u0018N_1uS>tWc\u0001-`_R\u0019\u0011l[7\u0015\u0005i\u0003'cA.\u0013;\u001a!A\f\u0003\u0001[\u00051a$/\u001a4j]\u0016lWM\u001c;?!\rI\u0002A\u0018\t\u0003;}#Qa\b\u0005C\u0002\u0001BQ!\u0019\u0005A\u0004\t\fQa\u001d9bG\u0016\u0004Ba\u00194_Q6\tAM\u0003\u0002f\u001b\u0005!Q.\u0019;i\u0013\t9GM\u0001\nJ]:,'\u000f\u0015:pIV\u001cG/T8ek2,\u0007CA\nj\u0013\tQGC\u0001\u0004E_V\u0014G.\u001a\u0005\u0006Y\"\u0001\r!X\u0001\u0002I\")a\u000e\u0003a\u0001Q\u00061q/Z5hQR$Q\u0001\u001d\u0005C\u0002\u0001\u0012\u0011!S\u000b\u0004eblHcA:|yR\u0011A/\u001f\t\u00043U<\u0018B\u0001<\f\u0005E\u0011\u0015\r^2i\t&4gMR;oGRLwN\u001c\t\u0003;a$QaH\u0005C\u0002\u0001BQ!Y\u0005A\u0004i\u0004Ba\u00194xQ\")A.\u0003a\u0001i\")a.\u0003a\u0001Q\u0012)\u0001/\u0003b\u0001A\u0001"
)
public interface DiffFunction extends StochasticDiffFunction {
   static BatchDiffFunction withL2Regularization(final BatchDiffFunction d, final double weight, final InnerProductModule space) {
      return DiffFunction$.MODULE$.withL2Regularization(d, weight, space);
   }

   static DiffFunction withL2Regularization(final DiffFunction d, final double weight, final InnerProductModule space) {
      return DiffFunction$.MODULE$.withL2Regularization(d, weight, space);
   }

   static UFunc.UImpl2 castOps(final .less.colon.less v1ev, final .less.colon.less V2ev, final UFunc.UImpl2 op) {
      return DiffFunction$.MODULE$.castOps(v1ev, V2ev, op);
   }

   static UFunc.UImpl2 opDivLHSDiffFunction(final UFunc.UImpl2 opMul) {
      return DiffFunction$.MODULE$.opDivLHSDiffFunction(opMul);
   }

   static UFunc.UImpl2 opDivDiffFunction(final UFunc.UImpl2 opDiv) {
      return DiffFunction$.MODULE$.opDivDiffFunction(opDiv);
   }

   static UFunc.UImpl2 opMulLHSDiffFunction(final UFunc.UImpl2 opMul) {
      return DiffFunction$.MODULE$.opMulLHSDiffFunction(opMul);
   }

   static UFunc.UImpl2 opMulDiffFunction(final UFunc.UImpl2 opMul) {
      return DiffFunction$.MODULE$.opMulDiffFunction(opMul);
   }

   static UFunc.UImpl2 opSubDiffFunction(final UFunc.UImpl2 opSub) {
      return DiffFunction$.MODULE$.opSubDiffFunction(opSub);
   }

   static UFunc.UImpl2 opAddDiffFunction(final UFunc.UImpl2 opAdd) {
      return DiffFunction$.MODULE$.opAddDiffFunction(opAdd);
   }

   default DiffFunction repr() {
      return this;
   }

   default DiffFunction cached(final CanCopy copy) {
      return (DiffFunction)(this instanceof CachedDiffFunction ? this : new CachedDiffFunction(this, copy));
   }

   default DiffFunction throughLens(final Isomorphism l) {
      return new DiffFunction(l) {
         // $FF: synthetic field
         private final DiffFunction $outer;
         private final Isomorphism l$1;

         public DiffFunction repr() {
            return DiffFunction.super.repr();
         }

         public DiffFunction cached(final CanCopy copy) {
            return DiffFunction.super.cached(copy);
         }

         public DiffFunction throughLens(final Isomorphism l) {
            return DiffFunction.super.throughLens(l);
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
            if (DiffFunction.this == null) {
               throw null;
            } else {
               this.$outer = DiffFunction.this;
               this.l$1 = l$1;
               Function1.$init$(this);
               ImmutableNumericOps.$init$(this);
               NumericOps.$init$(this);
               StochasticDiffFunction.$init$(this);
               DiffFunction.$init$(this);
            }
         }
      };
   }

   static void $init$(final DiffFunction $this) {
   }
}
