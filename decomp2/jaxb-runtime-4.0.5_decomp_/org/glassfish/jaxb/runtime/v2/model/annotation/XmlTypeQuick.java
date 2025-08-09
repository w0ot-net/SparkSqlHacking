package org.glassfish.jaxb.runtime.v2.model.annotation;

import jakarta.xml.bind.annotation.XmlType;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

final class XmlTypeQuick extends Quick implements XmlType {
   private final XmlType core;

   public XmlTypeQuick(Locatable upstream, XmlType core) {
      super(upstream);
      this.core = core;
   }

   protected Annotation getAnnotation() {
      return this.core;
   }

   protected Quick newInstance(Locatable upstream, Annotation core) {
      return new XmlTypeQuick(upstream, (XmlType)core);
   }

   public Class annotationType() {
      return XmlType.class;
   }

   public Class factoryClass() {
      return this.core.factoryClass();
   }

   public String factoryMethod() {
      return this.core.factoryMethod();
   }

   public String name() {
      return this.core.name();
   }

   public String namespace() {
      return this.core.namespace();
   }

   public String[] propOrder() {
      return this.core.propOrder();
   }
}
