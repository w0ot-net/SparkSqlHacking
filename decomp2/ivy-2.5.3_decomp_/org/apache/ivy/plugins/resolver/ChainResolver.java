package org.apache.ivy.plugins.resolver;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.ivy.core.cache.ArtifactOrigin;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.ivy.core.report.DownloadReport;
import org.apache.ivy.core.report.DownloadStatus;
import org.apache.ivy.core.resolve.DownloadOptions;
import org.apache.ivy.core.resolve.ResolveData;
import org.apache.ivy.core.resolve.ResolvedModuleRevision;
import org.apache.ivy.plugins.latest.ArtifactInfo;
import org.apache.ivy.plugins.latest.LatestStrategy;
import org.apache.ivy.plugins.resolver.util.HasLatestStrategy;
import org.apache.ivy.plugins.resolver.util.ResolvedResource;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.StringUtils;

public class ChainResolver extends AbstractResolver {
   private boolean returnFirst = false;
   private List chain = new ArrayList();
   private boolean dual;

   public void add(DependencyResolver resolver) {
      this.chain.add(resolver);
   }

   public ResolvedModuleRevision getDependency(DependencyDescriptor dd, ResolveData data) throws ParseException {
      data = new ResolveData(data, this.doValidate(data));
      List<Exception> errors = new ArrayList();
      ResolvedModuleRevision resolved = data.getCurrentResolvedModuleRevision();
      ResolvedModuleRevision mr = resolved;
      if (resolved == null) {
         Message.verbose(this.getName() + ": Checking cache for: " + dd);
         mr = this.findModuleInCache(dd, data, true);
         if (mr != null) {
            Message.verbose(this.getName() + ": module revision found in cache: " + mr.getId());
            mr = this.forcedRevision(mr);
         }
      }

      for(DependencyResolver resolver : this.chain) {
         LatestStrategy oldLatest = this.setLatestIfRequired(resolver, this.getLatestStrategy());

         try {
            ResolvedModuleRevision previouslyResolved = mr;
            data.setCurrentResolvedModuleRevision(mr);
            mr = resolver.getDependency(dd, data);
            if (mr != previouslyResolved && this.isReturnFirst()) {
               mr = this.forcedRevision(mr);
            }
         } catch (Exception ex) {
            Message.verbose("problem occurred while resolving " + dd + " with " + resolver, ex);
            errors.add(ex);
         } finally {
            if (oldLatest != null) {
               setLatest(resolver, oldLatest);
            }

         }

         this.checkInterrupted();
      }

      if (mr == null && !errors.isEmpty()) {
         if (errors.size() == 1) {
            Exception ex = (Exception)errors.get(0);
            if (ex instanceof RuntimeException) {
               throw (RuntimeException)ex;
            } else if (ex instanceof ParseException) {
               throw (ParseException)ex;
            } else {
               throw new RuntimeException(ex.toString(), ex);
            }
         } else {
            StringBuilder err = new StringBuilder();

            for(Exception ex : errors) {
               err.append("\t").append(StringUtils.getErrorMessage(ex)).append("\n");
            }

            err.setLength(err.length() - 1);
            throw new RuntimeException("several problems occurred while resolving " + dd + ":\n" + err);
         }
      } else {
         return resolved == mr ? resolved : this.resolvedRevision(mr);
      }
   }

   private ResolvedModuleRevision resolvedRevision(ResolvedModuleRevision mr) {
      return this.isDual() && mr != null ? new ResolvedModuleRevision(mr.getResolver(), this, mr.getDescriptor(), mr.getReport(), mr.isForce()) : mr;
   }

   private ResolvedModuleRevision forcedRevision(ResolvedModuleRevision rmr) {
      return rmr == null ? null : new ResolvedModuleRevision(rmr.getResolver(), rmr.getArtifactResolver(), rmr.getDescriptor(), rmr.getReport(), true);
   }

   private LatestStrategy setLatestIfRequired(DependencyResolver resolver, LatestStrategy latestStrategy) {
      String latestName = getLatestStrategyName(resolver);
      if (latestName != null && !"default".equals(latestName)) {
         LatestStrategy oldLatest = getLatest(resolver);
         setLatest(resolver, latestStrategy);
         return oldLatest;
      } else {
         return null;
      }
   }

   public ResolvedResource findIvyFileRef(DependencyDescriptor dd, ResolveData data) {
      for(DependencyResolver resolver : this.chain) {
         ResolvedResource result = resolver.findIvyFileRef(dd, data);
         if (result != null) {
            return result;
         }
      }

      return null;
   }

   public Map[] listTokenValues(String[] tokens, Map criteria) {
      Set<Map<String, String>> result = new HashSet();

      for(DependencyResolver resolver : this.chain) {
         Map<String, String>[] temp = resolver.listTokenValues((String[])tokens, new HashMap(criteria));
         result.addAll(Arrays.asList(temp));
      }

      return (Map[])result.toArray(new Map[result.size()]);
   }

   public void reportFailure() {
      for(DependencyResolver resolver : this.chain) {
         resolver.reportFailure();
      }

   }

   public void reportFailure(Artifact art) {
      for(DependencyResolver resolver : this.chain) {
         resolver.reportFailure(art);
      }

   }

   public DownloadReport download(Artifact[] artifacts, DownloadOptions options) {
      List<Artifact> artifactsToDownload = new ArrayList(Arrays.asList(artifacts));
      DownloadReport report = new DownloadReport();

      for(DependencyResolver resolver : this.chain) {
         if (artifactsToDownload.isEmpty()) {
            break;
         }

         DownloadReport r = resolver.download((Artifact[])artifactsToDownload.toArray(new Artifact[artifactsToDownload.size()]), options);

         for(ArtifactDownloadReport adr : r.getArtifactsReports()) {
            if (adr.getDownloadStatus() != DownloadStatus.FAILED) {
               artifactsToDownload.remove(adr.getArtifact());
               report.addArtifactReport(adr);
            }
         }
      }

      for(Artifact art : artifactsToDownload) {
         ArtifactDownloadReport adr = new ArtifactDownloadReport(art);
         adr.setDownloadStatus(DownloadStatus.FAILED);
         report.addArtifactReport(adr);
      }

      return report;
   }

   public List getResolvers() {
      return this.chain;
   }

   public void publish(Artifact artifact, File src, boolean overwrite) throws IOException {
      this.getFirstResolver().publish(artifact, src, overwrite);
   }

   public void abortPublishTransaction() throws IOException {
      this.getFirstResolver().abortPublishTransaction();
   }

   public void beginPublishTransaction(ModuleRevisionId module, boolean overwrite) throws IOException {
      this.getFirstResolver().beginPublishTransaction(module, overwrite);
   }

   public void commitPublishTransaction() throws IOException {
      this.getFirstResolver().commitPublishTransaction();
   }

   private DependencyResolver getFirstResolver() {
      if (this.chain.isEmpty()) {
         throw new IllegalStateException("invalid chain resolver with no sub resolver");
      } else {
         return (DependencyResolver)this.chain.get(0);
      }
   }

   public boolean isReturnFirst() {
      return this.returnFirst;
   }

   public void setReturnFirst(boolean returnFirst) {
      this.returnFirst = returnFirst;
   }

   public void dumpSettings() {
      Message.verbose("\t" + this.getName() + " [chain] " + this.chain);
      Message.debug("\t\treturn first: " + this.isReturnFirst());
      Message.debug("\t\tdual: " + this.isDual());

      for(DependencyResolver resolver : this.chain) {
         Message.debug("\t\t-> " + resolver.getName());
      }

   }

   public boolean exists(Artifact artifact) {
      for(DependencyResolver resolver : this.chain) {
         if (resolver.exists(artifact)) {
            return true;
         }
      }

      return false;
   }

   public ArtifactOrigin locate(Artifact artifact) {
      for(DependencyResolver resolver : this.chain) {
         ArtifactOrigin origin = resolver.locate(artifact);
         if (!ArtifactOrigin.isUnknown(origin)) {
            return origin;
         }
      }

      return ArtifactOrigin.unknown(artifact);
   }

   public ArtifactDownloadReport download(ArtifactOrigin artifact, DownloadOptions options) {
      for(DependencyResolver resolver : this.chain) {
         ArtifactDownloadReport adr = resolver.download(artifact, options);
         if (adr.getDownloadStatus() != DownloadStatus.FAILED) {
            return adr;
         }
      }

      ArtifactDownloadReport adr = new ArtifactDownloadReport(artifact.getArtifact());
      adr.setDownloadStatus(DownloadStatus.FAILED);
      return adr;
   }

   private static void setLatest(DependencyResolver resolver, LatestStrategy latest) {
      if (resolver instanceof HasLatestStrategy) {
         HasLatestStrategy r = (HasLatestStrategy)resolver;
         r.setLatestStrategy(latest);
      }

   }

   private static LatestStrategy getLatest(DependencyResolver resolver) {
      if (resolver instanceof HasLatestStrategy) {
         HasLatestStrategy r = (HasLatestStrategy)resolver;
         return r.getLatestStrategy();
      } else {
         return null;
      }
   }

   private static String getLatestStrategyName(DependencyResolver resolver) {
      if (resolver instanceof HasLatestStrategy) {
         HasLatestStrategy r = (HasLatestStrategy)resolver;
         return r.getLatest();
      } else {
         return null;
      }
   }

   public void setDual(boolean b) {
      this.dual = b;
   }

   public boolean isDual() {
      return this.dual;
   }

   public static class ResolvedModuleRevisionArtifactInfo implements ArtifactInfo {
      private ResolvedModuleRevision rmr;

      public ResolvedModuleRevisionArtifactInfo(ResolvedModuleRevision rmr) {
         this.rmr = rmr;
      }

      public String getRevision() {
         return this.rmr.getId().getRevision();
      }

      public long getLastModified() {
         return this.rmr.getPublicationDate().getTime();
      }
   }
}
