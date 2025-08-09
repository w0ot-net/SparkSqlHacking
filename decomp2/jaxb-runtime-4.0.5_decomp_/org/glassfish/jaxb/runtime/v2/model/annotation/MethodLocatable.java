package org.glassfish.jaxb.runtime.v2.model.annotation;

import org.glassfish.jaxb.core.v2.model.annotation.Locatable;
import org.glassfish.jaxb.core.v2.model.nav.Navigator;
import org.glassfish.jaxb.core.v2.runtime.Location;

public class MethodLocatable implements Locatable {
   private final Locatable upstream;
   private final Object method;
   private final Navigator nav;

   public MethodLocatable(Locatable upstream, Object method, Navigator nav) {
      this.upstream = upstream;
      this.method = method;
      this.nav = nav;
   }

   public Locatable getUpstream() {
      return this.upstream;
   }

   public Location getLocation() {
      return this.nav.getMethodLocation(this.method);
   }
}
