package org.glassfish.jaxb.runtime.v2.model.annotation;

import jakarta.xml.bind.annotation.XmlAccessOrder;
import jakarta.xml.bind.annotation.XmlAccessorOrder;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

final class XmlAccessorOrderQuick extends Quick implements XmlAccessorOrder {
   private final XmlAccessorOrder core;

   public XmlAccessorOrderQuick(Locatable upstream, XmlAccessorOrder core) {
      super(upstream);
      this.core = core;
   }

   protected Annotation getAnnotation() {
      return this.core;
   }

   protected Quick newInstance(Locatable upstream, Annotation core) {
      return new XmlAccessorOrderQuick(upstream, (XmlAccessorOrder)core);
   }

   public Class annotationType() {
      return XmlAccessorOrder.class;
   }

   public XmlAccessOrder value() {
      return this.core.value();
   }
}
