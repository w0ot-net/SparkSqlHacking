package org.glassfish.jaxb.runtime.v2.model.annotation;

import jakarta.xml.bind.annotation.XmlElement;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

final class XmlElementQuick extends Quick implements XmlElement {
   private final XmlElement core;

   public XmlElementQuick(Locatable upstream, XmlElement core) {
      super(upstream);
      this.core = core;
   }

   protected Annotation getAnnotation() {
      return this.core;
   }

   protected Quick newInstance(Locatable upstream, Annotation core) {
      return new XmlElementQuick(upstream, (XmlElement)core);
   }

   public Class annotationType() {
      return XmlElement.class;
   }

   public String defaultValue() {
      return this.core.defaultValue();
   }

   public String name() {
      return this.core.name();
   }

   public String namespace() {
      return this.core.namespace();
   }

   public boolean nillable() {
      return this.core.nillable();
   }

   public boolean required() {
      return this.core.required();
   }

   public Class type() {
      return this.core.type();
   }
}
