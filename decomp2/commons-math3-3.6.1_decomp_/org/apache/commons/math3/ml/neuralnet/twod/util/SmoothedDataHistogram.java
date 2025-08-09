package org.apache.commons.math3.ml.neuralnet.twod.util;

import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.neuralnet.MapUtils;
import org.apache.commons.math3.ml.neuralnet.Neuron;
import org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D;

public class SmoothedDataHistogram implements MapDataVisualization {
   private final int smoothingBins;
   private final DistanceMeasure distance;
   private final double membershipNormalization;

   public SmoothedDataHistogram(int smoothingBins, DistanceMeasure distance) {
      this.smoothingBins = smoothingBins;
      this.distance = distance;
      double sum = (double)0.0F;

      for(int i = 0; i < smoothingBins; ++i) {
         sum += (double)(smoothingBins - i);
      }

      this.membershipNormalization = (double)1.0F / sum;
   }

   public double[][] computeImage(NeuronSquareMesh2D map, Iterable data) {
      int nR = map.getNumberOfRows();
      int nC = map.getNumberOfColumns();
      int mapSize = nR * nC;
      if (mapSize < this.smoothingBins) {
         throw new NumberIsTooSmallException(mapSize, this.smoothingBins, true);
      } else {
         LocationFinder finder = new LocationFinder(map);
         double[][] histo = new double[nR][nC];

         for(double[] sample : data) {
            Neuron[] sorted = MapUtils.sort(sample, map.getNetwork(), this.distance);

            for(int i = 0; i < this.smoothingBins; ++i) {
               LocationFinder.Location loc = finder.getLocation(sorted[i]);
               int row = loc.getRow();
               int col = loc.getColumn();
               histo[row][col] += (double)(this.smoothingBins - i) * this.membershipNormalization;
            }
         }

         return histo;
      }
   }
}
