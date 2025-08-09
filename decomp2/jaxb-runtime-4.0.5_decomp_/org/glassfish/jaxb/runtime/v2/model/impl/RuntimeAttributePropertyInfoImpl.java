package org.glassfish.jaxb.runtime.v2.model.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeAttributePropertyInfo;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeNonElement;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimePropertyInfo;

class RuntimeAttributePropertyInfoImpl extends AttributePropertyInfoImpl implements RuntimeAttributePropertyInfo {
   RuntimeAttributePropertyInfoImpl(RuntimeClassInfoImpl classInfo, PropertySeed seed) {
      super(classInfo, seed);
   }

   public boolean elementOnlyContent() {
      return true;
   }

   public RuntimeNonElement getTarget() {
      return (RuntimeNonElement)super.getTarget();
   }

   public List ref() {
      return super.ref();
   }

   public RuntimePropertyInfo getSource() {
      return this;
   }

   public void link() {
      this.getTransducer();
      super.link();
   }
}
