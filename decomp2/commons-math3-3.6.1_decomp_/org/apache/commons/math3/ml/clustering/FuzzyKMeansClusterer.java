package org.apache.commons.math3.ml.clustering;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.distance.EuclideanDistance;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

public class FuzzyKMeansClusterer extends Clusterer {
   private static final double DEFAULT_EPSILON = 0.001;
   private final int k;
   private final int maxIterations;
   private final double fuzziness;
   private final double epsilon;
   private final RandomGenerator random;
   private double[][] membershipMatrix;
   private List points;
   private List clusters;

   public FuzzyKMeansClusterer(int k, double fuzziness) throws NumberIsTooSmallException {
      this(k, fuzziness, -1, new EuclideanDistance());
   }

   public FuzzyKMeansClusterer(int k, double fuzziness, int maxIterations, DistanceMeasure measure) throws NumberIsTooSmallException {
      this(k, fuzziness, maxIterations, measure, 0.001, new JDKRandomGenerator());
   }

   public FuzzyKMeansClusterer(int k, double fuzziness, int maxIterations, DistanceMeasure measure, double epsilon, RandomGenerator random) throws NumberIsTooSmallException {
      super(measure);
      if (fuzziness <= (double)1.0F) {
         throw new NumberIsTooSmallException(fuzziness, (double)1.0F, false);
      } else {
         this.k = k;
         this.fuzziness = fuzziness;
         this.maxIterations = maxIterations;
         this.epsilon = epsilon;
         this.random = random;
         this.membershipMatrix = (double[][])null;
         this.points = null;
         this.clusters = null;
      }
   }

   public int getK() {
      return this.k;
   }

   public double getFuzziness() {
      return this.fuzziness;
   }

   public int getMaxIterations() {
      return this.maxIterations;
   }

   public double getEpsilon() {
      return this.epsilon;
   }

   public RandomGenerator getRandomGenerator() {
      return this.random;
   }

   public RealMatrix getMembershipMatrix() {
      if (this.membershipMatrix == null) {
         throw new MathIllegalStateException();
      } else {
         return MatrixUtils.createRealMatrix(this.membershipMatrix);
      }
   }

   public List getDataPoints() {
      return this.points;
   }

   public List getClusters() {
      return this.clusters;
   }

   public double getObjectiveFunctionValue() {
      if (this.points != null && this.clusters != null) {
         int i = 0;
         double objFunction = (double)0.0F;

         for(Clusterable point : this.points) {
            int j = 0;

            for(CentroidCluster cluster : this.clusters) {
               double dist = this.distance(point, cluster.getCenter());
               objFunction += dist * dist * FastMath.pow(this.membershipMatrix[i][j], this.fuzziness);
               ++j;
            }

            ++i;
         }

         return objFunction;
      } else {
         throw new MathIllegalStateException();
      }
   }

   public List cluster(Collection dataPoints) throws MathIllegalArgumentException {
      MathUtils.checkNotNull(dataPoints);
      int size = dataPoints.size();
      if (size < this.k) {
         throw new NumberIsTooSmallException(size, this.k, false);
      } else {
         this.points = Collections.unmodifiableList(new ArrayList(dataPoints));
         this.clusters = new ArrayList();
         this.membershipMatrix = new double[size][this.k];
         double[][] oldMatrix = new double[size][this.k];
         if (size == 0) {
            return this.clusters;
         } else {
            this.initializeMembershipMatrix();
            int pointDimension = ((Clusterable)this.points.get(0)).getPoint().length;

            for(int i = 0; i < this.k; ++i) {
               this.clusters.add(new CentroidCluster(new DoublePoint(new double[pointDimension])));
            }

            int iteration = 0;
            int max = this.maxIterations < 0 ? Integer.MAX_VALUE : this.maxIterations;
            double difference = (double)0.0F;

            do {
               this.saveMembershipMatrix(oldMatrix);
               this.updateClusterCenters();
               this.updateMembershipMatrix();
               difference = this.calculateMaxMembershipChange(oldMatrix);
               if (!(difference > this.epsilon)) {
                  break;
               }

               ++iteration;
            } while(iteration < max);

            return this.clusters;
         }
      }
   }

   private void updateClusterCenters() {
      int j = 0;
      List<CentroidCluster<T>> newClusters = new ArrayList(this.k);

      for(CentroidCluster cluster : this.clusters) {
         Clusterable center = cluster.getCenter();
         int i = 0;
         double[] arr = new double[center.getPoint().length];
         double sum = (double)0.0F;

         for(Clusterable point : this.points) {
            double u = FastMath.pow(this.membershipMatrix[i][j], this.fuzziness);
            double[] pointArr = point.getPoint();

            for(int idx = 0; idx < arr.length; ++idx) {
               arr[idx] += u * pointArr[idx];
            }

            sum += u;
            ++i;
         }

         MathArrays.scaleInPlace((double)1.0F / sum, arr);
         newClusters.add(new CentroidCluster(new DoublePoint(arr)));
         ++j;
      }

      this.clusters.clear();
      this.clusters = newClusters;
   }

   private void updateMembershipMatrix() {
      for(int i = 0; i < this.points.size(); ++i) {
         T point = (T)((Clusterable)this.points.get(i));
         double maxMembership = Double.MIN_VALUE;
         int newCluster = -1;

         for(int j = 0; j < this.clusters.size(); ++j) {
            double sum = (double)0.0F;
            double distA = FastMath.abs(this.distance(point, ((CentroidCluster)this.clusters.get(j)).getCenter()));
            if (distA != (double)0.0F) {
               for(CentroidCluster c : this.clusters) {
                  double distB = FastMath.abs(this.distance(point, c.getCenter()));
                  if (distB == (double)0.0F) {
                     sum = Double.POSITIVE_INFINITY;
                     break;
                  }

                  sum += FastMath.pow(distA / distB, (double)2.0F / (this.fuzziness - (double)1.0F));
               }
            }

            double membership;
            if (sum == (double)0.0F) {
               membership = (double)1.0F;
            } else if (sum == Double.POSITIVE_INFINITY) {
               membership = (double)0.0F;
            } else {
               membership = (double)1.0F / sum;
            }

            this.membershipMatrix[i][j] = membership;
            if (this.membershipMatrix[i][j] > maxMembership) {
               maxMembership = this.membershipMatrix[i][j];
               newCluster = j;
            }
         }

         ((CentroidCluster)this.clusters.get(newCluster)).addPoint(point);
      }

   }

   private void initializeMembershipMatrix() {
      for(int i = 0; i < this.points.size(); ++i) {
         for(int j = 0; j < this.k; ++j) {
            this.membershipMatrix[i][j] = this.random.nextDouble();
         }

         this.membershipMatrix[i] = MathArrays.normalizeArray(this.membershipMatrix[i], (double)1.0F);
      }

   }

   private double calculateMaxMembershipChange(double[][] matrix) {
      double maxMembership = (double)0.0F;

      for(int i = 0; i < this.points.size(); ++i) {
         for(int j = 0; j < this.clusters.size(); ++j) {
            double v = FastMath.abs(this.membershipMatrix[i][j] - matrix[i][j]);
            maxMembership = FastMath.max(v, maxMembership);
         }
      }

      return maxMembership;
   }

   private void saveMembershipMatrix(double[][] matrix) {
      for(int i = 0; i < this.points.size(); ++i) {
         System.arraycopy(this.membershipMatrix[i], 0, matrix[i], 0, this.clusters.size());
      }

   }
}
