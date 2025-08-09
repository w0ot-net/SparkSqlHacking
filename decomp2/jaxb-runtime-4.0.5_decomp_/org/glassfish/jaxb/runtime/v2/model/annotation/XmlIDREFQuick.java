package org.glassfish.jaxb.runtime.v2.model.annotation;

import jakarta.xml.bind.annotation.XmlIDREF;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

final class XmlIDREFQuick extends Quick implements XmlIDREF {
   private final XmlIDREF core;

   public XmlIDREFQuick(Locatable upstream, XmlIDREF core) {
      super(upstream);
      this.core = core;
   }

   protected Annotation getAnnotation() {
      return this.core;
   }

   protected Quick newInstance(Locatable upstream, Annotation core) {
      return new XmlIDREFQuick(upstream, (XmlIDREF)core);
   }

   public Class annotationType() {
      return XmlIDREF.class;
   }
}
