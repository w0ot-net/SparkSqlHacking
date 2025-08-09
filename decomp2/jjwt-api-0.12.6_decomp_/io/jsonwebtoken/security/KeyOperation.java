package io.jsonwebtoken.security;

import io.jsonwebtoken.Identifiable;

public interface KeyOperation extends Identifiable {
   String getDescription();

   boolean isRelated(KeyOperation var1);
}
