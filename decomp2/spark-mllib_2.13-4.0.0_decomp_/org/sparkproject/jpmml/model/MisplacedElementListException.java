package org.sparkproject.jpmml.model;

import java.util.List;
import org.sparkproject.dmg.pmml.PMMLObject;

public class MisplacedElementListException extends InvalidElementListException {
   public MisplacedElementListException(List objects) {
      super(formatMessage(XPathUtil.formatElement(((PMMLObject)objects.get(0)).getClass())), (PMMLObject)objects.get(0));
   }

   public static String formatMessage(String xPath) {
      return "List of elements " + xPath + " is not permitted in this location";
   }
}
