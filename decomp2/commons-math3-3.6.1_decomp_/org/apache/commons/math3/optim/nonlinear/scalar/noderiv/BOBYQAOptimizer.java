package org.apache.commons.math3.optim.nonlinear.scalar.noderiv;

import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer;
import org.apache.commons.math3.util.FastMath;

public class BOBYQAOptimizer extends MultivariateOptimizer {
   public static final int MINIMUM_PROBLEM_DIMENSION = 2;
   public static final double DEFAULT_INITIAL_RADIUS = (double)10.0F;
   public static final double DEFAULT_STOPPING_RADIUS = 1.0E-8;
   private static final double ZERO = (double)0.0F;
   private static final double ONE = (double)1.0F;
   private static final double TWO = (double)2.0F;
   private static final double TEN = (double)10.0F;
   private static final double SIXTEEN = (double)16.0F;
   private static final double TWO_HUNDRED_FIFTY = (double)250.0F;
   private static final double MINUS_ONE = (double)-1.0F;
   private static final double HALF = (double)0.5F;
   private static final double ONE_OVER_FOUR = (double)0.25F;
   private static final double ONE_OVER_EIGHT = (double)0.125F;
   private static final double ONE_OVER_TEN = 0.1;
   private static final double ONE_OVER_A_THOUSAND = 0.001;
   private final int numberOfInterpolationPoints;
   private double initialTrustRegionRadius;
   private final double stoppingTrustRegionRadius;
   private boolean isMinimize;
   private ArrayRealVector currentBest;
   private double[] boundDifference;
   private int trustRegionCenterInterpolationPointIndex;
   private Array2DRowRealMatrix bMatrix;
   private Array2DRowRealMatrix zMatrix;
   private Array2DRowRealMatrix interpolationPoints;
   private ArrayRealVector originShift;
   private ArrayRealVector fAtInterpolationPoints;
   private ArrayRealVector trustRegionCenterOffset;
   private ArrayRealVector gradientAtTrustRegionCenter;
   private ArrayRealVector lowerDifference;
   private ArrayRealVector upperDifference;
   private ArrayRealVector modelSecondDerivativesParameters;
   private ArrayRealVector newPoint;
   private ArrayRealVector alternativeNewPoint;
   private ArrayRealVector trialStepPoint;
   private ArrayRealVector lagrangeValuesAtNewPoint;
   private ArrayRealVector modelSecondDerivativesValues;

   public BOBYQAOptimizer(int numberOfInterpolationPoints) {
      this(numberOfInterpolationPoints, (double)10.0F, 1.0E-8);
   }

   public BOBYQAOptimizer(int numberOfInterpolationPoints, double initialTrustRegionRadius, double stoppingTrustRegionRadius) {
      super((ConvergenceChecker)null);
      this.numberOfInterpolationPoints = numberOfInterpolationPoints;
      this.initialTrustRegionRadius = initialTrustRegionRadius;
      this.stoppingTrustRegionRadius = stoppingTrustRegionRadius;
   }

   protected PointValuePair doOptimize() {
      double[] lowerBound = this.getLowerBound();
      double[] upperBound = this.getUpperBound();
      this.setup(lowerBound, upperBound);
      this.isMinimize = this.getGoalType() == GoalType.MINIMIZE;
      this.currentBest = new ArrayRealVector(this.getStartPoint());
      double value = this.bobyqa(lowerBound, upperBound);
      return new PointValuePair(this.currentBest.getDataRef(), this.isMinimize ? value : -value);
   }

   private double bobyqa(double[] lowerBound, double[] upperBound) {
      printMethod();
      int n = this.currentBest.getDimension();

      for(int j = 0; j < n; ++j) {
         double boundDiff = this.boundDifference[j];
         this.lowerDifference.setEntry(j, lowerBound[j] - this.currentBest.getEntry(j));
         this.upperDifference.setEntry(j, upperBound[j] - this.currentBest.getEntry(j));
         if (this.lowerDifference.getEntry(j) >= -this.initialTrustRegionRadius) {
            if (this.lowerDifference.getEntry(j) >= (double)0.0F) {
               this.currentBest.setEntry(j, lowerBound[j]);
               this.lowerDifference.setEntry(j, (double)0.0F);
               this.upperDifference.setEntry(j, boundDiff);
            } else {
               this.currentBest.setEntry(j, lowerBound[j] + this.initialTrustRegionRadius);
               this.lowerDifference.setEntry(j, -this.initialTrustRegionRadius);
               double deltaOne = upperBound[j] - this.currentBest.getEntry(j);
               this.upperDifference.setEntry(j, FastMath.max(deltaOne, this.initialTrustRegionRadius));
            }
         } else if (this.upperDifference.getEntry(j) <= this.initialTrustRegionRadius) {
            if (this.upperDifference.getEntry(j) <= (double)0.0F) {
               this.currentBest.setEntry(j, upperBound[j]);
               this.lowerDifference.setEntry(j, -boundDiff);
               this.upperDifference.setEntry(j, (double)0.0F);
            } else {
               this.currentBest.setEntry(j, upperBound[j] - this.initialTrustRegionRadius);
               double deltaOne = lowerBound[j] - this.currentBest.getEntry(j);
               double deltaTwo = -this.initialTrustRegionRadius;
               this.lowerDifference.setEntry(j, FastMath.min(deltaOne, deltaTwo));
               this.upperDifference.setEntry(j, this.initialTrustRegionRadius);
            }
         }
      }

      return this.bobyqb(lowerBound, upperBound);
   }

   private double bobyqb(double[] lowerBound, double[] upperBound) {
      printMethod();
      int n = this.currentBest.getDimension();
      int npt = this.numberOfInterpolationPoints;
      int np = n + 1;
      int nptm = npt - np;
      int nh = n * np / 2;
      ArrayRealVector work1 = new ArrayRealVector(n);
      ArrayRealVector work2 = new ArrayRealVector(npt);
      ArrayRealVector work3 = new ArrayRealVector(npt);
      double cauchy = Double.NaN;
      double alpha = Double.NaN;
      double dsq = Double.NaN;
      double crvmin = Double.NaN;
      this.trustRegionCenterInterpolationPointIndex = 0;
      this.prelim(lowerBound, upperBound);
      double xoptsq = (double)0.0F;

      for(int i = 0; i < n; ++i) {
         this.trustRegionCenterOffset.setEntry(i, this.interpolationPoints.getEntry(this.trustRegionCenterInterpolationPointIndex, i));
         double deltaOne = this.trustRegionCenterOffset.getEntry(i);
         xoptsq += deltaOne * deltaOne;
      }

      double fsave = this.fAtInterpolationPoints.getEntry(0);
      int kbase = 0;
      int ntrits = 0;
      int itest = 0;
      int knew = 0;
      int nfsav = this.getEvaluations();
      double rho = this.initialTrustRegionRadius;
      double delta = rho;
      double diffa = (double)0.0F;
      double diffb = (double)0.0F;
      double diffc = (double)0.0F;
      double f = (double)0.0F;
      double beta = (double)0.0F;
      double adelt = (double)0.0F;
      double denom = (double)0.0F;
      double ratio = (double)0.0F;
      double dnorm = (double)0.0F;
      double scaden = (double)0.0F;
      double biglsq = (double)0.0F;
      double distsq = (double)0.0F;
      int state = 20;

      label874:
      while(true) {
         label872:
         while(true) {
            label870:
            while(true) {
               label868:
               while(true) {
                  label865:
                  while(true) {
                     switch (state) {
                        case 20:
                           printState(20);
                           if (this.trustRegionCenterInterpolationPointIndex != 0) {
                              int ih = 0;

                              for(int j = 0; j < n; ++j) {
                                 for(int i = 0; i <= j; ++i) {
                                    if (i < j) {
                                       this.gradientAtTrustRegionCenter.setEntry(j, this.gradientAtTrustRegionCenter.getEntry(j) + this.modelSecondDerivativesValues.getEntry(ih) * this.trustRegionCenterOffset.getEntry(i));
                                    }

                                    this.gradientAtTrustRegionCenter.setEntry(i, this.gradientAtTrustRegionCenter.getEntry(i) + this.modelSecondDerivativesValues.getEntry(ih) * this.trustRegionCenterOffset.getEntry(j));
                                    ++ih;
                                 }
                              }

                              if (this.getEvaluations() > npt) {
                                 for(int k = 0; k < npt; ++k) {
                                    double temp = (double)0.0F;

                                    for(int j = 0; j < n; ++j) {
                                       temp += this.interpolationPoints.getEntry(k, j) * this.trustRegionCenterOffset.getEntry(j);
                                    }

                                    temp *= this.modelSecondDerivativesParameters.getEntry(k);

                                    for(int i = 0; i < n; ++i) {
                                       this.gradientAtTrustRegionCenter.setEntry(i, this.gradientAtTrustRegionCenter.getEntry(i) + temp * this.interpolationPoints.getEntry(k, i));
                                    }
                                 }
                              }
                           }
                        case 60:
                           break label868;
                        case 90:
                           break label872;
                        case 210:
                           printState(210);
                           double[] alphaCauchy = this.altmov(knew, adelt);
                           alpha = alphaCauchy[0];
                           cauchy = alphaCauchy[1];

                           for(int i = 0; i < n; ++i) {
                              this.trialStepPoint.setEntry(i, this.newPoint.getEntry(i) - this.trustRegionCenterOffset.getEntry(i));
                           }
                        case 230:
                           printState(230);

                           for(int k = 0; k < npt; ++k) {
                              double suma = (double)0.0F;
                              double sumb = (double)0.0F;
                              double sum = (double)0.0F;

                              for(int j = 0; j < n; ++j) {
                                 suma += this.interpolationPoints.getEntry(k, j) * this.trialStepPoint.getEntry(j);
                                 sumb += this.interpolationPoints.getEntry(k, j) * this.trustRegionCenterOffset.getEntry(j);
                                 sum += this.bMatrix.getEntry(k, j) * this.trialStepPoint.getEntry(j);
                              }

                              work3.setEntry(k, suma * ((double)0.5F * suma + sumb));
                              this.lagrangeValuesAtNewPoint.setEntry(k, sum);
                              work2.setEntry(k, suma);
                           }

                           beta = (double)0.0F;

                           for(int m = 0; m < nptm; ++m) {
                              double sum = (double)0.0F;

                              for(int k = 0; k < npt; ++k) {
                                 sum += this.zMatrix.getEntry(k, m) * work3.getEntry(k);
                              }

                              beta -= sum * sum;

                              for(int k = 0; k < npt; ++k) {
                                 this.lagrangeValuesAtNewPoint.setEntry(k, this.lagrangeValuesAtNewPoint.getEntry(k) + sum * this.zMatrix.getEntry(k, m));
                              }
                           }

                           dsq = (double)0.0F;
                           double bsum = (double)0.0F;
                           double dx = (double)0.0F;

                           for(int j = 0; j < n; ++j) {
                              double d1 = this.trialStepPoint.getEntry(j);
                              dsq += d1 * d1;
                              double sum = (double)0.0F;

                              for(int k = 0; k < npt; ++k) {
                                 sum += work3.getEntry(k) * this.bMatrix.getEntry(k, j);
                              }

                              bsum += sum * this.trialStepPoint.getEntry(j);
                              int jp = npt + j;

                              for(int i = 0; i < n; ++i) {
                                 sum += this.bMatrix.getEntry(jp, i) * this.trialStepPoint.getEntry(i);
                              }

                              this.lagrangeValuesAtNewPoint.setEntry(jp, sum);
                              bsum += sum * this.trialStepPoint.getEntry(j);
                              dx += this.trialStepPoint.getEntry(j) * this.trustRegionCenterOffset.getEntry(j);
                           }

                           beta = dx * dx + dsq * (xoptsq + dx + dx + (double)0.5F * dsq) + beta - bsum;
                           this.lagrangeValuesAtNewPoint.setEntry(this.trustRegionCenterInterpolationPointIndex, this.lagrangeValuesAtNewPoint.getEntry(this.trustRegionCenterInterpolationPointIndex) + (double)1.0F);
                           if (ntrits == 0) {
                              double d1 = this.lagrangeValuesAtNewPoint.getEntry(knew);
                              denom = d1 * d1 + alpha * beta;
                              if (denom < cauchy && cauchy > (double)0.0F) {
                                 for(int i = 0; i < n; ++i) {
                                    this.newPoint.setEntry(i, this.alternativeNewPoint.getEntry(i));
                                    this.trialStepPoint.setEntry(i, this.newPoint.getEntry(i) - this.trustRegionCenterOffset.getEntry(i));
                                 }

                                 cauchy = (double)0.0F;
                                 state = 230;
                                 continue;
                              }
                           } else {
                              double delsq = delta * delta;
                              scaden = (double)0.0F;
                              biglsq = (double)0.0F;
                              knew = 0;

                              for(int k = 0; k < npt; ++k) {
                                 if (k != this.trustRegionCenterInterpolationPointIndex) {
                                    double hdiag = (double)0.0F;

                                    for(int m = 0; m < nptm; ++m) {
                                       double d1 = this.zMatrix.getEntry(k, m);
                                       hdiag += d1 * d1;
                                    }

                                    double d2 = this.lagrangeValuesAtNewPoint.getEntry(k);
                                    double den = beta * hdiag + d2 * d2;
                                    distsq = (double)0.0F;

                                    for(int j = 0; j < n; ++j) {
                                       double d3 = this.interpolationPoints.getEntry(k, j) - this.trustRegionCenterOffset.getEntry(j);
                                       distsq += d3 * d3;
                                    }

                                    double d4 = distsq / delsq;
                                    double temp = FastMath.max((double)1.0F, d4 * d4);
                                    if (temp * den > scaden) {
                                       scaden = temp * den;
                                       knew = k;
                                       denom = den;
                                    }

                                    double d5 = this.lagrangeValuesAtNewPoint.getEntry(k);
                                    biglsq = FastMath.max(biglsq, temp * d5 * d5);
                                 }
                              }
                           }
                        case 360:
                           break;
                        case 650:
                           break label865;
                        case 680:
                           break label870;
                        case 720:
                           break label874;
                        default:
                           throw new MathIllegalStateException(LocalizedFormats.SIMPLE_MESSAGE, new Object[]{"bobyqb"});
                     }

                     printState(360);

                     for(int i = 0; i < n; ++i) {
                        double d3 = lowerBound[i];
                        double d4 = this.originShift.getEntry(i) + this.newPoint.getEntry(i);
                        double d1 = FastMath.max(d3, d4);
                        double d2 = upperBound[i];
                        this.currentBest.setEntry(i, FastMath.min(d1, d2));
                        if (this.newPoint.getEntry(i) == this.lowerDifference.getEntry(i)) {
                           this.currentBest.setEntry(i, lowerBound[i]);
                        }

                        if (this.newPoint.getEntry(i) == this.upperDifference.getEntry(i)) {
                           this.currentBest.setEntry(i, upperBound[i]);
                        }
                     }

                     f = this.computeObjectiveValue(this.currentBest.toArray());
                     if (!this.isMinimize) {
                        f = -f;
                     }

                     if (ntrits == -1) {
                        fsave = f;
                        state = 720;
                     } else {
                        double fopt = this.fAtInterpolationPoints.getEntry(this.trustRegionCenterInterpolationPointIndex);
                        double vquad = (double)0.0F;
                        int ih = 0;

                        for(int j = 0; j < n; ++j) {
                           vquad += this.trialStepPoint.getEntry(j) * this.gradientAtTrustRegionCenter.getEntry(j);

                           for(int i = 0; i <= j; ++i) {
                              double temp = this.trialStepPoint.getEntry(i) * this.trialStepPoint.getEntry(j);
                              if (i == j) {
                                 temp *= (double)0.5F;
                              }

                              vquad += this.modelSecondDerivativesValues.getEntry(ih) * temp;
                              ++ih;
                           }
                        }

                        for(int k = 0; k < npt; ++k) {
                           double d1 = work2.getEntry(k);
                           double d2 = d1 * d1;
                           vquad += (double)0.5F * this.modelSecondDerivativesParameters.getEntry(k) * d2;
                        }

                        double diff = f - fopt - vquad;
                        diffc = diffb;
                        diffb = diffa;
                        diffa = FastMath.abs(diff);
                        if (dnorm > rho) {
                           nfsav = this.getEvaluations();
                        }

                        if (ntrits > 0) {
                           if (vquad >= (double)0.0F) {
                              throw new MathIllegalStateException(LocalizedFormats.TRUST_REGION_STEP_FAILED, new Object[]{vquad});
                           }

                           ratio = (f - fopt) / vquad;
                           double hDelta = (double)0.5F * delta;
                           if (ratio <= 0.1) {
                              delta = FastMath.min(hDelta, dnorm);
                           } else if (ratio <= 0.7) {
                              delta = FastMath.max(hDelta, dnorm);
                           } else {
                              delta = FastMath.max(hDelta, (double)2.0F * dnorm);
                           }

                           if (delta <= rho * (double)1.5F) {
                              delta = rho;
                           }

                           if (f < fopt) {
                              int ksav = knew;
                              double densav = denom;
                              double delsq = delta * delta;
                              scaden = (double)0.0F;
                              biglsq = (double)0.0F;
                              knew = 0;

                              for(int k = 0; k < npt; ++k) {
                                 double hdiag = (double)0.0F;

                                 for(int m = 0; m < nptm; ++m) {
                                    double d1 = this.zMatrix.getEntry(k, m);
                                    hdiag += d1 * d1;
                                 }

                                 double d1 = this.lagrangeValuesAtNewPoint.getEntry(k);
                                 double den = beta * hdiag + d1 * d1;
                                 distsq = (double)0.0F;

                                 for(int j = 0; j < n; ++j) {
                                    double d2 = this.interpolationPoints.getEntry(k, j) - this.newPoint.getEntry(j);
                                    distsq += d2 * d2;
                                 }

                                 double d3 = distsq / delsq;
                                 double temp = FastMath.max((double)1.0F, d3 * d3);
                                 if (temp * den > scaden) {
                                    scaden = temp * den;
                                    knew = k;
                                    denom = den;
                                 }

                                 double d4 = this.lagrangeValuesAtNewPoint.getEntry(k);
                                 double d5 = temp * d4 * d4;
                                 biglsq = FastMath.max(biglsq, d5);
                              }

                              if (scaden <= (double)0.5F * biglsq) {
                                 knew = ksav;
                                 denom = densav;
                              }
                           }
                        }

                        this.update(beta, denom, knew);
                        ih = 0;
                        double pqold = this.modelSecondDerivativesParameters.getEntry(knew);
                        this.modelSecondDerivativesParameters.setEntry(knew, (double)0.0F);

                        for(int i = 0; i < n; ++i) {
                           double temp = pqold * this.interpolationPoints.getEntry(knew, i);

                           for(int j = 0; j <= i; ++j) {
                              this.modelSecondDerivativesValues.setEntry(ih, this.modelSecondDerivativesValues.getEntry(ih) + temp * this.interpolationPoints.getEntry(knew, j));
                              ++ih;
                           }
                        }

                        for(int m = 0; m < nptm; ++m) {
                           double temp = diff * this.zMatrix.getEntry(knew, m);

                           for(int k = 0; k < npt; ++k) {
                              this.modelSecondDerivativesParameters.setEntry(k, this.modelSecondDerivativesParameters.getEntry(k) + temp * this.zMatrix.getEntry(k, m));
                           }
                        }

                        this.fAtInterpolationPoints.setEntry(knew, f);

                        for(int i = 0; i < n; ++i) {
                           this.interpolationPoints.setEntry(knew, i, this.newPoint.getEntry(i));
                           work1.setEntry(i, this.bMatrix.getEntry(knew, i));
                        }

                        for(int k = 0; k < npt; ++k) {
                           double suma = (double)0.0F;

                           for(int m = 0; m < nptm; ++m) {
                              suma += this.zMatrix.getEntry(knew, m) * this.zMatrix.getEntry(k, m);
                           }

                           double sumb = (double)0.0F;

                           for(int j = 0; j < n; ++j) {
                              sumb += this.interpolationPoints.getEntry(k, j) * this.trustRegionCenterOffset.getEntry(j);
                           }

                           double temp = suma * sumb;

                           for(int i = 0; i < n; ++i) {
                              work1.setEntry(i, work1.getEntry(i) + temp * this.interpolationPoints.getEntry(k, i));
                           }
                        }

                        for(int i = 0; i < n; ++i) {
                           this.gradientAtTrustRegionCenter.setEntry(i, this.gradientAtTrustRegionCenter.getEntry(i) + diff * work1.getEntry(i));
                        }

                        if (f < fopt) {
                           this.trustRegionCenterInterpolationPointIndex = knew;
                           xoptsq = (double)0.0F;
                           ih = 0;

                           for(int j = 0; j < n; ++j) {
                              this.trustRegionCenterOffset.setEntry(j, this.newPoint.getEntry(j));
                              double d1 = this.trustRegionCenterOffset.getEntry(j);
                              xoptsq += d1 * d1;

                              for(int i = 0; i <= j; ++i) {
                                 if (i < j) {
                                    this.gradientAtTrustRegionCenter.setEntry(j, this.gradientAtTrustRegionCenter.getEntry(j) + this.modelSecondDerivativesValues.getEntry(ih) * this.trialStepPoint.getEntry(i));
                                 }

                                 this.gradientAtTrustRegionCenter.setEntry(i, this.gradientAtTrustRegionCenter.getEntry(i) + this.modelSecondDerivativesValues.getEntry(ih) * this.trialStepPoint.getEntry(j));
                                 ++ih;
                              }
                           }

                           for(int k = 0; k < npt; ++k) {
                              double temp = (double)0.0F;

                              for(int j = 0; j < n; ++j) {
                                 temp += this.interpolationPoints.getEntry(k, j) * this.trialStepPoint.getEntry(j);
                              }

                              temp *= this.modelSecondDerivativesParameters.getEntry(k);

                              for(int i = 0; i < n; ++i) {
                                 this.gradientAtTrustRegionCenter.setEntry(i, this.gradientAtTrustRegionCenter.getEntry(i) + temp * this.interpolationPoints.getEntry(k, i));
                              }
                           }
                        }

                        if (ntrits > 0) {
                           for(int k = 0; k < npt; ++k) {
                              this.lagrangeValuesAtNewPoint.setEntry(k, this.fAtInterpolationPoints.getEntry(k) - this.fAtInterpolationPoints.getEntry(this.trustRegionCenterInterpolationPointIndex));
                              work3.setEntry(k, (double)0.0F);
                           }

                           for(int j = 0; j < nptm; ++j) {
                              double sum = (double)0.0F;

                              for(int k = 0; k < npt; ++k) {
                                 sum += this.zMatrix.getEntry(k, j) * this.lagrangeValuesAtNewPoint.getEntry(k);
                              }

                              for(int k = 0; k < npt; ++k) {
                                 work3.setEntry(k, work3.getEntry(k) + sum * this.zMatrix.getEntry(k, j));
                              }
                           }

                           for(int k = 0; k < npt; ++k) {
                              double sum = (double)0.0F;

                              for(int j = 0; j < n; ++j) {
                                 sum += this.interpolationPoints.getEntry(k, j) * this.trustRegionCenterOffset.getEntry(j);
                              }

                              work2.setEntry(k, work3.getEntry(k));
                              work3.setEntry(k, sum * work3.getEntry(k));
                           }

                           double gqsq = (double)0.0F;
                           double gisq = (double)0.0F;

                           for(int i = 0; i < n; ++i) {
                              double sum = (double)0.0F;

                              for(int k = 0; k < npt; ++k) {
                                 sum += this.bMatrix.getEntry(k, i) * this.lagrangeValuesAtNewPoint.getEntry(k) + this.interpolationPoints.getEntry(k, i) * work3.getEntry(k);
                              }

                              if (this.trustRegionCenterOffset.getEntry(i) == this.lowerDifference.getEntry(i)) {
                                 double d1 = FastMath.min((double)0.0F, this.gradientAtTrustRegionCenter.getEntry(i));
                                 gqsq += d1 * d1;
                                 double d2 = FastMath.min((double)0.0F, sum);
                                 gisq += d2 * d2;
                              } else if (this.trustRegionCenterOffset.getEntry(i) == this.upperDifference.getEntry(i)) {
                                 double d1 = FastMath.max((double)0.0F, this.gradientAtTrustRegionCenter.getEntry(i));
                                 gqsq += d1 * d1;
                                 double d2 = FastMath.max((double)0.0F, sum);
                                 gisq += d2 * d2;
                              } else {
                                 double d1 = this.gradientAtTrustRegionCenter.getEntry(i);
                                 gqsq += d1 * d1;
                                 gisq += sum * sum;
                              }

                              this.lagrangeValuesAtNewPoint.setEntry(npt + i, sum);
                           }

                           ++itest;
                           if (gqsq < (double)10.0F * gisq) {
                              itest = 0;
                           }

                           if (itest >= 3) {
                              int i = 0;

                              for(int max = FastMath.max(npt, nh); i < max; ++i) {
                                 if (i < n) {
                                    this.gradientAtTrustRegionCenter.setEntry(i, this.lagrangeValuesAtNewPoint.getEntry(npt + i));
                                 }

                                 if (i < npt) {
                                    this.modelSecondDerivativesParameters.setEntry(i, work2.getEntry(i));
                                 }

                                 if (i < nh) {
                                    this.modelSecondDerivativesValues.setEntry(i, (double)0.0F);
                                 }

                                 itest = 0;
                              }
                           }
                        }

                        if (ntrits == 0) {
                           state = 60;
                        } else {
                           if (!(f <= fopt + 0.1 * vquad)) {
                              double d1 = (double)2.0F * delta;
                              double d2 = (double)10.0F * rho;
                              distsq = FastMath.max(d1 * d1, d2 * d2);
                              break;
                           }

                           state = 60;
                        }
                     }
                  }

                  printState(650);
                  knew = -1;

                  for(int k = 0; k < npt; ++k) {
                     double sum = (double)0.0F;

                     for(int j = 0; j < n; ++j) {
                        double d1 = this.interpolationPoints.getEntry(k, j) - this.trustRegionCenterOffset.getEntry(j);
                        sum += d1 * d1;
                     }

                     if (sum > distsq) {
                        knew = k;
                        distsq = sum;
                     }
                  }

                  if (knew >= 0) {
                     double dist = FastMath.sqrt(distsq);
                     if (ntrits == -1) {
                        delta = FastMath.min(0.1 * delta, (double)0.5F * dist);
                        if (delta <= rho * (double)1.5F) {
                           delta = rho;
                        }
                     }

                     ntrits = 0;
                     double d1 = FastMath.min(0.1 * dist, delta);
                     adelt = FastMath.max(d1, rho);
                     dsq = adelt * adelt;
                     state = 90;
                  } else if (ntrits == -1) {
                     state = 680;
                  } else if (ratio > (double)0.0F) {
                     state = 60;
                  } else {
                     if (FastMath.max(delta, dnorm) > rho) {
                        state = 60;
                        continue;
                     }
                     break label870;
                  }
               }

               printState(60);
               ArrayRealVector gnew = new ArrayRealVector(n);
               ArrayRealVector xbdi = new ArrayRealVector(n);
               ArrayRealVector s = new ArrayRealVector(n);
               ArrayRealVector hs = new ArrayRealVector(n);
               ArrayRealVector hred = new ArrayRealVector(n);
               double[] dsqCrvmin = this.trsbox(delta, gnew, xbdi, s, hs, hred);
               dsq = dsqCrvmin[0];
               crvmin = dsqCrvmin[1];
               double deltaTwo = FastMath.sqrt(dsq);
               dnorm = FastMath.min(delta, deltaTwo);
               if (!(dnorm < (double)0.5F * rho)) {
                  ++ntrits;
                  break label872;
               }

               ntrits = -1;
               double var154 = (double)10.0F * rho;
               distsq = var154 * var154;
               if (this.getEvaluations() <= nfsav + 2) {
                  state = 650;
               } else {
                  var154 = FastMath.max(diffa, diffb);
                  double errbig = FastMath.max(var154, diffc);
                  double frhosq = rho * (double)0.125F * rho;
                  if (crvmin > (double)0.0F && errbig > frhosq * crvmin) {
                     state = 650;
                  } else {
                     double bdtol = errbig / rho;

                     for(int j = 0; j < n; ++j) {
                        double bdtest = bdtol;
                        if (this.newPoint.getEntry(j) == this.lowerDifference.getEntry(j)) {
                           bdtest = work1.getEntry(j);
                        }

                        if (this.newPoint.getEntry(j) == this.upperDifference.getEntry(j)) {
                           bdtest = -work1.getEntry(j);
                        }

                        if (bdtest < bdtol) {
                           double curv = this.modelSecondDerivativesValues.getEntry((j + j * j) / 2);

                           for(int k = 0; k < npt; ++k) {
                              double d1 = this.interpolationPoints.getEntry(k, j);
                              curv += this.modelSecondDerivativesParameters.getEntry(k) * d1 * d1;
                           }

                           bdtest += (double)0.5F * curv * rho;
                           if (bdtest < bdtol) {
                              state = 650;
                              break;
                           }
                        }
                     }

                     state = 680;
                  }
               }
            }

            printState(680);
            if (rho > this.stoppingTrustRegionRadius) {
               delta = (double)0.5F * rho;
               ratio = rho / this.stoppingTrustRegionRadius;
               if (ratio <= (double)16.0F) {
                  rho = this.stoppingTrustRegionRadius;
               } else if (ratio <= (double)250.0F) {
                  rho = FastMath.sqrt(ratio) * this.stoppingTrustRegionRadius;
               } else {
                  rho *= 0.1;
               }

               delta = FastMath.max(delta, rho);
               ntrits = 0;
               nfsav = this.getEvaluations();
               state = 60;
            } else {
               if (ntrits == -1) {
                  state = 360;
                  continue;
               }
               break label874;
            }
         }

         printState(90);
         if (dsq <= xoptsq * 0.001) {
            double fracsq = xoptsq * (double)0.25F;
            double sumpq = (double)0.0F;

            for(int k = 0; k < npt; ++k) {
               sumpq += this.modelSecondDerivativesParameters.getEntry(k);
               double sum = (double)-0.5F * xoptsq;

               for(int i = 0; i < n; ++i) {
                  sum += this.interpolationPoints.getEntry(k, i) * this.trustRegionCenterOffset.getEntry(i);
               }

               work2.setEntry(k, sum);
               double temp = fracsq - (double)0.5F * sum;

               for(int i = 0; i < n; ++i) {
                  work1.setEntry(i, this.bMatrix.getEntry(k, i));
                  this.lagrangeValuesAtNewPoint.setEntry(i, sum * this.interpolationPoints.getEntry(k, i) + temp * this.trustRegionCenterOffset.getEntry(i));
                  int ip = npt + i;

                  for(int j = 0; j <= i; ++j) {
                     this.bMatrix.setEntry(ip, j, this.bMatrix.getEntry(ip, j) + work1.getEntry(i) * this.lagrangeValuesAtNewPoint.getEntry(j) + this.lagrangeValuesAtNewPoint.getEntry(i) * work1.getEntry(j));
                  }
               }
            }

            for(int m = 0; m < nptm; ++m) {
               double sumz = (double)0.0F;
               double sumw = (double)0.0F;

               for(int k = 0; k < npt; ++k) {
                  sumz += this.zMatrix.getEntry(k, m);
                  this.lagrangeValuesAtNewPoint.setEntry(k, work2.getEntry(k) * this.zMatrix.getEntry(k, m));
                  sumw += this.lagrangeValuesAtNewPoint.getEntry(k);
               }

               for(int j = 0; j < n; ++j) {
                  double sum = (fracsq * sumz - (double)0.5F * sumw) * this.trustRegionCenterOffset.getEntry(j);

                  for(int k = 0; k < npt; ++k) {
                     sum += this.lagrangeValuesAtNewPoint.getEntry(k) * this.interpolationPoints.getEntry(k, j);
                  }

                  work1.setEntry(j, sum);

                  for(int k = 0; k < npt; ++k) {
                     this.bMatrix.setEntry(k, j, this.bMatrix.getEntry(k, j) + sum * this.zMatrix.getEntry(k, m));
                  }
               }

               for(int i = 0; i < n; ++i) {
                  int ip = i + npt;
                  double temp = work1.getEntry(i);

                  for(int j = 0; j <= i; ++j) {
                     this.bMatrix.setEntry(ip, j, this.bMatrix.getEntry(ip, j) + temp * work1.getEntry(j));
                  }
               }
            }

            int ih = 0;

            for(int j = 0; j < n; ++j) {
               work1.setEntry(j, (double)-0.5F * sumpq * this.trustRegionCenterOffset.getEntry(j));

               for(int k = 0; k < npt; ++k) {
                  work1.setEntry(j, work1.getEntry(j) + this.modelSecondDerivativesParameters.getEntry(k) * this.interpolationPoints.getEntry(k, j));
                  this.interpolationPoints.setEntry(k, j, this.interpolationPoints.getEntry(k, j) - this.trustRegionCenterOffset.getEntry(j));
               }

               for(int i = 0; i <= j; ++i) {
                  this.modelSecondDerivativesValues.setEntry(ih, this.modelSecondDerivativesValues.getEntry(ih) + work1.getEntry(i) * this.trustRegionCenterOffset.getEntry(j) + this.trustRegionCenterOffset.getEntry(i) * work1.getEntry(j));
                  this.bMatrix.setEntry(npt + i, j, this.bMatrix.getEntry(npt + j, i));
                  ++ih;
               }
            }

            for(int i = 0; i < n; ++i) {
               this.originShift.setEntry(i, this.originShift.getEntry(i) + this.trustRegionCenterOffset.getEntry(i));
               this.newPoint.setEntry(i, this.newPoint.getEntry(i) - this.trustRegionCenterOffset.getEntry(i));
               this.lowerDifference.setEntry(i, this.lowerDifference.getEntry(i) - this.trustRegionCenterOffset.getEntry(i));
               this.upperDifference.setEntry(i, this.upperDifference.getEntry(i) - this.trustRegionCenterOffset.getEntry(i));
               this.trustRegionCenterOffset.setEntry(i, (double)0.0F);
            }

            xoptsq = (double)0.0F;
         }

         if (ntrits == 0) {
            state = 210;
         } else {
            state = 230;
         }
      }

      printState(720);
      if (this.fAtInterpolationPoints.getEntry(this.trustRegionCenterInterpolationPointIndex) <= fsave) {
         for(int i = 0; i < n; ++i) {
            double d3 = lowerBound[i];
            double d4 = this.originShift.getEntry(i) + this.trustRegionCenterOffset.getEntry(i);
            double d1 = FastMath.max(d3, d4);
            double d2 = upperBound[i];
            this.currentBest.setEntry(i, FastMath.min(d1, d2));
            if (this.trustRegionCenterOffset.getEntry(i) == this.lowerDifference.getEntry(i)) {
               this.currentBest.setEntry(i, lowerBound[i]);
            }

            if (this.trustRegionCenterOffset.getEntry(i) == this.upperDifference.getEntry(i)) {
               this.currentBest.setEntry(i, upperBound[i]);
            }
         }

         f = this.fAtInterpolationPoints.getEntry(this.trustRegionCenterInterpolationPointIndex);
      }

      return f;
   }

   private double[] altmov(int knew, double adelt) {
      printMethod();
      int n = this.currentBest.getDimension();
      int npt = this.numberOfInterpolationPoints;
      ArrayRealVector glag = new ArrayRealVector(n);
      ArrayRealVector hcol = new ArrayRealVector(npt);
      ArrayRealVector work1 = new ArrayRealVector(n);
      ArrayRealVector work2 = new ArrayRealVector(n);

      for(int k = 0; k < npt; ++k) {
         hcol.setEntry(k, (double)0.0F);
      }

      int j = 0;

      for(int max = npt - n - 1; j < max; ++j) {
         double tmp = this.zMatrix.getEntry(knew, j);

         for(int k = 0; k < npt; ++k) {
            hcol.setEntry(k, hcol.getEntry(k) + tmp * this.zMatrix.getEntry(k, j));
         }
      }

      double alpha = hcol.getEntry(knew);
      double ha = (double)0.5F * alpha;

      for(int i = 0; i < n; ++i) {
         glag.setEntry(i, this.bMatrix.getEntry(knew, i));
      }

      for(int k = 0; k < npt; ++k) {
         double tmp = (double)0.0F;

         for(int j = 0; j < n; ++j) {
            tmp += this.interpolationPoints.getEntry(k, j) * this.trustRegionCenterOffset.getEntry(j);
         }

         tmp *= hcol.getEntry(k);

         for(int i = 0; i < n; ++i) {
            glag.setEntry(i, glag.getEntry(i) + tmp * this.interpolationPoints.getEntry(k, i));
         }
      }

      double presav = (double)0.0F;
      double step = Double.NaN;
      int ksav = 0;
      int ibdsav = 0;
      double stpsav = (double)0.0F;

      for(int k = 0; k < npt; ++k) {
         if (k != this.trustRegionCenterInterpolationPointIndex) {
            double dderiv = (double)0.0F;
            double distsq = (double)0.0F;

            for(int i = 0; i < n; ++i) {
               double tmp = this.interpolationPoints.getEntry(k, i) - this.trustRegionCenterOffset.getEntry(i);
               dderiv += glag.getEntry(i) * tmp;
               distsq += tmp * tmp;
            }

            double subd = adelt / FastMath.sqrt(distsq);
            double slbd = -subd;
            int ilbd = 0;
            int iubd = 0;
            double sumin = FastMath.min((double)1.0F, subd);

            for(int i = 0; i < n; ++i) {
               double tmp = this.interpolationPoints.getEntry(k, i) - this.trustRegionCenterOffset.getEntry(i);
               if (tmp > (double)0.0F) {
                  if (slbd * tmp < this.lowerDifference.getEntry(i) - this.trustRegionCenterOffset.getEntry(i)) {
                     slbd = (this.lowerDifference.getEntry(i) - this.trustRegionCenterOffset.getEntry(i)) / tmp;
                     ilbd = -i - 1;
                  }

                  if (subd * tmp > this.upperDifference.getEntry(i) - this.trustRegionCenterOffset.getEntry(i)) {
                     subd = FastMath.max(sumin, (this.upperDifference.getEntry(i) - this.trustRegionCenterOffset.getEntry(i)) / tmp);
                     iubd = i + 1;
                  }
               } else if (tmp < (double)0.0F) {
                  if (slbd * tmp > this.upperDifference.getEntry(i) - this.trustRegionCenterOffset.getEntry(i)) {
                     slbd = (this.upperDifference.getEntry(i) - this.trustRegionCenterOffset.getEntry(i)) / tmp;
                     ilbd = i + 1;
                  }

                  if (subd * tmp < this.lowerDifference.getEntry(i) - this.trustRegionCenterOffset.getEntry(i)) {
                     subd = FastMath.max(sumin, (this.lowerDifference.getEntry(i) - this.trustRegionCenterOffset.getEntry(i)) / tmp);
                     iubd = -i - 1;
                  }
               }
            }

            step = slbd;
            int isbd = ilbd;
            double vlag = Double.NaN;
            if (k == knew) {
               double diff = dderiv - (double)1.0F;
               vlag = slbd * (dderiv - slbd * diff);
               double d1 = subd * (dderiv - subd * diff);
               if (FastMath.abs(d1) > FastMath.abs(vlag)) {
                  step = subd;
                  vlag = d1;
                  isbd = iubd;
               }

               double d2 = (double)0.5F * dderiv;
               double d3 = d2 - diff * slbd;
               double d4 = d2 - diff * subd;
               if (d3 * d4 < (double)0.0F) {
                  double d5 = d2 * d2 / diff;
                  if (FastMath.abs(d5) > FastMath.abs(vlag)) {
                     step = d2 / diff;
                     vlag = d5;
                     isbd = 0;
                  }
               }
            } else {
               vlag = slbd * ((double)1.0F - slbd);
               double tmp = subd * ((double)1.0F - subd);
               if (FastMath.abs(tmp) > FastMath.abs(vlag)) {
                  step = subd;
                  vlag = tmp;
                  isbd = iubd;
               }

               if (subd > (double)0.5F && FastMath.abs(vlag) < (double)0.25F) {
                  step = (double)0.5F;
                  vlag = (double)0.25F;
                  isbd = 0;
               }

               vlag *= dderiv;
            }

            double tmp = step * ((double)1.0F - step) * distsq;
            double predsq = vlag * vlag * (vlag * vlag + ha * tmp * tmp);
            if (predsq > presav) {
               presav = predsq;
               ksav = k;
               stpsav = step;
               ibdsav = isbd;
            }
         }
      }

      for(int i = 0; i < n; ++i) {
         double tmp = this.trustRegionCenterOffset.getEntry(i) + stpsav * (this.interpolationPoints.getEntry(ksav, i) - this.trustRegionCenterOffset.getEntry(i));
         this.newPoint.setEntry(i, FastMath.max(this.lowerDifference.getEntry(i), FastMath.min(this.upperDifference.getEntry(i), tmp)));
      }

      if (ibdsav < 0) {
         this.newPoint.setEntry(-ibdsav - 1, this.lowerDifference.getEntry(-ibdsav - 1));
      }

      if (ibdsav > 0) {
         this.newPoint.setEntry(ibdsav - 1, this.upperDifference.getEntry(ibdsav - 1));
      }

      double bigstp = adelt + adelt;
      int iflag = 0;
      double cauchy = Double.NaN;
      double csave = (double)0.0F;

      while(true) {
         double wfixsq = (double)0.0F;
         double ggfree = (double)0.0F;

         for(int i = 0; i < n; ++i) {
            double glagValue = glag.getEntry(i);
            work1.setEntry(i, (double)0.0F);
            if (FastMath.min(this.trustRegionCenterOffset.getEntry(i) - this.lowerDifference.getEntry(i), glagValue) > (double)0.0F || FastMath.max(this.trustRegionCenterOffset.getEntry(i) - this.upperDifference.getEntry(i), glagValue) < (double)0.0F) {
               work1.setEntry(i, bigstp);
               ggfree += glagValue * glagValue;
            }
         }

         if (ggfree == (double)0.0F) {
            return new double[]{alpha, (double)0.0F};
         }

         double tmp1 = adelt * adelt - wfixsq;
         if (tmp1 > (double)0.0F) {
            step = FastMath.sqrt(tmp1 / ggfree);
            ggfree = (double)0.0F;

            for(int i = 0; i < n; ++i) {
               if (work1.getEntry(i) == bigstp) {
                  double tmp2 = this.trustRegionCenterOffset.getEntry(i) - step * glag.getEntry(i);
                  if (tmp2 <= this.lowerDifference.getEntry(i)) {
                     work1.setEntry(i, this.lowerDifference.getEntry(i) - this.trustRegionCenterOffset.getEntry(i));
                     double d1 = work1.getEntry(i);
                     wfixsq += d1 * d1;
                  } else if (tmp2 >= this.upperDifference.getEntry(i)) {
                     work1.setEntry(i, this.upperDifference.getEntry(i) - this.trustRegionCenterOffset.getEntry(i));
                     double d1 = work1.getEntry(i);
                     wfixsq += d1 * d1;
                  } else {
                     double d1 = glag.getEntry(i);
                     ggfree += d1 * d1;
                  }
               }
            }
         }

         double gw = (double)0.0F;

         for(int i = 0; i < n; ++i) {
            double glagValue = glag.getEntry(i);
            if (work1.getEntry(i) == bigstp) {
               work1.setEntry(i, -step * glagValue);
               double min = FastMath.min(this.upperDifference.getEntry(i), this.trustRegionCenterOffset.getEntry(i) + work1.getEntry(i));
               this.alternativeNewPoint.setEntry(i, FastMath.max(this.lowerDifference.getEntry(i), min));
            } else if (work1.getEntry(i) == (double)0.0F) {
               this.alternativeNewPoint.setEntry(i, this.trustRegionCenterOffset.getEntry(i));
            } else if (glagValue > (double)0.0F) {
               this.alternativeNewPoint.setEntry(i, this.lowerDifference.getEntry(i));
            } else {
               this.alternativeNewPoint.setEntry(i, this.upperDifference.getEntry(i));
            }

            gw += glagValue * work1.getEntry(i);
         }

         double curv = (double)0.0F;

         for(int k = 0; k < npt; ++k) {
            double tmp = (double)0.0F;

            for(int j = 0; j < n; ++j) {
               tmp += this.interpolationPoints.getEntry(k, j) * work1.getEntry(j);
            }

            curv += hcol.getEntry(k) * tmp * tmp;
         }

         if (iflag == 1) {
            curv = -curv;
         }

         if (curv > -gw && curv < -gw * ((double)1.0F + FastMath.sqrt((double)2.0F))) {
            double scale = -gw / curv;

            for(int i = 0; i < n; ++i) {
               double tmp = this.trustRegionCenterOffset.getEntry(i) + scale * work1.getEntry(i);
               this.alternativeNewPoint.setEntry(i, FastMath.max(this.lowerDifference.getEntry(i), FastMath.min(this.upperDifference.getEntry(i), tmp)));
            }

            double d1 = (double)0.5F * gw * scale;
            cauchy = d1 * d1;
         } else {
            double d1 = gw + (double)0.5F * curv;
            cauchy = d1 * d1;
         }

         if (iflag != 0) {
            if (csave > cauchy) {
               for(int i = 0; i < n; ++i) {
                  this.alternativeNewPoint.setEntry(i, work2.getEntry(i));
               }

               cauchy = csave;
            }

            return new double[]{alpha, cauchy};
         }

         for(int i = 0; i < n; ++i) {
            glag.setEntry(i, -glag.getEntry(i));
            work2.setEntry(i, this.alternativeNewPoint.getEntry(i));
         }

         csave = cauchy;
         iflag = 1;
      }
   }

   private void prelim(double[] lowerBound, double[] upperBound) {
      printMethod();
      int n = this.currentBest.getDimension();
      int npt = this.numberOfInterpolationPoints;
      int ndim = this.bMatrix.getRowDimension();
      double rhosq = this.initialTrustRegionRadius * this.initialTrustRegionRadius;
      double recip = (double)1.0F / rhosq;
      int np = n + 1;

      for(int j = 0; j < n; ++j) {
         this.originShift.setEntry(j, this.currentBest.getEntry(j));

         for(int k = 0; k < npt; ++k) {
            this.interpolationPoints.setEntry(k, j, (double)0.0F);
         }

         for(int i = 0; i < ndim; ++i) {
            this.bMatrix.setEntry(i, j, (double)0.0F);
         }
      }

      int i = 0;

      for(int max = n * np / 2; i < max; ++i) {
         this.modelSecondDerivativesValues.setEntry(i, (double)0.0F);
      }

      for(int k = 0; k < npt; ++k) {
         this.modelSecondDerivativesParameters.setEntry(k, (double)0.0F);
         int j = 0;

         for(int max = npt - np; j < max; ++j) {
            this.zMatrix.setEntry(k, j, (double)0.0F);
         }
      }

      i = 0;
      int jpt = 0;
      double fbeg = Double.NaN;

      do {
         int nfm = this.getEvaluations();
         int nfx = nfm - n;
         int nfmm = nfm - 1;
         int nfxm = nfx - 1;
         double stepa = (double)0.0F;
         double stepb = (double)0.0F;
         if (nfm <= 2 * n) {
            if (nfm >= 1 && nfm <= n) {
               stepa = this.initialTrustRegionRadius;
               if (this.upperDifference.getEntry(nfmm) == (double)0.0F) {
                  stepa = -stepa;
               }

               this.interpolationPoints.setEntry(nfm, nfmm, stepa);
            } else if (nfm > n) {
               stepa = this.interpolationPoints.getEntry(nfx, nfxm);
               stepb = -this.initialTrustRegionRadius;
               if (this.lowerDifference.getEntry(nfxm) == (double)0.0F) {
                  stepb = FastMath.min((double)2.0F * this.initialTrustRegionRadius, this.upperDifference.getEntry(nfxm));
               }

               if (this.upperDifference.getEntry(nfxm) == (double)0.0F) {
                  stepb = FastMath.max((double)-2.0F * this.initialTrustRegionRadius, this.lowerDifference.getEntry(nfxm));
               }

               this.interpolationPoints.setEntry(nfm, nfxm, stepb);
            }
         } else {
            int tmp1 = (nfm - np) / n;
            jpt = nfm - tmp1 * n - n;
            i = jpt + tmp1;
            if (i > n) {
               int tmp2 = jpt;
               jpt = i - n;
               i = tmp2;
            }

            int iptMinus1 = i - 1;
            int jptMinus1 = jpt - 1;
            this.interpolationPoints.setEntry(nfm, iptMinus1, this.interpolationPoints.getEntry(i, iptMinus1));
            this.interpolationPoints.setEntry(nfm, jptMinus1, this.interpolationPoints.getEntry(jpt, jptMinus1));
         }

         for(int j = 0; j < n; ++j) {
            this.currentBest.setEntry(j, FastMath.min(FastMath.max(lowerBound[j], this.originShift.getEntry(j) + this.interpolationPoints.getEntry(nfm, j)), upperBound[j]));
            if (this.interpolationPoints.getEntry(nfm, j) == this.lowerDifference.getEntry(j)) {
               this.currentBest.setEntry(j, lowerBound[j]);
            }

            if (this.interpolationPoints.getEntry(nfm, j) == this.upperDifference.getEntry(j)) {
               this.currentBest.setEntry(j, upperBound[j]);
            }
         }

         double objectiveValue = this.computeObjectiveValue(this.currentBest.toArray());
         double f = this.isMinimize ? objectiveValue : -objectiveValue;
         int numEval = this.getEvaluations();
         this.fAtInterpolationPoints.setEntry(nfm, f);
         if (numEval == 1) {
            fbeg = f;
            this.trustRegionCenterInterpolationPointIndex = 0;
         } else if (f < this.fAtInterpolationPoints.getEntry(this.trustRegionCenterInterpolationPointIndex)) {
            this.trustRegionCenterInterpolationPointIndex = nfm;
         }

         if (numEval <= 2 * n + 1) {
            if (numEval >= 2 && numEval <= n + 1) {
               this.gradientAtTrustRegionCenter.setEntry(nfmm, (f - fbeg) / stepa);
               if (npt < numEval + n) {
                  double oneOverStepA = (double)1.0F / stepa;
                  this.bMatrix.setEntry(0, nfmm, -oneOverStepA);
                  this.bMatrix.setEntry(nfm, nfmm, oneOverStepA);
                  this.bMatrix.setEntry(npt + nfmm, nfmm, (double)-0.5F * rhosq);
               }
            } else if (numEval >= n + 2) {
               int ih = nfx * (nfx + 1) / 2 - 1;
               double tmp = (f - fbeg) / stepb;
               double diff = stepb - stepa;
               this.modelSecondDerivativesValues.setEntry(ih, (double)2.0F * (tmp - this.gradientAtTrustRegionCenter.getEntry(nfxm)) / diff);
               this.gradientAtTrustRegionCenter.setEntry(nfxm, (this.gradientAtTrustRegionCenter.getEntry(nfxm) * stepb - tmp * stepa) / diff);
               if (stepa * stepb < (double)0.0F && f < this.fAtInterpolationPoints.getEntry(nfm - n)) {
                  this.fAtInterpolationPoints.setEntry(nfm, this.fAtInterpolationPoints.getEntry(nfm - n));
                  this.fAtInterpolationPoints.setEntry(nfm - n, f);
                  if (this.trustRegionCenterInterpolationPointIndex == nfm) {
                     this.trustRegionCenterInterpolationPointIndex = nfm - n;
                  }

                  this.interpolationPoints.setEntry(nfm - n, nfxm, stepb);
                  this.interpolationPoints.setEntry(nfm, nfxm, stepa);
               }

               this.bMatrix.setEntry(0, nfxm, -(stepa + stepb) / (stepa * stepb));
               this.bMatrix.setEntry(nfm, nfxm, (double)-0.5F / this.interpolationPoints.getEntry(nfm - n, nfxm));
               this.bMatrix.setEntry(nfm - n, nfxm, -this.bMatrix.getEntry(0, nfxm) - this.bMatrix.getEntry(nfm, nfxm));
               this.zMatrix.setEntry(0, nfxm, FastMath.sqrt((double)2.0F) / (stepa * stepb));
               this.zMatrix.setEntry(nfm, nfxm, FastMath.sqrt((double)0.5F) / rhosq);
               this.zMatrix.setEntry(nfm - n, nfxm, -this.zMatrix.getEntry(0, nfxm) - this.zMatrix.getEntry(nfm, nfxm));
            }
         } else {
            this.zMatrix.setEntry(0, nfxm, recip);
            this.zMatrix.setEntry(nfm, nfxm, recip);
            this.zMatrix.setEntry(i, nfxm, -recip);
            this.zMatrix.setEntry(jpt, nfxm, -recip);
            int ih = i * (i - 1) / 2 + jpt - 1;
            double tmp = this.interpolationPoints.getEntry(nfm, i - 1) * this.interpolationPoints.getEntry(nfm, jpt - 1);
            this.modelSecondDerivativesValues.setEntry(ih, (fbeg - this.fAtInterpolationPoints.getEntry(i) - this.fAtInterpolationPoints.getEntry(jpt) + f) / tmp);
         }
      } while(this.getEvaluations() < npt);

   }

   private double[] trsbox(double delta, ArrayRealVector gnew, ArrayRealVector xbdi, ArrayRealVector s, ArrayRealVector hs, ArrayRealVector hred) {
      printMethod();
      int n = this.currentBest.getDimension();
      int npt = this.numberOfInterpolationPoints;
      double dsq = Double.NaN;
      double crvmin = Double.NaN;
      double beta = (double)0.0F;
      int iact = -1;
      int nact = 0;
      double angt = (double)0.0F;
      double temp = (double)0.0F;
      double xsav = (double)0.0F;
      double xsum = (double)0.0F;
      double angbd = (double)0.0F;
      double dredg = (double)0.0F;
      double sredg = (double)0.0F;
      double resid = (double)0.0F;
      double delsq = (double)0.0F;
      double ggsav = (double)0.0F;
      double tempa = (double)0.0F;
      double tempb = (double)0.0F;
      double redmax = (double)0.0F;
      double dredsq = (double)0.0F;
      double redsav = (double)0.0F;
      double gredsq = (double)0.0F;
      double rednew = (double)0.0F;
      int itcsav = 0;
      double rdprev = (double)0.0F;
      double rdnext = (double)0.0F;
      double stplen = (double)0.0F;
      double stepsq = (double)0.0F;
      int itermax = 0;
      int iterc = 0;
      nact = 0;

      for(int i = 0; i < n; ++i) {
         xbdi.setEntry(i, (double)0.0F);
         if (this.trustRegionCenterOffset.getEntry(i) <= this.lowerDifference.getEntry(i)) {
            if (this.gradientAtTrustRegionCenter.getEntry(i) >= (double)0.0F) {
               xbdi.setEntry(i, (double)-1.0F);
            }
         } else if (this.trustRegionCenterOffset.getEntry(i) >= this.upperDifference.getEntry(i) && this.gradientAtTrustRegionCenter.getEntry(i) <= (double)0.0F) {
            xbdi.setEntry(i, (double)1.0F);
         }

         if (xbdi.getEntry(i) != (double)0.0F) {
            ++nact;
         }

         this.trialStepPoint.setEntry(i, (double)0.0F);
         gnew.setEntry(i, this.gradientAtTrustRegionCenter.getEntry(i));
      }

      delsq = delta * delta;
      double qred = (double)0.0F;
      crvmin = (double)-1.0F;
      int state = 20;

      label381:
      while(true) {
         label375:
         while(true) {
            switch (state) {
               case 20:
                  printState(20);
                  beta = (double)0.0F;
               case 30:
                  break;
               case 50:
                  printState(50);
                  resid = delsq;
                  double ds = (double)0.0F;
                  double shs = (double)0.0F;

                  for(int i = 0; i < n; ++i) {
                     if (xbdi.getEntry(i) == (double)0.0F) {
                        double d1 = this.trialStepPoint.getEntry(i);
                        resid -= d1 * d1;
                        ds += s.getEntry(i) * this.trialStepPoint.getEntry(i);
                        shs += s.getEntry(i) * hs.getEntry(i);
                     }
                  }

                  if (resid <= (double)0.0F) {
                     state = 90;
                     continue;
                  } else {
                     temp = FastMath.sqrt(stepsq * resid + ds * ds);
                     double blen;
                     if (ds < (double)0.0F) {
                        blen = (temp - ds) / stepsq;
                     } else {
                        blen = resid / (temp + ds);
                     }

                     stplen = blen;
                     if (shs > (double)0.0F) {
                        stplen = FastMath.min(blen, gredsq / shs);
                     }

                     iact = -1;

                     for(int i = 0; i < n; ++i) {
                        if (s.getEntry(i) != (double)0.0F) {
                           xsum = this.trustRegionCenterOffset.getEntry(i) + this.trialStepPoint.getEntry(i);
                           if (s.getEntry(i) > (double)0.0F) {
                              temp = (this.upperDifference.getEntry(i) - xsum) / s.getEntry(i);
                           } else {
                              temp = (this.lowerDifference.getEntry(i) - xsum) / s.getEntry(i);
                           }

                           if (temp < stplen) {
                              stplen = temp;
                              iact = i;
                           }
                        }
                     }

                     double sdec = (double)0.0F;
                     if (stplen > (double)0.0F) {
                        ++iterc;
                        temp = shs / stepsq;
                        if (iact == -1 && temp > (double)0.0F) {
                           crvmin = FastMath.min(crvmin, temp);
                           if (crvmin == (double)-1.0F) {
                              crvmin = temp;
                           }
                        }

                        ggsav = gredsq;
                        gredsq = (double)0.0F;

                        for(int i = 0; i < n; ++i) {
                           gnew.setEntry(i, gnew.getEntry(i) + stplen * hs.getEntry(i));
                           if (xbdi.getEntry(i) == (double)0.0F) {
                              double d1 = gnew.getEntry(i);
                              gredsq += d1 * d1;
                           }

                           this.trialStepPoint.setEntry(i, this.trialStepPoint.getEntry(i) + stplen * s.getEntry(i));
                        }

                        double d1 = stplen * (ggsav - (double)0.5F * stplen * shs);
                        sdec = FastMath.max(d1, (double)0.0F);
                        qred += sdec;
                     }

                     if (iact >= 0) {
                        ++nact;
                        xbdi.setEntry(iact, (double)1.0F);
                        if (s.getEntry(iact) < (double)0.0F) {
                           xbdi.setEntry(iact, (double)-1.0F);
                        }

                        double d1 = this.trialStepPoint.getEntry(iact);
                        delsq -= d1 * d1;
                        if (delsq <= (double)0.0F) {
                           state = 190;
                        } else {
                           state = 20;
                        }
                        continue;
                     } else if (stplen < blen) {
                        if (iterc == itermax) {
                           state = 190;
                        } else if (sdec <= qred * 0.01) {
                           state = 190;
                        } else {
                           beta = gredsq / ggsav;
                           state = 30;
                        }
                        continue;
                     }
                  }
               case 90:
                  printState(90);
                  crvmin = (double)0.0F;
               case 100:
                  break label375;
               case 120:
                  printState(120);
                  ++iterc;
                  temp = gredsq * dredsq - dredg * dredg;
                  if (temp <= qred * 1.0E-4 * qred) {
                     state = 190;
                  } else {
                     temp = FastMath.sqrt(temp);

                     for(int i = 0; i < n; ++i) {
                        if (xbdi.getEntry(i) == (double)0.0F) {
                           s.setEntry(i, (dredg * this.trialStepPoint.getEntry(i) - dredsq * gnew.getEntry(i)) / temp);
                        } else {
                           s.setEntry(i, (double)0.0F);
                        }
                     }

                     sredg = -temp;
                     angbd = (double)1.0F;
                     iact = -1;

                     for(int i = 0; i < n; ++i) {
                        if (xbdi.getEntry(i) == (double)0.0F) {
                           tempa = this.trustRegionCenterOffset.getEntry(i) + this.trialStepPoint.getEntry(i) - this.lowerDifference.getEntry(i);
                           tempb = this.upperDifference.getEntry(i) - this.trustRegionCenterOffset.getEntry(i) - this.trialStepPoint.getEntry(i);
                           if (tempa <= (double)0.0F) {
                              ++nact;
                              xbdi.setEntry(i, (double)-1.0F);
                              state = 100;
                              break;
                           }

                           if (tempb <= (double)0.0F) {
                              ++nact;
                              xbdi.setEntry(i, (double)1.0F);
                              state = 100;
                              break;
                           }

                           double d1 = this.trialStepPoint.getEntry(i);
                           double d2 = s.getEntry(i);
                           double ssq = d1 * d1 + d2 * d2;
                           d1 = this.trustRegionCenterOffset.getEntry(i) - this.lowerDifference.getEntry(i);
                           temp = ssq - d1 * d1;
                           if (temp > (double)0.0F) {
                              temp = FastMath.sqrt(temp) - s.getEntry(i);
                              if (angbd * temp > tempa) {
                                 angbd = tempa / temp;
                                 iact = i;
                                 xsav = (double)-1.0F;
                              }
                           }

                           d1 = this.upperDifference.getEntry(i) - this.trustRegionCenterOffset.getEntry(i);
                           temp = ssq - d1 * d1;
                           if (temp > (double)0.0F) {
                              temp = FastMath.sqrt(temp) + s.getEntry(i);
                              if (angbd * temp > tempb) {
                                 angbd = tempb / temp;
                                 iact = i;
                                 xsav = (double)1.0F;
                              }
                           }
                        }
                     }

                     state = 210;
                  }
                  continue;
               case 150:
                  printState(150);
                  double shs = (double)0.0F;
                  double dhs = (double)0.0F;
                  double dhd = (double)0.0F;

                  for(int i = 0; i < n; ++i) {
                     if (xbdi.getEntry(i) == (double)0.0F) {
                        shs += s.getEntry(i) * hs.getEntry(i);
                        dhs += this.trialStepPoint.getEntry(i) * hs.getEntry(i);
                        dhd += this.trialStepPoint.getEntry(i) * hred.getEntry(i);
                     }
                  }

                  redmax = (double)0.0F;
                  int isav = -1;
                  redsav = (double)0.0F;
                  int iu = (int)(angbd * (double)17.0F + 3.1);

                  for(int i = 0; i < iu; ++i) {
                     angt = angbd * (double)i / (double)iu;
                     double sth = (angt + angt) / ((double)1.0F + angt * angt);
                     temp = shs + angt * (angt * dhd - dhs - dhs);
                     rednew = sth * (angt * dredg - sredg - (double)0.5F * sth * temp);
                     if (rednew > redmax) {
                        redmax = rednew;
                        isav = i;
                        rdprev = redsav;
                     } else if (i == isav + 1) {
                        rdnext = rednew;
                     }

                     redsav = rednew;
                  }

                  if (isav < 0) {
                     state = 190;
                     continue;
                  } else {
                     if (isav < iu) {
                        temp = (rdnext - rdprev) / (redmax + redmax - rdprev - rdnext);
                        angt = angbd * ((double)isav + (double)0.5F * temp) / (double)iu;
                     }

                     double cth = ((double)1.0F - angt * angt) / ((double)1.0F + angt * angt);
                     double sth = (angt + angt) / ((double)1.0F + angt * angt);
                     temp = shs + angt * (angt * dhd - dhs - dhs);
                     double sdec = sth * (angt * dredg - sredg - (double)0.5F * sth * temp);
                     if (sdec <= (double)0.0F) {
                        state = 190;
                        continue;
                     } else {
                        dredg = (double)0.0F;
                        gredsq = (double)0.0F;

                        for(int i = 0; i < n; ++i) {
                           gnew.setEntry(i, gnew.getEntry(i) + (cth - (double)1.0F) * hred.getEntry(i) + sth * hs.getEntry(i));
                           if (xbdi.getEntry(i) == (double)0.0F) {
                              this.trialStepPoint.setEntry(i, cth * this.trialStepPoint.getEntry(i) + sth * s.getEntry(i));
                              dredg += this.trialStepPoint.getEntry(i) * gnew.getEntry(i);
                              double d1 = gnew.getEntry(i);
                              gredsq += d1 * d1;
                           }

                           hred.setEntry(i, cth * hred.getEntry(i) + sth * hs.getEntry(i));
                        }

                        qred += sdec;
                        if (iact >= 0 && isav == iu) {
                           ++nact;
                           xbdi.setEntry(iact, xsav);
                           state = 100;
                           continue;
                        } else if (sdec > qred * 0.01) {
                           state = 120;
                           continue;
                        }
                     }
                  }
               case 190:
                  break label381;
               case 210:
                  printState(210);
                  int ih = 0;

                  for(int j = 0; j < n; ++j) {
                     hs.setEntry(j, (double)0.0F);

                     for(int i = 0; i <= j; ++i) {
                        if (i < j) {
                           hs.setEntry(j, hs.getEntry(j) + this.modelSecondDerivativesValues.getEntry(ih) * s.getEntry(i));
                        }

                        hs.setEntry(i, hs.getEntry(i) + this.modelSecondDerivativesValues.getEntry(ih) * s.getEntry(j));
                        ++ih;
                     }
                  }

                  RealVector tmp = this.interpolationPoints.operate(s).ebeMultiply(this.modelSecondDerivativesParameters);

                  for(int k = 0; k < npt; ++k) {
                     if (this.modelSecondDerivativesParameters.getEntry(k) != (double)0.0F) {
                        for(int i = 0; i < n; ++i) {
                           hs.setEntry(i, hs.getEntry(i) + tmp.getEntry(k) * this.interpolationPoints.getEntry(k, i));
                        }
                     }
                  }

                  if (crvmin != (double)0.0F) {
                     state = 50;
                  } else if (iterc > itcsav) {
                     state = 150;
                  } else {
                     for(int i = 0; i < n; ++i) {
                        hred.setEntry(i, hs.getEntry(i));
                     }

                     state = 120;
                  }
                  continue;
               default:
                  throw new MathIllegalStateException(LocalizedFormats.SIMPLE_MESSAGE, new Object[]{"trsbox"});
            }

            printState(30);
            stepsq = (double)0.0F;

            for(int i = 0; i < n; ++i) {
               if (xbdi.getEntry(i) != (double)0.0F) {
                  s.setEntry(i, (double)0.0F);
               } else if (beta == (double)0.0F) {
                  s.setEntry(i, -gnew.getEntry(i));
               } else {
                  s.setEntry(i, beta * s.getEntry(i) - gnew.getEntry(i));
               }

               double d1 = s.getEntry(i);
               stepsq += d1 * d1;
            }

            if (stepsq == (double)0.0F) {
               state = 190;
            } else {
               if (beta == (double)0.0F) {
                  gredsq = stepsq;
                  itermax = iterc + n - nact;
               }

               if (gredsq * delsq <= qred * 1.0E-4 * qred) {
                  state = 190;
               } else {
                  state = 210;
               }
            }
         }

         printState(100);
         if (nact >= n - 1) {
            state = 190;
         } else {
            dredsq = (double)0.0F;
            dredg = (double)0.0F;
            gredsq = (double)0.0F;

            for(int i = 0; i < n; ++i) {
               if (xbdi.getEntry(i) == (double)0.0F) {
                  double d1 = this.trialStepPoint.getEntry(i);
                  dredsq += d1 * d1;
                  dredg += this.trialStepPoint.getEntry(i) * gnew.getEntry(i);
                  d1 = gnew.getEntry(i);
                  gredsq += d1 * d1;
                  s.setEntry(i, this.trialStepPoint.getEntry(i));
               } else {
                  s.setEntry(i, (double)0.0F);
               }
            }

            itcsav = iterc;
            state = 210;
         }
      }

      printState(190);
      dsq = (double)0.0F;

      for(int i = 0; i < n; ++i) {
         double min = FastMath.min(this.trustRegionCenterOffset.getEntry(i) + this.trialStepPoint.getEntry(i), this.upperDifference.getEntry(i));
         this.newPoint.setEntry(i, FastMath.max(min, this.lowerDifference.getEntry(i)));
         if (xbdi.getEntry(i) == (double)-1.0F) {
            this.newPoint.setEntry(i, this.lowerDifference.getEntry(i));
         }

         if (xbdi.getEntry(i) == (double)1.0F) {
            this.newPoint.setEntry(i, this.upperDifference.getEntry(i));
         }

         this.trialStepPoint.setEntry(i, this.newPoint.getEntry(i) - this.trustRegionCenterOffset.getEntry(i));
         double d1 = this.trialStepPoint.getEntry(i);
         dsq += d1 * d1;
      }

      return new double[]{dsq, crvmin};
   }

   private void update(double beta, double denom, int knew) {
      printMethod();
      int n = this.currentBest.getDimension();
      int npt = this.numberOfInterpolationPoints;
      int nptm = npt - n - 1;
      ArrayRealVector work = new ArrayRealVector(npt + n);
      double ztest = (double)0.0F;

      for(int k = 0; k < npt; ++k) {
         for(int j = 0; j < nptm; ++j) {
            ztest = FastMath.max(ztest, FastMath.abs(this.zMatrix.getEntry(k, j)));
         }
      }

      ztest *= 1.0E-20;

      for(int j = 1; j < nptm; ++j) {
         double d1 = this.zMatrix.getEntry(knew, j);
         if (FastMath.abs(d1) > ztest) {
            double d2 = this.zMatrix.getEntry(knew, 0);
            double d3 = this.zMatrix.getEntry(knew, j);
            double d4 = FastMath.sqrt(d2 * d2 + d3 * d3);
            double d5 = this.zMatrix.getEntry(knew, 0) / d4;
            double d6 = this.zMatrix.getEntry(knew, j) / d4;

            for(int i = 0; i < npt; ++i) {
               double d7 = d5 * this.zMatrix.getEntry(i, 0) + d6 * this.zMatrix.getEntry(i, j);
               this.zMatrix.setEntry(i, j, d5 * this.zMatrix.getEntry(i, j) - d6 * this.zMatrix.getEntry(i, 0));
               this.zMatrix.setEntry(i, 0, d7);
            }
         }

         this.zMatrix.setEntry(knew, j, (double)0.0F);
      }

      for(int i = 0; i < npt; ++i) {
         work.setEntry(i, this.zMatrix.getEntry(knew, 0) * this.zMatrix.getEntry(i, 0));
      }

      double alpha = work.getEntry(knew);
      double tau = this.lagrangeValuesAtNewPoint.getEntry(knew);
      this.lagrangeValuesAtNewPoint.setEntry(knew, this.lagrangeValuesAtNewPoint.getEntry(knew) - (double)1.0F);
      double sqrtDenom = FastMath.sqrt(denom);
      double d1 = tau / sqrtDenom;
      double d2 = this.zMatrix.getEntry(knew, 0) / sqrtDenom;

      for(int i = 0; i < npt; ++i) {
         this.zMatrix.setEntry(i, 0, d1 * this.zMatrix.getEntry(i, 0) - d2 * this.lagrangeValuesAtNewPoint.getEntry(i));
      }

      for(int j = 0; j < n; ++j) {
         int jp = npt + j;
         work.setEntry(jp, this.bMatrix.getEntry(knew, j));
         double d3 = (alpha * this.lagrangeValuesAtNewPoint.getEntry(jp) - tau * work.getEntry(jp)) / denom;
         double d4 = (-beta * work.getEntry(jp) - tau * this.lagrangeValuesAtNewPoint.getEntry(jp)) / denom;

         for(int i = 0; i <= jp; ++i) {
            this.bMatrix.setEntry(i, j, this.bMatrix.getEntry(i, j) + d3 * this.lagrangeValuesAtNewPoint.getEntry(i) + d4 * work.getEntry(i));
            if (i >= npt) {
               this.bMatrix.setEntry(jp, i - npt, this.bMatrix.getEntry(i, j));
            }
         }
      }

   }

   private void setup(double[] lowerBound, double[] upperBound) {
      printMethod();
      double[] init = this.getStartPoint();
      int dimension = init.length;
      if (dimension < 2) {
         throw new NumberIsTooSmallException(dimension, 2, true);
      } else {
         int[] nPointsInterval = new int[]{dimension + 2, (dimension + 2) * (dimension + 1) / 2};
         if (this.numberOfInterpolationPoints >= nPointsInterval[0] && this.numberOfInterpolationPoints <= nPointsInterval[1]) {
            this.boundDifference = new double[dimension];
            double requiredMinDiff = (double)2.0F * this.initialTrustRegionRadius;
            double minDiff = Double.POSITIVE_INFINITY;

            for(int i = 0; i < dimension; ++i) {
               this.boundDifference[i] = upperBound[i] - lowerBound[i];
               minDiff = FastMath.min(minDiff, this.boundDifference[i]);
            }

            if (minDiff < requiredMinDiff) {
               this.initialTrustRegionRadius = minDiff / (double)3.0F;
            }

            this.bMatrix = new Array2DRowRealMatrix(dimension + this.numberOfInterpolationPoints, dimension);
            this.zMatrix = new Array2DRowRealMatrix(this.numberOfInterpolationPoints, this.numberOfInterpolationPoints - dimension - 1);
            this.interpolationPoints = new Array2DRowRealMatrix(this.numberOfInterpolationPoints, dimension);
            this.originShift = new ArrayRealVector(dimension);
            this.fAtInterpolationPoints = new ArrayRealVector(this.numberOfInterpolationPoints);
            this.trustRegionCenterOffset = new ArrayRealVector(dimension);
            this.gradientAtTrustRegionCenter = new ArrayRealVector(dimension);
            this.lowerDifference = new ArrayRealVector(dimension);
            this.upperDifference = new ArrayRealVector(dimension);
            this.modelSecondDerivativesParameters = new ArrayRealVector(this.numberOfInterpolationPoints);
            this.newPoint = new ArrayRealVector(dimension);
            this.alternativeNewPoint = new ArrayRealVector(dimension);
            this.trialStepPoint = new ArrayRealVector(dimension);
            this.lagrangeValuesAtNewPoint = new ArrayRealVector(dimension + this.numberOfInterpolationPoints);
            this.modelSecondDerivativesValues = new ArrayRealVector(dimension * (dimension + 1) / 2);
         } else {
            throw new OutOfRangeException(LocalizedFormats.NUMBER_OF_INTERPOLATION_POINTS, this.numberOfInterpolationPoints, nPointsInterval[0], nPointsInterval[1]);
         }
      }
   }

   private static String caller(int n) {
      Throwable t = new Throwable();
      StackTraceElement[] elements = t.getStackTrace();
      StackTraceElement e = elements[n];
      return e.getMethodName() + " (at line " + e.getLineNumber() + ")";
   }

   private static void printState(int s) {
   }

   private static void printMethod() {
   }

   private static class PathIsExploredException extends RuntimeException {
      private static final long serialVersionUID = 745350979634801853L;
      private static final String PATH_IS_EXPLORED = "If this exception is thrown, just remove it from the code";

      PathIsExploredException() {
         super("If this exception is thrown, just remove it from the code " + BOBYQAOptimizer.caller(3));
      }
   }
}
