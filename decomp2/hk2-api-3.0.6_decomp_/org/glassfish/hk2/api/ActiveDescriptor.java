package org.glassfish.hk2.api;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

public interface ActiveDescriptor extends Descriptor, SingleCache {
   boolean isReified();

   Class getImplementationClass();

   Type getImplementationType();

   Set getContractTypes();

   Annotation getScopeAsAnnotation();

   Class getScopeAnnotation();

   Set getQualifierAnnotations();

   List getInjectees();

   Long getFactoryServiceId();

   Long getFactoryLocatorId();

   Object create(ServiceHandle var1);

   void dispose(Object var1);
}
