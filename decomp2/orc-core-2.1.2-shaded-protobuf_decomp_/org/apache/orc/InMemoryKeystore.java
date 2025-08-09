package org.apache.orc;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.orc.impl.HadoopShims;
import org.apache.orc.impl.KeyProvider;
import org.apache.orc.impl.LocalKey;
import org.apache.orc.impl.HadoopShims.KeyProviderKind;

public class InMemoryKeystore implements KeyProvider {
   public static final boolean SUPPORTS_AES_256;
   private final Random random;
   private final TreeMap keys;
   private final Map currentVersion;

   public InMemoryKeystore() {
      this(new SecureRandom());
   }

   public InMemoryKeystore(Random random) {
      this.keys = new TreeMap();
      this.currentVersion = new HashMap();
      this.random = random;
   }

   private static String buildVersionName(String name, int version) {
      return name + "@" + version;
   }

   public List getKeyNames() {
      return new ArrayList(this.currentVersion.keySet());
   }

   public HadoopShims.KeyMetadata getCurrentKeyVersion(String keyName) {
      String versionName = buildVersionName(keyName, (Integer)this.currentVersion.get(keyName));
      KeyVersion keyVersion = (KeyVersion)this.keys.get(versionName);
      if (keyVersion == null) {
         throw new IllegalArgumentException("Unknown key " + keyName);
      } else {
         return (HadoopShims.KeyMetadata)this.keys.get(versionName);
      }
   }

   public LocalKey createLocalKey(HadoopShims.KeyMetadata key) {
      String keyVersion = buildVersionName(key.getKeyName(), key.getVersion());
      KeyVersion secret = (KeyVersion)this.keys.get(keyVersion);
      if (secret == null) {
         throw new IllegalArgumentException("Unknown key " + String.valueOf(key));
      } else {
         EncryptionAlgorithm algorithm = secret.getAlgorithm();
         byte[] encryptedKey = new byte[algorithm.keyLength()];
         this.random.nextBytes(encryptedKey);
         byte[] iv = Arrays.copyOf(encryptedKey, algorithm.getIvLength());
         Cipher localCipher = algorithm.createCipher();

         try {
            localCipher.init(2, new SecretKeySpec(secret.getMaterial(), algorithm.getAlgorithm()), new IvParameterSpec(iv));
         } catch (InvalidKeyException e) {
            throw new IllegalStateException("ORC bad encryption key for " + keyVersion, e);
         } catch (InvalidAlgorithmParameterException e) {
            throw new IllegalStateException("ORC bad encryption parameter for " + keyVersion, e);
         }

         try {
            byte[] decryptedKey = localCipher.doFinal(encryptedKey);
            return new LocalKey(algorithm, decryptedKey, encryptedKey);
         } catch (IllegalBlockSizeException e) {
            throw new IllegalStateException("ORC bad block size for " + keyVersion, e);
         } catch (BadPaddingException e) {
            throw new IllegalStateException("ORC bad padding for " + keyVersion, e);
         }
      }
   }

   public Key decryptLocalKey(HadoopShims.KeyMetadata key, byte[] encryptedKey) {
      String keyVersion = buildVersionName(key.getKeyName(), key.getVersion());
      KeyVersion secret = (KeyVersion)this.keys.get(keyVersion);
      if (secret == null) {
         return null;
      } else {
         EncryptionAlgorithm algorithm = secret.getAlgorithm();
         byte[] iv = Arrays.copyOf(encryptedKey, algorithm.getIvLength());
         Cipher localCipher = algorithm.createCipher();

         try {
            localCipher.init(2, new SecretKeySpec(secret.getMaterial(), algorithm.getAlgorithm()), new IvParameterSpec(iv));
         } catch (InvalidKeyException e) {
            throw new IllegalStateException("ORC bad encryption key for " + keyVersion, e);
         } catch (InvalidAlgorithmParameterException e) {
            throw new IllegalStateException("ORC bad encryption parameter for " + keyVersion, e);
         }

         try {
            byte[] decryptedKey = localCipher.doFinal(encryptedKey);
            return new SecretKeySpec(decryptedKey, algorithm.getAlgorithm());
         } catch (IllegalBlockSizeException e) {
            throw new IllegalStateException("ORC bad block size for " + keyVersion, e);
         } catch (BadPaddingException e) {
            throw new IllegalStateException("ORC bad padding for " + keyVersion, e);
         }
      }
   }

   public HadoopShims.KeyProviderKind getKind() {
      return KeyProviderKind.HADOOP;
   }

   public InMemoryKeystore addKey(String keyName, EncryptionAlgorithm algorithm, byte[] masterKey) throws IOException {
      return this.addKey(keyName, 0, algorithm, masterKey);
   }

   public InMemoryKeystore addKey(String keyName, int version, EncryptionAlgorithm algorithm, byte[] masterKey) throws IOException {
      if (!SUPPORTS_AES_256 && algorithm != EncryptionAlgorithm.AES_CTR_128) {
         algorithm = EncryptionAlgorithm.AES_CTR_128;
      }

      byte[] buffer = new byte[algorithm.keyLength()];
      if (algorithm.keyLength() > masterKey.length) {
         System.arraycopy(masterKey, 0, buffer, 0, masterKey.length);
         Arrays.fill(buffer, masterKey.length, buffer.length - 1, (byte)0);
      } else {
         System.arraycopy(masterKey, 0, buffer, 0, algorithm.keyLength());
      }

      KeyVersion key = new KeyVersion(keyName, version, algorithm, buffer);
      Integer currentKeyVersion = (Integer)this.currentVersion.get(keyName);
      if (currentKeyVersion != null && currentKeyVersion >= version) {
         throw new IOException(String.format("Key %s with equal or higher version %d already exists", keyName, version));
      } else {
         this.keys.put(buildVersionName(keyName, version), key);
         this.currentVersion.put(keyName, version);
         return this;
      }
   }

   static {
      try {
         SUPPORTS_AES_256 = Cipher.getMaxAllowedKeyLength("AES") >= 256;
      } catch (NoSuchAlgorithmException e) {
         throw new IllegalArgumentException("Unknown algorithm", e);
      }
   }

   static class KeyVersion extends HadoopShims.KeyMetadata {
      private final byte[] material;

      KeyVersion(String keyName, int version, EncryptionAlgorithm algorithm, byte[] material) {
         super(keyName, version, algorithm);
         this.material = material;
      }

      private byte[] getMaterial() {
         return this.material;
      }
   }
}
