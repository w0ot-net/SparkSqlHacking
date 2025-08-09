package org.apache.hive.service.auth;

import javax.security.sasl.AuthenticationException;

public final class AuthenticationProviderFactory {
   private AuthenticationProviderFactory() {
   }

   public static PasswdAuthenticationProvider getAuthenticationProvider(AuthMethods authMethod) throws AuthenticationException {
      if (authMethod == AuthenticationProviderFactory.AuthMethods.LDAP) {
         return new LdapAuthenticationProviderImpl();
      } else if (authMethod == AuthenticationProviderFactory.AuthMethods.PAM) {
         return new PamAuthenticationProviderImpl();
      } else if (authMethod == AuthenticationProviderFactory.AuthMethods.CUSTOM) {
         return new CustomAuthenticationProviderImpl();
      } else if (authMethod == AuthenticationProviderFactory.AuthMethods.NONE) {
         return new AnonymousAuthenticationProviderImpl();
      } else {
         throw new AuthenticationException("Unsupported authentication method");
      }
   }

   public static enum AuthMethods {
      LDAP("LDAP"),
      PAM("PAM"),
      CUSTOM("CUSTOM"),
      NONE("NONE");

      private final String authMethod;

      private AuthMethods(String authMethod) {
         this.authMethod = authMethod;
      }

      public String getAuthMethod() {
         return this.authMethod;
      }

      public static AuthMethods getValidAuthMethod(String authMethodStr) throws AuthenticationException {
         for(AuthMethods auth : values()) {
            if (authMethodStr.equals(auth.getAuthMethod())) {
               return auth;
            }
         }

         throw new AuthenticationException("Not a valid authentication method");
      }

      // $FF: synthetic method
      private static AuthMethods[] $values() {
         return new AuthMethods[]{LDAP, PAM, CUSTOM, NONE};
      }
   }
}
