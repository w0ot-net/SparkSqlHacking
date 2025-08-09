package org.apache.ivy.core.sort;

import org.apache.ivy.plugins.circular.CircularDependencyStrategy;
import org.apache.ivy.plugins.version.VersionMatcher;

public class SimpleSortEngineSettings implements SortEngineSettings {
   private CircularDependencyStrategy circularStrategy;
   private VersionMatcher versionMatcher;

   public CircularDependencyStrategy getCircularDependencyStrategy() {
      return this.circularStrategy;
   }

   public VersionMatcher getVersionMatcher() {
      return this.versionMatcher;
   }

   public void setCircularDependencyStrategy(CircularDependencyStrategy strategy) {
      this.circularStrategy = strategy;
   }

   public void setVersionMatcher(VersionMatcher matcher) {
      this.versionMatcher = matcher;
   }
}
