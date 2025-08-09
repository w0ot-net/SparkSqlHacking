package org.apache.ivy.core.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import org.apache.ivy.core.module.descriptor.DefaultDependencyDescriptor;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.resolve.ResolveData;
import org.apache.ivy.core.resolve.ResolveEngine;
import org.apache.ivy.core.resolve.ResolveOptions;
import org.apache.ivy.core.resolve.ResolvedModuleRevision;
import org.apache.ivy.core.search.SearchEngine;
import org.apache.ivy.plugins.latest.ArtifactInfo;
import org.apache.ivy.plugins.matcher.RegexpPatternMatcher;
import org.apache.ivy.plugins.version.VersionMatcher;
import org.apache.ivy.util.MemoryUtil;
import org.apache.ivy.util.Message;

public class RepositoryManagementEngine {
   private static final double THOUSAND = (double)1000.0F;
   private static final int KILO = 1024;
   private boolean loaded;
   private Map revisions = new HashMap();
   private Map errors = new HashMap();
   private Map modules = new HashMap();
   private boolean analyzed;
   private Map cache = new HashMap();
   private Map dependers = new HashMap();
   private SearchEngine searchEngine;
   private ResolveEngine resolveEngine;
   private RepositoryManagementEngineSettings settings;

   public RepositoryManagementEngine(RepositoryManagementEngineSettings settings, SearchEngine searchEngine, ResolveEngine resolveEngine) {
      this.settings = settings;
      this.searchEngine = searchEngine;
      this.resolveEngine = resolveEngine;
   }

   public void load() {
      long startingMemoryUse = 0L;
      if (this.settings.dumpMemoryUsage()) {
         startingMemoryUse = MemoryUtil.getUsedMemory();
      }

      long startTime = System.currentTimeMillis();
      Message.rawinfo("searching modules... ");
      ModuleRevisionId[] mrids = this.searchModules();
      Message.info("loading repository metadata...");

      for(ModuleRevisionId mrid : mrids) {
         try {
            this.loadModuleRevision(mrid);
         } catch (Exception e) {
            Message.debug((Throwable)e);
            this.errors.put(mrid, e.getMessage());
         }
      }

      long endTime = System.currentTimeMillis();
      Message.info(String.format("%nrepository loaded: %d modules; %d revisions; %s%ss", this.modules.size(), this.revisions.size(), this.settings.dumpMemoryUsage() ? (MemoryUtil.getUsedMemory() - startingMemoryUse) / 1024L + "kB; " : "", (double)(endTime - startTime) / (double)1000.0F));
      this.loaded = true;
   }

   public void analyze() {
      this.ensureLoaded();
      Message.info("\nanalyzing dependencies...");

      for(ModuleDescriptor md : this.revisions.values()) {
         for(DependencyDescriptor dd : md.getDependencies()) {
            ModuleRevisionId dep = this.getDependency(dd);
            if (dep == null) {
               Message.warn("inconsistent repository: declared dependency not found: " + dd);
            } else {
               this.getDependers(dep).add(md.getModuleRevisionId());
            }
         }

         Message.progress();
      }

      this.analyzed = true;
   }

   public int getRevisionsNumber() {
      this.ensureLoaded();
      return this.revisions.size();
   }

   public int getModuleIdsNumber() {
      this.ensureLoaded();
      return this.modules.size();
   }

   public Collection getOrphans() {
      this.ensureAnalyzed();
      Collection<ModuleRevisionId> orphans = new HashSet(this.revisions.keySet());
      orphans.removeAll(this.dependers.keySet());
      return orphans;
   }

   private ModuleRevisionId[] searchModules() {
      return this.searchEngine.listModules((ModuleRevisionId)ModuleRevisionId.newInstance("*", "*", "*", "*"), RegexpPatternMatcher.INSTANCE);
   }

   private ModuleRevisionId getDependency(DependencyDescriptor dd) {
      ModuleRevisionId askedMrid = dd.getDependencyRevisionId();
      VersionMatcher vmatcher = this.settings.getVersionMatcher();
      if (!vmatcher.isDynamic(askedMrid)) {
         return askedMrid;
      } else {
         ModuleRevisionId mrid = (ModuleRevisionId)this.cache.get(askedMrid);
         if (mrid == null) {
            for(ModuleDescriptor md : this.getAllRevisions(askedMrid)) {
               if (vmatcher.needModuleDescriptor(askedMrid, md.getResolvedModuleRevisionId())) {
                  if (vmatcher.accept(askedMrid, md)) {
                     mrid = md.getResolvedModuleRevisionId();
                     break;
                  }
               } else if (vmatcher.accept(askedMrid, md.getResolvedModuleRevisionId())) {
                  mrid = md.getResolvedModuleRevisionId();
                  break;
               }
            }

            if (mrid == null) {
               return null;
            }

            this.cache.put(askedMrid, mrid);
         }

         return mrid;
      }
   }

   private Collection getDependers(ModuleRevisionId id) {
      List<ModuleRevisionId> depders = (List)this.dependers.get(id);
      if (depders == null) {
         depders = new ArrayList();
         this.dependers.put(id, depders);
      }

      return depders;
   }

   private void loadModuleRevision(ModuleRevisionId mrid) throws Exception {
      ResolvedModuleRevision module = this.settings.getResolver(mrid).getDependency(new DefaultDependencyDescriptor(mrid, false), this.newResolveData());
      if (module == null) {
         Message.warn("module not found while listed: " + mrid);
      } else {
         this.revisions.put(module.getId(), module.getDescriptor());
         this.getAllRevisions(module.getId()).add(module.getDescriptor());
      }

      Message.progress();
   }

   private Collection getAllRevisions(ModuleRevisionId id) {
      Collection<ModuleDescriptor> revisions = (Collection)this.modules.get(id.getModuleId());
      if (revisions == null) {
         revisions = new TreeSet(new Comparator() {
            public int compare(ModuleDescriptor md1, ModuleDescriptor md2) {
               return ((ArtifactInfo)RepositoryManagementEngine.this.settings.getDefaultLatestStrategy().sort(new ArtifactInfo[]{md1, md2}).get(0)).equals(md1) ? 1 : -1;
            }
         });
         this.modules.put(id.getModuleId(), revisions);
      }

      return revisions;
   }

   private ResolveData newResolveData() {
      return new ResolveData(this.resolveEngine, new ResolveOptions());
   }

   private void ensureAnalyzed() {
      if (!this.analyzed) {
         throw new IllegalStateException("repository must have been analyzed to perform this method");
      }
   }

   private void ensureLoaded() {
      if (!this.loaded) {
         throw new IllegalStateException("repository must have be loaded to perform this method");
      }
   }
}
