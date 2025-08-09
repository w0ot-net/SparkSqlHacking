package org.apache.commons.math3.analysis.interpolation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.random.UnitSphereRandomVectorGenerator;
import org.apache.commons.math3.util.FastMath;

/** @deprecated */
@Deprecated
public class MicrosphereInterpolatingFunction implements MultivariateFunction {
   private final int dimension;
   private final List microsphere;
   private final double brightnessExponent;
   private final Map samples;

   public MicrosphereInterpolatingFunction(double[][] xval, double[] yval, int brightnessExponent, int microsphereElements, UnitSphereRandomVectorGenerator rand) throws DimensionMismatchException, NoDataException, NullArgumentException {
      if (xval != null && yval != null) {
         if (xval.length == 0) {
            throw new NoDataException();
         } else if (xval.length != yval.length) {
            throw new DimensionMismatchException(xval.length, yval.length);
         } else if (xval[0] == null) {
            throw new NullArgumentException();
         } else {
            this.dimension = xval[0].length;
            this.brightnessExponent = (double)brightnessExponent;
            this.samples = new HashMap(yval.length);

            for(int i = 0; i < xval.length; ++i) {
               double[] xvalI = xval[i];
               if (xvalI == null) {
                  throw new NullArgumentException();
               }

               if (xvalI.length != this.dimension) {
                  throw new DimensionMismatchException(xvalI.length, this.dimension);
               }

               this.samples.put(new ArrayRealVector(xvalI), yval[i]);
            }

            this.microsphere = new ArrayList(microsphereElements);

            for(int i = 0; i < microsphereElements; ++i) {
               this.microsphere.add(new MicrosphereSurfaceElement(rand.nextVector()));
            }

         }
      } else {
         throw new NullArgumentException();
      }
   }

   public double value(double[] point) throws DimensionMismatchException {
      RealVector p = new ArrayRealVector(point);

      for(MicrosphereSurfaceElement md : this.microsphere) {
         md.reset();
      }

      for(Map.Entry sd : this.samples.entrySet()) {
         RealVector diff = ((RealVector)sd.getKey()).subtract(p);
         double diffNorm = diff.getNorm();
         if (FastMath.abs(diffNorm) < FastMath.ulp((double)1.0F)) {
            return (Double)sd.getValue();
         }

         for(MicrosphereSurfaceElement md : this.microsphere) {
            double w = FastMath.pow(diffNorm, -this.brightnessExponent);
            md.store(this.cosAngle(diff, md.normal()) * w, sd);
         }
      }

      double value = (double)0.0F;
      double totalWeight = (double)0.0F;

      for(MicrosphereSurfaceElement md : this.microsphere) {
         double iV = md.illumination();
         Map.Entry<RealVector, Double> sd = md.sample();
         if (sd != null) {
            value += iV * (Double)sd.getValue();
            totalWeight += iV;
         }
      }

      return value / totalWeight;
   }

   private double cosAngle(RealVector v, RealVector w) {
      return v.dotProduct(w) / (v.getNorm() * w.getNorm());
   }

   private static class MicrosphereSurfaceElement {
      private final RealVector normal;
      private double brightestIllumination;
      private Map.Entry brightestSample;

      MicrosphereSurfaceElement(double[] n) {
         this.normal = new ArrayRealVector(n);
      }

      RealVector normal() {
         return this.normal;
      }

      void reset() {
         this.brightestIllumination = (double)0.0F;
         this.brightestSample = null;
      }

      void store(double illuminationFromSample, Map.Entry sample) {
         if (illuminationFromSample > this.brightestIllumination) {
            this.brightestIllumination = illuminationFromSample;
            this.brightestSample = sample;
         }

      }

      double illumination() {
         return this.brightestIllumination;
      }

      Map.Entry sample() {
         return this.brightestSample;
      }
   }
}
