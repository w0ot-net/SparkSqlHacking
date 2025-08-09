package org.glassfish.jaxb.runtime.v2.model.runtime;

import java.lang.reflect.Type;
import java.util.Map;
import javax.xml.namespace.QName;
import org.glassfish.jaxb.core.v2.model.core.TypeInfoSet;

public interface RuntimeTypeInfoSet extends TypeInfoSet {
   Map arrays();

   Map beans();

   Map builtins();

   Map enums();

   RuntimeNonElement getTypeInfo(Type var1);

   RuntimeNonElement getAnyTypeInfo();

   RuntimeNonElement getClassInfo(Class var1);

   RuntimeElementInfo getElementInfo(Class var1, QName var2);

   Map getElementMappings(Class var1);

   Iterable getAllElements();
}
