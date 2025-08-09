package org.glassfish.jaxb.runtime.v2.model.annotation;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

final class XmlAccessorTypeQuick extends Quick implements XmlAccessorType {
   private final XmlAccessorType core;

   public XmlAccessorTypeQuick(Locatable upstream, XmlAccessorType core) {
      super(upstream);
      this.core = core;
   }

   protected Annotation getAnnotation() {
      return this.core;
   }

   protected Quick newInstance(Locatable upstream, Annotation core) {
      return new XmlAccessorTypeQuick(upstream, (XmlAccessorType)core);
   }

   public Class annotationType() {
      return XmlAccessorType.class;
   }

   public XmlAccessType value() {
      return this.core.value();
   }
}
