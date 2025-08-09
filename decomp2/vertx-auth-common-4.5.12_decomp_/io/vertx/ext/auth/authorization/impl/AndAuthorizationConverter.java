package io.vertx.ext.auth.authorization.impl;

import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.authorization.AndAuthorization;
import io.vertx.ext.auth.authorization.Authorization;
import java.util.Objects;

public class AndAuthorizationConverter {
   public static final String TYPE = "and";

   public static JsonObject encode(AndAuthorization value) throws IllegalArgumentException {
      Objects.requireNonNull(value);
      JsonObject result = new JsonObject();
      result.put("type", "and");
      JsonArray authorizations = new JsonArray();
      result.put("authorizations", authorizations);

      for(Authorization authorization : value.getAuthorizations()) {
         authorizations.add(AuthorizationConverter.encode(authorization));
      }

      return result;
   }

   public static @Nullable AndAuthorization decode(JsonObject json) throws IllegalArgumentException {
      Objects.requireNonNull(json);
      AndAuthorization result = AndAuthorization.create();
      JsonArray authorizations = json.getJsonArray("authorizations");

      for(int i = 0; i < authorizations.size(); ++i) {
         JsonObject authorization = authorizations.getJsonObject(i);
         result.addAuthorization(AuthorizationConverter.decode(authorization));
      }

      return result;
   }
}
