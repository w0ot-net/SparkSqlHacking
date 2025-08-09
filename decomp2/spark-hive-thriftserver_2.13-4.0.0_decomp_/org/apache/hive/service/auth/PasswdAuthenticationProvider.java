package org.apache.hive.service.auth;

import javax.security.sasl.AuthenticationException;

public interface PasswdAuthenticationProvider {
   void Authenticate(String var1, String var2) throws AuthenticationException;
}
