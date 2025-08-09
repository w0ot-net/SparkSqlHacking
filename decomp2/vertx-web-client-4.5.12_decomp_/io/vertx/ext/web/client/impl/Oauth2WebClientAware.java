package io.vertx.ext.web.client.impl;

import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authentication.Credentials;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.web.client.OAuth2WebClient;
import io.vertx.ext.web.client.OAuth2WebClientOptions;
import io.vertx.ext.web.client.WebClient;

public class Oauth2WebClientAware extends WebClientBase implements OAuth2WebClient {
   private final OAuth2Auth oauth2Auth;
   private final OAuth2WebClientOptions option;
   private Credentials credentials;
   private User user;

   public Oauth2WebClientAware(WebClient client, OAuth2Auth oauth2Auth, OAuth2WebClientOptions options) {
      super((WebClientBase)client);
      if (oauth2Auth == null) {
         throw new IllegalArgumentException("OAuth2Auth cannot be null");
      } else {
         this.oauth2Auth = oauth2Auth;
         if (options == null) {
            throw new IllegalArgumentException("Options cannot be null");
         } else {
            this.option = options;
            this.addInterceptor(new OAuth2AwareInterceptor(this));
         }
      }
   }

   public OAuth2WebClient withCredentials(Credentials credentials) {
      if (credentials == null) {
         throw new NullPointerException("Token Configuration passed to WebClientOauth2Aware can not be null");
      } else {
         if (this.credentials != null && !this.credentials.equals(credentials)) {
            this.user = null;
         }

         this.credentials = credentials;
         return this;
      }
   }

   Credentials getCredentials() {
      return this.credentials;
   }

   public User getUser() {
      return this.user;
   }

   void setUser(User user) {
      this.user = user;
   }

   OAuth2Auth oauth2Auth() {
      return this.oauth2Auth;
   }

   public int getLeeway() {
      return this.option.getLeeway();
   }

   public boolean isRenewTokenOnForbidden() {
      return this.option.isRenewTokenOnForbidden();
   }
}
