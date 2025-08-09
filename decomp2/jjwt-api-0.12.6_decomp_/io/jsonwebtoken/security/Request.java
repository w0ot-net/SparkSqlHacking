package io.jsonwebtoken.security;

import java.security.Provider;
import java.security.SecureRandom;

public interface Request extends Message {
   Provider getProvider();

   SecureRandom getSecureRandom();
}
