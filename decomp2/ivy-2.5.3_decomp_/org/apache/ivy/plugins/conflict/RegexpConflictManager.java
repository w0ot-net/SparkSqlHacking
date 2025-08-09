package org.apache.ivy.plugins.conflict;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.ivy.core.resolve.IvyNode;
import org.apache.ivy.util.Message;

public class RegexpConflictManager extends AbstractConflictManager {
   private Pattern pattern = Pattern.compile("(.*)");
   private boolean mIgnoreNonMatching;

   public void setRegexp(String regexp) {
      this.pattern = Pattern.compile(regexp);
      Matcher matcher = this.pattern.matcher("abcdef");
      if (matcher.groupCount() != 1) {
         String message = "Pattern does not contain ONE (capturing group): '" + this.pattern + "'";
         Message.error(message);
         throw new IllegalArgumentException(message);
      }
   }

   public void setIgnoreNonMatching(boolean ignoreNonMatching) {
      this.mIgnoreNonMatching = ignoreNonMatching;
   }

   public Collection resolveConflicts(IvyNode parent, Collection conflicts) {
      IvyNode lastNode = null;

      for(IvyNode node : conflicts) {
         if (lastNode != null && !this.matchEquals(node, lastNode)) {
            String msg = lastNode + ":" + this.getMatch(lastNode) + " (needed by " + Arrays.asList(lastNode.getAllRealCallers()) + ") conflicts with " + node + ":" + this.getMatch(node) + " (needed by " + Arrays.asList(node.getAllRealCallers()) + ")";
            throw new StrictConflictException(msg);
         }

         if (lastNode == null || this.nodeIsGreater(node, lastNode)) {
            lastNode = node;
         }
      }

      return Collections.singleton(lastNode);
   }

   private boolean nodeIsGreater(IvyNode node, IvyNode lastNode) {
      return this.getMatch(node).compareTo(this.getMatch(lastNode)) > 0;
   }

   private boolean matchEquals(IvyNode lastNode, IvyNode node) {
      return this.getMatch(lastNode).equals(this.getMatch(node));
   }

   private String getMatch(IvyNode node) {
      String revision = node.getId().getRevision();
      Matcher matcher = this.pattern.matcher(revision);
      if (matcher.matches()) {
         String match = matcher.group(1);
         if (match != null) {
            return match;
         }

         this.warnOrThrow("First group of pattern: '" + this.pattern + "' does not match: " + revision + " " + node);
      } else {
         this.warnOrThrow("Pattern: '" + this.pattern + "' does not match: " + revision + " " + node);
      }

      return revision;
   }

   private void warnOrThrow(String message) {
      if (this.mIgnoreNonMatching) {
         Message.warn(message);
      } else {
         throw new StrictConflictException(message);
      }
   }
}
