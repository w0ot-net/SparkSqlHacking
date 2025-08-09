package org.apache.commons.lang3.exception;

import java.util.List;
import java.util.Set;

public class ContextedRuntimeException extends RuntimeException implements ExceptionContext {
   private static final long serialVersionUID = 20110706L;
   private final ExceptionContext exceptionContext;

   public ContextedRuntimeException() {
      this.exceptionContext = new DefaultExceptionContext();
   }

   public ContextedRuntimeException(String message) {
      super(message);
      this.exceptionContext = new DefaultExceptionContext();
   }

   public ContextedRuntimeException(String message, Throwable cause) {
      super(message, cause);
      this.exceptionContext = new DefaultExceptionContext();
   }

   public ContextedRuntimeException(String message, Throwable cause, ExceptionContext context) {
      super(message, cause);
      if (context == null) {
         context = new DefaultExceptionContext();
      }

      this.exceptionContext = context;
   }

   public ContextedRuntimeException(Throwable cause) {
      super(cause);
      this.exceptionContext = new DefaultExceptionContext();
   }

   public ContextedRuntimeException addContextValue(String label, Object value) {
      this.exceptionContext.addContextValue(label, value);
      return this;
   }

   public List getContextEntries() {
      return this.exceptionContext.getContextEntries();
   }

   public Set getContextLabels() {
      return this.exceptionContext.getContextLabels();
   }

   public List getContextValues(String label) {
      return this.exceptionContext.getContextValues(label);
   }

   public Object getFirstContextValue(String label) {
      return this.exceptionContext.getFirstContextValue(label);
   }

   public String getFormattedExceptionMessage(String baseMessage) {
      return this.exceptionContext.getFormattedExceptionMessage(baseMessage);
   }

   public String getMessage() {
      return this.getFormattedExceptionMessage(super.getMessage());
   }

   public String getRawMessage() {
      return super.getMessage();
   }

   public ContextedRuntimeException setContextValue(String label, Object value) {
      this.exceptionContext.setContextValue(label, value);
      return this;
   }
}
