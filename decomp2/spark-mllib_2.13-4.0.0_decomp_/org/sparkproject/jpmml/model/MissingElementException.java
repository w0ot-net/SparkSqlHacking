package org.sparkproject.jpmml.model;

import java.lang.reflect.Field;
import org.sparkproject.dmg.pmml.PMMLObject;

public class MissingElementException extends MissingMarkupException {
   public MissingElementException(String message) {
      super(message);
   }

   public MissingElementException(String message, PMMLObject context) {
      super(message, context);
   }

   public MissingElementException(PMMLObject object, Field field) {
      super(formatMessage(XPathUtil.formatElementOrAttribute(object.getClass(), field)), object);
   }

   public static String formatMessage(String xPath) {
      return "Required element " + xPath + " is not defined";
   }
}
