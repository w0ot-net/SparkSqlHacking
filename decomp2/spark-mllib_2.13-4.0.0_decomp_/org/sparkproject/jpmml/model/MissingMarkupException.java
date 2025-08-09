package org.sparkproject.jpmml.model;

import org.sparkproject.dmg.pmml.PMMLObject;

public abstract class MissingMarkupException extends MarkupException {
   public MissingMarkupException(String message) {
      super(message);
   }

   public MissingMarkupException(String message, PMMLObject context) {
      super(message, context);
   }
}
