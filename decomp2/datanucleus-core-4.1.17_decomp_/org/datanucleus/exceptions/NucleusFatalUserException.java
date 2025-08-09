package org.datanucleus.exceptions;

public class NucleusFatalUserException extends NucleusUserException {
   private static final long serialVersionUID = 900516249145530058L;

   public NucleusFatalUserException() {
      this.setFatal();
   }

   public NucleusFatalUserException(String msg) {
      super(msg);
      this.setFatal();
   }

   public NucleusFatalUserException(String msg, Throwable[] nested) {
      super(msg, nested);
      this.setFatal();
   }

   public NucleusFatalUserException(String msg, Throwable nested) {
      super(msg, nested);
      this.setFatal();
   }

   public NucleusFatalUserException(String msg, Object failed) {
      super(msg, failed);
      this.setFatal();
   }

   public NucleusFatalUserException(String msg, Throwable[] nested, Object failed) {
      super(msg, nested, failed);
      this.setFatal();
   }

   public NucleusFatalUserException(String msg, Throwable nested, Object failed) {
      super(msg, nested, failed);
      this.setFatal();
   }
}
