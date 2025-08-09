package org.sparkproject.jpmml.model;

import org.sparkproject.dmg.pmml.PMMLObject;

public abstract class UnsupportedMarkupException extends MarkupException {
   public UnsupportedMarkupException(String message) {
      super(message);
   }

   public UnsupportedMarkupException(String message, PMMLObject context) {
      super(message, context);
   }
}
