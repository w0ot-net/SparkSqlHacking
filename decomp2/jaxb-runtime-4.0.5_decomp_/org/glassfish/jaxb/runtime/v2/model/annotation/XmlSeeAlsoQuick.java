package org.glassfish.jaxb.runtime.v2.model.annotation;

import jakarta.xml.bind.annotation.XmlSeeAlso;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

final class XmlSeeAlsoQuick extends Quick implements XmlSeeAlso {
   private final XmlSeeAlso core;

   public XmlSeeAlsoQuick(Locatable upstream, XmlSeeAlso core) {
      super(upstream);
      this.core = core;
   }

   protected Annotation getAnnotation() {
      return this.core;
   }

   protected Quick newInstance(Locatable upstream, Annotation core) {
      return new XmlSeeAlsoQuick(upstream, (XmlSeeAlso)core);
   }

   public Class annotationType() {
      return XmlSeeAlso.class;
   }

   public Class[] value() {
      return this.core.value();
   }
}
