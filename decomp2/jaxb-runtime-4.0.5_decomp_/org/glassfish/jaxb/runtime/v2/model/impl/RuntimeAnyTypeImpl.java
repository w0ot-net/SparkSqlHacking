package org.glassfish.jaxb.runtime.v2.model.impl;

import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeNonElement;
import org.glassfish.jaxb.runtime.v2.runtime.Transducer;

final class RuntimeAnyTypeImpl extends AnyTypeImpl implements RuntimeNonElement {
   static final RuntimeNonElement theInstance = new RuntimeAnyTypeImpl();

   private RuntimeAnyTypeImpl() {
      super(Utils.REFLECTION_NAVIGATOR);
   }

   public Transducer getTransducer() {
      return null;
   }
}
