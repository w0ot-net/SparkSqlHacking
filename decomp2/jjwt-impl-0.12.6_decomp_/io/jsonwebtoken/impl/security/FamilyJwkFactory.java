package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.Identifiable;
import java.security.Key;

public interface FamilyJwkFactory extends JwkFactory, Identifiable {
   boolean supports(Key var1);

   boolean supports(JwkContext var1);
}
