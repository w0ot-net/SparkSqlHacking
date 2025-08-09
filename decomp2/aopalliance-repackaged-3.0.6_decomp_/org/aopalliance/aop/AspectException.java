package org.aopalliance.aop;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public class AspectException extends RuntimeException {
   private String message;
   private String stackTrace;
   private Throwable t;

   public AspectException(String s) {
      super(s);
      this.message = s;
      this.stackTrace = s;
   }

   public AspectException(String s, Throwable t) {
      super(s + "; nested exception is " + t.getMessage());
      this.t = t;
      StringWriter out = new StringWriter();
      t.printStackTrace(new PrintWriter(out));
      this.stackTrace = out.toString();
   }

   public Throwable getCause() {
      return this.t;
   }

   public String toString() {
      return this.getMessage();
   }

   public String getMessage() {
      return this.message;
   }

   public void printStackTrace() {
      System.err.print(this.stackTrace);
   }

   public void printStackTrace(PrintStream out) {
      this.printStackTrace(new PrintWriter(out));
   }

   public void printStackTrace(PrintWriter out) {
      out.print(this.stackTrace);
   }
}
