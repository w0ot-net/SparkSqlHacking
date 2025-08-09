package org.glassfish.jaxb.runtime.v2.model.impl;

import com.sun.istack.FinalArrayList;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlList;
import java.util.AbstractList;
import java.util.Collections;
import java.util.List;
import javax.xml.namespace.QName;
import org.glassfish.jaxb.core.v2.model.core.ElementPropertyInfo;
import org.glassfish.jaxb.core.v2.model.core.ID;
import org.glassfish.jaxb.core.v2.model.core.PropertyKind;
import org.glassfish.jaxb.core.v2.model.core.TypeInfo;
import org.glassfish.jaxb.core.v2.runtime.IllegalAnnotationException;

class ElementPropertyInfoImpl extends ERPropertyInfoImpl implements ElementPropertyInfo {
   private List types;
   private final List ref = new AbstractList() {
      public TypeInfo get(int index) {
         return ((TypeRefImpl)ElementPropertyInfoImpl.this.getTypes().get(index)).getTarget();
      }

      public int size() {
         return ElementPropertyInfoImpl.this.getTypes().size();
      }
   };
   private Boolean isRequired;
   private final boolean isValueList;

   ElementPropertyInfoImpl(ClassInfoImpl parent, PropertySeed propertySeed) {
      super(parent, propertySeed);
      this.isValueList = this.seed.hasAnnotation(XmlList.class);
   }

   public List getTypes() {
      if (this.types == null) {
         this.types = new FinalArrayList();
         XmlElement[] ann = null;
         XmlElement xe = (XmlElement)this.seed.readAnnotation(XmlElement.class);
         XmlElements xes = (XmlElements)this.seed.readAnnotation(XmlElements.class);
         if (xe != null && xes != null) {
            ModelBuilder var10000 = this.parent.builder;
            Messages var10003 = Messages.MUTUALLY_EXCLUSIVE_ANNOTATIONS;
            Object[] var10004 = new Object[3];
            String var10007 = this.nav().getClassName(this.parent.getClazz());
            var10004[0] = var10007 + "#" + this.seed.getName();
            var10004[1] = xe.annotationType().getName();
            var10004[2] = xes.annotationType().getName();
            var10000.reportError(new IllegalAnnotationException(var10003.format(var10004), xe, xes));
         }

         this.isRequired = true;
         if (xe != null) {
            ann = new XmlElement[]{xe};
         } else if (xes != null) {
            ann = xes.value();
         }

         if (ann == null) {
            TypeT t = (TypeT)this.getIndividualType();
            if (!this.nav().isPrimitive(t) || this.isCollection()) {
               this.isRequired = false;
            }

            this.types.add(this.createTypeRef(this.calcXmlName((XmlElement)null), t, this.isCollection(), (String)null));
         } else {
            for(XmlElement item : ann) {
               QName name = this.calcXmlName(item);
               TypeT type = (TypeT)this.reader().getClassValue(item, "type");
               if (this.nav().isSameType(type, this.nav().ref(XmlElement.DEFAULT.class))) {
                  type = (TypeT)this.getIndividualType();
               }

               if ((!this.nav().isPrimitive(type) || this.isCollection()) && !item.required()) {
                  this.isRequired = false;
               }

               this.types.add(this.createTypeRef(name, type, item.nillable(), this.getDefaultValue(item.defaultValue())));
            }
         }

         this.types = Collections.unmodifiableList(this.types);

         assert !this.types.contains((Object)null);
      }

      return this.types;
   }

   private String getDefaultValue(String value) {
      return value.equals("\u0000") ? null : value;
   }

   protected TypeRefImpl createTypeRef(QName name, Object type, boolean isNillable, String defaultValue) {
      return new TypeRefImpl(this, name, type, isNillable, defaultValue);
   }

   public boolean isValueList() {
      return this.isValueList;
   }

   public boolean isRequired() {
      if (this.isRequired == null) {
         this.getTypes();
      }

      return this.isRequired;
   }

   public List ref() {
      return this.ref;
   }

   public final PropertyKind kind() {
      return PropertyKind.ELEMENT;
   }

   protected void link() {
      super.link();

      for(TypeRefImpl ref : this.getTypes()) {
         ref.link();
      }

      if (this.isValueList()) {
         if (this.id() != ID.IDREF) {
            for(TypeRefImpl ref : this.types) {
               if (!ref.getTarget().isSimpleType()) {
                  this.parent.builder.reportError(new IllegalAnnotationException(Messages.XMLLIST_NEEDS_SIMPLETYPE.format(this.nav().getTypeName(ref.getTarget().getType())), this));
                  break;
               }
            }
         }

         if (!this.isCollection()) {
            this.parent.builder.reportError(new IllegalAnnotationException(Messages.XMLLIST_ON_SINGLE_PROPERTY.format(), this));
         }
      }

   }
}
