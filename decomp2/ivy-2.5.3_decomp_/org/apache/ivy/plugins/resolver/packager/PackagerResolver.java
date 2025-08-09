package org.apache.ivy.plugins.resolver.packager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.DefaultArtifact;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.plugins.resolver.URLResolver;
import org.apache.ivy.plugins.resolver.util.ResolvedResource;
import org.apache.ivy.util.FileUtil;
import org.apache.ivy.util.Message;

public class PackagerResolver extends URLResolver {
   private static final String PACKAGER_ARTIFACT_NAME = "packager";
   private static final String PACKAGER_ARTIFACT_TYPE = "packager";
   private static final String PACKAGER_ARTIFACT_EXT = "xml";
   private final Map packagerCache = new HashMap();
   private File buildRoot;
   private File resourceCache;
   private String resourceURL;
   private final Map properties = new LinkedHashMap();
   private boolean validate = true;
   private boolean preserve;
   private boolean restricted = true;
   private boolean verbose;
   private boolean quiet;

   public PackagerResolver() {
      Runtime.getRuntime().addShutdownHook(new Thread() {
         public void run() {
            PackagerResolver.this.clearCache();
         }
      });
   }

   protected synchronized void clearCache() {
      if (!this.preserve) {
         for(PackagerCacheEntry entry : this.packagerCache.values()) {
            entry.cleanup();
         }

         this.packagerCache.clear();
         if (this.buildRoot != null) {
            FileUtil.forceDelete(this.buildRoot);
         }

      }
   }

   public void setBuildRoot(File buildRoot) {
      this.buildRoot = buildRoot;
   }

   public File getBuildRoot() {
      return this.buildRoot;
   }

   public void setResourceCache(File resourceCache) {
      this.resourceCache = resourceCache;
   }

   public File getResourceCache() {
      return this.resourceCache;
   }

   public void setResourceURL(String resourceURL) {
      this.resourceURL = resourceURL;
   }

   public void setPackagerPattern(String pattern) {
      List<String> list = new ArrayList();
      list.add(pattern);
      this.setArtifactPatterns(list);
   }

   public void setPreserveBuildDirectories(boolean preserve) {
      this.preserve = preserve;
   }

   public void setRestricted(boolean restricted) {
      this.restricted = restricted;
   }

   public void setVerbose(boolean verbose) {
      this.verbose = verbose;
   }

   public void setQuiet(boolean quiet) {
      this.quiet = quiet;
   }

   public void setValidate(boolean validate) {
      this.validate = validate;
   }

   public void setAllownomd(boolean b) {
      Message.error("allownomd not supported by resolver " + this);
   }

   public void setDescriptor(String rule) {
      if ("optional".equals(rule)) {
         Message.error("descriptor=\"optional\" not supported by resolver " + this);
      } else {
         super.setDescriptor(rule);
      }
   }

   public void setProperty(String propertyKey, String propertyValue) {
      this.properties.put(propertyKey, propertyValue);
   }

   public void validate() {
      super.validate();
      if (this.buildRoot == null) {
         throw new IllegalStateException("no buildRoot specified");
      } else if (this.getArtifactPatterns().size() == 0) {
         throw new IllegalStateException("no packager pattern specified");
      }
   }

   public synchronized ResolvedResource findArtifactRef(Artifact artifact, Date date) {
      if ("packager".equals(artifact.getName()) && "packager".equals(artifact.getType()) && "xml".equals(artifact.getExt())) {
         return super.findArtifactRef(artifact, date);
      } else {
         ModuleRevisionId mr = artifact.getModuleRevisionId();
         PackagerCacheEntry entry = (PackagerCacheEntry)this.packagerCache.get(mr);
         if (entry != null && !entry.isBuilt()) {
            this.packagerCache.remove(mr);
            entry.cleanup();
            entry = null;
         }

         if (entry == null) {
            ResolvedResource packager = this.findArtifactRef(new DefaultArtifact(mr, (Date)null, "packager", "packager", "xml"), date);
            if (packager == null) {
               return null;
            }

            entry = new PackagerCacheEntry(mr, this.buildRoot, this.resourceCache, this.resourceURL, this.validate, this.preserve, this.restricted, this.verbose, this.quiet);

            try {
               entry.build(packager.getResource(), this.properties);
            } catch (IOException e) {
               throw new RuntimeException("can't build artifact " + artifact, e);
            }

            this.packagerCache.put(mr, entry);
         }

         return entry.getBuiltArtifact(artifact);
      }
   }

   public String getTypeName() {
      return "packager";
   }
}
