package io.jsonwebtoken.security;

import io.jsonwebtoken.lang.Builder;

public interface KeyOperationBuilder extends Builder {
   KeyOperationBuilder id(String var1);

   KeyOperationBuilder description(String var1);

   KeyOperationBuilder related(String var1);
}
