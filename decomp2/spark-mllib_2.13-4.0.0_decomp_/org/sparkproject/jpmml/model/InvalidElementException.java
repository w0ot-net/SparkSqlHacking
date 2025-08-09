package org.sparkproject.jpmml.model;

import org.sparkproject.dmg.pmml.PMMLObject;

public class InvalidElementException extends InvalidMarkupException {
   public InvalidElementException(String message) {
      super(message);
   }

   public InvalidElementException(String message, PMMLObject context) {
      super(message, context);
   }

   public InvalidElementException(PMMLObject object) {
      super(formatMessage(XPathUtil.formatElement(object.getClass())), object);
   }

   public static String formatMessage(String xPath) {
      return "Element " + xPath + " is not valid";
   }
}
