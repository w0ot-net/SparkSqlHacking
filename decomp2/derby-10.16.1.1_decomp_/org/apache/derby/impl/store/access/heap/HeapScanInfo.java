package org.apache.derby.impl.store.access.heap;

import java.util.Properties;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.store.access.ScanInfo;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;

class HeapScanInfo implements ScanInfo {
   private int stat_numpages_visited = 0;
   private int stat_numrows_visited = 0;
   private int stat_numrows_qualified = 0;
   private int stat_numColumnsFetched = 0;
   private FormatableBitSet stat_validColumns = null;

   HeapScanInfo(HeapScan var1) {
      this.stat_numpages_visited = var1.getNumPagesVisited();
      this.stat_numrows_visited = var1.getNumRowsVisited();
      this.stat_numrows_qualified = var1.getNumRowsQualified();
      this.stat_validColumns = var1.getScanColumnList() == null ? null : (FormatableBitSet)var1.getScanColumnList().clone();
      if (this.stat_validColumns == null) {
         this.stat_numColumnsFetched = ((Heap)var1.getOpenConglom().getConglomerate()).format_ids.length;
      } else {
         for(int var2 = 0; var2 < this.stat_validColumns.size(); ++var2) {
            if (this.stat_validColumns.get(var2)) {
               ++this.stat_numColumnsFetched;
            }
         }
      }

   }

   public Properties getAllScanInfo(Properties var1) throws StandardException {
      if (var1 == null) {
         var1 = new Properties();
      }

      var1.put(MessageService.getTextMessage("XSAJ0.U", new Object[0]), MessageService.getTextMessage("XSAJG.U", new Object[0]));
      var1.put(MessageService.getTextMessage("XSAJ1.U", new Object[0]), Integer.toString(this.stat_numpages_visited));
      var1.put(MessageService.getTextMessage("XSAJ2.U", new Object[0]), Integer.toString(this.stat_numrows_visited));
      var1.put(MessageService.getTextMessage("XSAJ4.U", new Object[0]), Integer.toString(this.stat_numrows_qualified));
      var1.put(MessageService.getTextMessage("XSAJ5.U", new Object[0]), Integer.toString(this.stat_numColumnsFetched));
      var1.put(MessageService.getTextMessage("XSAJ6.U", new Object[0]), this.stat_validColumns == null ? MessageService.getTextMessage("XSAJE.U", new Object[0]) : this.stat_validColumns.toString());
      return var1;
   }
}
