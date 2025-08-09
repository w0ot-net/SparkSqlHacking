package org.apache.commons.math3.analysis.interpolation;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.random.UnitSphereRandomVectorGenerator;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class InterpolatingMicrosphere {
   private final List microsphere;
   private final List microsphereData;
   private final int dimension;
   private final int size;
   private final double maxDarkFraction;
   private final double darkThreshold;
   private final double background;

   protected InterpolatingMicrosphere(int dimension, int size, double maxDarkFraction, double darkThreshold, double background) {
      if (dimension <= 0) {
         throw new NotStrictlyPositiveException(dimension);
      } else if (size <= 0) {
         throw new NotStrictlyPositiveException(size);
      } else if (!(maxDarkFraction < (double)0.0F) && !(maxDarkFraction > (double)1.0F)) {
         if (darkThreshold < (double)0.0F) {
            throw new NotPositiveException(darkThreshold);
         } else {
            this.dimension = dimension;
            this.size = size;
            this.maxDarkFraction = maxDarkFraction;
            this.darkThreshold = darkThreshold;
            this.background = background;
            this.microsphere = new ArrayList(size);
            this.microsphereData = new ArrayList(size);
         }
      } else {
         throw new OutOfRangeException(maxDarkFraction, 0, 1);
      }
   }

   public InterpolatingMicrosphere(int dimension, int size, double maxDarkFraction, double darkThreshold, double background, UnitSphereRandomVectorGenerator rand) {
      this(dimension, size, maxDarkFraction, darkThreshold, background);

      for(int i = 0; i < size; ++i) {
         this.add(rand.nextVector(), false);
      }

   }

   protected InterpolatingMicrosphere(InterpolatingMicrosphere other) {
      this.dimension = other.dimension;
      this.size = other.size;
      this.maxDarkFraction = other.maxDarkFraction;
      this.darkThreshold = other.darkThreshold;
      this.background = other.background;
      this.microsphere = other.microsphere;
      this.microsphereData = new ArrayList(this.size);

      for(FacetData fd : other.microsphereData) {
         this.microsphereData.add(new FacetData(fd.illumination(), fd.sample()));
      }

   }

   public InterpolatingMicrosphere copy() {
      return new InterpolatingMicrosphere(this);
   }

   public int getDimension() {
      return this.dimension;
   }

   public int getSize() {
      return this.size;
   }

   public double value(double[] point, double[][] samplePoints, double[] sampleValues, double exponent, double noInterpolationTolerance) {
      if (exponent < (double)0.0F) {
         throw new NotPositiveException(exponent);
      } else {
         this.clear();
         int numSamples = samplePoints.length;

         for(int i = 0; i < numSamples; ++i) {
            double[] diff = MathArrays.ebeSubtract(samplePoints[i], point);
            double diffNorm = MathArrays.safeNorm(diff);
            if (FastMath.abs(diffNorm) < noInterpolationTolerance) {
               return sampleValues[i];
            }

            double weight = FastMath.pow(diffNorm, -exponent);
            this.illuminate(diff, sampleValues[i], weight);
         }

         return this.interpolate();
      }
   }

   protected void add(double[] normal, boolean copy) {
      if (this.microsphere.size() >= this.size) {
         throw new MaxCountExceededException(this.size);
      } else if (normal.length > this.dimension) {
         throw new DimensionMismatchException(normal.length, this.dimension);
      } else {
         this.microsphere.add(new Facet(copy ? (double[])(([D)normal).clone() : normal));
         this.microsphereData.add(new FacetData((double)0.0F, (double)0.0F));
      }
   }

   private double interpolate() {
      int darkCount = 0;
      double value = (double)0.0F;
      double totalWeight = (double)0.0F;

      for(FacetData fd : this.microsphereData) {
         double iV = fd.illumination();
         if (iV != (double)0.0F) {
            value += iV * fd.sample();
            totalWeight += iV;
         } else {
            ++darkCount;
         }
      }

      double darkFraction = (double)darkCount / (double)this.size;
      return darkFraction <= this.maxDarkFraction ? value / totalWeight : this.background;
   }

   private void illuminate(double[] sampleDirection, double sampleValue, double weight) {
      for(int i = 0; i < this.size; ++i) {
         double[] n = ((Facet)this.microsphere.get(i)).getNormal();
         double cos = MathArrays.cosAngle(n, sampleDirection);
         if (cos > (double)0.0F) {
            double illumination = cos * weight;
            if (illumination > this.darkThreshold && illumination > ((FacetData)this.microsphereData.get(i)).illumination()) {
               this.microsphereData.set(i, new FacetData(illumination, sampleValue));
            }
         }
      }

   }

   private void clear() {
      for(int i = 0; i < this.size; ++i) {
         this.microsphereData.set(i, new FacetData((double)0.0F, (double)0.0F));
      }

   }

   private static class Facet {
      private final double[] normal;

      Facet(double[] n) {
         this.normal = n;
      }

      public double[] getNormal() {
         return this.normal;
      }
   }

   private static class FacetData {
      private final double illumination;
      private final double sample;

      FacetData(double illumination, double sample) {
         this.illumination = illumination;
         this.sample = sample;
      }

      public double illumination() {
         return this.illumination;
      }

      public double sample() {
         return this.sample;
      }
   }
}
