package breeze.optimize;

import breeze.generic.UFunc;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.QuasiTensor;
import breeze.linalg.Tensor;
import breeze.linalg.TensorLike;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanCreateZerosLike;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.util.Isomorphism;
import java.lang.invoke.SerializedLambda;
import scala.;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005Y4AAD\b\u0001)!A1\u0006\u0001B\u0001B\u0003%A\u0006\u0003\u00053\u0001\t\u0005\t\u0015!\u00030\u0011!\u0019\u0004A!A!\u0002\u0017!\u0004\u0002\u0003\u001f\u0001\u0005\u0003\u0005\u000b1B\u001f\t\u0011\u001d\u0003!\u0011!Q\u0001\f!CQa\u0013\u0001\u0005\u00021CQ\u0001\u0016\u0001\u0005BUCQ\u0001\u0017\u0001\u0005\u0002eCQA\u0018\u0001\u0005\u0002};qaY\b\u0002\u0002#\u0005AMB\u0004\u000f\u001f\u0005\u0005\t\u0012A3\t\u000b-[A\u0011\u00014\t\u000f\u001d\\\u0011\u0013!C\u0001Q\nY\u0012\t\u001d9s_bLW.\u0019;f\u000fJ\fG-[3oi\u001a+hn\u0019;j_:T!\u0001E\t\u0002\u0011=\u0004H/[7ju\u0016T\u0011AE\u0001\u0007EJ,WM_3\u0004\u0001U\u0019Q#\u0012\u0012\u0014\u0007\u00011B\u0004\u0005\u0002\u001855\t\u0001DC\u0001\u001a\u0003\u0015\u00198-\u00197b\u0013\tY\u0002D\u0001\u0004B]f\u0014VM\u001a\t\u0004;y\u0001S\"A\b\n\u0005}y!\u0001\u0004#jM\u001a4UO\\2uS>t\u0007CA\u0011#\u0019\u0001!Qa\t\u0001C\u0002\u0011\u0012\u0011\u0001V\t\u0003K!\u0002\"a\u0006\u0014\n\u0005\u001dB\"a\u0002(pi\"Lgn\u001a\t\u0003/%J!A\u000b\r\u0003\u0007\u0005s\u00170A\u0001g!\u00119R\u0006I\u0018\n\u00059B\"!\u0003$v]\u000e$\u0018n\u001c82!\t9\u0002'\u0003\u000221\t1Ai\\;cY\u0016\fq!\u001a9tS2|g.A\u0003{KJ|7\u000f\u0005\u00036u\u0001\u0002S\"\u0001\u001c\u000b\u0005]B\u0014aB:vaB|'\u000f\u001e\u0006\u0003sE\ta\u0001\\5oC2<\u0017BA\u001e7\u0005I\u0019\u0015M\\\"sK\u0006$XMW3s_Nd\u0015n[3\u0002\tYLWm\u001e\t\u0005/y\u0002\u0003)\u0003\u0002@1\t\u0001B\u0005\\3tg\u0012\u001aw\u000e\\8oI1,7o\u001d\t\u0005\u0003\n#u&D\u00019\u0013\t\u0019\u0005H\u0001\u0004UK:\u001cxN\u001d\t\u0003C\u0015#QA\u0012\u0001C\u0002\u0011\u0012\u0011aS\u0001\u0005G>\u0004\u0018\u0010E\u00026\u0013\u0002J!A\u0013\u001c\u0003\u000f\r\u000bgnQ8qs\u00061A(\u001b8jiz\"2!\u0014*T)\u0011qu\nU)\u0011\tu\u0001A\t\t\u0005\u0006g\u0019\u0001\u001d\u0001\u000e\u0005\u0006y\u0019\u0001\u001d!\u0010\u0005\u0006\u000f\u001a\u0001\u001d\u0001\u0013\u0005\u0006W\u0019\u0001\r\u0001\f\u0005\be\u0019\u0001\n\u00111\u00010\u0003\u001d1\u0018\r\\;f\u0003R$\"a\f,\t\u000b];\u0001\u0019\u0001\u0011\u0002\u0003a\f\u0011bY1mGVd\u0017\r^3\u0015\u0005ik\u0006\u0003B\f\\_\u0001J!\u0001\u0018\r\u0003\rQ+\b\u000f\\33\u0011\u00159\u0006\u00021\u0001!\u0003E\u0019\u0017\r\\2vY\u0006$X-\u00118e!JLg\u000e\u001e\u000b\u00045\u0002\f\u0007\"B,\n\u0001\u0004\u0001\u0003\"\u00022\n\u0001\u0004\u0001\u0013\u0001\u0003;sk\u0016<%/\u00193\u00027\u0005\u0003\bO]8yS6\fG/Z$sC\u0012LWM\u001c;Gk:\u001cG/[8o!\ti2b\u0005\u0002\f-Q\tA-A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEM\u000b\u0004SR,X#\u00016+\u0005=Z7&\u00017\u0011\u00055\u0014X\"\u00018\u000b\u0005=\u0004\u0018!C;oG\",7m[3e\u0015\t\t\b$\u0001\u0006b]:|G/\u0019;j_:L!a\u001d8\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rB\u0003G\u001b\t\u0007A\u0005B\u0003$\u001b\t\u0007A\u0005"
)
public class ApproximateGradientFunction implements DiffFunction {
   private final Function1 f;
   private final double epsilon;
   private final CanCreateZerosLike zeros;
   private final .less.colon.less view;
   private final CanCopy copy;

   public static double $lessinit$greater$default$2() {
      return ApproximateGradientFunction$.MODULE$.$lessinit$greater$default$2();
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

   public double valueAt(final Object x) {
      return BoxesRunTime.unboxToDouble(this.f.apply(x));
   }

   public Tuple2 calculate(final Object x) {
      double fx = BoxesRunTime.unboxToDouble(this.f.apply(x));
      Object grad = this.zeros.apply(x);
      Object xx = this.copy.apply(x);
      ((QuasiTensor)this.view.apply(x)).iterator().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$calculate$1(check$ifrefutable$1))).foreach((x$1) -> {
         $anonfun$calculate$2(this, xx, grad, fx, x$1);
         return BoxedUnit.UNIT;
      });
      return new Tuple2(BoxesRunTime.boxToDouble(fx), grad);
   }

   public Tuple2 calculateAndPrint(final Object x, final Object trueGrad) {
      double fx = BoxesRunTime.unboxToDouble(this.f.apply(x));
      Object grad = this.zeros.apply(x);
      Object xx = this.copy.apply(x);
      ((QuasiTensor)this.view.apply(x)).activeIterator().withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$calculateAndPrint$1(check$ifrefutable$2))).foreach((x$2) -> {
         $anonfun$calculateAndPrint$2(this, xx, grad, fx, trueGrad, x$2);
         return BoxedUnit.UNIT;
      });
      return new Tuple2(BoxesRunTime.boxToDouble(fx), grad);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$calculate$1(final Tuple2 check$ifrefutable$1) {
      boolean var1;
      if (check$ifrefutable$1 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   public static final void $anonfun$calculate$2(final ApproximateGradientFunction $this, final Object xx$1, final Object grad$1, final double fx$1, final Tuple2 x$1) {
      if (x$1 != null) {
         Object k = x$1._1();
         Tensor var9 = (Tensor)$this.view.apply(xx$1);
         var9.update(k, BoxesRunTime.boxToDouble(BoxesRunTime.unboxToDouble(var9.apply(k)) + $this.epsilon));
         ((TensorLike)$this.view.apply(grad$1)).update(k, BoxesRunTime.boxToDouble((BoxesRunTime.unboxToDouble($this.f.apply(xx$1)) - fx$1) / $this.epsilon));
         Tensor var10 = (Tensor)$this.view.apply(xx$1);
         var10.update(k, BoxesRunTime.boxToDouble(BoxesRunTime.unboxToDouble(var10.apply(k)) - $this.epsilon));
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$calculateAndPrint$1(final Tuple2 check$ifrefutable$2) {
      boolean var1;
      if (check$ifrefutable$2 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   public static final void $anonfun$calculateAndPrint$2(final ApproximateGradientFunction $this, final Object xx$2, final Object grad$2, final double fx$2, final Object trueGrad$1, final Tuple2 x$2) {
      if (x$2 != null) {
         Object k = x$2._1();
         Tensor var10 = (Tensor)$this.view.apply(xx$2);
         var10.update(k, BoxesRunTime.boxToDouble(BoxesRunTime.unboxToDouble(var10.apply(k)) + $this.epsilon));
         ((TensorLike)$this.view.apply(grad$2)).update(k, BoxesRunTime.boxToDouble((BoxesRunTime.unboxToDouble($this.f.apply(xx$2)) - fx$2) / $this.epsilon));
         Tensor var11 = (Tensor)$this.view.apply(xx$2);
         var11.update(k, BoxesRunTime.boxToDouble(BoxesRunTime.unboxToDouble(var11.apply(k)) - $this.epsilon));
         scala.Predef..MODULE$.println((new StringBuilder(30)).append("diff : ").append($this.epsilon).append(" val: ").append(BoxesRunTime.unboxToDouble(((TensorLike)$this.view.apply(grad$2)).apply(k)) - BoxesRunTime.unboxToDouble(((TensorLike)$this.view.apply(trueGrad$1)).apply(k))).append(" dp: ").append(((TensorLike)$this.view.apply(trueGrad$1)).apply(k)).append(" empirical: ").append(((TensorLike)$this.view.apply(grad$2)).apply(k)).toString());
         BoxedUnit var7 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$2);
      }
   }

   public ApproximateGradientFunction(final Function1 f, final double epsilon, final CanCreateZerosLike zeros, final .less.colon.less view, final CanCopy copy) {
      this.f = f;
      this.epsilon = epsilon;
      this.zeros = zeros;
      this.view = view;
      this.copy = copy;
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
