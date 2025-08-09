package org.apache.ivy.ant;

import org.apache.ivy.core.cache.RepositoryCacheManager;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.tools.ant.BuildException;

public class IvyCleanCache extends IvyTask {
   public static final String ALL = "*";
   public static final String NONE = "NONE";
   private boolean resolution = true;
   private String cache = "*";

   public String getCache() {
      return this.cache;
   }

   public void setCache(String cache) {
      this.cache = cache;
   }

   public boolean isResolution() {
      return this.resolution;
   }

   public void setResolution(boolean resolution) {
      this.resolution = resolution;
   }

   public void doExecute() throws BuildException {
      IvySettings settings = this.getIvyInstance().getSettings();
      if (this.isResolution()) {
         settings.getResolutionCacheManager().clean();
      }

      if ("*".equals(this.getCache())) {
         for(RepositoryCacheManager cache : settings.getRepositoryCacheManagers()) {
            cache.clean();
         }
      } else if (!"NONE".equals(this.getCache())) {
         RepositoryCacheManager cache = settings.getRepositoryCacheManager(this.getCache());
         if (cache == null) {
            throw new BuildException("unknown cache '" + this.getCache() + "'");
         }

         cache.clean();
      }

   }
}
