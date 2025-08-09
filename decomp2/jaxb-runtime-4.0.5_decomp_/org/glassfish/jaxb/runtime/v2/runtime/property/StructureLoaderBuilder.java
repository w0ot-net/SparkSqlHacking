package org.glassfish.jaxb.runtime.v2.runtime.property;

import javax.xml.namespace.QName;
import org.glassfish.jaxb.runtime.v2.util.QNameMap;

public interface StructureLoaderBuilder {
   QName TEXT_HANDLER = new QName("\u0000", "text");
   QName CATCH_ALL = new QName("\u0000", "catchAll");

   void buildChildElementUnmarshallers(UnmarshallerChain var1, QNameMap var2);
}
