package io.jsonwebtoken.security;

import java.io.OutputStream;

public interface AeadResult {
   OutputStream getOutputStream();

   AeadResult setTag(byte[] var1);

   AeadResult setIv(byte[] var1);
}
