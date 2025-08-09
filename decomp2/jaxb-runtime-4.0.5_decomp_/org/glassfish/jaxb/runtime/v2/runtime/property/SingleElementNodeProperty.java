package org.glassfish.jaxb.runtime.v2.runtime.property;

import jakarta.xml.bind.JAXBElement;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import org.glassfish.jaxb.core.v2.model.core.PropertyKind;
import org.glassfish.jaxb.core.v2.model.core.TypeRef;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeElementPropertyInfo;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeTypeInfo;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeTypeRef;
import org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl;
import org.glassfish.jaxb.runtime.v2.runtime.JaxBeanInfo;
import org.glassfish.jaxb.runtime.v2.runtime.Name;
import org.glassfish.jaxb.runtime.v2.runtime.XMLSerializer;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.ChildLoader;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.DefaultValueLoaderDecorator;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.Loader;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.XsiNilLoader;
import org.glassfish.jaxb.runtime.v2.util.QNameMap;
import org.xml.sax.SAXException;

final class SingleElementNodeProperty extends PropertyImpl {
   private final Accessor acc;
   private final boolean nillable;
   private final QName[] acceptedElements;
   private final Map typeNames = new HashMap();
   private RuntimeElementPropertyInfo prop;
   private final Name nullTagName;

   public SingleElementNodeProperty(JAXBContextImpl context, RuntimeElementPropertyInfo prop) {
      super(context, prop);
      this.acc = prop.getAccessor().optimize(context);
      this.prop = prop;
      QName nt = null;
      boolean nil = false;
      this.acceptedElements = new QName[prop.getTypes().size()];

      for(int i = 0; i < this.acceptedElements.length; ++i) {
         this.acceptedElements[i] = ((RuntimeTypeRef)prop.getTypes().get(i)).getTagName();
      }

      for(RuntimeTypeRef e : prop.getTypes()) {
         JaxBeanInfo beanInfo = context.getOrCreate((RuntimeTypeInfo)e.getTarget());
         if (nt == null) {
            nt = e.getTagName();
         }

         this.typeNames.put(beanInfo.jaxbType, new TagAndType(context.nameBuilder.createElementName(e.getTagName()), beanInfo));
         nil |= e.isNillable();
      }

      this.nullTagName = context.nameBuilder.createElementName(nt);
      this.nillable = nil;
   }

   public void wrapUp() {
      super.wrapUp();
      this.prop = null;
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
         Class vtype = v.getClass();
         TagAndType tt = (TagAndType)this.typeNames.get(vtype);
         if (tt == null) {
            for(Map.Entry e : this.typeNames.entrySet()) {
               if (((Class)e.getKey()).isAssignableFrom(vtype)) {
                  tt = (TagAndType)e.getValue();
                  break;
               }
            }
         }

         boolean addNilDecl = o instanceof JAXBElement && ((JAXBElement)o).isNil();
         if (tt == null) {
            w.startElement(((TagAndType)this.typeNames.values().iterator().next()).tagName, (Object)null);
            w.childAsXsiType(v, this.fieldName, w.grammar.getBeanInfo(Object.class), addNilDecl && this.nillable);
         } else {
            w.startElement(tt.tagName, (Object)null);
            w.childAsXsiType(v, this.fieldName, tt.beanInfo, addNilDecl && this.nillable);
         }

         w.endElement();
      } else if (this.nillable) {
         w.startElement(this.nullTagName, (Object)null);
         w.writeXsiNilTrue();
         w.endElement();
      }

   }

   public void buildChildElementUnmarshallers(UnmarshallerChain chain, QNameMap handlers) {
      JAXBContextImpl context = chain.context;

      for(TypeRef e : this.prop.getTypes()) {
         JaxBeanInfo bi = context.getOrCreate((RuntimeTypeInfo)e.getTarget());
         Loader l = bi.getLoader(context, !Modifier.isFinal(bi.jaxbType.getModifiers()));
         if (e.getDefaultValue() != null) {
            l = new DefaultValueLoaderDecorator(l, e.getDefaultValue());
         }

         if (this.nillable || chain.context.allNillable) {
            l = new XsiNilLoader.Single(l, this.acc);
         }

         handlers.put((QName)e.getTagName(), new ChildLoader(l, this.acc));
      }

   }

   public PropertyKind getKind() {
      return PropertyKind.ELEMENT;
   }

   public Accessor getElementPropertyAccessor(String nsUri, String localName) {
      for(QName n : this.acceptedElements) {
         if (n.getNamespaceURI().equals(nsUri) && n.getLocalPart().equals(localName)) {
            return this.acc;
         }
      }

      return null;
   }
}
