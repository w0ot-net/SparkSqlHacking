package org.apache.hive.service.auth;

import javax.security.sasl.AuthenticationException;

public class AnonymousAuthenticationProviderImpl implements PasswdAuthenticationProvider {
   public void Authenticate(String user, String password) throws AuthenticationException {
   }
}
