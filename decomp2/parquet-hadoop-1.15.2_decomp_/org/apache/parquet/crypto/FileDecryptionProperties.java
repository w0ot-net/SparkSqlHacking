package org.apache.parquet.crypto;

import java.util.HashMap;
import java.util.Map;
import org.apache.parquet.hadoop.metadata.ColumnPath;

public class FileDecryptionProperties {
   private static final boolean CHECK_SIGNATURE = true;
   private static final boolean ALLOW_PLAINTEXT_FILES = false;
   private final byte[] footerKey;
   private final DecryptionKeyRetriever keyRetriever;
   private final byte[] aadPrefix;
   private final AADPrefixVerifier aadPrefixVerifier;
   private final Map columnPropertyMap;
   private final boolean checkPlaintextFooterIntegrity;
   private final boolean allowPlaintextFiles;

   private FileDecryptionProperties(byte[] footerKey, DecryptionKeyRetriever keyRetriever, boolean checkPlaintextFooterIntegrity, byte[] aadPrefix, AADPrefixVerifier aadPrefixVerifier, Map columnPropertyMap, boolean allowPlaintextFiles) {
      if (null == footerKey && null == keyRetriever && null == columnPropertyMap) {
         throw new IllegalArgumentException("No decryption properties are specified");
      } else if (null != footerKey && footerKey.length != 16 && footerKey.length != 24 && footerKey.length != 32) {
         throw new IllegalArgumentException("Wrong footer key length " + footerKey.length);
      } else if (null == footerKey && checkPlaintextFooterIntegrity && null == keyRetriever) {
         throw new IllegalArgumentException("Can't check footer integrity with null footer key and null key retriever");
      } else {
         this.footerKey = footerKey;
         this.checkPlaintextFooterIntegrity = checkPlaintextFooterIntegrity;
         this.keyRetriever = keyRetriever;
         this.aadPrefix = aadPrefix;
         this.columnPropertyMap = columnPropertyMap;
         this.aadPrefixVerifier = aadPrefixVerifier;
         this.allowPlaintextFiles = allowPlaintextFiles;
      }
   }

   public static Builder builder() {
      return new Builder();
   }

   public byte[] getFooterKey() {
      return this.footerKey;
   }

   public byte[] getColumnKey(ColumnPath path) {
      if (null == this.columnPropertyMap) {
         return null;
      } else {
         ColumnDecryptionProperties columnDecryptionProperties = (ColumnDecryptionProperties)this.columnPropertyMap.get(path);
         return null == columnDecryptionProperties ? null : columnDecryptionProperties.getKeyBytes();
      }
   }

   public DecryptionKeyRetriever getKeyRetriever() {
      return this.keyRetriever;
   }

   public byte[] getAADPrefix() {
      return this.aadPrefix;
   }

   public boolean checkFooterIntegrity() {
      return this.checkPlaintextFooterIntegrity;
   }

   boolean plaintextFilesAllowed() {
      return this.allowPlaintextFiles;
   }

   AADPrefixVerifier getAADPrefixVerifier() {
      return this.aadPrefixVerifier;
   }

   public static class Builder {
      private byte[] footerKeyBytes;
      private DecryptionKeyRetriever keyRetriever;
      private byte[] aadPrefixBytes;
      private AADPrefixVerifier aadPrefixVerifier;
      private Map columnPropertyMap;
      private boolean checkPlaintextFooterIntegrity;
      private boolean plaintextFilesAllowed;

      private Builder() {
         this.checkPlaintextFooterIntegrity = true;
         this.plaintextFilesAllowed = false;
      }

      public Builder withFooterKey(byte[] footerKey) {
         if (null == footerKey) {
            return this;
         } else if (null != this.footerKeyBytes) {
            throw new IllegalStateException("Footer key already set");
         } else {
            this.footerKeyBytes = new byte[footerKey.length];
            System.arraycopy(footerKey, 0, this.footerKeyBytes, 0, footerKey.length);
            return this;
         }
      }

      public Builder withColumnKeys(Map columnProperties) {
         if (null == columnProperties) {
            return this;
         } else if (null != this.columnPropertyMap) {
            throw new IllegalStateException("Column properties already set");
         } else {
            this.columnPropertyMap = new HashMap(columnProperties);
            return this;
         }
      }

      public Builder withKeyRetriever(DecryptionKeyRetriever keyRetriever) {
         if (null == keyRetriever) {
            return this;
         } else if (null != this.keyRetriever) {
            throw new IllegalStateException("Key retriever already set");
         } else {
            this.keyRetriever = keyRetriever;
            return this;
         }
      }

      public Builder withoutFooterSignatureVerification() {
         this.checkPlaintextFooterIntegrity = false;
         return this;
      }

      public Builder withAADPrefix(byte[] aadPrefixBytes) {
         if (null == aadPrefixBytes) {
            return this;
         } else if (null != this.aadPrefixBytes) {
            throw new IllegalStateException("AAD Prefix already set");
         } else {
            this.aadPrefixBytes = aadPrefixBytes;
            return this;
         }
      }

      public Builder withAADPrefixVerifier(AADPrefixVerifier aadPrefixVerifier) {
         if (null == aadPrefixVerifier) {
            return this;
         } else if (null != this.aadPrefixVerifier) {
            throw new IllegalStateException("AAD Prefix verifier already set");
         } else {
            this.aadPrefixVerifier = aadPrefixVerifier;
            return this;
         }
      }

      public Builder withPlaintextFilesAllowed() {
         this.plaintextFilesAllowed = true;
         return this;
      }

      public FileDecryptionProperties build() {
         return new FileDecryptionProperties(this.footerKeyBytes, this.keyRetriever, this.checkPlaintextFooterIntegrity, this.aadPrefixBytes, this.aadPrefixVerifier, this.columnPropertyMap, this.plaintextFilesAllowed);
      }
   }
}
