package org.glassfish.jaxb.runtime.v2.runtime.property;

import java.io.IOException;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import org.glassfish.jaxb.core.v2.model.core.PropertyKind;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeValuePropertyInfo;
import org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl;
import org.glassfish.jaxb.runtime.v2.runtime.XMLSerializer;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.TransducedAccessor;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.ChildLoader;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.Receiver;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.ValuePropertyLoader;
import org.glassfish.jaxb.runtime.v2.util.QNameMap;
import org.xml.sax.SAXException;

public final class ValueProperty extends PropertyImpl {
   private final TransducedAccessor xacc;
   private final Accessor acc;

   public ValueProperty(JAXBContextImpl context, RuntimeValuePropertyInfo prop) {
      super(context, prop);
      this.xacc = TransducedAccessor.get(context, prop);
      this.acc = prop.getAccessor();
   }

   public void serializeBody(Object o, XMLSerializer w, Object outerPeer) throws SAXException, AccessorException, IOException, XMLStreamException {
      if (this.xacc.hasValue(o)) {
         this.xacc.writeText(w, o, this.fieldName);
      }

   }

   public void serializeURIs(Object o, XMLSerializer w) throws SAXException, AccessorException {
      this.xacc.declareNamespace(o, w);
   }

   public boolean hasSerializeURIAction() {
      return this.xacc.useNamespace();
   }

   public void buildChildElementUnmarshallers(UnmarshallerChain chainElem, QNameMap handlers) {
      handlers.put((QName)TEXT_HANDLER, new ChildLoader(new ValuePropertyLoader(this.xacc), (Receiver)null));
   }

   public PropertyKind getKind() {
      return PropertyKind.VALUE;
   }

   public void reset(Object o) throws AccessorException {
      this.acc.set(o, (Object)null);
   }

   public String getIdValue(Object bean) throws AccessorException, SAXException {
      return this.xacc.print(bean).toString();
   }
}
