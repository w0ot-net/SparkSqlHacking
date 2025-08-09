package org.glassfish.jaxb.runtime.v2.runtime;

import com.sun.istack.FinalArrayList;
import jakarta.xml.bind.ValidationEventLocator;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.helpers.ValidationEventImpl;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import org.glassfish.jaxb.core.v2.ClassFactory;
import org.glassfish.jaxb.core.v2.model.core.ID;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeClassInfo;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimePropertyInfo;
import org.glassfish.jaxb.runtime.v2.runtime.property.AttributeProperty;
import org.glassfish.jaxb.runtime.v2.runtime.property.Property;
import org.glassfish.jaxb.runtime.v2.runtime.property.PropertyFactory;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.Loader;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.StructureLoader;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.UnmarshallingContext;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.XsiTypeLoader;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.LocatorImpl;

public final class ClassBeanInfoImpl extends JaxBeanInfo implements AttributeAccessor {
   public final Property[] properties;
   private Property idProperty;
   private Loader loader;
   private Loader loaderWithTypeSubst;
   private RuntimeClassInfo ci;
   private final Accessor inheritedAttWildcard;
   private final Transducer xducer;
   public final ClassBeanInfoImpl superClazz;
   private final Accessor xmlLocatorField;
   private final Name tagName;
   private boolean retainPropertyInfo = false;
   private AttributeProperty[] attributeProperties;
   private Property[] uriProperties;
   private final Method factoryMethod;
   private static final AttributeProperty[] EMPTY_PROPERTIES = new AttributeProperty[0];
   private static final Logger logger = org.glassfish.jaxb.core.Utils.getClassLogger();

   ClassBeanInfoImpl(JAXBContextImpl owner, RuntimeClassInfo ci) {
      super(owner, ci, (Class)ci.getClazz(), (QName)ci.getTypeName(), ci.isElement(), false, true);
      this.ci = ci;
      this.inheritedAttWildcard = ci.getAttributeWildcard();
      this.xducer = ci.getTransducer();
      this.factoryMethod = ci.getFactoryMethod();
      this.retainPropertyInfo = owner.retainPropertyInfo;
      if (this.factoryMethod != null) {
         int classMod = this.factoryMethod.getDeclaringClass().getModifiers();
         if (!Modifier.isPublic(classMod) || !Modifier.isPublic(this.factoryMethod.getModifiers())) {
            try {
               this.factoryMethod.setAccessible(true);
            } catch (SecurityException e) {
               logger.log(Level.FINE, "Unable to make the method of " + this.factoryMethod + " accessible", e);
               throw e;
            }
         }
      }

      if (ci.getBaseClass() == null) {
         this.superClazz = null;
      } else {
         this.superClazz = owner.getOrCreate(ci.getBaseClass());
      }

      if (this.superClazz != null && this.superClazz.xmlLocatorField != null) {
         this.xmlLocatorField = this.superClazz.xmlLocatorField;
      } else {
         this.xmlLocatorField = ci.getLocatorField();
      }

      Collection<? extends RuntimePropertyInfo> ps = ci.getProperties();
      this.properties = new Property[ps.size()];
      int idx = 0;
      boolean elementOnly = true;

      for(RuntimePropertyInfo info : ps) {
         Property p = PropertyFactory.create(owner, info);
         if (info.id() == ID.ID) {
            this.idProperty = p;
         }

         this.properties[idx++] = p;
         elementOnly &= info.elementOnlyContent();
         this.checkOverrideProperties(p);
      }

      this.hasElementOnlyContentModel(elementOnly);
      if (ci.isElement()) {
         this.tagName = owner.nameBuilder.createElementName(ci.getElementName());
      } else {
         this.tagName = null;
      }

      this.setLifecycleFlags();
   }

   private void checkOverrideProperties(Property p) {
      ClassBeanInfoImpl bi = this;

      while((bi = bi.superClazz) != null) {
         Property[] props = bi.properties;
         if (props == null) {
            break;
         }

         for(Property superProperty : props) {
            if (superProperty != null) {
               String spName = superProperty.getFieldName();
               if (spName != null && spName.equals(p.getFieldName())) {
                  superProperty.setHiddenByOverride(true);
               }
            }
         }
      }

   }

   protected void link(JAXBContextImpl grammar) {
      if (this.uriProperties == null) {
         super.link(grammar);
         if (this.superClazz != null) {
            this.superClazz.link(grammar);
         }

         this.getLoader(grammar, true);
         if (this.superClazz != null) {
            if (this.idProperty == null) {
               this.idProperty = this.superClazz.idProperty;
            }

            if (!this.superClazz.hasElementOnlyContentModel()) {
               this.hasElementOnlyContentModel(false);
            }
         }

         List<AttributeProperty> attProps = new FinalArrayList();
         List<Property> uriProps = new FinalArrayList();

         for(ClassBeanInfoImpl bi = this; bi != null; bi = bi.superClazz) {
            for(int i = 0; i < bi.properties.length; ++i) {
               Property p = bi.properties[i];
               if (p instanceof AttributeProperty) {
                  attProps.add((AttributeProperty)p);
               }

               if (p.hasSerializeURIAction()) {
                  uriProps.add(p);
               }
            }
         }

         if (grammar.c14nSupport) {
            Collections.sort(attProps);
         }

         if (attProps.isEmpty()) {
            this.attributeProperties = EMPTY_PROPERTIES;
         } else {
            this.attributeProperties = (AttributeProperty[])attProps.toArray(new AttributeProperty[0]);
         }

         if (uriProps.isEmpty()) {
            this.uriProperties = EMPTY_PROPERTIES;
         } else {
            this.uriProperties = (Property[])uriProps.toArray(new Property[0]);
         }

      }
   }

   public void wrapUp() {
      for(Property p : this.properties) {
         p.wrapUp();
      }

      this.ci = null;
      super.wrapUp();
   }

   public String getElementNamespaceURI(Object bean) {
      return this.tagName.nsUri;
   }

   public String getElementLocalName(Object bean) {
      return this.tagName.localName;
   }

   public Object createInstance(UnmarshallingContext context) throws IllegalAccessException, InvocationTargetException, InstantiationException, SAXException {
      BeanT bean = (BeanT)null;
      if (this.factoryMethod == null) {
         bean = (BeanT)ClassFactory.create0(this.jaxbType);
      } else {
         Object o = ClassFactory.create(this.factoryMethod);
         if (!this.jaxbType.isInstance(o)) {
            throw new InstantiationException("The factory method didn't return a correct object");
         }

         bean = (BeanT)o;
      }

      if (this.xmlLocatorField != null) {
         try {
            this.xmlLocatorField.set(bean, new LocatorImpl(context.getLocator()));
         } catch (AccessorException e) {
            context.handleError((Exception)e);
         }
      }

      return bean;
   }

   public boolean reset(Object bean, UnmarshallingContext context) throws SAXException {
      try {
         if (this.superClazz != null) {
            this.superClazz.reset(bean, context);
         }

         for(Property p : this.properties) {
            p.reset(bean);
         }

         return true;
      } catch (AccessorException e) {
         context.handleError((Exception)e);
         return false;
      }
   }

   public String getId(Object bean, XMLSerializer target) throws SAXException {
      if (this.idProperty != null) {
         try {
            return this.idProperty.getIdValue(bean);
         } catch (AccessorException e) {
            target.reportError((String)null, e);
         }
      }

      return null;
   }

   public void serializeRoot(Object bean, XMLSerializer target) throws SAXException, IOException, XMLStreamException {
      if (this.tagName == null) {
         Class beanClass = bean.getClass();
         String message;
         if (beanClass.isAnnotationPresent(XmlRootElement.class)) {
            message = Messages.UNABLE_TO_MARSHAL_UNBOUND_CLASS.format(beanClass.getName());
         } else {
            message = Messages.UNABLE_TO_MARSHAL_NON_ELEMENT.format(beanClass.getName());
         }

         target.reportError(new ValidationEventImpl(1, message, (ValidationEventLocator)null, (Throwable)null));
      } else {
         target.startElement(this.tagName, bean);
         target.childAsSoleContent(bean, (String)null);
         target.endElement();
         if (this.retainPropertyInfo) {
            target.currentProperty.remove();
         }
      }

   }

   public void serializeBody(Object bean, XMLSerializer target) throws SAXException, IOException, XMLStreamException {
      if (this.superClazz != null) {
         this.superClazz.serializeBody(bean, target);
      }

      try {
         for(Property p : this.properties) {
            if (this.retainPropertyInfo) {
               target.currentProperty.set(p);
            }

            boolean isThereAnOverridingProperty = p.isHiddenByOverride();
            if (isThereAnOverridingProperty && !bean.getClass().equals(this.jaxbType)) {
               if (isThereAnOverridingProperty) {
                  Class beanSuperClass = bean.getClass().getSuperclass();
                  if (Utils.REFLECTION_NAVIGATOR.getDeclaredField(beanSuperClass, p.getFieldName()) == null) {
                     p.serializeBody(bean, target, (Object)null);
                  }
               }
            } else {
               p.serializeBody(bean, target, (Object)null);
            }
         }
      } catch (AccessorException e) {
         target.reportError((String)null, e);
      }

   }

   public void serializeAttributes(Object bean, XMLSerializer target) throws SAXException, IOException, XMLStreamException {
      for(AttributeProperty p : this.attributeProperties) {
         try {
            if (this.retainPropertyInfo) {
               Property parentProperty = target.getCurrentProperty();
               target.currentProperty.set(p);
               p.serializeAttributes(bean, target);
               target.currentProperty.set(parentProperty);
            } else {
               p.serializeAttributes(bean, target);
            }

            if (p.attName.equals("http://www.w3.org/2001/XMLSchema-instance", "nil")) {
               this.isNilIncluded = true;
            }
         } catch (AccessorException e) {
            target.reportError((String)null, e);
         }
      }

      try {
         if (this.inheritedAttWildcard != null) {
            Map<QName, String> map = (Map)this.inheritedAttWildcard.get(bean);
            target.attWildcardAsAttributes(map, (String)null);
         }
      } catch (AccessorException e) {
         target.reportError((String)null, e);
      }

   }

   public void serializeURIs(Object bean, XMLSerializer target) throws SAXException {
      try {
         if (!this.retainPropertyInfo) {
            for(Property p : this.uriProperties) {
               p.serializeURIs(bean, target);
            }
         } else {
            Property parentProperty = target.getCurrentProperty();

            for(Property p : this.uriProperties) {
               target.currentProperty.set(p);
               p.serializeURIs(bean, target);
            }

            target.currentProperty.set(parentProperty);
         }

         if (this.inheritedAttWildcard != null) {
            Map<QName, String> map = (Map)this.inheritedAttWildcard.get(bean);
            target.attWildcardAsURIs(map, (String)null);
         }
      } catch (AccessorException e) {
         target.reportError((String)null, e);
      }

   }

   public Loader getLoader(JAXBContextImpl context, boolean typeSubstitutionCapable) {
      if (this.loader == null) {
         StructureLoader sl = new StructureLoader(this);
         this.loader = sl;
         if (this.ci.hasSubClasses()) {
            this.loaderWithTypeSubst = new XsiTypeLoader(this);
         } else {
            this.loaderWithTypeSubst = this.loader;
         }

         sl.init(context, this, this.ci.getAttributeWildcard());
      }

      return typeSubstitutionCapable ? this.loaderWithTypeSubst : this.loader;
   }

   public Transducer getTransducer() {
      return this.xducer;
   }
}
