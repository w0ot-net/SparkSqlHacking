package io.jsonwebtoken.security;

import io.jsonwebtoken.io.ParserBuilder;

public interface JwkSetParserBuilder extends ParserBuilder, KeyOperationPolicied {
   JwkSetParserBuilder ignoreUnsupported(boolean var1);
}
