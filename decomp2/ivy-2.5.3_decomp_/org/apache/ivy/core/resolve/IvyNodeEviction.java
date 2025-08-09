package org.apache.ivy.core.resolve;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.plugins.conflict.ConflictManager;

public class IvyNodeEviction {
   private IvyNode node;
   private Map selectedDeps = new HashMap();
   private Map pendingConflicts = new HashMap();
   private Map evictedDeps = new HashMap();
   private Map evictedRevs = new HashMap();
   private Map evicted = new HashMap();

   public IvyNodeEviction(IvyNode node) {
      if (node == null) {
         throw new NullPointerException("node must not be null");
      } else {
         this.node = node;
      }
   }

   public Set getResolvedNodes(ModuleId mid, String rootModuleConf) {
      Collection<IvyNode> resolved = (Collection)this.selectedDeps.get(new ModuleIdConf(mid, rootModuleConf));
      Set<IvyNode> ret = new HashSet();
      if (resolved != null) {
         for(IvyNode node : resolved) {
            ret.add(node.getRealNode());
         }
      }

      return ret;
   }

   public Collection getResolvedRevisions(ModuleId mid, String rootModuleConf) {
      Collection<IvyNode> resolved = (Collection)this.selectedDeps.get(new ModuleIdConf(mid, rootModuleConf));
      if (resolved == null) {
         return new HashSet();
      } else {
         Collection<ModuleRevisionId> resolvedRevs = new HashSet();

         for(IvyNode node : resolved) {
            ModuleRevisionId resolvedId = node.getResolvedId();
            resolvedRevs.add(node.getId());
            resolvedRevs.add(resolvedId);
            if (!resolvedId.getExtraAttributes().isEmpty()) {
               resolvedRevs.add(ModuleRevisionId.newInstance(resolvedId.getOrganisation(), resolvedId.getName(), resolvedId.getBranch(), resolvedId.getRevision()));
            }
         }

         return resolvedRevs;
      }
   }

   public void setResolvedNodes(ModuleId moduleId, String rootModuleConf, Collection resolved) {
      ModuleIdConf moduleIdConf = new ModuleIdConf(moduleId, rootModuleConf);
      this.selectedDeps.put(moduleIdConf, new HashSet(resolved));
   }

   public Collection getEvictedNodes(ModuleId mid, String rootModuleConf) {
      Collection<IvyNode> resolved = (Collection)this.evictedDeps.get(new ModuleIdConf(mid, rootModuleConf));
      Set<IvyNode> ret = new HashSet();
      if (resolved != null) {
         for(IvyNode node : resolved) {
            ret.add(node.getRealNode());
         }
      }

      return ret;
   }

   public Collection getEvictedRevisions(ModuleId mid, String rootModuleConf) {
      Collection<ModuleRevisionId> evicted = (Collection)this.evictedRevs.get(new ModuleIdConf(mid, rootModuleConf));
      return evicted == null ? new HashSet() : new HashSet(evicted);
   }

   public void setEvictedNodes(ModuleId moduleId, String rootModuleConf, Collection evicted) {
      ModuleIdConf moduleIdConf = new ModuleIdConf(moduleId, rootModuleConf);
      this.evictedDeps.put(moduleIdConf, new HashSet(evicted));
      Collection<ModuleRevisionId> evictedRevs = new HashSet();

      for(IvyNode node : evicted) {
         evictedRevs.add(node.getId());
         evictedRevs.add(node.getResolvedId());
      }

      this.evictedRevs.put(moduleIdConf, evictedRevs);
   }

   public boolean isEvicted(String rootModuleConf) {
      this.cleanEvicted();
      if (this.node.isRoot()) {
         return false;
      } else {
         EvictionData evictedData = this.getEvictedData(rootModuleConf);
         if (evictedData == null) {
            return false;
         } else {
            IvyNode root = this.node.getRoot();
            ModuleId moduleId = this.node.getId().getModuleId();
            Collection<ModuleRevisionId> resolvedRevisions = root.getResolvedRevisions(moduleId, rootModuleConf);
            return !resolvedRevisions.contains(this.node.getResolvedId()) || evictedData.isTransitivelyEvicted();
         }
      }
   }

   public boolean isCompletelyEvicted() {
      this.cleanEvicted();
      if (this.node.isRoot()) {
         return false;
      } else {
         for(String rootModuleConfiguration : this.node.getRootModuleConfigurations()) {
            if (!this.isEvicted(rootModuleConfiguration)) {
               return false;
            }
         }

         return true;
      }
   }

   private void cleanEvicted() {
      Iterator<String> iter = this.evicted.keySet().iterator();

      while(iter.hasNext()) {
         Collection<IvyNode> sel = ((EvictionData)this.evicted.get(iter.next())).getSelected();
         if (sel != null) {
            for(IvyNode n : sel) {
               if (n.getRealNode().equals(this.node)) {
                  iter.remove();
               }
            }
         }
      }

   }

   public void markEvicted(EvictionData evictionData) {
      this.evicted.put(evictionData.getRootModuleConf(), evictionData);
   }

   public EvictionData getEvictedData(String rootModuleConf) {
      this.cleanEvicted();
      return (EvictionData)this.evicted.get(rootModuleConf);
   }

   public String[] getEvictedConfs() {
      this.cleanEvicted();
      return (String[])this.evicted.keySet().toArray(new String[this.evicted.keySet().size()]);
   }

   public Collection getAllEvictingNodes() {
      Collection<IvyNode> allEvictingNodes = null;

      for(EvictionData ed : this.evicted.values()) {
         Collection<IvyNode> selected = ed.getSelected();
         if (selected != null) {
            if (allEvictingNodes == null) {
               allEvictingNodes = new HashSet();
            }

            allEvictingNodes.addAll(selected);
         }
      }

      return allEvictingNodes;
   }

   public Collection getAllEvictingNodesDetails() {
      Collection<String> ret = null;

      for(EvictionData ed : this.evicted.values()) {
         Collection<IvyNode> selected = ed.getSelected();
         if (selected != null) {
            if (ret == null) {
               ret = new HashSet();
            }

            if (selected.size() == 1) {
               ret.add(selected.iterator().next() + (ed.getDetail() == null ? "" : " " + ed.getDetail()));
            } else if (selected.size() > 1) {
               ret.add(selected + (ed.getDetail() == null ? "" : " " + ed.getDetail()));
            }
         }
      }

      return ret;
   }

   public Collection getAllEvictingConflictManagers() {
      Collection<ConflictManager> ret = new HashSet();

      for(EvictionData ed : this.evicted.values()) {
         ret.add(ed.getConflictManager());
      }

      return ret;
   }

   public EvictionData getEvictionDataInRoot(String rootModuleConf, IvyNode ancestor) {
      Collection<IvyNode> selectedNodes = this.node.getRoot().getResolvedNodes(this.node.getModuleId(), rootModuleConf);

      for(IvyNode node : selectedNodes) {
         if (node.getResolvedId().equals(this.node.getResolvedId())) {
            return null;
         }
      }

      return new EvictionData(rootModuleConf, ancestor, this.node.getRoot().getConflictManager(this.node.getModuleId()), selectedNodes);
   }

   public Collection getPendingConflicts(String rootModuleConf, ModuleId mid) {
      Collection<IvyNode> resolved = (Collection)this.pendingConflicts.get(new ModuleIdConf(mid, rootModuleConf));
      Set<IvyNode> ret = new HashSet();
      if (resolved != null) {
         for(IvyNode node : resolved) {
            ret.add(node.getRealNode());
         }
      }

      return ret;
   }

   public void setPendingConflicts(ModuleId moduleId, String rootModuleConf, Collection conflicts) {
      ModuleIdConf moduleIdConf = new ModuleIdConf(moduleId, rootModuleConf);
      this.pendingConflicts.put(moduleIdConf, new HashSet(conflicts));
   }

   public static class EvictionData {
      private IvyNode parent;
      private ConflictManager conflictManager;
      private Collection selected;
      private String rootModuleConf;
      private String detail;

      public EvictionData(String rootModuleConf, IvyNode parent, ConflictManager conflictManager, Collection selected) {
         this(rootModuleConf, parent, conflictManager, selected, (String)null);
      }

      public EvictionData(String rootModuleConf, IvyNode parent, ConflictManager conflictManager, Collection selected, String detail) {
         this.rootModuleConf = rootModuleConf;
         this.parent = parent;
         this.conflictManager = conflictManager;
         this.selected = selected;
         this.detail = detail;
      }

      public String toString() {
         return this.selected != null ? this.selected + " in " + this.parent + (this.detail == null ? "" : " " + this.detail) + " (" + this.conflictManager + ") [" + this.rootModuleConf + "]" : "transitively [" + this.rootModuleConf + "]";
      }

      public ConflictManager getConflictManager() {
         return this.conflictManager;
      }

      public IvyNode getParent() {
         return this.parent;
      }

      public Collection getSelected() {
         return this.selected;
      }

      public String getRootModuleConf() {
         return this.rootModuleConf;
      }

      public boolean isTransitivelyEvicted() {
         return this.parent == null;
      }

      public String getDetail() {
         return this.detail;
      }
   }

   private static final class ModuleIdConf {
      private ModuleId moduleId;
      private String conf;

      public ModuleIdConf(ModuleId mid, String conf) {
         if (mid == null) {
            throw new NullPointerException("mid cannot be null");
         } else if (conf == null) {
            throw new NullPointerException("conf cannot be null");
         } else {
            this.moduleId = mid;
            this.conf = conf;
         }
      }

      public final String getConf() {
         return this.conf;
      }

      public final ModuleId getModuleId() {
         return this.moduleId;
      }

      public boolean equals(Object obj) {
         return obj instanceof ModuleIdConf && this.getModuleId().equals(((ModuleIdConf)obj).getModuleId()) && this.getConf().equals(((ModuleIdConf)obj).getConf());
      }

      public int hashCode() {
         int hash = 33;
         hash += this.getModuleId().hashCode() * 17;
         hash += this.getConf().hashCode() * 17;
         return hash;
      }
   }
}
