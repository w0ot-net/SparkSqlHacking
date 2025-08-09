package breeze.optimize;

import breeze.generic.UFunc;
import breeze.linalg.QuasiTensor;
import breeze.linalg.Tensor;
import breeze.linalg.TensorLike;
import breeze.linalg.support.CanCopy;
import breeze.stats.distributions.Rand$;
import breeze.util.LazyLogger;
import breeze.util.SerializableLogging;
import java.lang.invoke.SerializedLambda;
import scala.;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.immutable.IndexedSeq;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ModuleSerializationProxy;

public final class GradientTester$ implements SerializableLogging {
   public static final GradientTester$ MODULE$ = new GradientTester$();
   private static transient volatile LazyLogger breeze$util$SerializableLogging$$_the_logger;

   static {
      SerializableLogging.$init$(MODULE$);
   }

   public LazyLogger logger() {
      return SerializableLogging.logger$(this);
   }

   public LazyLogger breeze$util$SerializableLogging$$_the_logger() {
      return breeze$util$SerializableLogging$$_the_logger;
   }

   public void breeze$util$SerializableLogging$$_the_logger_$eq(final LazyLogger x$1) {
      breeze$util$SerializableLogging$$_the_logger = x$1;
   }

   public Object test(final DiffFunction f, final Object x, final double randFraction, final boolean skipZeros, final double epsilon, final double tolerance, final Function1 toString, final .less.colon.less view2, final .less.colon.less view, final CanCopy copy, final UFunc.UImpl canNorm, final UFunc.UImpl2 opSub) {
      IndexedSeq indices = (IndexedSeq)Rand$.MODULE$.subsetsOfSize(((QuasiTensor)view.apply(x)).keysIterator().toIndexedSeq(), (int)((double)((TensorLike)view.apply(x)).size() * randFraction + (double)1)).draw();
      return this.testIndices(f, x, indices, skipZeros, toString, epsilon, tolerance, view2, view, copy, canNorm, opSub);
   }

   public double test$default$3() {
      return 0.01;
   }

   public boolean test$default$4() {
      return false;
   }

   public double test$default$5() {
      return 1.0E-8;
   }

   public double test$default$6() {
      return 0.001;
   }

   public Function1 test$default$7() {
      return (x$1) -> x$1.toString();
   }

   public Object testIndices(final DiffFunction f, final Object x, final Iterable indices, final boolean skipZeros, final Function1 toString, final double epsilon, final double tolerance, final .less.colon.less view2, final .less.colon.less view, final CanCopy copy, final UFunc.UImpl canNorm, final UFunc.UImpl2 opSub) {
      Tuple2 var17 = f.calculate(x);
      if (var17 != null) {
         double fx = var17._1$mcD$sp();
         Object trueGrad = var17._2();
         Tuple2 var15 = new Tuple2(BoxesRunTime.boxToDouble(fx), trueGrad);
         double fx = var15._1$mcD$sp();
         Object trueGrad = var15._2();
         Object xx = copy.apply(x);
         Object differences = opSub.apply(x, x);
         IntRef ok = IntRef.create(0);
         IntRef tried = IntRef.create(0);
         int sz = indices.size();
         indices.foreach((k) -> {
            $anonfun$testIndices$1(skipZeros, view, trueGrad, toString, xx, epsilon, f, fx, tolerance, ok, differences, tried, sz, x, k);
            return BoxedUnit.UNIT;
         });
         return differences;
      } else {
         throw new MatchError(var17);
      }
   }

   public boolean testIndices$default$4() {
      return false;
   }

   public Function1 testIndices$default$5() {
      return (x$2) -> x$2.toString();
   }

   public double testIndices$default$6() {
      return 1.0E-8;
   }

   public double testIndices$default$7() {
      return 0.001;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(GradientTester$.class);
   }

   // $FF: synthetic method
   public static final void $anonfun$testIndices$1(final boolean skipZeros$1, final .less.colon.less view$1, final Object trueGrad$1, final Function1 toString$1, final Object xx$1, final double epsilon$1, final DiffFunction f$1, final double fx$1, final double tolerance$1, final IntRef ok$1, final Object differences$1, final IntRef tried$1, final int sz$1, final Object x$4, final Object k) {
      if (skipZeros$1 && BoxesRunTime.unboxToDouble(((TensorLike)view$1.apply(trueGrad$1)).apply(k)) == (double)0.0F) {
         MODULE$.logger().debug(() -> (new StringBuilder(11)).append("Zero Grad: ").append(toString$1.apply(k)).toString());
         scala.Predef..MODULE$.print((new StringBuilder(1)).append((String)toString$1.apply(k)).append(" ").toString());
      } else {
         Tensor var18 = (Tensor)view$1.apply(xx$1);
         var18.update(k, BoxesRunTime.boxToDouble(BoxesRunTime.unboxToDouble(var18.apply(k)) + epsilon$1));
         double grad = (f$1.apply(xx$1) - fx$1) / epsilon$1;
         Tensor var21 = (Tensor)view$1.apply(xx$1);
         var21.update(k, BoxesRunTime.boxToDouble(BoxesRunTime.unboxToDouble(var21.apply(k)) - epsilon$1));
         double relDif = scala.runtime.RichDouble..MODULE$.abs$extension(scala.Predef..MODULE$.doubleWrapper(grad - BoxesRunTime.unboxToDouble(((TensorLike)view$1.apply(trueGrad$1)).apply(k)))) / scala.runtime.RichDouble..MODULE$.max$extension(scala.Predef..MODULE$.doubleWrapper(scala.math.package..MODULE$.max(scala.runtime.RichDouble..MODULE$.abs$extension(scala.Predef..MODULE$.doubleWrapper(BoxesRunTime.unboxToDouble(((TensorLike)view$1.apply(trueGrad$1)).apply(k)))), scala.runtime.RichDouble..MODULE$.abs$extension(scala.Predef..MODULE$.doubleWrapper(grad)))), 1.0E-4);
         if (relDif < tolerance$1) {
            ++ok$1.elem;
            MODULE$.logger().debug(() -> (new StringBuilder(5)).append("OK: ").append(toString$1.apply(k)).append(" ").append(relDif).toString());
         } else {
            MODULE$.logger().warn(() -> (new StringBuilder(0)).append((String)toString$1.apply(k)).append(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString(" relDif: %.3e [eps : %e, calculated: %4.3e empirical: %4.3e]"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToDouble(relDif), BoxesRunTime.boxToDouble(epsilon$1), ((TensorLike)view$1.apply(trueGrad$1)).apply(k), BoxesRunTime.boxToDouble(grad)}))).toString());
         }

         ((TensorLike)view$1.apply(differences$1)).update(k, BoxesRunTime.boxToDouble(relDif));
         ++tried$1.elem;
      }

      if (tried$1.elem % 100 == 0 || tried$1.elem == sz$1) {
         MODULE$.logger().info(() -> {
            Object arg$macro$1 = BoxesRunTime.boxToInteger(tried$1.elem);
            Object arg$macro$2 = BoxesRunTime.boxToInteger(sz$1);
            Object arg$macro$3 = BoxesRunTime.boxToInteger(((TensorLike)view$1.apply(x$4)).size());
            double arg$macro$4 = (double)ok$1.elem * (double)100.0F / (double)tried$1.elem;
            return scala.collection.StringOps..MODULE$.format$extension("Checked %s of %s (out of dimension %s). %.4g%% ok.", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{arg$macro$1, arg$macro$2, arg$macro$3, BoxesRunTime.boxToDouble(arg$macro$4)}));
         });
      }

   }

   private GradientTester$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
