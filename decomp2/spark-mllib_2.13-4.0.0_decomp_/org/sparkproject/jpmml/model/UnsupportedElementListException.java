package org.sparkproject.jpmml.model;

import java.util.List;
import org.sparkproject.dmg.pmml.PMMLObject;

public class UnsupportedElementListException extends UnsupportedMarkupException {
   public UnsupportedElementListException(List objects) {
      super("List of elements " + XPathUtil.formatElement(((PMMLObject)objects.get(0)).getClass()) + " is not supported", (PMMLObject)objects.get(0));
   }
}
