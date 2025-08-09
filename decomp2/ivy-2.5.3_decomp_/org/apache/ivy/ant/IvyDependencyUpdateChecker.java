package org.apache.ivy.ant;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.apache.ivy.core.module.descriptor.Configuration;
import org.apache.ivy.core.module.descriptor.DefaultModuleDescriptor;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.report.ResolveReport;
import org.apache.ivy.core.resolve.IvyNode;
import org.apache.ivy.core.resolve.ResolveOptions;
import org.apache.ivy.util.StringUtils;
import org.apache.tools.ant.BuildException;

public class IvyDependencyUpdateChecker extends IvyPostResolveTask {
   private String revisionToCheck = "latest.integration";
   private boolean download = false;
   private boolean checkIfChanged = false;
   private boolean showTransitive = false;

   public void doExecute() throws BuildException {
      this.prepareAndCheck();
      ModuleDescriptor originalModuleDescriptor = this.getResolvedReport().getModuleDescriptor();
      DefaultModuleDescriptor latestModuleDescriptor = new DefaultModuleDescriptor(originalModuleDescriptor.getModuleRevisionId(), originalModuleDescriptor.getStatus(), originalModuleDescriptor.getPublicationDate());

      for(Configuration configuration : originalModuleDescriptor.getConfigurations()) {
         latestModuleDescriptor.addConfiguration(configuration);
      }

      for(DependencyDescriptor dependencyDescriptor : originalModuleDescriptor.getDependencies()) {
         ModuleRevisionId upToDateMrid = ModuleRevisionId.newInstance(dependencyDescriptor.getDependencyRevisionId(), this.revisionToCheck);
         latestModuleDescriptor.addDependency(dependencyDescriptor.clone(upToDateMrid));
      }

      ResolveOptions resolveOptions = new ResolveOptions();
      resolveOptions.setDownload(this.isDownload());
      resolveOptions.setLog(this.getLog());
      resolveOptions.setConfs(StringUtils.splitToArray(this.getConf()));
      resolveOptions.setCheckIfChanged(this.checkIfChanged);

      try {
         ResolveReport latestReport = this.getIvyInstance().getResolveEngine().resolve((ModuleDescriptor)latestModuleDescriptor, resolveOptions);
         this.displayDependencyUpdates(this.getResolvedReport(), latestReport);
         if (this.showTransitive) {
            this.displayNewDependencyOnLatest(this.getResolvedReport(), latestReport);
            this.displayMissingDependencyOnLatest(this.getResolvedReport(), latestReport);
         }

      } catch (IOException | ParseException e) {
         throw new BuildException("impossible to resolve dependencies:\n\t" + e, e);
      }
   }

   private void displayDependencyUpdates(ResolveReport originalReport, ResolveReport latestReport) {
      this.log("Dependencies updates available :");
      boolean dependencyUpdateDetected = false;

      for(IvyNode latest : latestReport.getDependencies()) {
         for(IvyNode originalDependency : originalReport.getDependencies()) {
            if (originalDependency.getModuleId().equals(latest.getModuleId()) && !originalDependency.getResolvedId().getRevision().equals(latest.getResolvedId().getRevision())) {
               boolean isTransitiveDependency = latest.getDependencyDescriptor(latest.getRoot()) == null;
               if (!isTransitiveDependency || this.showTransitive) {
                  this.log(String.format("\t%s#%s%s\t%s -> %s", originalDependency.getResolvedId().getOrganisation(), originalDependency.getResolvedId().getName(), isTransitiveDependency ? " (transitive)" : "", originalDependency.getResolvedId().getRevision(), latest.getResolvedId().getRevision()));
                  dependencyUpdateDetected = true;
               }
            }
         }
      }

      if (!dependencyUpdateDetected) {
         this.log("\tAll dependencies are up to date");
      }

   }

   private void displayMissingDependencyOnLatest(ResolveReport originalReport, ResolveReport latestReport) {
      List<ModuleRevisionId> listOfMissingDependencyOnLatest = new ArrayList();

      for(IvyNode originalDependency : originalReport.getDependencies()) {
         boolean dependencyFound = false;

         for(IvyNode latest : latestReport.getDependencies()) {
            if (originalDependency.getModuleId().equals(latest.getModuleId())) {
               dependencyFound = true;
            }
         }

         if (!dependencyFound) {
            listOfMissingDependencyOnLatest.add(originalDependency.getId());
         }
      }

      if (listOfMissingDependencyOnLatest.size() > 0) {
         this.log("List of missing dependency on latest resolve :");

         for(ModuleRevisionId moduleRevisionId : listOfMissingDependencyOnLatest) {
            this.log("\t" + moduleRevisionId.toString());
         }
      }

   }

   private void displayNewDependencyOnLatest(ResolveReport originalReport, ResolveReport latestReport) {
      List<ModuleRevisionId> listOfNewDependencyOnLatest = new ArrayList();

      for(IvyNode latest : latestReport.getDependencies()) {
         boolean dependencyFound = false;

         for(IvyNode originalDependency : originalReport.getDependencies()) {
            if (originalDependency.getModuleId().equals(latest.getModuleId())) {
               dependencyFound = true;
            }
         }

         if (!dependencyFound) {
            listOfNewDependencyOnLatest.add(latest.getId());
         }
      }

      if (listOfNewDependencyOnLatest.size() > 0) {
         this.log("List of new dependency on latest resolve :");

         for(ModuleRevisionId moduleRevisionId : listOfNewDependencyOnLatest) {
            this.log("\t" + moduleRevisionId.toString());
         }
      }

   }

   public String getRevisionToCheck() {
      return this.revisionToCheck;
   }

   public void setRevisionToCheck(String revisionToCheck) {
      this.revisionToCheck = revisionToCheck;
   }

   public boolean isDownload() {
      return this.download;
   }

   public void setDownload(boolean download) {
      this.download = download;
   }

   public boolean isShowTransitive() {
      return this.showTransitive;
   }

   public void setShowTransitive(boolean showTransitive) {
      this.showTransitive = showTransitive;
   }

   public boolean isCheckIfChanged() {
      return this.checkIfChanged;
   }

   public void setCheckIfChanged(boolean checkIfChanged) {
      this.checkIfChanged = checkIfChanged;
   }
}
