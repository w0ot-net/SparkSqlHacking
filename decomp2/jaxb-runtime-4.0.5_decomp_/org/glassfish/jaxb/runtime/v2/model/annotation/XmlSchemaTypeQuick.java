package org.glassfish.jaxb.runtime.v2.model.annotation;

import jakarta.xml.bind.annotation.XmlSchemaType;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

final class XmlSchemaTypeQuick extends Quick implements XmlSchemaType {
   private final XmlSchemaType core;

   public XmlSchemaTypeQuick(Locatable upstream, XmlSchemaType core) {
      super(upstream);
      this.core = core;
   }

   protected Annotation getAnnotation() {
      return this.core;
   }

   protected Quick newInstance(Locatable upstream, Annotation core) {
      return new XmlSchemaTypeQuick(upstream, (XmlSchemaType)core);
   }

   public Class annotationType() {
      return XmlSchemaType.class;
   }

   public String name() {
      return this.core.name();
   }

   public String namespace() {
      return this.core.namespace();
   }

   public Class type() {
      return this.core.type();
   }
}
