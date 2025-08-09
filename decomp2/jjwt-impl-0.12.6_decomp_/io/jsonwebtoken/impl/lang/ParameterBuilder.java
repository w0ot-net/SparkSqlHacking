package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.lang.Builder;

public interface ParameterBuilder extends Builder {
   ParameterBuilder setId(String var1);

   ParameterBuilder setName(String var1);

   ParameterBuilder setSecret(boolean var1);

   ParameterBuilder list();

   ParameterBuilder set();

   ParameterBuilder setConverter(Converter var1);
}
