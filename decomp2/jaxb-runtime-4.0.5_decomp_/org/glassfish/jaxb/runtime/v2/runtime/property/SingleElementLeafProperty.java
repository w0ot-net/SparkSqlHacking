package org.glassfish.jaxb.runtime.v2.runtime.property;

import jakarta.xml.bind.JAXBElement;
import java.io.IOException;
import java.lang.reflect.Modifier;
import javax.xml.stream.XMLStreamException;
import org.glassfish.jaxb.core.v2.model.core.ID;
import org.glassfish.jaxb.core.v2.model.core.PropertyKind;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeElementPropertyInfo;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeTypeRef;
import org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl;
import org.glassfish.jaxb.runtime.v2.runtime.Name;
import org.glassfish.jaxb.runtime.v2.runtime.XMLSerializer;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.TransducedAccessor;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.ChildLoader;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.DefaultValueLoaderDecorator;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.LeafPropertyLoader;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.LeafPropertyXsiLoader;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.Loader;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.Receiver;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.XsiNilLoader;
import org.glassfish.jaxb.runtime.v2.util.QNameMap;
import org.xml.sax.SAXException;

final class SingleElementLeafProperty extends PropertyImpl {
   private final Name tagName;
   private final boolean nillable;
   private final Accessor acc;
   private final String defaultValue;
   private final TransducedAccessor xacc;
   private final boolean improvedXsiTypeHandling;
   private final boolean idRef;

   public SingleElementLeafProperty(JAXBContextImpl context, RuntimeElementPropertyInfo prop) {
      super(context, prop);
      RuntimeTypeRef ref = (RuntimeTypeRef)prop.getTypes().get(0);
      this.tagName = context.nameBuilder.createElementName(ref.getTagName());

      assert this.tagName != null;

      this.nillable = ref.isNillable();
      this.defaultValue = ref.getDefaultValue();
      this.acc = prop.getAccessor().optimize(context);
      this.xacc = TransducedAccessor.get(context, ref);

      assert this.xacc != null;

      this.improvedXsiTypeHandling = context.improvedXsiTypeHandling;
      this.idRef = ref.getSource().id() == ID.IDREF;
   }

   public void reset(Object o) throws AccessorException {
      this.acc.set(o, (Object)null);
   }

   public String getIdValue(Object bean) throws AccessorException, SAXException {
      return this.xacc.print(bean).toString();
   }

   public void serializeBody(Object o, XMLSerializer w, Object outerPeer) throws SAXException, AccessorException, IOException, XMLStreamException {
      boolean hasValue = this.xacc.hasValue(o);
      Object obj = null;

      try {
         obj = this.acc.getUnadapted(o);
      } catch (AccessorException var7) {
      }

      Class valueType = this.acc.getValueType();
      if (this.xsiTypeNeeded(o, w, obj, valueType)) {
         w.startElement(this.tagName, outerPeer);
         w.childAsXsiType(obj, this.fieldName, w.grammar.getBeanInfo(valueType), false);
         w.endElement();
      } else if (hasValue) {
         this.xacc.writeLeafElement(w, this.tagName, o, this.fieldName);
      } else if (this.nillable) {
         w.startElement(this.tagName, (Object)null);
         w.writeXsiNilTrue();
         w.endElement();
      }

   }

   private boolean xsiTypeNeeded(Object bean, XMLSerializer w, Object value, Class valueTypeClass) {
      if (!this.improvedXsiTypeHandling) {
         return false;
      } else if (this.acc.isAdapted()) {
         return false;
      } else if (value == null) {
         return false;
      } else if (value.getClass().equals(valueTypeClass)) {
         return false;
      } else if (this.idRef) {
         return false;
      } else if (valueTypeClass.isPrimitive()) {
         return false;
      } else {
         return this.acc.isValueTypeAbstractable() || this.isNillableAbstract(bean, w.grammar, value, valueTypeClass);
      }
   }

   private boolean isNillableAbstract(Object bean, JAXBContextImpl context, Object value, Class valueTypeClass) {
      if (!this.nillable) {
         return false;
      } else if (valueTypeClass != Object.class) {
         return false;
      } else if (bean.getClass() != JAXBElement.class) {
         return false;
      } else {
         JAXBElement jaxbElement = (JAXBElement)bean;
         Class valueClass = value.getClass();
         Class declaredTypeClass = jaxbElement.getDeclaredType();
         if (declaredTypeClass.equals(valueClass)) {
            return false;
         } else if (!declaredTypeClass.isAssignableFrom(valueClass)) {
            return false;
         } else {
            return !Modifier.isAbstract(declaredTypeClass.getModifiers()) ? false : this.acc.isAbstractable(declaredTypeClass);
         }
      }
   }

   public void buildChildElementUnmarshallers(UnmarshallerChain chain, QNameMap handlers) {
      Loader l = new LeafPropertyLoader(this.xacc);
      if (this.defaultValue != null) {
         l = new DefaultValueLoaderDecorator(l, this.defaultValue);
      }

      if (this.nillable || chain.context.allNillable) {
         l = new XsiNilLoader.Single(l, this.acc);
      }

      if (this.improvedXsiTypeHandling) {
         l = new LeafPropertyXsiLoader(l, this.xacc, this.acc);
      }

      handlers.put((Name)this.tagName, new ChildLoader(l, (Receiver)null));
   }

   public PropertyKind getKind() {
      return PropertyKind.ELEMENT;
   }

   public Accessor getElementPropertyAccessor(String nsUri, String localName) {
      return this.tagName.equals(nsUri, localName) ? this.acc : null;
   }
}
