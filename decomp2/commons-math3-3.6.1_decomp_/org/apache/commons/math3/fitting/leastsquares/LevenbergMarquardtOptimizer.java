package org.apache.commons.math3.fitting.leastsquares;

import java.util.Arrays;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Incrementor;
import org.apache.commons.math3.util.Precision;

public class LevenbergMarquardtOptimizer implements LeastSquaresOptimizer {
   private static final double TWO_EPS;
   private final double initialStepBoundFactor;
   private final double costRelativeTolerance;
   private final double parRelativeTolerance;
   private final double orthoTolerance;
   private final double qrRankingThreshold;

   public LevenbergMarquardtOptimizer() {
      this((double)100.0F, 1.0E-10, 1.0E-10, 1.0E-10, Precision.SAFE_MIN);
   }

   public LevenbergMarquardtOptimizer(double initialStepBoundFactor, double costRelativeTolerance, double parRelativeTolerance, double orthoTolerance, double qrRankingThreshold) {
      this.initialStepBoundFactor = initialStepBoundFactor;
      this.costRelativeTolerance = costRelativeTolerance;
      this.parRelativeTolerance = parRelativeTolerance;
      this.orthoTolerance = orthoTolerance;
      this.qrRankingThreshold = qrRankingThreshold;
   }

   public LevenbergMarquardtOptimizer withInitialStepBoundFactor(double newInitialStepBoundFactor) {
      return new LevenbergMarquardtOptimizer(newInitialStepBoundFactor, this.costRelativeTolerance, this.parRelativeTolerance, this.orthoTolerance, this.qrRankingThreshold);
   }

   public LevenbergMarquardtOptimizer withCostRelativeTolerance(double newCostRelativeTolerance) {
      return new LevenbergMarquardtOptimizer(this.initialStepBoundFactor, newCostRelativeTolerance, this.parRelativeTolerance, this.orthoTolerance, this.qrRankingThreshold);
   }

   public LevenbergMarquardtOptimizer withParameterRelativeTolerance(double newParRelativeTolerance) {
      return new LevenbergMarquardtOptimizer(this.initialStepBoundFactor, this.costRelativeTolerance, newParRelativeTolerance, this.orthoTolerance, this.qrRankingThreshold);
   }

   public LevenbergMarquardtOptimizer withOrthoTolerance(double newOrthoTolerance) {
      return new LevenbergMarquardtOptimizer(this.initialStepBoundFactor, this.costRelativeTolerance, this.parRelativeTolerance, newOrthoTolerance, this.qrRankingThreshold);
   }

   public LevenbergMarquardtOptimizer withRankingThreshold(double newQRRankingThreshold) {
      return new LevenbergMarquardtOptimizer(this.initialStepBoundFactor, this.costRelativeTolerance, this.parRelativeTolerance, this.orthoTolerance, newQRRankingThreshold);
   }

   public double getInitialStepBoundFactor() {
      return this.initialStepBoundFactor;
   }

   public double getCostRelativeTolerance() {
      return this.costRelativeTolerance;
   }

   public double getParameterRelativeTolerance() {
      return this.parRelativeTolerance;
   }

   public double getOrthoTolerance() {
      return this.orthoTolerance;
   }

   public double getRankingThreshold() {
      return this.qrRankingThreshold;
   }

   public LeastSquaresOptimizer.Optimum optimize(LeastSquaresProblem problem) {
      int nR = problem.getObservationSize();
      int nC = problem.getParameterSize();
      Incrementor iterationCounter = problem.getIterationCounter();
      Incrementor evaluationCounter = problem.getEvaluationCounter();
      ConvergenceChecker<LeastSquaresProblem.Evaluation> checker = problem.getConvergenceChecker();
      int solvedCols = FastMath.min(nR, nC);
      double[] lmDir = new double[nC];
      double lmPar = (double)0.0F;
      double delta = (double)0.0F;
      double xNorm = (double)0.0F;
      double[] diag = new double[nC];
      double[] oldX = new double[nC];
      double[] oldRes = new double[nR];
      double[] qtf = new double[nR];
      double[] work1 = new double[nC];
      double[] work2 = new double[nC];
      double[] work3 = new double[nC];
      evaluationCounter.incrementCount();
      LeastSquaresProblem.Evaluation current = problem.evaluate(problem.getStart());
      double[] currentResiduals = current.getResiduals().toArray();
      double currentCost = current.getCost();
      double[] currentPoint = current.getPoint().toArray();
      boolean firstIteration = true;

      while(true) {
         iterationCounter.incrementCount();
         LeastSquaresProblem.Evaluation previous = current;
         InternalData internalData = this.qrDecomposition(current.getJacobian(), solvedCols);
         double[][] weightedJacobian = internalData.weightedJacobian;
         int[] permutation = internalData.permutation;
         double[] diagR = internalData.diagR;
         double[] jacNorm = internalData.jacNorm;
         double[] weightedResidual = currentResiduals;

         for(int i = 0; i < nR; ++i) {
            qtf[i] = weightedResidual[i];
         }

         this.qTy(qtf, internalData);

         for(int k = 0; k < solvedCols; ++k) {
            int pk = permutation[k];
            weightedJacobian[k][pk] = diagR[pk];
         }

         if (firstIteration) {
            xNorm = (double)0.0F;

            for(int k = 0; k < nC; ++k) {
               double dk = jacNorm[k];
               if (dk == (double)0.0F) {
                  dk = (double)1.0F;
               }

               double xk = dk * currentPoint[k];
               xNorm += xk * xk;
               diag[k] = dk;
            }

            xNorm = FastMath.sqrt(xNorm);
            delta = xNorm == (double)0.0F ? this.initialStepBoundFactor : this.initialStepBoundFactor * xNorm;
         }

         double maxCosine = (double)0.0F;
         if (currentCost != (double)0.0F) {
            for(int j = 0; j < solvedCols; ++j) {
               int pj = permutation[j];
               double s = jacNorm[pj];
               if (s != (double)0.0F) {
                  double sum = (double)0.0F;

                  for(int i = 0; i <= j; ++i) {
                     sum += weightedJacobian[i][pj] * qtf[i];
                  }

                  maxCosine = FastMath.max(maxCosine, FastMath.abs(sum) / (s * currentCost));
               }
            }
         }

         if (maxCosine <= this.orthoTolerance) {
            return new OptimumImpl(current, evaluationCounter.getCount(), iterationCounter.getCount());
         }

         for(int j = 0; j < nC; ++j) {
            diag[j] = FastMath.max(diag[j], jacNorm[j]);
         }

         double ratio = (double)0.0F;

         while(ratio < 1.0E-4) {
            for(int j = 0; j < solvedCols; ++j) {
               int pj = permutation[j];
               oldX[pj] = currentPoint[pj];
            }

            double previousCost = currentCost;
            double[] tmpVec = weightedResidual;
            weightedResidual = oldRes;
            oldRes = tmpVec;
            lmPar = this.determineLMParameter(qtf, delta, diag, internalData, solvedCols, work1, work2, work3, lmDir, lmPar);
            double lmNorm = (double)0.0F;

            for(int j = 0; j < solvedCols; ++j) {
               int pj = permutation[j];
               lmDir[pj] = -lmDir[pj];
               currentPoint[pj] = oldX[pj] + lmDir[pj];
               double s = diag[pj] * lmDir[pj];
               lmNorm += s * s;
            }

            lmNorm = FastMath.sqrt(lmNorm);
            if (firstIteration) {
               delta = FastMath.min(delta, lmNorm);
            }

            evaluationCounter.incrementCount();
            current = problem.evaluate(new ArrayRealVector(currentPoint));
            currentResiduals = current.getResiduals().toArray();
            currentCost = current.getCost();
            currentPoint = current.getPoint().toArray();
            double actRed = (double)-1.0F;
            if (0.1 * currentCost < previousCost) {
               double r = currentCost / previousCost;
               actRed = (double)1.0F - r * r;
            }

            for(int j = 0; j < solvedCols; ++j) {
               int pj = permutation[j];
               double dirJ = lmDir[pj];
               work1[j] = (double)0.0F;

               for(int i = 0; i <= j; ++i) {
                  work1[i] += weightedJacobian[i][pj] * dirJ;
               }
            }

            double coeff1 = (double)0.0F;

            for(int j = 0; j < solvedCols; ++j) {
               coeff1 += work1[j] * work1[j];
            }

            double pc2 = previousCost * previousCost;
            coeff1 /= pc2;
            double coeff2 = lmPar * lmNorm * lmNorm / pc2;
            double preRed = coeff1 + (double)2.0F * coeff2;
            double dirDer = -(coeff1 + coeff2);
            ratio = preRed == (double)0.0F ? (double)0.0F : actRed / preRed;
            if (ratio <= (double)0.25F) {
               double tmp = actRed < (double)0.0F ? (double)0.5F * dirDer / (dirDer + (double)0.5F * actRed) : (double)0.5F;
               if (0.1 * currentCost >= previousCost || tmp < 0.1) {
                  tmp = 0.1;
               }

               delta = tmp * FastMath.min(delta, (double)10.0F * lmNorm);
               lmPar /= tmp;
            } else if (lmPar == (double)0.0F || ratio >= (double)0.75F) {
               delta = (double)2.0F * lmNorm;
               lmPar *= (double)0.5F;
            }

            if (ratio >= 1.0E-4) {
               firstIteration = false;
               xNorm = (double)0.0F;

               for(int k = 0; k < nC; ++k) {
                  double xK = diag[k] * currentPoint[k];
                  xNorm += xK * xK;
               }

               xNorm = FastMath.sqrt(xNorm);
               if (checker != null && checker.converged(iterationCounter.getCount(), previous, current)) {
                  return new OptimumImpl(current, evaluationCounter.getCount(), iterationCounter.getCount());
               }
            } else {
               currentCost = previousCost;

               for(int j = 0; j < solvedCols; ++j) {
                  int pj = permutation[j];
                  currentPoint[pj] = oldX[pj];
               }

               double[] var71 = weightedResidual;
               weightedResidual = tmpVec;
               oldRes = var71;
               current = previous;
            }

            if (FastMath.abs(actRed) <= this.costRelativeTolerance && preRed <= this.costRelativeTolerance && ratio <= (double)2.0F || delta <= this.parRelativeTolerance * xNorm) {
               return new OptimumImpl(current, evaluationCounter.getCount(), iterationCounter.getCount());
            }

            if (FastMath.abs(actRed) <= TWO_EPS && preRed <= TWO_EPS && ratio <= (double)2.0F) {
               throw new ConvergenceException(LocalizedFormats.TOO_SMALL_COST_RELATIVE_TOLERANCE, new Object[]{this.costRelativeTolerance});
            }

            if (delta <= TWO_EPS * xNorm) {
               throw new ConvergenceException(LocalizedFormats.TOO_SMALL_PARAMETERS_RELATIVE_TOLERANCE, new Object[]{this.parRelativeTolerance});
            }

            if (maxCosine <= TWO_EPS) {
               throw new ConvergenceException(LocalizedFormats.TOO_SMALL_ORTHOGONALITY_TOLERANCE, new Object[]{this.orthoTolerance});
            }
         }
      }
   }

   private double determineLMParameter(double[] qy, double delta, double[] diag, InternalData internalData, int solvedCols, double[] work1, double[] work2, double[] work3, double[] lmDir, double lmPar) {
      double[][] weightedJacobian = internalData.weightedJacobian;
      int[] permutation = internalData.permutation;
      int rank = internalData.rank;
      double[] diagR = internalData.diagR;
      int nC = weightedJacobian[0].length;

      for(int j = 0; j < rank; ++j) {
         lmDir[permutation[j]] = qy[j];
      }

      for(int j = rank; j < nC; ++j) {
         lmDir[permutation[j]] = (double)0.0F;
      }

      for(int k = rank - 1; k >= 0; --k) {
         int pk = permutation[k];
         double ypk = lmDir[pk] / diagR[pk];

         for(int i = 0; i < k; ++i) {
            lmDir[permutation[i]] -= ypk * weightedJacobian[i][pk];
         }

         lmDir[pk] = ypk;
      }

      double dxNorm = (double)0.0F;

      for(int j = 0; j < solvedCols; ++j) {
         int pj = permutation[j];
         double s = diag[pj] * lmDir[pj];
         work1[pj] = s;
         dxNorm += s * s;
      }

      dxNorm = FastMath.sqrt(dxNorm);
      double fp = dxNorm - delta;
      if (fp <= 0.1 * delta) {
         lmPar = (double)0.0F;
         return lmPar;
      } else {
         double parl = (double)0.0F;
         if (rank == solvedCols) {
            for(int j = 0; j < solvedCols; ++j) {
               int pj = permutation[j];
               work1[pj] *= diag[pj] / dxNorm;
            }

            double sum2 = (double)0.0F;

            for(int j = 0; j < solvedCols; ++j) {
               int pj = permutation[j];
               double sum = (double)0.0F;

               for(int i = 0; i < j; ++i) {
                  sum += weightedJacobian[i][pj] * work1[permutation[i]];
               }

               double s = (work1[pj] - sum) / diagR[pj];
               work1[pj] = s;
               sum2 += s * s;
            }

            parl = fp / (delta * sum2);
         }

         double sum2 = (double)0.0F;

         for(int j = 0; j < solvedCols; ++j) {
            int pj = permutation[j];
            double sum = (double)0.0F;

            for(int i = 0; i <= j; ++i) {
               sum += weightedJacobian[i][pj] * qy[i];
            }

            sum /= diag[pj];
            sum2 += sum * sum;
         }

         double gNorm = FastMath.sqrt(sum2);
         double paru = gNorm / delta;
         if (paru == (double)0.0F) {
            paru = Precision.SAFE_MIN / FastMath.min(delta, 0.1);
         }

         lmPar = FastMath.min(paru, FastMath.max(lmPar, parl));
         if (lmPar == (double)0.0F) {
            lmPar = gNorm / dxNorm;
         }

         for(int countdown = 10; countdown >= 0; --countdown) {
            if (lmPar == (double)0.0F) {
               lmPar = FastMath.max(Precision.SAFE_MIN, 0.001 * paru);
            }

            double sPar = FastMath.sqrt(lmPar);

            for(int j = 0; j < solvedCols; ++j) {
               int pj = permutation[j];
               work1[pj] = sPar * diag[pj];
            }

            this.determineLMDirection(qy, work1, work2, internalData, solvedCols, work3, lmDir);
            dxNorm = (double)0.0F;

            for(int j = 0; j < solvedCols; ++j) {
               int pj = permutation[j];
               double s = diag[pj] * lmDir[pj];
               work3[pj] = s;
               dxNorm += s * s;
            }

            dxNorm = FastMath.sqrt(dxNorm);
            double previousFP = fp;
            fp = dxNorm - delta;
            if (FastMath.abs(fp) <= 0.1 * delta || parl == (double)0.0F && fp <= previousFP && previousFP < (double)0.0F) {
               return lmPar;
            }

            for(int j = 0; j < solvedCols; ++j) {
               int pj = permutation[j];
               work1[pj] = work3[pj] * diag[pj] / dxNorm;
            }

            for(int j = 0; j < solvedCols; ++j) {
               int pj = permutation[j];
               work1[pj] /= work2[j];
               double tmp = work1[pj];

               for(int i = j + 1; i < solvedCols; ++i) {
                  work1[permutation[i]] -= weightedJacobian[i][pj] * tmp;
               }
            }

            sum2 = (double)0.0F;

            for(int j = 0; j < solvedCols; ++j) {
               double s = work1[permutation[j]];
               sum2 += s * s;
            }

            double correction = fp / (delta * sum2);
            if (fp > (double)0.0F) {
               parl = FastMath.max(parl, lmPar);
            } else if (fp < (double)0.0F) {
               paru = FastMath.min(paru, lmPar);
            }

            lmPar = FastMath.max(parl, lmPar + correction);
         }

         return lmPar;
      }
   }

   private void determineLMDirection(double[] qy, double[] diag, double[] lmDiag, InternalData internalData, int solvedCols, double[] work, double[] lmDir) {
      int[] permutation = internalData.permutation;
      double[][] weightedJacobian = internalData.weightedJacobian;
      double[] diagR = internalData.diagR;

      for(int j = 0; j < solvedCols; ++j) {
         int pj = permutation[j];

         for(int i = j + 1; i < solvedCols; ++i) {
            weightedJacobian[i][pj] = weightedJacobian[j][permutation[i]];
         }

         lmDir[j] = diagR[pj];
         work[j] = qy[j];
      }

      for(int j = 0; j < solvedCols; ++j) {
         int pj = permutation[j];
         double dpj = diag[pj];
         if (dpj != (double)0.0F) {
            Arrays.fill(lmDiag, j + 1, lmDiag.length, (double)0.0F);
         }

         lmDiag[j] = dpj;
         double qtbpj = (double)0.0F;

         for(int k = j; k < solvedCols; ++k) {
            int pk = permutation[k];
            if (lmDiag[k] != (double)0.0F) {
               double rkk = weightedJacobian[k][pk];
               double sin;
               double cos;
               if (FastMath.abs(rkk) < FastMath.abs(lmDiag[k])) {
                  double cotan = rkk / lmDiag[k];
                  sin = (double)1.0F / FastMath.sqrt((double)1.0F + cotan * cotan);
                  cos = sin * cotan;
               } else {
                  double tan = lmDiag[k] / rkk;
                  cos = (double)1.0F / FastMath.sqrt((double)1.0F + tan * tan);
                  sin = cos * tan;
               }

               weightedJacobian[k][pk] = cos * rkk + sin * lmDiag[k];
               double temp = cos * work[k] + sin * qtbpj;
               qtbpj = -sin * work[k] + cos * qtbpj;
               work[k] = temp;

               for(int i = k + 1; i < solvedCols; ++i) {
                  double rik = weightedJacobian[i][pk];
                  double temp2 = cos * rik + sin * lmDiag[i];
                  lmDiag[i] = -sin * rik + cos * lmDiag[i];
                  weightedJacobian[i][pk] = temp2;
               }
            }
         }

         lmDiag[j] = weightedJacobian[j][permutation[j]];
         weightedJacobian[j][permutation[j]] = lmDir[j];
      }

      int nSing = solvedCols;

      for(int j = 0; j < solvedCols; ++j) {
         if (lmDiag[j] == (double)0.0F && nSing == solvedCols) {
            nSing = j;
         }

         if (nSing < solvedCols) {
            work[j] = (double)0.0F;
         }
      }

      if (nSing > 0) {
         for(int j = nSing - 1; j >= 0; --j) {
            int pj = permutation[j];
            double sum = (double)0.0F;

            for(int i = j + 1; i < nSing; ++i) {
               sum += weightedJacobian[i][pj] * work[i];
            }

            work[j] = (work[j] - sum) / lmDiag[j];
         }
      }

      for(int j = 0; j < lmDir.length; ++j) {
         lmDir[permutation[j]] = work[j];
      }

   }

   private InternalData qrDecomposition(RealMatrix jacobian, int solvedCols) throws ConvergenceException {
      double[][] weightedJacobian = jacobian.scalarMultiply((double)-1.0F).getData();
      int nR = weightedJacobian.length;
      int nC = weightedJacobian[0].length;
      int[] permutation = new int[nC];
      double[] diagR = new double[nC];
      double[] jacNorm = new double[nC];
      double[] beta = new double[nC];

      for(int k = 0; k < nC; ++k) {
         permutation[k] = k;
         double norm2 = (double)0.0F;

         for(int i = 0; i < nR; ++i) {
            double akk = weightedJacobian[i][k];
            norm2 += akk * akk;
         }

         jacNorm[k] = FastMath.sqrt(norm2);
      }

      for(int k = 0; k < nC; ++k) {
         int nextColumn = -1;
         double ak2 = Double.NEGATIVE_INFINITY;

         for(int i = k; i < nC; ++i) {
            double norm2 = (double)0.0F;

            for(int j = k; j < nR; ++j) {
               double aki = weightedJacobian[j][permutation[i]];
               norm2 += aki * aki;
            }

            if (Double.isInfinite(norm2) || Double.isNaN(norm2)) {
               throw new ConvergenceException(LocalizedFormats.UNABLE_TO_PERFORM_QR_DECOMPOSITION_ON_JACOBIAN, new Object[]{nR, nC});
            }

            if (norm2 > ak2) {
               nextColumn = i;
               ak2 = norm2;
            }
         }

         if (ak2 <= this.qrRankingThreshold) {
            return new InternalData(weightedJacobian, permutation, k, diagR, jacNorm, beta);
         }

         int pk = permutation[nextColumn];
         permutation[nextColumn] = permutation[k];
         permutation[k] = pk;
         double akk = weightedJacobian[k][pk];
         double alpha = akk > (double)0.0F ? -FastMath.sqrt(ak2) : FastMath.sqrt(ak2);
         double betak = (double)1.0F / (ak2 - akk * alpha);
         beta[pk] = betak;
         diagR[pk] = alpha;
         weightedJacobian[k][pk] -= alpha;

         for(int dk = nC - 1 - k; dk > 0; --dk) {
            double gamma = (double)0.0F;

            for(int j = k; j < nR; ++j) {
               gamma += weightedJacobian[j][pk] * weightedJacobian[j][permutation[k + dk]];
            }

            gamma *= betak;

            for(int j = k; j < nR; ++j) {
               weightedJacobian[j][permutation[k + dk]] -= gamma * weightedJacobian[j][pk];
            }
         }
      }

      return new InternalData(weightedJacobian, permutation, solvedCols, diagR, jacNorm, beta);
   }

   private void qTy(double[] y, InternalData internalData) {
      double[][] weightedJacobian = internalData.weightedJacobian;
      int[] permutation = internalData.permutation;
      double[] beta = internalData.beta;
      int nR = weightedJacobian.length;
      int nC = weightedJacobian[0].length;

      for(int k = 0; k < nC; ++k) {
         int pk = permutation[k];
         double gamma = (double)0.0F;

         for(int i = k; i < nR; ++i) {
            gamma += weightedJacobian[i][pk] * y[i];
         }

         gamma *= beta[pk];

         for(int i = k; i < nR; ++i) {
            y[i] -= gamma * weightedJacobian[i][pk];
         }
      }

   }

   static {
      TWO_EPS = (double)2.0F * Precision.EPSILON;
   }

   private static class InternalData {
      private final double[][] weightedJacobian;
      private final int[] permutation;
      private final int rank;
      private final double[] diagR;
      private final double[] jacNorm;
      private final double[] beta;

      InternalData(double[][] weightedJacobian, int[] permutation, int rank, double[] diagR, double[] jacNorm, double[] beta) {
         this.weightedJacobian = weightedJacobian;
         this.permutation = permutation;
         this.rank = rank;
         this.diagR = diagR;
         this.jacNorm = jacNorm;
         this.beta = beta;
      }
   }
}
