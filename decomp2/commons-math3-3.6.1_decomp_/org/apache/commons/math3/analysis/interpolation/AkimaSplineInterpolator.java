package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.Precision;

public class AkimaSplineInterpolator implements UnivariateInterpolator {
   private static final int MINIMUM_NUMBER_POINTS = 5;

   public PolynomialSplineFunction interpolate(double[] xvals, double[] yvals) throws DimensionMismatchException, NumberIsTooSmallException, NonMonotonicSequenceException {
      if (xvals != null && yvals != null) {
         if (xvals.length != yvals.length) {
            throw new DimensionMismatchException(xvals.length, yvals.length);
         } else if (xvals.length < 5) {
            throw new NumberIsTooSmallException(LocalizedFormats.NUMBER_OF_POINTS, xvals.length, 5, true);
         } else {
            MathArrays.checkOrder(xvals);
            int numberOfDiffAndWeightElements = xvals.length - 1;
            double[] differences = new double[numberOfDiffAndWeightElements];
            double[] weights = new double[numberOfDiffAndWeightElements];

            for(int i = 0; i < differences.length; ++i) {
               differences[i] = (yvals[i + 1] - yvals[i]) / (xvals[i + 1] - xvals[i]);
            }

            for(int i = 1; i < weights.length; ++i) {
               weights[i] = FastMath.abs(differences[i] - differences[i - 1]);
            }

            double[] firstDerivatives = new double[xvals.length];

            for(int i = 2; i < firstDerivatives.length - 2; ++i) {
               double wP = weights[i + 1];
               double wM = weights[i - 1];
               if (Precision.equals(wP, (double)0.0F) && Precision.equals(wM, (double)0.0F)) {
                  double xv = xvals[i];
                  double xvP = xvals[i + 1];
                  double xvM = xvals[i - 1];
                  firstDerivatives[i] = ((xvP - xv) * differences[i - 1] + (xv - xvM) * differences[i]) / (xvP - xvM);
               } else {
                  firstDerivatives[i] = (wP * differences[i - 1] + wM * differences[i]) / (wP + wM);
               }
            }

            firstDerivatives[0] = this.differentiateThreePoint(xvals, yvals, 0, 0, 1, 2);
            firstDerivatives[1] = this.differentiateThreePoint(xvals, yvals, 1, 0, 1, 2);
            firstDerivatives[xvals.length - 2] = this.differentiateThreePoint(xvals, yvals, xvals.length - 2, xvals.length - 3, xvals.length - 2, xvals.length - 1);
            firstDerivatives[xvals.length - 1] = this.differentiateThreePoint(xvals, yvals, xvals.length - 1, xvals.length - 3, xvals.length - 2, xvals.length - 1);
            return this.interpolateHermiteSorted(xvals, yvals, firstDerivatives);
         }
      } else {
         throw new NullArgumentException();
      }
   }

   private double differentiateThreePoint(double[] xvals, double[] yvals, int indexOfDifferentiation, int indexOfFirstSample, int indexOfSecondsample, int indexOfThirdSample) {
      double x0 = yvals[indexOfFirstSample];
      double x1 = yvals[indexOfSecondsample];
      double x2 = yvals[indexOfThirdSample];
      double t = xvals[indexOfDifferentiation] - xvals[indexOfFirstSample];
      double t1 = xvals[indexOfSecondsample] - xvals[indexOfFirstSample];
      double t2 = xvals[indexOfThirdSample] - xvals[indexOfFirstSample];
      double a = (x2 - x0 - t2 / t1 * (x1 - x0)) / (t2 * t2 - t1 * t2);
      double b = (x1 - x0 - a * t1 * t1) / t1;
      return (double)2.0F * a * t + b;
   }

   private PolynomialSplineFunction interpolateHermiteSorted(double[] xvals, double[] yvals, double[] firstDerivatives) {
      if (xvals.length != yvals.length) {
         throw new DimensionMismatchException(xvals.length, yvals.length);
      } else if (xvals.length != firstDerivatives.length) {
         throw new DimensionMismatchException(xvals.length, firstDerivatives.length);
      } else {
         int minimumLength = 2;
         if (xvals.length < 2) {
            throw new NumberIsTooSmallException(LocalizedFormats.NUMBER_OF_POINTS, xvals.length, 2, true);
         } else {
            int size = xvals.length - 1;
            PolynomialFunction[] polynomials = new PolynomialFunction[size];
            double[] coefficients = new double[4];

            for(int i = 0; i < polynomials.length; ++i) {
               double w = xvals[i + 1] - xvals[i];
               double w2 = w * w;
               double yv = yvals[i];
               double yvP = yvals[i + 1];
               double fd = firstDerivatives[i];
               double fdP = firstDerivatives[i + 1];
               coefficients[0] = yv;
               coefficients[1] = firstDerivatives[i];
               coefficients[2] = ((double)3.0F * (yvP - yv) / w - (double)2.0F * fd - fdP) / w;
               coefficients[3] = ((double)2.0F * (yv - yvP) / w + fd + fdP) / w2;
               polynomials[i] = new PolynomialFunction(coefficients);
            }

            return new PolynomialSplineFunction(xvals, polynomials);
         }
      }
   }
}
