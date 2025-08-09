package org.apache.ivy.core.report;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.ivy.core.cache.ResolutionCacheManager;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.Configuration;
import org.apache.ivy.core.module.descriptor.DefaultDependencyDescriptor;
import org.apache.ivy.core.module.descriptor.DefaultModuleDescriptor;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.resolve.IvyNode;
import org.apache.ivy.core.resolve.ResolveOptions;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.ivy.plugins.report.ReportOutputter;
import org.apache.ivy.util.filter.Filter;

public class ResolveReport {
   private ModuleDescriptor md;
   private Map confReports;
   private List problemMessages;
   private List dependencies;
   private List artifacts;
   private long resolveTime;
   private long downloadTime;
   private String resolveId;
   private long downloadSize;

   public ResolveReport(ModuleDescriptor md) {
      this(md, ResolveOptions.getDefaultResolveId(md));
   }

   public ResolveReport(ModuleDescriptor md, String resolveId) {
      this.confReports = new LinkedHashMap();
      this.problemMessages = new ArrayList();
      this.dependencies = new ArrayList();
      this.artifacts = new ArrayList();
      this.md = md;
      this.resolveId = resolveId;
   }

   public void addReport(String conf, ConfigurationResolveReport report) {
      this.confReports.put(conf, report);
   }

   public ConfigurationResolveReport getConfigurationReport(String conf) {
      return (ConfigurationResolveReport)this.confReports.get(conf);
   }

   public String[] getConfigurations() {
      return (String[])this.confReports.keySet().toArray(new String[this.confReports.size()]);
   }

   public boolean hasError() {
      for(ConfigurationResolveReport report : this.confReports.values()) {
         if (report.hasError()) {
            return true;
         }
      }

      return false;
   }

   public void output(ReportOutputter[] outputters, ResolutionCacheManager cacheMgr, ResolveOptions options) throws IOException {
      for(ReportOutputter outputter : outputters) {
         outputter.output(this, cacheMgr, options);
      }

   }

   public ModuleDescriptor getModuleDescriptor() {
      return this.md;
   }

   public IvyNode[] getEvictedNodes() {
      Collection<IvyNode> all = new LinkedHashSet();

      for(ConfigurationResolveReport report : this.confReports.values()) {
         all.addAll(Arrays.asList(report.getEvictedNodes()));
      }

      return (IvyNode[])all.toArray(new IvyNode[all.size()]);
   }

   public IvyNode[] getUnresolvedDependencies() {
      Collection<IvyNode> all = new LinkedHashSet();

      for(ConfigurationResolveReport report : this.confReports.values()) {
         all.addAll(Arrays.asList(report.getUnresolvedDependencies()));
      }

      return (IvyNode[])all.toArray(new IvyNode[all.size()]);
   }

   public ArtifactDownloadReport[] getFailedArtifactsReports() {
      return ConfigurationResolveReport.filterOutMergedArtifacts(this.getArtifactsReports(DownloadStatus.FAILED, true));
   }

   public ArtifactDownloadReport[] getAllArtifactsReports() {
      return this.getArtifactsReports((DownloadStatus)null, true);
   }

   public ArtifactDownloadReport[] getArtifactsReports(DownloadStatus downloadStatus, boolean withEvicted) {
      Collection<ArtifactDownloadReport> all = new LinkedHashSet();

      for(ConfigurationResolveReport report : this.confReports.values()) {
         ArtifactDownloadReport[] reports = report.getArtifactsReports(downloadStatus, withEvicted);
         all.addAll(Arrays.asList(reports));
      }

      return (ArtifactDownloadReport[])all.toArray(new ArtifactDownloadReport[all.size()]);
   }

   public ArtifactDownloadReport[] getArtifactsReports(ModuleRevisionId mrid) {
      Collection<ArtifactDownloadReport> all = new LinkedHashSet();

      for(ConfigurationResolveReport report : this.confReports.values()) {
         all.addAll(Arrays.asList(report.getDownloadReports(mrid)));
      }

      return (ArtifactDownloadReport[])all.toArray(new ArtifactDownloadReport[all.size()]);
   }

   public void checkIfChanged() {
      for(ConfigurationResolveReport report : this.confReports.values()) {
         report.checkIfChanged();
      }

   }

   public boolean hasChanged() {
      for(ConfigurationResolveReport report : this.confReports.values()) {
         if (report.hasChanged()) {
            return true;
         }
      }

      return false;
   }

   public void setProblemMessages(List problems) {
      this.problemMessages = problems;
   }

   public List getProblemMessages() {
      return this.problemMessages;
   }

   public List getAllProblemMessages() {
      List<String> ret = new ArrayList(this.problemMessages);

      for(ConfigurationResolveReport r : this.confReports.values()) {
         for(IvyNode unresolved : r.getUnresolvedDependencies()) {
            String errMsg = unresolved.getProblemMessage();
            if (errMsg.isEmpty()) {
               ret.add("unresolved dependency: " + unresolved.getId());
            } else {
               ret.add("unresolved dependency: " + unresolved.getId() + ": " + errMsg);
            }
         }

         for(ArtifactDownloadReport adr : r.getFailedArtifactsReports()) {
            ret.add("download failed: " + adr.getArtifact());
         }
      }

      return ret;
   }

   public void setDependencies(List dependencies, Filter artifactFilter) {
      this.dependencies = dependencies;
      this.artifacts = new ArrayList();

      for(IvyNode dependency : dependencies) {
         if (!dependency.isCompletelyEvicted() && !dependency.hasProblem()) {
            this.artifacts.addAll(Arrays.asList(dependency.getSelectedArtifacts(artifactFilter)));
         }

         for(String dconf : dependency.getRootModuleConfigurations()) {
            ConfigurationResolveReport configurationReport = this.getConfigurationReport(dconf);
            if (configurationReport != null) {
               configurationReport.addDependency(dependency);
            }
         }
      }

   }

   public List getDependencies() {
      return this.dependencies;
   }

   public List getArtifacts() {
      return this.artifacts;
   }

   public List getModuleIds() {
      List<ModuleId> ret = new ArrayList();

      for(IvyNode dependency : new ArrayList(this.dependencies)) {
         ModuleId mid = dependency.getResolvedId().getModuleId();
         if (!ret.contains(mid)) {
            ret.add(mid);
         }
      }

      return ret;
   }

   public void setResolveTime(long elapsedTime) {
      this.resolveTime = elapsedTime;
   }

   public long getResolveTime() {
      return this.resolveTime;
   }

   public void setDownloadTime(long elapsedTime) {
      this.downloadTime = elapsedTime;
   }

   public long getDownloadTime() {
      return this.downloadTime;
   }

   public void setDownloadSize(long size) {
      this.downloadSize = size;
   }

   public long getDownloadSize() {
      return this.downloadSize;
   }

   public String getResolveId() {
      return this.resolveId;
   }

   private Set getExtendingConfs(String extended) {
      Set<String> extendingConfs = new HashSet();
      extendingConfs.add(extended);

      for(String conf : this.md.getConfigurationsNames()) {
         this.gatherExtendingConfs(extendingConfs, conf, extended);
      }

      return extendingConfs;
   }

   private boolean gatherExtendingConfs(Set extendingConfs, String conf, String extended) {
      if (extendingConfs.contains(conf)) {
         return true;
      } else {
         String[] exts = this.md.getConfiguration(conf).getExtends();
         if (exts != null && exts.length != 0) {
            for(String ext : exts) {
               if (extendingConfs.contains(ext)) {
                  extendingConfs.add(conf);
                  return true;
               }

               if (ext.equals(extended)) {
                  extendingConfs.add(conf);
                  return true;
               }

               if (this.gatherExtendingConfs(extendingConfs, ext, extended)) {
                  extendingConfs.add(conf);
                  return true;
               }
            }

            return false;
         } else {
            return false;
         }
      }
   }

   public ModuleDescriptor toFixedModuleDescriptor(IvySettings settings, List midToKeep) {
      DefaultModuleDescriptor fixedmd = new DefaultModuleDescriptor(this.md.getModuleRevisionId(), this.md.getStatus(), new Date());

      for(Map.Entry ns : this.md.getExtraAttributesNamespaces().entrySet()) {
         fixedmd.addExtraAttributeNamespace((String)ns.getKey(), (String)ns.getValue());
      }

      fixedmd.setDescription(this.md.getDescription());
      fixedmd.setHomePage(this.md.getHomePage());
      fixedmd.getExtraInfos().addAll(this.md.getExtraInfos());
      List<String> resolvedConfs = Arrays.asList(this.getConfigurations());

      for(String conf : resolvedConfs) {
         fixedmd.addConfiguration(new Configuration(conf));
      }

      for(String conf : resolvedConfs) {
         for(Artifact a : this.md.getArtifacts(conf)) {
            fixedmd.addArtifact(conf, a);
         }
      }

      for(IvyNode dep : this.dependencies) {
         ModuleRevisionId depMrid;
         boolean force;
         if (midToKeep != null && midToKeep.contains(dep.getModuleId())) {
            depMrid = dep.getId();
            force = false;
         } else {
            depMrid = dep.getResolvedId();
            force = true;
         }

         DefaultDependencyDescriptor dd = new DefaultDependencyDescriptor(fixedmd, depMrid, force, false, false);
         boolean evicted = true;

         for(String rootConf : dep.getRootModuleConfigurations()) {
            if (!dep.isEvicted(rootConf)) {
               evicted = false;

               for(String targetConf : dep.getConfigurations(rootConf)) {
                  dd.addDependencyConfiguration(rootConf, targetConf);
               }
            }
         }

         if (!evicted) {
            fixedmd.addDependency(dd);
         }
      }

      return fixedmd;
   }
}
