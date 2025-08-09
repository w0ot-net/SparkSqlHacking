package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.Identifiable;

public interface Parameter extends Identifiable, Converter {
   String getName();

   boolean supports(Object var1);

   Object cast(Object var1);

   boolean isSecret();
}
