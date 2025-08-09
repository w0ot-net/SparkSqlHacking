package io.vertx.ext.auth.authorization.impl;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.authorization.AndAuthorization;
import io.vertx.ext.auth.authorization.Authorization;
import io.vertx.ext.auth.authorization.NotAuthorization;
import io.vertx.ext.auth.authorization.OrAuthorization;
import io.vertx.ext.auth.authorization.PermissionBasedAuthorization;
import io.vertx.ext.auth.authorization.RoleBasedAuthorization;
import io.vertx.ext.auth.authorization.WildcardPermissionBasedAuthorization;
import java.util.Objects;

public class AuthorizationConverter {
   public static final String FIELD_TYPE = "type";
   public static final String FIELD_AUTHORIZATIONS = "authorizations";

   public static Authorization decode(JsonObject json) throws IllegalArgumentException {
      Objects.requireNonNull(json);
      switch ((String)Objects.requireNonNull(json.getString("type"))) {
         case "and":
            return AndAuthorizationConverter.decode(json);
         case "not":
            return NotAuthorizationConverter.decode(json);
         case "or":
            return OrAuthorizationConverter.decode(json);
         case "permission":
            return PermissionBasedAuthorizationConverter.decode(json);
         case "role":
            return RoleBasedAuthorizationConverter.decode(json);
         case "wildcard":
            return WildcardPermissionBasedAuthorizationConverter.decode(json);
         default:
            throw new IllegalArgumentException("Unsupported authorization " + json.getString("type"));
      }
   }

   public static JsonObject encode(Authorization value) throws IllegalArgumentException {
      Objects.requireNonNull(value);
      if (value instanceof AndAuthorization) {
         return AndAuthorizationConverter.encode((AndAuthorization)value);
      } else if (value instanceof NotAuthorization) {
         return NotAuthorizationConverter.encode((NotAuthorization)value);
      } else if (value instanceof OrAuthorization) {
         return OrAuthorizationConverter.encode((OrAuthorization)value);
      } else if (value instanceof PermissionBasedAuthorization) {
         return PermissionBasedAuthorizationConverter.encode((PermissionBasedAuthorization)value);
      } else if (value instanceof RoleBasedAuthorization) {
         return RoleBasedAuthorizationConverter.encode((RoleBasedAuthorization)value);
      } else if (value instanceof WildcardPermissionBasedAuthorization) {
         return WildcardPermissionBasedAuthorizationConverter.encode((WildcardPermissionBasedAuthorization)value);
      } else {
         throw new IllegalArgumentException("Unsupported authorization " + value.getClass());
      }
   }
}
