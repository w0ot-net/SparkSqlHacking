package org.glassfish.jaxb.runtime.v2.model.annotation;

import org.glassfish.jaxb.core.v2.model.annotation.Locatable;
import org.glassfish.jaxb.core.v2.model.nav.Navigator;
import org.glassfish.jaxb.core.v2.runtime.Location;

public class ClassLocatable implements Locatable {
   private final Locatable upstream;
   private final Object clazz;
   private final Navigator nav;

   public ClassLocatable(Locatable upstream, Object clazz, Navigator nav) {
      this.upstream = upstream;
      this.clazz = clazz;
      this.nav = nav;
   }

   public Locatable getUpstream() {
      return this.upstream;
   }

   public Location getLocation() {
      return this.nav.getClassLocation(this.clazz);
   }
}
