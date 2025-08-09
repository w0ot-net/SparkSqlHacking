package org.glassfish.jaxb.core.v2.model.core;

import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.annotation.XmlNsForm;
import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.transform.Result;
import org.glassfish.jaxb.core.v2.model.nav.Navigator;

public interface TypeInfoSet {
   Navigator getNavigator();

   NonElement getTypeInfo(Object var1);

   NonElement getAnyTypeInfo();

   NonElement getClassInfo(Object var1);

   Map arrays();

   Map beans();

   Map builtins();

   Map enums();

   ElementInfo getElementInfo(Object var1, QName var2);

   NonElement getTypeInfo(Ref var1);

   Map getElementMappings(Object var1);

   Iterable getAllElements();

   Map getXmlNs(String var1);

   Map getSchemaLocations();

   XmlNsForm getElementFormDefault(String var1);

   XmlNsForm getAttributeFormDefault(String var1);

   void dump(Result var1) throws JAXBException;
}
