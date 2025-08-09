package org.glassfish.jaxb.runtime.v2.model.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import javax.xml.namespace.QName;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeElementPropertyInfo;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;

class RuntimeElementPropertyInfoImpl extends ElementPropertyInfoImpl implements RuntimeElementPropertyInfo {
   private final Accessor acc;

   RuntimeElementPropertyInfoImpl(RuntimeClassInfoImpl classInfo, PropertySeed seed) {
      super(classInfo, seed);
      Accessor rawAcc = ((RuntimeClassInfoImpl.RuntimePropertySeed)seed).getAccessor();
      if (this.getAdapter() != null && !this.isCollection()) {
         rawAcc = rawAcc.adapt(this.getAdapter());
      }

      this.acc = rawAcc;
   }

   public Accessor getAccessor() {
      return this.acc;
   }

   public boolean elementOnlyContent() {
      return true;
   }

   public List ref() {
      return super.ref();
   }

   protected RuntimeTypeRefImpl createTypeRef(QName name, Type type, boolean isNillable, String defaultValue) {
      return new RuntimeTypeRefImpl(this, name, type, isNillable, defaultValue);
   }

   public List getTypes() {
      return super.getTypes();
   }
}
