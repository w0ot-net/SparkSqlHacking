package org.datanucleus.flush;

import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.scostore.CollectionStore;
import org.datanucleus.store.scostore.Store;

public class CollectionClearOperation implements SCOOperation {
   final ObjectProvider op;
   final int fieldNumber;
   final CollectionStore store;

   public CollectionClearOperation(ObjectProvider op, CollectionStore store) {
      this.op = op;
      this.fieldNumber = store.getOwnerMemberMetaData().getAbsoluteFieldNumber();
      this.store = store;
   }

   public CollectionClearOperation(ObjectProvider op, int fieldNum) {
      this.op = op;
      this.fieldNumber = fieldNum;
      this.store = null;
   }

   public AbstractMemberMetaData getMemberMetaData() {
      return this.store != null ? this.store.getOwnerMemberMetaData() : this.op.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(this.fieldNumber);
   }

   public void perform() {
      if (this.store != null) {
         this.store.clear(this.op);
      }

   }

   public Store getStore() {
      return this.store;
   }

   public ObjectProvider getObjectProvider() {
      return this.op;
   }

   public String toString() {
      return "COLLECTION CLEAR : " + this.op + " field=" + this.getMemberMetaData().getName();
   }
}
