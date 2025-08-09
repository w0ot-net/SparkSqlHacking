package org.glassfish.jersey.server.model.internal;

import java.util.List;
import java.util.stream.Collectors;
import org.glassfish.jersey.internal.Errors;
import org.glassfish.jersey.server.model.ResourceModelIssue;

public class ModelErrors {
   public static List getErrorsAsResourceModelIssues() {
      return getErrorsAsResourceModelIssues(false);
   }

   public static List getErrorsAsResourceModelIssues(boolean afterMark) {
      return (List)Errors.getErrorMessages(afterMark).stream().map((input) -> new ResourceModelIssue(input.getSource(), input.getMessage(), input.getSeverity())).collect(Collectors.toList());
   }
}
