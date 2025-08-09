package org.glassfish.jaxb.runtime.v2.schemagen;

import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.ContentModelContainer;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.Particle;

enum GroupKind {
   ALL("all"),
   SEQUENCE("sequence"),
   CHOICE("choice");

   private final String name;

   private GroupKind(String name) {
      this.name = name;
   }

   Particle write(ContentModelContainer parent) {
      return (Particle)parent._element(this.name, Particle.class);
   }
}
