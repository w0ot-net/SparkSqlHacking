package org.glassfish.jaxb.runtime.v2.model.impl;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSchema;
import javax.xml.namespace.QName;
import org.glassfish.jaxb.core.api.impl.NameConverter;
import org.glassfish.jaxb.core.v2.model.core.AttributePropertyInfo;
import org.glassfish.jaxb.core.v2.model.core.PropertyKind;

class AttributePropertyInfoImpl extends SingleTypePropertyInfoImpl implements AttributePropertyInfo {
   private final QName xmlName;
   private final boolean isRequired;

   AttributePropertyInfoImpl(ClassInfoImpl parent, PropertySeed seed) {
      super(parent, seed);
      XmlAttribute att = (XmlAttribute)seed.readAnnotation(XmlAttribute.class);

      assert att != null;

      if (att.required()) {
         this.isRequired = true;
      } else {
         this.isRequired = this.nav().isPrimitive(this.getIndividualType());
      }

      this.xmlName = this.calcXmlName(att);
   }

   private QName calcXmlName(XmlAttribute att) {
      String uri = att.namespace();
      String local = att.name();
      if (local.equals("##default")) {
         local = NameConverter.standard.toVariableName(this.getName());
      }

      if (uri.equals("##default")) {
         XmlSchema xs = (XmlSchema)this.reader().getPackageAnnotation(XmlSchema.class, this.parent.getClazz(), this);
         if (xs != null) {
            switch (xs.attributeFormDefault()) {
               case QUALIFIED:
                  uri = this.parent.getTypeName().getNamespaceURI();
                  if (uri.length() == 0) {
                     uri = this.parent.builder.defaultNsUri;
                  }
                  break;
               case UNQUALIFIED:
               case UNSET:
                  uri = "";
            }
         } else {
            uri = "";
         }
      }

      return new QName(uri.intern(), local.intern());
   }

   public boolean isRequired() {
      return this.isRequired;
   }

   public final QName getXmlName() {
      return this.xmlName;
   }

   public final PropertyKind kind() {
      return PropertyKind.ATTRIBUTE;
   }
}
