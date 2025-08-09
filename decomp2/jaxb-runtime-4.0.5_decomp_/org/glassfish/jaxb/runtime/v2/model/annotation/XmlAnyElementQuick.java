package org.glassfish.jaxb.runtime.v2.model.annotation;

import jakarta.xml.bind.annotation.XmlAnyElement;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

final class XmlAnyElementQuick extends Quick implements XmlAnyElement {
   private final XmlAnyElement core;

   public XmlAnyElementQuick(Locatable upstream, XmlAnyElement core) {
      super(upstream);
      this.core = core;
   }

   protected Annotation getAnnotation() {
      return this.core;
   }

   protected Quick newInstance(Locatable upstream, Annotation core) {
      return new XmlAnyElementQuick(upstream, (XmlAnyElement)core);
   }

   public Class annotationType() {
      return XmlAnyElement.class;
   }

   public boolean lax() {
      return this.core.lax();
   }

   public Class value() {
      return this.core.value();
   }
}
