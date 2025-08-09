package org.glassfish.jaxb.runtime.v2.runtime.property;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.annotation.DomHandler;
import java.io.IOException;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import org.glassfish.jaxb.core.v2.ClassFactory;
import org.glassfish.jaxb.core.v2.model.core.PropertyKind;
import org.glassfish.jaxb.core.v2.model.core.WildcardMode;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeElement;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeReferencePropertyInfo;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeTypeInfo;
import org.glassfish.jaxb.runtime.v2.runtime.ElementBeanInfoImpl;
import org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl;
import org.glassfish.jaxb.runtime.v2.runtime.JaxBeanInfo;
import org.glassfish.jaxb.runtime.v2.runtime.XMLSerializer;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.ChildLoader;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.WildcardLoader;
import org.glassfish.jaxb.runtime.v2.util.QNameMap;
import org.xml.sax.SAXException;

final class SingleReferenceNodeProperty extends PropertyImpl {
   private final Accessor acc;
   private final QNameMap expectedElements = new QNameMap();
   private final DomHandler domHandler;
   private final WildcardMode wcMode;

   public SingleReferenceNodeProperty(JAXBContextImpl context, RuntimeReferencePropertyInfo prop) {
      super(context, prop);
      this.acc = prop.getAccessor().optimize(context);

      for(RuntimeElement e : prop.getElements()) {
         this.expectedElements.put((QName)e.getElementName(), context.getOrCreate((RuntimeTypeInfo)e));
      }

      if (prop.getWildcard() != null) {
         this.domHandler = (DomHandler)ClassFactory.create((Class)prop.getDOMHandler());
         this.wcMode = prop.getWildcard();
      } else {
         this.domHandler = null;
         this.wcMode = null;
      }

   }

   public void reset(Object bean) throws AccessorException {
      this.acc.set(bean, (Object)null);
   }

   public String getIdValue(Object beanT) {
      return null;
   }

   public void serializeBody(Object o, XMLSerializer w, Object outerPeer) throws SAXException, AccessorException, IOException, XMLStreamException {
      ValueT v = (ValueT)this.acc.get(o);
      if (v != null) {
         try {
            JaxBeanInfo bi = w.grammar.getBeanInfo(v, true);
            if (bi.jaxbType == Object.class && this.domHandler != null) {
               w.writeDom(v, this.domHandler, o, this.fieldName);
            } else {
               bi.serializeRoot(v, w);
            }
         } catch (JAXBException e) {
            w.reportError(this.fieldName, e);
         }
      }

   }

   public void buildChildElementUnmarshallers(UnmarshallerChain chain, QNameMap handlers) {
      for(QNameMap.Entry n : this.expectedElements.entrySet()) {
         handlers.put(n.nsUri, n.localName, new ChildLoader(((JaxBeanInfo)n.getValue()).getLoader(chain.context, true), this.acc));
      }

      if (this.domHandler != null) {
         handlers.put((QName)CATCH_ALL, new ChildLoader(new WildcardLoader(this.domHandler, this.wcMode), this.acc));
      }

   }

   public PropertyKind getKind() {
      return PropertyKind.REFERENCE;
   }

   public Accessor getElementPropertyAccessor(String nsUri, String localName) {
      JaxBeanInfo bi = (JaxBeanInfo)this.expectedElements.get(nsUri, localName);
      if (bi != null) {
         if (bi instanceof ElementBeanInfoImpl) {
            final ElementBeanInfoImpl ebi = (ElementBeanInfoImpl)bi;
            return new Accessor(ebi.expectedType) {
               public Object get(Object bean) throws AccessorException {
                  ValueT r = (ValueT)SingleReferenceNodeProperty.this.acc.get(bean);
                  return r instanceof JAXBElement ? ((JAXBElement)r).getValue() : r;
               }

               public void set(Object bean, Object value) throws AccessorException {
                  if (value != null) {
                     try {
                        value = ebi.createInstanceFromValue(value);
                     } catch (ReflectiveOperationException e) {
                        throw new AccessorException(e);
                     }
                  }

                  SingleReferenceNodeProperty.this.acc.set(bean, value);
               }
            };
         } else {
            return this.acc;
         }
      } else {
         return null;
      }
   }
}
