package org.apache.hive.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;

public class CookieSigner {
   private static final String SIGNATURE = "&s=";
   private static final String SHA_STRING = "SHA-512";
   private byte[] secretBytes;
   private static final SparkLogger LOG = SparkLoggerFactory.getLogger(CookieSigner.class);

   public CookieSigner(byte[] secret) {
      if (secret == null) {
         throw new IllegalArgumentException(" NULL Secret Bytes");
      } else {
         this.secretBytes = (byte[])(([B)secret).clone();
      }
   }

   public String signCookie(String str) {
      if (str != null && !str.isEmpty()) {
         String signature = this.getSignature(str);
         if (LOG.isDebugEnabled()) {
            LOG.debug("Signature generated for " + str + " is " + signature);
         }

         return str + "&s=" + signature;
      } else {
         throw new IllegalArgumentException("NULL or empty string to sign");
      }
   }

   public String verifyAndExtract(String signedStr) {
      int index = signedStr.lastIndexOf("&s=");
      if (index == -1) {
         throw new IllegalArgumentException("Invalid input sign: " + signedStr);
      } else {
         String originalSignature = signedStr.substring(index + "&s=".length());
         String rawValue = signedStr.substring(0, index);
         String currentSignature = this.getSignature(rawValue);
         if (!MessageDigest.isEqual(originalSignature.getBytes(), currentSignature.getBytes())) {
            throw new IllegalArgumentException("Invalid sign");
         } else {
            return rawValue;
         }
      }
   }

   private String getSignature(String str) {
      try {
         MessageDigest md = MessageDigest.getInstance("SHA-512");
         md.update(str.getBytes());
         md.update(this.secretBytes);
         byte[] digest = md.digest();
         return (new Base64(0)).encodeToString(digest);
      } catch (NoSuchAlgorithmException ex) {
         throw new RuntimeException("Invalid SHA digest String: SHA-512 " + ex.getMessage(), ex);
      }
   }
}
