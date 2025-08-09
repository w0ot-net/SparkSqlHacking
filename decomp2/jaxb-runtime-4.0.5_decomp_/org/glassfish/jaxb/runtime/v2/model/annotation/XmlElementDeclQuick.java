package org.glassfish.jaxb.runtime.v2.model.annotation;

import jakarta.xml.bind.annotation.XmlElementDecl;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

final class XmlElementDeclQuick extends Quick implements XmlElementDecl {
   private final XmlElementDecl core;

   public XmlElementDeclQuick(Locatable upstream, XmlElementDecl core) {
      super(upstream);
      this.core = core;
   }

   protected Annotation getAnnotation() {
      return this.core;
   }

   protected Quick newInstance(Locatable upstream, Annotation core) {
      return new XmlElementDeclQuick(upstream, (XmlElementDecl)core);
   }

   public Class annotationType() {
      return XmlElementDecl.class;
   }

   public String defaultValue() {
      return this.core.defaultValue();
   }

   public String name() {
      return this.core.name();
   }

   public String namespace() {
      return this.core.namespace();
   }

   public Class scope() {
      return this.core.scope();
   }

   public String substitutionHeadName() {
      return this.core.substitutionHeadName();
   }

   public String substitutionHeadNamespace() {
      return this.core.substitutionHeadNamespace();
   }
}
