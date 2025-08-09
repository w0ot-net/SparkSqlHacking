package org.apache.commons.math3.filter;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

public class DefaultMeasurementModel implements MeasurementModel {
   private RealMatrix measurementMatrix;
   private RealMatrix measurementNoise;

   public DefaultMeasurementModel(double[][] measMatrix, double[][] measNoise) throws NullArgumentException, NoDataException, DimensionMismatchException {
      this((RealMatrix)(new Array2DRowRealMatrix(measMatrix)), (RealMatrix)(new Array2DRowRealMatrix(measNoise)));
   }

   public DefaultMeasurementModel(RealMatrix measMatrix, RealMatrix measNoise) {
      this.measurementMatrix = measMatrix;
      this.measurementNoise = measNoise;
   }

   public RealMatrix getMeasurementMatrix() {
      return this.measurementMatrix;
   }

   public RealMatrix getMeasurementNoise() {
      return this.measurementNoise;
   }
}
