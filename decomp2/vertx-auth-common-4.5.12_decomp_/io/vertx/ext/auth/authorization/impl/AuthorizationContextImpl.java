package io.vertx.ext.auth.authorization.impl;

import io.vertx.core.MultiMap;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authorization.AuthorizationContext;
import java.util.Objects;

public class AuthorizationContextImpl implements AuthorizationContext {
   private final User user;
   private final MultiMap variables;

   public AuthorizationContextImpl(User user) {
      this(user, MultiMap.caseInsensitiveMultiMap());
   }

   public AuthorizationContextImpl(User user, MultiMap variables) {
      this.user = (User)Objects.requireNonNull(user);
      this.variables = (MultiMap)Objects.requireNonNull(variables);
   }

   public User user() {
      return this.user;
   }

   public MultiMap variables() {
      return this.variables;
   }
}
