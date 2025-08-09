package org.glassfish.jaxb.runtime.v2.runtime.property;

import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.annotation.DomHandler;
import java.io.IOException;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import org.glassfish.jaxb.core.v2.ClassFactory;
import org.glassfish.jaxb.core.v2.model.core.PropertyKind;
import org.glassfish.jaxb.core.v2.model.core.WildcardMode;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeElement;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeReferencePropertyInfo;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeTypeInfo;
import org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl;
import org.glassfish.jaxb.runtime.v2.runtime.JaxBeanInfo;
import org.glassfish.jaxb.runtime.v2.runtime.XMLSerializer;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.ListIterator;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.ChildLoader;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.Loader;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.Receiver;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.UnmarshallingContext;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.WildcardLoader;
import org.glassfish.jaxb.runtime.v2.util.QNameMap;
import org.xml.sax.SAXException;

class ArrayReferenceNodeProperty extends ArrayERProperty {
   private final QNameMap expectedElements = new QNameMap();
   private final boolean isMixed;
   private final DomHandler domHandler;
   private final WildcardMode wcMode;

   public ArrayReferenceNodeProperty(JAXBContextImpl p, RuntimeReferencePropertyInfo prop) {
      super(p, prop, prop.getXmlName(), prop.isCollectionNillable());

      for(RuntimeElement e : prop.getElements()) {
         JaxBeanInfo bi = p.getOrCreate((RuntimeTypeInfo)e);
         this.expectedElements.put(e.getElementName().getNamespaceURI(), e.getElementName().getLocalPart(), bi);
      }

      this.isMixed = prop.isMixed();
      if (prop.getWildcard() != null) {
         this.domHandler = (DomHandler)ClassFactory.create((Class)prop.getDOMHandler());
         this.wcMode = prop.getWildcard();
      } else {
         this.domHandler = null;
         this.wcMode = null;
      }

   }

   protected final void serializeListBody(Object o, XMLSerializer w, Object list) throws IOException, XMLStreamException, SAXException {
      ListIterator<ItemT> itr = this.lister.iterator(list, w);

      while(itr.hasNext()) {
         try {
            ItemT item = (ItemT)itr.next();
            if (item != null) {
               if (this.isMixed && item.getClass() == String.class) {
                  w.text((String)((String)item), (String)null);
               } else {
                  JaxBeanInfo bi = w.grammar.getBeanInfo(item, true);
                  if (bi.jaxbType == Object.class && this.domHandler != null) {
                     w.writeDom(item, this.domHandler, o, this.fieldName);
                  } else {
                     bi.serializeRoot(item, w);
                  }
               }
            }
         } catch (JAXBException e) {
            w.reportError(this.fieldName, e);
         }
      }

   }

   public void createBodyUnmarshaller(UnmarshallerChain chain, QNameMap loaders) {
      int offset = chain.allocateOffset();
      Receiver recv = new ArrayERProperty.ReceiverImpl(offset);

      for(QNameMap.Entry n : this.expectedElements.entrySet()) {
         JaxBeanInfo beanInfo = (JaxBeanInfo)n.getValue();
         loaders.put(n.nsUri, n.localName, new ChildLoader(beanInfo.getLoader(chain.context, true), recv));
      }

      if (this.isMixed) {
         loaders.put((QName)TEXT_HANDLER, new ChildLoader(new MixedTextLoader(recv), (Receiver)null));
      }

      if (this.domHandler != null) {
         loaders.put((QName)CATCH_ALL, new ChildLoader(new WildcardLoader(this.domHandler, this.wcMode), recv));
      }

   }

   public PropertyKind getKind() {
      return PropertyKind.REFERENCE;
   }

   public Accessor getElementPropertyAccessor(String nsUri, String localName) {
      if (this.wrapperTagName != null) {
         if (this.wrapperTagName.equals(nsUri, localName)) {
            return this.acc;
         }
      } else if (this.expectedElements.containsKey(nsUri, localName)) {
         return this.acc;
      }

      return null;
   }

   private static final class MixedTextLoader extends Loader {
      private final Receiver recv;

      public MixedTextLoader(Receiver recv) {
         super(true);
         this.recv = recv;
      }

      public void text(UnmarshallingContext.State state, CharSequence text) throws SAXException {
         if (text.length() != 0) {
            this.recv.receive(state, text.toString());
         }

      }
   }
}
