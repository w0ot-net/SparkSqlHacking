package org.glassfish.hk2.api;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class MultiException extends HK2RuntimeException {
   private static final long serialVersionUID = 2112432697858621044L;
   private final Object lock;
   private final List throwables;
   private boolean reportToErrorService;

   public MultiException() {
      this.lock = new byte[0];
      this.throwables = new LinkedList();
      this.reportToErrorService = true;
   }

   public MultiException(List ths) {
      super(((Throwable)ths.get(0)).getMessage(), (Throwable)ths.get(0));
      this.lock = new byte[0];
      this.throwables = new LinkedList();
      this.reportToErrorService = true;

      for(Throwable th : ths) {
         if (th instanceof MultiException) {
            MultiException me = (MultiException)th;
            this.throwables.addAll(me.throwables);
         } else {
            this.throwables.add(th);
         }
      }

   }

   public MultiException(Throwable th, boolean reportToErrorService) {
      super(th.getMessage(), th);
      this.lock = new byte[0];
      this.throwables = new LinkedList();
      this.reportToErrorService = true;
      if (th instanceof MultiException) {
         MultiException me = (MultiException)th;
         this.throwables.addAll(me.throwables);
      } else {
         this.throwables.add(th);
      }

      this.reportToErrorService = reportToErrorService;
   }

   public MultiException(Throwable th) {
      this(th, true);
   }

   public List getErrors() {
      synchronized(this.lock) {
         return new LinkedList(this.throwables);
      }
   }

   public void addError(Throwable error) {
      synchronized(this.lock) {
         this.throwables.add(error);
      }
   }

   public String getMessage() {
      List<Throwable> listCopy = this.getErrors();
      StringBuffer sb = new StringBuffer("A MultiException has " + listCopy.size() + " exceptions.  They are:\n");
      int lcv = 1;

      for(Throwable th : listCopy) {
         int var10001 = lcv++;
         sb.append(var10001 + ". " + th.getClass().getName() + (th.getMessage() != null ? ": " + th.getMessage() : "") + "\n");
      }

      return sb.toString();
   }

   public void printStackTrace(PrintStream s) {
      List<Throwable> listCopy = this.getErrors();
      if (listCopy.size() <= 0) {
         super.printStackTrace(s);
      } else {
         int lcv = 1;

         for(Throwable th : listCopy) {
            int var10001 = lcv++;
            s.println("MultiException stack " + var10001 + " of " + listCopy.size());
            th.printStackTrace(s);
         }

      }
   }

   public void printStackTrace(PrintWriter s) {
      List<Throwable> listCopy = this.getErrors();
      if (listCopy.size() <= 0) {
         super.printStackTrace(s);
      } else {
         int lcv = 1;

         for(Throwable th : listCopy) {
            int var10001 = lcv++;
            s.println("MultiException stack " + var10001 + " of " + listCopy.size());
            th.printStackTrace(s);
         }

      }
   }

   public boolean getReportToErrorService() {
      return this.reportToErrorService;
   }

   public void setReportToErrorService(boolean report) {
      this.reportToErrorService = report;
   }

   public String toString() {
      return this.getMessage();
   }
}
