package org.datanucleus.store.fieldmanager;

import org.datanucleus.exceptions.NucleusException;

public abstract class AbstractFieldManager implements FieldManager {
   private String failureMessage(String method) {
      return "Somehow " + this.getClass().getName() + "." + method + "() was called, which should have been impossible";
   }

   public void storeBooleanField(int fieldNumber, boolean value) {
      throw (new NucleusException(this.failureMessage("storeBooleanField"))).setFatal();
   }

   public boolean fetchBooleanField(int fieldNumber) {
      throw (new NucleusException(this.failureMessage("fetchBooleanField"))).setFatal();
   }

   public void storeCharField(int fieldNumber, char value) {
      throw (new NucleusException(this.failureMessage("storeCharField"))).setFatal();
   }

   public char fetchCharField(int fieldNumber) {
      throw (new NucleusException(this.failureMessage("fetchCharField"))).setFatal();
   }

   public void storeByteField(int fieldNumber, byte value) {
      throw (new NucleusException(this.failureMessage("storeByteField"))).setFatal();
   }

   public byte fetchByteField(int fieldNumber) {
      throw (new NucleusException(this.failureMessage("fetchByteField"))).setFatal();
   }

   public void storeShortField(int fieldNumber, short value) {
      throw (new NucleusException(this.failureMessage("storeShortField"))).setFatal();
   }

   public short fetchShortField(int fieldNumber) {
      throw (new NucleusException(this.failureMessage("fetchShortField"))).setFatal();
   }

   public void storeIntField(int fieldNumber, int value) {
      throw (new NucleusException(this.failureMessage("storeIntField"))).setFatal();
   }

   public int fetchIntField(int fieldNumber) {
      throw (new NucleusException(this.failureMessage("fetchIntField"))).setFatal();
   }

   public void storeLongField(int fieldNumber, long value) {
      throw (new NucleusException(this.failureMessage("storeLongField"))).setFatal();
   }

   public long fetchLongField(int fieldNumber) {
      throw (new NucleusException(this.failureMessage("fetchLongField"))).setFatal();
   }

   public void storeFloatField(int fieldNumber, float value) {
      throw (new NucleusException(this.failureMessage("storeFloatField"))).setFatal();
   }

   public float fetchFloatField(int fieldNumber) {
      throw (new NucleusException(this.failureMessage("fetchFloatField"))).setFatal();
   }

   public void storeDoubleField(int fieldNumber, double value) {
      throw (new NucleusException(this.failureMessage("storeDoubleField"))).setFatal();
   }

   public double fetchDoubleField(int fieldNumber) {
      throw (new NucleusException(this.failureMessage("fetchDoubleField"))).setFatal();
   }

   public void storeStringField(int fieldNumber, String value) {
      throw (new NucleusException(this.failureMessage("storeStringField"))).setFatal();
   }

   public String fetchStringField(int fieldNumber) {
      throw (new NucleusException(this.failureMessage("fetchStringField"))).setFatal();
   }

   public void storeObjectField(int fieldNumber, Object value) {
      throw (new NucleusException(this.failureMessage("storeObjectField"))).setFatal();
   }

   public Object fetchObjectField(int fieldNumber) {
      throw (new NucleusException(this.failureMessage("fetchObjectField"))).setFatal();
   }
}
