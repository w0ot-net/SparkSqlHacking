package org.apache.ivy.core.resolve;

public class IvyNodeBlacklist {
   private IvyNode conflictParent;
   private IvyNode selectedNode;
   private IvyNode evictedNode;
   private IvyNode blacklistedNode;
   private String rootModuleConf;

   public IvyNodeBlacklist(IvyNode conflictParent, IvyNode selectedNode, IvyNode evictedNode, IvyNode blacklistedNode, String rootModuleConf) {
      this.conflictParent = conflictParent;
      this.selectedNode = selectedNode;
      this.evictedNode = evictedNode;
      this.blacklistedNode = blacklistedNode;
      this.rootModuleConf = rootModuleConf;
   }

   public IvyNode getConflictParent() {
      return this.conflictParent;
   }

   public IvyNode getSelectedNode() {
      return this.selectedNode;
   }

   public IvyNode getEvictedNode() {
      return this.evictedNode;
   }

   public IvyNode getBlacklistedNode() {
      return this.blacklistedNode;
   }

   public String getRootModuleConf() {
      return this.rootModuleConf;
   }

   public String toString() {
      return "[" + this.blacklistedNode + " blacklisted to evict " + this.evictedNode + " in favor of " + this.selectedNode + " in " + this.conflictParent + " for " + this.rootModuleConf + "]";
   }
}
