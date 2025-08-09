package org.datanucleus.store.fieldmanager;

public class SingleValueFieldManager implements FieldManager {
   private Object fieldValue = null;

   public void storeBooleanField(int fieldNumber, boolean value) {
      this.fieldValue = value ? Boolean.TRUE : Boolean.FALSE;
   }

   public boolean fetchBooleanField(int fieldNumber) {
      return (Boolean)this.fieldValue;
   }

   public void storeCharField(int fieldNumber, char value) {
      this.fieldValue = value;
   }

   public char fetchCharField(int fieldNumber) {
      return (Character)this.fieldValue;
   }

   public void storeByteField(int fieldNumber, byte value) {
      this.fieldValue = value;
   }

   public byte fetchByteField(int fieldNumber) {
      return (Byte)this.fieldValue;
   }

   public void storeShortField(int fieldNumber, short value) {
      this.fieldValue = value;
   }

   public short fetchShortField(int fieldNumber) {
      return (Short)this.fieldValue;
   }

   public void storeIntField(int fieldNumber, int value) {
      this.fieldValue = value;
   }

   public int fetchIntField(int fieldNumber) {
      return (Integer)this.fieldValue;
   }

   public void storeLongField(int fieldNumber, long value) {
      this.fieldValue = value;
   }

   public long fetchLongField(int fieldNumber) {
      return (Long)this.fieldValue;
   }

   public void storeFloatField(int fieldNumber, float value) {
      this.fieldValue = value;
   }

   public float fetchFloatField(int fieldNumber) {
      return (Float)this.fieldValue;
   }

   public void storeDoubleField(int fieldNumber, double value) {
      this.fieldValue = value;
   }

   public double fetchDoubleField(int fieldNumber) {
      return (Double)this.fieldValue;
   }

   public void storeStringField(int fieldNumber, String value) {
      this.fieldValue = value;
   }

   public String fetchStringField(int fieldNumber) {
      return (String)this.fieldValue;
   }

   public void storeObjectField(int fieldNumber, Object value) {
      this.fieldValue = value;
   }

   public Object fetchObjectField(int fieldNumber) {
      return this.fieldValue;
   }
}
