package org.apache.spark.deploy.yarn;

import java.security.Principal;

public class AmIpPrincipal implements Principal {
   private final String name;

   public AmIpPrincipal(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }
}
