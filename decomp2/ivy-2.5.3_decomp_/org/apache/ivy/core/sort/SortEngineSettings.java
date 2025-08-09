package org.apache.ivy.core.sort;

import org.apache.ivy.plugins.circular.CircularDependencyStrategy;
import org.apache.ivy.plugins.version.VersionMatcher;

public interface SortEngineSettings {
   CircularDependencyStrategy getCircularDependencyStrategy();

   VersionMatcher getVersionMatcher();
}
