package org.apache.ivy.plugins.conflict;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.apache.ivy.core.resolve.IvyNode;

public class FixedConflictManager extends AbstractConflictManager {
   private Collection revisions;

   public FixedConflictManager(String[] revs) {
      this.revisions = Arrays.asList(revs);
      this.setName("fixed" + this.revisions);
   }

   public Collection resolveConflicts(IvyNode parent, Collection conflicts) {
      Collection<IvyNode> resolved = new ArrayList(conflicts.size());

      for(IvyNode node : conflicts) {
         String revision = node.getResolvedId().getRevision();
         if (this.revisions.contains(revision)) {
            resolved.add(node);
         }
      }

      return resolved;
   }

   public Collection getRevs() {
      return this.revisions;
   }
}
