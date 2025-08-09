package org.glassfish.jaxb.runtime.v2.model.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeNonElement;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimePropertyInfo;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeValuePropertyInfo;

final class RuntimeValuePropertyInfoImpl extends ValuePropertyInfoImpl implements RuntimeValuePropertyInfo {
   RuntimeValuePropertyInfoImpl(RuntimeClassInfoImpl classInfo, PropertySeed seed) {
      super(classInfo, seed);
   }

   public boolean elementOnlyContent() {
      return false;
   }

   public RuntimePropertyInfo getSource() {
      return (RuntimePropertyInfo)super.getSource();
   }

   public RuntimeNonElement getTarget() {
      return (RuntimeNonElement)super.getTarget();
   }

   public List ref() {
      return super.ref();
   }

   public void link() {
      this.getTransducer();
      super.link();
   }
}
