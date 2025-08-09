package org.datanucleus.state;

import org.datanucleus.api.ApiAdapter;
import org.datanucleus.enhancement.Persistable;
import org.datanucleus.store.fieldmanager.FieldManager;

public class AppIdObjectIdFieldConsumer implements FieldManager, Persistable.ObjectIdFieldConsumer {
   ApiAdapter api;
   FieldManager fm;

   public AppIdObjectIdFieldConsumer(ApiAdapter api, FieldManager fm) {
      this.api = api;
      this.fm = fm;
   }

   public void storeBooleanField(int fieldNumber, boolean value) {
      this.fm.storeBooleanField(fieldNumber, value);
   }

   public void storeByteField(int fieldNumber, byte value) {
      this.fm.storeByteField(fieldNumber, value);
   }

   public void storeCharField(int fieldNumber, char value) {
      this.fm.storeCharField(fieldNumber, value);
   }

   public void storeDoubleField(int fieldNumber, double value) {
      this.fm.storeDoubleField(fieldNumber, value);
   }

   public void storeFloatField(int fieldNumber, float value) {
      this.fm.storeFloatField(fieldNumber, value);
   }

   public void storeIntField(int fieldNumber, int value) {
      this.fm.storeIntField(fieldNumber, value);
   }

   public void storeLongField(int fieldNumber, long value) {
      this.fm.storeLongField(fieldNumber, value);
   }

   public void storeShortField(int fieldNumber, short value) {
      this.fm.storeShortField(fieldNumber, value);
   }

   public void storeStringField(int fieldNumber, String value) {
      this.fm.storeStringField(fieldNumber, value);
   }

   public void storeObjectField(int fieldNumber, Object value) {
      if (this.api.isPersistable(value)) {
         Persistable pc = (Persistable)value;
         pc.dnCopyKeyFieldsFromObjectId(this, pc.dnGetObjectId());
      } else {
         this.fm.storeObjectField(fieldNumber, value);
      }
   }

   public boolean fetchBooleanField(int fieldNumber) {
      return this.fm.fetchBooleanField(fieldNumber);
   }

   public byte fetchByteField(int fieldNumber) {
      return this.fm.fetchByteField(fieldNumber);
   }

   public char fetchCharField(int fieldNumber) {
      return this.fm.fetchCharField(fieldNumber);
   }

   public double fetchDoubleField(int fieldNumber) {
      return this.fm.fetchDoubleField(fieldNumber);
   }

   public float fetchFloatField(int fieldNumber) {
      return this.fm.fetchFloatField(fieldNumber);
   }

   public int fetchIntField(int fieldNumber) {
      return this.fm.fetchIntField(fieldNumber);
   }

   public long fetchLongField(int fieldNumber) {
      return this.fm.fetchLongField(fieldNumber);
   }

   public short fetchShortField(int fieldNumber) {
      return this.fm.fetchShortField(fieldNumber);
   }

   public String fetchStringField(int fieldNumber) {
      return this.fm.fetchStringField(fieldNumber);
   }

   public Object fetchObjectField(int fieldNumber) {
      return this.fm.fetchObjectField(fieldNumber);
   }
}
