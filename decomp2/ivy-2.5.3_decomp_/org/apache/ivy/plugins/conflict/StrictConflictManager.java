package org.apache.ivy.plugins.conflict;

import java.util.Collection;
import java.util.Collections;
import org.apache.ivy.core.resolve.IvyNode;
import org.apache.ivy.plugins.version.VersionMatcher;

public class StrictConflictManager extends AbstractConflictManager {
   public Collection resolveConflicts(IvyNode parent, Collection conflicts) {
      VersionMatcher versionMatcher = this.getSettings().getVersionMatcher();
      IvyNode lastNode = null;

      for(IvyNode node : conflicts) {
         if (versionMatcher.isDynamic(node.getResolvedId())) {
            return null;
         }

         if (lastNode != null && !lastNode.equals(node)) {
            throw new StrictConflictException(lastNode, node);
         }

         lastNode = node;
      }

      return Collections.singleton(lastNode);
   }
}
