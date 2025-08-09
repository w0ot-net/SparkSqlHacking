package org.glassfish.jaxb.runtime.v2.model.annotation;

import jakarta.xml.bind.annotation.XmlElementWrapper;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

final class XmlElementWrapperQuick extends Quick implements XmlElementWrapper {
   private final XmlElementWrapper core;

   public XmlElementWrapperQuick(Locatable upstream, XmlElementWrapper core) {
      super(upstream);
      this.core = core;
   }

   protected Annotation getAnnotation() {
      return this.core;
   }

   protected Quick newInstance(Locatable upstream, Annotation core) {
      return new XmlElementWrapperQuick(upstream, (XmlElementWrapper)core);
   }

   public Class annotationType() {
      return XmlElementWrapper.class;
   }

   public String name() {
      return this.core.name();
   }

   public String namespace() {
      return this.core.namespace();
   }

   public boolean nillable() {
      return this.core.nillable();
   }

   public boolean required() {
      return this.core.required();
   }
}
