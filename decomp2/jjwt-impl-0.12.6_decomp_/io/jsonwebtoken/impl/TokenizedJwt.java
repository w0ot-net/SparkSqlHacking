package io.jsonwebtoken.impl;

import io.jsonwebtoken.Header;
import java.util.Map;

public interface TokenizedJwt {
   CharSequence getProtected();

   CharSequence getPayload();

   CharSequence getDigest();

   Header createHeader(Map var1);
}
