package org.apache.ivy.plugins.resolver;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import org.apache.ivy.core.cache.ArtifactOrigin;
import org.apache.ivy.core.cache.DefaultRepositoryCacheManager;
import org.apache.ivy.core.cache.RepositoryCacheManager;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.ivy.core.report.DownloadReport;
import org.apache.ivy.core.report.DownloadStatus;
import org.apache.ivy.core.resolve.DownloadOptions;
import org.apache.ivy.core.resolve.IvyNode;
import org.apache.ivy.core.resolve.ResolveData;
import org.apache.ivy.core.resolve.ResolvedModuleRevision;
import org.apache.ivy.core.search.ModuleEntry;
import org.apache.ivy.core.search.OrganisationEntry;
import org.apache.ivy.core.search.RevisionEntry;
import org.apache.ivy.plugins.repository.file.FileResource;
import org.apache.ivy.plugins.resolver.util.ResolvedResource;
import org.apache.ivy.util.Message;

/** @deprecated */
@Deprecated
public class CacheResolver extends FileSystemResolver {
   public CacheResolver() {
   }

   public CacheResolver(ResolverSettings settings) {
      this.setSettings(settings);
      this.setName("cache");
   }

   public ResolvedModuleRevision getDependency(DependencyDescriptor dd, ResolveData data) throws ParseException {
      this.clearIvyAttempts();
      ModuleRevisionId mrid = dd.getDependencyRevisionId();
      ResolvedModuleRevision rmr = this.getRepositoryCacheManager().findModuleInCache(dd, mrid, this.getCacheOptions(data), (String)null);
      if (rmr != null) {
         Message.verbose("\t" + this.getName() + ": revision in cache: " + mrid);
         return rmr;
      } else if (!this.getSettings().getVersionMatcher().isDynamic(mrid)) {
         Message.verbose("\t" + this.getName() + ": no ivy file in cache found for " + mrid);
         return null;
      } else {
         this.ensureConfigured();
         ResolvedResource ivyRef = this.findIvyFileRef(dd, data);
         if (ivyRef != null) {
            Message.verbose("\t" + this.getName() + ": found ivy file in cache for " + mrid);
            Message.verbose("\t\t=> " + ivyRef);
            ModuleRevisionId resolvedMrid = ModuleRevisionId.newInstance(mrid, ivyRef.getRevision());
            IvyNode node = data.getNode(resolvedMrid);
            if (node != null && node.getModuleRevision() != null) {
               Message.verbose("\t" + this.getName() + ": revision already resolved: " + resolvedMrid);
               return node.getModuleRevision();
            } else {
               rmr = this.getRepositoryCacheManager().findModuleInCache(dd.clone(ModuleRevisionId.newInstance(dd.getDependencyRevisionId(), ivyRef.getRevision())), dd.getDependencyRevisionId(), this.getCacheOptions(data), (String)null);
               if (rmr != null) {
                  Message.verbose("\t" + this.getName() + ": revision in cache: " + resolvedMrid);
                  return rmr;
               } else {
                  Message.error("\t" + this.getName() + ": inconsistent cache: clean it and resolve again");
                  return null;
               }
            }
         } else {
            Message.verbose("\t" + this.getName() + ": no ivy file in cache found for " + mrid);
            return null;
         }
      }
   }

   public DownloadReport download(Artifact[] artifacts, DownloadOptions options) {
      this.ensureConfigured();
      this.clearArtifactAttempts();
      DownloadReport dr = new DownloadReport();

      for(Artifact artifact : artifacts) {
         ArtifactDownloadReport adr = new ArtifactDownloadReport(artifact);
         dr.addArtifactReport(adr);
         ResolvedResource artifactRef = this.getArtifactRef(artifact, (Date)null);
         if (artifactRef != null) {
            Message.verbose("\t[NOT REQUIRED] " + artifact);
            ArtifactOrigin origin = new ArtifactOrigin(artifact, true, artifactRef.getResource().getName());
            File archiveFile = ((FileResource)artifactRef.getResource()).getFile();
            adr.setDownloadStatus(DownloadStatus.NO);
            adr.setSize(archiveFile.length());
            adr.setArtifactOrigin(origin);
            adr.setLocalFile(archiveFile);
         } else {
            adr.setDownloadStatus(DownloadStatus.FAILED);
         }
      }

      return dr;
   }

   public boolean exists(Artifact artifact) {
      this.ensureConfigured();
      return super.exists(artifact);
   }

   public ArtifactOrigin locate(Artifact artifact) {
      this.ensureConfigured();
      return super.locate(artifact);
   }

   public void publish(Artifact artifact, File src, boolean overwrite) throws IOException {
      this.ensureConfigured();
      super.publish(artifact, src, overwrite);
   }

   public OrganisationEntry[] listOrganisations() {
      this.ensureConfigured();
      return super.listOrganisations();
   }

   public ModuleEntry[] listModules(OrganisationEntry org) {
      this.ensureConfigured();
      return super.listModules(org);
   }

   public RevisionEntry[] listRevisions(ModuleEntry module) {
      this.ensureConfigured();
      return super.listRevisions(module);
   }

   public void dumpSettings() {
      Message.verbose("\t" + this.getName() + " [cache]");
   }

   private void ensureConfigured() {
      if (this.getIvyPatterns().isEmpty()) {
         this.setIvyPatterns(new ArrayList());
         this.setArtifactPatterns(new ArrayList());

         for(RepositoryCacheManager cache : this.getSettings().getRepositoryCacheManagers()) {
            if (cache instanceof DefaultRepositoryCacheManager) {
               DefaultRepositoryCacheManager c = (DefaultRepositoryCacheManager)cache;
               this.addIvyPattern(c.getBasedir().getAbsolutePath() + "/" + c.getIvyPattern());
               this.addArtifactPattern(c.getBasedir().getAbsolutePath() + "/" + c.getArtifactPattern());
            } else {
               Message.verbose(cache + ": cache implementation is not a DefaultRepositoryCacheManager: unable to configure cache resolver with it");
            }
         }
      }

   }

   public String getTypeName() {
      return "cache";
   }
}
