package org.apache.derby.impl.store.access.sort;

import java.util.Properties;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.store.access.BackingStoreHashtable;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.ScanInfo;
import org.apache.derby.iapi.store.access.conglomerate.ScanManager;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;

public abstract class Scan implements ScanManager, ScanInfo {
   public void didNotQualify() throws StandardException {
   }

   public int fetchNextGroup(DataValueDescriptor[][] var1, RowLocation[] var2) throws StandardException {
      throw StandardException.newException("XSAS0.S", new Object[0]);
   }

   public int fetchNextGroup(DataValueDescriptor[][] var1, RowLocation[] var2, RowLocation[] var3) throws StandardException {
      throw StandardException.newException("XSAS0.S", new Object[0]);
   }

   public void fetchSet(long var1, int[] var3, BackingStoreHashtable var4) throws StandardException {
      throw StandardException.newException("XSAS0.S", new Object[0]);
   }

   public boolean doesCurrentPositionQualify() throws StandardException {
      return true;
   }

   public void fetchLocation(RowLocation var1) throws StandardException {
      throw StandardException.newException("XSAS0.S", new Object[0]);
   }

   public ScanInfo getScanInfo() throws StandardException {
      return this;
   }

   public long getEstimatedRowCount() throws StandardException {
      throw StandardException.newException("XSAS0.S", new Object[0]);
   }

   public void setEstimatedRowCount(long var1) throws StandardException {
      throw StandardException.newException("XSAS0.S", new Object[0]);
   }

   public boolean isCurrentPositionDeleted() throws StandardException {
      throw StandardException.newException("XSAS0.S", new Object[0]);
   }

   public boolean isKeyed() {
      return false;
   }

   public boolean isTableLocked() {
      return true;
   }

   public boolean delete() throws StandardException {
      throw StandardException.newException("XSAS0.S", new Object[0]);
   }

   public void reopenScan(DataValueDescriptor[] var1, int var2, Qualifier[][] var3, DataValueDescriptor[] var4, int var5) throws StandardException {
      throw StandardException.newException("XSAS0.S", new Object[0]);
   }

   public void reopenScanByRowLocation(RowLocation var1, Qualifier[][] var2) throws StandardException {
      throw StandardException.newException("XSAS0.S", new Object[0]);
   }

   public boolean replace(DataValueDescriptor[] var1, FormatableBitSet var2) throws StandardException {
      throw StandardException.newException("XSAS0.S", new Object[0]);
   }

   public RowLocation newRowLocationTemplate() throws StandardException {
      throw StandardException.newException("XSAS0.S", new Object[0]);
   }

   public boolean positionAtRowLocation(RowLocation var1) throws StandardException {
      throw StandardException.newException("XSAS0.S", new Object[0]);
   }

   public Properties getAllScanInfo(Properties var1) throws StandardException {
      if (var1 == null) {
         var1 = new Properties();
      }

      var1.put(MessageService.getTextMessage("XSAJ0.U", new Object[0]), MessageService.getTextMessage("XSAJH.U", new Object[0]));
      return var1;
   }

   public boolean isHeldAfterCommit() throws StandardException {
      throw StandardException.newException("XSAS0.S", new Object[0]);
   }
}
