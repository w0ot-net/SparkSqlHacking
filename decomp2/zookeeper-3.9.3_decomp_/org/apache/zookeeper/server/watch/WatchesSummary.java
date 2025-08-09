package org.apache.zookeeper.server.watch;

import java.util.LinkedHashMap;
import java.util.Map;

public class WatchesSummary {
   public static final String KEY_NUM_CONNECTIONS = "num_connections";
   public static final String KEY_NUM_PATHS = "num_paths";
   public static final String KEY_NUM_TOTAL_WATCHES = "num_total_watches";
   private final int numConnections;
   private final int numPaths;
   private final int totalWatches;

   WatchesSummary(int numConnections, int numPaths, int totalWatches) {
      this.numConnections = numConnections;
      this.numPaths = numPaths;
      this.totalWatches = totalWatches;
   }

   public int getNumConnections() {
      return this.numConnections;
   }

   public int getNumPaths() {
      return this.numPaths;
   }

   public int getTotalWatches() {
      return this.totalWatches;
   }

   public Map toMap() {
      Map<String, Object> summary = new LinkedHashMap();
      summary.put("num_connections", this.numConnections);
      summary.put("num_paths", this.numPaths);
      summary.put("num_total_watches", this.totalWatches);
      return summary;
   }
}
