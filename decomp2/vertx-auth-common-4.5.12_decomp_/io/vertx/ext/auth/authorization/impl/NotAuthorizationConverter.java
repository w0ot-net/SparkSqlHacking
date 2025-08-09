package io.vertx.ext.auth.authorization.impl;

import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.authorization.NotAuthorization;
import java.util.Objects;

public class NotAuthorizationConverter {
   public static final String TYPE = "not";

   public static JsonObject encode(NotAuthorization value) throws IllegalArgumentException {
      Objects.requireNonNull(value);
      JsonObject result = new JsonObject();
      result.put("type", "not");
      result.put("authorizations", AuthorizationConverter.encode(value.getAuthorization()));
      return result;
   }

   public static @Nullable NotAuthorization decode(JsonObject json) throws IllegalArgumentException {
      Objects.requireNonNull(json);
      return NotAuthorization.create(AuthorizationConverter.decode(json.getJsonObject("authorizations")));
   }
}
