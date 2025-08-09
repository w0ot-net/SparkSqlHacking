package org.glassfish.jaxb.runtime.v2.model.impl;

import javax.xml.namespace.QName;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;
import org.glassfish.jaxb.core.v2.model.core.LeafInfo;
import org.glassfish.jaxb.core.v2.runtime.Location;

abstract class LeafInfoImpl implements LeafInfo, Location {
   private final Object type;
   private final QName typeName;

   protected LeafInfoImpl(Object type, QName typeName) {
      assert type != null;

      this.type = type;
      this.typeName = typeName;
   }

   public Object getType() {
      return this.type;
   }

   /** @deprecated */
   @Deprecated
   public final boolean canBeReferencedByIDREF() {
      return false;
   }

   public QName getTypeName() {
      return this.typeName;
   }

   public Locatable getUpstream() {
      return null;
   }

   public Location getLocation() {
      return this;
   }

   public boolean isSimpleType() {
      return true;
   }

   public String toString() {
      return this.type.toString();
   }
}
