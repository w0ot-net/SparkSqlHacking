package org.apache.ivy.plugins.resolver;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.apache.ivy.Ivy;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.Configuration;
import org.apache.ivy.core.module.descriptor.DefaultArtifact;
import org.apache.ivy.core.module.descriptor.DefaultWorkspaceModuleDescriptor;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.descriptor.ExcludeRule;
import org.apache.ivy.core.module.descriptor.License;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.descriptor.WorkspaceModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.report.DownloadStatus;
import org.apache.ivy.core.report.MetadataArtifactDownloadReport;
import org.apache.ivy.core.resolve.ResolveData;
import org.apache.ivy.core.resolve.ResolvedModuleRevision;
import org.apache.ivy.osgi.core.ManifestHeaderElement;
import org.apache.ivy.osgi.core.ManifestHeaderValue;
import org.apache.ivy.plugins.resolver.util.ResolvedResource;
import org.apache.ivy.plugins.version.VersionMatcher;
import org.apache.ivy.util.Message;

public abstract class AbstractWorkspaceResolver extends AbstractResolver {
   private boolean ignoreBranch;
   private boolean ignoreVersion;

   public void setIgnoreBranch(boolean ignoreBranch) {
      this.ignoreBranch = ignoreBranch;
   }

   public void setIgnoreVersion(boolean ignoreVersion) {
      this.ignoreVersion = ignoreVersion;
   }

   protected ResolvedModuleRevision checkCandidate(DependencyDescriptor dd, ModuleDescriptor md, String workspaceModuleName) {
      if (workspaceModuleName == null) {
         workspaceModuleName = dd.getDependencyId().toString();
      }

      ModuleRevisionId dependencyMrid = dd.getDependencyRevisionId();
      String org = dependencyMrid.getModuleId().getOrganisation();
      String module = dependencyMrid.getModuleId().getName();
      VersionMatcher versionMatcher = this.getSettings().getVersionMatcher();
      ModuleRevisionId candidateMrid = md.getModuleRevisionId();
      switch (org) {
         case "bundle":
            String sn = md.getExtraInfoContentByTagName("Bundle-SymbolicName");
            if (sn == null || !module.equals(sn)) {
               return null;
            }
            break;
         case "package":
            String exportedPackages = md.getExtraInfoContentByTagName("Export-Package");
            if (exportedPackages == null) {
               return null;
            }

            boolean found = false;
            String version = null;

            ManifestHeaderValue exportElements;
            try {
               exportElements = new ManifestHeaderValue(exportedPackages);
            } catch (ParseException var18) {
               return null;
            }

            for(ManifestHeaderElement exportElement : exportElements.getElements()) {
               if (exportElement.getValues().contains(module)) {
                  found = true;
                  version = (String)exportElement.getAttributes().get("version");
                  break;
               }
            }

            if (!found) {
               return null;
            }

            if (version == null) {
               version = dependencyMrid.getRevision();
            }

            md.setResolvedModuleRevisionId(ModuleRevisionId.newInstance(org, module, version));
            break;
         default:
            if (!candidateMrid.getModuleId().equals(dependencyMrid.getModuleId())) {
               return null;
            }
      }

      Message.verbose("Workspace resolver found potential matching workspace module " + workspaceModuleName + " with module " + candidateMrid + " for module " + dependencyMrid);
      if (!this.ignoreBranch) {
         ModuleId mid = dependencyMrid.getModuleId();
         String defaultBranch = this.getSettings().getDefaultBranch(mid);
         String dependencyBranch = dependencyMrid.getBranch();
         String candidateBranch = candidateMrid.getBranch();
         if (dependencyBranch == null) {
            dependencyBranch = defaultBranch;
         }

         if (candidateBranch == null) {
            candidateBranch = defaultBranch;
         }

         if (dependencyBranch != candidateBranch) {
            if (dependencyBranch == null || candidateBranch == null) {
               Message.verbose("\t\trejected since branches doesn't match (one is set, the other isn't)");
               return null;
            }

            if (!dependencyBranch.equals(candidateBranch)) {
               Message.verbose("\t\trejected since branches doesn't match");
               return null;
            }
         }
      }

      if (!this.ignoreVersion && !md.getModuleRevisionId().getRevision().equals(Ivy.getWorkingRevision()) && !versionMatcher.accept(dd.getDependencyRevisionId(), md)) {
         Message.verbose("\t\treject as version didn't match");
         return null;
      } else {
         if (this.ignoreVersion) {
            Message.verbose("\t\tmatched (version are ignored)");
         } else {
            Message.verbose("\t\tversion matched");
         }

         WorkspaceModuleDescriptor workspaceMd = this.createWorkspaceMd(md);
         Artifact mdaf = md.getMetadataArtifact();
         if (mdaf == null) {
            mdaf = new DefaultArtifact(md.getModuleRevisionId(), md.getPublicationDate(), workspaceModuleName, "ivy", "");
         }

         MetadataArtifactDownloadReport madr = new MetadataArtifactDownloadReport(mdaf);
         madr.setDownloadStatus(DownloadStatus.SUCCESSFUL);
         madr.setSearched(true);
         return new ResolvedModuleRevision(this, this, workspaceMd, madr);
      }
   }

   protected WorkspaceModuleDescriptor createWorkspaceMd(ModuleDescriptor md) {
      DefaultWorkspaceModuleDescriptor newMd = new DefaultWorkspaceModuleDescriptor(md.getModuleRevisionId(), "release", (Date)null, true);
      newMd.addConfiguration(new Configuration("default"));
      newMd.setLastModified(System.currentTimeMillis());
      newMd.setDescription(md.getDescription());
      newMd.setHomePage(md.getHomePage());
      newMd.setLastModified(md.getLastModified());
      newMd.setPublicationDate(md.getPublicationDate());
      newMd.setResolvedPublicationDate(md.getResolvedPublicationDate());
      newMd.setStatus(md.getStatus());
      Configuration[] allConfs = md.getConfigurations();

      for(Artifact af : this.createWorkspaceArtifacts(md)) {
         if (allConfs.length == 0) {
            newMd.addArtifact("default", af);
         } else {
            for(Configuration conf : allConfs) {
               newMd.addConfiguration(conf);
               newMd.addArtifact(conf.getName(), af);
            }
         }
      }

      for(DependencyDescriptor dependency : md.getDependencies()) {
         newMd.addDependency(dependency);
      }

      for(ExcludeRule excludeRule : md.getAllExcludeRules()) {
         newMd.addExcludeRule(excludeRule);
      }

      newMd.getExtraInfos().addAll(md.getExtraInfos());

      for(License license : md.getLicenses()) {
         newMd.addLicense(license);
      }

      return newMd;
   }

   protected abstract List createWorkspaceArtifacts(ModuleDescriptor var1);

   public void publish(Artifact artifact, File src, boolean overwrite) throws IOException {
      throw new UnsupportedOperationException("publish not supported by " + this.getName());
   }

   public ResolvedResource findIvyFileRef(DependencyDescriptor dd, ResolveData data) {
      return null;
   }
}
