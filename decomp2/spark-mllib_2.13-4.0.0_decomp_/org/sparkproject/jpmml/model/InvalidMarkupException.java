package org.sparkproject.jpmml.model;

import org.sparkproject.dmg.pmml.PMMLObject;

public abstract class InvalidMarkupException extends MarkupException {
   public InvalidMarkupException(String message) {
      super(message);
   }

   public InvalidMarkupException(String message, PMMLObject context) {
      super(message, context);
   }
}
