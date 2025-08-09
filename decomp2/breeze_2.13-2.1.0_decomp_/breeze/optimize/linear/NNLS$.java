package breeze.optimize.linear;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.operators.HasOps$;
import breeze.optimize.proximal.QpGenerator$;
import breeze.stats.distributions.Rand$;
import java.io.Serializable;
import scala..less.colon.less.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class NNLS$ implements Serializable {
   public static final NNLS$ MODULE$ = new NNLS$();

   public int $lessinit$greater$default$1() {
      return -1;
   }

   public double computeObjectiveValue(final DenseMatrix ata, final DenseVector atb, final DenseVector x) {
      double res = BoxesRunTime.unboxToDouble(((ImmutableNumericOps)((ImmutableNumericOps)x.t(HasOps$.MODULE$.transposeTensor(.MODULE$.refl()))).$times(ata, HasOps$.MODULE$.impl_OpMulMatrix_DVTt_DMT_eq_DMT(HasOps$.MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD()))).$times(x, HasOps$.MODULE$.transTimesNormalFromDot(HasOps$.MODULE$.canDotD()))) * (double)0.5F - BoxesRunTime.unboxToDouble(atb.dot(x, HasOps$.MODULE$.canDotD()));
      return res;
   }

   public NNLS apply(final int iters) {
      return new NNLS(iters);
   }

   public void main(final String[] args) {
      if (args.length < 2) {
         scala.Predef..MODULE$.println("Usage: NNLS n s");
         scala.Predef..MODULE$.println("Test NNLS with quadratic function of dimension n for s consecutive solves");
         throw scala.sys.package..MODULE$.exit(1);
      } else {
         int problemSize = scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(args[0]));
         int numSolves = scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(args[1]));
         NNLS nnls = new NNLS(this.$lessinit$greater$default$1());
         int i = 0;

         long nnlsTime;
         for(nnlsTime = 0L; i < numSolves; ++i) {
            DenseMatrix ata = QpGenerator$.MODULE$.getGram(problemSize);
            DenseVector atb = (DenseVector)DenseVector$.MODULE$.rand(problemSize, Rand$.MODULE$.gaussian((double)0.0F, (double)1.0F), scala.reflect.ClassTag..MODULE$.Double());
            long startTime = System.nanoTime();
            nnls.minimize(ata, atb);
            nnlsTime += System.nanoTime() - startTime;
         }

         scala.Predef..MODULE$.println((new StringBuilder(29)).append("NNLS problemSize ").append(problemSize).append(" solves ").append(numSolves).append(" ").append((double)nnlsTime / (double)1000000.0F).append(" ms").toString());
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(NNLS$.class);
   }

   private NNLS$() {
   }
}
