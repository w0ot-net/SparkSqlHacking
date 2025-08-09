package org.apache.orc.impl;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.List;
import java.util.Random;
import javax.crypto.spec.SecretKeySpec;
import org.apache.hadoop.crypto.key.KeyProviderCryptoExtension;
import org.apache.hadoop.crypto.key.KeyProviderCryptoExtension.EncryptedKeyVersion;
import org.apache.orc.EncryptionAlgorithm;

class KeyProviderImpl implements KeyProvider {
   private final org.apache.hadoop.crypto.key.KeyProvider provider;
   private final Random random;

   KeyProviderImpl(org.apache.hadoop.crypto.key.KeyProvider provider, Random random) {
      this.provider = provider;
      this.random = random;
   }

   public List getKeyNames() throws IOException {
      return this.provider.getKeys();
   }

   public HadoopShims.KeyMetadata getCurrentKeyVersion(String keyName) throws IOException {
      org.apache.hadoop.crypto.key.KeyProvider.Metadata meta = this.provider.getMetadata(keyName);
      return new HadoopShims.KeyMetadata(keyName, meta.getVersions() - 1, HadoopShimsCurrent.findAlgorithm(meta));
   }

   private static void unmangleIv(byte[] input, byte[] output) {
      for(int i = 0; i < output.length && i < input.length; ++i) {
         output[i] = (byte)(255 ^ input[i]);
      }

   }

   public LocalKey createLocalKey(HadoopShims.KeyMetadata key) throws IOException {
      EncryptionAlgorithm algorithm = key.getAlgorithm();
      byte[] encryptedKey = new byte[algorithm.keyLength()];
      this.random.nextBytes(encryptedKey);
      byte[] iv = new byte[algorithm.getIvLength()];
      unmangleIv(encryptedKey, iv);
      KeyProviderCryptoExtension.EncryptedKeyVersion param = EncryptedKeyVersion.createForDecryption(key.getKeyName(), HadoopShimsCurrent.buildKeyVersionName(key), iv, encryptedKey);

      try {
         org.apache.hadoop.crypto.key.KeyProvider.KeyVersion decryptedKey;
         if (this.provider instanceof KeyProviderCryptoExtension) {
            decryptedKey = ((KeyProviderCryptoExtension)this.provider).decryptEncryptedKey(param);
         } else {
            if (!(this.provider instanceof KeyProviderCryptoExtension.CryptoExtension)) {
               throw new UnsupportedOperationException(this.provider.getClass().getCanonicalName() + " is not supported.");
            }

            decryptedKey = ((KeyProviderCryptoExtension.CryptoExtension)this.provider).decryptEncryptedKey(param);
         }

         return new LocalKey(algorithm, decryptedKey.getMaterial(), encryptedKey);
      } catch (GeneralSecurityException e) {
         throw new IOException("Can't create local encryption key for " + String.valueOf(key), e);
      }
   }

   public Key decryptLocalKey(HadoopShims.KeyMetadata key, byte[] encryptedKey) throws IOException {
      EncryptionAlgorithm algorithm = key.getAlgorithm();
      byte[] iv = new byte[algorithm.getIvLength()];
      unmangleIv(encryptedKey, iv);
      KeyProviderCryptoExtension.EncryptedKeyVersion param = EncryptedKeyVersion.createForDecryption(key.getKeyName(), HadoopShimsCurrent.buildKeyVersionName(key), iv, encryptedKey);

      try {
         org.apache.hadoop.crypto.key.KeyProvider.KeyVersion decryptedKey;
         if (this.provider instanceof KeyProviderCryptoExtension) {
            decryptedKey = ((KeyProviderCryptoExtension)this.provider).decryptEncryptedKey(param);
         } else {
            if (!(this.provider instanceof KeyProviderCryptoExtension.CryptoExtension)) {
               throw new UnsupportedOperationException(this.provider.getClass().getCanonicalName() + " is not supported.");
            }

            decryptedKey = ((KeyProviderCryptoExtension.CryptoExtension)this.provider).decryptEncryptedKey(param);
         }

         return new SecretKeySpec(decryptedKey.getMaterial(), algorithm.getAlgorithm());
      } catch (GeneralSecurityException var7) {
         return null;
      }
   }

   public HadoopShims.KeyProviderKind getKind() {
      return HadoopShims.KeyProviderKind.HADOOP;
   }
}
