package org.glassfish.jaxb.runtime.v2.model.annotation;

import jakarta.xml.bind.annotation.XmlMixed;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

final class XmlMixedQuick extends Quick implements XmlMixed {
   private final XmlMixed core;

   public XmlMixedQuick(Locatable upstream, XmlMixed core) {
      super(upstream);
      this.core = core;
   }

   protected Annotation getAnnotation() {
      return this.core;
   }

   protected Quick newInstance(Locatable upstream, Annotation core) {
      return new XmlMixedQuick(upstream, (XmlMixed)core);
   }

   public Class annotationType() {
      return XmlMixed.class;
   }
}
