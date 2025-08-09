package org.apache.ivy.plugins.resolver;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import org.apache.ivy.core.cache.ArtifactOrigin;
import org.apache.ivy.core.cache.RepositoryCacheManager;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.ivy.core.report.DownloadReport;
import org.apache.ivy.core.resolve.DownloadOptions;
import org.apache.ivy.core.resolve.ResolveData;
import org.apache.ivy.core.resolve.ResolvedModuleRevision;
import org.apache.ivy.core.search.ModuleEntry;
import org.apache.ivy.core.search.OrganisationEntry;
import org.apache.ivy.core.search.RevisionEntry;
import org.apache.ivy.plugins.namespace.Namespace;
import org.apache.ivy.plugins.resolver.util.ResolvedResource;

public interface DependencyResolver {
   String getName();

   void setName(String var1);

   ResolvedModuleRevision getDependency(DependencyDescriptor var1, ResolveData var2) throws ParseException;

   ResolvedResource findIvyFileRef(DependencyDescriptor var1, ResolveData var2);

   DownloadReport download(Artifact[] var1, DownloadOptions var2);

   ArtifactDownloadReport download(ArtifactOrigin var1, DownloadOptions var2);

   boolean exists(Artifact var1);

   ArtifactOrigin locate(Artifact var1);

   void publish(Artifact var1, File var2, boolean var3) throws IOException;

   void beginPublishTransaction(ModuleRevisionId var1, boolean var2) throws IOException;

   void abortPublishTransaction() throws IOException;

   void commitPublishTransaction() throws IOException;

   void reportFailure();

   void reportFailure(Artifact var1);

   String[] listTokenValues(String var1, Map var2);

   Map[] listTokenValues(String[] var1, Map var2);

   OrganisationEntry[] listOrganisations();

   ModuleEntry[] listModules(OrganisationEntry var1);

   RevisionEntry[] listRevisions(ModuleEntry var1);

   Namespace getNamespace();

   void dumpSettings();

   void setSettings(ResolverSettings var1);

   RepositoryCacheManager getRepositoryCacheManager();
}
