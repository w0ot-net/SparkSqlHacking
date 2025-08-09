package org.apache.ivy.plugins.conflict;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Stack;
import org.apache.ivy.core.IvyContext;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.resolve.IvyNode;
import org.apache.ivy.core.resolve.IvyNodeBlacklist;
import org.apache.ivy.core.resolve.IvyNodeCallers;
import org.apache.ivy.core.resolve.IvyNodeEviction;
import org.apache.ivy.core.resolve.ResolveData;
import org.apache.ivy.core.resolve.RestartResolveProcess;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.ivy.plugins.latest.LatestStrategy;
import org.apache.ivy.plugins.version.VersionMatcher;
import org.apache.ivy.util.Message;

public class LatestCompatibleConflictManager extends LatestConflictManager {
   public LatestCompatibleConflictManager() {
   }

   public LatestCompatibleConflictManager(String name, LatestStrategy strategy) {
      super(name, strategy);
   }

   public Collection resolveConflicts(IvyNode parent, Collection conflicts) {
      if (conflicts.size() < 2) {
         return conflicts;
      } else {
         VersionMatcher versionMatcher = this.getSettings().getVersionMatcher();
         Iterator<IvyNode> iter = conflicts.iterator();
         IvyNode node = (IvyNode)iter.next();
         ModuleRevisionId mrid = node.getResolvedId();
         if (!versionMatcher.isDynamic(mrid)) {
            while(iter.hasNext()) {
               IvyNode other = (IvyNode)iter.next();
               if (!versionMatcher.accept(other.getResolvedId(), mrid) && !this.handleIncompatibleConflict(parent, conflicts, node, other)) {
                  return null;
               }
            }

            return Collections.singleton(node);
         } else {
            while(iter.hasNext()) {
               IvyNode other = (IvyNode)iter.next();
               if (versionMatcher.isDynamic(other.getResolvedId()) || !versionMatcher.accept(mrid, other.getResolvedId()) && !this.handleIncompatibleConflict(parent, conflicts, node, other)) {
                  return null;
               }
            }

            if (conflicts.size() == 2) {
               Iterator<IvyNode> it = conflicts.iterator();
               it.next();
               return Collections.singleton(it.next());
            } else {
               Collection<IvyNode> newConflicts = new LinkedHashSet(conflicts);
               newConflicts.remove(node);
               return super.resolveConflicts(parent, newConflicts);
            }
         }
      }
   }

   private boolean handleIncompatibleConflict(IvyNode parent, Collection conflicts, IvyNode node, IvyNode other) {
      try {
         LatestConflictManager.IvyNodeArtifactInfo latest = (LatestConflictManager.IvyNodeArtifactInfo)this.getStrategy().findLatest(this.toArtifactInfo(Arrays.asList(node, other)), (Date)null);
         if (latest != null) {
            IvyNode latestNode = latest.getNode();
            IvyNode oldestNode = latestNode == node ? other : node;
            this.blackListIncompatibleCallerAndRestartResolveIfPossible(this.getSettings(), parent, oldestNode, latestNode);
            this.blackListIncompatibleCallerAndRestartResolveIfPossible(this.getSettings(), parent, latestNode, oldestNode);
            this.handleUnsolvableConflict(parent, conflicts, node, other);
            return true;
         } else {
            return false;
         }
      } catch (LatestConflictManager.NoConflictResolvedYetException var8) {
         return false;
      }
   }

   private void blackListIncompatibleCallerAndRestartResolveIfPossible(IvySettings settings, IvyNode parent, IvyNode selected, IvyNode evicted) {
      Stack<IvyNode> callerStack = new Stack();
      callerStack.push(evicted);
      Collection<IvyNodeBlacklist> toBlacklist = this.blackListIncompatibleCaller(settings.getVersionMatcher(), parent, selected, evicted, callerStack);
      if (toBlacklist != null) {
         StringBuilder blacklisted = new StringBuilder();

         for(IvyNodeBlacklist blacklist : toBlacklist) {
            if (blacklisted.length() > 0) {
               blacklisted.append(" ");
            }

            IvyNode blacklistedNode = blacklist.getBlacklistedNode();
            blacklistedNode.blacklist(blacklist);
            blacklisted.append(blacklistedNode);
         }

         String rootModuleConf = parent.getData().getReport().getConfiguration();
         evicted.markEvicted(new IvyNodeEviction.EvictionData(rootModuleConf, parent, this, Collections.singleton(selected), "with blacklisting of " + blacklisted));
         if (settings.debugConflictResolution()) {
            Message.debug("evicting " + evicted + " by " + evicted.getEvictedData(rootModuleConf));
         }

         throw new RestartResolveProcess("trying to handle incompatibilities between " + selected + " and " + evicted);
      }
   }

   private boolean handleIncompatibleCaller(Stack callerStack, IvyNode node, IvyNode callerNode, IvyNode conflictParent, IvyNode selectedNode, IvyNode evictedNode, Collection blacklisted, VersionMatcher versionMatcher) {
      if (callerStack.subList(0, callerStack.size() - 1).contains(node)) {
         return true;
      } else {
         callerStack.push(callerNode);
         Collection<IvyNodeBlacklist> sub = this.blackListIncompatibleCaller(versionMatcher, conflictParent, selectedNode, evictedNode, callerStack);
         callerStack.pop();
         if (sub == null) {
            return false;
         } else {
            blacklisted.addAll(sub);
            return true;
         }
      }
   }

   private Collection blackListIncompatibleCaller(VersionMatcher versionMatcher, IvyNode conflictParent, IvyNode selectedNode, IvyNode evictedNode, Stack callerStack) {
      Collection<IvyNodeBlacklist> blacklisted = new ArrayList();
      IvyNode node = (IvyNode)callerStack.peek();
      String rootModuleConf = conflictParent.getData().getReport().getConfiguration();

      for(IvyNodeCallers.Caller caller : node.getCallers(rootModuleConf)) {
         IvyNode callerNode = node.findNode(caller.getModuleRevisionId());
         if (!callerNode.isBlacklisted(rootModuleConf)) {
            if (versionMatcher.isDynamic(caller.getAskedDependencyId())) {
               blacklisted.add(new IvyNodeBlacklist(conflictParent, selectedNode, evictedNode, node, rootModuleConf));
               if (node.isEvicted(rootModuleConf) && !this.handleIncompatibleCaller(callerStack, node, callerNode, conflictParent, selectedNode, evictedNode, blacklisted, versionMatcher)) {
                  return null;
               }
            } else if (!this.handleIncompatibleCaller(callerStack, node, callerNode, conflictParent, selectedNode, evictedNode, blacklisted, versionMatcher)) {
               return null;
            }
         }
      }

      if (blacklisted.isEmpty() && !callerStack.subList(0, callerStack.size() - 1).contains(node)) {
         return null;
      } else {
         return blacklisted;
      }
   }

   protected void handleUnsolvableConflict(IvyNode parent, Collection conflicts, IvyNode node1, IvyNode node2) {
      throw new StrictConflictException(node1, node2);
   }

   public void handleAllBlacklistedRevisions(DependencyDescriptor dd, Collection foundBlacklisted) {
      ResolveData resolveData = IvyContext.getContext().getResolveData();
      Collection<IvyNode> blacklisted = new HashSet();

      for(ModuleRevisionId mrid : foundBlacklisted) {
         blacklisted.add(resolveData.getNode(mrid));
      }

      for(IvyNode node : blacklisted) {
         IvyNodeBlacklist bdata = node.getBlacklistData(resolveData.getReport().getConfiguration());
         this.handleUnsolvableConflict(bdata.getConflictParent(), Arrays.asList(bdata.getEvictedNode(), bdata.getSelectedNode()), bdata.getEvictedNode(), bdata.getSelectedNode());
      }

   }

   public String toString() {
      return this.getName();
   }
}
