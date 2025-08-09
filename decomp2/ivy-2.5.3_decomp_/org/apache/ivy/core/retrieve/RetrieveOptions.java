package org.apache.ivy.core.retrieve;

import org.apache.ivy.core.LogOptions;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.filter.Filter;
import org.apache.ivy.util.filter.FilterHelper;

public class RetrieveOptions extends LogOptions {
   public static final String OVERWRITEMODE_NEVER = "never";
   public static final String OVERWRITEMODE_ALWAYS = "always";
   public static final String OVERWRITEMODE_NEWER = "newer";
   public static final String OVERWRITEMODE_DIFFERENT = "different";
   private String[] confs = new String[]{"*"};
   private String destIvyPattern = null;
   private String destArtifactPattern = null;
   private Filter artifactFilter;
   private boolean sync;
   private String overwriteMode;
   private boolean useOrigin;
   private boolean makeSymlinks;
   /** @deprecated */
   @Deprecated
   private boolean makeSymlinksInMass;
   private String resolveId;
   private FileNameMapper mapper;

   public RetrieveOptions() {
      this.artifactFilter = FilterHelper.NO_FILTER;
      this.sync = false;
      this.overwriteMode = "newer";
      this.useOrigin = false;
      this.makeSymlinks = false;
      this.makeSymlinksInMass = false;
   }

   public RetrieveOptions(RetrieveOptions options) {
      super(options);
      this.artifactFilter = FilterHelper.NO_FILTER;
      this.sync = false;
      this.overwriteMode = "newer";
      this.useOrigin = false;
      this.makeSymlinks = false;
      this.makeSymlinksInMass = false;
      this.confs = options.confs;
      this.destIvyPattern = options.destIvyPattern;
      this.destArtifactPattern = options.destArtifactPattern;
      this.artifactFilter = options.artifactFilter;
      this.sync = options.sync;
      this.overwriteMode = options.overwriteMode;
      this.useOrigin = options.useOrigin;
      this.makeSymlinks = options.makeSymlinks;
      this.makeSymlinksInMass = options.makeSymlinksInMass;
      this.resolveId = options.resolveId;
      this.mapper = options.mapper;
   }

   public String getDestArtifactPattern() {
      return this.destArtifactPattern;
   }

   public RetrieveOptions setDestArtifactPattern(String destArtifactPattern) {
      this.destArtifactPattern = destArtifactPattern;
      return this;
   }

   public Filter getArtifactFilter() {
      return this.artifactFilter;
   }

   public RetrieveOptions setArtifactFilter(Filter artifactFilter) {
      this.artifactFilter = artifactFilter;
      return this;
   }

   public String[] getConfs() {
      return this.confs;
   }

   public RetrieveOptions setConfs(String[] confs) {
      this.confs = confs;
      return this;
   }

   public String getOverwriteMode() {
      return this.overwriteMode == null ? "newer" : this.overwriteMode;
   }

   public RetrieveOptions setOverwriteMode(String overwriteMode) {
      this.overwriteMode = overwriteMode;
      return this;
   }

   public String getDestIvyPattern() {
      return this.destIvyPattern;
   }

   public RetrieveOptions setDestIvyPattern(String destIvyPattern) {
      this.destIvyPattern = destIvyPattern;
      return this;
   }

   public boolean isMakeSymlinks() {
      return this.makeSymlinks || this.makeSymlinksInMass;
   }

   /** @deprecated */
   @Deprecated
   public boolean isMakeSymlinksInMass() {
      return false;
   }

   public RetrieveOptions setMakeSymlinks(boolean makeSymlinks) {
      this.makeSymlinks = makeSymlinks;
      return this;
   }

   /** @deprecated */
   @Deprecated
   public RetrieveOptions setMakeSymlinksInMass(boolean makeSymlinksInMass) {
      this.makeSymlinksInMass = makeSymlinksInMass;
      Message.warn("symlinkmass option has been deprecated and will no longer be supported");
      return this;
   }

   public boolean isSync() {
      return this.sync;
   }

   public RetrieveOptions setSync(boolean sync) {
      this.sync = sync;
      return this;
   }

   public boolean isUseOrigin() {
      return this.useOrigin;
   }

   public RetrieveOptions setUseOrigin(boolean useOrigin) {
      this.useOrigin = useOrigin;
      return this;
   }

   public String getResolveId() {
      return this.resolveId;
   }

   public RetrieveOptions setResolveId(String resolveId) {
      this.resolveId = resolveId;
      return this;
   }

   public FileNameMapper getMapper() {
      return this.mapper;
   }

   public RetrieveOptions setMapper(FileNameMapper mapper) {
      this.mapper = mapper;
      return this;
   }
}
