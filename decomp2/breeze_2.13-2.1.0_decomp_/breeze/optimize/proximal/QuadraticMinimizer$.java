package breeze.optimize.proximal;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.LapackException;
import breeze.linalg.cholesky;
import breeze.linalg.cholesky$;
import breeze.linalg.norm$;
import breeze.linalg.operators.HasOps$;
import breeze.math.Semiring$;
import breeze.numerics.package$;
import breeze.optimize.FirstOrderMinimizer;
import breeze.optimize.LBFGS;
import breeze.optimize.LBFGS$;
import breeze.optimize.OWLQN;
import breeze.optimize.linear.ConjugateGradient;
import breeze.optimize.linear.ConjugateGradient$;
import breeze.optimize.linear.NNLS;
import breeze.optimize.linear.NNLS$;
import breeze.optimize.linear.PowerMethod;
import breeze.optimize.linear.PowerMethod$;
import breeze.stats.distributions.Rand$;
import breeze.storage.Zero$;
import dev.ludovic.netlib.blas.BLAS;
import dev.ludovic.netlib.lapack.LAPACK;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.netlib.util.intW;
import scala.Enumeration;
import scala.MatchError;
import scala.Tuple6;
import scala.runtime.BoxesRunTime;
import scala.runtime.DoubleRef;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.RichInt.;
import scala.runtime.java8.JFunction1;

public final class QuadraticMinimizer$ implements Serializable {
   public static final QuadraticMinimizer$ MODULE$ = new QuadraticMinimizer$();

   public Proximal $lessinit$greater$default$2() {
      return null;
   }

   public DenseMatrix $lessinit$greater$default$3() {
      return null;
   }

   public DenseVector $lessinit$greater$default$4() {
      return null;
   }

   public int $lessinit$greater$default$5() {
      return -1;
   }

   public double $lessinit$greater$default$6() {
      return 1.0E-6;
   }

   public double $lessinit$greater$default$7() {
      return 1.0E-4;
   }

   public double $lessinit$greater$default$8() {
      return (double)1.0F;
   }

   public void gemv(final double alpha, final DenseMatrix A, final DenseVector x, final double beta, final DenseVector y) {
      String tStrA = A.isTranspose() ? "T" : "N";
      int mA = !A.isTranspose() ? A.rows() : A.cols();
      int nA = !A.isTranspose() ? A.rows() : A.cols();
      BLAS.getInstance().dgemv(tStrA, mA, nA, alpha, A.data$mcD$sp(), mA, x.data$mcD$sp(), 1, beta, y.data$mcD$sp(), 1);
   }

   public void dgetrs(final DenseMatrix A, final int[] pivot, final DenseVector x) {
      int n = x.length();
      int left$macro$1 = A.rows();
      if (left$macro$1 != n) {
         throw new IllegalArgumentException((new StringBuilder(38)).append("requirement failed: ").append("A.rows == n (").append(left$macro$1).append(" ").append("!=").append(" ").append(n).append(")").toString());
      } else {
         int nrhs = 1;
         intW info = new intW(0);
         LAPACK.getInstance().dgetrs("No transpose", n, nrhs, A.data$mcD$sp(), 0, n, pivot, 0, x.data$mcD$sp(), 0, n, info);
         if (info.val > 0) {
            throw new LapackException("DGETRS: LU solve unsuccessful");
         }
      }
   }

   public void dpotrs(final DenseMatrix A, final DenseVector x) {
      int n = x.length();
      int nrhs = 1;
      int left$macro$1 = A.rows();
      if (left$macro$1 != n) {
         throw new IllegalArgumentException((new StringBuilder(38)).append("requirement failed: ").append("A.rows == n (").append(left$macro$1).append(" ").append("!=").append(" ").append(n).append(")").toString());
      } else {
         intW info = new intW(0);
         LAPACK.getInstance().dpotrs("L", n, nrhs, A.data$mcD$sp(), 0, n, x.data$mcD$sp(), 0, n, info);
         if (info.val > 0) {
            throw new LapackException("DPOTRS : Leading minor of order i of A is not positive definite.");
         }
      }
   }

   public double normColumn(final DenseMatrix H) {
      DoubleRef absColSum = DoubleRef.create((double)0.0F);
      DoubleRef maxColSum = DoubleRef.create((double)0.0F);
      .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), H.cols()).foreach$mVc$sp((JFunction1.mcVI.sp)(c) -> {
         .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), H.rows()).foreach$mVc$sp((JFunction1.mcVI.sp)(r) -> absColSum.elem += scala.math.package..MODULE$.abs(H.apply$mcD$sp(r, c)));
         if (absColSum.elem > maxColSum.elem) {
            maxColSum.elem = absColSum.elem;
         }

         absColSum.elem = (double)0.0F;
      });
      return maxColSum.elem;
   }

   public double approximateMaxEigen(final DenseMatrix H) {
      PowerMethod pm = new PowerMethod(PowerMethod$.MODULE$.$lessinit$greater$default$1(), PowerMethod$.MODULE$.$lessinit$greater$default$2());
      DenseVector init = (DenseVector)DenseVector$.MODULE$.rand(H.rows(), Rand$.MODULE$.gaussian((double)0.0F, (double)1.0F), scala.reflect.ClassTag..MODULE$.Double());
      return pm.eigen(H, init);
   }

   public double approximateMinEigen(final DenseMatrix H) {
      DenseMatrix R = (DenseMatrix)((ImmutableNumericOps)cholesky$.MODULE$.apply(H, cholesky.ImplCholesky_DM$.MODULE$)).t(HasOps$.MODULE$.canTranspose_DM());
      PowerMethod pmInv = PowerMethod$.MODULE$.inverse(PowerMethod$.MODULE$.inverse$default$1(), PowerMethod$.MODULE$.inverse$default$2());
      DenseVector init = (DenseVector)DenseVector$.MODULE$.rand(H.rows(), Rand$.MODULE$.gaussian((double)0.0F, (double)1.0F), scala.reflect.ClassTag..MODULE$.Double());
      return (double)1.0F / pmInv.eigen(R, init);
   }

   public QuadraticMinimizer apply(final int rank, final Enumeration.Value constraint, final double lambda) {
      QuadraticMinimizer var5;
      label108: {
         Enumeration.Value var10000 = Constraint$.MODULE$.SMOOTH();
         if (var10000 == null) {
            if (constraint == null) {
               break label108;
            }
         } else if (var10000.equals(constraint)) {
            break label108;
         }

         label109: {
            var10000 = Constraint$.MODULE$.POSITIVE();
            if (var10000 == null) {
               if (constraint == null) {
                  break label109;
               }
            } else if (var10000.equals(constraint)) {
               break label109;
            }

            label110: {
               var10000 = Constraint$.MODULE$.BOX();
               if (var10000 == null) {
                  if (constraint == null) {
                     break label110;
                  }
               } else if (var10000.equals(constraint)) {
                  break label110;
               }

               label111: {
                  var10000 = Constraint$.MODULE$.PROBABILITYSIMPLEX();
                  if (var10000 == null) {
                     if (constraint == null) {
                        break label111;
                     }
                  } else if (var10000.equals(constraint)) {
                     break label111;
                  }

                  label112: {
                     var10000 = Constraint$.MODULE$.EQUALITY();
                     if (var10000 == null) {
                        if (constraint == null) {
                           break label112;
                        }
                     } else if (var10000.equals(constraint)) {
                        break label112;
                     }

                     var10000 = Constraint$.MODULE$.SPARSE();
                     if (var10000 == null) {
                        if (constraint != null) {
                           throw new MatchError(constraint);
                        }
                     } else if (!var10000.equals(constraint)) {
                        throw new MatchError(constraint);
                     }

                     var5 = new QuadraticMinimizer(rank, (new ProximalL1(ProximalL1$.MODULE$.apply$default$1())).setLambda(lambda), this.$lessinit$greater$default$3(), this.$lessinit$greater$default$4(), this.$lessinit$greater$default$5(), this.$lessinit$greater$default$6(), this.$lessinit$greater$default$7(), this.$lessinit$greater$default$8());
                     return var5;
                  }

                  DenseMatrix Aeq = DenseMatrix$.MODULE$.ones$mDc$sp(1, rank, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero(), Semiring$.MODULE$.semiringD());
                  DenseVector beq = DenseVector$.MODULE$.ones$mDc$sp(1, scala.reflect.ClassTag..MODULE$.Double(), Semiring$.MODULE$.semiringD());
                  var5 = new QuadraticMinimizer(rank, new ProjectPos(), Aeq, beq, this.$lessinit$greater$default$5(), this.$lessinit$greater$default$6(), this.$lessinit$greater$default$7(), this.$lessinit$greater$default$8());
                  return var5;
               }

               var5 = new QuadraticMinimizer(rank, new ProjectProbabilitySimplex(lambda), this.$lessinit$greater$default$3(), this.$lessinit$greater$default$4(), this.$lessinit$greater$default$5(), this.$lessinit$greater$default$6(), this.$lessinit$greater$default$7(), this.$lessinit$greater$default$8());
               return var5;
            }

            DenseVector lb = DenseVector$.MODULE$.zeros$mDc$sp(rank, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            DenseVector ub = DenseVector$.MODULE$.ones$mDc$sp(rank, scala.reflect.ClassTag..MODULE$.Double(), Semiring$.MODULE$.semiringD());
            var5 = new QuadraticMinimizer(rank, new ProjectBox(lb, ub), this.$lessinit$greater$default$3(), this.$lessinit$greater$default$4(), this.$lessinit$greater$default$5(), this.$lessinit$greater$default$6(), this.$lessinit$greater$default$7(), this.$lessinit$greater$default$8());
            return var5;
         }

         var5 = new QuadraticMinimizer(rank, new ProjectPos(), this.$lessinit$greater$default$3(), this.$lessinit$greater$default$4(), this.$lessinit$greater$default$5(), this.$lessinit$greater$default$6(), this.$lessinit$greater$default$7(), this.$lessinit$greater$default$8());
         return var5;
      }

      var5 = new QuadraticMinimizer(rank, this.$lessinit$greater$default$2(), this.$lessinit$greater$default$3(), this.$lessinit$greater$default$4(), this.$lessinit$greater$default$5(), this.$lessinit$greater$default$6(), this.$lessinit$greater$default$7(), this.$lessinit$greater$default$8());
      return var5;
   }

   public double apply$default$3() {
      return (double)1.0F;
   }

   public double computeObjective(final DenseMatrix h, final DenseVector q, final DenseVector x) {
      double res = BoxesRunTime.unboxToDouble(((ImmutableNumericOps)((ImmutableNumericOps)x.t(HasOps$.MODULE$.transposeTensor(scala..less.colon.less..MODULE$.refl()))).$times(h, HasOps$.MODULE$.impl_OpMulMatrix_DVTt_DMT_eq_DMT(HasOps$.MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD()))).$times(x, HasOps$.MODULE$.transTimesNormalFromDot(HasOps$.MODULE$.canDotD()))) * (double)0.5F + BoxesRunTime.unboxToDouble(q.dot(x, HasOps$.MODULE$.canDotD()));
      return res;
   }

   public DenseVector optimizeWithLBFGS(final DenseVector init, final DenseMatrix H, final DenseVector q) {
      LBFGS lbfgs = new LBFGS(-1, 7, LBFGS$.MODULE$.$lessinit$greater$default$3(), DenseVector$.MODULE$.space_Double());
      FirstOrderMinimizer.State state = lbfgs.minimizeAndReturnState(new QuadraticMinimizer.Cost(H, q), init);
      return (DenseVector)state.x();
   }

   public void main(final String[] args) {
      if (args.length < 4) {
         scala.Predef..MODULE$.println("Usage: QpSolver n m lambda beta");
         scala.Predef..MODULE$.println("Test QpSolver with a simple quadratic function of dimension n and m equalities lambda beta for elasticNet");
         throw scala.sys.package..MODULE$.exit(1);
      } else {
         int problemSize = scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(args[0]));
         int nequalities = scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(args[1]));
         double lambda = scala.collection.StringOps..MODULE$.toDouble$extension(scala.Predef..MODULE$.augmentString(args[2]));
         double beta = scala.collection.StringOps..MODULE$.toDouble$extension(scala.Predef..MODULE$.augmentString(args[3]));
         scala.Predef..MODULE$.println((new StringBuilder(48)).append("Generating randomized QPs with rank ").append(problemSize).append(" equalities ").append(nequalities).toString());
         Tuple6 var10 = QpGenerator$.MODULE$.apply(problemSize, nequalities);
         if (var10 != null) {
            DenseMatrix aeq = (DenseMatrix)var10._1();
            DenseVector b = (DenseVector)var10._2();
            DenseVector bl = (DenseVector)var10._3();
            DenseVector bu = (DenseVector)var10._4();
            DenseVector q = (DenseVector)var10._5();
            DenseMatrix h = (DenseMatrix)var10._6();
            Tuple6 var2 = new Tuple6(aeq, b, bl, bu, q, h);
            DenseMatrix aeq = (DenseMatrix)var2._1();
            DenseVector b = (DenseVector)var2._2();
            DenseVector bl = (DenseVector)var2._3();
            DenseVector bu = (DenseVector)var2._4();
            DenseVector q = (DenseVector)var2._5();
            DenseMatrix h = (DenseMatrix)var2._6();
            scala.Predef..MODULE$.println((new StringBuilder(86)).append("Test QuadraticMinimizer, CG , BFGS and OWLQN with ").append(problemSize).append(" variables and ").append(nequalities).append(" equality constraints").toString());
            long luStart = System.nanoTime();
            DenseVector luResult = (DenseVector)((ImmutableNumericOps)h.$bslash(q, HasOps$.MODULE$.impl_OpSolveMatrixBy_DMD_DVD_eq_DVD())).$times$colon$times(BoxesRunTime.boxToDouble((double)-1.0F), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulScalar());
            long luTime = System.nanoTime() - luStart;
            ConjugateGradient cg = new ConjugateGradient(ConjugateGradient$.MODULE$.$lessinit$greater$default$1(), ConjugateGradient$.MODULE$.$lessinit$greater$default$2(), ConjugateGradient$.MODULE$.$lessinit$greater$default$3(), ConjugateGradient$.MODULE$.$lessinit$greater$default$4(), DenseVector$.MODULE$.space_Double(), HasOps$.MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD());
            long startCg = System.nanoTime();
            DenseVector cgResult = (DenseVector)cg.minimize(q.$times$colon$times(BoxesRunTime.boxToDouble((double)-1.0F), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulScalar()), h);
            long cgTime = System.nanoTime() - startCg;
            QuadraticMinimizer qpSolver = new QuadraticMinimizer(problemSize, this.$lessinit$greater$default$2(), this.$lessinit$greater$default$3(), this.$lessinit$greater$default$4(), this.$lessinit$greater$default$5(), this.$lessinit$greater$default$6(), this.$lessinit$greater$default$7(), this.$lessinit$greater$default$8());
            long qpStart = System.nanoTime();
            DenseVector result = qpSolver.minimize(h, q);
            long qpTime = System.nanoTime() - qpStart;
            long startBFGS = System.nanoTime();
            DenseVector bfgsResult = this.optimizeWithLBFGS((DenseVector)DenseVector$.MODULE$.rand(problemSize, DenseVector$.MODULE$.rand$default$2(), scala.reflect.ClassTag..MODULE$.Double()), h, q);
            long bfgsTime = System.nanoTime() - startBFGS;
            scala.Predef..MODULE$.println((new StringBuilder(27)).append("||qp - lu|| norm ").append(norm$.MODULE$.apply(result.$minus(luResult, HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double()), BoxesRunTime.boxToInteger(2), norm$.MODULE$.fromCanNormInt(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Double())))).append(" max-norm ").append(norm$.MODULE$.apply(result.$minus(luResult, HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double()), BoxesRunTime.boxToDouble(package$.MODULE$.inf()), norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Double()))).toString());
            scala.Predef..MODULE$.println((new StringBuilder(27)).append("||cg - lu|| norm ").append(norm$.MODULE$.apply(cgResult.$minus(luResult, HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double()), BoxesRunTime.boxToInteger(2), norm$.MODULE$.fromCanNormInt(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Double())))).append(" max-norm ").append(norm$.MODULE$.apply(cgResult.$minus(luResult, HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double()), BoxesRunTime.boxToDouble(package$.MODULE$.inf()), norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Double()))).toString());
            scala.Predef..MODULE$.println((new StringBuilder(29)).append("||bfgs - lu|| norm ").append(norm$.MODULE$.apply(bfgsResult.$minus(luResult, HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double()), BoxesRunTime.boxToInteger(2), norm$.MODULE$.fromCanNormInt(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Double())))).append(" max-norm ").append(norm$.MODULE$.apply(bfgsResult.$minus(luResult, HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double()), BoxesRunTime.boxToDouble(package$.MODULE$.inf()), norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Double()))).toString());
            double luObj = this.computeObjective(h, q, luResult);
            double bfgsObj = this.computeObjective(h, q, bfgsResult);
            double qpObj = this.computeObjective(h, q, result);
            scala.Predef..MODULE$.println((new StringBuilder(23)).append("Objective lu ").append(luObj).append(" bfgs ").append(bfgsObj).append(" qp ").append(qpObj).toString());
            scala.Predef..MODULE$.println((new StringBuilder(34)).append("dim ").append(problemSize).append(" lu ").append((double)luTime / (double)1000000.0F).append(" ms qp ").append((double)qpTime / (double)1000000.0F).append(" ms cg ").append((double)cgTime / (double)1000000.0F).append(" ms bfgs ").append((double)bfgsTime / (double)1000000.0F).append(" ms").toString());
            double lambdaL1 = lambda * beta;
            double lambdaL2 = lambda * ((double)1 - beta);
            DenseMatrix regularizedGram = (DenseMatrix)h.$plus(DenseMatrix$.MODULE$.eye$mDc$sp(h.rows(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero(), Semiring$.MODULE$.semiringD()).$times$colon$times(BoxesRunTime.boxToDouble(lambdaL2), HasOps$.MODULE$.op_DM_S_Double_OpMulScalar()), HasOps$.MODULE$.op_DM_DM_Double_OpAdd());
            QuadraticMinimizer sparseQp = this.apply(h.rows(), Constraint$.MODULE$.SPARSE(), lambdaL1);
            long sparseQpStart = System.nanoTime();
            QuadraticMinimizer.State sparseQpResult = sparseQp.minimizeAndReturnState(regularizedGram, q);
            long sparseQpTime = System.nanoTime() - sparseQpStart;
            OWLQN owlqn = new OWLQN(-1, 7, lambdaL1, 1.0E-6, DenseVector$.MODULE$.space_Double());
            DenseVector init = (DenseVector)DenseVector$.MODULE$.rand(problemSize, DenseVector$.MODULE$.rand$default$2(), scala.reflect.ClassTag..MODULE$.Double());
            long startOWLQN = System.nanoTime();
            FirstOrderMinimizer.State owlqnResult = owlqn.minimizeAndReturnState(new QuadraticMinimizer.Cost(regularizedGram, q), init);
            long owlqnTime = System.nanoTime() - startOWLQN;
            scala.Predef..MODULE$.println((new StringBuilder(36)).append("||owlqn - sparseqp|| norm ").append(norm$.MODULE$.apply(((ImmutableNumericOps)owlqnResult.x()).$minus(sparseQpResult.x(), HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double()), BoxesRunTime.boxToInteger(2), norm$.MODULE$.fromCanNormInt(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Double())))).append(" inf-norm ").append(norm$.MODULE$.apply(((ImmutableNumericOps)owlqnResult.x()).$minus(sparseQpResult.x(), HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double()), BoxesRunTime.boxToDouble(package$.MODULE$.inf()), norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Double()))).toString());
            scala.Predef..MODULE$.println((new StringBuilder(36)).append("sparseQp ").append((double)sparseQpTime / (double)1000000.0F).append(" ms iters ").append(sparseQpResult.iter()).append(" owlqn ").append((double)owlqnTime / (double)1000000.0F).append(" ms iters ").append(owlqnResult.iter()).toString());
            QuadraticMinimizer posQp = this.apply(h.rows(), Constraint$.MODULE$.POSITIVE(), (double)0.0F);
            long posQpStart = System.nanoTime();
            QuadraticMinimizer.State posQpResult = posQp.minimizeAndReturnState(h, q);
            long posQpTime = System.nanoTime() - posQpStart;
            NNLS nnls = new NNLS(NNLS$.MODULE$.$lessinit$greater$default$1());
            long nnlsStart = System.nanoTime();
            NNLS.State nnlsResult = nnls.minimizeAndReturnState(h, q);
            long nnlsTime = System.nanoTime() - nnlsStart;
            scala.Predef..MODULE$.println((new StringBuilder(32)).append("posQp ").append((double)posQpTime / (double)1000000.0F).append(" ms iters ").append(posQpResult.iter()).append(" nnls ").append((double)nnlsTime / (double)1000000.0F).append(" ms iters ").append(nnlsResult.iter()).toString());
            QuadraticMinimizer boundsQp = new QuadraticMinimizer(h.rows(), new ProjectBox(bl, bu), this.$lessinit$greater$default$3(), this.$lessinit$greater$default$4(), this.$lessinit$greater$default$5(), this.$lessinit$greater$default$6(), this.$lessinit$greater$default$7(), this.$lessinit$greater$default$8());
            long boundsQpStart = System.nanoTime();
            QuadraticMinimizer.State boundsQpResult = boundsQp.minimizeAndReturnState(h, q);
            long boundsQpTime = System.nanoTime() - boundsQpStart;
            scala.Predef..MODULE$.println((new StringBuilder(30)).append("boundsQp ").append((double)boundsQpTime / (double)1000000.0F).append(" ms iters ").append(boundsQpResult.iter()).append(" converged ").append(boundsQpResult.converged()).toString());
            QuadraticMinimizer qpEquality = new QuadraticMinimizer(h.rows(), new ProjectPos(), aeq, b, this.$lessinit$greater$default$5(), this.$lessinit$greater$default$6(), this.$lessinit$greater$default$7(), this.$lessinit$greater$default$8());
            long qpEqualityStart = System.nanoTime();
            QuadraticMinimizer.State qpEqualityResult = qpEquality.minimizeAndReturnState(h, q);
            long qpEqualityTime = System.nanoTime() - qpEqualityStart;
            scala.Predef..MODULE$.println((new StringBuilder(33)).append("Qp Equality ").append((double)qpEqualityTime / (double)1000000.0F).append(" ms iters ").append(qpEqualityResult.iter()).append(" converged ").append(qpEqualityResult.converged()).toString());
         } else {
            throw new MatchError(var10);
         }
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(QuadraticMinimizer$.class);
   }

   private QuadraticMinimizer$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
