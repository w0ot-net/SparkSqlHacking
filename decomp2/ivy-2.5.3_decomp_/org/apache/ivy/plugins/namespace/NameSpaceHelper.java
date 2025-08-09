package org.apache.ivy.plugins.namespace;

import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.DefaultArtifact;
import org.apache.ivy.core.module.descriptor.DefaultDependencyDescriptor;
import org.apache.ivy.core.module.descriptor.DefaultModuleDescriptor;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ArtifactId;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.report.MetadataArtifactDownloadReport;
import org.apache.ivy.core.resolve.ResolvedModuleRevision;

public final class NameSpaceHelper {
   private NameSpaceHelper() {
   }

   public static DependencyDescriptor toSystem(DependencyDescriptor dd, Namespace ns) {
      return DefaultDependencyDescriptor.transformInstance(dd, ns);
   }

   public static DependencyDescriptor transform(DependencyDescriptor dd, NamespaceTransformer t, boolean fromSystem) {
      return DefaultDependencyDescriptor.transformInstance(dd, t, fromSystem);
   }

   public static ModuleDescriptor toSystem(ModuleDescriptor md, Namespace ns) {
      return DefaultModuleDescriptor.transformInstance(md, ns);
   }

   public static ResolvedModuleRevision toSystem(ResolvedModuleRevision rmr, Namespace ns) {
      if (ns.getToSystemTransformer().isIdentity()) {
         return rmr;
      } else {
         ModuleDescriptor md = toSystem(rmr.getDescriptor(), ns);
         return md.equals(rmr.getDescriptor()) ? rmr : new ResolvedModuleRevision(rmr.getResolver(), rmr.getArtifactResolver(), md, transform(rmr.getReport(), ns.getToSystemTransformer()), rmr.isForce());
      }
   }

   public static Artifact transform(Artifact artifact, NamespaceTransformer t) {
      if (t.isIdentity()) {
         return artifact;
      } else {
         ModuleRevisionId mrid = t.transform(artifact.getModuleRevisionId());
         return (Artifact)(artifact.getModuleRevisionId().equals(mrid) ? artifact : new DefaultArtifact(mrid, artifact.getPublicationDate(), artifact.getName(), artifact.getType(), artifact.getExt(), artifact.getUrl(), artifact.getQualifiedExtraAttributes()));
      }
   }

   public static MetadataArtifactDownloadReport transform(MetadataArtifactDownloadReport report, NamespaceTransformer t) {
      if (t.isIdentity()) {
         return report;
      } else {
         MetadataArtifactDownloadReport madr = new MetadataArtifactDownloadReport(transform(report.getArtifact(), t));
         madr.setSearched(report.isSearched());
         madr.setDownloadStatus(report.getDownloadStatus());
         madr.setDownloadDetails(report.getDownloadDetails());
         madr.setArtifactOrigin(report.getArtifactOrigin());
         madr.setDownloadTimeMillis(report.getDownloadTimeMillis());
         madr.setOriginalLocalFile(report.getOriginalLocalFile());
         madr.setLocalFile(report.getLocalFile());
         madr.setSize(report.getSize());
         return madr;
      }
   }

   public static ArtifactId transform(ArtifactId artifactId, NamespaceTransformer t) {
      if (t.isIdentity()) {
         return artifactId;
      } else {
         ModuleId mid = transform(artifactId.getModuleId(), t);
         return mid.equals(artifactId.getModuleId()) ? artifactId : new ArtifactId(mid, artifactId.getName(), artifactId.getType(), artifactId.getExt());
      }
   }

   public static ModuleId transform(ModuleId mid, NamespaceTransformer t) {
      return t.isIdentity() ? mid : t.transform(new ModuleRevisionId(mid, "")).getModuleId();
   }

   public static String transformOrganisation(String org, NamespaceTransformer t) {
      return transform(new ModuleId(org, ""), t).getOrganisation();
   }
}
