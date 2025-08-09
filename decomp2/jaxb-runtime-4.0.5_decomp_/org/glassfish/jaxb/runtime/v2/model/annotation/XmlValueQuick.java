package org.glassfish.jaxb.runtime.v2.model.annotation;

import jakarta.xml.bind.annotation.XmlValue;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

final class XmlValueQuick extends Quick implements XmlValue {
   private final XmlValue core;

   public XmlValueQuick(Locatable upstream, XmlValue core) {
      super(upstream);
      this.core = core;
   }

   protected Annotation getAnnotation() {
      return this.core;
   }

   protected Quick newInstance(Locatable upstream, Annotation core) {
      return new XmlValueQuick(upstream, (XmlValue)core);
   }

   public Class annotationType() {
      return XmlValue.class;
   }
}
