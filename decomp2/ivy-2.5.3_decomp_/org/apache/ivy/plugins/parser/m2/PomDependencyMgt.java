package org.apache.ivy.plugins.parser.m2;

import java.util.List;

public interface PomDependencyMgt {
   String getGroupId();

   String getArtifactId();

   String getVersion();

   String getScope();

   List getExcludedModules();
}
