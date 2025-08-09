package org.apache.parquet.io.api;

import org.apache.parquet.column.Dictionary;

public abstract class PrimitiveConverter extends Converter {
   public boolean isPrimitive() {
      return true;
   }

   public PrimitiveConverter asPrimitiveConverter() {
      return this;
   }

   public boolean hasDictionarySupport() {
      return false;
   }

   public void setDictionary(Dictionary dictionary) {
      throw new UnsupportedOperationException(this.getClass().getName());
   }

   public void addValueFromDictionary(int dictionaryId) {
      throw new UnsupportedOperationException(this.getClass().getName());
   }

   public void addBinary(Binary value) {
      throw new UnsupportedOperationException(this.getClass().getName());
   }

   public void addBoolean(boolean value) {
      throw new UnsupportedOperationException(this.getClass().getName());
   }

   public void addDouble(double value) {
      throw new UnsupportedOperationException(this.getClass().getName());
   }

   public void addFloat(float value) {
      throw new UnsupportedOperationException(this.getClass().getName());
   }

   public void addInt(int value) {
      throw new UnsupportedOperationException(this.getClass().getName());
   }

   public void addLong(long value) {
      throw new UnsupportedOperationException(this.getClass().getName());
   }
}
