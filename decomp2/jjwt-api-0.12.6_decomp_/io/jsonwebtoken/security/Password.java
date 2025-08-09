package io.jsonwebtoken.security;

import javax.crypto.SecretKey;
import javax.security.auth.Destroyable;

public interface Password extends SecretKey, Destroyable {
   char[] toCharArray();
}
