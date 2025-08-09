package breeze.optimize.proximal;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.Vector;
import breeze.linalg.sum$;
import breeze.linalg.operators.HasOps$;
import breeze.math.Semiring$;
import breeze.optimize.DiffFunction;
import breeze.optimize.DiffFunction$;
import breeze.optimize.FirstOrderMinimizer;
import breeze.optimize.OWLQN;
import breeze.optimize.ProjectedQuasiNewton;
import breeze.optimize.ProjectedQuasiNewton$;
import breeze.optimize.SpectralProjectedGradient;
import breeze.optimize.SpectralProjectedGradient$;
import breeze.storage.Zero$;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Enumeration;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction2;

public final class NonlinearMinimizer$ implements Serializable {
   public static final NonlinearMinimizer$ MODULE$ = new NonlinearMinimizer$();

   public int $lessinit$greater$default$2() {
      return -1;
   }

   public int $lessinit$greater$default$3() {
      return 3;
   }

   public int $lessinit$greater$default$4() {
      return 7;
   }

   public double $lessinit$greater$default$5() {
      return (double)1.0F;
   }

   public double $lessinit$greater$default$6() {
      return (double)1.0F;
   }

   public double $lessinit$greater$default$7() {
      return 1.0E-6;
   }

   public double $lessinit$greater$default$8() {
      return 1.0E-4;
   }

   public FirstOrderMinimizer project(final Proximal proximal, final int maxIter, final int m, final double tolerance, final boolean usePQN) {
      NonlinearMinimizer.Projection projectionOp = new NonlinearMinimizer.Projection(proximal);
      Object var10000;
      if (usePQN) {
         Function1 x$1 = (x) -> projectionOp.project(x);
         double x$2 = 1.0E-6;
         boolean x$5 = ProjectedQuasiNewton$.MODULE$.$lessinit$greater$default$3();
         boolean x$6 = ProjectedQuasiNewton$.MODULE$.$lessinit$greater$default$4();
         int x$7 = ProjectedQuasiNewton$.MODULE$.$lessinit$greater$default$6();
         double x$8 = ProjectedQuasiNewton$.MODULE$.$lessinit$greater$default$7();
         boolean x$9 = ProjectedQuasiNewton$.MODULE$.$lessinit$greater$default$9();
         var10000 = new ProjectedQuasiNewton(1.0E-6, m, x$5, x$6, maxIter, x$7, x$8, x$1, x$9, DenseVector$.MODULE$.space_Double());
      } else {
         Function1 x$10 = (x) -> projectionOp.project(x);
         double x$14 = SpectralProjectedGradient$.MODULE$.$lessinit$greater$default$3();
         int x$15 = SpectralProjectedGradient$.MODULE$.$lessinit$greater$default$4();
         double x$16 = SpectralProjectedGradient$.MODULE$.$lessinit$greater$default$5();
         double x$17 = SpectralProjectedGradient$.MODULE$.$lessinit$greater$default$6();
         boolean x$18 = SpectralProjectedGradient$.MODULE$.$lessinit$greater$default$9();
         boolean x$19 = SpectralProjectedGradient$.MODULE$.$lessinit$greater$default$10();
         int x$20 = SpectralProjectedGradient$.MODULE$.$lessinit$greater$default$11();
         int x$21 = SpectralProjectedGradient$.MODULE$.$lessinit$greater$default$12();
         var10000 = new SpectralProjectedGradient(x$10, tolerance, x$14, x$15, x$16, x$17, m, maxIter, x$18, x$19, x$20, x$21, DenseVector$.MODULE$.space_Double());
      }

      return (FirstOrderMinimizer)var10000;
   }

   public int project$default$2() {
      return -1;
   }

   public int project$default$3() {
      return 10;
   }

   public double project$default$4() {
      return 1.0E-6;
   }

   public boolean project$default$5() {
      return false;
   }

   public FirstOrderMinimizer apply(final int ndim, final Enumeration.Value constraint, final double lambda, final boolean usePQN) {
      FirstOrderMinimizer var6;
      label108: {
         Enumeration.Value var10000 = Constraint$.MODULE$.IDENTITY();
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
                  var10000 = Constraint$.MODULE$.EQUALITY();
                  if (var10000 == null) {
                     if (constraint == null) {
                        break label111;
                     }
                  } else if (var10000.equals(constraint)) {
                     break label111;
                  }

                  label112: {
                     var10000 = Constraint$.MODULE$.PROBABILITYSIMPLEX();
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
                           throw new IllegalArgumentException("NonlinearMinimizer does not support the Projection Operator");
                        }
                     } else if (!var10000.equals(constraint)) {
                        throw new IllegalArgumentException("NonlinearMinimizer does not support the Projection Operator");
                     }

                     var6 = this.project(new ProjectL1(lambda), this.project$default$2(), this.project$default$3(), this.project$default$4(), this.project$default$5());
                     return var6;
                  }

                  var6 = this.project(new ProjectProbabilitySimplex(lambda), this.project$default$2(), this.project$default$3(), this.project$default$4(), this.project$default$5());
                  return var6;
               }

               DenseVector aeq = DenseVector$.MODULE$.ones$mDc$sp(ndim, .MODULE$.Double(), Semiring$.MODULE$.semiringD());
               var6 = this.project(new ProjectHyperPlane(aeq, (double)1.0F), this.project$default$2(), this.project$default$3(), this.project$default$4(), this.project$default$5());
               return var6;
            }

            DenseVector lb = DenseVector$.MODULE$.zeros$mDc$sp(ndim, .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            DenseVector ub = DenseVector$.MODULE$.ones$mDc$sp(ndim, .MODULE$.Double(), Semiring$.MODULE$.semiringD());
            var6 = this.project(new ProjectBox(lb, ub), this.project$default$2(), this.project$default$3(), this.project$default$4(), this.project$default$5());
            return var6;
         }

         var6 = this.project(new ProjectPos(), this.project$default$2(), this.project$default$3(), this.project$default$4(), this.project$default$5());
         return var6;
      }

      var6 = this.project(new ProjectIdentity(), this.project$default$2(), this.project$default$3(), this.project$default$4(), this.project$default$5());
      return var6;
   }

   public boolean apply$default$4() {
      return false;
   }

   public void main(final String[] args) {
      if (args.length < 3) {
         scala.Predef..MODULE$.println("Usage: ProjectedQuasiNewton n lambda beta");
         scala.Predef..MODULE$.println("Test NonlinearMinimizer with a quadratic function of dimenion n and m equalities with lambda beta for elasticNet");
         throw scala.sys.package..MODULE$.exit(1);
      } else {
         int problemSize = scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(args[0]));
         double lambda = scala.collection.StringOps..MODULE$.toDouble$extension(scala.Predef..MODULE$.augmentString(args[1]));
         double beta = scala.collection.StringOps..MODULE$.toDouble$extension(scala.Predef..MODULE$.augmentString(args[2]));
         scala.Predef..MODULE$.println((new StringBuilder(46)).append("Generating Linear and Logistic Loss with rank ").append(problemSize).toString());
         Tuple3 var9 = LinearGenerator$.MODULE$.apply(problemSize);
         if (var9 != null) {
            DiffFunction quadraticCost = (DiffFunction)var9._1();
            DenseMatrix h = (DenseMatrix)var9._2();
            DenseVector q = (DenseVector)var9._3();
            Tuple3 var2 = new Tuple3(quadraticCost, h, q);
            DiffFunction quadraticCost = (DiffFunction)var2._1();
            DenseMatrix h = (DenseMatrix)var2._2();
            DenseVector q = (DenseVector)var2._3();
            double lambdaL1 = lambda * beta;
            double lambdaL2 = lambda * ((double)1 - beta);
            OWLQN owlqn = new OWLQN(-1, 10, lambdaL1, 1.0E-6, DenseVector$.MODULE$.space_Double());
            DenseMatrix regularizedGram = (DenseMatrix)h.$plus(DenseMatrix$.MODULE$.eye$mDc$sp(h.rows(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero(), Semiring$.MODULE$.semiringD()).$times$colon$times(BoxesRunTime.boxToDouble(lambdaL2), HasOps$.MODULE$.op_DM_S_Double_OpMulScalar()), HasOps$.MODULE$.op_DM_DM_Double_OpAdd());
            QuadraticMinimizer sparseQp = QuadraticMinimizer$.MODULE$.apply(h.rows(), Constraint$.MODULE$.SPARSE(), lambdaL1);
            long sparseQpStart = System.nanoTime();
            QuadraticMinimizer.State sparseQpResult = sparseQp.minimizeAndReturnState(regularizedGram, q);
            long sparseQpTime = System.nanoTime() - sparseQpStart;
            DenseVector init = DenseVector$.MODULE$.zeros$mDc$sp(problemSize, .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            long owlqnStart = System.nanoTime();
            FirstOrderMinimizer.State owlqnResult = owlqn.minimizeAndReturnState(new LinearGenerator.Cost(regularizedGram, q), init);
            long owlqnTime = System.nanoTime() - owlqnStart;
            scala.Predef..MODULE$.println("ElasticNet Formulation");
            scala.Predef..MODULE$.println("Linear Regression");
            double owlqnObj = QuadraticMinimizer$.MODULE$.computeObjective(regularizedGram, q, (DenseVector)owlqnResult.x()) + lambdaL1 * BoxesRunTime.unboxToDouble(((Vector)owlqnResult.x()).foldLeft$mcD$sp(BoxesRunTime.boxToDouble((double)0.0F), (JFunction2.mcDDD.sp)(agg, entry) -> agg + scala.math.package..MODULE$.abs(entry)));
            double sparseQpL1Obj = BoxesRunTime.unboxToDouble(sparseQpResult.x().foldLeft$mcD$sp(BoxesRunTime.boxToDouble((double)0.0F), (JFunction2.mcDDD.sp)(agg, entry) -> agg + scala.math.package..MODULE$.abs(entry)));
            double sparseQpObj = QuadraticMinimizer$.MODULE$.computeObjective(regularizedGram, q, sparseQpResult.x()) + lambdaL1 * sparseQpL1Obj;
            QuadraticMinimizer.Cost quadraticCostWithL2 = new QuadraticMinimizer.Cost(regularizedGram, q);
            init.$colon$eq(BoxesRunTime.boxToDouble((double)0.0F), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpSet());
            ProjectL1 projectL1Linear = new ProjectL1(sparseQpL1Obj);
            long nlSparseStart = System.nanoTime();
            FirstOrderMinimizer.State nlSparseResult = this.project(projectL1Linear, this.project$default$2(), this.project$default$3(), this.project$default$4(), this.project$default$5()).minimizeAndReturnState(quadraticCostWithL2, init);
            long nlSparseTime = System.nanoTime() - nlSparseStart;
            double nlSparseL1Obj = BoxesRunTime.unboxToDouble(((Vector)nlSparseResult.x()).foldLeft$mcD$sp(BoxesRunTime.boxToDouble((double)0.0F), (JFunction2.mcDDD.sp)(agg, entry) -> agg + scala.math.package..MODULE$.abs(entry)));
            double nlSparseObj = QuadraticMinimizer$.MODULE$.computeObjective(regularizedGram, q, (DenseVector)nlSparseResult.x()) + lambdaL1 * nlSparseL1Obj;
            init.$colon$eq(BoxesRunTime.boxToDouble((double)0.0F), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpSet());
            NonlinearMinimizer nlProx = new NonlinearMinimizer(new ProximalL1(lambdaL1), this.$lessinit$greater$default$2(), this.$lessinit$greater$default$3(), this.$lessinit$greater$default$4(), this.$lessinit$greater$default$5(), this.$lessinit$greater$default$6(), this.$lessinit$greater$default$7(), this.$lessinit$greater$default$8());
            long nlProxStart = System.nanoTime();
            NonlinearMinimizer.State nlProxResult = nlProx.minimizeAndReturnState(quadraticCostWithL2, init);
            long nlProxTime = System.nanoTime() - nlProxStart;
            double nlProxObj = QuadraticMinimizer$.MODULE$.computeObjective(regularizedGram, q, nlProxResult.z()) + lambdaL1 * BoxesRunTime.unboxToDouble(nlProxResult.z().foldLeft$mcD$sp(BoxesRunTime.boxToDouble((double)0.0F), (JFunction2.mcDDD.sp)(agg, entry) -> agg + scala.math.package..MODULE$.abs(entry)));
            scala.Predef..MODULE$.println((new StringBuilder(36)).append("owlqn ").append((double)owlqnTime / (double)1000000.0F).append(" ms iters ").append(owlqnResult.iter()).append(" sparseQp ").append((double)sparseQpTime / (double)1000000.0F).append(" ms iters ").append(sparseQpResult.iter()).toString());
            scala.Predef..MODULE$.println((new StringBuilder(23)).append("nlSparseTime ").append((double)nlSparseTime / (double)1000000.0F).append(" ms iters ").append(nlSparseResult.iter()).toString());
            scala.Predef..MODULE$.println((new StringBuilder(21)).append("nlProxTime ").append((double)nlProxTime / (double)1000000.0F).append(" ms iters ").append(nlProxResult.iter()).toString());
            scala.Predef..MODULE$.println((new StringBuilder(46)).append("owlqnObj ").append(owlqnObj).append(" sparseQpObj ").append(sparseQpObj).append(" nlSparseObj ").append(nlSparseObj).append(" nlProxObj ").append(nlProxObj).toString());
            DiffFunction logisticLoss = LogisticGenerator$.MODULE$.apply(problemSize);
            DiffFunction elasticNetLoss = DiffFunction$.MODULE$.withL2Regularization((DiffFunction)logisticLoss, lambdaL2, DenseVector$.MODULE$.space_Double());
            scala.Predef..MODULE$.println("Linear Regression with Bounds");
            init.$colon$eq(BoxesRunTime.boxToDouble((double)0.0F), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpSet());
            FirstOrderMinimizer nlBox = this.apply(problemSize, Constraint$.MODULE$.BOX(), (double)0.0F, this.apply$default$4());
            long nlBoxStart = System.nanoTime();
            FirstOrderMinimizer.State nlBoxResult = nlBox.minimizeAndReturnState(quadraticCostWithL2, init);
            long nlBoxTime = System.nanoTime() - nlBoxStart;
            double nlBoxObj = quadraticCostWithL2.calculate((DenseVector)nlBoxResult.x())._1$mcD$sp();
            QuadraticMinimizer qpBox = QuadraticMinimizer$.MODULE$.apply(problemSize, Constraint$.MODULE$.BOX(), (double)0.0F);
            long qpBoxStart = System.nanoTime();
            QuadraticMinimizer.State qpBoxResult = qpBox.minimizeAndReturnState(regularizedGram, q);
            long qpBoxTime = System.nanoTime() - qpBoxStart;
            double qpBoxObj = QuadraticMinimizer$.MODULE$.computeObjective(regularizedGram, q, qpBoxResult.x());
            scala.Predef..MODULE$.println((new StringBuilder(16)).append("qpBox ").append((double)qpBoxTime / (double)1000000.0F).append(" ms iters ").append(qpBoxResult.iter()).toString());
            scala.Predef..MODULE$.println((new StringBuilder(16)).append("nlBox ").append((double)nlBoxTime / (double)1000000.0F).append(" ms iters ").append(nlBoxResult.iter()).toString());
            scala.Predef..MODULE$.println((new StringBuilder(19)).append("qpBoxObj ").append(qpBoxObj).append(" nlBoxObj ").append(nlBoxObj).toString());
            scala.Predef..MODULE$.println("Logistic Regression with Bounds");
            init.$colon$eq(BoxesRunTime.boxToDouble((double)0.0F), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpSet());
            long nlBoxLogisticStart = System.nanoTime();
            FirstOrderMinimizer.State nlBoxLogisticResult = nlBox.minimizeAndReturnState(elasticNetLoss, init);
            long nlBoxLogisticTime = System.nanoTime() - nlBoxLogisticStart;
            double nlBoxLogisticObj = elasticNetLoss.calculate(nlBoxLogisticResult.x())._1$mcD$sp();
            scala.Predef..MODULE$.println((new StringBuilder(22)).append("Objective nl ").append(nlBoxLogisticObj).append(" time ").append((double)nlBoxLogisticTime / (double)1000000.0F).append(" ms").toString());
            scala.Predef..MODULE$.println("Linear Regression with ProbabilitySimplex");
            FirstOrderMinimizer nlSimplex = this.apply(problemSize, Constraint$.MODULE$.PROBABILITYSIMPLEX(), (double)1.0F, this.apply$default$4());
            init.$colon$eq(BoxesRunTime.boxToDouble((double)0.0F), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpSet());
            long nlSimplexStart = System.nanoTime();
            FirstOrderMinimizer.State nlSimplexResult = nlSimplex.minimizeAndReturnState(quadraticCost, init);
            long nlSimplexTime = System.nanoTime() - nlSimplexStart;
            double nlSimplexObj = quadraticCost.calculate(nlSimplexResult.x())._1$mcD$sp();
            long qpSimplexStart = System.nanoTime();
            QuadraticMinimizer.State qpSimplexResult = QuadraticMinimizer$.MODULE$.apply(problemSize, Constraint$.MODULE$.EQUALITY(), QuadraticMinimizer$.MODULE$.apply$default$3()).minimizeAndReturnState(h, q);
            long qpSimplexTime = System.nanoTime() - qpSimplexStart;
            double qpSimplexObj = quadraticCost.calculate(qpSimplexResult.x())._1$mcD$sp();
            scala.Predef..MODULE$.println((new StringBuilder(17)).append("Objective nl ").append(nlSimplexObj).append(" qp ").append(qpSimplexObj).toString());
            scala.Predef..MODULE$.println((new StringBuilder(18)).append("Constraint nl ").append(sum$.MODULE$.apply(nlSimplexResult.x(), sum$.MODULE$.reduce_Double(HasOps$.MODULE$.DV_canIterateValues()))).append(" qp ").append(sum$.MODULE$.apply(qpSimplexResult.x(), sum$.MODULE$.reduce_Double(HasOps$.MODULE$.DV_canIterateValues()))).toString());
            scala.Predef..MODULE$.println((new StringBuilder(18)).append("time nl ").append((double)nlSimplexTime / (double)1000000.0F).append(" ms qp ").append((double)qpSimplexTime / (double)1000000.0F).append(" ms").toString());
            scala.Predef..MODULE$.println("Logistic Regression with ProbabilitySimplex");
            init.$colon$eq(BoxesRunTime.boxToDouble((double)0.0F), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpSet());
            long nlLogisticSimplexStart = System.nanoTime();
            FirstOrderMinimizer.State nlLogisticSimplexResult = nlSimplex.minimizeAndReturnState(elasticNetLoss, init);
            long nlLogisticSimplexTime = System.nanoTime() - nlLogisticSimplexStart;
            double nlLogisticSimplexObj = elasticNetLoss.calculate(nlLogisticSimplexResult.x())._1$mcD$sp();
            init.$colon$eq(BoxesRunTime.boxToDouble((double)0.0F), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpSet());
            NonlinearMinimizer nlProxSimplex = new NonlinearMinimizer(new ProjectProbabilitySimplex((double)1.0F), this.$lessinit$greater$default$2(), this.$lessinit$greater$default$3(), this.$lessinit$greater$default$4(), this.$lessinit$greater$default$5(), this.$lessinit$greater$default$6(), this.$lessinit$greater$default$7(), this.$lessinit$greater$default$8());
            long nlProxLogisticSimplexStart = System.nanoTime();
            NonlinearMinimizer.State nlProxLogisticSimplexResult = nlProxSimplex.minimizeAndReturnState(elasticNetLoss, init);
            long nlProxLogisticSimplexTime = System.nanoTime() - nlProxLogisticSimplexStart;
            double nlProxLogisticSimplexObj = elasticNetLoss.calculate(nlProxLogisticSimplexResult.z())._1$mcD$sp();
            scala.Predef..MODULE$.println((new StringBuilder(19)).append("Objective nl ").append(nlLogisticSimplexObj).append(" admm ").append(nlProxLogisticSimplexObj).toString());
            scala.Predef..MODULE$.println((new StringBuilder(20)).append("Constraint nl ").append(sum$.MODULE$.apply(nlLogisticSimplexResult.x(), sum$.MODULE$.reduce_Double(HasOps$.MODULE$.DV_canIterateValues()))).append(" admm ").append(sum$.MODULE$.apply(nlProxLogisticSimplexResult.z(), sum$.MODULE$.reduce_Double(HasOps$.MODULE$.DV_canIterateValues()))).toString());
            scala.Predef..MODULE$.println((new StringBuilder(32)).append("time nlProjection ").append((double)nlLogisticSimplexTime / (double)1000000.0F).append(" ms nlProx ").append((double)nlProxLogisticSimplexTime / (double)1000000.0F).append(" ms").toString());
            scala.Predef..MODULE$.println("Logistic Regression with ProximalL1 and ProjectL1");
            init.$colon$eq(BoxesRunTime.boxToDouble((double)0.0F), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpSet());
            long owlqnLogisticStart = System.nanoTime();
            FirstOrderMinimizer.State owlqnLogisticResult = owlqn.minimizeAndReturnState(elasticNetLoss, init);
            long owlqnLogisticTime = System.nanoTime() - owlqnLogisticStart;
            double owlqnLogisticObj = elasticNetLoss.calculate(owlqnLogisticResult.x())._1$mcD$sp();
            double s = BoxesRunTime.unboxToDouble(((Vector)owlqnLogisticResult.x()).foldLeft$mcD$sp(BoxesRunTime.boxToDouble((double)0.0F), (JFunction2.mcDDD.sp)(x0$1, x1$1) -> {
               Tuple2.mcDD.sp var6 = new Tuple2.mcDD.sp(x0$1, x1$1);
               if (var6 != null) {
                  double agg = ((Tuple2)var6)._1$mcD$sp();
                  double entry = ((Tuple2)var6)._2$mcD$sp();
                  double var4 = agg + scala.math.package..MODULE$.abs(entry);
                  return var4;
               } else {
                  throw new MatchError(var6);
               }
            }));
            init.$colon$eq(BoxesRunTime.boxToDouble((double)0.0F), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpSet());
            ProximalL1 proximalL1 = (new ProximalL1(ProximalL1$.MODULE$.apply$default$1())).setLambda(lambdaL1);
            long nlLogisticProximalL1Start = System.nanoTime();
            NonlinearMinimizer.State nlLogisticProximalL1Result = (new NonlinearMinimizer(proximalL1, this.$lessinit$greater$default$2(), this.$lessinit$greater$default$3(), this.$lessinit$greater$default$4(), this.$lessinit$greater$default$5(), this.$lessinit$greater$default$6(), this.$lessinit$greater$default$7(), this.$lessinit$greater$default$8())).minimizeAndReturnState(elasticNetLoss, init);
            long nlLogisticProximalL1Time = System.nanoTime() - nlLogisticProximalL1Start;
            init.$colon$eq(BoxesRunTime.boxToDouble((double)0.0F), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpSet());
            long nlLogisticProjectL1Start = System.nanoTime();
            FirstOrderMinimizer.State nlLogisticProjectL1Result = this.apply(problemSize, Constraint$.MODULE$.SPARSE(), s, this.apply$default$4()).minimizeAndReturnState(elasticNetLoss, init);
            long nlLogisticProjectL1Time = System.nanoTime() - nlLogisticProjectL1Start;
            double proximalL1Obj = elasticNetLoss.calculate(nlLogisticProximalL1Result.z())._1$mcD$sp();
            double projectL1Obj = elasticNetLoss.calculate(nlLogisticProjectL1Result.x())._1$mcD$sp();
            scala.Predef..MODULE$.println((new StringBuilder(39)).append("Objective proximalL1 ").append(proximalL1Obj).append(" projectL1 ").append(projectL1Obj).append(" owlqn ").append(owlqnLogisticObj).toString());
            scala.Predef..MODULE$.println((new StringBuilder(43)).append("time proximalL1 ").append((double)nlLogisticProximalL1Time / (double)1000000.0F).append(" ms projectL1 ").append((double)nlLogisticProjectL1Time / (double)1000000.0F).append(" ms owlqn ").append((double)owlqnLogisticTime / (double)1000000.0F).append(" ms").toString());
         } else {
            throw new MatchError(var9);
         }
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(NonlinearMinimizer$.class);
   }

   private NonlinearMinimizer$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
