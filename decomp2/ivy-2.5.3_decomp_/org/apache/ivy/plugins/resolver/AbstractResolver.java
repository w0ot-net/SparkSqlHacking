package org.apache.ivy.plugins.resolver;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import org.apache.ivy.core.IvyContext;
import org.apache.ivy.core.RelativeUrlResolver;
import org.apache.ivy.core.cache.ArtifactOrigin;
import org.apache.ivy.core.cache.CacheDownloadOptions;
import org.apache.ivy.core.cache.CacheMetadataOptions;
import org.apache.ivy.core.cache.DownloadListener;
import org.apache.ivy.core.cache.RepositoryCacheManager;
import org.apache.ivy.core.cache.ResolutionCacheManager;
import org.apache.ivy.core.event.EventManager;
import org.apache.ivy.core.event.download.EndArtifactDownloadEvent;
import org.apache.ivy.core.event.download.NeedArtifactEvent;
import org.apache.ivy.core.event.download.StartArtifactDownloadEvent;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.module.status.StatusManager;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.ivy.core.report.DownloadReport;
import org.apache.ivy.core.report.DownloadStatus;
import org.apache.ivy.core.report.MetadataArtifactDownloadReport;
import org.apache.ivy.core.resolve.DownloadOptions;
import org.apache.ivy.core.resolve.IvyNode;
import org.apache.ivy.core.resolve.ResolveData;
import org.apache.ivy.core.resolve.ResolveOptions;
import org.apache.ivy.core.resolve.ResolvedModuleRevision;
import org.apache.ivy.core.search.ModuleEntry;
import org.apache.ivy.core.search.OrganisationEntry;
import org.apache.ivy.core.search.RevisionEntry;
import org.apache.ivy.core.settings.TimeoutConstraint;
import org.apache.ivy.core.settings.Validatable;
import org.apache.ivy.plugins.conflict.ConflictManager;
import org.apache.ivy.plugins.latest.ArtifactInfo;
import org.apache.ivy.plugins.latest.LatestStrategy;
import org.apache.ivy.plugins.matcher.PatternMatcher;
import org.apache.ivy.plugins.namespace.NameSpaceHelper;
import org.apache.ivy.plugins.namespace.Namespace;
import org.apache.ivy.plugins.parser.ParserSettings;
import org.apache.ivy.plugins.resolver.util.HasLatestStrategy;
import org.apache.ivy.plugins.resolver.util.ResolvedResource;
import org.apache.ivy.util.Checks;
import org.apache.ivy.util.Message;

public abstract class AbstractResolver implements DependencyResolver, HasLatestStrategy, Validatable {
   private Boolean validate = null;
   private String name;
   private ResolverSettings settings;
   private EventManager eventManager = null;
   private LatestStrategy latestStrategy;
   private String latestStrategyName;
   private Namespace namespace;
   private String namespaceName;
   private String cacheManagerName;
   private RepositoryCacheManager repositoryCacheManager;
   private String changingMatcherName;
   private String changingPattern;
   private Boolean checkmodified;
   private String timeoutConstraintName;
   private TimeoutConstraint timeoutConstraint;

   public ResolverSettings getSettings() {
      return this.settings;
   }

   public ParserSettings getParserSettings() {
      return new ResolverParserSettings();
   }

   public void setSettings(ResolverSettings ivy) {
      this.settings = ivy;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String hidePassword(String name) {
      return name;
   }

   protected boolean doValidate(ResolveData data) {
      return this.validate != null ? this.validate : data.isValidate();
   }

   public boolean isValidate() {
      return this.validate == null || this.validate;
   }

   public void setValidate(boolean validate) {
      this.validate = validate;
   }

   protected void checkInterrupted() {
      IvyContext.getContext().getIvy().checkInterrupted();
   }

   public void reportFailure() {
      Message.verbose("no failure report implemented by " + this.getName());
   }

   public void reportFailure(Artifact art) {
      Message.verbose("no failure report implemented by " + this.getName());
   }

   public String[] listTokenValues(String token, Map otherTokenValues) {
      return new String[0];
   }

   public Map[] listTokenValues(String[] tokens, Map criteria) {
      return new Map[0];
   }

   public OrganisationEntry[] listOrganisations() {
      return new OrganisationEntry[0];
   }

   public ModuleEntry[] listModules(OrganisationEntry org) {
      return new ModuleEntry[0];
   }

   public RevisionEntry[] listRevisions(ModuleEntry module) {
      return new RevisionEntry[0];
   }

   public String toString() {
      return this.getName();
   }

   public void dumpSettings() {
      Message.verbose("\t" + this.getName() + " [" + this.getTypeName() + "]");
      Message.debug("\t\tcache: " + this.cacheManagerName);
   }

   public String getTypeName() {
      return this.getClass().getName();
   }

   public TimeoutConstraint getTimeoutConstraint() {
      return this.timeoutConstraint;
   }

   public void setTimeoutConstraint(String name) {
      this.timeoutConstraintName = name;
   }

   public ArtifactDownloadReport download(ArtifactOrigin artifact, DownloadOptions options) {
      DownloadReport r = this.download(new Artifact[]{artifact.getArtifact()}, options);
      return r.getArtifactReport(artifact.getArtifact());
   }

   public boolean exists(Artifact artifact) {
      return this.locate(artifact) != null;
   }

   public ArtifactOrigin locate(Artifact artifact) {
      DownloadReport dr = this.download(new Artifact[]{artifact}, new DownloadOptions());
      if (dr == null) {
         throw new IllegalStateException("null download report returned by " + this.getName() + " (" + this.getClass().getName() + ") when trying to download " + artifact);
      } else {
         ArtifactDownloadReport adr = dr.getArtifactReport(artifact);
         return adr.getDownloadStatus() == DownloadStatus.FAILED ? null : adr.getArtifactOrigin();
      }
   }

   public LatestStrategy getLatestStrategy() {
      if (this.latestStrategy == null) {
         this.initLatestStrategyFromSettings();
      }

      return this.latestStrategy;
   }

   private void initLatestStrategyFromSettings() {
      if (this.getSettings() == null) {
         throw new IllegalStateException("no ivy instance found: impossible to get a latest strategy without ivy instance");
      } else {
         if (this.latestStrategyName != null && !"default".equals(this.latestStrategyName)) {
            this.latestStrategy = this.getSettings().getLatestStrategy(this.latestStrategyName);
            if (this.latestStrategy == null) {
               throw new IllegalStateException("unknown latest strategy '" + this.latestStrategyName + "'");
            }
         } else {
            this.latestStrategy = this.getSettings().getDefaultLatestStrategy();
            Message.debug(this.getName() + ": no latest strategy defined: using default");
         }

      }
   }

   public void setLatestStrategy(LatestStrategy latestStrategy) {
      this.latestStrategy = latestStrategy;
   }

   public void setLatest(String strategyName) {
      this.latestStrategyName = strategyName;
   }

   public String getLatest() {
      if (this.latestStrategyName == null) {
         this.latestStrategyName = "default";
      }

      return this.latestStrategyName;
   }

   public Namespace getNamespace() {
      if (this.namespace == null) {
         this.initNamespaceFromSettings();
      }

      return this.namespace;
   }

   private void initNamespaceFromSettings() {
      if (this.getSettings() != null) {
         if (this.namespaceName != null) {
            this.namespace = this.getSettings().getNamespace(this.namespaceName);
            if (this.namespace == null) {
               throw new IllegalStateException("unknown namespace '" + this.namespaceName + "'");
            }
         } else {
            this.namespace = this.getSettings().getSystemNamespace();
            Message.debug(this.getName() + ": no namespace defined: using system");
         }
      } else {
         Message.verbose(this.getName() + ": no namespace defined nor ivy instance: using system namespace");
         this.namespace = Namespace.SYSTEM_NAMESPACE;
      }

   }

   public void setNamespace(String namespaceName) {
      this.namespaceName = namespaceName;
   }

   protected ModuleDescriptor toSystem(ModuleDescriptor md) {
      return NameSpaceHelper.toSystem(md, this.getNamespace());
   }

   protected Artifact fromSystem(Artifact artifact) {
      return NameSpaceHelper.transform(artifact, this.getNamespace().getFromSystemTransformer());
   }

   protected Artifact toSystem(Artifact artifact) {
      return NameSpaceHelper.transform(artifact, this.getNamespace().getToSystemTransformer());
   }

   protected MetadataArtifactDownloadReport toSystem(MetadataArtifactDownloadReport report) {
      return NameSpaceHelper.transform(report, this.getNamespace().getToSystemTransformer());
   }

   protected ResolvedModuleRevision toSystem(ResolvedModuleRevision rmr) {
      return NameSpaceHelper.toSystem(rmr, this.getNamespace());
   }

   protected ModuleRevisionId toSystem(ModuleRevisionId resolvedMrid) {
      return this.getNamespace().getToSystemTransformer().transform(resolvedMrid);
   }

   protected DependencyDescriptor fromSystem(DependencyDescriptor dd) {
      return NameSpaceHelper.transform(dd, this.getNamespace().getFromSystemTransformer(), true);
   }

   protected DependencyDescriptor toSystem(DependencyDescriptor dd) {
      return NameSpaceHelper.transform(dd, this.getNamespace().getToSystemTransformer(), true);
   }

   protected IvyNode getSystemNode(ResolveData data, ModuleRevisionId resolvedMrid) {
      return data.getNode(this.toSystem(resolvedMrid));
   }

   protected ResolvedModuleRevision findModuleInCache(DependencyDescriptor dd, ResolveData data) {
      return this.findModuleInCache(dd, data, false);
   }

   protected ResolvedModuleRevision findModuleInCache(DependencyDescriptor dd, ResolveData data, boolean anyResolver) {
      ResolvedModuleRevision rmr = this.getRepositoryCacheManager().findModuleInCache(dd, dd.getDependencyRevisionId(), this.getCacheOptions(data), anyResolver ? null : this.getName());
      if (rmr == null) {
         return null;
      } else if (data.getReport() != null && data.isBlacklisted(data.getReport().getConfiguration(), rmr.getId())) {
         Message.verbose("\t" + this.getName() + ": found revision in cache: " + rmr.getId() + " for " + dd + ", but it is blacklisted");
         return null;
      } else {
         return rmr;
      }
   }

   public void setChangingMatcher(String changingMatcherName) {
      this.changingMatcherName = changingMatcherName;
   }

   protected String getChangingMatcherName() {
      return this.changingMatcherName;
   }

   public void setChangingPattern(String changingPattern) {
      this.changingPattern = changingPattern;
   }

   protected String getChangingPattern() {
      return this.changingPattern;
   }

   public void setCheckmodified(boolean check) {
      this.checkmodified = check;
   }

   public RepositoryCacheManager getRepositoryCacheManager() {
      if (this.repositoryCacheManager == null) {
         this.initRepositoryCacheManagerFromSettings();
      }

      return this.repositoryCacheManager;
   }

   private void initRepositoryCacheManagerFromSettings() {
      if (this.cacheManagerName == null) {
         this.repositoryCacheManager = this.settings.getDefaultRepositoryCacheManager();
         if (this.repositoryCacheManager == null) {
            throw new IllegalStateException("no default cache manager defined with current settings");
         }
      } else {
         this.repositoryCacheManager = this.settings.getRepositoryCacheManager(this.cacheManagerName);
         if (this.repositoryCacheManager == null) {
            throw new IllegalStateException("unknown cache manager '" + this.cacheManagerName + "'. Available caches are " + Arrays.asList(this.settings.getRepositoryCacheManagers()));
         }
      }

   }

   private void initTimeoutConstraintFromSettings() {
      if (this.timeoutConstraintName != null) {
         this.timeoutConstraint = this.settings.getTimeoutConstraint(this.timeoutConstraintName);
         if (this.timeoutConstraint == null) {
            throw new IllegalStateException("Unknown timeout constraint '" + this.timeoutConstraintName + "' on resolver '" + this.name + "'");
         }
      }
   }

   public void setRepositoryCacheManager(RepositoryCacheManager repositoryCacheManager) {
      this.cacheManagerName = repositoryCacheManager.getName();
      this.repositoryCacheManager = repositoryCacheManager;
   }

   public void setCache(String cacheName) {
      this.cacheManagerName = cacheName;
   }

   public void setEventManager(EventManager eventManager) {
      this.eventManager = eventManager;
   }

   public EventManager getEventManager() {
      return this.eventManager;
   }

   public void validate() {
      this.initRepositoryCacheManagerFromSettings();
      this.initNamespaceFromSettings();
      this.initLatestStrategyFromSettings();
      this.initTimeoutConstraintFromSettings();
   }

   protected CacheMetadataOptions getCacheOptions(ResolveData data) {
      return (CacheMetadataOptions)(new CacheMetadataOptions()).setChangingMatcherName(this.getChangingMatcherName()).setChangingPattern(this.getChangingPattern()).setCheckTTL(!data.getOptions().isUseCacheOnly()).setCheckmodified(data.getOptions().isUseCacheOnly() ? Boolean.FALSE : this.checkmodified).setValidate(this.doValidate(data)).setNamespace(this.getNamespace()).setUseCacheOnly(data.getOptions().isUseCacheOnly()).setForce(data.getOptions().isRefresh()).setListener(this.getDownloadListener(this.getDownloadOptions(data.getOptions())));
   }

   protected CacheDownloadOptions getCacheDownloadOptions(DownloadOptions options) {
      CacheDownloadOptions cacheDownloadOptions = new CacheDownloadOptions();
      cacheDownloadOptions.setListener(this.getDownloadListener(options));
      return cacheDownloadOptions;
   }

   protected DownloadOptions getDownloadOptions(ResolveOptions options) {
      return (DownloadOptions)(new DownloadOptions()).setLog(options.getLog());
   }

   public void abortPublishTransaction() throws IOException {
   }

   public void commitPublishTransaction() throws IOException {
   }

   public void beginPublishTransaction(ModuleRevisionId module, boolean overwrite) throws IOException {
   }

   private DownloadListener getDownloadListener(final DownloadOptions options) {
      return new DownloadListener() {
         public void needArtifact(RepositoryCacheManager cache, Artifact artifact) {
            if (AbstractResolver.this.eventManager != null) {
               AbstractResolver.this.eventManager.fireIvyEvent(new NeedArtifactEvent(AbstractResolver.this, artifact));
            }

         }

         public void startArtifactDownload(RepositoryCacheManager cache, ResolvedResource rres, Artifact artifact, ArtifactOrigin origin) {
            if (!artifact.isMetadata() && !"quiet".equals(options.getLog())) {
               Message.info("downloading " + rres.getResource() + " ...");
            } else {
               Message.verbose("downloading " + rres.getResource() + " ...");
            }

            if (AbstractResolver.this.eventManager != null) {
               AbstractResolver.this.eventManager.fireIvyEvent(new StartArtifactDownloadEvent(AbstractResolver.this, artifact, origin));
            }

         }

         public void endArtifactDownload(RepositoryCacheManager cache, Artifact artifact, ArtifactDownloadReport adr, File archiveFile) {
            if (AbstractResolver.this.eventManager != null) {
               AbstractResolver.this.eventManager.fireIvyEvent(new EndArtifactDownloadEvent(AbstractResolver.this, artifact, adr, archiveFile));
            }

         }
      };
   }

   protected boolean isAfter(ResolvedModuleRevision rmr1, ResolvedModuleRevision rmr2, Date date) {
      ArtifactInfo[] ais = new ArtifactInfo[]{new ChainResolver.ResolvedModuleRevisionArtifactInfo(rmr1), new ChainResolver.ResolvedModuleRevisionArtifactInfo(rmr2)};
      return this.getLatestStrategy().findLatest(ais, date) == ais[0];
   }

   protected ResolvedModuleRevision checkLatest(DependencyDescriptor dd, ResolvedModuleRevision newModuleFound, ResolveData data) {
      Checks.checkNotNull(dd, "dd");
      Checks.checkNotNull(data, "data");
      this.saveModuleRevisionIfNeeded(dd, newModuleFound);
      ResolvedModuleRevision previousModuleFound = data.getCurrentResolvedModuleRevision();
      String newModuleDesc = this.describe(newModuleFound);
      Message.debug("\tchecking " + newModuleDesc + " against " + this.describe(previousModuleFound));
      if (previousModuleFound == null) {
         Message.debug("\tmodule revision kept as first found: " + newModuleDesc);
         return newModuleFound;
      } else if (this.isAfter(newModuleFound, previousModuleFound, data.getDate())) {
         Message.debug("\tmodule revision kept as younger: " + newModuleDesc);
         return newModuleFound;
      } else if (!newModuleFound.getDescriptor().isDefault() && previousModuleFound.getDescriptor().isDefault()) {
         Message.debug("\tmodule revision kept as better (not default): " + newModuleDesc);
         return newModuleFound;
      } else {
         Message.debug("\tmodule revision discarded as older: " + newModuleDesc);
         return previousModuleFound;
      }
   }

   protected void saveModuleRevisionIfNeeded(DependencyDescriptor dd, ResolvedModuleRevision newModuleFound) {
      if (newModuleFound != null && this.getSettings().getVersionMatcher().isDynamic(dd.getDependencyRevisionId())) {
         this.getRepositoryCacheManager().saveResolvedRevision(this.getName(), dd.getDependencyRevisionId(), newModuleFound.getId().getRevision());
      }

   }

   private String describe(ResolvedModuleRevision rmr) {
      return rmr == null ? "[none]" : rmr.getId() + (rmr.getDescriptor().isDefault() ? "[default]" : "") + " from " + rmr.getResolver().getName();
   }

   private class ResolverParserSettings implements ParserSettings {
      private ResolverParserSettings() {
      }

      public ConflictManager getConflictManager(String name) {
         return AbstractResolver.this.getSettings().getConflictManager(name);
      }

      public Namespace getContextNamespace() {
         return AbstractResolver.this.getNamespace();
      }

      public String getDefaultBranch(ModuleId moduleId) {
         return AbstractResolver.this.getSettings().getDefaultBranch(moduleId);
      }

      public PatternMatcher getMatcher(String matcherName) {
         return AbstractResolver.this.getSettings().getMatcher(matcherName);
      }

      public Namespace getNamespace(String namespace) {
         return AbstractResolver.this.getSettings().getNamespace(namespace);
      }

      public RelativeUrlResolver getRelativeUrlResolver() {
         return AbstractResolver.this.getSettings().getRelativeUrlResolver();
      }

      public ResolutionCacheManager getResolutionCacheManager() {
         return AbstractResolver.this.getSettings().getResolutionCacheManager();
      }

      public DependencyResolver getResolver(ModuleRevisionId mRevId) {
         return AbstractResolver.this.getSettings().getResolver(mRevId);
      }

      public StatusManager getStatusManager() {
         return AbstractResolver.this.getSettings().getStatusManager();
      }

      public File resolveFile(String filename) {
         return AbstractResolver.this.getSettings().resolveFile(filename);
      }

      public Map substitute(Map strings) {
         return AbstractResolver.this.getSettings().substitute(strings);
      }

      public String substitute(String value) {
         return AbstractResolver.this.getSettings().substitute(value);
      }

      public String getVariable(String value) {
         return AbstractResolver.this.getSettings().getVariable(value);
      }

      public TimeoutConstraint getTimeoutConstraint(String name) {
         return AbstractResolver.this.getSettings().getTimeoutConstraint(name);
      }
   }
}
