package org.datanucleus.exceptions;

import java.io.PrintStream;
import java.io.PrintWriter;

public class NucleusException extends RuntimeException {
   private static final long serialVersionUID = 6533560396693164660L;
   Throwable[] nested;
   Object failed;
   boolean fatal;

   public NucleusException() {
   }

   public NucleusException(String msg) {
      super(msg);
   }

   public NucleusException(String msg, Throwable[] nested) {
      super(msg);
      this.nested = nested;
   }

   public NucleusException(String msg, Throwable nested) {
      super(msg);
      this.nested = new Throwable[]{nested};
   }

   public NucleusException(String msg, Object failed) {
      super(msg);
      this.failed = failed;
   }

   public NucleusException(String msg, Throwable[] nested, Object failed) {
      super(msg);
      this.nested = nested;
      this.failed = failed;
   }

   public NucleusException(String msg, Throwable nested, Object failed) {
      super(msg);
      this.nested = new Throwable[]{nested};
      this.failed = failed;
   }

   public NucleusException setFatal() {
      this.fatal = true;
      return this;
   }

   public boolean isFatal() {
      return this.fatal;
   }

   public Object getFailedObject() {
      return this.failed;
   }

   public void setNestedException(Throwable nested) {
      this.nested = new Throwable[]{nested};
   }

   public Throwable[] getNestedExceptions() {
      return this.nested;
   }

   public synchronized Throwable getCause() {
      return this.nested != null && this.nested.length != 0 ? this.nested[0] : null;
   }

   public void printStackTrace() {
      this.printStackTrace(System.err);
   }

   public synchronized void printStackTrace(PrintStream s) {
      int len = this.nested == null ? 0 : this.nested.length;
      synchronized(s) {
         if (this.getMessage() != null) {
            s.println(this.getMessage());
         }

         super.printStackTrace(s);
         if (len > 0) {
            s.println("Nested Throwables StackTrace:");

            for(int i = 0; i < len; ++i) {
               Throwable exception = this.nested[i];
               if (exception != null) {
                  exception.printStackTrace(s);
               }
            }
         }

      }
   }

   public synchronized void printStackTrace(PrintWriter s) {
      int len = this.nested == null ? 0 : this.nested.length;
      synchronized(s) {
         if (this.getMessage() != null) {
            s.println(this.getMessage());
         }

         super.printStackTrace(s);
         if (len > 0) {
            s.println("Nested Throwables StackTrace:");

            for(int i = 0; i < len; ++i) {
               Throwable exception = this.nested[i];
               if (exception != null) {
                  exception.printStackTrace(s);
               }
            }
         }

      }
   }
}
