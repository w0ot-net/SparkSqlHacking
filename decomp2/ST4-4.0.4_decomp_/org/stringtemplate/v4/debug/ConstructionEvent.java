package org.stringtemplate.v4.debug;

public class ConstructionEvent {
   public Throwable stack = new Throwable();

   public String getFileName() {
      return this.getSTEntryPoint().getFileName();
   }

   public int getLine() {
      return this.getSTEntryPoint().getLineNumber();
   }

   public StackTraceElement getSTEntryPoint() {
      StackTraceElement[] trace = this.stack.getStackTrace();

      for(StackTraceElement e : trace) {
         String name = e.toString();
         if (!name.startsWith("org.stringtemplate.v4")) {
            return e;
         }
      }

      return trace[0];
   }
}
