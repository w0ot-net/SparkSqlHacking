package io.vertx.ext.auth.authorization.impl;

import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.authorization.Authorization;
import io.vertx.ext.auth.authorization.OrAuthorization;
import java.util.Objects;

public class OrAuthorizationConverter {
   public static final String TYPE = "or";

   public static JsonObject encode(OrAuthorization value) throws IllegalArgumentException {
      Objects.requireNonNull(value);
      JsonObject result = new JsonObject();
      result.put("type", "or");
      JsonArray authorizations = new JsonArray();
      result.put("authorizations", authorizations);

      for(Authorization authorization : value.getAuthorizations()) {
         authorizations.add(AuthorizationConverter.encode(authorization));
      }

      return result;
   }

   public static @Nullable OrAuthorization decode(JsonObject json) throws IllegalArgumentException {
      Objects.requireNonNull(json);
      OrAuthorization result = OrAuthorization.create();
      JsonArray authorizations = json.getJsonArray("authorizations");

      for(int i = 0; i < authorizations.size(); ++i) {
         JsonObject authorization = authorizations.getJsonObject(i);
         result.addAuthorization(AuthorizationConverter.decode(authorization));
      }

      return result;
   }
}
