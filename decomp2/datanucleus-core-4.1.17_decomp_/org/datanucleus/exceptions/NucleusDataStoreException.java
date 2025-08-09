package org.datanucleus.exceptions;

public class NucleusDataStoreException extends NucleusException {
   private static final long serialVersionUID = 1543994673619470996L;

   public NucleusDataStoreException() {
   }

   public NucleusDataStoreException(String msg) {
      super(msg);
   }

   public NucleusDataStoreException(String msg, Throwable[] nested) {
      super(msg, nested);
   }

   public NucleusDataStoreException(String msg, Throwable nested) {
      super(msg, nested);
   }

   public NucleusDataStoreException(String msg, Object failed) {
      super(msg, failed);
   }

   public NucleusDataStoreException(String msg, Throwable[] nested, Object failed) {
      super(msg, nested, failed);
   }

   public NucleusDataStoreException(String msg, Throwable nested, Object failed) {
      super(msg, nested, failed);
   }
}
