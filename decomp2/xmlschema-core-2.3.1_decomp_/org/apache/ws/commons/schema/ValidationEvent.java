package org.apache.ws.commons.schema;

import java.util.EventObject;

public class ValidationEvent extends EventObject {
   private static final long serialVersionUID = 1L;

   public ValidationEvent(Object source) {
      super(source);
   }
}
