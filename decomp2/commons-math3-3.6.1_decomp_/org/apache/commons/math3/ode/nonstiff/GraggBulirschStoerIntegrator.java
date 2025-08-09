package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.ode.events.EventHandler;
import org.apache.commons.math3.ode.sampling.AbstractStepInterpolator;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.util.FastMath;

public class GraggBulirschStoerIntegrator extends AdaptiveStepsizeIntegrator {
   private static final String METHOD_NAME = "Gragg-Bulirsch-Stoer";
   private int maxOrder;
   private int[] sequence;
   private int[] costPerStep;
   private double[] costPerTimeUnit;
   private double[] optimalStep;
   private double[][] coeff;
   private boolean performTest;
   private int maxChecks;
   private int maxIter;
   private double stabilityReduction;
   private double stepControl1;
   private double stepControl2;
   private double stepControl3;
   private double stepControl4;
   private double orderControl1;
   private double orderControl2;
   private boolean useInterpolationError;
   private int mudif;

   public GraggBulirschStoerIntegrator(double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) {
      super("Gragg-Bulirsch-Stoer", minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
      this.setStabilityCheck(true, -1, -1, (double)-1.0F);
      this.setControlFactors((double)-1.0F, (double)-1.0F, (double)-1.0F, (double)-1.0F);
      this.setOrderControl(-1, (double)-1.0F, (double)-1.0F);
      this.setInterpolationControl(true, -1);
   }

   public GraggBulirschStoerIntegrator(double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) {
      super("Gragg-Bulirsch-Stoer", minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
      this.setStabilityCheck(true, -1, -1, (double)-1.0F);
      this.setControlFactors((double)-1.0F, (double)-1.0F, (double)-1.0F, (double)-1.0F);
      this.setOrderControl(-1, (double)-1.0F, (double)-1.0F);
      this.setInterpolationControl(true, -1);
   }

   public void setStabilityCheck(boolean performStabilityCheck, int maxNumIter, int maxNumChecks, double stepsizeReductionFactor) {
      this.performTest = performStabilityCheck;
      this.maxIter = maxNumIter <= 0 ? 2 : maxNumIter;
      this.maxChecks = maxNumChecks <= 0 ? 1 : maxNumChecks;
      if (!(stepsizeReductionFactor < 1.0E-4) && !(stepsizeReductionFactor > 0.9999)) {
         this.stabilityReduction = stepsizeReductionFactor;
      } else {
         this.stabilityReduction = (double)0.5F;
      }

   }

   public void setControlFactors(double control1, double control2, double control3, double control4) {
      if (!(control1 < 1.0E-4) && !(control1 > 0.9999)) {
         this.stepControl1 = control1;
      } else {
         this.stepControl1 = 0.65;
      }

      if (!(control2 < 1.0E-4) && !(control2 > 0.9999)) {
         this.stepControl2 = control2;
      } else {
         this.stepControl2 = 0.94;
      }

      if (!(control3 < 1.0E-4) && !(control3 > 0.9999)) {
         this.stepControl3 = control3;
      } else {
         this.stepControl3 = 0.02;
      }

      if (!(control4 < 1.0001) && !(control4 > 999.9)) {
         this.stepControl4 = control4;
      } else {
         this.stepControl4 = (double)4.0F;
      }

   }

   public void setOrderControl(int maximalOrder, double control1, double control2) {
      if (maximalOrder <= 6 || maximalOrder % 2 != 0) {
         this.maxOrder = 18;
      }

      if (!(control1 < 1.0E-4) && !(control1 > 0.9999)) {
         this.orderControl1 = control1;
      } else {
         this.orderControl1 = 0.8;
      }

      if (!(control2 < 1.0E-4) && !(control2 > 0.9999)) {
         this.orderControl2 = control2;
      } else {
         this.orderControl2 = 0.9;
      }

      this.initializeArrays();
   }

   public void addStepHandler(StepHandler handler) {
      super.addStepHandler(handler);
      this.initializeArrays();
   }

   public void addEventHandler(EventHandler function, double maxCheckInterval, double convergence, int maxIterationCount, UnivariateSolver solver) {
      super.addEventHandler(function, maxCheckInterval, convergence, maxIterationCount, solver);
      this.initializeArrays();
   }

   private void initializeArrays() {
      int size = this.maxOrder / 2;
      if (this.sequence == null || this.sequence.length != size) {
         this.sequence = new int[size];
         this.costPerStep = new int[size];
         this.coeff = new double[size][];
         this.costPerTimeUnit = new double[size];
         this.optimalStep = new double[size];
      }

      for(int k = 0; k < size; ++k) {
         this.sequence[k] = 4 * k + 2;
      }

      this.costPerStep[0] = this.sequence[0] + 1;

      for(int k = 1; k < size; ++k) {
         this.costPerStep[k] = this.costPerStep[k - 1] + this.sequence[k];
      }

      for(int k = 0; k < size; ++k) {
         this.coeff[k] = k > 0 ? new double[k] : null;

         for(int l = 0; l < k; ++l) {
            double ratio = (double)this.sequence[k] / (double)this.sequence[k - l - 1];
            this.coeff[k][l] = (double)1.0F / (ratio * ratio - (double)1.0F);
         }
      }

   }

   public void setInterpolationControl(boolean useInterpolationErrorForControl, int mudifControlParameter) {
      this.useInterpolationError = useInterpolationErrorForControl;
      if (mudifControlParameter > 0 && mudifControlParameter < 7) {
         this.mudif = mudifControlParameter;
      } else {
         this.mudif = 4;
      }

   }

   private void rescale(double[] y1, double[] y2, double[] scale) {
      if (this.vecAbsoluteTolerance == null) {
         for(int i = 0; i < scale.length; ++i) {
            double yi = FastMath.max(FastMath.abs(y1[i]), FastMath.abs(y2[i]));
            scale[i] = this.scalAbsoluteTolerance + this.scalRelativeTolerance * yi;
         }
      } else {
         for(int i = 0; i < scale.length; ++i) {
            double yi = FastMath.max(FastMath.abs(y1[i]), FastMath.abs(y2[i]));
            scale[i] = this.vecAbsoluteTolerance[i] + this.vecRelativeTolerance[i] * yi;
         }
      }

   }

   private boolean tryStep(double t0, double[] y0, double step, int k, double[] scale, double[][] f, double[] yMiddle, double[] yEnd, double[] yTmp) throws MaxCountExceededException, DimensionMismatchException {
      int n = this.sequence[k];
      double subStep = step / (double)n;
      double subStep2 = (double)2.0F * subStep;
      double t = t0 + subStep;

      for(int i = 0; i < y0.length; ++i) {
         yTmp[i] = y0[i];
         yEnd[i] = y0[i] + subStep * f[0][i];
      }

      this.computeDerivatives(t, yEnd, f[1]);

      for(int j = 1; j < n; ++j) {
         if (2 * j == n) {
            System.arraycopy(yEnd, 0, yMiddle, 0, y0.length);
         }

         t += subStep;

         for(int i = 0; i < y0.length; ++i) {
            double middle = yEnd[i];
            yEnd[i] = yTmp[i] + subStep2 * f[j][i];
            yTmp[i] = middle;
         }

         this.computeDerivatives(t, yEnd, f[j + 1]);
         if (this.performTest && j <= this.maxChecks && k < this.maxIter) {
            double initialNorm = (double)0.0F;

            for(int l = 0; l < scale.length; ++l) {
               double ratio = f[0][l] / scale[l];
               initialNorm += ratio * ratio;
            }

            double deltaNorm = (double)0.0F;

            for(int l = 0; l < scale.length; ++l) {
               double ratio = (f[j + 1][l] - f[0][l]) / scale[l];
               deltaNorm += ratio * ratio;
            }

            if (deltaNorm > (double)4.0F * FastMath.max(1.0E-15, initialNorm)) {
               return false;
            }
         }
      }

      for(int i = 0; i < y0.length; ++i) {
         yEnd[i] = (double)0.5F * (yTmp[i] + yEnd[i] + subStep * f[n][i]);
      }

      return true;
   }

   private void extrapolate(int offset, int k, double[][] diag, double[] last) {
      for(int j = 1; j < k; ++j) {
         for(int i = 0; i < last.length; ++i) {
            diag[k - j - 1][i] = diag[k - j][i] + this.coeff[k + offset][j - 1] * (diag[k - j][i] - diag[k - j - 1][i]);
         }
      }

      for(int i = 0; i < last.length; ++i) {
         last[i] = diag[0][i] + this.coeff[k + offset][k - 1] * (diag[0][i] - last[i]);
      }

   }

   public void integrate(ExpandableStatefulODE equations, double t) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
      this.sanityChecks(equations, t);
      this.setEquations(equations);
      boolean forward = t > equations.getTime();
      double[] y0 = equations.getCompleteState();
      double[] y = (double[])(([D)y0).clone();
      double[] yDot0 = new double[y.length];
      double[] y1 = new double[y.length];
      double[] yTmp = new double[y.length];
      double[] yTmpDot = new double[y.length];
      double[][] diagonal = new double[this.sequence.length - 1][];
      double[][] y1Diag = new double[this.sequence.length - 1][];

      for(int k = 0; k < this.sequence.length - 1; ++k) {
         diagonal[k] = new double[y.length];
         y1Diag[k] = new double[y.length];
      }

      double[][][] fk = new double[this.sequence.length][][];

      for(int k = 0; k < this.sequence.length; ++k) {
         fk[k] = new double[this.sequence[k] + 1][];
         fk[k][0] = yDot0;

         for(int l = 0; l < this.sequence[k]; ++l) {
            fk[k][l + 1] = new double[y0.length];
         }
      }

      if (y != y0) {
         System.arraycopy(y0, 0, y, 0, y0.length);
      }

      double[] yDot1 = new double[y0.length];
      double[][] yMidDots = new double[1 + 2 * this.sequence.length][y0.length];
      double[] scale = new double[this.mainSetDimension];
      this.rescale(y, y, scale);
      double tol = this.vecRelativeTolerance == null ? this.scalRelativeTolerance : this.vecRelativeTolerance[0];
      double log10R = FastMath.log10(FastMath.max(1.0E-10, tol));
      int targetIter = FastMath.max(1, FastMath.min(this.sequence.length - 2, (int)FastMath.floor((double)0.5F - 0.6 * log10R)));
      AbstractStepInterpolator interpolator = new GraggBulirschStoerStepInterpolator(y, yDot0, y1, yDot1, yMidDots, forward, equations.getPrimaryMapper(), equations.getSecondaryMappers());
      interpolator.storeTime(equations.getTime());
      this.stepStart = equations.getTime();
      double hNew = (double)0.0F;
      double maxError = Double.MAX_VALUE;
      boolean previousRejected = false;
      boolean firstTime = true;
      boolean newStep = true;
      boolean firstStepAlreadyComputed = false;
      this.initIntegration(equations.getTime(), y0, t);
      this.costPerTimeUnit[0] = (double)0.0F;
      this.isLastStep = false;

      do {
         boolean reject = false;
         if (newStep) {
            interpolator.shift();
            if (!firstStepAlreadyComputed) {
               this.computeDerivatives(this.stepStart, y, yDot0);
            }

            if (firstTime) {
               hNew = this.initializeStep(forward, 2 * targetIter + 1, scale, this.stepStart, y, yDot0, yTmp, yTmpDot);
            }

            newStep = false;
         }

         this.stepSize = hNew;
         if (forward && this.stepStart + this.stepSize > t || !forward && this.stepStart + this.stepSize < t) {
            this.stepSize = t - this.stepStart;
         }

         double nextT = this.stepStart + this.stepSize;
         this.isLastStep = forward ? nextT >= t : nextT <= t;
         int k = -1;
         boolean loop = true;

         while(loop) {
            ++k;
            if (!this.tryStep(this.stepStart, y, this.stepSize, k, scale, fk[k], k == 0 ? yMidDots[0] : diagonal[k - 1], k == 0 ? y1 : y1Diag[k - 1], yTmp)) {
               hNew = FastMath.abs(this.filterStep(this.stepSize * this.stabilityReduction, forward, false));
               reject = true;
               loop = false;
            } else if (k > 0) {
               this.extrapolate(0, k, y1Diag, y1);
               this.rescale(y, y1, scale);
               double error = (double)0.0F;

               for(int j = 0; j < this.mainSetDimension; ++j) {
                  double e = FastMath.abs(y1[j] - y1Diag[0][j]) / scale[j];
                  error += e * e;
               }

               error = FastMath.sqrt(error / (double)this.mainSetDimension);
               if (!(error > 1.0E15) && (k <= 1 || !(error > maxError))) {
                  maxError = FastMath.max((double)4.0F * error, (double)1.0F);
                  double exp = (double)1.0F / (double)(2 * k + 1);
                  double fac = this.stepControl2 / FastMath.pow(error / this.stepControl1, exp);
                  double pow = FastMath.pow(this.stepControl3, exp);
                  fac = FastMath.max(pow / this.stepControl4, FastMath.min((double)1.0F / pow, fac));
                  this.optimalStep[k] = FastMath.abs(this.filterStep(this.stepSize * fac, forward, true));
                  this.costPerTimeUnit[k] = (double)this.costPerStep[k] / this.optimalStep[k];
                  switch (k - targetIter) {
                     case -1:
                        if (targetIter > 1 && !previousRejected) {
                           if (error <= (double)1.0F) {
                              loop = false;
                           } else {
                              double ratio = (double)this.sequence[targetIter] * (double)this.sequence[targetIter + 1] / (double)(this.sequence[0] * this.sequence[0]);
                              if (error > ratio * ratio) {
                                 reject = true;
                                 loop = false;
                                 targetIter = k;
                                 if (k > 1 && this.costPerTimeUnit[k - 1] < this.orderControl1 * this.costPerTimeUnit[k]) {
                                    targetIter = k - 1;
                                 }

                                 hNew = this.optimalStep[targetIter];
                              }
                           }
                        }
                        break;
                     case 0:
                        if (error <= (double)1.0F) {
                           loop = false;
                        } else {
                           double ratio = (double)this.sequence[k + 1] / (double)this.sequence[0];
                           if (error > ratio * ratio) {
                              reject = true;
                              loop = false;
                              if (targetIter > 1 && this.costPerTimeUnit[targetIter - 1] < this.orderControl1 * this.costPerTimeUnit[targetIter]) {
                                 --targetIter;
                              }

                              hNew = this.optimalStep[targetIter];
                           }
                        }
                        break;
                     case 1:
                        if (error > (double)1.0F) {
                           reject = true;
                           if (targetIter > 1 && this.costPerTimeUnit[targetIter - 1] < this.orderControl1 * this.costPerTimeUnit[targetIter]) {
                              --targetIter;
                           }

                           hNew = this.optimalStep[targetIter];
                        }

                        loop = false;
                        break;
                     default:
                        if ((firstTime || this.isLastStep) && error <= (double)1.0F) {
                           loop = false;
                        }
                  }
               } else {
                  hNew = FastMath.abs(this.filterStep(this.stepSize * this.stabilityReduction, forward, false));
                  reject = true;
                  loop = false;
               }
            }
         }

         if (!reject) {
            this.computeDerivatives(this.stepStart + this.stepSize, y1, yDot1);
         }

         double hInt = this.getMaxStep();
         if (!reject) {
            for(int j = 1; j <= k; ++j) {
               this.extrapolate(0, j, diagonal, yMidDots[0]);
            }

            int mu = 2 * k - this.mudif + 3;

            for(int l = 0; l < mu; ++l) {
               int l2 = l / 2;
               double factor = FastMath.pow((double)0.5F * (double)this.sequence[l2], l);
               int middleIndex = fk[l2].length / 2;

               for(int i = 0; i < y0.length; ++i) {
                  yMidDots[l + 1][i] = factor * fk[l2][middleIndex + l][i];
               }

               for(int j = 1; j <= k - l2; ++j) {
                  factor = FastMath.pow((double)0.5F * (double)this.sequence[j + l2], l);
                  middleIndex = fk[l2 + j].length / 2;

                  for(int i = 0; i < y0.length; ++i) {
                     diagonal[j - 1][i] = factor * fk[l2 + j][middleIndex + l][i];
                  }

                  this.extrapolate(l2, j, diagonal, yMidDots[l + 1]);
               }

               for(int i = 0; i < y0.length; ++i) {
                  yMidDots[l + 1][i] *= this.stepSize;
               }

               for(int j = (l + 1) / 2; j <= k; ++j) {
                  for(int m = fk[j].length - 1; m >= 2 * (l + 1); --m) {
                     for(int i = 0; i < y0.length; ++i) {
                        fk[j][m][i] -= fk[j][m - 2][i];
                     }
                  }
               }
            }

            if (mu >= 0) {
               GraggBulirschStoerStepInterpolator gbsInterpolator = (GraggBulirschStoerStepInterpolator)interpolator;
               gbsInterpolator.computeCoefficients(mu, this.stepSize);
               if (this.useInterpolationError) {
                  double interpError = gbsInterpolator.estimateError(scale);
                  hInt = FastMath.abs(this.stepSize / FastMath.max(FastMath.pow(interpError, (double)1.0F / (double)(mu + 4)), 0.01));
                  if (interpError > (double)10.0F) {
                     hNew = hInt;
                     reject = true;
                  }
               }
            }
         }

         if (!reject) {
            interpolator.storeTime(this.stepStart + this.stepSize);
            this.stepStart = this.acceptStep(interpolator, y1, yDot1, t);
            interpolator.storeTime(this.stepStart);
            System.arraycopy(y1, 0, y, 0, y0.length);
            System.arraycopy(yDot1, 0, yDot0, 0, y0.length);
            firstStepAlreadyComputed = true;
            int optimalIter;
            if (k == 1) {
               optimalIter = 2;
               if (previousRejected) {
                  optimalIter = 1;
               }
            } else if (k <= targetIter) {
               optimalIter = k;
               if (this.costPerTimeUnit[k - 1] < this.orderControl1 * this.costPerTimeUnit[k]) {
                  optimalIter = k - 1;
               } else if (this.costPerTimeUnit[k] < this.orderControl2 * this.costPerTimeUnit[k - 1]) {
                  optimalIter = FastMath.min(k + 1, this.sequence.length - 2);
               }
            } else {
               optimalIter = k - 1;
               if (k > 2 && this.costPerTimeUnit[k - 2] < this.orderControl1 * this.costPerTimeUnit[k - 1]) {
                  optimalIter = k - 2;
               }

               if (this.costPerTimeUnit[k] < this.orderControl2 * this.costPerTimeUnit[optimalIter]) {
                  optimalIter = FastMath.min(k, this.sequence.length - 2);
               }
            }

            if (previousRejected) {
               targetIter = FastMath.min(optimalIter, k);
               hNew = FastMath.min(FastMath.abs(this.stepSize), this.optimalStep[targetIter]);
            } else {
               if (optimalIter <= k) {
                  hNew = this.optimalStep[optimalIter];
               } else if (k < targetIter && this.costPerTimeUnit[k] < this.orderControl2 * this.costPerTimeUnit[k - 1]) {
                  hNew = this.filterStep(this.optimalStep[k] * (double)this.costPerStep[optimalIter + 1] / (double)this.costPerStep[k], forward, false);
               } else {
                  hNew = this.filterStep(this.optimalStep[k] * (double)this.costPerStep[optimalIter] / (double)this.costPerStep[k], forward, false);
               }

               targetIter = optimalIter;
            }

            newStep = true;
         }

         hNew = FastMath.min(hNew, hInt);
         if (!forward) {
            hNew = -hNew;
         }

         firstTime = false;
         if (reject) {
            this.isLastStep = false;
            previousRejected = true;
         } else {
            previousRejected = false;
         }
      } while(!this.isLastStep);

      equations.setTime(this.stepStart);
      equations.setCompleteState(y);
      this.resetInternalState();
   }
}
