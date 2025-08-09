package org.datanucleus.exceptions;

public class NucleusObjectNotFoundException extends NucleusException {
   private static final long serialVersionUID = 85359813123790599L;

   public NucleusObjectNotFoundException() {
   }

   public NucleusObjectNotFoundException(String msg) {
      super(msg);
   }

   public NucleusObjectNotFoundException(String msg, Throwable[] nested) {
      super(msg, nested);
   }

   public NucleusObjectNotFoundException(String msg, Object failed) {
      super(msg, failed);
   }
}
