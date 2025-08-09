package org.glassfish.jaxb.runtime.v2.runtime.property;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.xml.stream.XMLStreamException;
import org.glassfish.jaxb.core.v2.ClassFactory;
import org.glassfish.jaxb.core.v2.model.core.PropertyKind;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeMapPropertyInfo;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeTypeInfo;
import org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl;
import org.glassfish.jaxb.runtime.v2.runtime.JaxBeanInfo;
import org.glassfish.jaxb.runtime.v2.runtime.Name;
import org.glassfish.jaxb.runtime.v2.runtime.XMLSerializer;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.ChildLoader;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.Loader;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.Receiver;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.TagName;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.UnmarshallingContext;
import org.glassfish.jaxb.runtime.v2.util.QNameMap;
import org.xml.sax.SAXException;

final class SingleMapNodeProperty extends PropertyImpl {
   private final Accessor acc;
   private final Name tagName;
   private final Name entryTag;
   private final Name keyTag;
   private final Name valueTag;
   private final boolean nillable;
   private JaxBeanInfo keyBeanInfo;
   private JaxBeanInfo valueBeanInfo;
   private final Class mapImplClass;
   private static final Class[] knownImplClasses = new Class[]{HashMap.class, TreeMap.class, LinkedHashMap.class};
   private Loader keyLoader;
   private Loader valueLoader;
   private final Loader itemsLoader = new Loader(false) {
      private ThreadLocal target = new ThreadLocal();
      private ThreadLocal map = new ThreadLocal();

      public void startElement(UnmarshallingContext.State state, TagName ea) throws SAXException {
         try {
            BeanT target = (BeanT)state.getPrev().getTarget();
            ValueT mapValue = (ValueT)((Map)SingleMapNodeProperty.this.acc.get(target));
            if (mapValue == null) {
               mapValue = (ValueT)((Map)ClassFactory.create(SingleMapNodeProperty.this.mapImplClass));
            } else {
               mapValue.clear();
            }

            SingleMapNodeProperty.Stack.push(this.target, target);
            SingleMapNodeProperty.Stack.push(this.map, mapValue);
            state.setTarget(mapValue);
         } catch (AccessorException e) {
            handleGenericException(e, true);
            state.setTarget(new HashMap());
         }

      }

      public void leaveElement(UnmarshallingContext.State state, TagName ea) throws SAXException {
         super.leaveElement(state, ea);

         try {
            SingleMapNodeProperty.this.acc.set(SingleMapNodeProperty.Stack.pop(this.target), (Map)SingleMapNodeProperty.Stack.pop(this.map));
         } catch (AccessorException ex) {
            handleGenericException(ex, true);
         }

      }

      public void childElement(UnmarshallingContext.State state, TagName ea) throws SAXException {
         if (ea.matches(SingleMapNodeProperty.this.entryTag)) {
            state.setLoader(SingleMapNodeProperty.this.entryLoader);
         } else {
            super.childElement(state, ea);
         }

      }

      public Collection getExpectedChildElements() {
         return Collections.singleton(SingleMapNodeProperty.this.entryTag.toQName());
      }
   };
   private final Loader entryLoader = new Loader(false) {
      public void startElement(UnmarshallingContext.State state, TagName ea) {
         state.setTarget(new Object[2]);
      }

      public void leaveElement(UnmarshallingContext.State state, TagName ea) {
         Object[] keyValue = state.getTarget();
         Map map = (Map)state.getPrev().getTarget();
         map.put(keyValue[0], keyValue[1]);
      }

      public void childElement(UnmarshallingContext.State state, TagName ea) throws SAXException {
         if (ea.matches(SingleMapNodeProperty.this.keyTag)) {
            state.setLoader(SingleMapNodeProperty.this.keyLoader);
            state.setReceiver(SingleMapNodeProperty.keyReceiver);
         } else if (ea.matches(SingleMapNodeProperty.this.valueTag)) {
            state.setLoader(SingleMapNodeProperty.this.valueLoader);
            state.setReceiver(SingleMapNodeProperty.valueReceiver);
         } else {
            super.childElement(state, ea);
         }
      }

      public Collection getExpectedChildElements() {
         return Arrays.asList(SingleMapNodeProperty.this.keyTag.toQName(), SingleMapNodeProperty.this.valueTag.toQName());
      }
   };
   private static final Receiver keyReceiver = new ReceiverImpl(0);
   private static final Receiver valueReceiver = new ReceiverImpl(1);

   public SingleMapNodeProperty(JAXBContextImpl context, RuntimeMapPropertyInfo prop) {
      super(context, prop);
      this.acc = prop.getAccessor().optimize(context);
      this.tagName = context.nameBuilder.createElementName(prop.getXmlName());
      this.entryTag = context.nameBuilder.createElementName("", "entry");
      this.keyTag = context.nameBuilder.createElementName("", "key");
      this.valueTag = context.nameBuilder.createElementName("", "value");
      this.nillable = prop.isCollectionNillable();
      this.keyBeanInfo = context.getOrCreate((RuntimeTypeInfo)prop.getKeyType());
      this.valueBeanInfo = context.getOrCreate((RuntimeTypeInfo)prop.getValueType());
      Class<ValueT> sig = (Class)Utils.REFLECTION_NAVIGATOR.erasure(prop.getRawType());
      this.mapImplClass = ClassFactory.inferImplClass(sig, knownImplClasses);
   }

   public void reset(Object bean) throws AccessorException {
      this.acc.set(bean, (Object)null);
   }

   public String getIdValue(Object bean) {
      return null;
   }

   public PropertyKind getKind() {
      return PropertyKind.MAP;
   }

   public void buildChildElementUnmarshallers(UnmarshallerChain chain, QNameMap handlers) {
      this.keyLoader = this.keyBeanInfo.getLoader(chain.context, true);
      this.valueLoader = this.valueBeanInfo.getLoader(chain.context, true);
      handlers.put((Name)this.tagName, new ChildLoader(this.itemsLoader, (Receiver)null));
   }

   public void serializeBody(Object o, XMLSerializer w, Object outerPeer) throws SAXException, AccessorException, IOException, XMLStreamException {
      ValueT v = (ValueT)((Map)this.acc.get(o));
      if (v != null) {
         this.bareStartTag(w, this.tagName, v);

         for(Map.Entry e : v.entrySet()) {
            this.bareStartTag(w, this.entryTag, (Object)null);
            Object key = e.getKey();
            if (key != null) {
               w.startElement(this.keyTag, key);
               w.childAsXsiType(key, this.fieldName, this.keyBeanInfo, false);
               w.endElement();
            }

            Object value = e.getValue();
            if (value != null) {
               w.startElement(this.valueTag, value);
               w.childAsXsiType(value, this.fieldName, this.valueBeanInfo, false);
               w.endElement();
            }

            w.endElement();
         }

         w.endElement();
      } else if (this.nillable) {
         w.startElement(this.tagName, (Object)null);
         w.writeXsiNilTrue();
         w.endElement();
      }

   }

   private void bareStartTag(XMLSerializer w, Name tagName, Object peer) throws IOException, XMLStreamException, SAXException {
      w.startElement(tagName, peer);
      w.endNamespaceDecls(peer);
      w.endAttributes();
   }

   public Accessor getElementPropertyAccessor(String nsUri, String localName) {
      return this.tagName.equals(nsUri, localName) ? this.acc : null;
   }

   private static final class ReceiverImpl implements Receiver {
      private final int index;

      public ReceiverImpl(int index) {
         this.index = index;
      }

      public void receive(UnmarshallingContext.State state, Object o) {
         ((Object[])state.getTarget())[this.index] = o;
      }
   }

   private static final class Stack {
      private Stack parent;
      private Object value;

      private Stack(Stack parent, Object value) {
         this.parent = parent;
         this.value = value;
      }

      private Stack(Object value) {
         this.value = value;
      }

      private static void push(ThreadLocal holder, Object value) {
         Stack<T> parent = (Stack)holder.get();
         if (parent == null) {
            holder.set(new Stack(value));
         } else {
            holder.set(new Stack(parent, value));
         }

      }

      private static Object pop(ThreadLocal holder) {
         Stack<T> current = (Stack)holder.get();
         if (current.parent == null) {
            holder.remove();
         } else {
            holder.set(current.parent);
         }

         return current.value;
      }
   }
}
