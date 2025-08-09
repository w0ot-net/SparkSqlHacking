package org.sparkproject.jpmml.model;

import java.lang.reflect.Field;
import org.sparkproject.dmg.pmml.PMMLObject;

public class InvalidAttributeException extends InvalidMarkupException {
   public InvalidAttributeException(String message) {
      super(message);
   }

   public InvalidAttributeException(String message, PMMLObject context) {
      super(message, context);
   }

   public InvalidAttributeException(PMMLObject object, Enum value) {
      this(object, EnumUtil.getEnumField(object, value), EnumUtil.getEnumValue(value));
   }

   public InvalidAttributeException(PMMLObject object, Field field, Object value) {
      super(formatMessage(XPathUtil.formatAttribute(object.getClass(), field, value)), object);
   }

   public static String formatMessage(String xPath) {
      return "Attribute with value " + xPath + " is not valid";
   }
}
