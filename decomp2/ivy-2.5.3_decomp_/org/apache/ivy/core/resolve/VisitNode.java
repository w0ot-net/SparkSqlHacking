package org.apache.ivy.core.resolve;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import org.apache.ivy.core.IvyContext;
import org.apache.ivy.core.module.descriptor.Configuration;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.plugins.conflict.ConflictManager;
import org.apache.ivy.util.Checks;

public class VisitNode {
   private IvyNode node;
   private VisitNode parent;
   private VisitNode root;
   private Collection path;
   private String parentConf;
   private String requestedConf;
   private String rootModuleConf;
   private ResolveData data;
   private Boolean isCircular;
   private IvyNodeUsage usage;

   public VisitNode(ResolveData data, IvyNode node, VisitNode parent, String rootModuleConf, String parentConf) {
      this(data, node, parent, rootModuleConf, parentConf, (IvyNodeUsage)null);
   }

   public VisitNode(ResolveData data, IvyNode node, VisitNode parent, String rootModuleConf, String parentConf, IvyNodeUsage usage) {
      this.parent = null;
      this.root = null;
      this.path = null;
      this.parentConf = null;
      Checks.checkNotNull(data, "data");
      Checks.checkNotNull(node, "node");
      Checks.checkNotNull(rootModuleConf, "rootModuleConf");
      this.data = data;
      this.node = node;
      this.parent = parent;
      this.rootModuleConf = rootModuleConf;
      this.parentConf = parentConf;
      this.usage = usage;
      this.data.register(this);
   }

   public IvyNode getNode() {
      return this.node;
   }

   public String getRequestedConf() {
      return this.requestedConf;
   }

   public void setRequestedConf(String requestedConf) {
      this.requestedConf = requestedConf;
   }

   public VisitNode getParent() {
      return this.parent;
   }

   public VisitNode getRoot() {
      if (this.root == null) {
         this.root = this.computeRoot();
      }

      return this.root;
   }

   public Collection getPath() {
      if (this.path == null) {
         this.path = this.computePath();
      }

      return this.path;
   }

   private Collection computePath() {
      if (this.parent != null) {
         Collection<VisitNode> p = new LinkedHashSet(this.parent.getPath());
         p.add(this);
         return p;
      } else {
         return Collections.singletonList(this);
      }
   }

   private VisitNode computeRoot() {
      if (this.node.isRoot()) {
         return this;
      } else {
         return this.parent != null ? this.parent.getRoot() : null;
      }
   }

   public String getParentConf() {
      return this.parentConf;
   }

   public void setParentConf(String parentConf) {
      this.parentConf = parentConf;
   }

   public String getRootModuleConf() {
      return this.rootModuleConf;
   }

   public static VisitNode getRoot(VisitNode parent) {
      VisitNode root = parent;
      Collection<VisitNode> path = new HashSet();
      path.add(parent);

      while(root.getParent() != null && !root.getNode().isRoot()) {
         if (path.contains(root.getParent())) {
            return root;
         }

         root = root.getParent();
         path.add(root);
      }

      return root;
   }

   public boolean isTransitive() {
      if (this.node.isRoot()) {
         return true;
      } else if (!this.data.isTransitive()) {
         return false;
      } else if (!this.isParentConfTransitive()) {
         return false;
      } else {
         DependencyDescriptor dd = this.node.getDependencyDescriptor(this.getParentNode());
         return dd != null && dd.isTransitive() || this.node.hasAnyMergedUsageWithTransitiveDependency(this.rootModuleConf);
      }
   }

   protected boolean isParentConfTransitive() {
      String conf = this.getParent().getRequestedConf();
      if (conf == null) {
         return true;
      } else {
         Configuration parentConf = this.getParentNode().getConfiguration(conf);
         return parentConf.isTransitive();
      }
   }

   public IvyNode getRealNode() {
      IvyNode node = this.node.getRealNode();
      return node != null ? node : this.node;
   }

   public void useRealNode() {
      if (this.parent != null) {
         IvyNode node = this.data.getNode(this.node.getId());
         if (node != null && node != this.node) {
            this.node = node;
         }
      }

   }

   public boolean loadData(String conf, boolean shouldBePublic) {
      boolean loaded = this.node.loadData(this.rootModuleConf, this.getParentNode(), this.parentConf, conf, shouldBePublic, this.getUsage());
      if (loaded) {
         this.useRealNode();
         if (this.data.getNode(this.node.getResolvedId()) == null || !this.data.getNode(this.node.getResolvedId()).getId().equals(this.node.getResolvedId())) {
            this.data.register(this.node.getResolvedId(), this);
         }
      }

      return loaded;
   }

   public Collection getDependencies(String conf) {
      Collection<IvyNode> deps = this.node.getDependencies(this.rootModuleConf, conf, this.requestedConf);
      Collection<VisitNode> ret = new ArrayList(deps.size());

      for(IvyNode depNode : deps) {
         ret.add(this.traverseChild(conf, depNode));
      }

      return ret;
   }

   VisitNode gotoNode(IvyNode node) {
      if (!this.getModuleId().equals(node.getModuleId())) {
         throw new IllegalArgumentException("You can't use gotoNode for a node which does not represent the same Module as the one represented by this node.\nCurrent node module id=" + this.getModuleId() + " Given node module id=" + node.getModuleId());
      } else {
         VisitData visitData = this.data.getVisitData(node.getId());
         if (visitData != null) {
            for(VisitNode vnode : visitData.getVisitNodes(this.rootModuleConf)) {
               if (this.parent == null && vnode.getParent() == null || this.parent != null && this.parent.getId().equals(vnode.getParent().getId())) {
                  vnode.parentConf = this.parentConf;
                  vnode.usage = this.getUsage();
                  return vnode;
               }
            }
         }

         return this.traverse(this.parent, this.parentConf, node, this.getUsage());
      }
   }

   private IvyNodeUsage getUsage() {
      return this.usage == null ? this.node.getMainUsage() : this.usage;
   }

   private VisitNode traverseChild(String parentConf, IvyNode child) {
      return this.traverse(this, parentConf, child, (IvyNodeUsage)null);
   }

   private VisitNode traverse(VisitNode parent, String parentConf, IvyNode node, IvyNodeUsage usage) {
      if (this.getPath().contains(node)) {
         IvyContext.getContext().getCircularDependencyStrategy().handleCircularDependency(this.toMrids(this.getPath(), node.getId()));
      }

      return new VisitNode(this.data, node, parent, this.rootModuleConf, parentConf, usage);
   }

   private ModuleRevisionId[] toMrids(Collection path, ModuleRevisionId last) {
      ModuleRevisionId[] ret = new ModuleRevisionId[path.size() + 1];
      int i = 0;

      for(VisitNode node : path) {
         ret[i] = node.getNode().getId();
      }

      ret[ret.length - 1] = last;
      return ret;
   }

   public ModuleRevisionId getResolvedId() {
      return this.node.getResolvedId();
   }

   public void updateConfsToFetch(Collection confs) {
      this.node.updateConfsToFetch(confs);
   }

   public ModuleRevisionId getId() {
      return this.node.getId();
   }

   public boolean isEvicted() {
      return this.node.isEvicted(this.rootModuleConf);
   }

   public String[] getRealConfs(String conf) {
      return this.node.getRealConfs(conf);
   }

   public boolean hasProblem() {
      return this.node.hasProblem();
   }

   public Configuration getConfiguration(String conf) {
      return this.node.getConfiguration(conf);
   }

   public IvyNodeEviction.EvictionData getEvictedData() {
      return this.node.getEvictedData(this.rootModuleConf);
   }

   public DependencyDescriptor getDependencyDescriptor() {
      return this.node.getDependencyDescriptor(this.getParentNode());
   }

   private IvyNode getParentNode() {
      return this.parent == null ? null : this.parent.getNode();
   }

   public boolean isCircular() {
      if (this.isCircular == null) {
         if (this.parent != null) {
            this.isCircular = Boolean.FALSE;

            for(VisitNode ancestor : this.parent.getPath()) {
               if (this.getId().getModuleId().equals(ancestor.getId().getModuleId())) {
                  this.isCircular = Boolean.TRUE;
                  break;
               }
            }
         } else {
            this.isCircular = Boolean.FALSE;
         }
      }

      return this.isCircular;
   }

   public String[] getConfsToFetch() {
      return this.node.getConfsToFetch();
   }

   public String[] getRequiredConfigurations(VisitNode in, String inConf) {
      return this.node.getRequiredConfigurations(in.getNode(), inConf);
   }

   public ModuleId getModuleId() {
      return this.node.getModuleId();
   }

   public Collection getResolvedRevisions(ModuleId mid) {
      return this.node.getResolvedRevisions(mid, this.rootModuleConf);
   }

   public void markEvicted(IvyNodeEviction.EvictionData evictionData) {
      this.node.markEvicted(evictionData);
   }

   public String[] getRequiredConfigurations() {
      return this.node.getRequiredConfigurations();
   }

   public void markEvicted(VisitNode parent, ConflictManager conflictMgr, Collection selected) {
      this.node.markEvicted(this.rootModuleConf, parent.getNode(), conflictMgr, selected);
   }

   public ModuleDescriptor getDescriptor() {
      return this.node.getDescriptor();
   }

   public IvyNodeEviction.EvictionData getEvictionDataInRoot(String rootModuleConf, VisitNode ancestor) {
      return this.node.getEvictionDataInRoot(rootModuleConf, ancestor.getNode());
   }

   public Collection getEvictedRevisions(ModuleId moduleId) {
      return this.node.getEvictedRevisions(moduleId, this.rootModuleConf);
   }

   public String toString() {
      return this.node.toString();
   }

   public boolean isConfRequiredByMergedUsageOnly(String conf) {
      return this.node.isConfRequiredByMergedUsageOnly(this.rootModuleConf, conf);
   }
}
