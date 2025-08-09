package org.datanucleus.flush;

import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.scostore.ListStore;
import org.datanucleus.store.scostore.Store;

public class ListSetOperation implements SCOOperation {
   final ObjectProvider op;
   final int fieldNumber;
   final ListStore store;
   final int index;
   final Object value;
   boolean allowCascadeDelete = true;

   public ListSetOperation(ObjectProvider op, ListStore store, int index, Object value, boolean allowCascadeDelete) {
      this.op = op;
      this.fieldNumber = store.getOwnerMemberMetaData().getAbsoluteFieldNumber();
      this.store = store;
      this.index = index;
      this.value = value;
      this.allowCascadeDelete = allowCascadeDelete;
   }

   public ListSetOperation(ObjectProvider op, int fieldNum, int index, Object value, boolean allowCascadeDelete) {
      this.op = op;
      this.fieldNumber = fieldNum;
      this.store = null;
      this.index = index;
      this.value = value;
      this.allowCascadeDelete = allowCascadeDelete;
   }

   public AbstractMemberMetaData getMemberMetaData() {
      return this.store != null ? this.store.getOwnerMemberMetaData() : this.op.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(this.fieldNumber);
   }

   public void perform() {
      if (this.store != null) {
         this.store.set(this.op, this.index, this.value, this.allowCascadeDelete);
      }

   }

   public Store getStore() {
      return this.store;
   }

   public ObjectProvider getObjectProvider() {
      return this.op;
   }

   public String toString() {
      return "LIST SET : " + this.op + " field=" + this.getMemberMetaData().getName() + " index=" + this.index;
   }
}
