package org.glassfish.jaxb.runtime.v2.model.impl;

import com.sun.istack.NotNull;
import jakarta.xml.bind.JAXBException;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import org.glassfish.jaxb.core.annotation.XmlLocation;
import org.glassfish.jaxb.core.v2.ClassFactory;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;
import org.glassfish.jaxb.core.v2.model.core.AttributePropertyInfo;
import org.glassfish.jaxb.core.v2.model.core.ElementPropertyInfo;
import org.glassfish.jaxb.core.v2.model.core.MapPropertyInfo;
import org.glassfish.jaxb.core.v2.model.core.PropertyKind;
import org.glassfish.jaxb.core.v2.model.core.ReferencePropertyInfo;
import org.glassfish.jaxb.core.v2.model.core.ValuePropertyInfo;
import org.glassfish.jaxb.core.v2.runtime.IllegalAnnotationException;
import org.glassfish.jaxb.core.v2.runtime.Location;
import org.glassfish.jaxb.runtime.AccessorFactory;
import org.glassfish.jaxb.runtime.AccessorFactoryImpl;
import org.glassfish.jaxb.runtime.InternalAccessorFactory;
import org.glassfish.jaxb.runtime.XmlAccessorFactory;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeClassInfo;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeElement;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimePropertyInfo;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeValuePropertyInfo;
import org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl;
import org.glassfish.jaxb.runtime.v2.runtime.Name;
import org.glassfish.jaxb.runtime.v2.runtime.Transducer;
import org.glassfish.jaxb.runtime.v2.runtime.XMLSerializer;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.TransducedAccessor;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.UnmarshallingContext;
import org.xml.sax.SAXException;

class RuntimeClassInfoImpl extends ClassInfoImpl implements RuntimeClassInfo, RuntimeElement {
   private Accessor xmlLocationAccessor;
   private AccessorFactory accessorFactory;
   private boolean supressAccessorWarnings = false;
   private Accessor attributeWildcardAccessor;
   private boolean computedTransducer = false;
   private Transducer xducer = null;

   public RuntimeClassInfoImpl(RuntimeModelBuilder modelBuilder, Locatable upstream, Class clazz) {
      super(modelBuilder, upstream, clazz);
      this.accessorFactory = this.createAccessorFactory(clazz);
   }

   protected AccessorFactory createAccessorFactory(Class clazz) {
      AccessorFactory accFactory = null;
      JAXBContextImpl context = ((RuntimeModelBuilder)this.builder).context;
      if (context != null) {
         this.supressAccessorWarnings = context.supressAccessorWarnings;
         if (context.xmlAccessorFactorySupport) {
            XmlAccessorFactory factoryAnn = this.findXmlAccessorFactoryAnnotation(clazz);
            if (factoryAnn != null) {
               try {
                  accFactory = (AccessorFactory)factoryAnn.value().getConstructor().newInstance();
               } catch (NoSuchMethodException | InvocationTargetException | InstantiationException var6) {
                  this.builder.reportError(new IllegalAnnotationException(Messages.ACCESSORFACTORY_INSTANTIATION_EXCEPTION.format(factoryAnn.getClass().getName(), this.nav().getClassName(clazz)), this));
               } catch (IllegalAccessException var7) {
                  this.builder.reportError(new IllegalAnnotationException(Messages.ACCESSORFACTORY_ACCESS_EXCEPTION.format(factoryAnn.getClass().getName(), this.nav().getClassName(clazz)), this));
               }
            }
         }
      }

      if (accFactory == null) {
         accFactory = AccessorFactoryImpl.getInstance();
      }

      return accFactory;
   }

   protected XmlAccessorFactory findXmlAccessorFactoryAnnotation(Class clazz) {
      XmlAccessorFactory factoryAnn = (XmlAccessorFactory)this.reader().getClassAnnotation(XmlAccessorFactory.class, clazz, this);
      if (factoryAnn == null) {
         factoryAnn = (XmlAccessorFactory)this.reader().getPackageAnnotation(XmlAccessorFactory.class, clazz, this);
      }

      return factoryAnn;
   }

   public Method getFactoryMethod() {
      return super.getFactoryMethod();
   }

   public final RuntimeClassInfoImpl getBaseClass() {
      return (RuntimeClassInfoImpl)super.getBaseClass();
   }

   protected ReferencePropertyInfo createReferenceProperty(PropertySeed seed) {
      return new RuntimeReferencePropertyInfoImpl(this, seed);
   }

   protected AttributePropertyInfo createAttributeProperty(PropertySeed seed) {
      return new RuntimeAttributePropertyInfoImpl(this, seed);
   }

   protected ValuePropertyInfo createValueProperty(PropertySeed seed) {
      return new RuntimeValuePropertyInfoImpl(this, seed);
   }

   protected ElementPropertyInfo createElementProperty(PropertySeed seed) {
      return new RuntimeElementPropertyInfoImpl(this, seed);
   }

   protected MapPropertyInfo createMapProperty(PropertySeed seed) {
      return new RuntimeMapPropertyInfoImpl(this, seed);
   }

   public List getProperties() {
      return super.getProperties();
   }

   public RuntimePropertyInfo getProperty(String name) {
      return (RuntimePropertyInfo)super.getProperty(name);
   }

   public void link() {
      this.getTransducer();
      super.link();
   }

   public Accessor getAttributeWildcard() {
      for(RuntimeClassInfoImpl c = this; c != null; c = c.getBaseClass()) {
         if (c.attributeWildcard != null) {
            if (c.attributeWildcardAccessor == null) {
               c.attributeWildcardAccessor = c.createAttributeWildcardAccessor();
            }

            return c.attributeWildcardAccessor;
         }
      }

      return null;
   }

   public Transducer getTransducer() {
      if (!this.computedTransducer) {
         this.computedTransducer = true;
         this.xducer = this.calcTransducer();
      }

      return this.xducer;
   }

   private Transducer calcTransducer() {
      RuntimeValuePropertyInfo valuep = null;
      if (this.hasAttributeWildcard()) {
         return null;
      } else {
         for(RuntimeClassInfoImpl ci = this; ci != null; ci = ci.getBaseClass()) {
            for(RuntimePropertyInfo pi : ci.getProperties()) {
               if (pi.kind() != PropertyKind.VALUE) {
                  return null;
               }

               valuep = (RuntimeValuePropertyInfo)pi;
            }
         }

         if (valuep == null) {
            return null;
         } else if (!valuep.getTarget().isSimpleType()) {
            return null;
         } else {
            return new TransducerImpl((Class)this.getClazz(), TransducedAccessor.get(((RuntimeModelBuilder)this.builder).context, valuep));
         }
      }
   }

   private Accessor createAttributeWildcardAccessor() {
      assert this.attributeWildcard != null;

      return ((RuntimePropertySeed)this.attributeWildcard).getAccessor();
   }

   protected RuntimePropertySeed createFieldSeed(Field field) {
      boolean readOnly = Modifier.isStatic(field.getModifiers());

      Accessor acc;
      try {
         if (this.supressAccessorWarnings) {
            acc = ((InternalAccessorFactory)this.accessorFactory).createFieldAccessor((Class)this.clazz, field, readOnly, this.supressAccessorWarnings);
         } else {
            acc = this.accessorFactory.createFieldAccessor((Class)this.clazz, field, readOnly);
         }
      } catch (JAXBException e) {
         this.builder.reportError(new IllegalAnnotationException(Messages.CUSTOM_ACCESSORFACTORY_FIELD_ERROR.format(this.nav().getClassName((Class)this.clazz), e.toString()), this));
         acc = Accessor.getErrorInstance();
      }

      return new RuntimePropertySeed(super.createFieldSeed(field), acc);
   }

   public RuntimePropertySeed createAccessorSeed(Method getter, Method setter) {
      Accessor acc;
      try {
         acc = this.accessorFactory.createPropertyAccessor((Class)this.clazz, getter, setter);
      } catch (JAXBException e) {
         this.builder.reportError(new IllegalAnnotationException(Messages.CUSTOM_ACCESSORFACTORY_PROPERTY_ERROR.format(this.nav().getClassName((Class)this.clazz), e.toString()), this));
         acc = Accessor.getErrorInstance();
      }

      return new RuntimePropertySeed(super.createAccessorSeed(getter, setter), acc);
   }

   protected void checkFieldXmlLocation(Field f) {
      if (this.reader().hasFieldAnnotation(XmlLocation.class, f)) {
         this.xmlLocationAccessor = new Accessor.FieldReflection(f);
      }

   }

   public Accessor getLocatorField() {
      return this.xmlLocationAccessor;
   }

   static final class RuntimePropertySeed implements PropertySeed {
      private final Accessor acc;
      private final PropertySeed core;

      public RuntimePropertySeed(PropertySeed core, Accessor acc) {
         this.core = core;
         this.acc = acc;
      }

      public String getName() {
         return this.core.getName();
      }

      public Annotation readAnnotation(Class annotationType) {
         return this.core.readAnnotation(annotationType);
      }

      public boolean hasAnnotation(Class annotationType) {
         return this.core.hasAnnotation(annotationType);
      }

      public Type getRawType() {
         return (Type)this.core.getRawType();
      }

      public Location getLocation() {
         return this.core.getLocation();
      }

      public Locatable getUpstream() {
         return this.core.getUpstream();
      }

      public Accessor getAccessor() {
         return this.acc;
      }
   }

   private static final class TransducerImpl implements Transducer {
      private final TransducedAccessor xacc;
      private final Class ownerClass;

      public TransducerImpl(Class ownerClass, TransducedAccessor xacc) {
         this.xacc = xacc;
         this.ownerClass = ownerClass;
      }

      public boolean useNamespace() {
         return this.xacc.useNamespace();
      }

      public void declareNamespace(Object bean, XMLSerializer w) throws AccessorException {
         try {
            this.xacc.declareNamespace(bean, w);
         } catch (SAXException e) {
            throw new AccessorException(e);
         }
      }

      @NotNull
      public CharSequence print(Object o) throws AccessorException {
         try {
            CharSequence value = this.xacc.print(o);
            if (value == null) {
               throw new AccessorException(Messages.THERE_MUST_BE_VALUE_IN_XMLVALUE.format(o));
            } else {
               return value;
            }
         } catch (SAXException e) {
            throw new AccessorException(e);
         }
      }

      public Object parse(CharSequence lexical) throws AccessorException, SAXException {
         UnmarshallingContext ctxt = UnmarshallingContext.getInstance();
         BeanT inst;
         if (ctxt != null) {
            inst = (BeanT)ctxt.createInstance(this.ownerClass);
         } else {
            inst = (BeanT)ClassFactory.create(this.ownerClass);
         }

         this.xacc.parse(inst, lexical);
         return inst;
      }

      public void writeText(XMLSerializer w, Object o, String fieldName) throws IOException, SAXException, XMLStreamException, AccessorException {
         if (!this.xacc.hasValue(o)) {
            throw new AccessorException(Messages.THERE_MUST_BE_VALUE_IN_XMLVALUE.format(o));
         } else {
            this.xacc.writeText(w, o, fieldName);
         }
      }

      public void writeLeafElement(XMLSerializer w, Name tagName, Object o, String fieldName) throws IOException, SAXException, XMLStreamException, AccessorException {
         if (!this.xacc.hasValue(o)) {
            throw new AccessorException(Messages.THERE_MUST_BE_VALUE_IN_XMLVALUE.format(o));
         } else {
            this.xacc.writeLeafElement(w, tagName, o, fieldName);
         }
      }

      public QName getTypeName(Object instance) {
         return null;
      }
   }
}
