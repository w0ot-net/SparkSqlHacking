package org.sparkproject.jetty.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MultiException extends Exception {
   private static final String DEFAULT_MESSAGE = "Multiple exceptions";
   private List nested;

   public MultiException() {
      super("Multiple exceptions", (Throwable)null, false, false);
      this.nested = new ArrayList();
   }

   private MultiException(List nested) {
      super("Multiple exceptions");
      this.nested = new ArrayList(nested);
      if (nested.size() > 0) {
         this.initCause((Throwable)nested.get(0));
      }

      for(Throwable t : nested) {
         if (t != this && t != this.getCause()) {
            this.addSuppressed(t);
         }
      }

   }

   public void add(Throwable e) {
      if (e instanceof MultiException) {
         MultiException me = (MultiException)e;
         this.nested.addAll(me.nested);
      } else {
         this.nested.add(e);
      }

   }

   public int size() {
      return this.nested == null ? 0 : this.nested.size();
   }

   public List getThrowables() {
      return this.nested == null ? Collections.emptyList() : this.nested;
   }

   public Throwable getThrowable(int i) {
      return (Throwable)this.nested.get(i);
   }

   public void ifExceptionThrow() throws Exception {
      if (this.nested != null) {
         switch (this.nested.size()) {
            case 0:
               return;
            case 1:
               Throwable th = (Throwable)this.nested.get(0);
               if (th instanceof Error) {
                  throw (Error)th;
               } else {
                  if (th instanceof Exception) {
                     throw (Exception)th;
                  }

                  throw new MultiException(this.nested);
               }
            default:
               throw new MultiException(this.nested);
         }
      }
   }

   public void ifExceptionThrowRuntime() throws Error {
      if (this.nested != null) {
         switch (this.nested.size()) {
            case 0:
               return;
            case 1:
               Throwable th = (Throwable)this.nested.get(0);
               if (th instanceof Error) {
                  throw (Error)th;
               } else {
                  if (th instanceof RuntimeException) {
                     throw (RuntimeException)th;
                  }

                  throw new RuntimeException(th);
               }
            default:
               throw new RuntimeException(new MultiException(this.nested));
         }
      }
   }

   public void ifExceptionThrowMulti() throws MultiException {
      if (this.nested != null) {
         if (this.nested.size() > 0) {
            throw new MultiException(this.nested);
         }
      }
   }

   public void ifExceptionThrowSuppressed() throws Exception {
      if (this.nested != null && this.nested.size() != 0) {
         Throwable th = (Throwable)this.nested.get(0);
         if (!Error.class.isInstance(th) && !Exception.class.isInstance(th)) {
            th = new MultiException(Collections.emptyList());
         }

         for(Throwable s : this.nested) {
            if (s != th) {
               th.addSuppressed(s);
            }
         }

         if (Error.class.isInstance(th)) {
            throw (Error)th;
         } else {
            throw (Exception)th;
         }
      }
   }

   public String toString() {
      StringBuilder str = new StringBuilder();
      str.append(MultiException.class.getSimpleName());
      if (this.nested != null && this.nested.size() > 0) {
         str.append(this.nested);
      } else {
         str.append("[]");
      }

      return str.toString();
   }
}
