package org.glassfish.jaxb.runtime.v2.model.annotation;

import jakarta.xml.bind.annotation.XmlID;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

final class XmlIDQuick extends Quick implements XmlID {
   private final XmlID core;

   public XmlIDQuick(Locatable upstream, XmlID core) {
      super(upstream);
      this.core = core;
   }

   protected Annotation getAnnotation() {
      return this.core;
   }

   protected Quick newInstance(Locatable upstream, Annotation core) {
      return new XmlIDQuick(upstream, (XmlID)core);
   }

   public Class annotationType() {
      return XmlID.class;
   }
}
