package org.glassfish.jaxb.runtime.v2.model.annotation;

import org.glassfish.jaxb.core.v2.model.annotation.Locatable;
import org.glassfish.jaxb.core.v2.model.nav.Navigator;
import org.glassfish.jaxb.core.v2.runtime.Location;

public class FieldLocatable implements Locatable {
   private final Locatable upstream;
   private final Object field;
   private final Navigator nav;

   public FieldLocatable(Locatable upstream, Object field, Navigator nav) {
      this.upstream = upstream;
      this.field = field;
      this.nav = nav;
   }

   public Locatable getUpstream() {
      return this.upstream;
   }

   public Location getLocation() {
      return this.nav.getFieldLocation(this.field);
   }
}
