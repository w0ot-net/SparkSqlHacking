package org.apache.derby.impl.store.access.btree;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.raw.FetchDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.impl.store.access.conglomerate.RowPosition;
import org.apache.derby.shared.common.error.StandardException;

public class BTreeRowPosition extends RowPosition {
   public DataValueDescriptor[] current_positionKey;
   public LeafControlRow current_leaf;
   protected LeafControlRow next_leaf;
   public DataValueDescriptor[] current_lock_template;
   public RowLocation current_lock_row_loc;
   private final BTreeScan parent;
   long versionWhenSaved;
   private DataValueDescriptor[] positionKey_template;
   private FetchDescriptor savedFetchDescriptor;

   public BTreeRowPosition(BTreeScan var1) {
      this.parent = var1;
   }

   public void init() {
      super.init();
      this.current_leaf = null;
      this.current_positionKey = null;
   }

   public final void unlatch() {
      if (this.current_leaf != null) {
         this.current_leaf.release();
         this.current_leaf = null;
      }

      this.current_slot = -1;
   }

   public void saveMeAndReleasePage() throws StandardException {
      this.parent.savePositionAndReleasePage();
   }

   DataValueDescriptor[] getKeyTemplate() throws StandardException {
      if (this.positionKey_template == null) {
         this.positionKey_template = this.parent.getRuntimeMem().get_row_for_export(this.parent.getRawTran());
      }

      return this.positionKey_template;
   }

   FetchDescriptor getFetchDescriptorForSaveKey(int[] var1, int var2) {
      if (this.savedFetchDescriptor == null) {
         FormatableBitSet var3 = new FormatableBitSet(var2);

         for(int var4 = 0; var4 < var1.length; ++var4) {
            if (var1[var4] == 0) {
               var3.set(var4);
            }
         }

         for(int var5 = var1.length; var5 < var2; ++var5) {
            var3.set(var5);
         }

         this.savedFetchDescriptor = new FetchDescriptor(var2, var3, (Qualifier[][])null);
      }

      return this.savedFetchDescriptor;
   }

   public final String toString() {
      Object var1 = null;
      return (String)var1;
   }
}
