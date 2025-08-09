package org.glassfish.jaxb.runtime.v2.model.annotation;

import jakarta.xml.bind.annotation.XmlEnumValue;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

final class XmlEnumValueQuick extends Quick implements XmlEnumValue {
   private final XmlEnumValue core;

   public XmlEnumValueQuick(Locatable upstream, XmlEnumValue core) {
      super(upstream);
      this.core = core;
   }

   protected Annotation getAnnotation() {
      return this.core;
   }

   protected Quick newInstance(Locatable upstream, Annotation core) {
      return new XmlEnumValueQuick(upstream, (XmlEnumValue)core);
   }

   public Class annotationType() {
      return XmlEnumValue.class;
   }

   public String value() {
      return this.core.value();
   }
}
