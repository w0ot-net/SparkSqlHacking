package org.glassfish.jersey.server.model;

import java.util.List;

public class ModelValidationException extends RuntimeException {
   private static final long serialVersionUID = 4076015716487596210L;
   private final List issues;

   public ModelValidationException(String message, List issues) {
      super(message);
      this.issues = issues;
   }

   public List getIssues() {
      return this.issues;
   }

   public String getMessage() {
      String message = super.getMessage();
      return (message == null ? "" : message + '\n') + this.issues.toString();
   }
}
