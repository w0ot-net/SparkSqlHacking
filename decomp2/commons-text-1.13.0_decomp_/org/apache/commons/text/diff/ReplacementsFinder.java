package org.apache.commons.text.diff;

import java.util.ArrayList;
import java.util.List;

public class ReplacementsFinder implements CommandVisitor {
   private final List pendingInsertions = new ArrayList();
   private final List pendingDeletions = new ArrayList();
   private int skipped = 0;
   private final ReplacementsHandler handler;

   public ReplacementsFinder(ReplacementsHandler handler) {
      this.handler = handler;
   }

   public void visitDeleteCommand(Object object) {
      this.pendingDeletions.add(object);
   }

   public void visitInsertCommand(Object object) {
      this.pendingInsertions.add(object);
   }

   public void visitKeepCommand(Object object) {
      if (this.pendingDeletions.isEmpty() && this.pendingInsertions.isEmpty()) {
         ++this.skipped;
      } else {
         this.handler.handleReplacement(this.skipped, this.pendingDeletions, this.pendingInsertions);
         this.pendingDeletions.clear();
         this.pendingInsertions.clear();
         this.skipped = 1;
      }

   }
}
