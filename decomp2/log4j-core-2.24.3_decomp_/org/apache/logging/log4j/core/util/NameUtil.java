package org.apache.logging.log4j.core.util;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import org.apache.logging.log4j.util.Strings;

public final class NameUtil {
   private NameUtil() {
   }

   public static String getSubName(final String name) {
      if (Strings.isEmpty(name)) {
         return null;
      } else {
         int i = name.lastIndexOf(46);
         return i > 0 ? name.substring(0, i) : "";
      }
   }

   /** @deprecated */
   @Deprecated
   @SuppressFBWarnings(
      value = {"WEAK_MESSAGE_DIGEST_MD5"},
      justification = "Used to create unique identifiers."
   )
   public static String md5(final String input) {
      Objects.requireNonNull(input, "input");

      try {
         byte[] inputBytes = input.getBytes();
         MessageDigest digest = MessageDigest.getInstance("MD5");
         byte[] bytes = digest.digest(inputBytes);
         StringBuilder md5 = new StringBuilder(bytes.length * 2);

         for(byte b : bytes) {
            md5.append(Character.forDigit((255 & b) >> 4, 16));
            md5.append(Character.forDigit(15 & b, 16));
         }

         return md5.toString();
      } catch (NoSuchAlgorithmException error) {
         throw new RuntimeException(error);
      }
   }
}
