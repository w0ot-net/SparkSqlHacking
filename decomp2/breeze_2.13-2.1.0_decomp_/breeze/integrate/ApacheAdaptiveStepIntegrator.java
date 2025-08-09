package breeze.integrate;

import breeze.linalg.DenseVector;
import java.lang.invoke.SerializedLambda;
import org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.Option.;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005]4Qa\u0006\r\u0002\u0002uA\u0001\u0002\u000b\u0001\u0003\u0002\u0003\u0006I!\u000b\u0005\te\u0001\u0011\t\u0011)A\u0005S!)1\u0007\u0001C\u0001i\u0011)\u0001\b\u0001B\u0001s!9Q\n\u0001b\u0001\n\u0013q\u0005B\u0002*\u0001A\u0003%q\nC\u0004T\u0001\t\u0007I\u0011\u0002(\t\rQ\u0003\u0001\u0015!\u0003P\u0011))\u0006\u0001%A\u0001\u0004\u0003\u0006IA\u0016\u0005\b9\u0002\u0011\r\u0011\"\u0005^\u0011\u0019q\u0006\u0001)A\u00053\"9q\f\u0001b\u0001\n#i\u0006B\u00021\u0001A\u0003%\u0011lB\u0003b1!\u0005!MB\u0003\u00181!\u00051\rC\u00034\u001f\u0011\u0005A\rC\u0004f\u001f\t\u0007I\u0011\u00014\t\r\u001d|\u0001\u0015!\u00030\u0011\u001dAwB1A\u0005\u0002\u0019Da![\b!\u0002\u0013y\u0003b\u00026\u0010#\u0003%\ta\u001b\u0005\bm>\t\n\u0011\"\u0001l\u0005q\t\u0005/Y2iK\u0006#\u0017\r\u001d;jm\u0016\u001cF/\u001a9J]R,wM]1u_JT!!\u0007\u000e\u0002\u0013%tG/Z4sCR,'\"A\u000e\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u00192\u0001\u0001\u0010%!\ty\"%D\u0001!\u0015\u0005\t\u0013!B:dC2\f\u0017BA\u0012!\u0005\u0019\te.\u001f*fMB\u0011QEJ\u0007\u00021%\u0011q\u0005\u0007\u0002\u0014\u0003B\f7\r[3PI\u0016Le\u000e^3he\u0006$xN]\u0001\u0007e\u0016dGk\u001c7\u0011\u0007)js&D\u0001,\u0015\ta#$\u0001\u0004mS:\fGnZ\u0005\u0003]-\u00121\u0002R3og\u00164Vm\u0019;peB\u0011q\u0004M\u0005\u0003c\u0001\u0012a\u0001R8vE2,\u0017AB1cgR{G.\u0001\u0004=S:LGO\u0010\u000b\u0004kY:\u0004CA\u0013\u0001\u0011\u001dA3\u0001%AA\u0002%BqAM\u0002\u0011\u0002\u0003\u0007\u0011FA\u0001U#\tQT\b\u0005\u0002 w%\u0011A\b\t\u0002\b\u001d>$\b.\u001b8h!\tq4*D\u0001@\u0015\t\u0001\u0015)\u0001\u0005o_:\u001cH/\u001b4g\u0015\t\u00115)A\u0002pI\u0016T!\u0001R#\u0002\u000b5\fG\u000f[\u001a\u000b\u0005\u0019;\u0015aB2p[6|gn\u001d\u0006\u0003\u0011&\u000ba!\u00199bG\",'\"\u0001&\u0002\u0007=\u0014x-\u0003\u0002M\u007f\tQ\u0012\tZ1qi&4Xm\u0015;faNL'0Z%oi\u0016<'/\u0019;pe\u0006Q1o\\7f%\u0016dGk\u001c7\u0016\u0003=\u00032a\b)*\u0013\t\t\u0006E\u0001\u0004PaRLwN\\\u0001\fg>lWMU3m)>d\u0007%\u0001\u0006t_6,\u0017IY:U_2\f1b]8nK\u0006\u00137\u000fV8mA\u0005\u0019\u0001\u0010J\u0019\u0011\t}9\u0016,W\u0005\u00031\u0002\u0012a\u0001V;qY\u0016\u0014\u0004cA\u0010[_%\u00111\f\t\u0002\u0006\u0003J\u0014\u0018-_\u0001\u0005CR{G.F\u0001Z\u0003\u0015\tGk\u001c7!\u0003\u0011\u0011Hk\u001c7\u0002\u000bI$v\u000e\u001c\u0011\u00029\u0005\u0003\u0018m\u00195f\u0003\u0012\f\u0007\u000f^5wKN#X\r]%oi\u0016<'/\u0019;peB\u0011QeD\n\u0003\u001fy!\u0012AY\u0001\u000eI\u00164\u0017-\u001e7u%\u0016dGk\u001c7\u0016\u0003=\na\u0002Z3gCVdGOU3m)>d\u0007%A\u0007eK\u001a\fW\u000f\u001c;BEN$v\u000e\\\u0001\u000fI\u00164\u0017-\u001e7u\u0003\n\u001cHk\u001c7!\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%cU\tAN\u000b\u0002*[.\na\u000e\u0005\u0002pi6\t\u0001O\u0003\u0002re\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003g\u0002\n!\"\u00198o_R\fG/[8o\u0013\t)\bOA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\f1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u0012\u0004"
)
public abstract class ApacheAdaptiveStepIntegrator implements ApacheOdeIntegrator {
   private final Option someRelTol;
   private final Option someAbsTol;
   // $FF: synthetic field
   private final Tuple2 x$1;
   private final double[] aTol;
   private final double[] rTol;
   private AdaptiveStepsizeIntegrator inner;

   public static DenseVector $lessinit$greater$default$2() {
      return ApacheAdaptiveStepIntegrator$.MODULE$.$lessinit$greater$default$2();
   }

   public static DenseVector $lessinit$greater$default$1() {
      return ApacheAdaptiveStepIntegrator$.MODULE$.$lessinit$greater$default$1();
   }

   public static double defaultAbsTol() {
      return ApacheAdaptiveStepIntegrator$.MODULE$.defaultAbsTol();
   }

   public static double defaultRelTol() {
      return ApacheAdaptiveStepIntegrator$.MODULE$.defaultRelTol();
   }

   public DenseVector[] integrate(final Function2 f, final DenseVector y0, final double[] t) {
      return ApacheOdeIntegrator.integrate$(this, f, y0, t);
   }

   public final AdaptiveStepsizeIntegrator inner() {
      return this.inner;
   }

   public final void breeze$integrate$ApacheOdeIntegrator$_setter_$inner_$eq(final AdaptiveStepsizeIntegrator x$1) {
      this.inner = x$1;
   }

   private Option someRelTol() {
      return this.someRelTol;
   }

   private Option someAbsTol() {
      return this.someAbsTol;
   }

   public double[] aTol() {
      return this.aTol;
   }

   public double[] rTol() {
      return this.rTol;
   }

   public ApacheAdaptiveStepIntegrator(final DenseVector relTol, final DenseVector absTol) {
      ApacheOdeIntegrator.$init$(this);
      this.someRelTol = .MODULE$.apply(relTol);
      this.someAbsTol = .MODULE$.apply(absTol);
      Tuple2 var4 = this.someRelTol().isEmpty() && this.someAbsTol().isEmpty() ? new Tuple2(scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Double()), scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Double())) : (!this.someRelTol().isEmpty() && !this.someAbsTol().isEmpty() ? new Tuple2(((DenseVector)this.someAbsTol().get()).toArray$mcD$sp(scala.reflect.ClassTag..MODULE$.Double()), ((DenseVector)this.someRelTol().get()).toArray$mcD$sp(scala.reflect.ClassTag..MODULE$.Double())) : (this.someRelTol().isEmpty() ? new Tuple2(((DenseVector)this.someAbsTol().get()).toArray$mcD$sp(scala.reflect.ClassTag..MODULE$.Double()), scala.Array..MODULE$.fill(((DenseVector)this.someAbsTol().get()).length(), (JFunction0.mcD.sp)() -> ApacheAdaptiveStepIntegrator$.MODULE$.defaultRelTol(), scala.reflect.ClassTag..MODULE$.Double())) : new Tuple2(scala.Array..MODULE$.fill(((DenseVector)this.someRelTol().get()).length(), (JFunction0.mcD.sp)() -> ApacheAdaptiveStepIntegrator$.MODULE$.defaultAbsTol(), scala.reflect.ClassTag..MODULE$.Double()), ((DenseVector)this.someRelTol().get()).toArray$mcD$sp(scala.reflect.ClassTag..MODULE$.Double()))));
      if (var4 == null) {
         throw new MatchError(var4);
      } else {
         double[] aTol = (double[])var4._1();
         double[] rTol = (double[])var4._2();
         Tuple2 var3 = new Tuple2(aTol, rTol);
         this.x$1 = var3;
         this.aTol = (double[])this.x$1._1();
         this.rTol = (double[])this.x$1._2();
         if (!scala.collection.ArrayOps..MODULE$.isEmpty$extension(scala.Predef..MODULE$.doubleArrayOps(this.aTol())) && !scala.collection.ArrayOps..MODULE$.isEmpty$extension(scala.Predef..MODULE$.doubleArrayOps(this.rTol()))) {
            ((AdaptiveStepsizeIntegrator)this.inner()).setStepSizeControl(((AdaptiveStepsizeIntegrator)this.inner()).getMinStep(), ((AdaptiveStepsizeIntegrator)this.inner()).getMaxStep(), this.aTol(), this.rTol());
         } else {
            ((AdaptiveStepsizeIntegrator)this.inner()).setStepSizeControl(((AdaptiveStepsizeIntegrator)this.inner()).getMinStep(), ((AdaptiveStepsizeIntegrator)this.inner()).getMaxStep(), ApacheAdaptiveStepIntegrator$.MODULE$.defaultAbsTol(), ApacheAdaptiveStepIntegrator$.MODULE$.defaultRelTol());
         }

         Statics.releaseFence();
      }
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
