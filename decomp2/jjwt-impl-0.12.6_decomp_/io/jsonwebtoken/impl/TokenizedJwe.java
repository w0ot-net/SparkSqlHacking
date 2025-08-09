package io.jsonwebtoken.impl;

public interface TokenizedJwe extends TokenizedJwt {
   CharSequence getEncryptedKey();

   CharSequence getIv();
}
