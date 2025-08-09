package org.datanucleus.store.fieldmanager;

public class SingleTypeFieldManager implements FieldManager {
   private boolean booleanValue = false;
   private char charValue = 0;
   private byte byteValue = 0;
   private short shortValue = 0;
   private int intValue = 0;
   private long longValue = 0L;
   private float floatValue = 0.0F;
   private double doubleValue = (double)0.0F;
   private String stringValue = null;
   private Object objectValue = null;

   public SingleTypeFieldManager() {
   }

   public SingleTypeFieldManager(boolean booleanValue) {
      this.booleanValue = booleanValue;
   }

   public SingleTypeFieldManager(char charValue) {
      this.charValue = charValue;
   }

   public SingleTypeFieldManager(byte byteValue) {
      this.byteValue = byteValue;
   }

   public SingleTypeFieldManager(short shortValue) {
      this.shortValue = shortValue;
   }

   public SingleTypeFieldManager(int intValue) {
      this.intValue = intValue;
   }

   public SingleTypeFieldManager(long longValue) {
      this.longValue = longValue;
   }

   public SingleTypeFieldManager(float floatValue) {
      this.floatValue = floatValue;
   }

   public SingleTypeFieldManager(double doubleValue) {
      this.doubleValue = doubleValue;
   }

   public SingleTypeFieldManager(String stringValue) {
      this.stringValue = stringValue;
   }

   public SingleTypeFieldManager(Object objectValue) {
      this.objectValue = objectValue;
   }

   public void storeBooleanField(int fieldNum, boolean value) {
      this.booleanValue = value;
   }

   public boolean fetchBooleanField(int fieldNum) {
      return this.booleanValue;
   }

   public void storeCharField(int fieldNum, char value) {
      this.charValue = value;
   }

   public char fetchCharField(int fieldNum) {
      return this.charValue;
   }

   public void storeByteField(int fieldNum, byte value) {
      this.byteValue = value;
   }

   public byte fetchByteField(int fieldNum) {
      return this.byteValue;
   }

   public void storeShortField(int fieldNum, short value) {
      this.shortValue = value;
   }

   public short fetchShortField(int fieldNum) {
      return this.shortValue;
   }

   public void storeIntField(int fieldNum, int value) {
      this.intValue = value;
   }

   public int fetchIntField(int fieldNum) {
      return this.intValue;
   }

   public void storeLongField(int fieldNum, long value) {
      this.longValue = value;
   }

   public long fetchLongField(int fieldNum) {
      return this.longValue;
   }

   public void storeFloatField(int fieldNum, float value) {
      this.floatValue = value;
   }

   public float fetchFloatField(int fieldNum) {
      return this.floatValue;
   }

   public void storeDoubleField(int fieldNum, double value) {
      this.doubleValue = value;
   }

   public double fetchDoubleField(int fieldNum) {
      return this.doubleValue;
   }

   public void storeStringField(int fieldNum, String value) {
      this.stringValue = value;
   }

   public String fetchStringField(int fieldNum) {
      return this.stringValue;
   }

   public void storeObjectField(int fieldNum, Object value) {
      this.objectValue = value;
   }

   public Object fetchObjectField(int fieldNum) {
      return this.objectValue;
   }
}
