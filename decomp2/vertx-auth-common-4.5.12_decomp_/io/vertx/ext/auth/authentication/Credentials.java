package io.vertx.ext.auth.authentication;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

public interface Credentials {
   default void checkValid(Object arg) throws CredentialValidationException {
   }

   JsonObject toJson();

   default Credentials applyHttpChallenge(String challenge, HttpMethod method, String uri, Integer nc, String cnonce) throws CredentialValidationException {
      if (challenge != null) {
         throw new CredentialValidationException("This implementation can't handle HTTP Authentication");
      } else {
         return this;
      }
   }

   default Credentials applyHttpChallenge(String challenge, HttpMethod method, String uri) throws CredentialValidationException {
      return this.applyHttpChallenge(challenge, method, uri, (Integer)null, (String)null);
   }

   default Credentials applyHttpChallenge(String challenge) throws CredentialValidationException {
      return this.applyHttpChallenge(challenge, (HttpMethod)null, (String)null, (Integer)null, (String)null);
   }

   default String toHttpAuthorization() {
      throw new UnsupportedOperationException(this.getClass().getName() + " cannot be converted to a HTTP Authorization");
   }
}
