package org.glassfish.jaxb.runtime.v2.model.impl;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlSchemaType;
import java.util.Iterator;
import javax.xml.namespace.QName;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;
import org.glassfish.jaxb.core.v2.model.core.ClassInfo;
import org.glassfish.jaxb.core.v2.model.core.Element;
import org.glassfish.jaxb.core.v2.model.core.EnumLeafInfo;
import org.glassfish.jaxb.core.v2.model.core.NonElement;
import org.glassfish.jaxb.core.v2.runtime.Location;

class EnumLeafInfoImpl extends TypeInfoImpl implements EnumLeafInfo, Element, Iterable {
   final Object clazz;
   NonElement baseType;
   private final Object type;
   private final QName typeName;
   private EnumConstantImpl firstConstant;
   private QName elementName;
   protected boolean tokenStringType;

   public EnumLeafInfoImpl(ModelBuilder builder, Locatable upstream, Object clazz, Object type) {
      super(builder, upstream);
      this.clazz = clazz;
      this.type = type;
      this.elementName = this.parseElementName(clazz);
      this.typeName = this.parseTypeName(clazz);
      XmlEnum xe = (XmlEnum)builder.reader.getClassAnnotation(XmlEnum.class, clazz, this);
      if (xe != null) {
         T base = (T)builder.reader.getClassValue(xe, "value");
         this.baseType = builder.getTypeInfo(base, this);
      } else {
         this.baseType = builder.getTypeInfo(builder.nav.ref(String.class), this);
      }

   }

   protected void calcConstants() {
      EnumConstantImpl<T, C, F, M> last = null;

      for(Object f : this.nav().getDeclaredFields(this.clazz)) {
         if (this.nav().isSameType(this.nav().getFieldType(f), this.nav().ref(String.class))) {
            XmlSchemaType schemaTypeAnnotation = (XmlSchemaType)this.builder.reader.getFieldAnnotation(XmlSchemaType.class, f, this);
            if (schemaTypeAnnotation != null && "token".equals(schemaTypeAnnotation.name())) {
               this.tokenStringType = true;
               break;
            }
         }
      }

      F[] constants = (F[])this.nav().getEnumConstants(this.clazz);

      for(int i = constants.length - 1; i >= 0; --i) {
         F constant = (F)constants[i];
         String name = this.nav().getFieldName(constant);
         XmlEnumValue xev = (XmlEnumValue)this.builder.reader.getFieldAnnotation(XmlEnumValue.class, constant, this);
         String literal;
         if (xev == null) {
            literal = name;
         } else {
            literal = xev.value();
         }

         last = this.createEnumConstant(name, literal, constant, last);
      }

      this.firstConstant = last;
   }

   protected EnumConstantImpl createEnumConstant(String name, String literal, Object constant, EnumConstantImpl last) {
      return new EnumConstantImpl(this, name, literal, last);
   }

   public Object getType() {
      return this.type;
   }

   public boolean isToken() {
      return this.tokenStringType;
   }

   /** @deprecated */
   @Deprecated
   public final boolean canBeReferencedByIDREF() {
      return false;
   }

   public QName getTypeName() {
      return this.typeName;
   }

   public Object getClazz() {
      return this.clazz;
   }

   public NonElement getBaseType() {
      return this.baseType;
   }

   public boolean isSimpleType() {
      return true;
   }

   public Location getLocation() {
      return this.nav().getClassLocation(this.clazz);
   }

   public Iterable getConstants() {
      if (this.firstConstant == null) {
         this.calcConstants();
      }

      return this;
   }

   public void link() {
      this.getConstants();
      super.link();
   }

   /** @deprecated */
   @Deprecated
   public Element getSubstitutionHead() {
      return null;
   }

   public QName getElementName() {
      return this.elementName;
   }

   public boolean isElement() {
      return this.elementName != null;
   }

   public Element asElement() {
      return this.isElement() ? this : null;
   }

   /** @deprecated */
   @Deprecated
   public ClassInfo getScope() {
      return null;
   }

   public Iterator iterator() {
      return new Iterator() {
         private EnumConstantImpl next;

         {
            this.next = EnumLeafInfoImpl.this.firstConstant;
         }

         public boolean hasNext() {
            return this.next != null;
         }

         public EnumConstantImpl next() {
            EnumConstantImpl<T, C, F, M> r = this.next;
            this.next = this.next.next;
            return r;
         }

         public void remove() {
            throw new UnsupportedOperationException();
         }
      };
   }
}
