package org.apache.hive.beeline;

import java.util.List;
import jline.console.completer.Completer;
import jline.console.completer.StringsCompleter;

class TableNameCompletor implements Completer {
   private final BeeLine beeLine;

   TableNameCompletor(BeeLine beeLine) {
      this.beeLine = beeLine;
   }

   public int complete(String buf, int pos, List cand) {
      return this.beeLine.getDatabaseConnection() == null ? -1 : (new StringsCompleter(this.beeLine.getDatabaseConnection().getTableNames(true))).complete(buf, pos, cand);
   }
}
