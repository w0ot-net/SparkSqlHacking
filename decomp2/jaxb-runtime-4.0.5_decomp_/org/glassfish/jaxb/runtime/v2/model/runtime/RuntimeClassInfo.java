package org.glassfish.jaxb.runtime.v2.model.runtime;

import java.lang.reflect.Method;
import java.util.List;
import org.glassfish.jaxb.core.v2.model.core.ClassInfo;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;

public interface RuntimeClassInfo extends ClassInfo, RuntimeNonElement {
   RuntimeClassInfo getBaseClass();

   List getProperties();

   RuntimePropertyInfo getProperty(String var1);

   Method getFactoryMethod();

   Accessor getAttributeWildcard();

   Accessor getLocatorField();
}
