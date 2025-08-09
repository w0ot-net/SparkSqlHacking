package org.apache.ivy.plugins.resolver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.apache.ivy.core.IvyContext;
import org.apache.ivy.core.cache.ArtifactOrigin;
import org.apache.ivy.core.cache.ModuleDescriptorWriter;
import org.apache.ivy.core.cache.RepositoryCacheManager;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.DefaultModuleDescriptor;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.ivy.core.report.DownloadReport;
import org.apache.ivy.core.report.DownloadStatus;
import org.apache.ivy.core.report.MetadataArtifactDownloadReport;
import org.apache.ivy.core.resolve.DownloadOptions;
import org.apache.ivy.core.resolve.IvyNode;
import org.apache.ivy.core.resolve.ResolveData;
import org.apache.ivy.core.resolve.ResolvedModuleRevision;
import org.apache.ivy.core.search.ModuleEntry;
import org.apache.ivy.core.search.OrganisationEntry;
import org.apache.ivy.core.search.RevisionEntry;
import org.apache.ivy.plugins.conflict.ConflictManager;
import org.apache.ivy.plugins.latest.ArtifactInfo;
import org.apache.ivy.plugins.namespace.Namespace;
import org.apache.ivy.plugins.parser.ModuleDescriptorParser;
import org.apache.ivy.plugins.parser.ModuleDescriptorParserRegistry;
import org.apache.ivy.plugins.parser.xml.XmlModuleDescriptorWriter;
import org.apache.ivy.plugins.repository.ArtifactResourceResolver;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.plugins.repository.ResourceDownloader;
import org.apache.ivy.plugins.repository.file.FileRepository;
import org.apache.ivy.plugins.repository.file.FileResource;
import org.apache.ivy.plugins.repository.url.URLRepository;
import org.apache.ivy.plugins.repository.url.URLResource;
import org.apache.ivy.plugins.resolver.util.MDResolvedResource;
import org.apache.ivy.plugins.resolver.util.ResolvedResource;
import org.apache.ivy.plugins.resolver.util.ResourceMDParser;
import org.apache.ivy.plugins.version.VersionMatcher;
import org.apache.ivy.util.Checks;
import org.apache.ivy.util.ChecksumHelper;
import org.apache.ivy.util.HostUtil;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.StringUtils;

public abstract class BasicResolver extends AbstractResolver {
   public static final String DESCRIPTOR_OPTIONAL = "optional";
   public static final String DESCRIPTOR_REQUIRED = "required";
   /** @deprecated */
   @Deprecated
   public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
   private String workspaceName = HostUtil.getLocalHostName();
   private boolean envDependent = true;
   private List ivyattempts = new ArrayList();
   private Map artattempts = new HashMap();
   private boolean checkconsistency = true;
   private boolean allownomd = true;
   private boolean force = false;
   private String checksums = null;
   private URLRepository extartifactrep = new URLRepository();
   private final ArtifactResourceResolver artifactResourceResolver = new ArtifactResourceResolver() {
      public ResolvedResource resolve(Artifact artifact) {
         artifact = BasicResolver.this.fromSystem(artifact);
         return BasicResolver.this.getArtifactRef(artifact, (Date)null);
      }
   };
   private final ResourceDownloader downloader = new ResourceDownloader() {
      public void download(Artifact artifact, Resource resource, File dest) throws IOException {
         if (dest.exists()) {
            dest.delete();
         }

         File part = new File(dest.getAbsolutePath() + ".part");
         if (resource.getName().equals(String.valueOf(artifact.getUrl()))) {
            if (part.getParentFile() != null) {
               part.getParentFile().mkdirs();
            }

            BasicResolver.this.extartifactrep.get(resource.getName(), part);
         } else {
            BasicResolver.this.getAndCheck(resource, part);
         }

         if (!part.renameTo(dest)) {
            throw new IOException("impossible to move part file to definitive one: " + part + " -> " + dest);
         }
      }
   };

   public String getWorkspaceName() {
      return this.workspaceName;
   }

   public void setWorkspaceName(String workspaceName) {
      this.workspaceName = workspaceName;
   }

   public boolean isEnvDependent() {
      return this.envDependent;
   }

   public void setEnvDependent(boolean envDependent) {
      this.envDependent = envDependent;
   }

   public ResolvedModuleRevision getDependency(DependencyDescriptor dd, ResolveData data) throws ParseException {
      IvyContext context = IvyContext.pushNewCopyContext();

      ResolvedModuleRevision var24;
      try {
         ResolvedModuleRevision mr = data.getCurrentResolvedModuleRevision();
         if (mr == null || !this.shouldReturnResolvedModule(dd, mr)) {
            if (this.isForce()) {
               dd = dd.clone(ModuleRevisionId.newInstance(dd.getDependencyRevisionId(), "latest.integration"));
            }

            DependencyDescriptor nsDd = this.fromSystem(dd);
            context.setDependencyDescriptor(dd);
            context.setResolveData(data);
            this.clearIvyAttempts();
            this.clearArtifactAttempts();
            ModuleRevisionId systemMrid = dd.getDependencyRevisionId();
            ModuleRevisionId nsMrid = nsDd.getDependencyRevisionId();
            this.checkRevision(systemMrid);
            boolean isDynamic = this.getAndCheckIsDynamic(systemMrid);
            ResolvedModuleRevision rmr = this.findModuleInCache(dd, data);
            if (rmr != null) {
               if (rmr.getDescriptor().isDefault() && rmr.getResolver() != this) {
                  Message.verbose("\t" + this.getName() + ": found revision in cache: " + systemMrid + " (resolved by " + rmr.getResolver().getName() + "): but it's a default one, maybe we can find a better one");
               } else {
                  if (!this.isForce() || rmr.getResolver() == this) {
                     Message.verbose("\t" + this.getName() + ": revision in cache: " + systemMrid);
                     ResolvedModuleRevision var11 = this.checkLatest(dd, this.checkForcedResolvedModuleRevision(rmr), data);
                     return var11;
                  }

                  Message.verbose("\t" + this.getName() + ": found revision in cache: " + systemMrid + " (resolved by " + rmr.getResolver().getName() + "): but we are in force mode, let's try to find one ourselves");
               }
            }

            if (data.getOptions().isUseCacheOnly()) {
               throw new UnresolvedDependencyException("\t" + this.getName() + " (useCacheOnly) : no ivy file found for " + systemMrid, false);
            }

            this.checkInterrupted();
            ResolvedResource ivyRef = this.findIvyFileRef(nsDd, data);
            this.checkInterrupted();
            ModuleDescriptor systemMd = null;
            if (ivyRef == null) {
               if (!this.isAllownomd()) {
                  throw new UnresolvedDependencyException("\t" + this.getName() + ": no ivy file found for " + systemMrid, false);
               }

               ModuleDescriptor nsMd = DefaultModuleDescriptor.newDefaultInstance(nsMrid, nsDd.getAllDependencyArtifacts());
               ModuleDescriptor md = dd.getModuleDescriptor();
               if (md != null) {
                  Map<String, String> extraAttributesNamespaces = md.getExtraAttributesNamespaces();

                  for(Map.Entry entry : extraAttributesNamespaces.entrySet()) {
                     ((DefaultModuleDescriptor)nsMd).addExtraAttributeNamespace((String)entry.getKey(), (String)entry.getValue());
                  }
               }

               ResolvedResource artifactRef = this.findFirstArtifactRef(nsMd, nsDd, data);
               this.checkInterrupted();
               if (artifactRef == null) {
                  throw new UnresolvedDependencyException("\t" + this.getName() + ": no ivy file nor artifact found for " + systemMrid, false);
               }

               long lastModified = artifactRef.getLastModified();
               if (lastModified != 0L && nsMd instanceof DefaultModuleDescriptor) {
                  ((DefaultModuleDescriptor)nsMd).setLastModified(lastModified);
               }

               Message.verbose("\t" + this.getName() + ": no ivy file found for " + systemMrid + ": using default data");
               if (isDynamic) {
                  nsMd.setResolvedModuleRevisionId(ModuleRevisionId.newInstance(nsMrid, artifactRef.getRevision()));
               }

               systemMd = this.toSystem(nsMd);
               MetadataArtifactDownloadReport madr = new MetadataArtifactDownloadReport(systemMd.getMetadataArtifact());
               madr.setDownloadStatus(DownloadStatus.NO);
               madr.setSearched(true);
               rmr = new ResolvedModuleRevision(this, this, systemMd, madr, this.isForce());
               this.getRepositoryCacheManager().cacheModuleDescriptor(this, artifactRef, this.toSystem(dd), systemMd.getAllArtifacts()[0], (ResourceDownloader)null, this.getCacheOptions(data));
            } else {
               if (ivyRef instanceof MDResolvedResource) {
                  rmr = ((MDResolvedResource)ivyRef).getResolvedModuleRevision();
               }

               if (rmr == null) {
                  rmr = this.parse(ivyRef, dd, data);
                  if (rmr == null) {
                     throw new UnresolvedDependencyException();
                  }
               }

               if (!rmr.getReport().isDownloaded() && rmr.getReport().getLocalFile() != null) {
                  ResolvedModuleRevision var31 = this.checkLatest(dd, this.checkForcedResolvedModuleRevision(rmr), data);
                  return var31;
               }

               ModuleDescriptor nsMd = rmr.getDescriptor();
               systemMd = this.toSystem(nsMd);
               if (this.isCheckconsistency()) {
                  this.checkDescriptorConsistency(systemMrid, systemMd, ivyRef);
                  this.checkDescriptorConsistency(nsMrid, nsMd, ivyRef);
               } else if (systemMd instanceof DefaultModuleDescriptor) {
                  DefaultModuleDescriptor defaultMd = (DefaultModuleDescriptor)systemMd;
                  ModuleRevisionId revision = this.getRevision(ivyRef, systemMrid, systemMd);
                  defaultMd.setModuleRevisionId(revision);
                  defaultMd.setResolvedModuleRevisionId(revision);
               } else {
                  Message.warn("consistency disabled with instance of non DefaultModuleDescriptor... module info can't be updated, so consistency check will be done");
                  this.checkDescriptorConsistency(nsMrid, nsMd, ivyRef);
                  this.checkDescriptorConsistency(systemMrid, systemMd, ivyRef);
               }

               rmr = new ResolvedModuleRevision(this, this, systemMd, this.toSystem(rmr.getReport()), this.isForce());
            }

            this.resolveAndCheckRevision(systemMd, systemMrid, ivyRef, isDynamic);
            this.resolveAndCheckPublicationDate(dd, systemMd, systemMrid, data);
            this.checkNotConvertedExclusionRule(systemMd, ivyRef, data);
            if (ivyRef == null || ivyRef.getResource() != null) {
               this.cacheModuleDescriptor(systemMd, systemMrid, ivyRef, rmr);
            }

            ResolvedModuleRevision var30 = this.checkLatest(dd, this.checkForcedResolvedModuleRevision(rmr), data);
            return var30;
         }

         var24 = mr;
      } catch (UnresolvedDependencyException ex) {
         if (!ex.getMessage().isEmpty()) {
            if (ex.isError()) {
               Message.error(ex.getMessage());
            } else {
               Message.verbose(ex.getMessage());
            }
         }

         var24 = data.getCurrentResolvedModuleRevision();
         return var24;
      } finally {
         IvyContext.popContext();
      }

      return var24;
   }

   protected boolean shouldReturnResolvedModule(DependencyDescriptor dd, ResolvedModuleRevision mr) {
      ModuleRevisionId mrid = dd.getDependencyRevisionId();
      boolean isDynamic = this.getSettings().getVersionMatcher().isDynamic(mrid);
      boolean shouldReturn = mr.isForce();
      shouldReturn |= !isDynamic && !mr.getDescriptor().isDefault();
      shouldReturn &= !this.isForce();
      return shouldReturn;
   }

   private ResolvedModuleRevision checkForcedResolvedModuleRevision(ResolvedModuleRevision rmr) {
      if (rmr == null) {
         return null;
      } else {
         return this.isForce() && !rmr.isForce() ? new ResolvedModuleRevision(rmr.getResolver(), rmr.getArtifactResolver(), rmr.getDescriptor(), rmr.getReport(), true) : rmr;
      }
   }

   private void cacheModuleDescriptor(ModuleDescriptor systemMd, ModuleRevisionId systemMrid, ResolvedResource ivyRef, ResolvedModuleRevision rmr) {
      RepositoryCacheManager cacheManager = this.getRepositoryCacheManager();
      final ModuleDescriptorParser parser = systemMd.getParser();
      Artifact requestedMetadataArtifact = ivyRef == null ? systemMd.getMetadataArtifact() : parser.getMetadataArtifact(ModuleRevisionId.newInstance(systemMrid, systemMd.getRevision()), ivyRef.getResource());
      cacheManager.originalToCachedModuleDescriptor(this, ivyRef, requestedMetadataArtifact, rmr, new ModuleDescriptorWriter() {
         public void write(ResolvedResource originalMdResource, ModuleDescriptor md, File src, File dest) throws IOException, ParseException {
            if (originalMdResource == null) {
               XmlModuleDescriptorWriter.write(md, dest);
            } else {
               parser.toIvyFile(new FileInputStream(src), originalMdResource.getResource(), dest, md);
               long repLastModified = originalMdResource.getLastModified();
               if (repLastModified > 0L) {
                  dest.setLastModified(repLastModified);
               }
            }

         }
      });
   }

   private void checkNotConvertedExclusionRule(ModuleDescriptor systemMd, ResolvedResource ivyRef, ResolveData data) {
      if (!this.getNamespace().equals(Namespace.SYSTEM_NAMESPACE) && !systemMd.isDefault() && data.getSettings().logNotConvertedExclusionRule() && systemMd instanceof DefaultModuleDescriptor) {
         DefaultModuleDescriptor dmd = (DefaultModuleDescriptor)systemMd;
         if (dmd.isNamespaceUseful()) {
            Message.warn("the module descriptor " + ivyRef.getResource() + " has information which can't be converted into the system namespace. It will require the availability of the namespace '" + this.getNamespace().getName() + "' to be fully usable.");
         }
      }

   }

   private void resolveAndCheckPublicationDate(DependencyDescriptor systemDd, ModuleDescriptor systemMd, ModuleRevisionId systemMrid, ResolveData data) {
      if (data.getDate() != null) {
         long pubDate = this.getPublicationDate(systemMd, systemDd, data);
         if (pubDate > data.getDate().getTime()) {
            throw new UnresolvedDependencyException("\t" + this.getName() + ": unacceptable publication date => was=" + new Date(pubDate) + " required=" + data.getDate());
         } else if (pubDate == -1L) {
            throw new UnresolvedDependencyException("\t" + this.getName() + ": impossible to guess publication date: artifact missing for " + systemMrid);
         } else {
            systemMd.setResolvedPublicationDate(new Date(pubDate));
         }
      }
   }

   protected void checkModuleDescriptorRevision(ModuleDescriptor systemMd, ModuleRevisionId systemMrid) {
      if (!this.getSettings().getVersionMatcher().accept(systemMrid, systemMd)) {
         throw new UnresolvedDependencyException("\t" + this.getName() + ": unacceptable revision => was=" + systemMd.getResolvedModuleRevisionId().getRevision() + " required=" + systemMrid.getRevision());
      }
   }

   private boolean getAndCheckIsDynamic(ModuleRevisionId systemMrid) {
      boolean isDynamic = this.getSettings().getVersionMatcher().isDynamic(systemMrid);
      if (isDynamic && !this.acceptLatest()) {
         throw new UnresolvedDependencyException("dynamic revisions not handled by " + this.getClass().getName() + ". impossible to resolve " + systemMrid);
      } else {
         return isDynamic;
      }
   }

   private void checkRevision(ModuleRevisionId systemMrid) {
      int index = systemMrid.getRevision().indexOf(64);
      if (index != -1 && !systemMrid.getRevision().substring(index + 1).equals(this.workspaceName)) {
         throw new UnresolvedDependencyException("\t" + this.getName() + ": unhandled revision => " + systemMrid.getRevision());
      }
   }

   private void resolveAndCheckRevision(ModuleDescriptor systemMd, ModuleRevisionId dependencyConstraint, ResolvedResource ivyRef, boolean isDynamic) {
      ModuleRevisionId resolvedMrid = systemMd.getResolvedModuleRevisionId();
      if (resolvedMrid.getRevision() == null || resolvedMrid.getRevision().length() == 0 || resolvedMrid.getRevision().startsWith("working@")) {
         if (!isDynamic) {
            resolvedMrid = ModuleRevisionId.newInstance(resolvedMrid, dependencyConstraint.getRevision());
         } else if (ivyRef == null) {
            resolvedMrid = systemMd.getMetadataArtifact().getModuleRevisionId();
         } else if (ivyRef.getRevision() != null && ivyRef.getRevision().length() != 0) {
            resolvedMrid = ModuleRevisionId.newInstance(resolvedMrid, ivyRef.getRevision());
         } else {
            resolvedMrid = ModuleRevisionId.newInstance(resolvedMrid, "working@" + this.getName());
         }
      }

      if (isDynamic) {
         Message.verbose("\t\t[" + this.toSystem(resolvedMrid).getRevision() + "] " + dependencyConstraint.getModuleId());
      }

      systemMd.setResolvedModuleRevisionId(resolvedMrid);
      this.checkModuleDescriptorRevision(systemMd, dependencyConstraint);
   }

   private ModuleRevisionId getRevision(ResolvedResource ivyRef, ModuleRevisionId askedMrid, ModuleDescriptor md) {
      Map<String, String> allAttributes = new HashMap();
      allAttributes.putAll(md.getQualifiedExtraAttributes());
      allAttributes.putAll(askedMrid.getQualifiedExtraAttributes());
      String revision = ivyRef.getRevision();
      if (revision == null) {
         Message.debug("no revision found in reference for " + askedMrid);
         if (this.getSettings().getVersionMatcher().isDynamic(askedMrid)) {
            if (md.getModuleRevisionId().getRevision() == null) {
               revision = "working@" + this.getName();
            } else {
               Message.debug("using " + askedMrid);
               revision = askedMrid.getRevision();
            }
         } else {
            Message.debug("using " + askedMrid);
            revision = askedMrid.getRevision();
         }
      }

      return ModuleRevisionId.newInstance(askedMrid.getOrganisation(), askedMrid.getName(), askedMrid.getBranch(), revision, allAttributes);
   }

   public ResolvedModuleRevision parse(ResolvedResource mdRef, DependencyDescriptor dd, ResolveData data) throws ParseException {
      dd = this.toSystem(dd);
      ModuleRevisionId mrid = dd.getDependencyRevisionId();
      ModuleDescriptorParser parser = ModuleDescriptorParserRegistry.getInstance().getParser(mdRef.getResource());
      if (parser == null) {
         Message.warn("no module descriptor parser available for " + mdRef.getResource());
         return null;
      } else {
         Message.verbose("\t" + this.getName() + ": found md file for " + mrid);
         Message.verbose("\t\t=> " + mdRef);
         Message.debug("\tparser = " + parser);
         ModuleRevisionId resolvedMrid = mrid;
         if (this.getSettings().getVersionMatcher().isDynamic(mrid)) {
            resolvedMrid = ModuleRevisionId.newInstance(mrid, mdRef.getRevision());
            IvyNode node = data.getNode(resolvedMrid);
            if (node != null && node.getModuleRevision() != null) {
               if (node.getDescriptor() == null || !node.getDescriptor().isDefault()) {
                  Message.verbose("\t" + this.getName() + ": revision already resolved: " + resolvedMrid);
                  node.getModuleRevision().getReport().setSearched(true);
                  return node.getModuleRevision();
               }

               Message.verbose("\t" + this.getName() + ": found already resolved revision: " + resolvedMrid + ": but it's a default one, maybe we can find a better one");
            }
         }

         Artifact moduleArtifact = parser.getMetadataArtifact(resolvedMrid, mdRef.getResource());
         return this.getRepositoryCacheManager().cacheModuleDescriptor(this, mdRef, dd, moduleArtifact, this.downloader, this.getCacheOptions(data));
      }
   }

   protected ResourceMDParser getRMDParser(final DependencyDescriptor dd, final ResolveData data) {
      return new ResourceMDParser() {
         public MDResolvedResource parse(Resource resource, String rev) {
            try {
               ResolvedModuleRevision rmr = BasicResolver.this.parse(new ResolvedResource(resource, rev), dd, data);
               if (rmr != null) {
                  return new MDResolvedResource(resource, rev, rmr);
               }
            } catch (ParseException e) {
               Message.warn("Failed to parse the file '" + resource + "'", e);
            }

            return null;
         }
      };
   }

   protected ResourceMDParser getDefaultRMDParser(final ModuleId mid) {
      return new ResourceMDParser() {
         public MDResolvedResource parse(Resource resource, String rev) {
            DefaultModuleDescriptor md = DefaultModuleDescriptor.newDefaultInstance(new ModuleRevisionId(mid, rev));
            MetadataArtifactDownloadReport madr = new MetadataArtifactDownloadReport(md.getMetadataArtifact());
            madr.setDownloadStatus(DownloadStatus.NO);
            madr.setSearched(true);
            return new MDResolvedResource(resource, rev, new ResolvedModuleRevision(BasicResolver.this, BasicResolver.this, md, madr, BasicResolver.this.isForce()));
         }
      };
   }

   private void checkDescriptorConsistency(ModuleRevisionId mrid, ModuleDescriptor md, ResolvedResource ivyRef) throws ParseException {
      boolean ok = true;
      StringBuilder errors = new StringBuilder();
      if (!mrid.getOrganisation().equals(md.getModuleRevisionId().getOrganisation())) {
         Message.error(String.format("\t%s: bad organisation found in %s: expected='%s' found='%s'", this.getName(), ivyRef.getResource(), mrid.getOrganisation(), md.getModuleRevisionId().getOrganisation()));
         errors.append("bad organisation: expected='").append(mrid.getOrganisation()).append("' found='").append(md.getModuleRevisionId().getOrganisation()).append("'; ");
         ok = false;
      }

      if (!mrid.getName().equals(md.getModuleRevisionId().getName())) {
         Message.error(String.format("\t%s: bad module name found in %s: expected='%s found='%s'", this.getName(), ivyRef.getResource(), mrid.getName(), md.getModuleRevisionId().getName()));
         errors.append("bad module name: expected='").append(mrid.getName()).append("' found='").append(md.getModuleRevisionId().getName()).append("'; ");
         ok = false;
      }

      if (mrid.getBranch() != null && !mrid.getBranch().equals(md.getModuleRevisionId().getBranch())) {
         Message.error(String.format("\t%s: bad branch name found in %s: expected='%s found='%s'", this.getName(), ivyRef.getResource(), mrid.getBranch(), md.getModuleRevisionId().getBranch()));
         errors.append("bad branch name: expected='").append(mrid.getBranch()).append("' found='").append(md.getModuleRevisionId().getBranch()).append("'; ");
         ok = false;
      }

      if (ivyRef.getRevision() != null && !ivyRef.getRevision().startsWith("working@") && !mrid.getRevision().equals(md.getModuleRevisionId().getRevision())) {
         ModuleRevisionId expectedMrid = ModuleRevisionId.newInstance(mrid, mrid.getRevision());
         if (!this.getSettings().getVersionMatcher().accept(expectedMrid, md)) {
            Message.error(String.format("\t%s: bad revision found in %s: expected='%s found='%s'", this.getName(), ivyRef.getResource(), ivyRef.getRevision(), md.getModuleRevisionId().getRevision()));
            errors.append("bad revision: expected='").append(ivyRef.getRevision()).append("' found='").append(md.getModuleRevisionId().getRevision()).append("'; ");
            ok = false;
         }
      }

      if (!this.getSettings().getStatusManager().isStatus(md.getStatus())) {
         Message.error(String.format("\t%s: bad status found in %s: '%s'", this.getName(), ivyRef.getResource(), md.getStatus()));
         errors.append("bad status: '").append(md.getStatus()).append("'; ");
         ok = false;
      }

      for(Map.Entry extra : mrid.getExtraAttributes().entrySet()) {
         if (extra.getValue() != null && !((String)extra.getValue()).equals(md.getExtraAttribute((String)extra.getKey()))) {
            String errorMsg = String.format("bad %s found in %s: expected='%s' found='%s'", extra.getKey(), ivyRef.getResource(), extra.getValue(), md.getExtraAttribute((String)extra.getKey()));
            Message.error("\t" + this.getName() + ": " + errorMsg);
            errors.append(errorMsg).append(";");
            ok = false;
         }
      }

      if (!ok) {
         throw new ParseException("inconsistent module descriptor file found in '" + ivyRef.getResource() + "': " + errors, 0);
      }
   }

   public ResolvedResource findResource(ResolvedResource[] rress, ResourceMDParser rmdparser, ModuleRevisionId mrid, Date date) {
      String name = this.getName();
      VersionMatcher versionMatcher = this.getSettings().getVersionMatcher();
      ResolvedResource found = null;
      List<ArtifactInfo> sorted = this.getLatestStrategy().sort(rress);
      List<String> rejected = new ArrayList();
      List<ModuleRevisionId> foundBlacklisted = new ArrayList();
      IvyContext context = IvyContext.getContext();
      ListIterator<ArtifactInfo> iter = sorted.listIterator(sorted.size());

      while(iter.hasPrevious()) {
         ResolvedResource rres = (ResolvedResource)iter.previous();
         if (this.filterNames(new ArrayList(Collections.singleton(rres.getRevision()))).isEmpty()) {
            Message.debug("\t" + name + ": filtered by name: " + rres);
         } else {
            ModuleRevisionId foundMrid = ModuleRevisionId.newInstance(mrid, rres.getRevision());
            ResolveData data = context.getResolveData();
            if (data != null && data.getReport() != null && data.isBlacklisted(data.getReport().getConfiguration(), foundMrid)) {
               Message.debug("\t" + name + ": blacklisted: " + rres);
               rejected.add(rres.getRevision() + " (blacklisted)");
               foundBlacklisted.add(foundMrid);
            } else if (!versionMatcher.accept(mrid, foundMrid)) {
               Message.debug("\t" + name + ": rejected by version matcher: " + rres);
               rejected.add(rres.getRevision());
            } else if (rres.getResource() != null && !rres.getResource().exists()) {
               Message.debug("\t" + name + ": unreachable: " + rres + "; res=" + rres.getResource());
               rejected.add(rres.getRevision() + " (unreachable)");
            } else if (date != null && rres.getLastModified() > date.getTime()) {
               Message.verbose("\t" + name + ": too young: " + rres);
               rejected.add(rres.getRevision() + " (" + rres.getLastModified() + ")");
            } else {
               if (versionMatcher.needModuleDescriptor(mrid, foundMrid)) {
                  MDResolvedResource r = rmdparser.parse(rres.getResource(), rres.getRevision());
                  if (r == null) {
                     Message.debug("\t" + name + ": impossible to get module descriptor resource: " + rres);
                     rejected.add(rres.getRevision() + " (no or bad MD)");
                     continue;
                  }

                  ModuleDescriptor md = r.getResolvedModuleRevision().getDescriptor();
                  if (md.isDefault()) {
                     Message.debug("\t" + name + ": default md rejected by version matcherrequiring module descriptor: " + rres);
                     rejected.add(rres.getRevision() + " (MD)");
                     continue;
                  }

                  if (!versionMatcher.accept(mrid, md)) {
                     Message.debug("\t" + name + ": md rejected by version matcher: " + rres);
                     rejected.add(rres.getRevision() + " (MD)");
                     continue;
                  }

                  found = r;
               } else {
                  found = rres;
               }

               if (found != null) {
                  break;
               }
            }
         }
      }

      if (found == null && !rejected.isEmpty()) {
         this.logAttempt(rejected.toString());
      }

      if (found == null && !foundBlacklisted.isEmpty()) {
         DependencyDescriptor dd = context.getDependencyDescriptor();
         IvyNode parentNode = context.getResolveData().getNode(dd.getParentRevisionId());
         ConflictManager cm = parentNode.getConflictManager(mrid.getModuleId());
         cm.handleAllBlacklistedRevisions(dd, foundBlacklisted);
      }

      return found;
   }

   protected Collection filterNames(Collection names) {
      this.getSettings().filterIgnore(names);
      return names;
   }

   protected void clearIvyAttempts() {
      this.ivyattempts.clear();
      this.clearArtifactAttempts();
   }

   protected void logIvyAttempt(String attempt) {
      this.ivyattempts.add(attempt);
      Message.verbose("\t\ttried " + attempt);
   }

   protected void logArtifactAttempt(Artifact art, String attempt) {
      List<String> attempts = (List)this.artattempts.get(art);
      if (attempts == null) {
         attempts = new ArrayList();
         this.artattempts.put(art, attempts);
      }

      attempts.add(attempt);
      Message.verbose("\t\ttried " + attempt);
   }

   protected void logAttempt(String attempt) {
      Artifact currentArtifact = (Artifact)IvyContext.getContext().get(this.getName() + ".artifact");
      if (currentArtifact == null) {
         this.logIvyAttempt(attempt);
      } else {
         this.logArtifactAttempt(currentArtifact, attempt);
      }

   }

   public void reportFailure() {
      Message.warn("==== " + this.getName() + ": tried");

      for(String m : this.ivyattempts) {
         Message.warn("  " + m);
      }

      for(Map.Entry entry : this.artattempts.entrySet()) {
         List<String> attempts = (List)entry.getValue();
         if (attempts != null) {
            Message.warn("  -- artifact " + entry.getKey() + ":");

            for(String m : attempts) {
               Message.warn("  " + m);
            }
         }
      }

   }

   public void reportFailure(Artifact art) {
      Message.warn("==== " + this.getName() + ": tried");
      List<String> attempts = (List)this.artattempts.get(art);
      if (attempts != null) {
         for(String m : attempts) {
            Message.warn("  " + m);
         }
      }

   }

   protected boolean acceptLatest() {
      return true;
   }

   public DownloadReport download(Artifact[] artifacts, DownloadOptions options) {
      RepositoryCacheManager cacheManager = this.getRepositoryCacheManager();
      this.clearArtifactAttempts();
      DownloadReport dr = new DownloadReport();

      for(Artifact artifact : artifacts) {
         ArtifactDownloadReport adr = cacheManager.download(artifact, this.artifactResourceResolver, this.downloader, this.getCacheDownloadOptions(options));
         if (DownloadStatus.FAILED == adr.getDownloadStatus()) {
            if (!"missing artifact".equals(adr.getDownloadDetails())) {
               Message.warn("\t" + adr);
            }
         } else if (DownloadStatus.NO == adr.getDownloadStatus()) {
            Message.verbose("\t" + adr);
         } else if ("quiet".equals(options.getLog())) {
            Message.verbose("\t" + adr);
         } else {
            Message.info("\t" + adr);
         }

         dr.addArtifactReport(adr);
         this.checkInterrupted();
      }

      return dr;
   }

   protected void clearArtifactAttempts() {
      this.artattempts.clear();
   }

   public ArtifactDownloadReport download(final ArtifactOrigin origin, DownloadOptions options) {
      Checks.checkNotNull(origin, "origin");
      return this.getRepositoryCacheManager().download(origin.getArtifact(), new ArtifactResourceResolver() {
         public ResolvedResource resolve(Artifact artifact) {
            try {
               Resource resource = BasicResolver.this.getResource(origin.getLocation());
               if (resource != null) {
                  String revision = origin.getArtifact().getModuleRevisionId().getRevision();
                  return new ResolvedResource(resource, revision);
               }
            } catch (IOException e) {
               Message.debug((Throwable)e);
            }

            return null;
         }
      }, this.downloader, this.getCacheDownloadOptions(options));
   }

   protected abstract Resource getResource(String var1) throws IOException;

   public boolean exists(Artifact artifact) {
      ResolvedResource artifactRef = this.getArtifactRef(artifact, (Date)null);
      return artifactRef != null && artifactRef.getResource().exists();
   }

   public ArtifactOrigin locate(Artifact artifact) {
      ArtifactOrigin origin = this.getRepositoryCacheManager().getSavedArtifactOrigin(this.toSystem(artifact));
      if (!ArtifactOrigin.isUnknown(origin)) {
         return origin;
      } else {
         ResolvedResource artifactRef = this.getArtifactRef(artifact, (Date)null);
         return artifactRef != null && artifactRef.getResource().exists() ? new ArtifactOrigin(artifact, artifactRef.getResource().isLocal(), artifactRef.getResource().getName()) : null;
      }
   }

   protected long getPublicationDate(ModuleDescriptor md, DependencyDescriptor dd, ResolveData data) {
      if (md.getPublicationDate() != null) {
         return md.getPublicationDate().getTime();
      } else {
         ResolvedResource artifactRef = this.findFirstArtifactRef(md, dd, data);
         return artifactRef != null ? artifactRef.getLastModified() : -1L;
      }
   }

   public String toString() {
      return this.getName();
   }

   public String[] listTokenValues(String token, Map otherTokenValues) {
      Collection<String> ret = this.findNames(otherTokenValues, token);
      return (String[])ret.toArray(new String[ret.size()]);
   }

   public OrganisationEntry[] listOrganisations() {
      Collection<String> names = this.findNames(Collections.emptyMap(), "organisation");
      List<OrganisationEntry> ret = new ArrayList(names.size());

      for(String org : names) {
         ret.add(new OrganisationEntry(this, org));
      }

      return (OrganisationEntry[])ret.toArray(new OrganisationEntry[names.size()]);
   }

   public ModuleEntry[] listModules(OrganisationEntry org) {
      Map<String, String> tokenValues = new HashMap();
      tokenValues.put("organisation", org.getOrganisation());
      Collection<String> names = this.findNames(tokenValues, "module");
      List<ModuleEntry> ret = new ArrayList(names.size());

      for(String name : names) {
         ret.add(new ModuleEntry(org, name));
      }

      return (ModuleEntry[])ret.toArray(new ModuleEntry[names.size()]);
   }

   public RevisionEntry[] listRevisions(ModuleEntry mod) {
      Map<String, String> tokenValues = new HashMap();
      tokenValues.put("organisation", mod.getOrganisation());
      tokenValues.put("module", mod.getModule());
      Collection<String> names = this.findNames(tokenValues, "revision");
      List<RevisionEntry> ret = new ArrayList(names.size());

      for(String name : names) {
         ret.add(new RevisionEntry(mod, name));
      }

      return (RevisionEntry[])ret.toArray(new RevisionEntry[names.size()]);
   }

   protected abstract Collection findNames(Map var1, String var2);

   protected ResolvedResource findFirstArtifactRef(ModuleDescriptor md, DependencyDescriptor dd, ResolveData data) {
      for(String configName : md.getConfigurationsNames()) {
         for(Artifact artifact : md.getArtifacts(configName)) {
            ResolvedResource ret = this.getArtifactRef(artifact, data.getDate());
            if (ret != null) {
               return ret;
            }
         }
      }

      return null;
   }

   protected long getAndCheck(Resource resource, File dest) throws IOException {
      long size = this.get(resource, dest);

      for(String checksum : this.getChecksumAlgorithms()) {
         if (this.check(resource, dest, checksum)) {
            break;
         }
      }

      return size;
   }

   private boolean check(Resource resource, File dest, String algorithm) throws IOException {
      if (!ChecksumHelper.isKnownAlgorithm(algorithm)) {
         throw new IllegalArgumentException("Unknown checksum algorithm: " + algorithm);
      } else {
         Resource csRes = resource.clone(resource.getName() + "." + algorithm);
         if (csRes.exists()) {
            Message.debug(algorithm + " file found for " + resource + ": checking...");
            File csFile = File.createTempFile("ivytmp", algorithm);

            boolean var6;
            try {
               this.get(csRes, csFile);

               try {
                  ChecksumHelper.check(dest, csFile, algorithm);
                  Message.verbose(algorithm + " OK for " + resource);
                  var6 = true;
               } catch (IOException ex) {
                  dest.delete();
                  throw ex;
               }
            } finally {
               csFile.delete();
            }

            return var6;
         } else {
            return false;
         }
      }
   }

   protected ResolvedResource getArtifactRef(Artifact artifact, Date date) {
      IvyContext.getContext().set(this.getName() + ".artifact", artifact);

      ResolvedResource var13;
      try {
         ResolvedResource ret = this.findArtifactRef(artifact, date);
         if (ret == null && artifact.getUrl() != null) {
            URL url = artifact.getUrl();
            Message.verbose("\tusing url for " + artifact + ": " + url);
            this.logArtifactAttempt(artifact, url.toExternalForm());
            Resource resource;
            if ("file".equals(url.getProtocol())) {
               File f;
               try {
                  f = new File(new URI(url.toExternalForm()));
               } catch (URISyntaxException var11) {
                  f = new File(url.getPath());
               }

               resource = new FileResource(new FileRepository(), f);
            } else {
               resource = new URLResource(url, this.getTimeoutConstraint());
            }

            ret = new ResolvedResource(resource, artifact.getModuleRevisionId().getRevision());
         }

         var13 = ret;
      } finally {
         IvyContext.getContext().set(this.getName() + ".artifact", (Object)null);
      }

      return var13;
   }

   public ResolvedResource doFindArtifactRef(Artifact artifact, Date date) {
      return this.findArtifactRef(artifact, date);
   }

   protected abstract ResolvedResource findArtifactRef(Artifact var1, Date var2);

   protected abstract long get(Resource var1, File var2) throws IOException;

   public boolean isCheckconsistency() {
      return this.checkconsistency;
   }

   public void setCheckconsistency(boolean checkConsistency) {
      this.checkconsistency = checkConsistency;
   }

   public void setForce(boolean force) {
      this.force = force;
   }

   public boolean isForce() {
      return this.force;
   }

   public boolean isAllownomd() {
      return this.allownomd;
   }

   public void setAllownomd(boolean b) {
      Message.deprecated("allownomd is deprecated, please use descriptor=\"" + (b ? "optional" : "required") + "\" instead");
      this.allownomd = b;
   }

   public void setDescriptor(String descriptorRule) {
      switch (descriptorRule) {
         case "required":
            this.allownomd = false;
            break;
         case "optional":
            this.allownomd = true;
            break;
         default:
            throw new IllegalArgumentException("unknown descriptor rule '" + descriptorRule + "'. Allowed rules are: " + Arrays.asList("required", "optional"));
      }

   }

   public String[] getChecksumAlgorithms() {
      String csDef = this.checksums == null ? this.getSettings().getVariable("ivy.checksums") : this.checksums;
      if (csDef == null) {
         return new String[0];
      } else {
         List<String> algos = new ArrayList();

         for(String cs : StringUtils.splitToArray(csDef)) {
            if (!cs.isEmpty() && !"none".equals(cs)) {
               algos.add(cs);
            }
         }

         return (String[])algos.toArray(new String[algos.size()]);
      }
   }

   public void setChecksums(String checksums) {
      this.checksums = checksums;
   }

   private static class UnresolvedDependencyException extends RuntimeException {
      private static final long serialVersionUID = 1L;
      private boolean error;

      public UnresolvedDependencyException() {
         this("", false);
      }

      public UnresolvedDependencyException(String message) {
         this(message, true);
      }

      public UnresolvedDependencyException(String message, boolean error) {
         super(message);
         this.error = error;
      }

      public boolean isError() {
         return this.error;
      }
   }
}
