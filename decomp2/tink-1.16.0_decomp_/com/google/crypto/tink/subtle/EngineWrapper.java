package com.google.crypto.tink.subtle;

import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.Mac;

public interface EngineWrapper {
   Object getInstance(String algorithm, Provider provider) throws GeneralSecurityException;

   public static class TCipher implements EngineWrapper {
      public Cipher getInstance(String algorithm, Provider provider) throws GeneralSecurityException {
         return provider == null ? Cipher.getInstance(algorithm) : Cipher.getInstance(algorithm, provider);
      }
   }

   public static class TMac implements EngineWrapper {
      public Mac getInstance(String algorithm, Provider provider) throws GeneralSecurityException {
         return provider == null ? Mac.getInstance(algorithm) : Mac.getInstance(algorithm, provider);
      }
   }

   public static class TKeyPairGenerator implements EngineWrapper {
      public KeyPairGenerator getInstance(String algorithm, Provider provider) throws GeneralSecurityException {
         return provider == null ? KeyPairGenerator.getInstance(algorithm) : KeyPairGenerator.getInstance(algorithm, provider);
      }
   }

   public static class TMessageDigest implements EngineWrapper {
      public MessageDigest getInstance(String algorithm, Provider provider) throws GeneralSecurityException {
         return provider == null ? MessageDigest.getInstance(algorithm) : MessageDigest.getInstance(algorithm, provider);
      }
   }

   public static class TSignature implements EngineWrapper {
      public Signature getInstance(String algorithm, Provider provider) throws GeneralSecurityException {
         return provider == null ? Signature.getInstance(algorithm) : Signature.getInstance(algorithm, provider);
      }
   }

   public static class TKeyFactory implements EngineWrapper {
      public KeyFactory getInstance(String algorithm, Provider provider) throws GeneralSecurityException {
         return provider == null ? KeyFactory.getInstance(algorithm) : KeyFactory.getInstance(algorithm, provider);
      }
   }

   public static class TKeyAgreement implements EngineWrapper {
      public KeyAgreement getInstance(String algorithm, Provider provider) throws GeneralSecurityException {
         return provider == null ? KeyAgreement.getInstance(algorithm) : KeyAgreement.getInstance(algorithm, provider);
      }
   }
}
