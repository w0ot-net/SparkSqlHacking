package io.vertx.ext.auth.impl;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authorization.Authorization;
import io.vertx.ext.auth.authorization.Authorizations;
import io.vertx.ext.auth.authorization.impl.AuthorizationConverter;
import java.util.Objects;

public class UserConverter {
   private static final String FIELD_PRINCIPAL = "principal";
   private static final String FIELD_AUTHORIZATIONS = "authorizations";
   private static final String FIELD_ATTRIBUTES = "attributes";

   public static JsonObject encode(User value) throws IllegalArgumentException {
      Objects.requireNonNull(value);
      JsonObject json = new JsonObject();
      json.put("principal", value.principal());
      Authorizations authorizations = value.authorizations();
      if (authorizations != null) {
         JsonObject jsonAuthorizations = new JsonObject();

         for(String providerId : authorizations.getProviderIds()) {
            JsonArray jsonAuthorizationByProvider = new JsonArray();
            jsonAuthorizations.put(providerId, jsonAuthorizationByProvider);

            for(Authorization authorization : authorizations.get(providerId)) {
               jsonAuthorizationByProvider.add(AuthorizationConverter.encode(authorization));
            }
         }

         json.put("authorizations", jsonAuthorizations);
      }

      json.put("attributes", value.attributes());
      return json;
   }

   public static User decode(JsonObject json) throws IllegalArgumentException {
      Objects.requireNonNull(json);
      JsonObject principal = json.getJsonObject("principal");
      User user = User.create(principal);
      JsonObject jsonAuthorizations = json.getJsonObject("authorizations");

      for(String fieldName : jsonAuthorizations.fieldNames()) {
         JsonArray jsonAuthorizationByProvider = jsonAuthorizations.getJsonArray(fieldName);

         for(int i = 0; i < jsonAuthorizationByProvider.size(); ++i) {
            JsonObject jsonAuthorization = jsonAuthorizationByProvider.getJsonObject(i);
            user.authorizations().add(fieldName, AuthorizationConverter.decode(jsonAuthorization));
         }
      }

      user.attributes().mergeIn(json.getJsonObject("attributes", new JsonObject()));
      return user;
   }
}
