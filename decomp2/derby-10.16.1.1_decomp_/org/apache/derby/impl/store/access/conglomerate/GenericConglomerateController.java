package org.apache.derby.impl.store.access.conglomerate;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.conglomerate.LogicalUndo;
import org.apache.derby.iapi.store.raw.FetchDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

public abstract class GenericConglomerateController extends GenericController implements ConglomerateController {
   public void close() throws StandardException {
      super.close();
      if (this.open_conglom != null && this.open_conglom.getXactMgr() != null) {
         this.open_conglom.getXactMgr().closeMe((ConglomerateController)this);
      }

   }

   public boolean closeForEndTransaction(boolean var1) throws StandardException {
      super.close();
      if (this.open_conglom.getHold() && !var1) {
         return false;
      } else {
         if (this.open_conglom != null && this.open_conglom.getXactMgr() != null) {
            this.open_conglom.getXactMgr().closeMe((ConglomerateController)this);
         }

         return true;
      }
   }

   public boolean delete(RowLocation var1) throws StandardException {
      if (this.open_conglom.isClosed()) {
         if (!this.open_conglom.getHold()) {
            throw StandardException.newException("XSCH6.S", new Object[]{this.open_conglom.getConglomerate().getId()});
         }

         if (this.open_conglom.isClosed()) {
            this.open_conglom.reopen();
         }
      }

      RowPosition var2 = this.open_conglom.getRuntimeMem().get_scratch_row_position();
      this.getRowPositionFromRowLocation(var1, var2);
      if (!this.open_conglom.latchPage(var2)) {
         return false;
      } else {
         this.open_conglom.lockPositionForWrite(var2, true);
         boolean var3 = true;
         if (var2.current_page.isDeletedAtSlot(var2.current_slot)) {
            var3 = false;
         } else {
            var2.current_page.deleteAtSlot(var2.current_slot, true, (LogicalUndo)null);
            if (var2.current_page.shouldReclaimSpace(var2.current_page.getPageNumber() == 1L ? 1 : 0, var2.current_slot)) {
               this.queueDeletePostCommitWork(var2);
            }
         }

         var2.current_page.unlatch();
         return var3;
      }
   }

   public boolean fetch(RowLocation var1, DataValueDescriptor[] var2, FormatableBitSet var3) throws StandardException {
      if (this.open_conglom.isClosed()) {
         if (!this.open_conglom.getHold()) {
            throw StandardException.newException("XSCH6.S", new Object[]{this.open_conglom.getConglomerate().getId()});
         }

         if (this.open_conglom.isClosed()) {
            this.open_conglom.reopen();
         }
      }

      RowPosition var4 = this.open_conglom.getRuntimeMem().get_scratch_row_position();
      this.getRowPositionFromRowLocation(var1, var4);
      if (!this.open_conglom.latchPage(var4)) {
         return false;
      } else {
         if (this.open_conglom.isForUpdate()) {
            this.open_conglom.lockPositionForWrite(var4, true);
         } else {
            this.open_conglom.lockPositionForRead(var4, (RowPosition)null, false, true);
         }

         if (var4.current_page == null) {
            return false;
         } else {
            boolean var5 = var4.current_page.fetchFromSlot(var4.current_rh, var4.current_slot, var2, new FetchDescriptor(var2.length, var3, (Qualifier[][])null), false) != null;
            if (!this.open_conglom.isForUpdate()) {
               this.open_conglom.unlockPositionAfterRead(var4);
            }

            var4.current_page.unlatch();
            return var5;
         }
      }
   }

   public boolean fetch(RowLocation var1, DataValueDescriptor[] var2, FormatableBitSet var3, boolean var4) throws StandardException {
      if (this.open_conglom.isClosed()) {
         if (!this.open_conglom.getHold()) {
            throw StandardException.newException("XSCH6.S", new Object[]{this.open_conglom.getConglomerate().getId()});
         }

         if (this.open_conglom.isClosed()) {
            this.open_conglom.reopen();
         }
      }

      RowPosition var5 = this.open_conglom.getRuntimeMem().get_scratch_row_position();
      this.getRowPositionFromRowLocation(var1, var5);
      if (!this.open_conglom.latchPage(var5)) {
         return false;
      } else {
         if (this.open_conglom.isForUpdate()) {
            this.open_conglom.lockPositionForWrite(var5, var4);
         } else {
            this.open_conglom.lockPositionForRead(var5, (RowPosition)null, false, var4);
         }

         if (var5.current_page == null) {
            return false;
         } else {
            boolean var6 = var5.current_page.fetchFromSlot(var5.current_rh, var5.current_slot, var2, new FetchDescriptor(var2.length, var3, (Qualifier[][])null), false) != null;
            if (!this.open_conglom.isForUpdate()) {
               this.open_conglom.unlockPositionAfterRead(var5);
            }

            var5.current_page.unlatch();
            return var6;
         }
      }
   }

   public boolean replace(RowLocation var1, DataValueDescriptor[] var2, FormatableBitSet var3) throws StandardException {
      if (this.open_conglom.isClosed()) {
         if (!this.open_conglom.getHold()) {
            throw StandardException.newException("XSCH6.S", new Object[]{this.open_conglom.getConglomerate().getId()});
         }

         if (this.open_conglom.isClosed()) {
            this.open_conglom.reopen();
         }
      }

      RowPosition var4 = this.open_conglom.getRuntimeMem().get_scratch_row_position();
      this.getRowPositionFromRowLocation(var1, var4);
      if (!this.open_conglom.latchPage(var4)) {
         return false;
      } else {
         this.open_conglom.lockPositionForWrite(var4, true);
         boolean var5 = true;
         if (var4.current_page.isDeletedAtSlot(var4.current_slot)) {
            var5 = false;
         } else {
            var4.current_page.updateAtSlot(var4.current_slot, var2, var3);
         }

         var4.current_page.unlatch();
         return var5;
      }
   }
}
