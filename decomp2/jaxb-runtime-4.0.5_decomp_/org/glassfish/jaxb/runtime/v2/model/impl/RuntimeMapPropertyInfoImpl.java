package org.glassfish.jaxb.runtime.v2.model.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeMapPropertyInfo;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeNonElement;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;

class RuntimeMapPropertyInfoImpl extends MapPropertyInfoImpl implements RuntimeMapPropertyInfo {
   private final Accessor acc;

   RuntimeMapPropertyInfoImpl(RuntimeClassInfoImpl classInfo, PropertySeed seed) {
      super(classInfo, seed);
      this.acc = ((RuntimeClassInfoImpl.RuntimePropertySeed)seed).getAccessor();
   }

   public Accessor getAccessor() {
      return this.acc;
   }

   public boolean elementOnlyContent() {
      return true;
   }

   public RuntimeNonElement getKeyType() {
      return (RuntimeNonElement)super.getKeyType();
   }

   public RuntimeNonElement getValueType() {
      return (RuntimeNonElement)super.getValueType();
   }

   public List ref() {
      return (List)super.ref();
   }
}
