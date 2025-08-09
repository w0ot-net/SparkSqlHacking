package breeze.optimize.linear;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.diag$;
import breeze.linalg.operators.HasOps$;
import breeze.storage.Zero$;
import breeze.util.LazyLogger;
import breeze.util.SerializableLogging;
import java.lang.invoke.SerializedLambda;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u000514A\u0001D\u0007\u0001)!)\u0011\u0005\u0001C\u0001E!)Q\u0005\u0001C\u0001M!9q\bAI\u0001\n\u0003\u0001\u0005bB&\u0001#\u0003%\t\u0001Q\u0004\u0006\u0019\u0002A\t!\u0014\u0004\u0006\u001f\u0002A\t\u0001\u0015\u0005\u0006C\u0019!\t!X\u0004\u0006=6A\ta\u0018\u0004\u0006\u00195A\t\u0001\u0019\u0005\u0006C%!\t!\u0019\u0005\bE&\t\t\u0011\"\u0003d\u00055\teMZ5oKN\u001b\u0017\r\\5oO*\u0011abD\u0001\u0007Y&tW-\u0019:\u000b\u0005A\t\u0012\u0001C8qi&l\u0017N_3\u000b\u0003I\taA\u0019:fKj,7\u0001A\n\u0004\u0001UY\u0002C\u0001\f\u001a\u001b\u00059\"\"\u0001\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005i9\"AB!osJ+g\r\u0005\u0002\u001d?5\tQD\u0003\u0002\u001f#\u0005!Q\u000f^5m\u0013\t\u0001SDA\nTKJL\u0017\r\\5{C\ndW\rT8hO&tw-\u0001\u0004=S:LGO\u0010\u000b\u0002GA\u0011A\u0005A\u0007\u0002\u001b\u0005AQ.\u0019=j[&TX\rF\u0004(aU:\u0014hO\u001f\u0011\u0007!ZS&D\u0001*\u0015\tQ\u0013#\u0001\u0004mS:\fGnZ\u0005\u0003Y%\u00121\u0002R3og\u00164Vm\u0019;peB\u0011aCL\u0005\u0003_]\u0011a\u0001R8vE2,\u0007\"B\u0019\u0003\u0001\u0004\u0011\u0014!A!\u0011\u0007!\u001aT&\u0003\u00025S\tYA)\u001a8tK6\u000bGO]5y\u0011\u00151$\u00011\u0001(\u0003\u0005\u0011\u0007\"\u0002\u001d\u0003\u0001\u00049\u0013!A2\t\u000bi\u0012\u0001\u0019A\u0014\u0002\u0005a\u0004\u0004b\u0002\u001f\u0003!\u0003\u0005\r!L\u0001\u0006O\u0006lW.\u0019\u0005\b}\t\u0001\n\u00111\u0001.\u0003\r)\u0007o]\u0001\u0013[\u0006D\u0018.\\5{K\u0012\"WMZ1vYR$S'F\u0001BU\ti#iK\u0001D!\t!\u0015*D\u0001F\u0015\t1u)A\u0005v]\u000eDWmY6fI*\u0011\u0001jF\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001&F\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u0013[\u0006D\u0018.\\5{K\u0012\"WMZ1vYR$c'\u0001\tV]\n|WO\u001c3fIB\u0013xN\u00197f[B\u0011aJB\u0007\u0002\u0001\t\u0001RK\u001c2pk:$W\r\u001a)s_\ndW-\\\n\u0003\rE\u0003\"A\u0015.\u000f\u0005MCfB\u0001+X\u001b\u0005)&B\u0001,\u0014\u0003\u0019a$o\\8u}%\t\u0001$\u0003\u0002Z/\u00059\u0001/Y2lC\u001e,\u0017BA.]\u0005%)\u0005pY3qi&|gN\u0003\u0002Z/Q\tQ*A\u0007BM\u001aLg.Z*dC2Lgn\u001a\t\u0003I%\u0019\"!C\u0012\u0015\u0003}\u000bAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\u0012\u0001\u001a\t\u0003K*l\u0011A\u001a\u0006\u0003O\"\fA\u0001\\1oO*\t\u0011.\u0001\u0003kCZ\f\u0017BA6g\u0005\u0019y%M[3di\u0002"
)
public class AffineScaling implements SerializableLogging {
   private volatile UnboundedProblem$ UnboundedProblem$module;
   private transient volatile LazyLogger breeze$util$SerializableLogging$$_the_logger;

   public LazyLogger logger() {
      return SerializableLogging.logger$(this);
   }

   public UnboundedProblem$ UnboundedProblem() {
      if (this.UnboundedProblem$module == null) {
         this.UnboundedProblem$lzycompute$1();
      }

      return this.UnboundedProblem$module;
   }

   public LazyLogger breeze$util$SerializableLogging$$_the_logger() {
      return this.breeze$util$SerializableLogging$$_the_logger;
   }

   public void breeze$util$SerializableLogging$$_the_logger_$eq(final LazyLogger x$1) {
      this.breeze$util$SerializableLogging$$_the_logger = x$1;
   }

   public DenseVector maximize(final DenseMatrix A, final DenseVector b, final DenseVector c, final DenseVector x0, final double gamma, final double eps) {
      boolean converged = false;
      DenseVector x = x0;

      DenseVector xn;
      for(double cv = BoxesRunTime.unboxToDouble(x0.dot(c, HasOps$.MODULE$.canDotD())); !converged; x = xn) {
         DenseVector vk = (DenseVector)b.$minus(A.$times(x, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD()), HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double());
         DenseMatrix D = (DenseMatrix)diag$.MODULE$.apply(vk.$up$colon$up(BoxesRunTime.boxToDouble((double)-2.0F), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpPow()), diag$.MODULE$.diagDVDMImpl(.MODULE$.Double(), Zero$.MODULE$.DoubleZero()));
         DenseVector hx = (DenseVector)((DenseMatrix)((ImmutableNumericOps)((ImmutableNumericOps)A.t(HasOps$.MODULE$.canTranspose_DM())).$times(D, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD())).$times(A, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD())).$bslash(c, HasOps$.MODULE$.impl_OpSolveMatrixBy_DMD_DVD_eq_DVD());
         DenseVector hv = (DenseVector)((ImmutableNumericOps)A.$times(hx, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD())).$times(BoxesRunTime.boxToDouble((double)-1.0F), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulMatrix());
         if (hv.values().exists((JFunction1.mcZD.sp)(x$1) -> x$1 >= (double)0)) {
            throw this.UnboundedProblem();
         }

         IndexedSeq constraints = (IndexedSeq)scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), hv.length()).withFilter((JFunction1.mcZI.sp)(i) -> hv.apply$mcD$sp(i) < (double)0).map((JFunction1.mcDI.sp)(i) -> -vk.apply$mcD$sp(i) / hv.apply$mcD$sp(i));
         double alpha = constraints.size() > 1 ? BoxesRunTime.unboxToDouble(constraints.min(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$)) * gamma : (double)0.0F;
         xn = (DenseVector)x.$plus(hx.$times(BoxesRunTime.boxToDouble(alpha), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulMatrix()), HasOps$.MODULE$.impl_OpAdd_DV_DV_eq_DV_Double());
         double cvn = BoxesRunTime.unboxToDouble(xn.dot(c, HasOps$.MODULE$.canDotD()));
         this.logger().info(() -> (new StringBuilder(13)).append("Current obj: ").append(cvn).toString());
         if (scala.runtime.RichDouble..MODULE$.abs$extension(scala.Predef..MODULE$.doubleWrapper(cvn - cv)) / scala.runtime.RichDouble..MODULE$.max$extension(scala.Predef..MODULE$.doubleWrapper((double)1.0F), cvn) < eps) {
            converged = true;
         }

         cv = cvn;
      }

      return x;
   }

   public double maximize$default$5() {
      return (double)0.5F;
   }

   public double maximize$default$6() {
      return 1.0E-5;
   }

   private final void UnboundedProblem$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.UnboundedProblem$module == null) {
            this.UnboundedProblem$module = new UnboundedProblem$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   public AffineScaling() {
      SerializableLogging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class UnboundedProblem$ extends Exception {
   }
}
