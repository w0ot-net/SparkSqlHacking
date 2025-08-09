package org.glassfish.jaxb.runtime.v2.model.annotation;

import jakarta.xml.bind.annotation.XmlAnyAttribute;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

final class XmlAnyAttributeQuick extends Quick implements XmlAnyAttribute {
   private final XmlAnyAttribute core;

   public XmlAnyAttributeQuick(Locatable upstream, XmlAnyAttribute core) {
      super(upstream);
      this.core = core;
   }

   protected Annotation getAnnotation() {
      return this.core;
   }

   protected Quick newInstance(Locatable upstream, Annotation core) {
      return new XmlAnyAttributeQuick(upstream, (XmlAnyAttribute)core);
   }

   public Class annotationType() {
      return XmlAnyAttribute.class;
   }
}
