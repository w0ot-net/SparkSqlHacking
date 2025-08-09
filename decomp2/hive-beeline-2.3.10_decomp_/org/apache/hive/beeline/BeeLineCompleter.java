package org.apache.hive.beeline;

import java.util.List;
import jline.console.completer.Completer;

class BeeLineCompleter implements Completer {
   private final BeeLine beeLine;

   BeeLineCompleter(BeeLine beeLine) {
      this.beeLine = beeLine;
   }

   public int complete(String buf, int pos, List cand) {
      if (buf != null && buf.startsWith("!") && !buf.startsWith("!all") && !buf.startsWith("!sql")) {
         return this.beeLine.getCommandCompletor().complete(buf, pos, cand);
      } else {
         return this.beeLine.getDatabaseConnection() != null && this.beeLine.getDatabaseConnection().getSQLCompleter() != null ? this.beeLine.getDatabaseConnection().getSQLCompleter().complete(buf, pos, cand) : -1;
      }
   }
}
