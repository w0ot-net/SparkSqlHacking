package org.datanucleus.store.rdbms.exceptions;

import org.datanucleus.exceptions.NucleusDataStoreException;

public class UnsupportedDataTypeException extends NucleusDataStoreException {
   private static final long serialVersionUID = -5976850971131790147L;

   public UnsupportedDataTypeException() {
      this.setFatal();
   }

   public UnsupportedDataTypeException(String msg) {
      super(msg);
      this.setFatal();
   }

   public UnsupportedDataTypeException(String msg, Exception nested) {
      super(msg, nested);
      this.setFatal();
   }
}
