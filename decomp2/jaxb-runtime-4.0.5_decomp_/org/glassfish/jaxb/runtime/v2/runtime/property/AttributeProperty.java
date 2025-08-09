package org.glassfish.jaxb.runtime.v2.runtime.property;

import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.glassfish.jaxb.core.v2.model.core.PropertyKind;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeAttributePropertyInfo;
import org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl;
import org.glassfish.jaxb.runtime.v2.runtime.Name;
import org.glassfish.jaxb.runtime.v2.runtime.XMLSerializer;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.TransducedAccessor;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.ChildLoader;
import org.glassfish.jaxb.runtime.v2.util.QNameMap;
import org.xml.sax.SAXException;

public final class AttributeProperty extends PropertyImpl implements Comparable {
   public final Name attName;
   public final TransducedAccessor xacc;
   private final Accessor acc;

   public AttributeProperty(JAXBContextImpl context, RuntimeAttributePropertyInfo prop) {
      super(context, prop);
      this.attName = context.nameBuilder.createAttributeName(prop.getXmlName());
      this.xacc = TransducedAccessor.get(context, prop);
      this.acc = prop.getAccessor();
   }

   public void serializeAttributes(Object o, XMLSerializer w) throws SAXException, AccessorException, IOException, XMLStreamException {
      CharSequence value = this.xacc.print(o);
      if (value != null) {
         w.attribute(this.attName, value.toString());
      }

   }

   public void serializeURIs(Object o, XMLSerializer w) throws AccessorException, SAXException {
      this.xacc.declareNamespace(o, w);
   }

   public boolean hasSerializeURIAction() {
      return this.xacc.useNamespace();
   }

   public void buildChildElementUnmarshallers(UnmarshallerChain chainElem, QNameMap handlers) {
      throw new IllegalStateException();
   }

   public PropertyKind getKind() {
      return PropertyKind.ATTRIBUTE;
   }

   public void reset(Object o) throws AccessorException {
      this.acc.set(o, (Object)null);
   }

   public String getIdValue(Object bean) throws AccessorException, SAXException {
      return this.xacc.print(bean).toString();
   }

   public int compareTo(AttributeProperty that) {
      return this.attName.compareTo(that.attName);
   }
}
