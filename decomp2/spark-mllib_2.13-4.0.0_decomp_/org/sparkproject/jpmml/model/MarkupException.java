package org.sparkproject.jpmml.model;

import org.sparkproject.dmg.pmml.PMMLObject;

public abstract class MarkupException extends PMMLException {
   public MarkupException(String message) {
      super(message);
   }

   public MarkupException(String message, PMMLObject context) {
      super(message, context);
   }
}
