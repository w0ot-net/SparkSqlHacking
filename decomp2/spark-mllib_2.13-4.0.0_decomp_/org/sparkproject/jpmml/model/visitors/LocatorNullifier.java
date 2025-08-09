package org.sparkproject.jpmml.model.visitors;

import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.xml.sax.Locator;

public class LocatorNullifier extends AbstractVisitor {
   public VisitorAction visit(PMMLObject object) {
      object.setLocator((Locator)null);
      return super.visit(object);
   }
}
