package org.apache.ivy.plugins.parser.m2;

import java.util.List;
import org.apache.ivy.core.module.id.ModuleId;

public class DefaultPomDependencyMgt implements PomDependencyMgt {
   private String groupId;
   private String artifactId;
   private String version;
   private String scope;
   private List excludedModules;

   public DefaultPomDependencyMgt(String groupId, String artifactId, String version, String scope, List excludedModules) {
      this.groupId = groupId;
      this.artifactId = artifactId;
      this.version = version;
      this.scope = scope;
      this.excludedModules = excludedModules;
   }

   public String getScope() {
      return this.scope;
   }

   public String getGroupId() {
      return this.groupId;
   }

   public String getArtifactId() {
      return this.artifactId;
   }

   public String getVersion() {
      return this.version;
   }

   public List getExcludedModules() {
      return this.excludedModules;
   }
}
