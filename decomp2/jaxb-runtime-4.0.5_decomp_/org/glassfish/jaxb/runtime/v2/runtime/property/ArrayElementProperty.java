package org.glassfish.jaxb.runtime.v2.runtime.property;

import jakarta.xml.bind.JAXBException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.stream.XMLStreamException;
import org.glassfish.jaxb.core.v2.model.core.PropertyKind;
import org.glassfish.jaxb.core.v2.runtime.RuntimeUtil;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeElementPropertyInfo;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeTypeInfo;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeTypeRef;
import org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl;
import org.glassfish.jaxb.runtime.v2.runtime.JaxBeanInfo;
import org.glassfish.jaxb.runtime.v2.runtime.Name;
import org.glassfish.jaxb.runtime.v2.runtime.Transducer;
import org.glassfish.jaxb.runtime.v2.runtime.XMLSerializer;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.ListIterator;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Lister;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.NullSafeAccessor;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.ChildLoader;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.DefaultValueLoaderDecorator;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.Loader;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.Receiver;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.TextLoader;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.XsiNilLoader;
import org.glassfish.jaxb.runtime.v2.util.QNameMap;
import org.xml.sax.SAXException;

abstract class ArrayElementProperty extends ArrayERProperty {
   private final Map typeMap = new HashMap();
   private Map refs = new HashMap();
   protected RuntimeElementPropertyInfo prop;
   private final Name nillableTagName;

   protected ArrayElementProperty(JAXBContextImpl grammar, RuntimeElementPropertyInfo prop) {
      super(grammar, prop, prop.getXmlName(), prop.isCollectionNillable());
      this.prop = prop;
      List<? extends RuntimeTypeRef> types = prop.getTypes();
      Name n = null;

      for(RuntimeTypeRef typeRef : types) {
         Class type = (Class)typeRef.getTarget().getType();
         if (type.isPrimitive()) {
            type = (Class)RuntimeUtil.primitiveToBox.get(type);
         }

         JaxBeanInfo beanInfo = grammar.getOrCreate((RuntimeTypeInfo)typeRef.getTarget());
         TagAndType tt = new TagAndType(grammar.nameBuilder.createElementName(typeRef.getTagName()), beanInfo);
         this.typeMap.put(type, tt);
         this.refs.put(typeRef, beanInfo);
         if (typeRef.isNillable() && n == null) {
            n = tt.tagName;
         }
      }

      this.nillableTagName = n;
   }

   public void wrapUp() {
      super.wrapUp();
      this.refs = null;
      this.prop = null;
   }

   protected void serializeListBody(Object beanT, XMLSerializer w, Object list) throws IOException, XMLStreamException, SAXException, AccessorException {
      ListIterator<ItemT> itr = this.lister.iterator(list, w);
      boolean isIdref = itr instanceof Lister.IDREFSIterator;

      while(itr.hasNext()) {
         try {
            ItemT item = (ItemT)itr.next();
            if (item == null) {
               if (this.nillableTagName != null) {
                  w.startElement(this.nillableTagName, (Object)null);
                  w.writeXsiNilTrue();
                  w.endElement();
               }
            } else {
               Class itemType = item.getClass();
               if (isIdref) {
                  itemType = ((Lister.IDREFSIterator)itr).last().getClass();
               }

               TagAndType tt;
               for(tt = (TagAndType)this.typeMap.get(itemType); tt == null && itemType != null; tt = (TagAndType)this.typeMap.get(itemType)) {
                  itemType = itemType.getSuperclass();
               }

               if (tt == null) {
                  w.startElement(((TagAndType)this.typeMap.values().iterator().next()).tagName, (Object)null);
                  w.childAsXsiType(item, this.fieldName, w.grammar.getBeanInfo(Object.class), false);
               } else {
                  w.startElement(tt.tagName, (Object)null);
                  this.serializeItem(tt.beanInfo, item, w);
               }

               w.endElement();
            }
         } catch (JAXBException e) {
            w.reportError(this.fieldName, e);
         }
      }

   }

   protected abstract void serializeItem(JaxBeanInfo var1, Object var2, XMLSerializer var3) throws SAXException, AccessorException, IOException, XMLStreamException;

   public void createBodyUnmarshaller(UnmarshallerChain chain, QNameMap loaders) {
      int offset = chain.allocateOffset();
      Receiver recv = new ArrayERProperty.ReceiverImpl(offset);

      for(RuntimeTypeRef typeRef : this.prop.getTypes()) {
         Name tagName = chain.context.nameBuilder.createElementName(typeRef.getTagName());
         Loader item = this.createItemUnmarshaller(chain, typeRef);
         if (typeRef.isNillable() || chain.context.allNillable) {
            item = new XsiNilLoader.Array(item);
         }

         if (typeRef.getDefaultValue() != null) {
            item = new DefaultValueLoaderDecorator(item, typeRef.getDefaultValue());
         }

         loaders.put((Name)tagName, new ChildLoader(item, recv));
      }

   }

   public final PropertyKind getKind() {
      return PropertyKind.ELEMENT;
   }

   private Loader createItemUnmarshaller(UnmarshallerChain chain, RuntimeTypeRef typeRef) {
      if (PropertyFactory.isLeaf(typeRef.getSource())) {
         Transducer xducer = typeRef.getTransducer();
         return new TextLoader(xducer);
      } else {
         return ((JaxBeanInfo)this.refs.get(typeRef)).getLoader(chain.context, true);
      }
   }

   public Accessor getElementPropertyAccessor(String nsUri, String localName) {
      if (this.wrapperTagName != null) {
         if (this.wrapperTagName.equals(nsUri, localName)) {
            return this.acc;
         }
      } else {
         for(TagAndType tt : this.typeMap.values()) {
            if (tt.tagName.equals(nsUri, localName)) {
               return new NullSafeAccessor(this.acc, this.lister);
            }
         }
      }

      return null;
   }
}
