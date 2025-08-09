package breeze.integrate;

import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import java.lang.invoke.SerializedLambda;
import org.apache.commons.math3.ode.AbstractIntegrator;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import scala.Function2;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.LazyRef;
import scala.runtime.RichInt.;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005-3qAB\u0004\u0011\u0002\u0007\u0005A\u0002C\u0003\u0018\u0001\u0011\u0005\u0001\u0004B\u0003\u001d\u0001\t\u0005Q\u0004C\u00030\u0001\u0019E\u0001\u0007C\u00044\u0001\t\u0007IQ\u0003\u0019\t\u000b!\u0001A\u0011\t\u001b\u0003'\u0005\u0003\u0018m\u00195f\u001f\u0012,\u0017J\u001c;fOJ\fGo\u001c:\u000b\u0005!I\u0011!C5oi\u0016<'/\u0019;f\u0015\u0005Q\u0011A\u00022sK\u0016TXm\u0001\u0001\u0014\u0007\u0001i1\u0003\u0005\u0002\u000f#5\tqBC\u0001\u0011\u0003\u0015\u00198-\u00197b\u0013\t\u0011rB\u0001\u0004B]f\u0014VM\u001a\t\u0003)Ui\u0011aB\u0005\u0003-\u001d\u0011Qb\u00143f\u0013:$Xm\u001a:bi>\u0014\u0018A\u0002\u0013j]&$H\u0005F\u0001\u001a!\tq!$\u0003\u0002\u001c\u001f\t!QK\\5u\u0005\u0005!\u0016C\u0001\u0010\"!\tqq$\u0003\u0002!\u001f\t9aj\u001c;iS:<\u0007C\u0001\u0012.\u001b\u0005\u0019#B\u0001\u0013&\u0003\ryG-\u001a\u0006\u0003M\u001d\nQ!\\1uQNR!\u0001K\u0015\u0002\u000f\r|W.\\8og*\u0011!fK\u0001\u0007CB\f7\r[3\u000b\u00031\n1a\u001c:h\u0013\tq3E\u0001\nBEN$(/Y2u\u0013:$Xm\u001a:bi>\u0014\u0018AB2sK\u0006$X-F\u00012!\t\u0011$!D\u0001\u0001\u0003\u0015IgN\\3s)\u0011)\u0014I\u0012%\u0011\u000791\u0004(\u0003\u00028\u001f\t)\u0011I\u001d:bsB\u0019\u0011\b\u0010 \u000e\u0003iR!aO\u0005\u0002\r1Lg.\u00197h\u0013\ti$HA\u0006EK:\u001cXMV3di>\u0014\bC\u0001\b@\u0013\t\u0001uB\u0001\u0004E_V\u0014G.\u001a\u0005\u0006\u0005\u0016\u0001\raQ\u0001\u0002MB)a\u0002\u0012\u001d?q%\u0011Qi\u0004\u0002\n\rVt7\r^5p]JBQaR\u0003A\u0002a\n!!\u001f\u0019\t\u000b%+\u0001\u0019\u0001&\u0002\u0003Q\u00042A\u0004\u001c?\u0001"
)
public interface ApacheOdeIntegrator extends OdeIntegrator {
   void breeze$integrate$ApacheOdeIntegrator$_setter_$inner_$eq(final AbstractIntegrator x$1);

   AbstractIntegrator create();

   AbstractIntegrator inner();

   // $FF: synthetic method
   static DenseVector[] integrate$(final ApacheOdeIntegrator $this, final Function2 f, final DenseVector y0, final double[] t) {
      return $this.integrate(f, y0, t);
   }

   default DenseVector[] integrate(final Function2 f, final DenseVector y0, final double[] t) {
      LazyRef equations$module = new LazyRef();
      DenseVector[] finalStates = new DenseVector[t.length];
      finalStates[0] = y0;
      .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(1), t.length).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
         double[] result = new double[y0.length()];
         this.inner().integrate(this.equations$2(equations$module, y0, f), t[i - 1], finalStates[i - 1].toArray$mcD$sp(scala.reflect.ClassTag..MODULE$.Double()), t[i], result);
         finalStates[i] = DenseVector$.MODULE$.apply$mDc$sp(result);
      });
      return finalStates;
   }

   // $FF: synthetic method
   private static equations$1$ equations$lzycompute$1(final LazyRef equations$module$1, final DenseVector y0$1, final Function2 f$1) {
      synchronized(equations$module$1){}

      equations$1$ var4;
      try {
         class equations$1$ implements FirstOrderDifferentialEquations {
            private final int getDimension;
            private final Function2 f$1;

            public int getDimension() {
               return this.getDimension;
            }

            public void computeDerivatives(final double t, final double[] y, final double[] yDot) {
               scala.collection.ArrayOps..MODULE$.copyToArray$extension(scala.Predef..MODULE$.doubleArrayOps(((DenseVector)this.f$1.apply(DenseVector$.MODULE$.apply$mDc$sp(y), BoxesRunTime.boxToDouble(t))).toArray$mcD$sp(scala.reflect.ClassTag..MODULE$.Double())), yDot);
            }

            public equations$1$(final DenseVector y0$1, final Function2 f$1) {
               this.f$1 = f$1;
               this.getDimension = y0$1.length();
            }
         }

         var4 = equations$module$1.initialized() ? (equations$1$)equations$module$1.value() : (equations$1$)equations$module$1.initialize(new equations$1$(y0$1, f$1));
      } catch (Throwable var6) {
         throw var6;
      }

      return var4;
   }

   private equations$1$ equations$2(final LazyRef equations$module$1, final DenseVector y0$1, final Function2 f$1) {
      return equations$module$1.initialized() ? (equations$1$)equations$module$1.value() : equations$lzycompute$1(equations$module$1, y0$1, f$1);
   }

   static void $init$(final ApacheOdeIntegrator $this) {
      $this.breeze$integrate$ApacheOdeIntegrator$_setter_$inner_$eq($this.create());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
