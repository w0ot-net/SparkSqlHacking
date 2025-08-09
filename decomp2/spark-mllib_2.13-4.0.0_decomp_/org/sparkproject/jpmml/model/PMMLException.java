package org.sparkproject.jpmml.model;

import org.sparkproject.dmg.pmml.PMMLObject;
import org.xml.sax.Locator;

public abstract class PMMLException extends RuntimeException {
   private PMMLObject context = null;

   public PMMLException(String message) {
      super(message);
   }

   public PMMLException(String message, PMMLObject context) {
      super(message);
      this.setContext(context);
   }

   public synchronized PMMLException initCause(Throwable throwable) {
      return (PMMLException)super.initCause(throwable);
   }

   public PMMLException ensureContext(PMMLObject parentContext) {
      PMMLObject context = this.getContext();
      if (context == null) {
         this.setContext(parentContext);
      }

      return this;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      Class<? extends PMMLException> clazz = this.getClass();
      sb.append(clazz.getName());
      PMMLObject context = this.getContext();
      if (context != null) {
         int lineNumber = -1;
         Locator locator = context.getLocator();
         if (locator != null) {
            lineNumber = locator.getLineNumber();
         }

         if (lineNumber != -1) {
            sb.append(" ").append("(at or around line ").append(lineNumber).append(" of the PMML document)");
         }
      }

      String message = this.getLocalizedMessage();
      if (message != null) {
         sb.append(":");
         sb.append(" ").append(message);
      }

      return sb.toString();
   }

   public PMMLObject getContext() {
      return this.context;
   }

   private void setContext(PMMLObject context) {
      this.context = context;
   }
}
