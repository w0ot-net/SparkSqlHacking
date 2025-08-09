package org.apache.ivy.core.cache;

import java.text.ParseException;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.ivy.core.resolve.ResolvedModuleRevision;
import org.apache.ivy.plugins.repository.ArtifactResourceResolver;
import org.apache.ivy.plugins.repository.Repository;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.plugins.repository.ResourceDownloader;
import org.apache.ivy.plugins.resolver.DependencyResolver;
import org.apache.ivy.plugins.resolver.util.ResolvedResource;

public interface RepositoryCacheManager {
   String getName();

   void saveResolvers(ModuleDescriptor var1, String var2, String var3);

   ArtifactOrigin getSavedArtifactOrigin(Artifact var1);

   ResolvedModuleRevision findModuleInCache(DependencyDescriptor var1, ModuleRevisionId var2, CacheMetadataOptions var3, String var4);

   ArtifactDownloadReport download(Artifact var1, ArtifactResourceResolver var2, ResourceDownloader var3, CacheDownloadOptions var4);

   ArtifactDownloadReport downloadRepositoryResource(Resource var1, String var2, String var3, String var4, CacheResourceOptions var5, Repository var6);

   ResolvedModuleRevision cacheModuleDescriptor(DependencyResolver var1, ResolvedResource var2, DependencyDescriptor var3, Artifact var4, ResourceDownloader var5, CacheMetadataOptions var6) throws ParseException;

   void originalToCachedModuleDescriptor(DependencyResolver var1, ResolvedResource var2, Artifact var3, ResolvedModuleRevision var4, ModuleDescriptorWriter var5);

   void clean();

   /** @deprecated */
   @Deprecated
   void saveResolvedRevision(ModuleRevisionId var1, String var2);

   void saveResolvedRevision(String var1, ModuleRevisionId var2, String var3);
}
