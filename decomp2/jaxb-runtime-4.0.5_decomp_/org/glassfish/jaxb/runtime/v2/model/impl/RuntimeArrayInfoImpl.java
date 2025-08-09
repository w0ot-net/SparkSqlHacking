package org.glassfish.jaxb.runtime.v2.model.impl;

import org.glassfish.jaxb.core.v2.model.annotation.Locatable;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeArrayInfo;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeNonElement;
import org.glassfish.jaxb.runtime.v2.runtime.Transducer;

final class RuntimeArrayInfoImpl extends ArrayInfoImpl implements RuntimeArrayInfo {
   RuntimeArrayInfoImpl(RuntimeModelBuilder builder, Locatable upstream, Class arrayType) {
      super(builder, upstream, arrayType);
   }

   public Class getType() {
      return (Class)super.getType();
   }

   public RuntimeNonElement getItemType() {
      return (RuntimeNonElement)super.getItemType();
   }

   public Transducer getTransducer() {
      return null;
   }
}
