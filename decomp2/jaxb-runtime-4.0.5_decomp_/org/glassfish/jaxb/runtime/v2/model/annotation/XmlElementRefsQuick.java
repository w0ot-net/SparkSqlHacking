package org.glassfish.jaxb.runtime.v2.model.annotation;

import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

final class XmlElementRefsQuick extends Quick implements XmlElementRefs {
   private final XmlElementRefs core;

   public XmlElementRefsQuick(Locatable upstream, XmlElementRefs core) {
      super(upstream);
      this.core = core;
   }

   protected Annotation getAnnotation() {
      return this.core;
   }

   protected Quick newInstance(Locatable upstream, Annotation core) {
      return new XmlElementRefsQuick(upstream, (XmlElementRefs)core);
   }

   public Class annotationType() {
      return XmlElementRefs.class;
   }

   public XmlElementRef[] value() {
      return this.core.value();
   }
}
