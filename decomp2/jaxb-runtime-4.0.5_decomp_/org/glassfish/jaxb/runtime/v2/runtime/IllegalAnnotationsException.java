package org.glassfish.jaxb.runtime.v2.runtime;

import jakarta.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.glassfish.jaxb.core.v2.model.core.ErrorHandler;
import org.glassfish.jaxb.core.v2.runtime.IllegalAnnotationException;

public class IllegalAnnotationsException extends JAXBException {
   private final transient List errors;
   private static final long serialVersionUID = 1L;

   public IllegalAnnotationsException(List errors) {
      super(errors.size() + " counts of IllegalAnnotationExceptions");

      assert !errors.isEmpty() : "there must be at least one error";

      this.errors = Collections.unmodifiableList(new ArrayList(errors));
   }

   public String toString() {
      StringBuilder sb = new StringBuilder(super.toString());
      sb.append('\n');

      for(IllegalAnnotationException error : this.errors) {
         sb.append(error.toString()).append('\n');
      }

      return sb.toString();
   }

   public List getErrors() {
      return this.errors;
   }

   public static class Builder implements ErrorHandler {
      private final List list = new ArrayList();

      public void error(IllegalAnnotationException e) {
         this.list.add(e);
      }

      public void check() throws IllegalAnnotationsException {
         if (!this.list.isEmpty()) {
            throw new IllegalAnnotationsException(this.list);
         }
      }
   }
}
