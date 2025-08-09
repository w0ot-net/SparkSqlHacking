package io.vertx.ext.web.client;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authentication.Credentials;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.web.client.impl.Oauth2WebClientAware;

@VertxGen
public interface OAuth2WebClient extends WebClient {
   static OAuth2WebClient create(WebClient webClient, OAuth2Auth oAuth2Auth) {
      return create(webClient, oAuth2Auth, new OAuth2WebClientOptions());
   }

   static OAuth2WebClient create(WebClient webClient, OAuth2Auth oAuth2Auth, OAuth2WebClientOptions options) {
      return new Oauth2WebClientAware(webClient, oAuth2Auth, options);
   }

   @Fluent
   @GenIgnore({"permitted-type"})
   OAuth2WebClient withCredentials(Credentials var1);

   User getUser();
}
