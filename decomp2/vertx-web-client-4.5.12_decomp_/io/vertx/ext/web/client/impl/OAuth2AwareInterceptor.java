package io.vertx.ext.web.client.impl;

import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpHeaders;
import io.vertx.ext.auth.User;
import java.util.HashSet;
import java.util.Set;

public class OAuth2AwareInterceptor implements Handler {
   private final Set dejaVu = new HashSet();
   private final Oauth2WebClientAware parentClient;

   public OAuth2AwareInterceptor(Oauth2WebClientAware webClientOauth2Aware) {
      this.parentClient = webClientOauth2Aware;
   }

   public void handle(HttpContext context) {
      switch (context.phase()) {
         case CREATE_REQUEST:
            this.createRequest(context).onFailure(context::fail).onSuccess((done) -> context.next());
            break;
         case DISPATCH_RESPONSE:
            this.processResponse(context);
            break;
         default:
            context.next();
      }

   }

   private void processResponse(HttpContext context) {
      switch (context.response().statusCode()) {
         case 401:
            if (this.parentClient.isRenewTokenOnForbidden() && !this.dejaVu.contains(context)) {
               this.dejaVu.add(context);
               this.parentClient.oauth2Auth().authenticate(this.parentClient.getCredentials()).onSuccess((userResult) -> {
                  this.parentClient.setUser(userResult);
                  context.createRequest(context.requestOptions());
               }).onFailure((err) -> {
                  this.dejaVu.remove(context);
                  this.parentClient.setUser((User)null);
                  context.fail(err);
               });
            } else {
               this.dejaVu.remove(context);
               context.next();
            }
            break;
         default:
            this.dejaVu.remove(context);
            context.next();
      }

   }

   private Future createRequest(HttpContext context) {
      Promise<Void> promise = Promise.promise();
      if (this.parentClient.getCredentials() != null) {
         if (this.parentClient.getUser() != null) {
            if (this.parentClient.getUser().expired(this.parentClient.getLeeway())) {
               this.parentClient.oauth2Auth().refresh(this.parentClient.getUser()).onSuccess((userResult) -> {
                  this.parentClient.setUser(userResult);
                  context.requestOptions().putHeader(HttpHeaders.AUTHORIZATION, "Bearer " + userResult.principal().getString("access_token"));
                  promise.complete();
               }).onFailure((error) -> this.parentClient.oauth2Auth().authenticate(this.parentClient.getCredentials()).onSuccess((userResult) -> {
                     this.parentClient.setUser(userResult);
                     context.requestOptions().putHeader(HttpHeaders.AUTHORIZATION, "Bearer " + userResult.principal().getString("access_token"));
                     promise.complete();
                  }).onFailure((errorAuth) -> {
                     this.parentClient.setUser((User)null);
                     promise.fail(errorAuth);
                  }));
            } else {
               context.requestOptions().putHeader(HttpHeaders.AUTHORIZATION, "Bearer " + this.parentClient.getUser().principal().getString("access_token"));
               promise.complete();
            }
         } else {
            this.parentClient.oauth2Auth().authenticate(this.parentClient.getCredentials()).onSuccess((userResult) -> {
               this.parentClient.setUser(userResult);
               context.requestOptions().putHeader(HttpHeaders.AUTHORIZATION, "Bearer " + userResult.principal().getString("access_token"));
               promise.complete();
            }).onFailure(promise::fail);
         }
      } else {
         promise.fail("Missing client credentials");
      }

      return promise.future();
   }
}
