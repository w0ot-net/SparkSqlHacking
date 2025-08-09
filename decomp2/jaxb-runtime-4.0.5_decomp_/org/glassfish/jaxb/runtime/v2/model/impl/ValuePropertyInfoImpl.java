package org.glassfish.jaxb.runtime.v2.model.impl;

import org.glassfish.jaxb.core.v2.model.core.PropertyKind;
import org.glassfish.jaxb.core.v2.model.core.ValuePropertyInfo;

class ValuePropertyInfoImpl extends SingleTypePropertyInfoImpl implements ValuePropertyInfo {
   ValuePropertyInfoImpl(ClassInfoImpl parent, PropertySeed seed) {
      super(parent, seed);
   }

   public PropertyKind kind() {
      return PropertyKind.VALUE;
   }
}
