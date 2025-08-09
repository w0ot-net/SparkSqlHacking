package org.apache.hadoop.hive.shims;

import org.apache.hadoop.yarn.server.MiniYARNCluster;

public class MiniSparkOnYARNCluster extends MiniYARNCluster {
   public MiniSparkOnYARNCluster(String testName) {
      this(testName, 1, 1);
   }

   public MiniSparkOnYARNCluster(String testName, int numResourceManagers, int numNodeManagers) {
      this(testName, numResourceManagers, numNodeManagers, 1, 1);
   }

   public MiniSparkOnYARNCluster(String testName, int numResourceManagers, int numNodeManagers, int numLocalDirs, int numLogDirs) {
      super(testName, numResourceManagers, numNodeManagers, numLocalDirs, numLogDirs);
   }
}
