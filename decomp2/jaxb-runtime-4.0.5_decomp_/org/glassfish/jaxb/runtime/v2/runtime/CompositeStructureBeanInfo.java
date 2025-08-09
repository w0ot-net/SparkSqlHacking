package org.glassfish.jaxb.runtime.v2.runtime;

import jakarta.xml.bind.ValidationEventLocator;
import jakarta.xml.bind.helpers.ValidationEventImpl;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.xml.stream.XMLStreamException;
import org.glassfish.jaxb.runtime.api.CompositeStructure;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeTypeInfo;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.Loader;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.UnmarshallingContext;
import org.xml.sax.SAXException;

public class CompositeStructureBeanInfo extends JaxBeanInfo {
   public CompositeStructureBeanInfo(JAXBContextImpl context) {
      super(context, (RuntimeTypeInfo)null, CompositeStructure.class, false, true, false);
   }

   public String getElementNamespaceURI(CompositeStructure o) {
      throw new UnsupportedOperationException();
   }

   public String getElementLocalName(CompositeStructure o) {
      throw new UnsupportedOperationException();
   }

   public CompositeStructure createInstance(UnmarshallingContext context) throws IllegalAccessException, InvocationTargetException, InstantiationException, SAXException {
      throw new UnsupportedOperationException();
   }

   public boolean reset(CompositeStructure o, UnmarshallingContext context) throws SAXException {
      throw new UnsupportedOperationException();
   }

   public String getId(CompositeStructure o, XMLSerializer target) throws SAXException {
      return null;
   }

   public Loader getLoader(JAXBContextImpl context, boolean typeSubstitutionCapable) {
      throw new UnsupportedOperationException();
   }

   public void serializeRoot(CompositeStructure o, XMLSerializer target) throws SAXException, IOException, XMLStreamException {
      target.reportError(new ValidationEventImpl(1, Messages.UNABLE_TO_MARSHAL_NON_ELEMENT.format(o.getClass().getName()), (ValidationEventLocator)null, (Throwable)null));
   }

   public void serializeURIs(CompositeStructure o, XMLSerializer target) throws SAXException {
   }

   public void serializeAttributes(CompositeStructure o, XMLSerializer target) throws SAXException, IOException, XMLStreamException {
   }

   public void serializeBody(CompositeStructure o, XMLSerializer target) throws SAXException, IOException, XMLStreamException {
      int len = o.bridges.length;

      for(int i = 0; i < len; ++i) {
         Object value = o.values[i];
         InternalBridge bi = (InternalBridge)o.bridges[i];
         bi.marshal(value, target);
      }

   }

   public Transducer getTransducer() {
      return null;
   }
}
