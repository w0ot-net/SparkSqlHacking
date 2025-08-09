package org.apache.ivy.ant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.report.ResolveReport;
import org.apache.ivy.core.resolve.IvyNode;
import org.apache.ivy.core.resolve.IvyNodeCallers;
import org.apache.ivy.core.resolve.IvyNodeEviction;
import org.apache.tools.ant.BuildException;

public class IvyDependencyTree extends IvyPostResolveTask {
   private final Map dependencies = new HashMap();
   private boolean showEvicted = false;

   public void doExecute() throws BuildException {
      this.prepareAndCheck();
      ResolveReport report = this.getResolvedReport();
      if (report == null) {
         throw new BuildException("No resolution report was available to run the post-resolve task. Make sure resolve was done before this task");
      } else {
         this.log("Dependency tree for " + report.getResolveId());
         ModuleRevisionId mrid = report.getModuleDescriptor().getModuleRevisionId();

         for(IvyNode dependency : report.getDependencies()) {
            this.populateDependencyTree(dependency);
         }

         List<IvyNode> dependencyList = (List)this.dependencies.get(mrid);
         if (dependencyList != null) {
            this.printDependencies(mrid, dependencyList, 0, new HashSet());
         }

      }
   }

   private void printDependencies(ModuleRevisionId mrid, List dependencyList, int indent, Set ancestors) {
      for(IvyNode dependency : dependencyList) {
         Set<ModuleRevisionId> ancestorsForCurrentDep = new HashSet(ancestors);
         ancestorsForCurrentDep.add(mrid);
         boolean evicted = dependency.isEvicted(this.getConf());
         if (!evicted || this.showEvicted) {
            boolean isLastDependency = dependencyList.indexOf(dependency) == dependencyList.size() - 1;
            StringBuilder sb = new StringBuilder();
            ModuleRevisionId dependencyMrid = dependency.getId();
            boolean circular = ancestorsForCurrentDep.contains(dependencyMrid);
            if (indent > 0) {
               for(int i = 0; i < indent; ++i) {
                  if (i == indent - 1 && isLastDependency && !this.hasDependencies(dependency)) {
                     sb.append("   ");
                  } else {
                     sb.append("|  ");
                  }
               }
            }

            sb.append(isLastDependency ? "\\- " : "+- ");
            if (!evicted && circular) {
               sb.append("(circularly depends on) ").append(dependencyMrid);
               this.log(sb.toString());
            } else {
               sb.append(dependencyMrid.toString());
               if (evicted && this.showEvicted) {
                  IvyNodeEviction.EvictionData evictedData = dependency.getEvictedData(this.getConf());
                  if (evictedData.isTransitivelyEvicted()) {
                     sb.append(" transitively");
                  } else {
                     sb.append(" evicted by ");
                     sb.append(evictedData.getSelected());
                     sb.append(" in ").append(evictedData.getParent());
                     if (evictedData.getDetail() != null) {
                        sb.append(" ").append(evictedData.getDetail());
                     }
                  }
               }

               this.log(sb.toString());
               this.printDependencies(dependencyMrid, (List)this.dependencies.get(dependencyMrid), indent + 1, ancestorsForCurrentDep);
            }
         }
      }

   }

   private boolean hasDependencies(IvyNode module) {
      if (module == null) {
         return false;
      } else {
         List<IvyNode> dependenciesForModule = (List)this.dependencies.get(module.getId());
         return dependenciesForModule != null && !dependenciesForModule.isEmpty();
      }
   }

   private void populateDependencyTree(IvyNode dependency) {
      this.registerNodeIfNecessary(dependency.getId());

      for(IvyNodeCallers.Caller caller : dependency.getAllCallers()) {
         this.addDependency(caller.getModuleRevisionId(), dependency);
      }

   }

   private void registerNodeIfNecessary(ModuleRevisionId moduleRevisionId) {
      if (!this.dependencies.containsKey(moduleRevisionId)) {
         this.dependencies.put(moduleRevisionId, new ArrayList());
      }

   }

   private void addDependency(ModuleRevisionId moduleRevisionId, IvyNode dependency) {
      this.registerNodeIfNecessary(moduleRevisionId);
      ((List)this.dependencies.get(moduleRevisionId)).add(dependency);
   }

   public boolean isShowEvicted() {
      return this.showEvicted;
   }

   public void setShowEvicted(boolean showEvicted) {
      this.showEvicted = showEvicted;
   }
}
