package org.apache.ivy.plugins.conflict;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.resolve.IvyNode;
import org.apache.ivy.plugins.latest.ArtifactInfo;
import org.apache.ivy.plugins.latest.LatestStrategy;
import org.apache.ivy.util.Message;

public class LatestConflictManager extends AbstractConflictManager {
   private LatestStrategy strategy;
   private String strategyName;

   public LatestConflictManager() {
   }

   public LatestConflictManager(LatestStrategy strategy) {
      this.strategy = strategy;
   }

   public LatestConflictManager(String name, LatestStrategy strategy) {
      this.setName(name);
      this.strategy = strategy;
   }

   public Collection resolveConflicts(IvyNode parent, Collection conflicts) {
      if (conflicts.size() < 2) {
         return conflicts;
      } else {
         for(IvyNode node : conflicts) {
            DependencyDescriptor dd = node.getDependencyDescriptor(parent);
            if (dd != null && dd.isForce() && parent.getResolvedId().equals(dd.getParentRevisionId())) {
               return Collections.singleton(node);
            }
         }

         for(IvyNode node : conflicts) {
            ModuleRevisionId modRev = node.getResolvedId();
            if (this.getSettings().getVersionMatcher().isDynamic(modRev)) {
               return null;
            }
         }

         List<IvyNode> unevicted = new ArrayList();

         for(IvyNode node : conflicts) {
            if (!node.isCompletelyEvicted()) {
               unevicted.add(node);
            }
         }

         if (unevicted.size() > 0) {
            conflicts = unevicted;
         }

         try {
            IvyNodeArtifactInfo latest = (IvyNodeArtifactInfo)this.getStrategy().findLatest(this.toArtifactInfo(conflicts), (Date)null);
            if (latest != null) {
               return Collections.singleton(latest.getNode());
            } else {
               return conflicts;
            }
         } catch (NoConflictResolvedYetException var6) {
            return null;
         }
      }
   }

   protected ArtifactInfo[] toArtifactInfo(Collection conflicts) {
      List<ArtifactInfo> artifacts = new ArrayList(conflicts.size());

      for(IvyNode node : conflicts) {
         artifacts.add(new IvyNodeArtifactInfo(node));
      }

      return (ArtifactInfo[])artifacts.toArray(new ArtifactInfo[artifacts.size()]);
   }

   public LatestStrategy getStrategy() {
      if (this.strategy == null) {
         if (this.strategyName != null) {
            this.strategy = this.getSettings().getLatestStrategy(this.strategyName);
            if (this.strategy == null) {
               Message.error("unknown latest strategy: " + this.strategyName);
               this.strategy = this.getSettings().getDefaultLatestStrategy();
            }
         } else {
            this.strategy = this.getSettings().getDefaultLatestStrategy();
         }
      }

      return this.strategy;
   }

   public void setLatest(String strategyName) {
      this.strategyName = strategyName;
   }

   public void setStrategy(LatestStrategy strategy) {
      this.strategy = strategy;
   }

   public String toString() {
      return this.strategy != null ? String.valueOf(this.strategy) : this.strategyName;
   }

   public static class NoConflictResolvedYetException extends RuntimeException {
   }

   protected static final class IvyNodeArtifactInfo implements ArtifactInfo {
      private final IvyNode node;

      private IvyNodeArtifactInfo(IvyNode dep) {
         this.node = dep;
      }

      public long getLastModified() {
         long lastModified = this.node.getLastModified();
         if (lastModified == 0L) {
            throw new NoConflictResolvedYetException();
         } else {
            return lastModified;
         }
      }

      public String getRevision() {
         return this.node.getResolvedId().getRevision();
      }

      public IvyNode getNode() {
         return this.node;
      }
   }
}
