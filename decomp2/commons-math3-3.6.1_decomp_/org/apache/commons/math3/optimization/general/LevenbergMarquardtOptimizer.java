package org.apache.commons.math3.optimization.general;

import java.util.Arrays;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.PointVectorValuePair;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

/** @deprecated */
@Deprecated
public class LevenbergMarquardtOptimizer extends AbstractLeastSquaresOptimizer {
   private int solvedCols;
   private double[] diagR;
   private double[] jacNorm;
   private double[] beta;
   private int[] permutation;
   private int rank;
   private double lmPar;
   private double[] lmDir;
   private final double initialStepBoundFactor;
   private final double costRelativeTolerance;
   private final double parRelativeTolerance;
   private final double orthoTolerance;
   private final double qrRankingThreshold;
   private double[] weightedResidual;
   private double[][] weightedJacobian;

   public LevenbergMarquardtOptimizer() {
      this((double)100.0F, 1.0E-10, 1.0E-10, 1.0E-10, Precision.SAFE_MIN);
   }

   public LevenbergMarquardtOptimizer(ConvergenceChecker checker) {
      this((double)100.0F, checker, 1.0E-10, 1.0E-10, 1.0E-10, Precision.SAFE_MIN);
   }

   public LevenbergMarquardtOptimizer(double initialStepBoundFactor, ConvergenceChecker checker, double costRelativeTolerance, double parRelativeTolerance, double orthoTolerance, double threshold) {
      super(checker);
      this.initialStepBoundFactor = initialStepBoundFactor;
      this.costRelativeTolerance = costRelativeTolerance;
      this.parRelativeTolerance = parRelativeTolerance;
      this.orthoTolerance = orthoTolerance;
      this.qrRankingThreshold = threshold;
   }

   public LevenbergMarquardtOptimizer(double costRelativeTolerance, double parRelativeTolerance, double orthoTolerance) {
      this((double)100.0F, costRelativeTolerance, parRelativeTolerance, orthoTolerance, Precision.SAFE_MIN);
   }

   public LevenbergMarquardtOptimizer(double initialStepBoundFactor, double costRelativeTolerance, double parRelativeTolerance, double orthoTolerance, double threshold) {
      super((ConvergenceChecker)null);
      this.initialStepBoundFactor = initialStepBoundFactor;
      this.costRelativeTolerance = costRelativeTolerance;
      this.parRelativeTolerance = parRelativeTolerance;
      this.orthoTolerance = orthoTolerance;
      this.qrRankingThreshold = threshold;
   }

   protected PointVectorValuePair doOptimize() {
      int nR = this.getTarget().length;
      double[] currentPoint = this.getStartPoint();
      int nC = currentPoint.length;
      this.solvedCols = FastMath.min(nR, nC);
      this.diagR = new double[nC];
      this.jacNorm = new double[nC];
      this.beta = new double[nC];
      this.permutation = new int[nC];
      this.lmDir = new double[nC];
      double delta = (double)0.0F;
      double xNorm = (double)0.0F;
      double[] diag = new double[nC];
      double[] oldX = new double[nC];
      double[] oldRes = new double[nR];
      double[] oldObj = new double[nR];
      double[] qtf = new double[nR];
      double[] work1 = new double[nC];
      double[] work2 = new double[nC];
      double[] work3 = new double[nC];
      RealMatrix weightMatrixSqrt = this.getWeightSquareRoot();
      double[] currentObjective = this.computeObjectiveValue(currentPoint);
      double[] currentResiduals = this.computeResiduals(currentObjective);
      PointVectorValuePair current = new PointVectorValuePair(currentPoint, currentObjective);
      double currentCost = this.computeCost(currentResiduals);
      this.lmPar = (double)0.0F;
      boolean firstIteration = true;
      int iter = 0;
      ConvergenceChecker<PointVectorValuePair> checker = this.getConvergenceChecker();

      while(true) {
         ++iter;
         PointVectorValuePair previous = current;
         this.qrDecomposition(this.computeWeightedJacobian(currentPoint));
         this.weightedResidual = weightMatrixSqrt.operate(currentResiduals);

         for(int i = 0; i < nR; ++i) {
            qtf[i] = this.weightedResidual[i];
         }

         this.qTy(qtf);

         for(int k = 0; k < this.solvedCols; ++k) {
            int pk = this.permutation[k];
            this.weightedJacobian[k][pk] = this.diagR[pk];
         }

         if (firstIteration) {
            xNorm = (double)0.0F;

            for(int k = 0; k < nC; ++k) {
               double dk = this.jacNorm[k];
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
            for(int j = 0; j < this.solvedCols; ++j) {
               int pj = this.permutation[j];
               double s = this.jacNorm[pj];
               if (s != (double)0.0F) {
                  double sum = (double)0.0F;

                  for(int i = 0; i <= j; ++i) {
                     sum += this.weightedJacobian[i][pj] * qtf[i];
                  }

                  maxCosine = FastMath.max(maxCosine, FastMath.abs(sum) / (s * currentCost));
               }
            }
         }

         if (maxCosine <= this.orthoTolerance) {
            this.setCost(currentCost);
            this.point = current.getPoint();
            return current;
         }

         for(int j = 0; j < nC; ++j) {
            diag[j] = FastMath.max(diag[j], this.jacNorm[j]);
         }

         double ratio = (double)0.0F;

         while(ratio < 1.0E-4) {
            for(int j = 0; j < this.solvedCols; ++j) {
               int pj = this.permutation[j];
               oldX[pj] = currentPoint[pj];
            }

            double previousCost = currentCost;
            double[] tmpVec = this.weightedResidual;
            this.weightedResidual = oldRes;
            oldRes = tmpVec;
            oldObj = currentObjective;
            this.determineLMParameter(qtf, delta, diag, work1, work2, work3);
            double lmNorm = (double)0.0F;

            for(int j = 0; j < this.solvedCols; ++j) {
               int pj = this.permutation[j];
               this.lmDir[pj] = -this.lmDir[pj];
               currentPoint[pj] = oldX[pj] + this.lmDir[pj];
               double s = diag[pj] * this.lmDir[pj];
               lmNorm += s * s;
            }

            lmNorm = FastMath.sqrt(lmNorm);
            if (firstIteration) {
               delta = FastMath.min(delta, lmNorm);
            }

            currentObjective = this.computeObjectiveValue(currentPoint);
            currentResiduals = this.computeResiduals(currentObjective);
            current = new PointVectorValuePair(currentPoint, currentObjective);
            currentCost = this.computeCost(currentResiduals);
            double actRed = (double)-1.0F;
            if (0.1 * currentCost < previousCost) {
               double r = currentCost / previousCost;
               actRed = (double)1.0F - r * r;
            }

            for(int j = 0; j < this.solvedCols; ++j) {
               int pj = this.permutation[j];
               double dirJ = this.lmDir[pj];
               work1[j] = (double)0.0F;

               for(int i = 0; i <= j; ++i) {
                  work1[i] += this.weightedJacobian[i][pj] * dirJ;
               }
            }

            double coeff1 = (double)0.0F;

            for(int j = 0; j < this.solvedCols; ++j) {
               coeff1 += work1[j] * work1[j];
            }

            double pc2 = previousCost * previousCost;
            coeff1 /= pc2;
            double coeff2 = this.lmPar * lmNorm * lmNorm / pc2;
            double preRed = coeff1 + (double)2.0F * coeff2;
            double dirDer = -(coeff1 + coeff2);
            ratio = preRed == (double)0.0F ? (double)0.0F : actRed / preRed;
            if (ratio <= (double)0.25F) {
               double tmp = actRed < (double)0.0F ? (double)0.5F * dirDer / (dirDer + (double)0.5F * actRed) : (double)0.5F;
               if (0.1 * currentCost >= previousCost || tmp < 0.1) {
                  tmp = 0.1;
               }

               delta = tmp * FastMath.min(delta, (double)10.0F * lmNorm);
               this.lmPar /= tmp;
            } else if (this.lmPar == (double)0.0F || ratio >= (double)0.75F) {
               delta = (double)2.0F * lmNorm;
               this.lmPar *= (double)0.5F;
            }

            if (ratio >= 1.0E-4) {
               firstIteration = false;
               xNorm = (double)0.0F;

               for(int k = 0; k < nC; ++k) {
                  double xK = diag[k] * currentPoint[k];
                  xNorm += xK * xK;
               }

               xNorm = FastMath.sqrt(xNorm);
               if (checker != null && checker.converged(iter, previous, current)) {
                  this.setCost(currentCost);
                  this.point = current.getPoint();
                  return current;
               }
            } else {
               currentCost = previousCost;

               for(int j = 0; j < this.solvedCols; ++j) {
                  int pj = this.permutation[j];
                  currentPoint[pj] = oldX[pj];
               }

               double[] var63 = this.weightedResidual;
               this.weightedResidual = tmpVec;
               oldRes = var63;
               currentObjective = oldObj;
               current = new PointVectorValuePair(currentPoint, oldObj);
            }

            if (FastMath.abs(actRed) <= this.costRelativeTolerance && preRed <= this.costRelativeTolerance && ratio <= (double)2.0F || delta <= this.parRelativeTolerance * xNorm) {
               this.setCost(currentCost);
               this.point = current.getPoint();
               return current;
            }

            if (FastMath.abs(actRed) <= 2.2204E-16 && preRed <= 2.2204E-16 && ratio <= (double)2.0F) {
               throw new ConvergenceException(LocalizedFormats.TOO_SMALL_COST_RELATIVE_TOLERANCE, new Object[]{this.costRelativeTolerance});
            }

            if (delta <= 2.2204E-16 * xNorm) {
               throw new ConvergenceException(LocalizedFormats.TOO_SMALL_PARAMETERS_RELATIVE_TOLERANCE, new Object[]{this.parRelativeTolerance});
            }

            if (maxCosine <= 2.2204E-16) {
               throw new ConvergenceException(LocalizedFormats.TOO_SMALL_ORTHOGONALITY_TOLERANCE, new Object[]{this.orthoTolerance});
            }
         }
      }
   }

   private void determineLMParameter(double[] qy, double delta, double[] diag, double[] work1, double[] work2, double[] work3) {
      int nC = this.weightedJacobian[0].length;

      for(int j = 0; j < this.rank; ++j) {
         this.lmDir[this.permutation[j]] = qy[j];
      }

      for(int j = this.rank; j < nC; ++j) {
         this.lmDir[this.permutation[j]] = (double)0.0F;
      }

      for(int k = this.rank - 1; k >= 0; --k) {
         int pk = this.permutation[k];
         double ypk = this.lmDir[pk] / this.diagR[pk];

         for(int i = 0; i < k; ++i) {
            double[] var10000 = this.lmDir;
            int var10001 = this.permutation[i];
            var10000[var10001] -= ypk * this.weightedJacobian[i][pk];
         }

         this.lmDir[pk] = ypk;
      }

      double dxNorm = (double)0.0F;

      for(int j = 0; j < this.solvedCols; ++j) {
         int pj = this.permutation[j];
         double s = diag[pj] * this.lmDir[pj];
         work1[pj] = s;
         dxNorm += s * s;
      }

      dxNorm = FastMath.sqrt(dxNorm);
      double fp = dxNorm - delta;
      if (fp <= 0.1 * delta) {
         this.lmPar = (double)0.0F;
      } else {
         double parl = (double)0.0F;
         if (this.rank == this.solvedCols) {
            for(int j = 0; j < this.solvedCols; ++j) {
               int pj = this.permutation[j];
               work1[pj] *= diag[pj] / dxNorm;
            }

            double sum2 = (double)0.0F;

            for(int j = 0; j < this.solvedCols; ++j) {
               int pj = this.permutation[j];
               double sum = (double)0.0F;

               for(int i = 0; i < j; ++i) {
                  sum += this.weightedJacobian[i][pj] * work1[this.permutation[i]];
               }

               double s = (work1[pj] - sum) / this.diagR[pj];
               work1[pj] = s;
               sum2 += s * s;
            }

            parl = fp / (delta * sum2);
         }

         double sum2 = (double)0.0F;

         for(int j = 0; j < this.solvedCols; ++j) {
            int pj = this.permutation[j];
            double sum = (double)0.0F;

            for(int i = 0; i <= j; ++i) {
               sum += this.weightedJacobian[i][pj] * qy[i];
            }

            sum /= diag[pj];
            sum2 += sum * sum;
         }

         double gNorm = FastMath.sqrt(sum2);
         double paru = gNorm / delta;
         if (paru == (double)0.0F) {
            paru = 2.2251E-308 / FastMath.min(delta, 0.1);
         }

         this.lmPar = FastMath.min(paru, FastMath.max(this.lmPar, parl));
         if (this.lmPar == (double)0.0F) {
            this.lmPar = gNorm / dxNorm;
         }

         for(int countdown = 10; countdown >= 0; --countdown) {
            if (this.lmPar == (double)0.0F) {
               this.lmPar = FastMath.max(2.2251E-308, 0.001 * paru);
            }

            double sPar = FastMath.sqrt(this.lmPar);

            for(int j = 0; j < this.solvedCols; ++j) {
               int pj = this.permutation[j];
               work1[pj] = sPar * diag[pj];
            }

            this.determineLMDirection(qy, work1, work2, work3);
            dxNorm = (double)0.0F;

            for(int j = 0; j < this.solvedCols; ++j) {
               int pj = this.permutation[j];
               double s = diag[pj] * this.lmDir[pj];
               work3[pj] = s;
               dxNorm += s * s;
            }

            dxNorm = FastMath.sqrt(dxNorm);
            double previousFP = fp;
            fp = dxNorm - delta;
            if (FastMath.abs(fp) <= 0.1 * delta || parl == (double)0.0F && fp <= previousFP && previousFP < (double)0.0F) {
               return;
            }

            for(int j = 0; j < this.solvedCols; ++j) {
               int pj = this.permutation[j];
               work1[pj] = work3[pj] * diag[pj] / dxNorm;
            }

            for(int j = 0; j < this.solvedCols; ++j) {
               int pj = this.permutation[j];
               work1[pj] /= work2[j];
               double tmp = work1[pj];

               for(int i = j + 1; i < this.solvedCols; ++i) {
                  int var63 = this.permutation[i];
                  work1[var63] -= this.weightedJacobian[i][pj] * tmp;
               }
            }

            sum2 = (double)0.0F;

            for(int j = 0; j < this.solvedCols; ++j) {
               double s = work1[this.permutation[j]];
               sum2 += s * s;
            }

            double correction = fp / (delta * sum2);
            if (fp > (double)0.0F) {
               parl = FastMath.max(parl, this.lmPar);
            } else if (fp < (double)0.0F) {
               paru = FastMath.min(paru, this.lmPar);
            }

            this.lmPar = FastMath.max(parl, this.lmPar + correction);
         }

      }
   }

   private void determineLMDirection(double[] qy, double[] diag, double[] lmDiag, double[] work) {
      for(int j = 0; j < this.solvedCols; ++j) {
         int pj = this.permutation[j];

         for(int i = j + 1; i < this.solvedCols; ++i) {
            this.weightedJacobian[i][pj] = this.weightedJacobian[j][this.permutation[i]];
         }

         this.lmDir[j] = this.diagR[pj];
         work[j] = qy[j];
      }

      for(int j = 0; j < this.solvedCols; ++j) {
         int pj = this.permutation[j];
         double dpj = diag[pj];
         if (dpj != (double)0.0F) {
            Arrays.fill(lmDiag, j + 1, lmDiag.length, (double)0.0F);
         }

         lmDiag[j] = dpj;
         double qtbpj = (double)0.0F;

         for(int k = j; k < this.solvedCols; ++k) {
            int pk = this.permutation[k];
            if (lmDiag[k] != (double)0.0F) {
               double rkk = this.weightedJacobian[k][pk];
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

               this.weightedJacobian[k][pk] = cos * rkk + sin * lmDiag[k];
               double temp = cos * work[k] + sin * qtbpj;
               qtbpj = -sin * work[k] + cos * qtbpj;
               work[k] = temp;

               for(int i = k + 1; i < this.solvedCols; ++i) {
                  double rik = this.weightedJacobian[i][pk];
                  double temp2 = cos * rik + sin * lmDiag[i];
                  lmDiag[i] = -sin * rik + cos * lmDiag[i];
                  this.weightedJacobian[i][pk] = temp2;
               }
            }
         }

         lmDiag[j] = this.weightedJacobian[j][this.permutation[j]];
         this.weightedJacobian[j][this.permutation[j]] = this.lmDir[j];
      }

      int nSing = this.solvedCols;

      for(int j = 0; j < this.solvedCols; ++j) {
         if (lmDiag[j] == (double)0.0F && nSing == this.solvedCols) {
            nSing = j;
         }

         if (nSing < this.solvedCols) {
            work[j] = (double)0.0F;
         }
      }

      if (nSing > 0) {
         for(int j = nSing - 1; j >= 0; --j) {
            int pj = this.permutation[j];
            double sum = (double)0.0F;

            for(int i = j + 1; i < nSing; ++i) {
               sum += this.weightedJacobian[i][pj] * work[i];
            }

            work[j] = (work[j] - sum) / lmDiag[j];
         }
      }

      for(int j = 0; j < this.lmDir.length; ++j) {
         this.lmDir[this.permutation[j]] = work[j];
      }

   }

   private void qrDecomposition(RealMatrix jacobian) throws ConvergenceException {
      this.weightedJacobian = jacobian.scalarMultiply((double)-1.0F).getData();
      int nR = this.weightedJacobian.length;
      int nC = this.weightedJacobian[0].length;

      for(int k = 0; k < nC; ++k) {
         this.permutation[k] = k;
         double norm2 = (double)0.0F;

         for(int i = 0; i < nR; ++i) {
            double akk = this.weightedJacobian[i][k];
            norm2 += akk * akk;
         }

         this.jacNorm[k] = FastMath.sqrt(norm2);
      }

      for(int k = 0; k < nC; ++k) {
         int nextColumn = -1;
         double ak2 = Double.NEGATIVE_INFINITY;

         for(int i = k; i < nC; ++i) {
            double norm2 = (double)0.0F;

            for(int j = k; j < nR; ++j) {
               double aki = this.weightedJacobian[j][this.permutation[i]];
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
            this.rank = k;
            return;
         }

         int pk = this.permutation[nextColumn];
         this.permutation[nextColumn] = this.permutation[k];
         this.permutation[k] = pk;
         double akk = this.weightedJacobian[k][pk];
         double alpha = akk > (double)0.0F ? -FastMath.sqrt(ak2) : FastMath.sqrt(ak2);
         double betak = (double)1.0F / (ak2 - akk * alpha);
         this.beta[pk] = betak;
         this.diagR[pk] = alpha;
         double[] var10000 = this.weightedJacobian[k];
         var10000[pk] -= alpha;

         for(int dk = nC - 1 - k; dk > 0; --dk) {
            double gamma = (double)0.0F;

            for(int j = k; j < nR; ++j) {
               gamma += this.weightedJacobian[j][pk] * this.weightedJacobian[j][this.permutation[k + dk]];
            }

            gamma *= betak;

            for(int j = k; j < nR; ++j) {
               var10000 = this.weightedJacobian[j];
               int var10001 = this.permutation[k + dk];
               var10000[var10001] -= gamma * this.weightedJacobian[j][pk];
            }
         }
      }

      this.rank = this.solvedCols;
   }

   private void qTy(double[] y) {
      int nR = this.weightedJacobian.length;
      int nC = this.weightedJacobian[0].length;

      for(int k = 0; k < nC; ++k) {
         int pk = this.permutation[k];
         double gamma = (double)0.0F;

         for(int i = k; i < nR; ++i) {
            gamma += this.weightedJacobian[i][pk] * y[i];
         }

         gamma *= this.beta[pk];

         for(int i = k; i < nR; ++i) {
            y[i] -= gamma * this.weightedJacobian[i][pk];
         }
      }

   }
}
