package org.glassfish.jaxb.runtime.v2.model.annotation;

import jakarta.xml.bind.annotation.XmlTransient;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

final class XmlTransientQuick extends Quick implements XmlTransient {
   private final XmlTransient core;

   public XmlTransientQuick(Locatable upstream, XmlTransient core) {
      super(upstream);
      this.core = core;
   }

   protected Annotation getAnnotation() {
      return this.core;
   }

   protected Quick newInstance(Locatable upstream, Annotation core) {
      return new XmlTransientQuick(upstream, (XmlTransient)core);
   }

   public Class annotationType() {
      return XmlTransient.class;
   }
}
