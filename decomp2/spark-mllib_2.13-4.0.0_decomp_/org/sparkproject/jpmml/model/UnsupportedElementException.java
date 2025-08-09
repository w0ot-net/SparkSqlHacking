package org.sparkproject.jpmml.model;

import org.sparkproject.dmg.pmml.PMMLObject;

public class UnsupportedElementException extends UnsupportedMarkupException {
   public UnsupportedElementException(PMMLObject object) {
      super("Element " + formatElement(object) + " is not supported", object);
   }

   private static String formatElement(PMMLObject object) {
      Class<? extends PMMLObject> clazz = object.getClass();
      String result = XPathUtil.formatElement(clazz);
      String name = clazz.getName();
      if (!name.startsWith("org.sparkproject.dmg.pmml.")) {
         result = result + " (Java class " + name + ")";
      }

      return result;
   }
}
