package org.apache.zookeeper.server.auth;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.zookeeper.KeeperException;

class WrappedAuthenticationProvider extends ServerAuthenticationProvider {
   private final AuthenticationProvider implementation;

   static ServerAuthenticationProvider wrap(AuthenticationProvider provider) {
      if (provider == null) {
         return null;
      } else {
         return (ServerAuthenticationProvider)(provider instanceof ServerAuthenticationProvider ? (ServerAuthenticationProvider)provider : new WrappedAuthenticationProvider(provider));
      }
   }

   private WrappedAuthenticationProvider(AuthenticationProvider implementation) {
      this.implementation = implementation;
   }

   public KeeperException.Code handleAuthentication(ServerAuthenticationProvider.ServerObjs serverObjs, byte[] authData) {
      return this.implementation.handleAuthentication(serverObjs.getCnxn(), authData);
   }

   public List handleAuthentication(HttpServletRequest request, byte[] authData) {
      return this.implementation.handleAuthentication(request, authData);
   }

   public boolean matches(ServerAuthenticationProvider.ServerObjs serverObjs, ServerAuthenticationProvider.MatchValues matchValues) {
      return this.implementation.matches(matchValues.getId(), matchValues.getAclExpr());
   }

   public String getScheme() {
      return this.implementation.getScheme();
   }

   public boolean isAuthenticated() {
      return this.implementation.isAuthenticated();
   }

   public boolean isValid(String id) {
      return this.implementation.isValid(id);
   }
}
