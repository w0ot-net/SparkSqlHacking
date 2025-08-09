package org.glassfish.jaxb.runtime.v2.model.annotation;

import jakarta.xml.bind.annotation.XmlElementRef;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

final class XmlElementRefQuick extends Quick implements XmlElementRef {
   private final XmlElementRef core;

   public XmlElementRefQuick(Locatable upstream, XmlElementRef core) {
      super(upstream);
      this.core = core;
   }

   protected Annotation getAnnotation() {
      return this.core;
   }

   protected Quick newInstance(Locatable upstream, Annotation core) {
      return new XmlElementRefQuick(upstream, (XmlElementRef)core);
   }

   public Class annotationType() {
      return XmlElementRef.class;
   }

   public String name() {
      return this.core.name();
   }

   public String namespace() {
      return this.core.namespace();
   }

   public boolean required() {
      return this.core.required();
   }

   public Class type() {
      return this.core.type();
   }
}
