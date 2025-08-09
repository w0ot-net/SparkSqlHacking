package org.glassfish.jaxb.runtime.v2.runtime;

import jakarta.xml.bind.ValidationEventLocator;
import jakarta.xml.bind.helpers.ValidationEventImpl;
import java.io.IOException;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeLeafInfo;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.Loader;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.TextLoader;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.UnmarshallingContext;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.XsiTypeLoader;
import org.xml.sax.SAXException;

final class LeafBeanInfoImpl extends JaxBeanInfo {
   private final Loader loader;
   private final Loader loaderWithSubst;
   private final Transducer xducer;
   private final Name tagName;

   public LeafBeanInfoImpl(JAXBContextImpl grammar, RuntimeLeafInfo li) {
      super(grammar, li, li.getClazz(), (QName[])li.getTypeNames(), li.isElement(), true, false);
      this.xducer = li.getTransducer();
      this.loader = new TextLoader(this.xducer);
      this.loaderWithSubst = new XsiTypeLoader(this);
      if (this.isElement()) {
         this.tagName = grammar.nameBuilder.createElementName(li.getElementName());
      } else {
         this.tagName = null;
      }

   }

   public QName getTypeName(Object instance) {
      QName tn = this.xducer.getTypeName(instance);
      return tn != null ? tn : super.getTypeName(instance);
   }

   public String getElementNamespaceURI(Object t) {
      return this.tagName.nsUri;
   }

   public String getElementLocalName(Object t) {
      return this.tagName.localName;
   }

   public Object createInstance(UnmarshallingContext context) {
      throw new UnsupportedOperationException();
   }

   public boolean reset(Object bean, UnmarshallingContext context) {
      return false;
   }

   public String getId(Object bean, XMLSerializer target) {
      return null;
   }

   public void serializeBody(Object bean, XMLSerializer w) throws SAXException, IOException, XMLStreamException {
      try {
         this.xducer.writeText(w, bean, (String)null);
      } catch (AccessorException e) {
         w.reportError((String)null, e);
      }

   }

   public void serializeAttributes(Object bean, XMLSerializer target) {
   }

   public void serializeRoot(Object bean, XMLSerializer target) throws SAXException, IOException, XMLStreamException {
      if (this.tagName == null) {
         target.reportError(new ValidationEventImpl(1, Messages.UNABLE_TO_MARSHAL_NON_ELEMENT.format(bean.getClass().getName()), (ValidationEventLocator)null, (Throwable)null));
      } else {
         target.startElement(this.tagName, bean);
         target.childAsSoleContent(bean, (String)null);
         target.endElement();
      }

   }

   public void serializeURIs(Object bean, XMLSerializer target) throws SAXException {
      if (this.xducer.useNamespace()) {
         try {
            this.xducer.declareNamespace(bean, target);
         } catch (AccessorException e) {
            target.reportError((String)null, e);
         }
      }

   }

   public Loader getLoader(JAXBContextImpl context, boolean typeSubstitutionCapable) {
      return typeSubstitutionCapable ? this.loaderWithSubst : this.loader;
   }

   public Transducer getTransducer() {
      return this.xducer;
   }
}
