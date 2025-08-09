package org.sparkproject.jpmml.model;

import java.lang.reflect.Field;
import org.sparkproject.dmg.pmml.PMMLObject;

public class MisplacedAttributeException extends InvalidAttributeException {
   public MisplacedAttributeException(PMMLObject object, Enum value) {
      this(object, EnumUtil.getEnumField(object, value), EnumUtil.getEnumValue(value));
   }

   public MisplacedAttributeException(PMMLObject object, Field field, Object value) {
      super(formatMessage(XPathUtil.formatAttribute(object.getClass(), field, value)), object);
   }

   public static String formatMessage(String xPath) {
      return "Attribute with value " + xPath + " is not permitted in this location";
   }
}
