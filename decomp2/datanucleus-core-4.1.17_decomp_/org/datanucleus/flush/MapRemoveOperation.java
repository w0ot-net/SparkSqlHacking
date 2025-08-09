package org.datanucleus.flush;

import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.scostore.MapStore;
import org.datanucleus.store.scostore.Store;

public class MapRemoveOperation implements SCOOperation {
   final ObjectProvider op;
   final int fieldNumber;
   final MapStore store;
   final Object key;
   final Object value;

   public MapRemoveOperation(ObjectProvider op, MapStore store, Object key, Object val) {
      this.op = op;
      this.fieldNumber = store.getOwnerMemberMetaData().getAbsoluteFieldNumber();
      this.store = store;
      this.key = key;
      this.value = val;
   }

   public MapRemoveOperation(ObjectProvider op, int fieldNum, Object key, Object val) {
      this.op = op;
      this.fieldNumber = fieldNum;
      this.store = null;
      this.key = key;
      this.value = val;
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
         if (this.value != null) {
            this.store.remove(this.op, this.key, this.value);
         } else {
            this.store.remove(this.op, this.key);
         }
      }

   }

   public Store getStore() {
      return this.store;
   }

   public ObjectProvider getObjectProvider() {
      return this.op;
   }

   public String toString() {
      return "MAP REMOVE : " + this.op + " field=" + this.getMemberMetaData().getName();
   }
}
