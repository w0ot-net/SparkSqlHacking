package org.apache.ivy.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class ChecksumHelper {
   private static final int BUFFER_SIZE = 2048;
   private static final Map algorithms = new HashMap();
   private static final char[] CHARS;

   private static boolean isAlgorithmSupportedInJRE(String algorithm) {
      if (algorithm == null) {
         return false;
      } else {
         try {
            MessageDigest.getInstance(algorithm);
            return true;
         } catch (NoSuchAlgorithmException var2) {
            return false;
         }
      }
   }

   public static void check(File dest, File checksumFile, String algorithm) throws IOException {
      String csFileContent = FileUtil.readEntirely(new BufferedReader(new FileReader(checksumFile))).trim().toLowerCase(Locale.US);
      String expected;
      if (csFileContent.indexOf(32) <= -1 || !csFileContent.startsWith("md") && !csFileContent.startsWith("sha")) {
         int spaceIndex = csFileContent.indexOf(32);
         if (spaceIndex != -1) {
            expected = csFileContent.substring(0, spaceIndex);
            if (expected.endsWith(":")) {
               StringBuilder result = new StringBuilder();

               for(char ch : csFileContent.substring(spaceIndex + 1).toCharArray()) {
                  if (!Character.isWhitespace(ch)) {
                     result.append(ch);
                  }
               }

               expected = result.toString();
            }
         } else {
            expected = csFileContent;
         }
      } else {
         int lastSpaceIndex = csFileContent.lastIndexOf(32);
         expected = csFileContent.substring(lastSpaceIndex + 1);
      }

      String computed = computeAsString(dest, algorithm).trim().toLowerCase(Locale.US);
      if (!expected.equals(computed)) {
         throw new IOException("invalid " + algorithm + ": expected=" + expected + " computed=" + computed);
      }
   }

   public static String computeAsString(File f, String algorithm) throws IOException {
      return byteArrayToHexString(compute(f, algorithm));
   }

   private static byte[] compute(File f, String algorithm) throws IOException {
      InputStream is = new FileInputStream(f);
      Throwable var3 = null;

      byte[] var7;
      try {
         MessageDigest md = getMessageDigest(algorithm);
         md.reset();
         byte[] buf = new byte[2048];
         int len = 0;

         while((len = is.read(buf)) != -1) {
            md.update(buf, 0, len);
         }

         var7 = md.digest();
      } catch (Throwable var16) {
         var3 = var16;
         throw var16;
      } finally {
         if (is != null) {
            if (var3 != null) {
               try {
                  is.close();
               } catch (Throwable var15) {
                  var3.addSuppressed(var15);
               }
            } else {
               is.close();
            }
         }

      }

      return var7;
   }

   public static boolean isKnownAlgorithm(String algorithm) {
      return algorithms.containsKey(algorithm);
   }

   private static MessageDigest getMessageDigest(String algorithm) {
      String mdAlgorithm = (String)algorithms.get(algorithm);
      if (mdAlgorithm == null) {
         throw new IllegalArgumentException("unknown algorithm " + algorithm);
      } else {
         try {
            return MessageDigest.getInstance(mdAlgorithm);
         } catch (NoSuchAlgorithmException var3) {
            throw new IllegalArgumentException("unknown algorithm " + algorithm);
         }
      }
   }

   public static String byteArrayToHexString(byte[] in) {
      byte ch = 0;
      if (in != null && in.length > 0) {
         StringBuilder out = new StringBuilder(in.length * 2);

         for(byte bt : in) {
            ch = (byte)(bt & 240);
            ch = (byte)(ch >>> 4);
            ch = (byte)(ch & 15);
            out.append(CHARS[ch]);
            ch = (byte)(bt & 15);
            out.append(CHARS[ch]);
         }

         return out.toString();
      } else {
         return null;
      }
   }

   private ChecksumHelper() {
   }

   static {
      algorithms.put("md5", "MD5");
      algorithms.put("sha1", "SHA-1");
      if (isAlgorithmSupportedInJRE("SHA-256")) {
         algorithms.put("SHA-256", "SHA-256");
      }

      if (isAlgorithmSupportedInJRE("SHA-512")) {
         algorithms.put("SHA-512", "SHA-512");
      }

      if (isAlgorithmSupportedInJRE("SHA-384")) {
         algorithms.put("SHA-384", "SHA-384");
      }

      CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
   }
}
