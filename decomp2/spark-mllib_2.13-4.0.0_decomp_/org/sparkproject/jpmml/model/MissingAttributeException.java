package org.sparkproject.jpmml.model;

import java.lang.reflect.Field;
import org.sparkproject.dmg.pmml.PMMLObject;

public class MissingAttributeException extends MissingMarkupException {
   public MissingAttributeException(String message) {
      super(message);
   }

   public MissingAttributeException(String message, PMMLObject context) {
      super(message, context);
   }

   public MissingAttributeException(PMMLObject object, Field field) {
      super(formatMessage(XPathUtil.formatElementOrAttribute(object.getClass(), field)), object);
   }

   public static String formatMessage(String xPath) {
      return "Required attribute " + xPath + " is not defined";
   }
}
