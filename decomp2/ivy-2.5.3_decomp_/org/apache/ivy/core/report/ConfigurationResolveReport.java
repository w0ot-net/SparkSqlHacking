package org.apache.ivy.core.report;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.ivy.core.cache.ResolutionCacheManager;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.resolve.IvyNode;
import org.apache.ivy.core.resolve.ResolveEngine;
import org.apache.ivy.core.resolve.ResolveOptions;
import org.apache.ivy.core.sort.SortOptions;
import org.apache.ivy.plugins.report.XmlReportParser;
import org.apache.ivy.util.Message;

public class ConfigurationResolveReport {
   private final ModuleDescriptor md;
   private final String conf;
   private final Date date;
   private final ResolveOptions options;
   private Map dependencyReports = new LinkedHashMap();
   private Map dependencies = new LinkedHashMap();
   private final ResolveEngine resolveEngine;
   private Map modulesIdsMap = new LinkedHashMap();
   private List modulesIds;
   private Boolean hasChanged = null;

   public ConfigurationResolveReport(ResolveEngine resolveEngine, ModuleDescriptor md, String conf, Date date, ResolveOptions options) {
      this.resolveEngine = resolveEngine;
      this.md = md;
      this.conf = conf;
      this.date = date;
      this.options = options;
   }

   public void checkIfChanged() {
      ResolutionCacheManager cache = this.resolveEngine.getSettings().getResolutionCacheManager();
      String resolveId = this.options.getResolveId();
      File previousReportFile = cache.getConfigurationResolveReportInCache(resolveId, this.conf);
      if (previousReportFile.exists()) {
         try {
            XmlReportParser parser = new XmlReportParser();
            parser.parse(previousReportFile);
            Set<ModuleRevisionId> previousDepSet = new HashSet(Arrays.asList(parser.getDependencyRevisionIds()));
            this.hasChanged = !previousDepSet.equals(this.getModuleRevisionIds());
         } catch (Exception e) {
            Message.warn("Error while parsing configuration resolve report " + previousReportFile.getAbsolutePath(), e);
            this.hasChanged = Boolean.TRUE;
         }
      } else {
         this.hasChanged = Boolean.TRUE;
      }

   }

   public boolean hasChanged() {
      return this.hasChanged;
   }

   public Set getModuleRevisionIds() {
      Set<ModuleRevisionId> mrids = new LinkedHashSet();

      for(IvyNode node : this.getDependencies()) {
         if (!node.isEvicted(this.getConfiguration()) && !node.hasProblem()) {
            mrids.add(node.getResolvedId());
         }
      }

      return mrids;
   }

   public void addDependency(IvyNode node) {
      this.dependencies.put(node.getId(), node);
      this.dependencies.put(node.getResolvedId(), node);
      this.dependencyReports.put(node, Collections.emptyList());
   }

   public void updateDependency(ModuleRevisionId mrid, IvyNode node) {
      this.dependencies.put(mrid, node);
   }

   public void addDependency(IvyNode node, DownloadReport report) {
      this.dependencies.put(node.getId(), node);
      this.dependencies.put(node.getResolvedId(), node);
      List<ArtifactDownloadReport> adrs = new ArrayList();

      for(Artifact artifact : node.getArtifacts(this.conf)) {
         ArtifactDownloadReport artifactReport = report.getArtifactReport(artifact);
         if (artifactReport != null) {
            adrs.add(artifactReport);
         } else {
            Message.debug("no report found for " + artifact);
         }
      }

      this.dependencyReports.put(node, adrs);
   }

   public String getConfiguration() {
      return this.conf;
   }

   public Date getDate() {
      return this.date;
   }

   public ModuleDescriptor getModuleDescriptor() {
      return this.md;
   }

   public ResolveOptions getResolveOptions() {
      return this.options;
   }

   public IvyNode[] getUnresolvedDependencies() {
      List<IvyNode> unresolved = new ArrayList();

      for(IvyNode node : this.getDependencies()) {
         if (node.hasProblem()) {
            unresolved.add(node);
         }
      }

      return (IvyNode[])unresolved.toArray(new IvyNode[unresolved.size()]);
   }

   private Collection getDependencies() {
      return new LinkedHashSet(this.dependencies.values());
   }

   public IvyNode[] getEvictedNodes() {
      List<IvyNode> evicted = new ArrayList();

      for(IvyNode node : this.getDependencies()) {
         if (node.isEvicted(this.conf)) {
            evicted.add(node);
         }
      }

      return (IvyNode[])evicted.toArray(new IvyNode[evicted.size()]);
   }

   private Set getEvictedMrids() {
      Set<ModuleRevisionId> evicted = new LinkedHashSet();

      for(IvyNode node : this.getEvictedNodes()) {
         evicted.add(node.getId());
      }

      return evicted;
   }

   public IvyNode[] getDownloadedNodes() {
      List<IvyNode> downloaded = new ArrayList();

      for(IvyNode node : this.getDependencies()) {
         if (node.isDownloaded() && node.getRealNode() == node) {
            downloaded.add(node);
         }
      }

      return (IvyNode[])downloaded.toArray(new IvyNode[downloaded.size()]);
   }

   public IvyNode[] getSearchedNodes() {
      List<IvyNode> downloaded = new ArrayList();

      for(IvyNode node : this.getDependencies()) {
         if (node.isSearched() && node.getRealNode() == node) {
            downloaded.add(node);
         }
      }

      return (IvyNode[])downloaded.toArray(new IvyNode[downloaded.size()]);
   }

   public ArtifactDownloadReport[] getDownloadReports(ModuleRevisionId mrid) {
      Collection<ArtifactDownloadReport> col = (Collection)this.dependencyReports.get(this.getDependency(mrid));
      return col == null ? new ArtifactDownloadReport[0] : (ArtifactDownloadReport[])col.toArray(new ArtifactDownloadReport[col.size()]);
   }

   public IvyNode getDependency(ModuleRevisionId mrid) {
      return (IvyNode)this.dependencies.get(mrid);
   }

   public List getModuleIds() {
      if (this.modulesIds == null) {
         List<IvyNode> sortedDependencies = this.resolveEngine.getSortEngine().sortNodes(this.getDependencies(), SortOptions.SILENT);
         Collections.reverse(sortedDependencies);

         for(IvyNode dependency : sortedDependencies) {
            ModuleId mid = dependency.getResolvedId().getModuleId();
            Collection<IvyNode> deps = (Collection)this.modulesIdsMap.get(mid);
            if (deps == null) {
               deps = new LinkedHashSet();
               this.modulesIdsMap.put(mid, deps);
            }

            deps.add(dependency);
         }

         this.modulesIds = new ArrayList(this.modulesIdsMap.keySet());
      }

      return Collections.unmodifiableList(this.modulesIds);
   }

   public Collection getNodes(ModuleId mid) {
      if (this.modulesIds == null) {
         this.getModuleIds();
      }

      return (Collection)this.modulesIdsMap.get(mid);
   }

   public ResolveEngine getResolveEngine() {
      return this.resolveEngine;
   }

   public int getArtifactsNumber() {
      int total = 0;

      for(Collection reports : this.dependencyReports.values()) {
         total += reports == null ? 0 : reports.size();
      }

      return total;
   }

   public ArtifactDownloadReport[] getAllArtifactsReports() {
      return this.getArtifactsReports((DownloadStatus)null, true);
   }

   public ArtifactDownloadReport[] getArtifactsReports(DownloadStatus downloadStatus, boolean withEvicted) {
      Collection<ArtifactDownloadReport> all = new LinkedHashSet();
      Collection<ModuleRevisionId> evictedMrids = null;
      if (!withEvicted) {
         evictedMrids = this.getEvictedMrids();
      }

      for(Collection reports : this.dependencyReports.values()) {
         for(ArtifactDownloadReport report : reports) {
            if ((downloadStatus == null || report.getDownloadStatus() == downloadStatus) && (withEvicted || !evictedMrids.contains(report.getArtifact().getModuleRevisionId()))) {
               all.add(report);
            }
         }
      }

      return (ArtifactDownloadReport[])all.toArray(new ArtifactDownloadReport[all.size()]);
   }

   public ArtifactDownloadReport[] getDownloadedArtifactsReports() {
      return this.getArtifactsReports(DownloadStatus.SUCCESSFUL, true);
   }

   public ArtifactDownloadReport[] getFailedArtifactsReports() {
      ArtifactDownloadReport[] allFailedReports = this.getArtifactsReports(DownloadStatus.FAILED, true);
      return filterOutMergedArtifacts(allFailedReports);
   }

   public boolean hasError() {
      return this.getUnresolvedDependencies().length > 0 || this.getFailedArtifactsReports().length > 0;
   }

   public int getNodesNumber() {
      return this.getDependencies().size();
   }

   public static ArtifactDownloadReport[] filterOutMergedArtifacts(ArtifactDownloadReport[] allFailedReports) {
      Collection<ArtifactDownloadReport> adrs = new ArrayList(Arrays.asList(allFailedReports));
      Iterator<ArtifactDownloadReport> iterator = adrs.iterator();

      while(iterator.hasNext()) {
         ArtifactDownloadReport adr = (ArtifactDownloadReport)iterator.next();
         if (adr.getArtifact().getExtraAttribute("ivy:merged") != null) {
            iterator.remove();
         }
      }

      return (ArtifactDownloadReport[])adrs.toArray(new ArtifactDownloadReport[adrs.size()]);
   }
}
