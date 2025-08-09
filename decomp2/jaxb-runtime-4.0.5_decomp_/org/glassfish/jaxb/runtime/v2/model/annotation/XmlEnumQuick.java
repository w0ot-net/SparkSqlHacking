package org.glassfish.jaxb.runtime.v2.model.annotation;

import jakarta.xml.bind.annotation.XmlEnum;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

final class XmlEnumQuick extends Quick implements XmlEnum {
   private final XmlEnum core;

   public XmlEnumQuick(Locatable upstream, XmlEnum core) {
      super(upstream);
      this.core = core;
   }

   protected Annotation getAnnotation() {
      return this.core;
   }

   protected Quick newInstance(Locatable upstream, Annotation core) {
      return new XmlEnumQuick(upstream, (XmlEnum)core);
   }

   public Class annotationType() {
      return XmlEnum.class;
   }

   public Class value() {
      return this.core.value();
   }
}
