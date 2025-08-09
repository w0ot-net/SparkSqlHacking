package org.apache.ivy.plugins.resolver.util;

import org.apache.ivy.plugins.latest.ArtifactInfo;
import org.apache.ivy.plugins.repository.Resource;

public class ResolvedResource implements ArtifactInfo {
   private Resource res;
   private String rev;

   public ResolvedResource(Resource res, String rev) {
      this.res = res;
      this.rev = rev;
   }

   public String getRevision() {
      return this.rev;
   }

   public Resource getResource() {
      return this.res;
   }

   public String toString() {
      return this.res + " (" + this.rev + ")";
   }

   public long getLastModified() {
      return this.getResource().getLastModified();
   }
}
