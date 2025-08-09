package org.apache.ivy.core.resolve;

import java.util.Date;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.report.MetadataArtifactDownloadReport;
import org.apache.ivy.plugins.resolver.DependencyResolver;

public class ResolvedModuleRevision {
   private DependencyResolver resolver;
   private DependencyResolver artifactResolver;
   private ModuleDescriptor descriptor;
   private MetadataArtifactDownloadReport report;
   private boolean force = false;

   public ResolvedModuleRevision(DependencyResolver resolver, DependencyResolver artifactResolver, ModuleDescriptor descriptor, MetadataArtifactDownloadReport report) {
      this.resolver = resolver;
      this.artifactResolver = artifactResolver;
      this.descriptor = descriptor;
      this.report = report;
   }

   public ResolvedModuleRevision(DependencyResolver resolver, DependencyResolver artifactResolver, ModuleDescriptor descriptor, MetadataArtifactDownloadReport report, boolean force) {
      this.resolver = resolver;
      this.artifactResolver = artifactResolver;
      this.descriptor = descriptor;
      this.report = report;
      this.force = force;
   }

   public ModuleRevisionId getId() {
      return this.descriptor.getResolvedModuleRevisionId();
   }

   public Date getPublicationDate() {
      return this.descriptor.getResolvedPublicationDate();
   }

   public ModuleDescriptor getDescriptor() {
      return this.descriptor;
   }

   public DependencyResolver getResolver() {
      return this.resolver;
   }

   public DependencyResolver getArtifactResolver() {
      return this.artifactResolver;
   }

   public MetadataArtifactDownloadReport getReport() {
      return this.report;
   }

   public boolean isForce() {
      return this.force;
   }

   public boolean equals(Object obj) {
      return obj instanceof ResolvedModuleRevision && ((ResolvedModuleRevision)obj).getId().equals(this.getId());
   }

   public int hashCode() {
      return this.getId().hashCode();
   }

   public String toString() {
      return this.getId().toString();
   }
}
