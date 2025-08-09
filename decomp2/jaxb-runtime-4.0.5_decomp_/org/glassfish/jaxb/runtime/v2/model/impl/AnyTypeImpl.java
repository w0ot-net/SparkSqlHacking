package org.glassfish.jaxb.runtime.v2.model.impl;

import javax.xml.namespace.QName;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;
import org.glassfish.jaxb.core.v2.model.core.NonElement;
import org.glassfish.jaxb.core.v2.model.nav.Navigator;
import org.glassfish.jaxb.core.v2.runtime.Location;

class AnyTypeImpl implements NonElement {
   private final Object type;
   private final Navigator nav;

   public AnyTypeImpl(Navigator nav) {
      this.type = nav.ref(Object.class);
      this.nav = nav;
   }

   public QName getTypeName() {
      return ANYTYPE_NAME;
   }

   public Object getType() {
      return this.type;
   }

   public Locatable getUpstream() {
      return null;
   }

   public boolean isSimpleType() {
      return false;
   }

   public Location getLocation() {
      return this.nav.getClassLocation(this.nav.asDecl(Object.class));
   }

   /** @deprecated */
   @Deprecated
   public final boolean canBeReferencedByIDREF() {
      return true;
   }
}
