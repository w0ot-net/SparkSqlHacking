package org.glassfish.jaxb.runtime.v2.model.annotation;

import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSchemaTypes;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

final class XmlSchemaTypesQuick extends Quick implements XmlSchemaTypes {
   private final XmlSchemaTypes core;

   public XmlSchemaTypesQuick(Locatable upstream, XmlSchemaTypes core) {
      super(upstream);
      this.core = core;
   }

   protected Annotation getAnnotation() {
      return this.core;
   }

   protected Quick newInstance(Locatable upstream, Annotation core) {
      return new XmlSchemaTypesQuick(upstream, (XmlSchemaTypes)core);
   }

   public Class annotationType() {
      return XmlSchemaTypes.class;
   }

   public XmlSchemaType[] value() {
      return this.core.value();
   }
}
