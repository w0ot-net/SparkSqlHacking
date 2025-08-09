package org.apache.ivy.plugins.resolver;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import org.apache.ivy.core.cache.ArtifactOrigin;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.ivy.core.report.DownloadReport;
import org.apache.ivy.core.resolve.DownloadOptions;
import org.apache.ivy.core.resolve.ResolveData;
import org.apache.ivy.core.resolve.ResolvedModuleRevision;
import org.apache.ivy.plugins.resolver.util.ResolvedResource;
import org.apache.ivy.util.Message;

public class DualResolver extends AbstractResolver {
   public static final String DESCRIPTOR_OPTIONAL = "optional";
   public static final String DESCRIPTOR_REQUIRED = "required";
   private DependencyResolver ivyResolver;
   private DependencyResolver artifactResolver;
   private boolean allownomd = true;

   public void add(DependencyResolver resolver) {
      if (this.ivyResolver == null) {
         this.ivyResolver = resolver;
      } else {
         if (this.artifactResolver != null) {
            throw new IllegalStateException("exactly two resolvers must be added: ivy(1) and artifact(2) one");
         }

         this.artifactResolver = resolver;
      }

   }

   public ResolvedModuleRevision getDependency(DependencyDescriptor dd, ResolveData data) throws ParseException {
      if (this.ivyResolver != null && this.artifactResolver != null) {
         ResolvedModuleRevision resolved = data.getCurrentResolvedModuleRevision();
         data = new ResolveData(data, this.doValidate(data));
         ResolvedModuleRevision mr = this.ivyResolver.getDependency(dd, data);
         if (mr == null) {
            this.checkInterrupted();
            if (this.isAllownomd()) {
               Message.verbose("ivy resolver didn't find " + dd + ": trying with artifact resolver");
               return this.artifactResolver.getDependency(dd, data);
            } else {
               return null;
            }
         } else {
            return mr == resolved ? mr : new ResolvedModuleRevision(mr.getResolver(), this, mr.getDescriptor(), mr.getReport(), mr.isForce());
         }
      } else {
         throw new IllegalStateException("exactly two resolvers must be added: ivy(1) and artifact(2) one");
      }
   }

   public ResolvedResource findIvyFileRef(DependencyDescriptor dd, ResolveData data) {
      return this.ivyResolver.findIvyFileRef(dd, data);
   }

   public void reportFailure() {
      this.ivyResolver.reportFailure();
      this.artifactResolver.reportFailure();
   }

   public void reportFailure(Artifact art) {
      this.ivyResolver.reportFailure(art);
      this.artifactResolver.reportFailure(art);
   }

   public DownloadReport download(Artifact[] artifacts, DownloadOptions options) {
      return this.artifactResolver.download(artifacts, options);
   }

   public DependencyResolver getArtifactResolver() {
      return this.artifactResolver;
   }

   public void setArtifactResolver(DependencyResolver artifactResolver) {
      this.artifactResolver = artifactResolver;
   }

   public DependencyResolver getIvyResolver() {
      return this.ivyResolver;
   }

   public void setIvyResolver(DependencyResolver ivyResolver) {
      this.ivyResolver = ivyResolver;
   }

   public void publish(Artifact artifact, File src, boolean overwrite) throws IOException {
      if ("ivy".equals(artifact.getType())) {
         this.ivyResolver.publish(artifact, src, overwrite);
      } else {
         this.artifactResolver.publish(artifact, src, overwrite);
      }

   }

   public void abortPublishTransaction() throws IOException {
      this.ivyResolver.abortPublishTransaction();
      this.artifactResolver.abortPublishTransaction();
   }

   public void beginPublishTransaction(ModuleRevisionId module, boolean overwrite) throws IOException {
      this.ivyResolver.beginPublishTransaction(module, overwrite);
      this.artifactResolver.beginPublishTransaction(module, overwrite);
   }

   public void commitPublishTransaction() throws IOException {
      this.ivyResolver.commitPublishTransaction();
      this.artifactResolver.commitPublishTransaction();
   }

   public void dumpSettings() {
      if (this.ivyResolver != null && this.artifactResolver != null) {
         Message.verbose("\t" + this.getName() + " [dual " + this.ivyResolver.getName() + " " + this.artifactResolver.getName() + "]");
      } else {
         throw new IllegalStateException("exactly two resolvers must be added: ivy(1) and artifact(2) one");
      }
   }

   public boolean exists(Artifact artifact) {
      return artifact.isMetadata() ? this.ivyResolver.exists(artifact) : this.artifactResolver.exists(artifact);
   }

   public ArtifactOrigin locate(Artifact artifact) {
      return artifact.isMetadata() ? this.ivyResolver.locate(artifact) : this.artifactResolver.locate(artifact);
   }

   public ArtifactDownloadReport download(ArtifactOrigin artifact, DownloadOptions options) {
      return artifact.getArtifact().isMetadata() ? this.ivyResolver.download(artifact, options) : this.artifactResolver.download(artifact, options);
   }

   public boolean isAllownomd() {
      return this.allownomd;
   }

   public void setAllownomd(boolean allownomd) {
      Message.deprecated("allownomd is deprecated, please use descriptor=\"" + (allownomd ? "optional" : "required") + "\" instead");
      this.allownomd = allownomd;
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
}
