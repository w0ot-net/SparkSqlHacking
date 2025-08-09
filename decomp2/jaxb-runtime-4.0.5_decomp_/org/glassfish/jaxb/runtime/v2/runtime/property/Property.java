package org.glassfish.jaxb.runtime.v2.runtime.property;

import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.glassfish.jaxb.core.v2.model.core.PropertyKind;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimePropertyInfo;
import org.glassfish.jaxb.runtime.v2.runtime.XMLSerializer;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;
import org.xml.sax.SAXException;

public interface Property extends StructureLoaderBuilder {
   void reset(Object var1) throws AccessorException;

   void serializeBody(Object var1, XMLSerializer var2, Object var3) throws SAXException, AccessorException, IOException, XMLStreamException;

   void serializeURIs(Object var1, XMLSerializer var2) throws SAXException, AccessorException;

   boolean hasSerializeURIAction();

   String getIdValue(Object var1) throws AccessorException, SAXException;

   PropertyKind getKind();

   Accessor getElementPropertyAccessor(String var1, String var2);

   void wrapUp();

   RuntimePropertyInfo getInfo();

   boolean isHiddenByOverride();

   void setHiddenByOverride(boolean var1);

   String getFieldName();
}
