package org.sparkproject.jpmml.model.visitors;

import java.io.Serializable;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.xml.sax.Locator;

public class LocatorTransformer extends AbstractVisitor {
   public VisitorAction visit(PMMLObject object) {
      object.setLocator(transform(object.getLocator()));
      return super.visit(object);
   }

   private static Locator transform(Locator locator) {
      return (Locator)(locator != null && !(locator instanceof Serializable) ? new SimpleLocator(locator) : locator);
   }
}
