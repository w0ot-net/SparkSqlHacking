package org.glassfish.jaxb.runtime.v2.model.annotation;

import jakarta.xml.bind.annotation.XmlNs;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

final class XmlNsQuick extends Quick implements XmlNs {
   private final XmlNs core;

   public XmlNsQuick(Locatable upstream, XmlNs core) {
      super(upstream);
      this.core = core;
   }

   protected Annotation getAnnotation() {
      return this.core;
   }

   protected Quick newInstance(Locatable upstream, Annotation core) {
      return new XmlNsQuick(upstream, (XmlNs)core);
   }

   public Class annotationType() {
      return XmlNs.class;
   }

   public String namespaceURI() {
      return this.core.namespaceURI();
   }

   public String prefix() {
      return this.core.prefix();
   }
}
