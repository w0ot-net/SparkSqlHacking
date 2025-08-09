package org.sparkproject.jetty.security;

import org.sparkproject.jetty.server.UserIdentity;

public class UserAuthentication extends AbstractUserAuthentication {
   public UserAuthentication(String method, UserIdentity userIdentity) {
      super(method, userIdentity);
   }

   public String toString() {
      String var10000 = this.getAuthMethod();
      return "{User," + var10000 + "," + String.valueOf(this._userIdentity) + "}";
   }
}
