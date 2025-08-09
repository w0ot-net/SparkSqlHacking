package org.datanucleus.flush;

import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.scostore.MapStore;
import org.datanucleus.store.scostore.Store;

public class MapPutOperation implements SCOOperation {
   final ObjectProvider op;
   final int fieldNumber;
   final MapStore store;
   final Object key;
   final Object value;

   public MapPutOperation(ObjectProvider op, MapStore store, Object key, Object value) {
      this.op = op;
      this.fieldNumber = store.getOwnerMemberMetaData().getAbsoluteFieldNumber();
      this.store = store;
      this.key = key;
      this.value = value;
   }

   public MapPutOperation(ObjectProvider op, int fieldNum, Object key, Object value) {
      this.op = op;
      this.fieldNumber = fieldNum;
      this.store = null;
      this.key = key;
      this.value = value;
   }

   public AbstractMemberMetaData getMemberMetaData() {
      return this.store != null ? this.store.getOwnerMemberMetaData() : this.op.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(this.fieldNumber);
   }

   public Object getKey() {
      return this.key;
   }

   public Object getValue() {
      return this.value;
   }

   public void perform() {
      if (this.store != null) {
         this.store.put(this.op, this.key, this.value);
      }

   }

   public Store getStore() {
      return this.store;
   }

   public ObjectProvider getObjectProvider() {
      return this.op;
   }

   public String toString() {
      return "MAP PUT : " + this.op + " field=" + this.getMemberMetaData().getName();
   }
}
