package io.jsonwebtoken.impl;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Strings;
import java.io.IOException;
import java.io.Reader;

public class JwtTokenizer {
   static final char DELIMITER = '.';
   private static final String DELIM_ERR_MSG_PREFIX = "Invalid compact JWT string: Compact JWSs must contain exactly 2 period characters, and compact JWEs must contain exactly 4.  Found: ";

   private static int read(Reader r, char[] buf) {
      try {
         return r.read(buf);
      } catch (IOException e) {
         String msg = "Unable to read compact JWT: " + e.getMessage();
         throw new MalformedJwtException(msg, e);
      }
   }

   public TokenizedJwt tokenize(Reader reader) {
      Assert.notNull(reader, "Reader argument cannot be null.");
      CharSequence protectedHeader = "";
      CharSequence body = "";
      CharSequence encryptedKey = "";
      CharSequence iv = "";
      CharSequence digest = "";
      int delimiterCount = 0;
      char[] buf = new char[4096];
      int len = 0;
      StringBuilder sb = new StringBuilder(4096);

      while(len != -1) {
         len = read(reader, buf);

         for(int i = 0; i < len; ++i) {
            char c = buf[i];
            if (Character.isWhitespace(c)) {
               String msg = "Compact JWT strings may not contain whitespace.";
               throw new MalformedJwtException(msg);
            }

            if (c == '.') {
               CharSequence seq = Strings.clean(sb);
               String token = seq != null ? seq.toString() : "";
               switch (delimiterCount) {
                  case 0:
                     protectedHeader = token;
                     break;
                  case 1:
                     body = token;
                     encryptedKey = token;
                     break;
                  case 2:
                     body = "";
                     iv = token;
                     break;
                  case 3:
                     body = token;
               }

               ++delimiterCount;
               sb.setLength(0);
            } else {
               sb.append(c);
            }
         }
      }

      if (delimiterCount != 2 && delimiterCount != 4) {
         String msg = "Invalid compact JWT string: Compact JWSs must contain exactly 2 period characters, and compact JWEs must contain exactly 4.  Found: " + delimiterCount;
         throw new MalformedJwtException(msg);
      } else {
         if (sb.length() > 0) {
            digest = sb.toString();
         }

         if (delimiterCount == 2) {
            return new DefaultTokenizedJwt(protectedHeader, body, digest);
         } else {
            return new DefaultTokenizedJwe(protectedHeader, body, digest, encryptedKey, iv);
         }
      }
   }
}
