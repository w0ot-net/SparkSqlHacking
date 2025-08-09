package org.datanucleus.flush;

import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.scostore.ListStore;
import org.datanucleus.store.scostore.Store;

public class ListRemoveAtOperation extends CollectionRemoveOperation {
   final int index;

   public ListRemoveAtOperation(ObjectProvider op, ListStore store, int index) {
      super(op, store, (Object)null, true);
      this.index = index;
   }

   public ListRemoveAtOperation(ObjectProvider op, int fieldNum, int index, Object value) {
      super(op, fieldNum, value, true);
      this.index = index;
   }

   public AbstractMemberMetaData getMemberMetaData() {
      return this.store != null ? this.store.getOwnerMemberMetaData() : this.op.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(this.fieldNumber);
   }

   public void perform() {
      if (this.store != null) {
         ((ListStore)this.store).remove(this.op, this.index, -1);
      }

   }

   public Store getStore() {
      return this.store;
   }

   public ObjectProvider getObjectProvider() {
      return this.op;
   }

   public String toString() {
      return "LIST REMOVE-AT : " + this.op + " field=" + this.getMemberMetaData().getName() + " index=" + this.index;
   }
}
