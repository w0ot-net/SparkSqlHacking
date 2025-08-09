package org.glassfish.jaxb.runtime.v2.model.impl;

import org.glassfish.jaxb.core.v2.model.core.EnumConstant;
import org.glassfish.jaxb.core.v2.model.core.EnumLeafInfo;

class EnumConstantImpl implements EnumConstant {
   protected final String lexical;
   protected final EnumLeafInfoImpl owner;
   protected final String name;
   protected final EnumConstantImpl next;

   public EnumConstantImpl(EnumLeafInfoImpl owner, String name, String lexical, EnumConstantImpl next) {
      this.lexical = lexical;
      this.owner = owner;
      this.name = name;
      this.next = next;
   }

   public EnumLeafInfo getEnclosingClass() {
      return this.owner;
   }

   public final String getLexicalValue() {
      return this.lexical;
   }

   public final String getName() {
      return this.name;
   }
}
