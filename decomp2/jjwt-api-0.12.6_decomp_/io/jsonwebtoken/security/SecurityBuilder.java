package io.jsonwebtoken.security;

import io.jsonwebtoken.lang.Builder;
import java.security.Provider;
import java.security.SecureRandom;

public interface SecurityBuilder extends Builder {
   SecurityBuilder provider(Provider var1);

   SecurityBuilder random(SecureRandom var1);
}
