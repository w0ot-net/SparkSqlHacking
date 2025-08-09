package breeze.optimize;

import breeze.generic.UFunc;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.norm$;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.math.MutableEnumeratedCoordinateField;
import breeze.numerics.package$I$iBoolImpl$;
import breeze.util.Isomorphism;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.Tuple3;
import scala.Predef.ArrowAssoc.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.DoubleRef;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055f\u0001B\u000b\u0017\u0001mA\u0011\"\u000e\u0001\u0003\u0002\u0003\u0006IA\u000e#\t\u0011\u001d\u0003!\u0011!Q\u0001\n!C\u0001b\u0013\u0001\u0003\u0002\u0003\u0006I\u0001\u0014\u0005\t+\u0002\u0011\t\u0011)A\u0006-\")A\f\u0001C\u0001;\")A\f\u0001C\u0001I\")A\f\u0001C\u0001[\")A\f\u0001C\u0001g\")A\f\u0001C\u0001u\"1A\f\u0001C\u0001\u0003\u0003Aq!a\u0003\u0001\t#\ni\u0001C\u0004\u0002&\u0001!\t&a\n\t\u000f\u0005M\u0002\u0001\"\u0015\u00026!9\u0011q\b\u0001\u0005R\u0005\u0005\u0003bBA+\u0001\u0011%\u0011qK\u0004\n\u0003C2\u0012\u0011!E\u0001\u0003G2\u0001\"\u0006\f\u0002\u0002#\u0005\u0011Q\r\u0005\u00079F!\t!! \t\u0013\u0005}\u0014#%A\u0005\u0002\u0005\u0005\u0005\"CAO#\u0005\u0005I\u0011BAP\u0005\u0015yu\u000bT)O\u0015\t9\u0002$\u0001\u0005paRLW.\u001b>f\u0015\u0005I\u0012A\u00022sK\u0016TXm\u0001\u0001\u0016\u0007q\u00016eE\u0002\u0001;=\u00022AH\u0010\"\u001b\u00051\u0012B\u0001\u0011\u0017\u0005\u0015a%IR$T!\t\u00113\u0005\u0004\u0001\u0005\u000b\u0011\u0002!\u0019A\u0013\u0003\u0003Q\u000b\"A\n\u0017\u0011\u0005\u001dRS\"\u0001\u0015\u000b\u0003%\nQa]2bY\u0006L!a\u000b\u0015\u0003\u000f9{G\u000f[5oOB\u0011q%L\u0005\u0003]!\u00121!\u00118z!\t\u00014'D\u00012\u0015\t\u0011\u0004$\u0001\u0003vi&d\u0017B\u0001\u001b2\u0005M\u0019VM]5bY&T\u0018M\u00197f\u0019><w-\u001b8h\u0003A\u0019wN\u001c<fe\u001e,gnY3DQ\u0016\u001c7\u000eE\u00028\u0003\u0006r!\u0001O \u000f\u0005erdB\u0001\u001e>\u001b\u0005Y$B\u0001\u001f\u001b\u0003\u0019a$o\\8u}%\t\u0011$\u0003\u0002\u00181%\u0011\u0001IF\u0001\u0014\r&\u00148\u000f^(sI\u0016\u0014X*\u001b8j[&TXM]\u0005\u0003\u0005\u000e\u0013\u0001cQ8om\u0016\u0014x-\u001a8dK\u000eCWmY6\u000b\u0005\u00013\u0012BA\u001bF\u0013\t1eCA\nGSJ\u001cHo\u0014:eKJl\u0015N\\5nSj,'/A\u0001n!\t9\u0013*\u0003\u0002KQ\t\u0019\u0011J\u001c;\u0002\u000b1\f$/Z4\u0011\t\u001djuJU\u0005\u0003\u001d\"\u0012\u0011BR;oGRLwN\\\u0019\u0011\u0005\t\u0002F!B)\u0001\u0005\u0004)#!A&\u0011\u0005\u001d\u001a\u0016B\u0001+)\u0005\u0019!u.\u001e2mK\u0006)1\u000f]1dKB)qKW\u0011P%6\t\u0001L\u0003\u0002Z1\u0005!Q.\u0019;i\u0013\tY\u0006L\u0001\u0011NkR\f'\r\\3F]VlWM]1uK\u0012\u001cun\u001c:eS:\fG/\u001a$jK2$\u0017A\u0002\u001fj]&$h\b\u0006\u0003_C\n\u001cGCA0a!\u0011q\u0002aT\u0011\t\u000bU+\u00019\u0001,\t\u000bU*\u0001\u0019\u0001\u001c\t\u000b\u001d+\u0001\u0019\u0001%\t\u000b-+\u0001\u0019\u0001'\u0015\u000b\u0015<\u0017N[6\u0015\u0005}3\u0007\"B+\u0007\u0001\b1\u0006\"\u00025\u0007\u0001\u0004A\u0015aB7bq&#XM\u001d\u0005\u0006\u000f\u001a\u0001\r\u0001\u0013\u0005\u0006\u0017\u001a\u0001\r\u0001\u0014\u0005\u0006Y\u001a\u0001\rAU\u0001\ni>dWM]1oG\u0016$BA\u001c9reR\u0011ql\u001c\u0005\u0006+\u001e\u0001\u001dA\u0016\u0005\u0006Q\u001e\u0001\r\u0001\u0013\u0005\u0006\u000f\u001e\u0001\r\u0001\u0013\u0005\u0006\u0017\u001e\u0001\r\u0001\u0014\u000b\u0006iZ<\b0\u001f\u000b\u0003?VDQ!\u0016\u0005A\u0004YCQ\u0001\u001b\u0005A\u0002!CQa\u0012\u0005A\u0002!CQa\u0013\u0005A\u0002ICq\u0001\u001c\u0005\u0011\u0002\u0003\u0007!\u000b\u0006\u0003|{z|HCA0}\u0011\u0015)\u0016\u0002q\u0001W\u0011\u0015A\u0017\u00021\u0001I\u0011\u00159\u0015\u00021\u0001I\u0011\u0015Y\u0015\u00021\u0001S)\u0019\t\u0019!a\u0002\u0002\nQ\u0019q,!\u0002\t\u000bUS\u00019\u0001,\t\u000b!T\u0001\u0019\u0001%\t\u000b\u001dS\u0001\u0019\u0001%\u0002-\rDwn\\:f\t\u0016\u001c8-\u001a8u\t&\u0014Xm\u0019;j_:$R!IA\b\u00037Aq!!\u0005\f\u0001\u0004\t\u0019\"A\u0003ti\u0006$X\r\u0005\u0003\u0002\u0016\u0005]Q\"\u0001\u0001\n\u0007\u0005eQIA\u0003Ti\u0006$X\rC\u0004\u0002\u001e-\u0001\r!a\b\u0002\u0005\u0019t\u0007\u0003\u0002\u0010\u0002\"\u0005J1!a\t\u0017\u00051!\u0015N\u001a4Gk:\u001cG/[8o\u0003E!W\r^3s[&tWm\u0015;faNK'0\u001a\u000b\b%\u0006%\u00121FA\u0018\u0011\u001d\t\t\u0002\u0004a\u0001\u0003'Aq!!\f\r\u0001\u0004\ty\"A\u0001g\u0011\u0019\t\t\u0004\u0004a\u0001C\u0005\u0019A-\u001b:\u0002\u0011Q\f7.Z*uKB$r!IA\u001c\u0003s\tY\u0004C\u0004\u0002\u00125\u0001\r!a\u0005\t\r\u0005ER\u00021\u0001\"\u0011\u0019\ti$\u0004a\u0001%\u0006A1\u000f^3q'&TX-\u0001\u0004bI*,8\u000f\u001e\u000b\t\u0003\u0007\nI%!\u0014\u0002RA)q%!\u0012SC%\u0019\u0011q\t\u0015\u0003\rQ+\b\u000f\\33\u0011\u0019\tYE\u0004a\u0001C\u0005!a.Z<Y\u0011\u0019\tyE\u0004a\u0001C\u00059a.Z<He\u0006$\u0007BBA*\u001d\u0001\u0007!+\u0001\u0004oK^4\u0016\r\\\u0001\u000fG>l\u0007/\u001e;f\u001fJ$\b.\u00198u)\u0015\t\u0013\u0011LA/\u0011\u0019\tYf\u0004a\u0001C\u0005\t\u0001\u0010\u0003\u0004\u0002`=\u0001\r!I\u0001\u0005OJ\fG-A\u0003P/2\u000bf\n\u0005\u0002\u001f#M)\u0011#a\u001a\u0002nA\u0019q%!\u001b\n\u0007\u0005-\u0004F\u0001\u0004B]f\u0014VM\u001a\t\u0005\u0003_\nI(\u0004\u0002\u0002r)!\u00111OA;\u0003\tIwN\u0003\u0002\u0002x\u0005!!.\u0019<b\u0013\u0011\tY(!\u001d\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0005\u0005\r\u0014a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$C'\u0006\u0004\u0002\u0004\u0006e\u00151T\u000b\u0003\u0003\u000bS3AUADW\t\tI\t\u0005\u0003\u0002\f\u0006UUBAAG\u0015\u0011\ty)!%\u0002\u0013Ut7\r[3dW\u0016$'bAAJQ\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005]\u0015Q\u0012\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,G!B)\u0014\u0005\u0004)C!\u0002\u0013\u0014\u0005\u0004)\u0013\u0001D<sSR,'+\u001a9mC\u000e,GCAAQ!\u0011\t\u0019+!+\u000e\u0005\u0005\u0015&\u0002BAT\u0003k\nA\u0001\\1oO&!\u00111VAS\u0005\u0019y%M[3di\u0002"
)
public class OWLQN extends LBFGS {
   private final Function1 l1reg;
   public final MutableEnumeratedCoordinateField breeze$optimize$OWLQN$$space;

   public static double $lessinit$greater$default$4() {
      return OWLQN$.MODULE$.$lessinit$greater$default$4();
   }

   public Object chooseDescentDirection(final FirstOrderMinimizer.State state, final DiffFunction fn) {
      Object x$1 = state.adjustedGradient();
      Object x$2 = state.copy$default$1();
      double x$3 = state.copy$default$2();
      double x$4 = state.copy$default$4();
      Object x$5 = state.copy$default$5();
      int x$6 = state.copy$default$6();
      double x$7 = state.copy$default$7();
      LBFGS.ApproximateInverseHessian x$8 = (LBFGS.ApproximateInverseHessian)state.copy$default$8();
      Object x$9 = state.copy$default$9();
      boolean x$10 = state.copy$default$10();
      Option x$11 = state.copy$default$11();
      Object descentDir = super.chooseDescentDirection(state.copy(x$2, x$3, x$1, x$4, x$5, x$6, x$7, x$8, x$9, x$10, x$11), fn);
      Object correctedDir = this.breeze$optimize$OWLQN$$space.zipMapValues().map$mcDD$sp(descentDir, state.adjustedGradient(), (JFunction2.mcDDD.sp)(x0$1, x1$1) -> {
         Tuple2.mcDD.sp var6 = new Tuple2.mcDD.sp(x0$1, x1$1);
         if (var6 != null) {
            double d = ((Tuple2)var6)._1$mcD$sp();
            double g = ((Tuple2)var6)._2$mcD$sp();
            double var4 = d * g < (double)0 ? d : (double)0.0F;
            return var4;
         } else {
            throw new MatchError(var6);
         }
      });
      return correctedDir;
   }

   public double determineStepSize(final FirstOrderMinimizer.State state, final DiffFunction f, final Object dir) {
      int iter = state.iter();
      double possibleNorm = BoxesRunTime.unboxToDouble(((ImmutableNumericOps)this.breeze$optimize$OWLQN$$space.hasOps().apply(dir)).dot(state.grad(), this.breeze$optimize$OWLQN$$space.dotVV()));
      DiffFunction ff = new DiffFunction(state, dir, f) {
         // $FF: synthetic field
         private final OWLQN $outer;
         private final FirstOrderMinimizer.State state$1;
         private final Object dir$1;
         private final DiffFunction f$1;

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

         public Tuple2 calculate(final double alpha) {
            Object newX = this.$outer.takeStep(this.state$1, this.dir$1, alpha);
            Tuple2 var7 = this.f$1.calculate(newX);
            if (var7 != null) {
               double v = var7._1$mcD$sp();
               Object newG = var7._2();
               Tuple2 var4 = new Tuple2(BoxesRunTime.boxToDouble(v), newG);
               double v = var4._1$mcD$sp();
               Object newG = var4._2();
               Tuple2 var15 = this.$outer.adjust(newX, newG, v);
               if (var15 != null) {
                  double adjv = var15._1$mcD$sp();
                  Object adjgrad = var15._2();
                  Tuple2 var3 = new Tuple2(BoxesRunTime.boxToDouble(adjv), adjgrad);
                  double adjvx = var3._1$mcD$sp();
                  Object adjgradx = var3._2();
                  return .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToDouble(adjvx)), ((ImmutableNumericOps)this.$outer.breeze$optimize$OWLQN$$space.hasOps().apply(adjgradx)).dot(this.dir$1, this.$outer.breeze$optimize$OWLQN$$space.dotVV()));
               } else {
                  throw new MatchError(var15);
               }
            } else {
               throw new MatchError(var7);
            }
         }

         public {
            if (OWLQN.this == null) {
               throw null;
            } else {
               this.$outer = OWLQN.this;
               this.state$1 = state$1;
               this.dir$1 = dir$1;
               this.f$1 = f$1;
               Function1.$init$(this);
               ImmutableNumericOps.$init$(this);
               NumericOps.$init$(this);
               StochasticDiffFunction.$init$(this);
               DiffFunction.$init$(this);
            }
         }
      };
      double x$1 = state.value();
      double x$2 = iter < 1 ? 0.1 : (double)0.5F;
      int x$3 = BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$2();
      double x$4 = BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$4();
      double x$5 = BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$5();
      double x$6 = BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$6();
      double x$7 = BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$7();
      double x$8 = BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$8();
      boolean x$9 = BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$9();
      boolean x$10 = BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$10();
      BacktrackingLineSearch search = new BacktrackingLineSearch(x$1, x$3, x$2, x$4, x$5, x$6, x$7, x$8, x$9, x$10);
      double alpha = search.minimize(ff, iter < 1 ? (double)0.5F / BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(state.grad(), this.breeze$optimize$OWLQN$$space.normImpl())) : (double)1.0F);
      return alpha;
   }

   public Object takeStep(final FirstOrderMinimizer.State state, final Object dir, final double stepSize) {
      Object stepped = ((NumericOps)this.breeze$optimize$OWLQN$$space.hasOps().apply(state.x())).$plus(((ImmutableNumericOps)this.breeze$optimize$OWLQN$$space.hasOps().apply(dir)).$times(BoxesRunTime.boxToDouble(stepSize), this.breeze$optimize$OWLQN$$space.mulVS_M()), this.breeze$optimize$OWLQN$$space.addVV());
      Object orthant = this.computeOrthant(state.x(), state.adjustedGradient());
      return this.breeze$optimize$OWLQN$$space.zipMapValues().map$mcDD$sp(stepped, orthant, (JFunction2.mcDDD.sp)(x0$1, x1$1) -> {
         Tuple2.mcDD.sp var6 = new Tuple2.mcDD.sp(x0$1, x1$1);
         if (var6 != null) {
            double v = ((Tuple2)var6)._1$mcD$sp();
            double ov = ((Tuple2)var6)._2$mcD$sp();
            double var4 = v * BoxesRunTime.unboxToDouble(breeze.numerics.package.I$.MODULE$.apply(BoxesRunTime.boxToBoolean(scala.math.package..MODULE$.signum(v) == scala.math.package..MODULE$.signum(ov)), package$I$iBoolImpl$.MODULE$));
            return var4;
         } else {
            throw new MatchError(var6);
         }
      });
   }

   public Tuple2 adjust(final Object newX, final Object newGrad, final double newVal) {
      DoubleRef adjValue = DoubleRef.create(newVal);
      Object res = this.breeze$optimize$OWLQN$$space.zipMapKeyValues().mapActive(newX, newGrad, (x0$1, x1$1, x2$1) -> BoxesRunTime.boxToDouble($anonfun$adjust$1(this, adjValue, x0$1, BoxesRunTime.unboxToDouble(x1$1), BoxesRunTime.unboxToDouble(x2$1))));
      return .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToDouble(adjValue.elem)), res);
   }

   private Object computeOrthant(final Object x, final Object grad) {
      Object orth = this.breeze$optimize$OWLQN$$space.zipMapValues().map$mcDD$sp(x, grad, (JFunction2.mcDDD.sp)(x0$1, x1$1) -> {
         Tuple2.mcDD.sp var6 = new Tuple2.mcDD.sp(x0$1, x1$1);
         if (var6 != null) {
            double v = ((Tuple2)var6)._1$mcD$sp();
            double gv = ((Tuple2)var6)._2$mcD$sp();
            double var4 = v != (double)0 ? scala.math.package..MODULE$.signum(v) : scala.math.package..MODULE$.signum(-gv);
            return var4;
         } else {
            throw new MatchError(var6);
         }
      });
      return orth;
   }

   // $FF: synthetic method
   public static final double $anonfun$adjust$1(final OWLQN $this, final DoubleRef adjValue$1, final Object x0$1, final double x1$1, final double x2$1) {
      Tuple3 var11 = new Tuple3(x0$1, BoxesRunTime.boxToDouble(x1$1), BoxesRunTime.boxToDouble(x2$1));
      if (var11 != null) {
         Object i = var11._1();
         double xv = BoxesRunTime.unboxToDouble(var11._2());
         double v = BoxesRunTime.unboxToDouble(var11._3());
         double l1regValue = BoxesRunTime.unboxToDouble($this.l1reg.apply(i));
         scala.Predef..MODULE$.require(l1regValue >= (double)0.0F);
         double var10000;
         if (l1regValue == (double)0.0F) {
            var10000 = v;
         } else {
            adjValue$1.elem += Math.abs(l1regValue * xv);
            double var9;
            if ((double)0.0F == xv) {
               double delta_$plus = v + l1regValue;
               double delta_$minus = v - l1regValue;
               var9 = delta_$minus > (double)0 ? delta_$minus : (delta_$plus < (double)0 ? delta_$plus : (double)0.0F);
            } else {
               var9 = v + scala.math.package..MODULE$.signum(xv) * l1regValue;
            }

            var10000 = var9;
         }

         double var7 = var10000;
         return var7;
      } else {
         throw new MatchError(var11);
      }
   }

   public OWLQN(final FirstOrderMinimizer.ConvergenceCheck convergenceCheck, final int m, final Function1 l1reg, final MutableEnumeratedCoordinateField space) {
      super(convergenceCheck, m, space);
      this.l1reg = l1reg;
      this.breeze$optimize$OWLQN$$space = space;
      scala.Predef..MODULE$.require(m > 0);
   }

   public OWLQN(final int maxIter, final int m, final Function1 l1reg, final double tolerance, final MutableEnumeratedCoordinateField space) {
      this(FirstOrderMinimizer$.MODULE$.defaultConvergenceCheck(maxIter, tolerance, FirstOrderMinimizer$.MODULE$.defaultConvergenceCheck$default$3(), FirstOrderMinimizer$.MODULE$.defaultConvergenceCheck$default$4(), space), m, l1reg, space);
   }

   public OWLQN(final int maxIter, final int m, final Function1 l1reg, final MutableEnumeratedCoordinateField space) {
      this(maxIter, m, l1reg, 1.0E-8, space);
   }

   public OWLQN(final int maxIter, final int m, final double l1reg, final double tolerance, final MutableEnumeratedCoordinateField space) {
      this(maxIter, m, new Serializable(l1reg) {
         private static final long serialVersionUID = 0L;
         private final double l1reg$1;

         public final double apply(final Object x$1) {
            return this.l1reg$1;
         }

         public {
            this.l1reg$1 = l1reg$1;
         }
      }, tolerance, space);
   }

   public OWLQN(final int maxIter, final int m, final double l1reg, final MutableEnumeratedCoordinateField space) {
      this(maxIter, m, new Serializable(l1reg) {
         private static final long serialVersionUID = 0L;
         private final double l1reg$2;

         public final double apply(final Object x$2) {
            return this.l1reg$2;
         }

         public {
            this.l1reg$2 = l1reg$2;
         }
      }, 1.0E-8, space);
   }

   public OWLQN(final int maxIter, final int m, final MutableEnumeratedCoordinateField space) {
      this(maxIter, m, new Serializable() {
         private static final long serialVersionUID = 0L;

         public final double apply(final Object x$3) {
            return (double)1.0F;
         }
      }, 1.0E-8, space);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
