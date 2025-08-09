package org.glassfish.jaxb.runtime.v2.model.annotation;

import jakarta.xml.bind.annotation.XmlRegistry;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

final class XmlRegistryQuick extends Quick implements XmlRegistry {
   private final XmlRegistry core;

   public XmlRegistryQuick(Locatable upstream, XmlRegistry core) {
      super(upstream);
      this.core = core;
   }

   protected Annotation getAnnotation() {
      return this.core;
   }

   protected Quick newInstance(Locatable upstream, Annotation core) {
      return new XmlRegistryQuick(upstream, (XmlRegistry)core);
   }

   public Class annotationType() {
      return XmlRegistry.class;
   }
}
