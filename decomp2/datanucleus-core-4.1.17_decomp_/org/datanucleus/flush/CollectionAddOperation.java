package org.datanucleus.flush;

import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.scostore.CollectionStore;
import org.datanucleus.store.scostore.Store;
import org.datanucleus.util.StringUtils;

public class CollectionAddOperation implements SCOOperation {
   final ObjectProvider op;
   final int fieldNumber;
   final CollectionStore store;
   final Object value;

   public CollectionAddOperation(ObjectProvider op, CollectionStore store, Object value) {
      this.op = op;
      this.fieldNumber = store.getOwnerMemberMetaData().getAbsoluteFieldNumber();
      this.store = store;
      this.value = value;
   }

   public CollectionAddOperation(ObjectProvider op, int fieldNum, Object value) {
      this.op = op;
      this.fieldNumber = fieldNum;
      this.store = null;
      this.value = value;
   }

   public AbstractMemberMetaData getMemberMetaData() {
      return this.store != null ? this.store.getOwnerMemberMetaData() : this.op.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(this.fieldNumber);
   }

   public Object getValue() {
      return this.value;
   }

   public void perform() {
      if (this.store != null) {
         this.store.add(this.op, this.value, -1);
      }

   }

   public Store getStore() {
      return this.store;
   }

   public ObjectProvider getObjectProvider() {
      return this.op;
   }

   public String toString() {
      return "COLLECTION ADD : " + this.op + " field=" + this.getMemberMetaData().getName() + " value=" + StringUtils.toJVMIDString(this.value);
   }
}
