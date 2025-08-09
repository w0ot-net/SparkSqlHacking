package org.sparkproject.jetty.client;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.sparkproject.jetty.client.api.Authentication;
import org.sparkproject.jetty.client.api.AuthenticationStore;
import org.sparkproject.jetty.client.util.AbstractAuthentication;

public class HttpAuthenticationStore implements AuthenticationStore {
   private final List authentications = new CopyOnWriteArrayList();
   private final Map results = new ConcurrentHashMap();

   public void addAuthentication(Authentication authentication) {
      this.authentications.add(authentication);
   }

   public void removeAuthentication(Authentication authentication) {
      this.authentications.remove(authentication);
   }

   public void clearAuthentications() {
      this.authentications.clear();
   }

   public Authentication findAuthentication(String type, URI uri, String realm) {
      for(Authentication authentication : this.authentications) {
         if (authentication.matches(type, uri, realm)) {
            return authentication;
         }
      }

      return null;
   }

   public void addAuthenticationResult(Authentication.Result result) {
      URI uri = result.getURI();
      if (uri != null) {
         this.results.put(uri, result);
      }

   }

   public void removeAuthenticationResult(Authentication.Result result) {
      this.results.remove(result.getURI());
   }

   public void clearAuthenticationResults() {
      this.results.clear();
   }

   public Authentication.Result findAuthenticationResult(URI uri) {
      for(Map.Entry entry : this.results.entrySet()) {
         if (AbstractAuthentication.matchesURI((URI)entry.getKey(), uri)) {
            return (Authentication.Result)entry.getValue();
         }
      }

      return null;
   }

   public boolean hasAuthenticationResults() {
      return !this.results.isEmpty();
   }
}
