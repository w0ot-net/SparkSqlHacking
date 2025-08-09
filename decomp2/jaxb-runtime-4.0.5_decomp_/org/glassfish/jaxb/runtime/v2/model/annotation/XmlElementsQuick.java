package org.glassfish.jaxb.runtime.v2.model.annotation;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

final class XmlElementsQuick extends Quick implements XmlElements {
   private final XmlElements core;

   public XmlElementsQuick(Locatable upstream, XmlElements core) {
      super(upstream);
      this.core = core;
   }

   protected Annotation getAnnotation() {
      return this.core;
   }

   protected Quick newInstance(Locatable upstream, Annotation core) {
      return new XmlElementsQuick(upstream, (XmlElements)core);
   }

   public Class annotationType() {
      return XmlElements.class;
   }

   public XmlElement[] value() {
      return this.core.value();
   }
}
