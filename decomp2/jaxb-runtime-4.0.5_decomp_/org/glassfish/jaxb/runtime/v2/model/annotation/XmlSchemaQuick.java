package org.glassfish.jaxb.runtime.v2.model.annotation;

import jakarta.xml.bind.annotation.XmlNs;
import jakarta.xml.bind.annotation.XmlNsForm;
import jakarta.xml.bind.annotation.XmlSchema;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

final class XmlSchemaQuick extends Quick implements XmlSchema {
   private final XmlSchema core;

   public XmlSchemaQuick(Locatable upstream, XmlSchema core) {
      super(upstream);
      this.core = core;
   }

   protected Annotation getAnnotation() {
      return this.core;
   }

   protected Quick newInstance(Locatable upstream, Annotation core) {
      return new XmlSchemaQuick(upstream, (XmlSchema)core);
   }

   public Class annotationType() {
      return XmlSchema.class;
   }

   public XmlNsForm attributeFormDefault() {
      return this.core.attributeFormDefault();
   }

   public XmlNsForm elementFormDefault() {
      return this.core.elementFormDefault();
   }

   public String location() {
      return this.core.location();
   }

   public String namespace() {
      return this.core.namespace();
   }

   public XmlNs[] xmlns() {
      return this.core.xmlns();
   }
}
