package org.apache.derby.impl.store.access.sort;

import java.util.Properties;
import java.util.Vector;
import org.apache.derby.iapi.store.access.SortInfo;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;

class MergeSortInfo implements SortInfo {
   private String stat_sortType;
   private int stat_numRowsInput;
   private int stat_numRowsOutput;
   private int stat_numMergeRuns;
   private Vector stat_mergeRunsSize;

   MergeSortInfo(MergeInserter var1) {
      this.stat_sortType = var1.stat_sortType;
      this.stat_numRowsInput = var1.stat_numRowsInput;
      this.stat_numRowsOutput = var1.stat_numRowsOutput;
      this.stat_numMergeRuns = var1.stat_numMergeRuns;
      this.stat_mergeRunsSize = var1.stat_mergeRunsSize;
   }

   public Properties getAllSortInfo(Properties var1) throws StandardException {
      if (var1 == null) {
         var1 = new Properties();
      }

      var1.put(MessageService.getTextMessage("XSAJ8.U", new Object[0]), "external".equals(this.stat_sortType) ? MessageService.getTextMessage("XSAJI.U", new Object[0]) : MessageService.getTextMessage("XSAJJ.U", new Object[0]));
      var1.put(MessageService.getTextMessage("XSAJA.U", new Object[0]), Integer.toString(this.stat_numRowsInput));
      var1.put(MessageService.getTextMessage("XSAJB.U", new Object[0]), Integer.toString(this.stat_numRowsOutput));
      if (this.stat_sortType == "external") {
         var1.put(MessageService.getTextMessage("XSAJC.U", new Object[0]), Integer.toString(this.stat_numMergeRuns));
         var1.put(MessageService.getTextMessage("XSAJD.U", new Object[0]), this.stat_mergeRunsSize.toString());
      }

      return var1;
   }
}
