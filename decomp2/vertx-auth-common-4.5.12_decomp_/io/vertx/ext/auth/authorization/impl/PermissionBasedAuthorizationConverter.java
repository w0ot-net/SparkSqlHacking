package io.vertx.ext.auth.authorization.impl;

import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.authorization.PermissionBasedAuthorization;
import java.util.Objects;

public class PermissionBasedAuthorizationConverter {
   public static final String TYPE = "permission";
   private static final String FIELD_PERMISSION = "permission";
   private static final String FIELD_RESOURCE = "resource";

   public static JsonObject encode(PermissionBasedAuthorization value) throws IllegalArgumentException {
      Objects.requireNonNull(value);
      JsonObject result = new JsonObject();
      result.put("type", "permission");
      result.put("permission", value.getPermission());
      if (value.getResource() != null) {
         result.put("resource", value.getResource());
      }

      return result;
   }

   public static @Nullable PermissionBasedAuthorization decode(JsonObject json) throws IllegalArgumentException {
      Objects.requireNonNull(json);
      PermissionBasedAuthorization result = PermissionBasedAuthorization.create(json.getString("permission"));
      if (json.getString("resource") != null) {
         result.setResource(json.getString("resource"));
      }

      return result;
   }
}
