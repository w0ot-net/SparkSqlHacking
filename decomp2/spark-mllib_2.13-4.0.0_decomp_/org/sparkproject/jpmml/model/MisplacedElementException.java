package org.sparkproject.jpmml.model;

import org.sparkproject.dmg.pmml.PMMLObject;

public class MisplacedElementException extends InvalidElementException {
   public MisplacedElementException(PMMLObject object) {
      super(formatMessage(XPathUtil.formatElement(object.getClass())), object);
   }

   public static String formatMessage(String xPath) {
      return "Element " + xPath + " is not permitted in this location";
   }
}
