package org.glassfish.jaxb.runtime.v2.model.impl;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchema;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import org.glassfish.jaxb.core.api.impl.NameConverter;
import org.glassfish.jaxb.core.v2.model.annotation.AnnotationReader;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;
import org.glassfish.jaxb.core.v2.model.core.TypeInfo;
import org.glassfish.jaxb.core.v2.model.nav.Navigator;

abstract class TypeInfoImpl implements TypeInfo, Locatable {
   private final Locatable upstream;
   protected final TypeInfoSetImpl owner;
   protected ModelBuilder builder;

   protected TypeInfoImpl(ModelBuilder builder, Locatable upstream) {
      this.builder = builder;
      this.owner = builder.typeInfoSet;
      this.upstream = upstream;
   }

   public Locatable getUpstream() {
      return this.upstream;
   }

   void link() {
      this.builder = null;
   }

   protected final Navigator nav() {
      return this.owner.nav;
   }

   protected final AnnotationReader reader() {
      return this.owner.reader;
   }

   protected final QName parseElementName(Object clazz) {
      XmlRootElement e = (XmlRootElement)this.reader().getClassAnnotation(XmlRootElement.class, clazz, this);
      if (e == null) {
         return null;
      } else {
         String local = e.name();
         if (local.equals("##default")) {
            local = NameConverter.standard.toVariableName(this.nav().getClassShortName(clazz));
         }

         String nsUri = e.namespace();
         if (nsUri.equals("##default")) {
            XmlSchema xs = (XmlSchema)this.reader().getPackageAnnotation(XmlSchema.class, clazz, this);
            if (xs != null) {
               nsUri = xs.namespace();
            } else {
               nsUri = this.builder.defaultNsUri;
            }
         }

         return new QName(nsUri.intern(), local.intern());
      }
   }

   protected final QName parseTypeName(Object clazz) {
      return this.parseTypeName(clazz, (XmlType)this.reader().getClassAnnotation(XmlType.class, clazz, this));
   }

   protected final QName parseTypeName(Object clazz, XmlType t) {
      String nsUri = "##default";
      String local = "##default";
      if (t != null) {
         nsUri = t.namespace();
         local = t.name();
      }

      if (local.length() == 0) {
         return null;
      } else {
         if (local.equals("##default")) {
            local = NameConverter.standard.toVariableName(this.nav().getClassShortName(clazz));
         }

         if (nsUri.equals("##default")) {
            XmlSchema xs = (XmlSchema)this.reader().getPackageAnnotation(XmlSchema.class, clazz, this);
            if (xs != null) {
               nsUri = xs.namespace();
            } else {
               nsUri = this.builder.defaultNsUri;
            }
         }

         return new QName(nsUri.intern(), local.intern());
      }
   }
}
