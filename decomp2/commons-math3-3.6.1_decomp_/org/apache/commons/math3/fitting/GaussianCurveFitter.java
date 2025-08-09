package org.apache.commons.math3.fitting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.math3.analysis.function.Gaussian;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;
import org.apache.commons.math3.linear.DiagonalMatrix;
import org.apache.commons.math3.util.FastMath;

public class GaussianCurveFitter extends AbstractCurveFitter {
   private static final Gaussian.Parametric FUNCTION = new Gaussian.Parametric() {
      public double value(double x, double... p) {
         double v = Double.POSITIVE_INFINITY;

         try {
            v = super.value(x, p);
         } catch (NotStrictlyPositiveException var7) {
         }

         return v;
      }

      public double[] gradient(double x, double... p) {
         double[] v = new double[]{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};

         try {
            v = super.gradient(x, p);
         } catch (NotStrictlyPositiveException var6) {
         }

         return v;
      }
   };
   private final double[] initialGuess;
   private final int maxIter;

   private GaussianCurveFitter(double[] initialGuess, int maxIter) {
      this.initialGuess = initialGuess;
      this.maxIter = maxIter;
   }

   public static GaussianCurveFitter create() {
      return new GaussianCurveFitter((double[])null, Integer.MAX_VALUE);
   }

   public GaussianCurveFitter withStartPoint(double[] newStart) {
      return new GaussianCurveFitter((double[])(([D)newStart).clone(), this.maxIter);
   }

   public GaussianCurveFitter withMaxIterations(int newMaxIter) {
      return new GaussianCurveFitter(this.initialGuess, newMaxIter);
   }

   protected LeastSquaresProblem getProblem(Collection observations) {
      int len = observations.size();
      double[] target = new double[len];
      double[] weights = new double[len];
      int i = 0;

      for(WeightedObservedPoint obs : observations) {
         target[i] = obs.getY();
         weights[i] = obs.getWeight();
         ++i;
      }

      AbstractCurveFitter.TheoreticalValuesFunction model = new AbstractCurveFitter.TheoreticalValuesFunction(FUNCTION, observations);
      double[] startPoint = this.initialGuess != null ? this.initialGuess : (new ParameterGuesser(observations)).guess();
      return (new LeastSquaresBuilder()).maxEvaluations(Integer.MAX_VALUE).maxIterations(this.maxIter).start(startPoint).target(target).weight(new DiagonalMatrix(weights)).model(model.getModelFunction(), model.getModelFunctionJacobian()).build();
   }

   public static class ParameterGuesser {
      private final double norm;
      private final double mean;
      private final double sigma;

      public ParameterGuesser(Collection observations) {
         if (observations == null) {
            throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY, new Object[0]);
         } else if (observations.size() < 3) {
            throw new NumberIsTooSmallException(observations.size(), 3, true);
         } else {
            List<WeightedObservedPoint> sorted = this.sortObservations(observations);
            double[] params = this.basicGuess((WeightedObservedPoint[])sorted.toArray(new WeightedObservedPoint[0]));
            this.norm = params[0];
            this.mean = params[1];
            this.sigma = params[2];
         }
      }

      public double[] guess() {
         return new double[]{this.norm, this.mean, this.sigma};
      }

      private List sortObservations(Collection unsorted) {
         List<WeightedObservedPoint> observations = new ArrayList(unsorted);
         Comparator<WeightedObservedPoint> cmp = new Comparator() {
            public int compare(WeightedObservedPoint p1, WeightedObservedPoint p2) {
               if (p1 == null && p2 == null) {
                  return 0;
               } else if (p1 == null) {
                  return -1;
               } else if (p2 == null) {
                  return 1;
               } else {
                  int cmpX = Double.compare(p1.getX(), p2.getX());
                  if (cmpX < 0) {
                     return -1;
                  } else if (cmpX > 0) {
                     return 1;
                  } else {
                     int cmpY = Double.compare(p1.getY(), p2.getY());
                     if (cmpY < 0) {
                        return -1;
                     } else if (cmpY > 0) {
                        return 1;
                     } else {
                        int cmpW = Double.compare(p1.getWeight(), p2.getWeight());
                        if (cmpW < 0) {
                           return -1;
                        } else {
                           return cmpW > 0 ? 1 : 0;
                        }
                     }
                  }
               }
            }
         };
         Collections.sort(observations, cmp);
         return observations;
      }

      private double[] basicGuess(WeightedObservedPoint[] points) {
         int maxYIdx = this.findMaxY(points);
         double n = points[maxYIdx].getY();
         double m = points[maxYIdx].getX();

         double fwhmApprox;
         try {
            double halfY = n + (m - n) / (double)2.0F;
            double fwhmX1 = this.interpolateXAtY(points, maxYIdx, -1, halfY);
            double fwhmX2 = this.interpolateXAtY(points, maxYIdx, 1, halfY);
            fwhmApprox = fwhmX2 - fwhmX1;
         } catch (OutOfRangeException var15) {
            fwhmApprox = points[points.length - 1].getX() - points[0].getX();
         }

         double s = fwhmApprox / ((double)2.0F * FastMath.sqrt((double)2.0F * FastMath.log((double)2.0F)));
         return new double[]{n, m, s};
      }

      private int findMaxY(WeightedObservedPoint[] points) {
         int maxYIdx = 0;

         for(int i = 1; i < points.length; ++i) {
            if (points[i].getY() > points[maxYIdx].getY()) {
               maxYIdx = i;
            }
         }

         return maxYIdx;
      }

      private double interpolateXAtY(WeightedObservedPoint[] points, int startIdx, int idxStep, double y) throws OutOfRangeException {
         if (idxStep == 0) {
            throw new ZeroException();
         } else {
            WeightedObservedPoint[] twoPoints = this.getInterpolationPointsForY(points, startIdx, idxStep, y);
            WeightedObservedPoint p1 = twoPoints[0];
            WeightedObservedPoint p2 = twoPoints[1];
            if (p1.getY() == y) {
               return p1.getX();
            } else {
               return p2.getY() == y ? p2.getX() : p1.getX() + (y - p1.getY()) * (p2.getX() - p1.getX()) / (p2.getY() - p1.getY());
            }
         }
      }

      private WeightedObservedPoint[] getInterpolationPointsForY(WeightedObservedPoint[] points, int startIdx, int idxStep, double y) throws OutOfRangeException {
         if (idxStep == 0) {
            throw new ZeroException();
         } else {
            int i = startIdx;

            while(true) {
               if (idxStep < 0) {
                  if (i + idxStep < 0) {
                     break;
                  }
               } else if (i + idxStep >= points.length) {
                  break;
               }

               WeightedObservedPoint p1 = points[i];
               WeightedObservedPoint p2 = points[i + idxStep];
               if (this.isBetween(y, p1.getY(), p2.getY())) {
                  if (idxStep < 0) {
                     return new WeightedObservedPoint[]{p2, p1};
                  }

                  return new WeightedObservedPoint[]{p1, p2};
               }

               i += idxStep;
            }

            throw new OutOfRangeException(y, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
         }
      }

      private boolean isBetween(double value, double boundary1, double boundary2) {
         return value >= boundary1 && value <= boundary2 || value >= boundary2 && value <= boundary1;
      }
   }
}
