package org.sparkproject.jpmml.model;

import java.util.List;
import org.sparkproject.dmg.pmml.PMMLObject;

public class InvalidElementListException extends InvalidMarkupException {
   public InvalidElementListException(String message) {
      super(message);
   }

   public InvalidElementListException(String message, PMMLObject object) {
      super(message, object);
   }

   public InvalidElementListException(List objects) {
      super("List of elements " + XPathUtil.formatElement(((PMMLObject)objects.get(0)).getClass()) + " is not valid", (PMMLObject)objects.get(0));
   }
}
