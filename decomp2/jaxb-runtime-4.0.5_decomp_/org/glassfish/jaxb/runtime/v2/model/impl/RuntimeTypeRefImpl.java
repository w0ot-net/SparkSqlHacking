package org.glassfish.jaxb.runtime.v2.model.impl;

import java.lang.reflect.Type;
import javax.xml.namespace.QName;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeNonElement;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimePropertyInfo;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeTypeRef;
import org.glassfish.jaxb.runtime.v2.runtime.Transducer;

final class RuntimeTypeRefImpl extends TypeRefImpl implements RuntimeTypeRef {
   public RuntimeTypeRefImpl(RuntimeElementPropertyInfoImpl elementPropertyInfo, QName elementName, Type type, boolean isNillable, String defaultValue) {
      super(elementPropertyInfo, elementName, type, isNillable, defaultValue);
   }

   public RuntimeNonElement getTarget() {
      return (RuntimeNonElement)super.getTarget();
   }

   public Transducer getTransducer() {
      return RuntimeModelBuilder.createTransducer(this);
   }

   public RuntimePropertyInfo getSource() {
      return (RuntimePropertyInfo)this.owner;
   }
}
