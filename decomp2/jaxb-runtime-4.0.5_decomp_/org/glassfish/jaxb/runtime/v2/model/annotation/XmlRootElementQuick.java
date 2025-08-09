package org.glassfish.jaxb.runtime.v2.model.annotation;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

final class XmlRootElementQuick extends Quick implements XmlRootElement {
   private final XmlRootElement core;

   public XmlRootElementQuick(Locatable upstream, XmlRootElement core) {
      super(upstream);
      this.core = core;
   }

   protected Annotation getAnnotation() {
      return this.core;
   }

   protected Quick newInstance(Locatable upstream, Annotation core) {
      return new XmlRootElementQuick(upstream, (XmlRootElement)core);
   }

   public Class annotationType() {
      return XmlRootElement.class;
   }

   public String name() {
      return this.core.name();
   }

   public String namespace() {
      return this.core.namespace();
   }
}
