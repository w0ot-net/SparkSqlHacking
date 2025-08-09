package org.apache.commons.math3.stat.clustering;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.util.MathUtils;

/** @deprecated */
@Deprecated
public class KMeansPlusPlusClusterer {
   private final Random random;
   private final EmptyClusterStrategy emptyStrategy;

   public KMeansPlusPlusClusterer(Random random) {
      this(random, KMeansPlusPlusClusterer.EmptyClusterStrategy.LARGEST_VARIANCE);
   }

   public KMeansPlusPlusClusterer(Random random, EmptyClusterStrategy emptyStrategy) {
      this.random = random;
      this.emptyStrategy = emptyStrategy;
   }

   public List cluster(Collection points, int k, int numTrials, int maxIterationsPerTrial) throws MathIllegalArgumentException, ConvergenceException {
      List<Cluster<T>> best = null;
      double bestVarianceSum = Double.POSITIVE_INFINITY;

      for(int i = 0; i < numTrials; ++i) {
         List<Cluster<T>> clusters = this.cluster(points, k, maxIterationsPerTrial);
         double varianceSum = (double)0.0F;

         for(Cluster cluster : clusters) {
            if (!cluster.getPoints().isEmpty()) {
               T center = (T)cluster.getCenter();
               Variance stat = new Variance();

               for(Clusterable point : cluster.getPoints()) {
                  stat.increment(point.distanceFrom(center));
               }

               varianceSum += stat.getResult();
            }
         }

         if (varianceSum <= bestVarianceSum) {
            best = clusters;
            bestVarianceSum = varianceSum;
         }
      }

      return best;
   }

   public List cluster(Collection points, int k, int maxIterations) throws MathIllegalArgumentException, ConvergenceException {
      MathUtils.checkNotNull(points);
      if (points.size() < k) {
         throw new NumberIsTooSmallException(points.size(), k, false);
      } else {
         List<Cluster<T>> clusters = chooseInitialCenters(points, k, this.random);
         int[] assignments = new int[points.size()];
         assignPointsToClusters(clusters, points, assignments);
         int max = maxIterations < 0 ? Integer.MAX_VALUE : maxIterations;

         for(int count = 0; count < max; ++count) {
            boolean emptyCluster = false;
            List<Cluster<T>> newClusters = new ArrayList();

            for(Cluster cluster : clusters) {
               T newCenter;
               if (cluster.getPoints().isEmpty()) {
                  switch (this.emptyStrategy) {
                     case LARGEST_VARIANCE:
                        newCenter = (T)this.getPointFromLargestVarianceCluster(clusters);
                        break;
                     case LARGEST_POINTS_NUMBER:
                        newCenter = (T)this.getPointFromLargestNumberCluster(clusters);
                        break;
                     case FARTHEST_POINT:
                        newCenter = (T)this.getFarthestPoint(clusters);
                        break;
                     default:
                        throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]);
                  }

                  emptyCluster = true;
               } else {
                  newCenter = (T)((Clusterable)cluster.getCenter().centroidOf(cluster.getPoints()));
               }

               newClusters.add(new Cluster(newCenter));
            }

            int changes = assignPointsToClusters(newClusters, points, assignments);
            clusters = newClusters;
            if (changes == 0 && !emptyCluster) {
               return newClusters;
            }
         }

         return clusters;
      }
   }

   private static int assignPointsToClusters(List clusters, Collection points, int[] assignments) {
      int assignedDifferently = 0;
      int pointIndex = 0;

      for(Clusterable p : points) {
         int clusterIndex = getNearestCluster(clusters, p);
         if (clusterIndex != assignments[pointIndex]) {
            ++assignedDifferently;
         }

         Cluster<T> cluster = (Cluster)clusters.get(clusterIndex);
         cluster.addPoint(p);
         assignments[pointIndex++] = clusterIndex;
      }

      return assignedDifferently;
   }

   private static List chooseInitialCenters(Collection points, int k, Random random) {
      List<T> pointList = Collections.unmodifiableList(new ArrayList(points));
      int numPoints = pointList.size();
      boolean[] taken = new boolean[numPoints];
      List<Cluster<T>> resultSet = new ArrayList();
      int firstPointIndex = random.nextInt(numPoints);
      T firstPoint = (T)((Clusterable)pointList.get(firstPointIndex));
      resultSet.add(new Cluster(firstPoint));
      taken[firstPointIndex] = true;
      double[] minDistSquared = new double[numPoints];

      for(int i = 0; i < numPoints; ++i) {
         if (i != firstPointIndex) {
            double d = firstPoint.distanceFrom(pointList.get(i));
            minDistSquared[i] = d * d;
         }
      }

      while(resultSet.size() < k) {
         double distSqSum = (double)0.0F;

         for(int i = 0; i < numPoints; ++i) {
            if (!taken[i]) {
               distSqSum += minDistSquared[i];
            }
         }

         double r = random.nextDouble() * distSqSum;
         int nextPointIndex = -1;
         double sum = (double)0.0F;

         for(int i = 0; i < numPoints; ++i) {
            if (!taken[i]) {
               sum += minDistSquared[i];
               if (sum >= r) {
                  nextPointIndex = i;
                  break;
               }
            }
         }

         if (nextPointIndex == -1) {
            for(int i = numPoints - 1; i >= 0; --i) {
               if (!taken[i]) {
                  nextPointIndex = i;
                  break;
               }
            }
         }

         if (nextPointIndex < 0) {
            break;
         }

         T p = (T)((Clusterable)pointList.get(nextPointIndex));
         resultSet.add(new Cluster(p));
         taken[nextPointIndex] = true;
         if (resultSet.size() < k) {
            for(int j = 0; j < numPoints; ++j) {
               if (!taken[j]) {
                  double d = p.distanceFrom(pointList.get(j));
                  double d2 = d * d;
                  if (d2 < minDistSquared[j]) {
                     minDistSquared[j] = d2;
                  }
               }
            }
         }
      }

      return resultSet;
   }

   private Clusterable getPointFromLargestVarianceCluster(Collection clusters) throws ConvergenceException {
      double maxVariance = Double.NEGATIVE_INFINITY;
      Cluster<T> selected = null;

      for(Cluster cluster : clusters) {
         if (!cluster.getPoints().isEmpty()) {
            T center = (T)cluster.getCenter();
            Variance stat = new Variance();

            for(Clusterable point : cluster.getPoints()) {
               stat.increment(point.distanceFrom(center));
            }

            double variance = stat.getResult();
            if (variance > maxVariance) {
               maxVariance = variance;
               selected = cluster;
            }
         }
      }

      if (selected == null) {
         throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]);
      } else {
         List<T> selectedPoints = selected.getPoints();
         return (Clusterable)selectedPoints.remove(this.random.nextInt(selectedPoints.size()));
      }
   }

   private Clusterable getPointFromLargestNumberCluster(Collection clusters) throws ConvergenceException {
      int maxNumber = 0;
      Cluster<T> selected = null;

      for(Cluster cluster : clusters) {
         int number = cluster.getPoints().size();
         if (number > maxNumber) {
            maxNumber = number;
            selected = cluster;
         }
      }

      if (selected == null) {
         throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]);
      } else {
         List<T> selectedPoints = selected.getPoints();
         return (Clusterable)selectedPoints.remove(this.random.nextInt(selectedPoints.size()));
      }
   }

   private Clusterable getFarthestPoint(Collection clusters) throws ConvergenceException {
      double maxDistance = Double.NEGATIVE_INFINITY;
      Cluster<T> selectedCluster = null;
      int selectedPoint = -1;

      for(Cluster cluster : clusters) {
         T center = (T)cluster.getCenter();
         List<T> points = cluster.getPoints();

         for(int i = 0; i < points.size(); ++i) {
            double distance = ((Clusterable)points.get(i)).distanceFrom(center);
            if (distance > maxDistance) {
               maxDistance = distance;
               selectedCluster = cluster;
               selectedPoint = i;
            }
         }
      }

      if (selectedCluster == null) {
         throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]);
      } else {
         return (Clusterable)selectedCluster.getPoints().remove(selectedPoint);
      }
   }

   private static int getNearestCluster(Collection clusters, Clusterable point) {
      double minDistance = Double.MAX_VALUE;
      int clusterIndex = 0;
      int minCluster = 0;

      for(Cluster c : clusters) {
         double distance = point.distanceFrom(c.getCenter());
         if (distance < minDistance) {
            minDistance = distance;
            minCluster = clusterIndex;
         }

         ++clusterIndex;
      }

      return minCluster;
   }

   public static enum EmptyClusterStrategy {
      LARGEST_VARIANCE,
      LARGEST_POINTS_NUMBER,
      FARTHEST_POINT,
      ERROR;
   }
}
