package org.apache.curator.framework.recipes.locks;

public class PredicateResults {
   private final boolean getsTheLock;
   private final String pathToWatch;

   public PredicateResults(String pathToWatch, boolean getsTheLock) {
      this.pathToWatch = pathToWatch;
      this.getsTheLock = getsTheLock;
   }

   public String getPathToWatch() {
      return this.pathToWatch;
   }

   public boolean getsTheLock() {
      return this.getsTheLock;
   }
}
