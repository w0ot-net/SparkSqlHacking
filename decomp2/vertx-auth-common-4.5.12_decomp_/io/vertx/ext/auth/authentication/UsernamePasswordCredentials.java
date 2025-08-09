package io.vertx.ext.auth.authentication;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.impl.Codec;
import java.nio.charset.StandardCharsets;

@DataObject
@JsonGen(
   publicConverter = false
)
public class UsernamePasswordCredentials implements Credentials {
   private String password;
   private String username;

   protected UsernamePasswordCredentials() {
   }

   public UsernamePasswordCredentials(String username, String password) {
      this.setUsername(username);
      this.setPassword(password);
   }

   public UsernamePasswordCredentials(JsonObject jsonObject) {
      UsernamePasswordCredentialsConverter.fromJson(jsonObject, this);
   }

   public String getPassword() {
      return this.password;
   }

   public String getUsername() {
      return this.username;
   }

   public UsernamePasswordCredentials setPassword(String password) {
      this.password = password;
      return this;
   }

   public UsernamePasswordCredentials setUsername(String username) {
      this.username = username;
      return this;
   }

   public void checkValid(Object arg) throws CredentialValidationException {
      if (this.username == null) {
         throw new CredentialValidationException("username cannot be null");
      } else if (this.password == null) {
         throw new CredentialValidationException("password cannot be null");
      }
   }

   public JsonObject toJson() {
      JsonObject result = new JsonObject();
      UsernamePasswordCredentialsConverter.toJson(this, result);
      return result;
   }

   public String toString() {
      return this.toJson().encode();
   }

   public UsernamePasswordCredentials applyHttpChallenge(String challenge, HttpMethod method, String uri, Integer nc, String cnonce) throws CredentialValidationException {
      if (challenge != null) {
         int spc = challenge.indexOf(32);
         if (!"Basic".equalsIgnoreCase(challenge.substring(0, spc))) {
            throw new IllegalArgumentException("Only 'Basic' auth-scheme is supported");
         }
      }

      this.checkValid((Object)null);
      return this;
   }

   public String toHttpAuthorization() {
      StringBuilder sb = new StringBuilder();
      if (this.username != null) {
         if (this.username.indexOf(58) != -1) {
            throw new IllegalArgumentException("Username cannot contain ':'");
         }

         sb.append(this.username);
      }

      sb.append(':');
      if (this.password != null) {
         sb.append(this.password);
      }

      return "Basic " + Codec.base64Encode(sb.toString().getBytes(StandardCharsets.UTF_8));
   }
}
