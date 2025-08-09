package org.apache.commons.math3.stat.clustering;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.MathUtils;

/** @deprecated */
@Deprecated
public class DBSCANClusterer {
   private final double eps;
   private final int minPts;

   public DBSCANClusterer(double eps, int minPts) throws NotPositiveException {
      if (eps < (double)0.0F) {
         throw new NotPositiveException(eps);
      } else if (minPts < 0) {
         throw new NotPositiveException(minPts);
      } else {
         this.eps = eps;
         this.minPts = minPts;
      }
   }

   public double getEps() {
      return this.eps;
   }

   public int getMinPts() {
      return this.minPts;
   }

   public List cluster(Collection points) throws NullArgumentException {
      MathUtils.checkNotNull(points);
      List<Cluster<T>> clusters = new ArrayList();
      Map<Clusterable<T>, PointStatus> visited = new HashMap();

      for(Clusterable point : points) {
         if (visited.get(point) == null) {
            List<T> neighbors = this.getNeighbors(point, points);
            if (neighbors.size() >= this.minPts) {
               Cluster<T> cluster = new Cluster((Clusterable)null);
               clusters.add(this.expandCluster(cluster, point, neighbors, points, visited));
            } else {
               visited.put(point, DBSCANClusterer.PointStatus.NOISE);
            }
         }
      }

      return clusters;
   }

   private Cluster expandCluster(Cluster cluster, Clusterable point, List neighbors, Collection points, Map visited) {
      cluster.addPoint(point);
      visited.put(point, DBSCANClusterer.PointStatus.PART_OF_CLUSTER);
      List<T> seeds = new ArrayList(neighbors);

      for(int index = 0; index < seeds.size(); ++index) {
         T current = (T)((Clusterable)seeds.get(index));
         PointStatus pStatus = (PointStatus)visited.get(current);
         if (pStatus == null) {
            List<T> currentNeighbors = this.getNeighbors(current, points);
            if (currentNeighbors.size() >= this.minPts) {
               seeds = this.merge(seeds, currentNeighbors);
            }
         }

         if (pStatus != DBSCANClusterer.PointStatus.PART_OF_CLUSTER) {
            visited.put(current, DBSCANClusterer.PointStatus.PART_OF_CLUSTER);
            cluster.addPoint(current);
         }
      }

      return cluster;
   }

   private List getNeighbors(Clusterable point, Collection points) {
      List<T> neighbors = new ArrayList();

      for(Clusterable neighbor : points) {
         if (point != neighbor && neighbor.distanceFrom(point) <= this.eps) {
            neighbors.add(neighbor);
         }
      }

      return neighbors;
   }

   private List merge(List one, List two) {
      Set<T> oneSet = new HashSet(one);

      for(Clusterable item : two) {
         if (!oneSet.contains(item)) {
            one.add(item);
         }
      }

      return one;
   }

   private static enum PointStatus {
      NOISE,
      PART_OF_CLUSTER;
   }
}
