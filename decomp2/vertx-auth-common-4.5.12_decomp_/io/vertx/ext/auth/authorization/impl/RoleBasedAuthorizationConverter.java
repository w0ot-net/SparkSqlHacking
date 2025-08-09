package io.vertx.ext.auth.authorization.impl;

import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.authorization.RoleBasedAuthorization;
import java.util.Objects;

public class RoleBasedAuthorizationConverter {
   public static final String TYPE = "role";
   private static final String FIELD_ROLE = "role";
   private static final String FIELD_RESOURCE = "resource";

   public static JsonObject encode(RoleBasedAuthorization value) throws IllegalArgumentException {
      Objects.requireNonNull(value);
      JsonObject result = new JsonObject();
      result.put("type", "role");
      result.put("role", value.getRole());
      if (value.getResource() != null) {
         result.put("resource", value.getResource());
      }

      return result;
   }

   public static @Nullable RoleBasedAuthorization decode(JsonObject json) throws IllegalArgumentException {
      Objects.requireNonNull(json);
      RoleBasedAuthorization result = RoleBasedAuthorization.create(json.getString("role"));
      if (json.getString("resource") != null) {
         result.setResource(json.getString("resource"));
      }

      return result;
   }
}
