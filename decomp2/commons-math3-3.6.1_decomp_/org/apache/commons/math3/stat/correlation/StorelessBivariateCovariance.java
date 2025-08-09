package org.apache.commons.math3.stat.correlation;

import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

class StorelessBivariateCovariance {
   private double meanX;
   private double meanY;
   private double n;
   private double covarianceNumerator;
   private boolean biasCorrected;

   StorelessBivariateCovariance() {
      this(true);
   }

   StorelessBivariateCovariance(boolean biasCorrection) {
      this.meanX = this.meanY = (double)0.0F;
      this.n = (double)0.0F;
      this.covarianceNumerator = (double)0.0F;
      this.biasCorrected = biasCorrection;
   }

   public void increment(double x, double y) {
      ++this.n;
      double deltaX = x - this.meanX;
      double deltaY = y - this.meanY;
      this.meanX += deltaX / this.n;
      this.meanY += deltaY / this.n;
      this.covarianceNumerator += (this.n - (double)1.0F) / this.n * deltaX * deltaY;
   }

   public void append(StorelessBivariateCovariance cov) {
      double oldN = this.n;
      this.n += cov.n;
      double deltaX = cov.meanX - this.meanX;
      double deltaY = cov.meanY - this.meanY;
      this.meanX += deltaX * cov.n / this.n;
      this.meanY += deltaY * cov.n / this.n;
      this.covarianceNumerator += cov.covarianceNumerator + oldN * cov.n / this.n * deltaX * deltaY;
   }

   public double getN() {
      return this.n;
   }

   public double getResult() throws NumberIsTooSmallException {
      if (this.n < (double)2.0F) {
         throw new NumberIsTooSmallException(LocalizedFormats.INSUFFICIENT_DIMENSION, this.n, 2, true);
      } else {
         return this.biasCorrected ? this.covarianceNumerator / (this.n - (double)1.0F) : this.covarianceNumerator / this.n;
      }
   }
}
