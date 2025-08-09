package org.apache.derby.impl.store.access.conglomerate;

import java.util.Properties;
import org.apache.derby.iapi.store.access.SpaceInfo;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

abstract class GenericController {
   protected OpenConglomerate open_conglom;

   protected void getRowPositionFromRowLocation(RowLocation var1, RowPosition var2) throws StandardException {
      throw StandardException.newException("XSCH8.S", new Object[0]);
   }

   protected void queueDeletePostCommitWork(RowPosition var1) throws StandardException {
      throw StandardException.newException("XSCH8.S", new Object[0]);
   }

   public void init(OpenConglomerate var1) throws StandardException {
      this.open_conglom = var1;
   }

   public OpenConglomerate getOpenConglom() {
      return this.open_conglom;
   }

   public void checkConsistency() throws StandardException {
      this.open_conglom.checkConsistency();
   }

   public void debugConglomerate() throws StandardException {
      this.open_conglom.debugConglomerate();
   }

   public void getTableProperties(Properties var1) throws StandardException {
      this.open_conglom.getTableProperties(var1);
   }

   public Properties getInternalTablePropertySet(Properties var1) throws StandardException {
      return this.open_conglom.getInternalTablePropertySet(var1);
   }

   public SpaceInfo getSpaceInfo() throws StandardException {
      return this.open_conglom.getSpaceInfo();
   }

   public void close() throws StandardException {
      if (this.open_conglom != null) {
         this.open_conglom.close();
      }

   }

   public boolean isKeyed() {
      return this.open_conglom.isKeyed();
   }

   public RowLocation newRowLocationTemplate() throws StandardException {
      if (this.open_conglom.isClosed()) {
         this.open_conglom.reopen();
      }

      return this.open_conglom.newRowLocationTemplate();
   }

   public boolean isTableLocked() {
      return this.open_conglom.isTableLocked();
   }

   public long getEstimatedRowCount() throws StandardException {
      if (this.open_conglom.isClosed()) {
         this.open_conglom.reopen();
      }

      long var1 = this.open_conglom.getContainer().getEstimatedRowCount(0);
      return var1 == 0L ? 1L : var1;
   }

   public void setEstimatedRowCount(long var1) throws StandardException {
      ContainerHandle var3 = this.open_conglom.getContainer();
      if (var3 == null) {
         this.open_conglom.reopen();
      }

      var3 = this.open_conglom.getContainer();
      if (var3 != null) {
         var3.setEstimatedRowCount(var1, 0);
      }

   }
}
