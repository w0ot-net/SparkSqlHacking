package org.sparkproject.jetty.util.security;

public interface CredentialProvider {
   Credential getCredential(String var1);

   String getPrefix();
}
