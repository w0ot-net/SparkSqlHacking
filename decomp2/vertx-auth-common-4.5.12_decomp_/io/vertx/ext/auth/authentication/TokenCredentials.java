package io.vertx.ext.auth.authentication;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

@DataObject
@JsonGen(
   publicConverter = false
)
public class TokenCredentials implements Credentials {
   private String token;
   private List scopes;

   public TokenCredentials() {
   }

   public TokenCredentials(String token) {
      this.token = token;
   }

   public TokenCredentials(JsonObject jsonObject) {
      TokenCredentialsConverter.fromJson(jsonObject, this);
   }

   public String getToken() {
      return this.token;
   }

   public TokenCredentials setToken(String token) {
      this.token = token;
      return this;
   }

   public List getScopes() {
      return this.scopes;
   }

   public TokenCredentials setScopes(List scopes) {
      this.scopes = scopes;
      return this;
   }

   public TokenCredentials addScope(String scope) {
      if (this.scopes == null) {
         this.scopes = new ArrayList();
      }

      this.scopes.add(scope);
      return this;
   }

   public void checkValid(Object arg) throws CredentialValidationException {
      if (this.token == null || this.token.length() == 0) {
         throw new CredentialValidationException("token cannot be null or empty");
      }
   }

   public JsonObject toJson() {
      JsonObject result = new JsonObject();
      TokenCredentialsConverter.toJson(this, result);
      return result;
   }

   public String toString() {
      return this.toJson().encode();
   }

   public TokenCredentials applyHttpChallenge(String challenge, HttpMethod method, String uri, Integer nc, String cnonce) throws CredentialValidationException {
      if (challenge != null) {
         int spc = challenge.indexOf(32);
         if (!"Bearer".equalsIgnoreCase(challenge.substring(0, spc))) {
            throw new IllegalArgumentException("Only 'Bearer' auth-scheme is supported");
         }
      }

      this.checkValid((Object)null);
      return this;
   }

   public String toHttpAuthorization() {
      return "Bearer " + this.token;
   }
}
