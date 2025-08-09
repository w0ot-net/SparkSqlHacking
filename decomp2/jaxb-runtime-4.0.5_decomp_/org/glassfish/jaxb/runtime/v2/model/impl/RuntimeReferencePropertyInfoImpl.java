package org.glassfish.jaxb.runtime.v2.model.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeReferencePropertyInfo;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;

class RuntimeReferencePropertyInfoImpl extends ReferencePropertyInfoImpl implements RuntimeReferencePropertyInfo {
   private final Accessor acc;

   public RuntimeReferencePropertyInfoImpl(RuntimeClassInfoImpl classInfo, PropertySeed seed) {
      super(classInfo, seed);
      Accessor rawAcc = ((RuntimeClassInfoImpl.RuntimePropertySeed)seed).getAccessor();
      if (this.getAdapter() != null && !this.isCollection()) {
         rawAcc = rawAcc.adapt(this.getAdapter());
      }

      this.acc = rawAcc;
   }

   public Set getElements() {
      return super.getElements();
   }

   public Set ref() {
      return super.ref();
   }

   public Accessor getAccessor() {
      return this.acc;
   }

   public boolean elementOnlyContent() {
      return !this.isMixed();
   }
}
